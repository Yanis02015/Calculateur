package com.l3.moyennecalculateur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ModuleDAO extends DAOBase{
    public static final String TABLE_NAME = "module";
    public static final String KEY = "id";
    public static final String INTITULE = "intitule";
    public static final String COEFFICIENT = "coefficient";
    public static final String EMD = "emd";
    public static final String TD = "td";
    public static final String TP = "tp";

    public static final String TABLE_CREATE =
            "CREATE TABLE" + TABLE_NAME + " (" +
                    KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    INTITULE + " TEXT, " +
                    COEFFICIENT + " INT, " +
                    EMD + " REAL, " +
                    TD + " REAL, " +
                    TP + " REAL);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

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
        this.open();
        mDb.insert(ModuleDAO.TABLE_NAME, null, contentValues);
        this.close();
    }

    public String selectModule() {
        this.open();
        Cursor cursor = mDb.rawQuery("SELECT " + INTITULE + " FROM " + TABLE_NAME, new String[]{});
        cursor.moveToFirst();
        String string = cursor.getString(0);
        cursor.close();
        return string;
    }
}
