package com.example.jobsi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;
import com.royrodriguez.transitionbutton.TransitionButton;

public class Login extends AppCompatActivity implements BiometricCallback {

    private EditText etEmail, etPassword;
    private TransitionButton transitionButton;
    private TextView Jobsi, mensaje;
    private ImageButton ivFingerprint;
    BiometricManager mBiometricManager;
    Animation animacion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Jobsi = findViewById(R.id.Jobsi);
        mensaje = findViewById(R.id.Mensaje);
        ivFingerprint = findViewById(R.id.ivFingerprint);


        animacion2 = AnimationUtils.loadAnimation(Login.this, R.anim.top_to_up);
        Jobsi.setAnimation(animacion2);
        mensaje.setAnimation(animacion2);


        transitionButton = findViewById(R.id.transition_button);

        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionButton.startAnimation();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String et1 = etEmail.getText().toString();
                        String et2 = etPassword.getText().toString();

                        if (et1.equals("test@mail.com") || et2.equals("12345")) {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                                @Override
                                public void onAnimationStopEnd() {
                                    Intent intent = new Intent(getBaseContext(), Home.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        } else {
                            transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                        }
                    }
                }, 2000);
            }
        });

        ivFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBiometricManager = new BiometricManager.BiometricBuilder(Login.this)
                        .setTitle("Ingresar")
                        .setSubtitle("Inicia sesión en tu cuenta")
                        .setDescription("Coloque su dedo en el botón de inicio del dispositivo para verificar su identidad")
                        .setNegativeButtonText("CANCELAR")
                        .build();

                mBiometricManager.authenticate(Login.this);
            }
        });
    }

    @Override
    public void onSdkVersionNotSupported() {
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {
    }

    @Override
    public void onAuthenticationFailed() {
    }

    @Override
    public void onAuthenticationCancelled() {
    }

    @Override
    public void onAuthenticationSuccessful() {
        Intent intent = new Intent(getBaseContext(), Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
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

    public void atrasLogin(View view) {
        onBackPressed();
        finish();
    }
}