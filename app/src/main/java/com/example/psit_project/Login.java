package com.example.psit_project;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class Login extends AppCompatActivity {
    public EditText fccode,password;
    public Button login_button;
    //public TextView forgot_pwd;
    SharedPreferences sharedpreferences;
    public static final String filename = "filename1" ;
    public static final String fcode = "fcode1";
    public static final String pwd = "password1";
    public int ans=0;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Login.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finish();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        fccode=findViewById(R.id.fccode);
        password=findViewById(R.id.password);
        login_button=findViewById(R.id.login_button);
        //forgot_pwd=findViewById(R.id.forgot_pwd);

        sharedpreferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        if(sharedpreferences.contains(fcode)){
            Intent intent=new Intent(Login.this,MainActivity.class);
            intent.putExtra("FId",sharedpreferences.getString("fcode1",""));
            startActivity(intent);
            finish();
        }
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fccode.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Faculty Code cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
                    final int[] f = {0};
                    ref.addListenerForSingleValueEvent(new ValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                                for(DataSnapshot c2: c1.getChildren()) {
                                    for(DataSnapshot c3: c2.getChildren()){
                                        if(fccode.getText().toString().toUpperCase(Locale.ROOT).equals(c3.getKey()) && password.getText().toString().equals(c3.child("password").getValue(String.class))){
                                            f[0] =1;
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString(fcode, fccode.getText().toString().toUpperCase());
                                            editor.putString(pwd, password.getText().toString());
                                            editor.commit();
                                            Intent intent=new Intent(Login.this,MainActivity.class);
                                            intent.putExtra("FId",fccode.getText().toString().toUpperCase(Locale.ROOT));
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                }
                            }
                            if(f[0]==0){
                                Toast.makeText(getApplicationContext(),"Invalid Faculty Code or Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        /*final String[] phoneno = {"@",""};
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Enter Faculty Code");
                final EditText input = new EditText(Login.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Send Message on Contact No.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions(Login.this, new String[] { Manifest.permission.SEND_SMS }, 1);
                        }
                        if(ContextCompat.checkSelfPermission(Login.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                            Log.d("ddddddddddddd","1111111111111");
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                                        for (DataSnapshot c2 : c1.getChildren()) {
                                            for (DataSnapshot c3 : c2.getChildren()) {
                                                if (c1.getKey().equals("Administration")) {
                                                    continue;
                                                }
                                                if (input.getText().toString().toUpperCase(Locale.ROOT).equals(c3.getKey())) {
                                                    phoneno[0]=c3.child("Contact").getValue(String.class);
                                                    phoneno[1]=c3.child("Password").getValue(String.class);
                                                }
                                            }
                                        }
                                    }
                                    if(phoneno[0].equals("@")){
                                        Toast.makeText(getApplicationContext(),"Invalid Faculty Code",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        sendSMS(phoneno[0],phoneno[1]);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else{
                            Log.d("ddddddddddddd","0000000000000");
                            dialog.cancel();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ans=1;
                }
                else {
                    ans=0;
                }
            }
        }
    }
    private void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {

                switch (getResultCode()) {

                    case Activity.RESULT_OK:

                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:

                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_NO_SERVICE:

                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_NULL_PDU:

                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_RADIO_OFF:

                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {

                switch (getResultCode()) {

                    case Activity.RESULT_OK:

                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case Activity.RESULT_CANCELED:

                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);*/
    }
}
