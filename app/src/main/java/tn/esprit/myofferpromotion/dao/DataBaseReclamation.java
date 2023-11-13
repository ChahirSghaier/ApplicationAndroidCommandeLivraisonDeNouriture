package tn.esprit.myofferpromotion.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseReclamation extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pimobile";
    private static final int DATABASE_VERSION = 1;
    // Table et colonnes
    public static final String TABLE_RECLAMATION = "reclamation";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    // Requête de création de la table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RECLAMATION + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT)";

    public DataBaseReclamation(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ici, vous pouvez mettre à jour la base de données si nécessaire.
        // Pour l'instant, nous laissons cela vide.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECLAMATION);
        onCreate(db);
    }
}
