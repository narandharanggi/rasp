package com.cun.rasp.view.detailsapi;

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
import com.cun.rasp.model.DetailSapi;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class DetailSapiActivity extends AppCompatActivity {
    private DetailSapiAdapter mAdapter;
    private List<DetailSapi> detailsapiList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noDetailSapiView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_sapi_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_detail_sapi);
        recyclerView = findViewById(R.id.recycler_view_detail_sapi);
        noDetailSapiView = findViewById(R.id.empty_detail_sapi_view);

        db = new DatabaseHelper(this);

        detailsapiList.addAll(db.getAllDetailSapis());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detail_sapi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailSapiDialog(false, null, -1);
            }
        });

        mAdapter = new DetailSapiAdapter(this, detailsapiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyDetailSapi();

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
    private void createDetailSapi(int sapi, int lemak_susu, int perBB) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertDetailSapi(sapi, lemak_susu, perBB);

        // get the newly inserted note from db
        DetailSapi n = db.getDetailSapi(id);

        if (n != null) {
            // adding new note to array list at 0 position
            detailsapiList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyDetailSapi();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateDetailSapi(int sapi, int lemak_susu, int perBB, int position) {
        DetailSapi n = detailsapiList.get(position);
        // updating note text
        n.setSapi(sapi);
        n.setLemakSusu(lemak_susu);
        n.setperBB(perBB);
    

        // updating note in db
        db.updateDetailSapi(n);

        // refreshing the list
        detailsapiList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyDetailSapi();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteDetailSapi(int position) {
        // deleting the note from db
        DetailSapi DetailSapi_position = detailsapiList.get(position);
        db.deleteDetailSapi(DetailSapi_position.getId());

        // removing the note from the list
        detailsapiList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyDetailSapi();
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
                    showDetailSapiDialog(true, detailsapiList.get(position), position);
                } else {
                    deleteDetailSapi(position);
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
    private void showDetailSapiDialog(final boolean shouldUpdate, final DetailSapi DetailSapi, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_detail_sapi, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(DetailSapiActivity.this);
        alertDialogBuilderUserInput.setView(view);

        // int sapi, int lemak_susu, int perBB

        final EditText input_sapi = view.findViewById(R.id.sapi_detail_sapi);
        final EditText input_lemak_susu = view.findViewById(R.id.lemak_susu_detail_sapi);
        final EditText input_perBB = view.findViewById(R.id.perBB_detail_sapi);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_detail_sapi_title) : getString(R.string.lbl_edit_detail_sapi_title));


        if (shouldUpdate && DetailSapi != null){
           

        input_sapi.setText(nm.format(DetailSapi.getSapi()).replace(",",""));
        input_lemak_susu.setText(nm.format(DetailSapi.getLemakSusu()).replace(",",""));
        input_perBB.setText(nm.format(DetailSapi.getperBB()).replace(",",""));
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
                    Toast.makeText(DetailSapiActivity.this, "Masukkan Keterangan Sapi!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_lemak_susu.getText().toString())) {
                    Toast.makeText(DetailSapiActivity.this, "Masukkan Keterangan Lemak Susu!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_perBB.getText().toString())) {
                    Toast.makeText(DetailSapiActivity.this, "Masukkan Keterangan perBB!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && DetailSapi != null) {
                    // update note by it's id
                    // updateDetailSapi(inputDetailSapi.getText().toString(), position);
                    updateDetailSapi(Integer.parseInt(input_sapi.getText().toString()), Integer.parseInt(input_lemak_susu.getText().toString()), Integer.parseInt(input_perBB.getText().toString()), position);

                } else {
                    // create new note
                    createDetailSapi(Integer.parseInt(input_sapi.getText().toString()), Integer.parseInt(input_lemak_susu.getText().toString()), Integer.parseInt(input_perBB.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty detailsapi view
     */
    private void toggleEmptyDetailSapi() {
        // you can check detailsapiList.size() > 0

        if (db.getDetailSapiCount() > 0) {
            noDetailSapiView.setVisibility(View.GONE);
        } else {
            noDetailSapiView.setVisibility(View.VISIBLE);
        }
    }
}