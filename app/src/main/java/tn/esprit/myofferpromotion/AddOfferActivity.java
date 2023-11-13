package tn.esprit.myofferpromotion;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.myofferpromotion.R;
import tn.esprit.myofferpromotion.dao.DatabaseHelper;
import tn.esprit.myofferpromotion.entity.Offer;

public class AddOfferActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private Button buttonAddOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        Intent intent = getIntent();

        if (intent != null) {
            String mode = intent.getStringExtra("mode");
            Offer offre = intent.getParcelableExtra("offre");

            if (mode != null && mode.equals("modify") && offre != null) {
                 updateUiWithOfferData(offre);
            }
        }

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonAddOffer = findViewById(R.id.buttonAddOffer);

        buttonAddOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les données saisies
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                double price = Double.parseDouble(editTextPrice.getText().toString());

                // Ajouter l'offre (vous devrez implémenter cette logique)
                addOffer(title, description, price);

                // Vous pouvez également revenir à l'activité précédente ou faire d'autres actions nécessaires
                finish();
            }
        });
    }

    private void updateUiWithOfferData(Offer offre) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, offre.getTitle());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, offre.getDescription());
        values.put(DatabaseHelper.COLUMN_PRICE, offre.getPrice());
        // Specify the whereClause to update the specific row
        String whereClause = DatabaseHelper.COLUMN_ID + " = ?";
        String[] whereArgs = { String.valueOf(offre.getId()) };
        long rowsUpdated = db.update(DatabaseHelper.TABLE_OFFERS, values,whereClause,whereArgs);

        if (rowsUpdated > 0) {
            // Successful update
            Toast.makeText(this, "Offre mise à jour avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            // Update failed
            Toast.makeText(this, "Erreur lors de la mise à jour de l'offre.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addOffer(String title, String description, double price) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_PRICE, price);

        long newRowId = db.insert(DatabaseHelper.TABLE_OFFERS, null, values);

        if (newRowId != -1) {
            // Succès de l'ajout
            Toast.makeText(this, "Offre ajoutée avec succès!", Toast.LENGTH_SHORT).show();
        } else {
            // Échec de l'ajout
            Toast.makeText(this, "Erreur lors de l'ajout de l'offre.", Toast.LENGTH_SHORT).show();
        }
    }




}
