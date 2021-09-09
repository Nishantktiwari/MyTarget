package com.example.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.assessment.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fAuth= FirebaseAuth.getInstance();
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailcfn.getText().toString().trim(); //taking input and storing in string form
                String password= binding.pwd2.getText().toString().trim();
                //Validation
                if (TextUtils.isEmpty(email)) {
                    binding.emailcfn.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    binding.emailcfn.setError("Email is Required");
                    return;
                }
                if(password.length()<6){
                    binding.pwd2.setError("Password must be grater than 6 characters");
                    return;
                }
                binding.pro3.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Login successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Daskboard.class));
                        }
                        else{
                            Toast.makeText(Login.this,"OOPs Email or Password is incorrect",Toast.LENGTH_SHORT).show();
                            binding.pro3.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
        binding.createACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }
}