package com.example.jobsi;

import static android.view.View.GONE;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Registrar extends AppCompatActivity {
    private TextView Hola;
    private ImageView check, check2, atrasRegistro, imageView2;
    private Animation animacion2;
    Dialog dialog;
    private ConstraintLayout Registerlinear, Comunalistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        animacion2 = AnimationUtils.loadAnimation(this, R.anim.top_to_up);
        Hola = findViewById(R.id.Hola);
        Hola.setAnimation(animacion2);
        imageView2 = findViewById(R.id.imageView2);
        atrasRegistro = findViewById(R.id.atrasRegistro);
        Registerlinear = findViewById(R.id.Registerlinear);
        Comunalistener = findViewById(R.id.Comunalistener);
        check = findViewById(R.id.check);
        check2 = findViewById(R.id.check2);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunalistener.setAnimation(animacion2);
                check.setVisibility(GONE);
                Registerlinear.setVisibility(GONE);
                atrasRegistro.setVisibility(GONE);
                imageView2.setVisibility(GONE);
                showDialog();
            }
        });
    }

    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_map);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationReport;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView regresar = dialog.findViewById(R.id.regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                check.setVisibility(View.VISIBLE);
                check2.setVisibility(GONE);
                Registerlinear.setVisibility(View.VISIBLE);
                Comunalistener.setVisibility(View.GONE);
                atrasRegistro.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
            }
        });

        dialog.setOnDismissListener(dialogInterface -> {
            check.setVisibility(View.VISIBLE);
            check2.setVisibility(GONE);
            Registerlinear.setVisibility(View.VISIBLE);
            Comunalistener.setVisibility(View.GONE);
            atrasRegistro.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        });

        TextView aceptar = dialog.findViewById(R.id.aceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIncorrecto("Los campos no pueden estar vacíos");
            }
        });

        dialog.show();
    }

    public void atrasRegistro(View view) {
        onBackPressed();
        finish();
    }

    public void DiaologoComunas(View view) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_comunas, (ConstraintLayout) findViewById(R.id.bottomSheetContainerLogin));

    /*    bottomSheetView.findViewById(R.id.text_view_iniciar_seccion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bottomSheetView.findViewById(R.id.image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.cancel();
            }
        });*/
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    public void toastCorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_correcto, (ViewGroup) findViewById(R.id.ll_custom_toast_ok));
        TextView txtMensaje = view.findViewById(R.id.mensajetoast_1);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.TOP, 0, 10);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();

    }

    public void toastIncorrecto(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_error, (ViewGroup) findViewById(R.id.ll_custom_toast_error));
        TextView txtMensaje = view.findViewById(R.id.mensajetoast_2);
        txtMensaje.setText(msg);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.TOP, 0, 10);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}