package com.cun.rasp.view.bahanpakan;

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
import com.cun.rasp.model.BahanPakan;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class BahanPakanActivity extends AppCompatActivity {
    private BahanPakanAdapter mAdapter;
    private List<BahanPakan> bahanPakanList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noBahanPakanView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bahan_pakan_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_bahan_pakan);
        recyclerView = findViewById(R.id.recycler_view_bahan_pakan);
        noBahanPakanView = findViewById(R.id.empty_bahan_pakan_view);

        db = new DatabaseHelper(this);

        bahanPakanList.addAll(db.getAllBahanPakans());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_bahan_pakan);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBahanPakanDialog(false, null, -1);
            }
        });

        mAdapter = new BahanPakanAdapter(this, bahanPakanList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyBahanPakan();

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
    private void createBahanPakan(String nama_pakan, Double bk ,Double tdn, Double pk, Double ca, Double p,int harga) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertBahanPakan(nama_pakan, bk,  tdn, pk, ca, p, harga);

        // get the newly inserted note from db
        BahanPakan n = db.getBahanPakan(id);

        if (n != null) {
            // adding new note to array list at 0 position
            bahanPakanList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyBahanPakan();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateBahanPakan(String nama_pakan, Double bk ,Double tdn, Double pk, Double ca, Double p,int harga, int position) {
        BahanPakan n = bahanPakanList.get(position);
        // updating note text
        n.setNamaPakan(nama_pakan);
        n.setTdn(tdn);
        n.setBk(bk);
        n.setPk(pk);
        n.setCa(ca);
        n.setP(p);
        n.setHarga(harga);

        // updating note in db
        db.updateBahanPakan(n);

        // refreshing the list
        bahanPakanList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyBahanPakan();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteBahanPakan(int position) {
        // deleting the note from db
        BahanPakan bahan_pakan_position = bahanPakanList.get(position);
        db.deleteBahanPakan(bahan_pakan_position.getId());

        // removing the note from the list
        bahanPakanList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyBahanPakan();
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
                    showBahanPakanDialog(true, bahanPakanList.get(position), position);
                } else {
                    deleteBahanPakan(position);
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
    private void showBahanPakanDialog(final boolean shouldUpdate, final BahanPakan bahan_pakan, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_bahan_pakan, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(BahanPakanActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_nama_pakan = view.findViewById(R.id.nama_pakan_BahanPakan);
        final EditText input_tdn = view.findViewById(R.id.tdn_BahanPakan);
        final EditText input_bk = view.findViewById(R.id.bk_BahanPakan);
        final EditText input_pk = view.findViewById(R.id.pk_BahanPakan);
        final EditText input_ca = view.findViewById(R.id.ca_BahanPakan);
        final EditText input_p = view.findViewById(R.id.p_BahanPakan);
        final EditText input_harga = view.findViewById(R.id.harga_BahanPakan);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_bahan_pakan_title) : getString(R.string.lbl_edit_bahan_pakan_title));


        if (shouldUpdate && bahan_pakan != null){
           
            input_nama_pakan.setText(bahan_pakan.getNamaPakan());
        input_tdn.setText(nm.format(bahan_pakan.getTdn()));
        input_bk.setText(nm.format(bahan_pakan.getBk()));
        input_pk.setText(nm.format(bahan_pakan.getPk()));
        input_ca.setText(nm.format(bahan_pakan.getCa()));
        input_p.setText(nm.format(bahan_pakan.getP()));
        input_harga.setText(String.valueOf(bahan_pakan.getHarga()));
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
                if (TextUtils.isEmpty(input_nama_pakan.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan nama pakan!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_tdn.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan tdn!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_bk.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan bk!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_pk.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan pk!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_ca.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan ca!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_p.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan p!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_harga.getText().toString())) {
                    Toast.makeText(BahanPakanActivity.this, "Masukkan harga!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && bahan_pakan != null) {
                    // update note by it's id
                    // updateBahanPakan(inputBahanPakan.getText().toString(), position);
                    updateBahanPakan(input_nama_pakan.getText().toString(), Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_bk.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()), Integer.parseInt(input_harga.getText().toString()), position);

                } else {
                    // create new note
                    createBahanPakan(input_nama_pakan.getText().toString(), Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_bk.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()), Integer.parseInt(input_harga.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty bahanPakan view
     */
    private void toggleEmptyBahanPakan() {
        // you can check bahanPakanList.size() > 0

        if (db.getBahanPakanCount() > 0) {
            noBahanPakanView.setVisibility(View.GONE);
        } else {
            noBahanPakanView.setVisibility(View.VISIBLE);
        }
    }
}