package com.alimoghimi.ezkala.eztask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Vector;

public class SignIn extends AppCompatActivity {

    Vector<Users> users = SignUp.users;

    EditText SignInTextUser;
    EditText SignInTextPass;
    TextView ErrorText;
    Button SignInbtn;
    static Users current;
    boolean PassLoginValid;


//------------------------------------------------------------------------------------------------------------------------------

    public boolean IsValidInfo(EditText usernameText , EditText passwordText){

        String usernameEmail = usernameText.getText().toString();
        String password = passwordText.getText().toString();

        for (int i = 0; i < Users.NumberUsers ; i++)
        {
            if(users.get(i).getUsername().equals(usernameEmail) || users.get(i).getEmail().equals(usernameEmail)  )
            {
                if(users.get(i).getPassword().equals(password))
                {
                    current = users.get(i);
                    return true;
                }
            }
        }
        return false;
    }

//------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignInTextUser = (EditText) findViewById(R.id.emailandusersignin);
        SignInTextPass = (EditText) findViewById(R.id.passsignin);
        SignInbtn = (Button) findViewById(R.id.loginbtn);
        ErrorText = (TextView) findViewById(R.id.errortxt);

        SignInbtn.setEnabled(false);
        PassLoginValid = false;

        //--------------------------------------------------------------

        try{

            //--------------------------------------------------------------

            SignInTextPass.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence signUpTextPass, int start, int before, int count) {


                    if(!IsValidInfo(SignInTextUser , SignInTextPass) && !SignInTextUser.toString().isEmpty() && !SignInTextPass.toString().isEmpty()) {
                        ErrorText.setText("Incorrect ID or password");
                        PassLoginValid = false;
                    }

                    if (SignInTextUser.getText().toString().isEmpty()) {
                        SignInTextUser.setError("Fill this box");
                        PassLoginValid = false;
                    }

                    if (SignInTextPass.getText().toString().isEmpty()) {
                        SignInTextPass.setError("Fill this box");
                        PassLoginValid = false;

                    }
                    else if (!signUpTextPass.toString().isEmpty() && IsValidInfo(SignInTextUser , SignInTextPass)) {
                        ErrorText.setText(" ");
                        PassLoginValid = true;
                    }

                    if (PassLoginValid)
                        SignInbtn.setEnabled(true);
                    else
                        SignInbtn.setEnabled(false);

                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            //-------------------------------------------------------------- logs in



            Button Signinbtn = (Button) findViewById(R.id.loginbtn);
            Signinbtn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {
                    ErrorText.setText(" ");
                    Toast.makeText(getApplicationContext(), "You logged in sucessfully " + current.getName() + " " + current.getFamilyName(), Toast.LENGTH_LONG).show();
                    Intent TaskPage = new Intent(getApplicationContext(),TaskPage.class);
                    startActivity(TaskPage);
                }

            });


        }
        //--------------------------------------------------------------
                catch(Exception e)
            {

            }


        //--------------------------------------------------------------sign up btn - to signuppage
        Button Signupbtn = (Button) findViewById(R.id.signupbtn);
        Signupbtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                Intent SignUp = new Intent(getApplicationContext(),SignUp.class);
                startActivity(SignUp);
            }

        });

    }
}
