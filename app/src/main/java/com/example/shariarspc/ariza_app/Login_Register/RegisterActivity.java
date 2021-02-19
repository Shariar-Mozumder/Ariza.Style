package com.example.shariarspc.ariza_app.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.RegisterMutation;
import com.example.shariarspc.ariza_app.R;
import com.example.type.CustomerInput;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class RegisterActivity extends AppCompatActivity{
    EditText firstName,lastname,email,phone,password,address,city,company,confirmPass;
    CheckBox checkBox;
    Button registerBtn;
    TextView loginText;

    String firstN,lastN,mail,phoneN,pass,addr,cityN,compny,confirm;
    boolean agreed;

    ApolloClient apolloClient;
    Handler handler=new Handler();

    CustomerInput customerInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inIt();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .okHttpClient(client)
                .build();

        firstN = firstName.getText().toString();
        lastN = lastname.getText().toString();
        mail = email.getText().toString();
        phoneN = phone.getText().toString();
        pass = password.getText().toString();
        confirm = confirmPass.getText().toString();
        addr = address.getText().toString();
        cityN = city.getText().toString();
        compny = company.getText().toString();
        agreed = checkBox.isChecked();

       customerInput=CustomerInput.builder().customer_group_id(1).firstname(firstN)
                .lastname(lastN)
                .email(mail)
                .telephone(phoneN)
                .address_1(addr)
                .address_2(addr)
                .city(cityN)
                .country_id("12")
                .zone_id("1")
                .password(pass)
                .confirm(confirm)
                .agree(agreed)
                .fax("default")
                .company(compny)
                .postcode("3900").build();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userResister();

                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this, "Registration Done", Toast.LENGTH_SHORT).show();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userResister() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



                if (customerInput!=null){

                }

             //  Input<CustomerInput> input= Input.fromNullable(customerInput);

                // RegisterMutation registerMutation=new RegisterMutation(input);

                apolloClient.mutate(new RegisterMutation(Input.fromNullable(customerInput))).enqueue(new ApolloCall.Callback<RegisterMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<RegisterMutation.Data> response) {
                        Log.d("Registerrrrr", "onResponse: "+response.data().hashCode());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d("Register", "onFailure: "+e);
                    }
                });
            }
        },3000);
    }

    private void inIt() {
        firstName=findViewById(R.id.firstNameRegister);
        lastname=findViewById(R.id.lastNameRegister);
        email=findViewById(R.id.emailRegister);
        phone=findViewById(R.id.phoneNumberRegister);
        password=findViewById(R.id.passwordRegister);
        address=findViewById(R.id.addressRegister);
        city=findViewById(R.id.cityRegister);
        company=findViewById(R.id.companyRegister);

        checkBox=findViewById(R.id.checkTerms);

        registerBtn=findViewById(R.id.RegisterBTn);
        loginText=findViewById(R.id.AlreadyRegisterdText);
        confirmPass=findViewById(R.id.ConfirmpasswordRegister);

    }
}
