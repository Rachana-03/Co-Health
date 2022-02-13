package com.rachana.co_health;

import static com.rachana.co_health.R.id.btnLogin;
import static com.rachana.co_health.R.id.username;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText usernameLogin,passwordLogin;
    Button btnLogin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameLogin=(EditText)findViewById(R.id.usernameLogin);
        passwordLogin=(EditText)findViewById(R.id.passwordLogin);
        btnLogin=(Button) findViewById(R.id.btnLogin);

        myDB=new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=usernameLogin.getText().toString();
                String pass=passwordLogin.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginPage.this,"Please entere the Credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean result=myDB.checkusernamePassword(user,pass);
                    if(result==true){
                        Intent intent=new Intent(getApplicationContext(),HomePage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this,"Please entere the Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}