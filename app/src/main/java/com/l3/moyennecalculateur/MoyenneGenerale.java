package com.l3.moyennecalculateur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MoyenneGenerale extends AppCompatActivity {
    ListView listView;
    TextView moyenneView, remarqueView;
    ModuleDAO moduleDAO = new ModuleDAO(this);
    public static final String CHALLANGER = "Bravo! Chuck Norris.";
    public static final String MASTER = "Bravo! Tu es comme Jhon dans game of thrones, même George R. R. Martin n'a pas pu le tuer.";
    public static final String PLATINIUM = "Tu es meilleur que 96.4% des joueur. C'est ce que j'aurait dit si j'était un métho.";
    public static final String GOLD = "C'est pas mal mais tu peux faire mieux, essais de spam Master YI pour monter plat.";
    public static final String SILVER = "Tu devrai commencer à paniquer être dans le juste milieu est stressant.";
    public static final String BRONZE = "Amaken c'est le moment akekssegh Netflix. Concetres toi chwiya gh leqrayaaa aww!";
    public static final String IRON = "aken idenan imenzoya, rattrapage yergazen !";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyenne_generale);

        listView = findViewById(R.id.activity_moyenne_generale_listview);
        remarqueView = findViewById(R.id.activity_moyenne_generale_remarque_textview);
        moyenneView = findViewById(R.id.activity_moyenne_generale_00_00);
        MarksViewAdaptater marksViewAdaptater = new MarksViewAdaptater(this, moduleDAO.getAllModule());
        listView.setAdapter(marksViewAdaptater);
        double moyenneGeneraleDouble = calculerMoyenne(moduleDAO.getAllModule());
        String moyenneGeneraleString = formatDouble(moyenneGeneraleDouble);
        setRemarque(moyenneGeneraleDouble);
        moyenneView.setText(moyenneGeneraleString);
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
}