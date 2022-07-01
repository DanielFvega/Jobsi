package com.example.jobsi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Home extends AppCompatActivity {

    ConstraintLayout loginlinear;
    Animation animacion1;
    Dialog dialog;
    ImageView imageView, ivEdit2, ivUser, ivExit;
    private Bitmap imagenprint;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int hasWriteContactsPermission = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
        }
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
        } else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
            onBackPressed();
//            Toast.makeText(Home.this, "Permissions rechazados", Toast.LENGTH_SHORT).show();
        }

        loginlinear = findViewById(R.id.loginlinear);
        animacion1 = AnimationUtils.loadAnimation(Home.this, R.anim.bottom_to_up);
        loginlinear.setAnimation(animacion1);
        imageView = findViewById(R.id.ivEdit);
        ivEdit2 = findViewById(R.id.ivEdit2);
        ivUser = findViewById(R.id.ivUser);
        ivExit = findViewById(R.id.ivExit);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/gallery/*");
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), 1);
            }
        });

        ivEdit2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });

        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Home.this);
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            ImageView imageView = findViewById(R.id.ivUser);
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                imagenprint = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(imagenprint);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            if (imagenprint == null || data == null) {
                onBackPressed();
            } else {
                ImageView imageView = findViewById(R.id.ivUser);
                imagenprint = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(imagenprint);
            }
        }
        if (resultCode == RESULT_OK && requestCode == 2) {
            ImageView imageView = findViewById(R.id.ivUser);
            imagenprint = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(imagenprint);
        }
    }

    public void btnStart(View v) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_home);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationReport;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnSell = dialog.findViewById(R.id.btnSell);
        Button btnBuy = dialog.findViewById(R.id.btnBuy);
        ImageButton ibClose = dialog.findViewById(R.id.ibClose);

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Cultivator.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), comprador.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
    }
}