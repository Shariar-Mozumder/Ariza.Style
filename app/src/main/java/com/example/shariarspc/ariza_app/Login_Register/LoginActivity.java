package com.example.shariarspc.ariza_app.Login_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.UserloginMutation;
import com.example.shariarspc.ariza_app.Cart.CartProductShow;
import com.example.shariarspc.ariza_app.MainActivity;
import com.example.shariarspc.ariza_app.R;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    Button loginBtn;
    TextView forgotPass,REgistrationHere;
    CheckBox rememberME;

    String emailtext,passtext;

    Handler handler22=new Handler();

    ApolloClient apolloClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inIt();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .okHttpClient(client)
                .build();

        emailtext=email.getText().toString();
        passtext=pass.getText().toString();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggingIn();
            }
        });
        REgistrationHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void inIt() {
        email=findViewById(R.id.emailLogin);
        pass=findViewById(R.id.passLogin);
        loginBtn=findViewById(R.id.LoginBTn);
        forgotPass=findViewById(R.id.forgotPass);
        rememberME=findViewById(R.id.rememberMe);
        REgistrationHere=findViewById(R.id.registrationHere);

    }

    private void loggingIn(){

        handler22.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (emailtext!=null&&passtext!=null) {

                    apolloClient.mutate(new UserloginMutation(emailtext, passtext)).enqueue(new ApolloCall.Callback<UserloginMutation.Data>() {
                        @Override
                        public void onResponse(@NotNull Response<UserloginMutation.Data> response) {
                           // Toast.makeText(LoginActivity.this, "LoggedIn Successfully" + response.data(), Toast.LENGTH_SHORT).show();
                            Log.d("LoginData", "onResponse: " + response.data());
                            Intent intent = new Intent(LoginActivity.this, CartProductShow.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        },2000);


    }
}
