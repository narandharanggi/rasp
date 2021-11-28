package com.cun.rasp.view.sapi;

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
import com.cun.rasp.model.Sapi;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class SapiActivity extends AppCompatActivity {
    private SapiAdapter mAdapter;
    private List<Sapi> sapiList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noSapiView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapi_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_sapi);
        recyclerView = findViewById(R.id.recycler_view_sapi);
        noSapiView = findViewById(R.id.empty_sapi_view);

        db = new DatabaseHelper(this);

        sapiList.addAll(db.getAllSapis());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_sapi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSapiDialog(false, null, -1);
            }
        });

        mAdapter = new SapiAdapter(this, sapiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptySapi();

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
    private void createSapi(int bobot, int produksi_susu, Double bk) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertSapi(bobot,  produksi_susu,bk );

        // get the newly inserted note from db
        Sapi n = db.getSapi(id);

        if (n != null) {
            // adding new note to array list at 0 position
            sapiList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptySapi();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateSapi(int bobot, int produksi_susu, Double bk, int position) {
        Sapi n = sapiList.get(position);
        // updating note text
        n.setProduksiSusu(produksi_susu);
        n.setBk(bk);
        n.setBobot(bobot);

        // updating note in db
        db.updateSapi(n);

        // refreshing the list
        sapiList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptySapi();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteSapi(int position) {
        // deleting the note from db
        Sapi sapi_position = sapiList.get(position);
        db.deleteSapi(sapi_position.getId());

        // removing the note from the list
        sapiList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptySapi();
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
                    showSapiDialog(true, sapiList.get(position), position);
                } else {
                    deleteSapi(position);
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
    private void showSapiDialog(final boolean shouldUpdate, final Sapi sapi, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_sapi, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SapiActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_produksi_susu = view.findViewById(R.id.produksi_susu_sapi);
        final EditText input_bk = view.findViewById(R.id.bk_sapi);
        final EditText input_bobot = view.findViewById(R.id.bobot_sapi);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_sapi_title) : getString(R.string.lbl_edit_sapi_title));


        if (shouldUpdate && sapi != null){
           
        input_produksi_susu.setText(nm.format(sapi.getProduksiSusu()).replace(",",""));
        input_bk.setText(nm.format(sapi.getBk()).replace(",",""));
        input_bobot.setText(String.valueOf(sapi.getBobot()).replace(",",""));
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
                if (TextUtils.isEmpty(input_produksi_susu.getText().toString())) {
                    Toast.makeText(SapiActivity.this, "Masukkan Produksi Susu!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_bk.getText().toString())) {
                    Toast.makeText(SapiActivity.this, "Masukkan bk!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_bobot.getText().toString())) {
                    Toast.makeText(SapiActivity.this, "Masukkan bobot!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && sapi != null) {
                    // update note by it's id
                    // updateSapi(inputSapi.getText().toString(), position);
                    updateSapi(Integer.parseInt(input_bobot.getText().toString()), Integer.parseInt(input_produksi_susu.getText().toString()), Double.parseDouble(input_bk.getText().toString()), position);

                } else {
                    // create new note
                    createSapi(Integer.parseInt(input_bobot.getText().toString()), Integer.parseInt(input_produksi_susu.getText().toString()), Double.parseDouble(input_bk.getText().toString()) );
                }
            }
        });
    }

    /**
     * Toggling list and empty sapi view
     */
    private void toggleEmptySapi() {
        // you can check sapiList.size() > 0

        if (db.getSapiCount() > 0) {
            noSapiView.setVisibility(View.GONE);
        } else {
            noSapiView.setVisibility(View.VISIBLE);
        }
    }
}