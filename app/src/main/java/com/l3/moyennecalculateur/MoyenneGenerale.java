package com.l3.moyennecalculateur;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MoyenneGenerale extends AppCompatActivity {
    ListView listView;
    TextView moyenneView, remarqueView;
    ModuleDAO moduleDAO = new ModuleDAO(this);
    public static final String CHALLANGER = "Bravo! Chuck Norris.";
    public static final String MASTER = "Bravo! Tu es comme Jhon dans game of thrones, même George R. R. Martin n'a pas pu le tuer.";
    public static final String PLATINIUM = "Tu es meilleur que 96.4% des joueur. C'est ce que j'aurait dit si j'était un métho.";
    public static final String GOLD = "C'est pas mal mais tu peux faire mieux, essais de spam Master YI pour monter plat.";
    public static final String SILVER = "Tu devrai commencer à paniquer être dans le juste milieu est stressant.";
    public static final String BRONZE = "Amaken c'est le moment akekssegh Netflix. Concentres toi chwiya gh leqrayaaa aww!";
    public static final String IRON = "aken idenan imenzoya, rattrapage yergazen !";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyenne_generale);

        listView = findViewById(R.id.activity_moyenne_generale_listview);
        remarqueView = findViewById(R.id.activity_moyenne_generale_remarque_textview);
        moyenneView = findViewById(R.id.activity_moyenne_generale_00_00);

        this.configureToolbar();
        this.configureOutput();
    }

    private void configureOutput() {
        MarksViewAdaptater marksViewAdaptater = new MarksViewAdaptater(this, moduleDAO.getAllModule());
        listView.setAdapter(marksViewAdaptater);
        double moyenneGeneraleDouble = calculerMoyenne(moduleDAO.getAllModule());
        String moyenneGeneraleString = formatDouble(moyenneGeneraleDouble);
        setRemarque(moyenneGeneraleDouble);
        moyenneView.setText(moyenneGeneraleString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_moyenne_generale, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_refresh:
                configureOutput();
                Toast.makeText(this, "Table actualisé avec succée.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_activity_main_delete:
                showAlertDialogForClearDb(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlertDialogForClearDb(Context context) {
        int nbModule = moduleDAO.getLength();
        if(nbModule != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle("Message de confirmation");
            builder.setMessage("Êtes vous sûr de vouloir supprimer les " +
                    nbModule +
                    " modules déjà enrgistrés ?");

            builder.setPositiveButton("Oui", (dialog, which) -> {
                moduleDAO.clearDb();
                Toast.makeText(context, nbModule + " module supprimé avec succée", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton(android.R.string.cancel, (dialog, which) ->
                    Toast.makeText(context, "Opération annulé", Toast.LENGTH_SHORT).show());
            AlertDialog dialog = builder.create();
            dialog.show();
        } else
            Toast.makeText(context, "Aucun module à supprimer, la DB est vide.", Toast.LENGTH_SHORT).show();
    }

    private void setRemarque(double moyenneGeneraleDouble) {
        if(moyenneGeneraleDouble > 19.5)
            remarqueView.setText(CHALLANGER);
        else if (moyenneGeneraleDouble > 17)
            remarqueView.setText(MASTER);
        else if (moyenneGeneraleDouble > 15)
            remarqueView.setText(PLATINIUM);
        else if (moyenneGeneraleDouble > 11)
            remarqueView.setText(GOLD);
        else if (moyenneGeneraleDouble > 9.9)
            remarqueView.setText(SILVER);
        else if (moyenneGeneraleDouble > 8)
            remarqueView.setText(BRONZE);
        else
            remarqueView.setText(IRON);
    }

    private double calculerMoyenne(ArrayList<MarksView> moduleList) {
        double moyenneGenerale = 0;
        int coefficient = 0;
        for(MarksView note : moduleList) {
            coefficient += note.getCoefficient();
            moyenneGenerale += note.getMoyenne() * note.getCoefficient();
        }
        return moyenneGenerale / coefficient;
    }

    private String formatDouble(double number) {
        DecimalFormat df = new DecimalFormat("00.00");
        return df.format(number);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void configureToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}