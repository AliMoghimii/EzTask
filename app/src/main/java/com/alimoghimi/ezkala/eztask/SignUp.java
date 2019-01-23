package com.alimoghimi.ezkala.eztask;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Character.isJavaIdentifierPart;

public class SignUp extends AppCompatActivity  {

//-----------------------------------------------------------------------------------------------------------------------------------------------Declarations
//-----------------------------------------------------------------------------------------------------------------------------------------------

    static List<Users> users = new ArrayList<>();
    File f = new File("D:\\dataAndroid\\data.txt");


    EditText SignUpTextUser;
    EditText SignUpTextPass;
    EditText SignUpTextName;
    EditText SignUpTextLName;
    EditText SignUpTextEmail;
    Button SignUpbtn2;
    RadioGroup radioGroup;
    RadioButton radioButton;

    boolean UserValid;
    boolean PassValid;
    boolean EmailValid;
    boolean NameValid;
    boolean LNameValid;


//----------------------------------------------------------------------------------------------------------------------------------------------- Functions
//-----------------------------------------------------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------------------------------- Username Validation



    public static boolean validUsername(EditText usernameEditText) {

        String username = usernameEditText.getText().toString();

        if (isDigit(username.charAt(0)))
        {
            usernameEditText.setError("A Username can't start with a Number");
            return false;
        }
        return true;

    }

//------------------------------------------------------------------------------------------------------------------------------- Email Validation

    public static boolean validEmail(EditText emailEditText){

        String email = emailEditText.getText().toString();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher myMatcher= pattern.matcher(email);
        return myMatcher.matches();
    }

//------------------------------------------------------------------------------------------------------------------------------- Password Validation

    public boolean validPassword(EditText passEditText) {

        String password = passEditText.getText().toString();
        int numberUpper = 0, numberLower = 0, numberDigit = 0;

        if (password.length() < 8) {
            passEditText.setError("Your Password should be more than 8 characters");
            return false;
        }


        for (int i = 0; i < password.length(); i++) {

            if (isDigit(password.charAt(i))) {
                numberDigit++;
            } else if (password.charAt(i) <= 'z' && password.charAt(i) >= 'a') {
                numberLower++;
            } else if (password.charAt(i) <= 'Z' && password.charAt(i) >= 'A') {
                numberUpper++;
            }

        }

        if (numberDigit == 0 || numberLower == 0 || numberUpper == 0) {
            passEditText.setError("Your Password should include atleast one capital letter , small cap letter and a number");
            //errortext.setText("Your Password should include atleast one capital letter , small cap letter and a number");
//
            return false;
        } else {

            return true;
        }
    }

//------------------------------------------------------------------------------------------------------------------------------- Set User Data

    public void SetData(EditText username, EditText password, EditText email, EditText name, EditText fname) {
//aghar radio button 1 bood
        users.add(new Users(username.getText().toString(), password.getText().toString(), email.getText().toString(), name.getText().toString(), fname.getText().toString()));

    }

//------------------------------------------------------------------------------------------------------------------------------- Duplicated Username

    public boolean duplicatedUsername(EditText usernameEditText){

        String username = usernameEditText.getText().toString();

        for (int i = 0; i < Users.NumberUsers ; i++) {

            if(users.get(i).getUsername().equals((username)))
            {
                usernameEditText.setError("A Username by this ID is already in the system");
                return false;
            }

        }
return true;

        /*Iterator<Users> it = users.iterator();
        while(it.hasNext())
        { }*/

    }

//------------------------------------------------------------------------------------------------------------------------------- Duplicated Email

    public boolean duplicatedEmail(EditText emailEditText) {

        String email = emailEditText.getText().toString();

        for (int i = 0; i < Users.NumberUsers; i++) {

            if (users.get(i).getEmail().equals((email))) {
                emailEditText.setError("A Email by this ID is already in the system");
                return false;
            }

        }
        return true;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------Main
//-----------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpTextUser = (EditText) findViewById(R.id.usernametxt);
        SignUpTextPass = (EditText) findViewById(R.id.passwordtxt);
        SignUpTextEmail = (EditText) findViewById(R.id.emailtxt);
        SignUpTextName = (EditText) findViewById(R.id.nametxt);
        SignUpTextLName = (EditText) findViewById(R.id.fnametxt);
        SignUpbtn2 = (Button) findViewById(R.id.signupbtn2);
        radioGroup = (RadioGroup) findViewById(R.id.rg1);

        SignUpbtn2.setEnabled(false);
        UserValid = false;
        EmailValid = false;
        PassValid = false;
        NameValid = false;
        LNameValid = false;

        SignUpTextUser.setError("Fill this box");
        SignUpTextPass.setError("Fill this box");
        SignUpTextEmail.setError("Fill this box");
        SignUpTextName.setError("Fill this box");
        SignUpTextLName.setError("Fill this box");



//--------------------------------------------------------------------------------------------------- Username Box

            try {

                //users.add(new Users("Ali", "aaaaaaaa1A", "ali_moghimi@yahoo.com", "Ali", "Moghimi"));

                SignUpTextUser.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence signUpTextUser, int start, int before, int count) {

                        if (!duplicatedUsername(SignUpTextUser) || !validUsername(SignUpTextUser)) {
                            UserValid = false;
                        }

                        else if (signUpTextUser.toString().isEmpty()) {
                            SignUpTextUser.setError("Fill this box");
                            UserValid = false;
                        }
                        else if (!signUpTextUser.toString().isEmpty() && validUsername(SignUpTextUser) && duplicatedUsername(SignUpTextUser))
                            UserValid = true;

                        if (UserValid && EmailValid && PassValid && NameValid && LNameValid)
                            SignUpbtn2.setEnabled(true);
                        else
                            SignUpbtn2.setEnabled(false);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

//--------------------------------------------------------------------------------------------------- Password Box

                SignUpTextPass.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence signUpTextPass, int start, int before, int count) {

                        if (!validPassword(SignUpTextPass))
                            PassValid = false;

                        if (signUpTextPass.toString().isEmpty()) {
                            SignUpTextPass.setError("Fill this box");
                            PassValid = false;
                        } else if (!signUpTextPass.toString().isEmpty() && validPassword(SignUpTextPass))
                            PassValid = true;

                        if (UserValid && EmailValid && PassValid && NameValid && LNameValid)
                            SignUpbtn2.setEnabled(true);
                        else
                            SignUpbtn2.setEnabled(false);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

//--------------------------------------------------------------------------------------------------- Email Box

                SignUpTextEmail.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence signUpTextEmail, int start, int before, int count) {

                        if (!duplicatedEmail(SignUpTextEmail)) {
                            EmailValid = false;
                        }


                        if (!validEmail(SignUpTextEmail)) {
                            SignUpTextEmail.setError("Invalid Email Format");
                            EmailValid = false;
                        }


                        if (signUpTextEmail.toString().isEmpty()) {
                            SignUpTextEmail.setError("Fill this box");
                            EmailValid = false;
                        } else if (validEmail(SignUpTextEmail) && !signUpTextEmail.toString().isEmpty() && duplicatedEmail(SignUpTextEmail)) {
                            EmailValid = true;
                        }

                        if (UserValid && EmailValid && PassValid && NameValid && LNameValid)
                            SignUpbtn2.setEnabled(true);
                        else
                            SignUpbtn2.setEnabled(false);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

//--------------------------------------------------------------------------------------------------- Name Box

                SignUpTextName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence signUpTextName, int start, int before, int count) {

                        if (signUpTextName.toString().isEmpty()) {
                            SignUpTextName.setError("Fill this box");
                            NameValid = false;
                        } else if (!signUpTextName.toString().isEmpty())
                            NameValid = true;

                        if (UserValid && EmailValid && PassValid && NameValid && LNameValid)
                            SignUpbtn2.setEnabled(true);
                        else
                            SignUpbtn2.setEnabled(false);


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

//--------------------------------------------------------------------------------------------------- Last Name Box

                SignUpTextLName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence signUpTextLastName, int start, int before, int count) {

                        if (signUpTextLastName.toString().isEmpty()) {
                            SignUpTextLName.setError("Fill this box");
                            LNameValid = false;
                        } else if (!signUpTextLastName.toString().isEmpty())
                            LNameValid = true;

                        if (UserValid && EmailValid && PassValid && NameValid && LNameValid)
                            SignUpbtn2.setEnabled(true);
                        else
                            SignUpbtn2.setEnabled(false);


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

//-------------------------------------------------------------------------------------------------------Change Intent

                SignUpbtn2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        SetData(SignUpTextUser, SignUpTextPass, SignUpTextEmail, SignUpTextName, SignUpTextLName);

                        Toast.makeText(getApplicationContext(), "Account created successfully " + users.get(0).getName() + " " + users.get(0).getFamilyName(), Toast.LENGTH_LONG).show();


                        Intent SignInIntent = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(SignInIntent);


                    }

                });
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

}
