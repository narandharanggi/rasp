package com.cun.rasp.view.perBB;

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
import com.cun.rasp.model.perBB;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class perBBActivity extends AppCompatActivity {
    private perBBAdapter mAdapter;
    private List<perBB> perBBList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noperBBView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perbb_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_perbb);
        recyclerView = findViewById(R.id.recycler_view_perbb);
        noperBBView = findViewById(R.id.empty_perbb_view);

        db = new DatabaseHelper(this);

        perBBList.addAll(db.getAllperBBs());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_perbb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showperBBDialog(false, null, -1);
            }
        });

        mAdapter = new perBBAdapter(this, perBBList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyperBB();

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
    private void createperBB(String keterangan, Double tdn, Double pk) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertPerBB(keterangan, tdn,  pk);

        // get the newly inserted note from db
        perBB n = db.getperBB(id);

        if (n != null) {
            // adding new note to array list at 0 position
            perBBList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyperBB();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateperBB(String keterangan, Double tdn, Double pk, int position) {
        perBB n = perBBList.get(position);
        // updating note text
        n.setKeterangan(keterangan);
        n.setTdn(tdn); 
        n.setPk(pk);


        // updating note in db
        db.updateperBB(n);

        // refreshing the list
        perBBList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyperBB();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteperBB(int position) {
        // deleting the note from db
        perBB perBB_position = perBBList.get(position);
        db.deleteperBB(perBB_position.getId());

        // removing the note from the list
        perBBList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyperBB();
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
                    showperBBDialog(true, perBBList.get(position), position);
                } else {
                    deleteperBB(position);
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
    private void showperBBDialog(final boolean shouldUpdate, final perBB perBB, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_perbb, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(perBBActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_keterangan = view.findViewById(R.id.keterangan_perbb);
        final EditText input_tdn = view.findViewById(R.id.tdn_perbb);
        final EditText input_pk = view.findViewById(R.id.pk_perbb);
     
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_perbb_title) : getString(R.string.lbl_edit_perbb_title));


        if (shouldUpdate && perBB != null){
           
            input_keterangan.setText(perBB.getKeterangan().replace(",",""));
        input_tdn.setText(nm.format(perBB.getTdn()).replace(",",""));

        input_pk.setText(nm.format(perBB.getPk()).replace(",",""));
      
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
                if (TextUtils.isEmpty(input_keterangan.getText().toString())) {
                    Toast.makeText(perBBActivity.this, "Masukkan nama pakan!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_tdn.getText().toString())) {
                    Toast.makeText(perBBActivity.this, "Masukkan tdn!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_pk.getText().toString())) {
                    Toast.makeText(perBBActivity.this, "Masukkan pk!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && perBB != null) {
                 
                    updateperBB(input_keterangan.getText().toString(), Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_pk.getText().toString()), position);

                } else {
                    // create new note
                    createperBB(input_keterangan.getText().toString(), Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_pk.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty perBB view
     */
    private void toggleEmptyperBB() {
        // you can check perBBList.size() > 0

        if (db.getperBBCount() > 0) {
            noperBBView.setVisibility(View.GONE);
        } else {
            noperBBView.setVisibility(View.VISIBLE);
        }
    }
}