package com.floydd.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private EditText edtemail,edtusernanme,edtpassword;
    private Button btnsignup,btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("SIGN UP!");

        edtemail=findViewById(R.id.edtemailsignup);
        edtusernanme=findViewById(R.id.edtusernamesignup);
        edtpassword=findViewById(R.id.edtpasswordsignup);
        edtpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    onClick(btnsignup);
                }
                return false;
            }
        });
        btnsignup=findViewById(R.id.btnsignup);
        btnlogin=findViewById(R.id.btnlogin);

        btnsignup.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.getCurrentUser().logOut();
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignup:
                if(edtemail.getText().toString().equals("") || edtusernanme.getText().toString().equals("") ||
                        edtpassword.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "email/username/password missing", Toast.LENGTH_SHORT).show();
                }
            else {
                    final ParseUser appeUser = new ParseUser();
                    appeUser.setEmail(edtemail.getText().toString());
                    appeUser.setUsername(edtusernanme.getText().toString());
                    appeUser.setPassword(edtpassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage(" Signing up" + edtusernanme.getText().toString());
                    progressDialog.show();
                    appeUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUp.this, appeUser.getUsername() + " signed up successfully", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }

               break;
            case R.id.btnlogin:
                Intent intent=new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;

        }


    }

    public void touchlayout(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

    }
}