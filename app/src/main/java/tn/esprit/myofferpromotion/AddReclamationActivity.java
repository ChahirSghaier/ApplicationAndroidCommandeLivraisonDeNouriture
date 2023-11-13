package tn.esprit.myofferpromotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.myofferpromotion.dao.DataBaseReclamation;
import tn.esprit.myofferpromotion.dao.DatabaseHelper;
import tn.esprit.myofferpromotion.entity.Reclamation;


public class AddReclamationActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;

    private Button buttonAddReclamation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reclamation);
        Intent intent = getIntent();

        if (intent != null) {
            String mode = intent.getStringExtra("mode");
            Reclamation reclamation = intent.getParcelableExtra("reclamation");

            if (mode != null && mode.equals("modify") && reclamation != null) {
                updateUiWithReclamationData(reclamation);
            }
        }

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAddReclamation = findViewById(R.id.buttonAddReclamation);

        buttonAddReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les données saisies
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();


                // Ajouter l'reclamation (vous devrez implémenter cette logique)
                addReclamation(title, description);

                // Vous pouvez également revenir à l'activité précédente ou faire d'autres actions nécessaires
                finish();
            }
        });
    }

    private void updateUiWithReclamationData(Reclamation reclamation) {
        DataBaseReclamation dbHelper = new DataBaseReclamation(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseReclamation.COLUMN_TITLE, reclamation.gettitle());
        values.put(DataBaseReclamation.COLUMN_DESCRIPTION, reclamation.getDescription());

        // Specify the whereClause to update the specific row
        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(reclamation.getId()) };
        long rowsUpdated = db.update(DataBaseReclamation.TABLE_RECLAMATION, values,whereClause,whereArgs);

        if (rowsUpdated > 0) {
            // Successful update
            Toast.makeText(this, "reclamation mise à jour avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            // Update failed
            Toast.makeText(this, "Erreur lors de la mise à jour de l'reclamation.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addReclamation(String title, String description) {
        DataBaseReclamation dbHelper = new DataBaseReclamation(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseReclamation.COLUMN_TITLE, title);
        values.put(DataBaseReclamation.COLUMN_DESCRIPTION, description);


        long newRowId = db.insert(DataBaseReclamation.TABLE_RECLAMATION, null, values);

        if (newRowId != -1) {
            // Succès de l'ajout
            Toast.makeText(this, "reclamation ajoutée avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            // Échec de l'ajout
            Toast.makeText(this, "Erreur lors de l'ajout de l'reclamation.", Toast.LENGTH_SHORT).show();
        }
    }




}