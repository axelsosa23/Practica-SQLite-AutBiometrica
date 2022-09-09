package com.mulapp.passwordapp.Fragmentos;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mulapp.passwordapp.R;
import com.mulapp.passwordapp.databinding.FragmentFAjustesBinding;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class F_Ajustes extends Fragment {

    private FragmentFAjustesBinding binding;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    private BiometricManager manager;
    private Cipher cipher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFAjustesBinding.inflate(inflater, container, false);

        manager = BiometricManager.from(getActivity());

        biometricPrompt = new BiometricPrompt(getActivity(), new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                //SE EJECUTA CUANDO EL DISPOSITIVO NO CUENTE CON HUELLAS DACTILARES REGISTRADAS
                Toast.makeText(getActivity(), "No existen huellas registradas", Toast.LENGTH_SHORT).show();
                Log.i("ESTADO", errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                //AUT EXITOSA
                Toast.makeText(getActivity(), "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                Log.d("result", ": "+(result.getCryptoObject()));

                BiometricPrompt.CryptoObject authenticatedCryptoObject = result.getCryptoObject();

                Log.d("onAuthentionSucceeded", ": "+(authenticatedCryptoObject==null));

                if (authenticatedCryptoObject != null) {
                    cipher = authenticatedCryptoObject.getCipher();
                    Log.d("onAuthentionSucceeded", ": " + cipher.toString());
                }else {
                    Log.d("cipher", "onAuthenticationSucceeded: null");
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                //FALLA DE AUTENTICACION
                Toast.makeText(getActivity(), "Fallo la aut", Toast.LENGTH_SHORT).show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Aut biometrica")
                .setNegativeButtonText("Cancelar")
                .build();

        binding.btnAut.setOnClickListener(view -> {
//            biometricPrompt.authenticate(promptInfo);

            BiometricManager biometricManager = BiometricManager.from(getActivity());

            if (biometricManager.canAuthenticate() != BiometricManager.BIOMETRIC_SUCCESS){
                Toast.makeText(getActivity(), "La lectura biométrica no es soportado", Toast.LENGTH_SHORT).show();
                return;
            }

            BiometricPrompt.CryptoObject cryptoObject = null;
            try {
                cryptoObject = new BiometricPrompt.CryptoObject(getEncryptCipher(createKey()));
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }

            biometricPrompt.authenticate(promptInfo, cryptoObject);

//            biometricPrompt.authenticate(promptInfo, new BiometricPrompt.CryptoObject(cipher));
        });

        return binding.getRoot();
    }

    private SecretKey createKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        String algorithm = KeyProperties.KEY_ALGORITHM_AES;
        String provider = "AndroidKeyStore";
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, provider);
        KeyGenParameterSpec keyGenParameterSpec = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyGenParameterSpec = new KeyGenParameterSpec.Builder("MY_KEY", KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .setUserAuthenticationRequired(true)
                    .build();

            keyGenerator.init(keyGenParameterSpec);
        }
        return keyGenerator.generateKey();
    }

    private Cipher getEncryptCipher(Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String algorithm = KeyProperties.KEY_ALGORITHM_AES;
        String blockMode = KeyProperties.BLOCK_MODE_CBC;
        String padding = KeyProperties.ENCRYPTION_PADDING_PKCS7;
        Cipher cipher = Cipher.getInstance(algorithm+"/"+blockMode+"/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher;
    }
}