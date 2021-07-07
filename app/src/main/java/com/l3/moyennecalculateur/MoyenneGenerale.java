package com.l3.moyennecalculateur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MoyenneGenerale extends AppCompatActivity {
    ListView listView;
    ModuleDAO moduleDAO = new ModuleDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyenne_generale);

        listView = (ListView) findViewById(R.id.activity_moyenne_generale_listview);
        String[] list = moduleDAO.selectModule();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_listview, R.id.text_view, list);
        listView.setAdapter(arrayAdapter);
    }
}