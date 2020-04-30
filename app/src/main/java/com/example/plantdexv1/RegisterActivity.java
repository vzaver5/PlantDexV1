package com.example.plantdexv1;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    String password;
    String password2;
    AutoCompleteTextView nameTextView;
    AutoCompleteTextView usernameTextView;
    EditText passwordTextView;
    EditText passwordTextView2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //this to set delegate/listener back to this class
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        System.out.println("In register onCreate");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        nameTextView = findViewById(R.id.nameRegisterFragment);
        usernameTextView = findViewById(R.id.usernameRegisterFragment);
        passwordTextView = findViewById(R.id.passwordRegisterFragment);
        passwordTextView2 = findViewById(R.id.password2RegisterFragment2);

        findViewById(R.id.register_button_registerFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Register button selected");
                //Determine if they gave valid credentials to make an acct
                //Get name
                String name = nameTextView.getText().toString();
                //Get username
                String username = usernameTextView.getText().toString();
                System.out.println(username);

                //Check passwords
                password = passwordTextView.getText().toString();
                password2 = passwordTextView2.getText().toString();

                System.out.println(password);
                System.out.println(password2);

                //Check if all requirement met
                if(!TextUtils.isEmpty(username) && checkEmailFormat(username)){
                    //Username has been filled
                    if(!TextUtils.isEmpty(password)  && checkPasswordFormat(password)){
                        //Password has been filled
                        if(password.equals(password2)){
                            //Passwords match each other
                            //We can now create the account
                            createAccount(name, username, password);
                        }else{//Pass don't match
                            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    }else if (TextUtils.isEmpty(password)){//Pass1 not filled
                        Toast.makeText(RegisterActivity.this, "Password cannot be left empty", Toast.LENGTH_LONG).show();
                    }else if(!checkPasswordFormat(password)){
                        Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
                    }
                }else if (TextUtils.isEmpty(username)){//Username not filled
                    Toast.makeText(RegisterActivity.this, "Username cannot be left empty", Toast.LENGTH_LONG).show();
                }else if(!checkEmailFormat(username)){       //Username not correct format
                    Toast.makeText(RegisterActivity.this, "Username must contain an '@' and a '.'", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    public void createAccount(final String name, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "Account has been registered", Toast.LENGTH_LONG).show();

                            //Add the user to the database
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            User registeredUser = new User(name,userId); //Create user object
                            DatabaseReference mDB =  FirebaseDatabase.getInstance().getReference();
                            mDB.child("user:" + userId).child("name").setValue(registeredUser.getName());   //Add userobject to user:userid path
                            mDB.child("user:" + userId).child("virtualGarden").child("testPlant").setValue(true);   //Add userobject to user:userid path

                            finish();   //End register activity return back to login
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
    }

    public boolean checkEmailFormat(String username){
        if(username.contains("@") && username.contains(".")){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPasswordFormat(String password){
        if(password.length() >= 6){
            return true;
        }else {
            return false;
        }
    }

}
