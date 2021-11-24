package com.cun.rasp.view.detailpakan;

import android.content.DialogInterface;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cun.rasp.Database.DatabaseHelper;
import com.cun.rasp.R;
import com.cun.rasp.model.DetailPakan;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class DetailPakanActivity extends AppCompatActivity {
    private DetailPakanAdapter mAdapter;
    private List<DetailPakan> detailPakanList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noDetailPakanView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pakan_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_detail_pakan);
        recyclerView = findViewById(R.id.recycler_view_detail_pakan);
        noDetailPakanView = findViewById(R.id.empty_detail_pakan_view);

        db = new DatabaseHelper(this);

        detailPakanList.addAll(db.getAllDetailPakans());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detail_pakan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailPakanDialog(false, null, -1);
            }
        });

        mAdapter = new DetailPakanAdapter(this, detailPakanList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyDetailPakan();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createDetailPakan(int sapi, int bahan_pakan) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertDetailPakan(sapi, bahan_pakan);

        // get the newly inserted note from db
        DetailPakan n = db.getDetailPakan(id);

        if (n != null) {
            // adding new note to array list at 0 position
            detailPakanList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyDetailPakan();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateDetailPakan(int sapi, int bahan_pakan, int position) {
        DetailPakan n = detailPakanList.get(position);
        // updating note text
        n.setSapi(sapi);
        n.setBahanPakan(bahan_pakan);
    

        // updating note in db
        db.updateDetailPakan(n);

        // refreshing the list
        detailPakanList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyDetailPakan();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteDetailPakan(int position) {
        // deleting the note from db
        DetailPakan DetailPakan_position = detailPakanList.get(position);
        db.deleteDetailPakan(DetailPakan_position.getId());

        // removing the note from the list
        detailPakanList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyDetailPakan();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Ubah", "Hapus"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Menu");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showDetailPakanDialog(true, detailPakanList.get(position), position);
                } else {
                    deleteDetailPakan(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    private void showDetailPakanDialog(final boolean shouldUpdate, final DetailPakan DetailPakan, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_detail_pakan, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(DetailPakanActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_sapi = view.findViewById(R.id.sapi_detail_pakan);
        final EditText input_bahan_pakan = view.findViewById(R.id.bahan_pakan_detail_pakan);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_detail_pakan_title) : getString(R.string.lbl_edit_detail_pakan_title));


        if (shouldUpdate && DetailPakan != null){
           
        input_sapi.setText(nm.format(DetailPakan.getSapi()));
        input_bahan_pakan.setText(nm.format(DetailPakan.getBahanPakan()));
        }


        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show toast message when no text is entered
                if (TextUtils.isEmpty(input_sapi.getText().toString())) {
                    Toast.makeText(DetailPakanActivity.this, "Masukkan Keterangan Sapi!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_bahan_pakan.getText().toString())) {
                    Toast.makeText(DetailPakanActivity.this, "Masukkan Keterangan Bahan Pakan!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && DetailPakan != null) {
                    // update note by it's id
                    // updateDetailPakan(inputDetailPakan.getText().toString(), position);
                    updateDetailPakan(Integer.parseInt(input_sapi.getText().toString()), Integer.parseInt(input_bahan_pakan.getText().toString()), position);

                } else {
                    // create new note
                    createDetailPakan(Integer.parseInt(input_sapi.getText().toString()), Integer.parseInt(input_bahan_pakan.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty detailPakan view
     */
    private void toggleEmptyDetailPakan() {
        // you can check detailPakanList.size() > 0

        if (db.getDetailPakanCount() > 0) {
            noDetailPakanView.setVisibility(View.GONE);
        } else {
            noDetailPakanView.setVisibility(View.VISIBLE);
        }
    }
}