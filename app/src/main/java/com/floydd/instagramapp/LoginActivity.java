package com.floydd.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
       private EditText edtemaillogin,edtpasswordlogin;
       private Button btnloginm,btnsignupm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemaillogin=findViewById(R.id.edtemaillogin);
        edtpasswordlogin=findViewById(R.id.edtpasswordlogin);
        btnloginm=findViewById(R.id.btnloginm);
        btnsignupm=findViewById(R.id.btnsignupm);
        btnloginm.setOnClickListener(this);
        btnsignupm.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null){
           // ParseUser.getCurrentUser().logOut();
            transitiontosocialmedia();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnloginm:
                final ProgressDialog progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("Login"+edtemaillogin.getText().toString());
                progressDialog.show();
                ParseUser.logInInBackground(edtemaillogin.getText().toString(), edtpasswordlogin.getText().toString(), new LogInCallback() {


                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null){
                            Toast.makeText(LoginActivity.this,user.getUsername()+" is successfull login ",Toast.LENGTH_LONG).show();
                            transitiontosocialmedia();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.btnsignupm:
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;
        }

    }
    public void transitiontosocialmedia() {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}