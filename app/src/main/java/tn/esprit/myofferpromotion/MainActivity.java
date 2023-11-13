package tn.esprit.myofferpromotion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnViewOffers = findViewById(R.id.btnViewOffers);

        // Ajouter un écouteur de clic sur le bouton
        btnViewOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Passer à l'activité OffersActivity
                Intent intent = new Intent(MainActivity.this, OffersActivity.class);
                startActivity(intent);
            }
        });
    }
}