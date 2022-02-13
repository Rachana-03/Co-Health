package com.rachana.co_health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //EditText username,password,c_password;
    Button btnSignUp,btnSignIn;
    DBHelper myDB;

    public EditText username,phone,email,password,c_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        c_password=(EditText)findViewById(R.id.c_password);

        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);

        myDB=new DBHelper(this);

        //check credential
        checkCredentials();





        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
//               String email=email.getText().toString();
//               String phone=phone.getText().toString();
                String c_pass = c_password.getText().toString();


                if(user.equals(" ") || pass.equals(" ") || c_pass.equals(" ")) {
                    Toast.makeText(MainActivity.this, "Fill all the fields ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(c_pass)){
                        boolean  userchckResult= myDB.checkusername(user);
                        if(userchckResult==false){
                            boolean regResult= myDB.insertData(user,pass);

                            if(regResult==true){
                                Toast.makeText(MainActivity.this,"Registration Successfull ",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Registration fail ",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            Toast.makeText(MainActivity.this,"User already exists.\n Please Login ",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(MainActivity.this,"Password not match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);

            }
        });

    }

    public void checkCredentials() {

    }


}