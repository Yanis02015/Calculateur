package com.l3.moyennecalculateur.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.l3.moyennecalculateur.control.MarksView;
import com.l3.moyennecalculateur.control.Module;

import java.util.ArrayList;

public class ModuleDAO extends DAOBase{
    public static final String TABLE_NAME = "module";
    public static final String KEY = "id";
    public static final String INTITULE = "intitule";
    public static final String COEFFICIENT = "coefficient";
    public static final String EMD = "emd";
    public static final String TD = "td";
    public static final String TP = "tp";
    public static final String MOYENNE = "moyenne";

    public ModuleDAO(Context pContext) {
        super(pContext);
    }

    public void addModule(Module module) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(INTITULE, module.getIntitule());
        contentValues.put(COEFFICIENT, module.getCoefficient());
        contentValues.put(EMD, module.getEmd());
        contentValues.put(TD, module.getTd());
        contentValues.put(TP, module.getTp());
        contentValues.put(MOYENNE, module.getMoyenne());
        this.open();
        mDb.insert(ModuleDAO.TABLE_NAME, null, contentValues);
        this.close();
        System.out.println("Donnée ajouté à la database");
    }

    public ArrayList<MarksView> getAllModule() {
        this.open();
        Cursor cursor = mDb.rawQuery("SELECT " + INTITULE + ", " + COEFFICIENT + ", " + MOYENNE + " FROM " + TABLE_NAME, new String[]{});
        ArrayList<MarksView> output = new ArrayList<>();
        while (cursor.moveToNext()) {
            String intitule = cursor.getString(0);
            String coefficient = cursor.getString(1);
            String moyenne = cursor.getString(2);
            output.add(new MarksView(intitule, Integer.parseInt(coefficient), Double.parseDouble(moyenne)));
        }
        cursor.close();
        this.close();
        return output;
    }

    public int getLength() {
        this.open();
        Cursor cursor = mDb.rawQuery("SELECT " + INTITULE + " FROM " + TABLE_NAME, new String[]{});
        int length = cursor.getCount();
        cursor.close();
        this.close();
        return length;
    }

    public void clearDb() {
        this.open();
        mDb.execSQL(DatabaseHandler.MODULE_TABLE_DROP);
        mDb.execSQL(DatabaseHandler.MODULE_TABLE_CREATE);
        this.close();
    }
}
