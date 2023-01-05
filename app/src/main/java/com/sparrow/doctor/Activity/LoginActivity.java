package com.sparrow.doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sparrow.doctor.Local_database.DBHandler;
import com.sparrow.doctor.Model.userModel;
import com.sparrow.doctor.Utility.CustomEditText;
import com.sparrow.doctor.Utility.LoadingDialog;
import com.sparrow.doctor.Utility.Session_management;
import com.sparrow.doctor.R;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    CustomEditText et_password1;


    LoadingDialog loadingDialog;
    boolean viewPass=false;
    SharedPreferences sp;


    RelativeLayout rv_user,rv_pass,rv_pass_eye;
    ImageView iv_user,iv_pass;
    EditText et_username,et_password;
    ImageView iv_showpass;

    String text;

    Button btn_login;
    private DBHandler databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DBHandler(LoginActivity.this);
        sp = getSharedPreferences("Login", MODE_PRIVATE);

        rv_user=findViewById(R.id.rv_user);
        rv_pass=findViewById(R.id.rv_pass);
        rv_pass_eye=findViewById(R.id.rv_pass_eye);
        iv_user=findViewById(R.id.iv_user);
        iv_pass=findViewById(R.id.iv_pass);

        et_username=findViewById(R.id.et_username);
        et_password1=findViewById(R.id.et_password1);
        et_password=findViewById(R.id.et_password);




        btn_login=findViewById(R.id.btn_login);
        iv_showpass=findViewById(R.id.iv_showpass);
        loadingDialog=new LoadingDialog(LoginActivity.this);


        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://sql5097.site4now.net/db_9f24e4_voywellness","Doctor","Doctor@2022");
            //here sonoo is database name, root is username and password
            String sql = "SELECT * FROM RegisterSelectAll where FK_CustomerID= 2";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Log.d("resultSet", String.valueOf(resultSet));
            con.close();
        }catch(Exception e){
            System.out.println(e);
            Log.d("resultSet",e.getMessage());
        }


        //or to support all versions use
        Typeface typeface = ResourcesCompat.getFont(LoginActivity.this, R.font.poppins_medium);
        btn_login.setEnabled(false);


        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                rv_user.setBackground(getResources().getDrawable(R.drawable.custom_active_input));
                iv_user.setBackgroundResource(R.drawable.user_focus);
                et_username.setTextColor(getResources().getColor(R.color.Green));
                et_username.setTypeface(typeface);
//                et_username.setTextColor();
            }
        });

        et_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                rv_pass.setBackground(getResources().getDrawable(R.drawable.custom_active_input));
                iv_pass.setBackgroundResource(R.drawable.pass_focus);
                et_password.setTextColor(getResources().getColor(R.color.Green));
                et_password.setTypeface(typeface);
//                et_username.setTextColor();


            }
        });





        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                btn_login.setEnabled(false);
                btn_login.setBackground(getResources().getDrawable(R.drawable.button));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!et_password.getText().toString().equals("")){
                    btn_login.setEnabled(true);
                    btn_login.setBackground(getResources().getDrawable(R.drawable.button_active));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rv_pass_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                iv_showpass.setBackgroundResource(R.drawable.eye_focus);
//                et_password.setTransformationMethod(null);
                if (viewPass){
                    viewPass=false;
                    iv_showpass.setBackgroundResource(R.drawable.eye);
                    et_password.setTransformationMethod(new AsteriskPasswordTransformationMethod());

                }else{
                    viewPass=true;
                    iv_showpass.setBackgroundResource(R.drawable.eye_focus);
                    et_password.setTransformationMethod(null);
                }
            }
        });





        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidate()){
                    loadingDialog.showLoadingDialog();
                    byte[] data = new byte[0];
                    try {
                        data = et_password.getText().toString().getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String enc_pass = Base64.encodeToString(data, Base64.DEFAULT);

                    Log.d("base64",enc_pass);

// Receiving side
                    byte[] data1 = Base64.decode(enc_pass, Base64.DEFAULT);
                    try {
                         text = new String(data1, "UTF-8");
                        Log.d("base64decode",text);

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }




                    WebService.getClient().getLoginData(et_username.getText().toString(),
                            /*enc_pass*/et_password.getText().toString()
                           ).enqueue(new Callback<userModel>() {
                        @Override
                        public void onResponse(Call<userModel> call, Response<userModel> response) {
                            loadingDialog.dismissLoadingDialog();
                            Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            if (response.body().getSuccess().equals(true)){
                               /* sp.edit().putString("emp_id", response.body().getUser_id()).apply();
                                sp.edit().putString("emp_name", response.body().getUsername()).apply();*/


                                Session_management session_management = new Session_management(LoginActivity.this);
                                session_management.createLoginSession(response.body().getUser_id(), response.body().getUsername(),text);

//                                Intent intent=new Intent(LoginActivity.this,CalenderMain.class);
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<userModel> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });
                 /*   Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);*/
//                    loadingDialog.dismissLoadingDialog();
                    
                }
            }
        });


//        et_password.setTransformationMethod(null);

        et_password1.setDrawableClickListener(new CustomEditText.DrawableClickListener() {


            public void onClick(CustomEditText.DrawableClickListener.DrawablePosition target) {
                switch (target) {
                    case RIGHT:
                        //Do something here
                        et_password.setTransformationMethod(null);
                        Log.d("etpasss","etpasss");
                        break;

                    default:
                        break;
                }
            }

        });
    }

    private boolean isValidate() {
        String mobile = et_username.getText().toString().trim();
        if (mobile.isEmpty()) {
            Toast.makeText(this, "Please Enter a proper username", Toast.LENGTH_SHORT).show();
            et_username.requestFocus();
            return false;
        }else if (et_password.getText().toString().equals("")) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            et_password.requestFocus();
            return false;
        }/*else   if (!databaseHelper.checkUser(et_username.getText().toString().trim()
                , et_password.getText().toString().trim())) {
            return false;
        }*/else{
            return  true;
        }

    }

    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }

            public char charAt(int index) {
                return '*'; // This is the important part
            }

            public int length() {
                return mSource.length(); // Return default
            }

            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    }

   /* private class DoNothingTransformation implements TransformationMethod {
    }*/
}