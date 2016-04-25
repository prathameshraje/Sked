package com.androidfp.prath.sked;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignUpActivity extends AppCompatActivity {

    SqlHelper sh = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSignUp(View view){
        if(view.getId()==R.id.buttonSignUp){
            EditText name = (EditText)findViewById(R.id.editTextFullName);
            EditText email= (EditText)findViewById(R.id.editTextEmailID);
            EditText password = (EditText)findViewById(R.id.editTextPassword);
            EditText cpassword = (EditText)findViewById(R.id.editTextConfirmPassword);
            EditText contact = (EditText)findViewById(R.id.editTextContact);
            //String strName,strEmail,strPassword,strCPassword,strContact=null;
            String strName = name.getText().toString();
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();
            String strCPassword = cpassword.getText().toString();
            String strContact = contact.getText().toString();
            Log.d("name", strName);

            if(strName.matches("") || strEmail.matches("") || strPassword.matches("") || strCPassword.matches("") || strContact.matches("")){
                Toast nullValueError = Toast.makeText(SignUpActivity.this,"Kindly fill all the field", Toast.LENGTH_SHORT);
                nullValueError.show();

                if(strName.matches("")){
                    name.setError("Name required");
                }
                if(strEmail.matches("")){
                    email.setError("Email required");
                }
                if(strPassword.matches("")){
                    password.setError("Password required");
                }
                if(strCPassword.matches("")){
                    cpassword.setError("Re-enter password ");
                }
                if(strContact.matches("")){
                    contact.setError("Contact required");
                }
            }

            else if(!isValidEmail(strEmail)) {
                email.setError("Invalid Email");
            }

            else if(!strPassword.equals(strCPassword)){
                cpassword.setError("Passwords do not match!");
                Toast passwordMismatchError = Toast.makeText(SignUpActivity.this,"Password do not match!", Toast.LENGTH_SHORT);
                passwordMismatchError.show();
            }
            else{
                //Insert into Database (Create new user record)
                User user =new User();
                user.setName(strName);
                user.setEmail(strEmail);
                user.setPassword(strPassword);
                user.setContact(strContact);

                sh.addUser(user);
                Toast accountCreated = Toast.makeText(SignUpActivity.this,"Account Created!", Toast.LENGTH_SHORT);
                accountCreated.show();
            }
        }
    }

    // validating email id
    private boolean isValidEmail(String strEmail){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(strEmail);
        return matcher.matches();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
