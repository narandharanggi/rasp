package com.cun.rasp.view.bobotsapi;

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
import com.cun.rasp.model.BobotSapi;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class BobotSapiActivity extends AppCompatActivity {
    private BobotSapiAdapter mAdapter;
    private List<BobotSapi> bobotSapiList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noBobotSapiView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bobot_sapi_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_bobot_sapi);
        recyclerView = findViewById(R.id.recycler_view_bobot_sapi);
        noBobotSapiView = findViewById(R.id.empty_bobot_sapi_view);

        db = new DatabaseHelper(this);

        bobotSapiList.addAll(db.getAllBobotSapis());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_bobot_sapi);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBobotSapiDialog(false, null, -1);
            }
        });

        mAdapter = new BobotSapiAdapter(this, bobotSapiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyBobotSapi();

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
    private void createBobotSapi(Double bobot, Double tdn, Double pk, Double ca, Double p) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertBobotSapi(bobot, tdn, pk, ca, p);

        // get the newly inserted note from db
        BobotSapi n = db.getBobotSapi(id);

        if (n != null) {
            // adding new note to array list at 0 position
            bobotSapiList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyBobotSapi();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateBobotSapi(Double bobot, Double tdn, Double pk, Double ca, Double p, int position) {
        BobotSapi n = bobotSapiList.get(position);
        // updating note text
        n.setBobot(bobot);
        n.setTdn(tdn);
        n.setPk(pk);
        n.setCa(ca);
        n.setP(p);

        // updating note in db
        db.updateBobotSapi(n);

        // refreshing the list
        bobotSapiList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyBobotSapi();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteBobotSapi(int position) {
        // deleting the note from db
        BobotSapi BobotSapi_position = bobotSapiList.get(position);
        db.deleteBobotSapi(BobotSapi_position.getId());

        // removing the note from the list
        bobotSapiList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyBobotSapi();
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
                    showBobotSapiDialog(true, bobotSapiList.get(position), position);
                } else {
                    deleteBobotSapi(position);
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
    private void showBobotSapiDialog(final boolean shouldUpdate, final BobotSapi BobotSapi, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_bobot_sapi, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(BobotSapiActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_bobot = view.findViewById(R.id.bobot_bobot_sapi);
        final EditText input_tdn = view.findViewById(R.id.tdn_bobot_sapi);
        final EditText input_pk = view.findViewById(R.id.pk_bobot_sapi);
        final EditText input_ca = view.findViewById(R.id.ca_bobot_sapi);
        final EditText input_p = view.findViewById(R.id.p_bobot_sapi);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_bobot_sapi_title) : getString(R.string.lbl_edit_bobot_sapi_title));


        if (shouldUpdate && BobotSapi != null){
           
        input_bobot.setText(nm.format(BobotSapi.getBobot()).replace(",",""));
        input_tdn.setText(nm.format(BobotSapi.getTdn()).replace(",",""));
        input_pk.setText(nm.format(BobotSapi.getPk()).replace(",",""));
        input_ca.setText(nm.format(BobotSapi.getCa()).replace(",",""));
        input_p.setText(nm.format(BobotSapi.getP()).replace(",",""));
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
                if (TextUtils.isEmpty(input_bobot.getText().toString())) {
                    Toast.makeText(BobotSapiActivity.this, "Masukkan nama pakan!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_tdn.getText().toString())) {
                    Toast.makeText(BobotSapiActivity.this, "Masukkan tdn!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_pk.getText().toString())) {
                    Toast.makeText(BobotSapiActivity.this, "Masukkan pk!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_ca.getText().toString())) {
                    Toast.makeText(BobotSapiActivity.this, "Masukkan ca!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_p.getText().toString())) {
                    Toast.makeText(BobotSapiActivity.this, "Masukkan p!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && BobotSapi != null) {
                    // update note by it's id
                    // updateBobotSapi(inputBobotSapi.getText().toString(), position);
                    updateBobotSapi(Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_bobot.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()), position);

                } else {
                    // create new note
                    createBobotSapi(Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_bobot.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty bobotSapi view
     */
    private void toggleEmptyBobotSapi() {
        // you can check bobotSapiList.size() > 0

        if (db.getBobotSapiCount() > 0) {
            noBobotSapiView.setVisibility(View.GONE);
        } else {
            noBobotSapiView.setVisibility(View.VISIBLE);
        }
    }
}