package com.cun.rasp.view.lemaksusu;

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
import com.cun.rasp.model.LemakSusu;
import com.cun.rasp.utils.MyDividerItemDecoration;
import com.cun.rasp.utils.RecyclerTouchListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;



public class LemakSusuActivity extends AppCompatActivity {
    private LemakSusuAdapter mAdapter;
    private List<LemakSusu> lemaksusuList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noLemakSusuView;
    NumberFormat nm = NumberFormat.getNumberInstance();

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lemak_susu_main);


        coordinatorLayout = findViewById(R.id.coordinator_layout_lemak_susu);
        recyclerView = findViewById(R.id.recycler_view_lemak_susu);
        noLemakSusuView = findViewById(R.id.empty_lemak_susu_view);

        db = new DatabaseHelper(this);

        lemaksusuList.addAll(db.getAllLemakSusus());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_lemak_susu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLemakSusuDialog(false, null, -1);
            }
        });

        mAdapter = new LemakSusuAdapter(this, lemaksusuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyLemakSusu();

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
    private void createLemakSusu(Double persen_lemak, Double tdn, Double pk, Double ca, Double p) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertLemakSusu(persen_lemak, tdn, pk, ca, p);

        // get the newly inserted note from db
        LemakSusu n = db.getLemakSusu(id);

        if (n != null) {
            // adding new note to array list at 0 position
            lemaksusuList.add(0, n);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyLemakSusu();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateLemakSusu(Double persen_lemak, Double tdn, Double pk, Double ca, Double p, int position) {
        LemakSusu n = lemaksusuList.get(position);
        // updating note text
        n.setPersenLemak(persen_lemak);
        n.setTdn(tdn);
        n.setPk(pk);
        n.setCa(ca);
        n.setP(p);

        // updating note in db
        db.updateLemakSusu(n);

        // refreshing the list
        lemaksusuList.set(position, n);
        mAdapter.notifyItemChanged(position);

        toggleEmptyLemakSusu();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteLemakSusu(int position) {
        // deleting the note from db
        LemakSusu LemakSusu_position = lemaksusuList.get(position);
        db.deleteLemakSusu(LemakSusu_position.getId());

        // removing the note from the list
        lemaksusuList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyLemakSusu();
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
                    showLemakSusuDialog(true, lemaksusuList.get(position), position);
                } else {
                    deleteLemakSusu(position);
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
    private void showLemakSusuDialog(final boolean shouldUpdate, final LemakSusu LemakSusu, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_dialog_lemak_susu, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(LemakSusuActivity.this);
        alertDialogBuilderUserInput.setView(view);

        

        final EditText input_persen_lemak = view.findViewById(R.id.persen_lemak_lemak_susu);
        final EditText input_tdn = view.findViewById(R.id.tdn_lemak_susu);
        final EditText input_pk = view.findViewById(R.id.pk_lemak_susu);
        final EditText input_ca = view.findViewById(R.id.ca_lemak_susu);
        final EditText input_p = view.findViewById(R.id.p_lemak_susu);

        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_lemak_susu_title) : getString(R.string.lbl_edit_lemak_susu_title));


        if (shouldUpdate && LemakSusu != null){
           
        input_persen_lemak.setText(nm.format(LemakSusu.getPersenLemak()).replace(",",""));
        input_tdn.setText(nm.format(LemakSusu.getTdn()).replace(",",""));
        input_pk.setText(nm.format(LemakSusu.getPk()).replace(",",""));
        input_ca.setText(nm.format(LemakSusu.getCa()).replace(",",""));
        input_p.setText(nm.format(LemakSusu.getP()).replace(",",""));
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
                if (TextUtils.isEmpty(input_persen_lemak.getText().toString())) {
                    Toast.makeText(LemakSusuActivity.this, "Masukkan Persen Lemak!", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(input_tdn.getText().toString())) {
                    Toast.makeText(LemakSusuActivity.this, "Masukkan tdn!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_pk.getText().toString())) {
                    Toast.makeText(LemakSusuActivity.this, "Masukkan pk!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_ca.getText().toString())) {
                    Toast.makeText(LemakSusuActivity.this, "Masukkan ca!", Toast.LENGTH_SHORT).show();
                    return;
                }if (TextUtils.isEmpty(input_p.getText().toString())) {
                    Toast.makeText(LemakSusuActivity.this, "Masukkan p!", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && LemakSusu != null) {
                    // update note by it's id
                    // updateLemakSusu(inputLemakSusu.getText().toString(), position);
                    updateLemakSusu(Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_persen_lemak.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()), position);

                } else {
                    // create new note
                    createLemakSusu(Double.parseDouble(input_tdn.getText().toString()), Double.parseDouble(input_persen_lemak.getText().toString()), Double.parseDouble(input_pk.getText().toString()), Double.parseDouble(input_ca.getText().toString()), Double.parseDouble(input_p.getText().toString()));
                }
            }
        });
    }

    /**
     * Toggling list and empty lemaksusu view
     */
    private void toggleEmptyLemakSusu() {
        // you can check lemaksusuList.size() > 0

        if (db.getLemakSusuCount() > 0) {
            noLemakSusuView.setVisibility(View.GONE);
        } else {
            noLemakSusuView.setVisibility(View.VISIBLE);
        }
    }
}