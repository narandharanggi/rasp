package com.cun.rasp.view.produksisusu;

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
import com.cun.rasp.model.ProduksiSusu;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class ProduksiSusuActivity extends AppCompatActivity {
    private ProduksiSusuAdapter mAdapter;
    private List<ProduksiSusu> produksisusuList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noProduksiSusuView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produksi_susu_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_produksi_susu);
        recyclerView = findViewById(R.id.recycler_view_produksi_susu);
        noProduksiSusuView = findViewById(R.id.empty_produksi_susu_view);

        db = new DatabaseHelper(this);

        produksisusuList.addAll(db.getAllProduksiSusus());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_produksi_susu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProduksiSusuDialog(false, null, -1);
            }
        });

        mAdapter = new ProduksiSusuAdapter(this, produksisusuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyProduksiSusu();

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
    private void createProduksiSusu(int produksi_susu) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertProduksiSusu(produksi_susu);

        // get the newly inserted note from db
        ProduksiSusu n = db.getProduksiSusu(id);

        if (n != null) {
            // adding new note to array list at 0 position
            produksisusuList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyProduksiSusu();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateProduksiSusu(int produksi_susu, int position) {
        ProduksiSusu n = produksisusuList.get(position);
        // updating note text
        n.setProduksiSusu(produksi_susu);
        
    

        // updating note in db
        db.updateProduksiSusu(n);

        // refreshing the list
        produksisusuList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyProduksiSusu();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteProduksiSusu(int position) {
        // deleting the note from db
        ProduksiSusu ProduksiSusu_position = produksisusuList.get(position);
        db.deleteProduksiSusu(ProduksiSusu_position.getId());

        // removing the note from the list
        produksisusuList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyProduksiSusu();
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
                    showProduksiSusuDialog(true, produksisusuList.get(position), position);
                } else {
                    deleteProduksiSusu(position);
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
    private void showProduksiSusuDialog(final boolean shouldUpdate, final ProduksiSusu ProduksiSusu, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_produksi_susu, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(ProduksiSusuActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_produksi_susu = view.findViewById(R.id.produksi_susu_produksi_susu);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_produksi_susu_title) : getString(R.string.lbl_edit_produksi_susu_title));


        if (shouldUpdate && ProduksiSusu != null){
           
        input_produksi_susu.setText(nm.format(ProduksiSusu.getProduksiSusu()).replace(",",""));
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
                    Toast.makeText(ProduksiSusuActivity.this, "Masukkan Keterangan Produksi Susu!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && ProduksiSusu != null) {
                    // update note by it's id
                    // updateProduksiSusu(inputProduksiSusu.getText().toString(), position);
                    updateProduksiSusu(Integer.parseInt(input_produksi_susu.getText().toString()), position);

                } else {
                    // create new note
                    createProduksiSusu(Integer.parseInt(input_produksi_susu.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty produksisusu view
     */
    private void toggleEmptyProduksiSusu() {
        // you can check produksisusuList.size() > 0

        if (db.getProduksiSusuCount() > 0) {
            noProduksiSusuView.setVisibility(View.GONE);
        } else {
            noProduksiSusuView.setVisibility(View.VISIBLE);
        }
    }
}