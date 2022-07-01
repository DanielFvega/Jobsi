package com.example.jobsi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Cultivator extends AppCompatActivity {

    private ImageView ivExit, ivUser, ivFlower;
    private LinearLayout card1, card2, card3, card4;
    private LinearLayout btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivator);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Cultivator.this);
        builder.setView(R.layout.dialog_oferta)
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();

        ivExit = findViewById(R.id.ivExit);
        ivUser = findViewById(R.id.ivUser);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        btnChange = findViewById(R.id.btnChange);

        ivExit.setOnClickListener(view -> {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Cultivator.this);
            dialogo1.setTitle("Cerrar sesión");
            dialogo1.setMessage("¿Estas seguro que quieres salir de Jobsi?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Acepta", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    Intent intent = new Intent(getBaseContext(), HomeLogin.class);
                    startActivity(intent);
                    finish();
                }
            });
            dialogo1.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                }
            });
            dialogo1.show();
        });

        card1.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SeeProducts.class);
            startActivity(intent);
        });

        card2.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), SeeCrops.class);
            startActivity(intent);
        });

        card3.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Learn.class);
            startActivity(intent);
        });

        card4.setOnClickListener(view -> {

        });

        btnChange.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), comprador.class);
            startActivity(intent);
        });

        ivUser.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), Home.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Cultivator.this);
        dialogo1.setTitle("Cerrar sesión");
        dialogo1.setMessage("¿Estas seguro que quieres salir de Jobsi?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Acepta", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                Intent intent = new Intent(getBaseContext(), HomeLogin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });
        dialogo1.setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        dialogo1.show();
    }

    public void trastoristaa(View view) {
        Intent intent = new Intent(getBaseContext(), MapsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

    /*    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_comunas, (ConstraintLayout) findViewById(R.id.bottomSheetContainerLogin));

        bottomSheetView.findViewById(R.id.text_view_iniciar_seccion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bottomSheetView.findViewById(R.id.image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();*/
    }
}
