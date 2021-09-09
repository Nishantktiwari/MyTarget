package com.example.assessment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.assessment.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getSupportActionBar().hide();
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding =ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){ //Redirecting users if regisgtered befored
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailR.getText().toString().trim(); //taking input and storing in string form
                String password= binding.passwordR.getText().toString().trim();
                //Validation
                if (TextUtils.isEmpty(email)) {
                    binding.emailR.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    binding.emailR.setError("Email is Required");
                    return;
                }
//                if (TextUtils.isEmpty((CharSequence) binding.Name)){
//                    binding.Name.setError("Name should not be Empty");
//                    return;
//                }
                if(password.length()<6){
                    binding.passwordR.setError("Password must be grater than 6 characters");
                    return;
                }
                binding.pro1.setVisibility(View.VISIBLE);
                //Register User to the Firebase
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"Congrulation Registeration is successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(Register.this,"X OOPS error occur",Toast.LENGTH_SHORT).show();
                            binding.pro1.setVisibility(View.INVISIBLE);
                            return;
                        }
                    }
                });
            }
        });
        binding.RegisteredACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }

}