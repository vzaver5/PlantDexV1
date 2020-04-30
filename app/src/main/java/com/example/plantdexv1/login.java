package com.example.plantdexv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.concurrent.Executor;

public class login extends Fragment {
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    String passwordString;
    String emailString;
    EditText email;
    EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //this to set delegate/listener back to this class
        super.onCreate(savedInstanceState);
        System.out.println("In login onCreate");
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        System.out.println("In login onCreateView");
        return inflater.inflate(R.layout.activity_login, container, false);
    }


    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        view.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Sign in button selected");
                //Get email
                emailString = email.getText().toString();
                //Get password
                passwordString = password.getText().toString();
                System.out.println(emailString);
                System.out.println(passwordString);

                if(!TextUtils.isEmpty(emailString)){
                    if(!TextUtils.isEmpty(passwordString)){
                        //Use firebase authentication
                        signIn(emailString, passwordString);
                    }else{
                        Toast.makeText(getContext(), "Sign in fail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Sign in fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Register button selected");
                //Go to register account
                Intent moveToRegister = new Intent(getContext(), RegisterActivity.class);
                startActivity(moveToRegister);
            }
        });
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println("SIGN IN SUCCESS");
                            //Go to first fragment
                            NavHostFragment.findNavController(login.this)
                                    .navigate(R.id.loginFragment_to_FirstFragment);
                            //Hide the keyboard
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Sign in failed",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);

                        }
                    }
                });
    }
}
