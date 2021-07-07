package com.l3.moyennecalculateur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ModuleDAO moduleDAO = new ModuleDAO(this);
    private EditText txtModuleName, txtModuleCoeff, txtModuleEmd, txtModuleTd, txtModuleTp;
    private Button btnMoyenne, btnMoyenneGenerale;
    private TextView txtMoyenne;
    private double mMoyenne;
    public static final String MODULE_DB_NAME = "DB_Module";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtModuleName = findViewById(R.id.activity_main_module_name_txt);
        txtModuleCoeff = findViewById(R.id.activity_main_module_coeff_txt);
        txtModuleEmd = findViewById(R.id.activity_main_module_emd_txt);
        txtModuleTd = findViewById(R.id.activity_main_module_td_txt);
        txtModuleTp = findViewById(R.id.activity_main_module_tp_txt);
        txtMoyenne = findViewById(R.id.activity_main_moyenne_txt);
        btnMoyenne = findViewById(R.id.activity_main_moyenne_btn);
        btnMoyenneGenerale = findViewById(R.id.activity_main_moyenne_generale_btn);

        btnMoyenneGeneraleEnable();

        btnMoyenne.setOnClickListener(v -> {
            if (inputIsOk()){
                Module module = new Module(txtModuleName.getText().toString(),
                        toInteger(txtModuleCoeff),
                        toDouble(txtModuleEmd),
                        toDouble(txtModuleTd),
                        toDouble(txtModuleTp));
                txtMoyenne.setText(String.valueOf(module.getMoyenne()));
                moduleDAO.addModule(module);
                btnMoyenneGeneraleEnable();
                showToast(String.valueOf(moduleDAO.getLength()));
            }
        });

        btnMoyenneGenerale.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoyenneGenerale.class);
            startActivity(intent);
        });
    }

    private void btnMoyenneGeneraleEnable() {
        btnMoyenneGenerale.setEnabled(moduleDAO.getLength() > 0);
    }

    private boolean inputIsOk() {
        if(!txtModuleName.getText().toString().isEmpty() &&
                !txtModuleCoeff.getText().toString().isEmpty() &&
                !txtModuleEmd.getText().toString().isEmpty() &&
                !txtModuleTd.getText().toString().isEmpty() &&
                !txtModuleTp.getText().toString().isEmpty()){
            if(toDouble(txtModuleCoeff)>0){
                if(toDouble(txtModuleEmd)>=0 && toDouble(txtModuleEmd)<=20){
                    if(toDouble(txtModuleTd)>=0 && toDouble(txtModuleTd)<=20){
                        if(toDouble(txtModuleTp)>=0 && toDouble(txtModuleTp)<=20){
                            return true;
                        } else
                            showToast("Le TP ne peut pas être négatif ou supperieur à 20");
                    } else
                        showToast("Le TD ne peut pas être négatif ou supperieur à 20");
                } else
                    showToast("L'EMD ne peut pas être négatif ou supperieur à 20");
            } else
                showToast("Le coefficient ne peut pas être négatif");
        } else
            showToast("Tous les champs doivent être saisis");

        return false;
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private double toDouble(EditText txtEditText) {
        if(canBeDouble(txtEditText)) {
            return Double.parseDouble(txtEditText.getText().toString());
        }
        return -1;
    }

    private int toInteger(EditText txtEditText) {
        if(canBeInteger(txtEditText)) {
            return Integer.parseInt(txtEditText.getText().toString());
        }
        return -1;
    }

    private boolean canBeDouble(EditText txtEditText) {
        try {
            double d = Double.parseDouble(txtEditText.getText().toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean canBeInteger(EditText txtEditText) {
        try {
            int i = Integer.parseInt(txtEditText.getText().toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getMoyenne(double emd, double td, double tp){
        return 00.00;
    }
}