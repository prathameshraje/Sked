package com.androidfp.prath.sked;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SqlHelper sh = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
/*
    public void onButtonClick(View view){
        if(view.getId() == R.id.buttonLogin){
            EditText userName = (EditText)findViewById(R.id.editTextUserName);
            String name = userName.getText().toString();
            Intent i= new Intent(LoginActivity.this, HomeActivity.class);
            i.putExtra("UserName",name);
            startActivity(i);
        }

        if(view.getId() == R.id.buttonSignUp){
            Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(i);
        }
    }
*/
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                EditText userName = (EditText)findViewById(R.id.editTextUserName);
                String loginID = userName.getText().toString();
                EditText userPassword = (EditText)findViewById(R.id.editTextPassword);
                String password = userPassword.getText().toString();

                String storedPassword = sh.searchUser(loginID);

                if(password.equals(storedPassword)) {

                    String uName = sh.getUserName(loginID);
                    //startActivity(new Intent(LoginActivity.this, EventHomeActivity.class));
                    Intent i = new Intent(LoginActivity.this, EventHomeActivity.class);
                    i.putExtra("UserName", uName);
                    startActivity(i);
                }
                else {
                    Toast incorrectPasswordError = Toast.makeText(LoginActivity.this, "Incorrect Login Credentials!", Toast.LENGTH_LONG);
                    incorrectPasswordError.show();
                }
                break;
            case R.id.buttonSignUp:
                Intent i2 = new Intent(this, SignUpActivity.class);
                startActivity(i2);
                break;
            default:
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
