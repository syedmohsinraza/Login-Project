package com.example.mohsinraza.loginproj;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
private EditText username,password;
private Button buttonlogin,buttonfacebook;
private CheckBox remeber_me;
private ImageView firstImage;

    public static final String SETTING_INFOS = "TESTFILE";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firstImage=(ImageView)findViewById(R.id.image1);
        firstImage.setImageDrawable(getResources().getDrawable(R.drawable.light));
        singInListener();
        sharedpreferences = getSharedPreferences(SETTING_INFOS, Context.MODE_PRIVATE);
        username.setText(sharedpreferences.getString("name",""));
        password.setText(sharedpreferences.getString("pass",""));

    }
    public void singInListener(){
          username=(EditText)findViewById(R.id.username);
          password=(EditText)findViewById(R.id.password);
          buttonlogin=(Button)findViewById(R.id.Signin);
          remeber_me=(CheckBox)findViewById(R.id.chkRember);


buttonlogin.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        String strUsername=username.getText().toString();
        String strPassword=password.getText().toString();
       // String strRememberMe=remeber_me.getText().toString();
        if(remeber_me.isChecked()){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("name",strUsername);
            editor.putString("pass",strPassword);
            editor.commit();

        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (strUsername.equals("")){
            Context context = getApplicationContext();
            CharSequence text = "User should not be null!";

            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        else if(strPassword.equals("")){
            Toast.makeText(getApplicationContext(),"password field required",Toast.LENGTH_LONG).show();

        }
        else{
            if(!strPassword.isEmpty()){
                boolean testPassword=passwordCharValidation(strPassword);
                if(testPassword==false){
                    Toast.makeText(getApplicationContext(),"password field invalid",Toast.LENGTH_LONG).show();
                }
            }
        }
        if(strUsername.matches(emailPattern)){
            //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_LONG).show();
        }
        if(strUsername.matches(emailPattern)&& passwordCharValidation(strPassword)){
            Toast.makeText(getApplicationContext(),"Login Sucess", Toast.LENGTH_LONG).show();
            Intent i= new Intent(getApplicationContext(),TestingActivity.class);
            i.putExtra("username",strUsername);
            i.putExtra("password",strPassword);
            startActivity(i);
        }
    }
  });
 }
    public boolean passwordCharValidation(String password) {
    Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
        }


    }


