package com.example.biometrics;

import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class BiometricAct {
    private BiometricManager biometricManager;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;

    public boolean checkCompatibility(Context context){
        biometricManager = androidx.biometric.BiometricManager.from(context);

        if(biometricManager.canAuthenticate() == biometricManager.BIOMETRIC_SUCCESS){
            return true;
        }else{
            return false;
        }
    }

    public void biometricPrompt(Context context){
        executor = ContextCompat.getMainExecutor(context);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            biometricPrompt = new BiometricPrompt((FragmentActivity)context, executor, new BiometricPrompt.AuthenticationCallback(){
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(context, "Authentication Error", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(context, "Authentication Succeeded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DiaryActivity.class);
                    context.startActivity(intent);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
        promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Login")
                .setSubtitle("Login using your fingerprint")
                .setNegativeButtonText("Cancel")
                .build();
        biometricPrompt.authenticate(promptInfo);
    }
}
