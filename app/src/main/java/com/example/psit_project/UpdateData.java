package com.example.psit_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class UpdateData extends AppCompatActivity {
    CardView modify,add,delete,reset_pwd;
    String pos,id,n;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent=new Intent(UpdateData.this,MainActivity.class);
        intent.putExtra("FId",id);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        modify=findViewById(R.id.modify);
        add=findViewById(R.id.add);
        delete=findViewById(R.id.delete);
        reset_pwd=findViewById(R.id.reset_pwd);
        id=getIntent().getStringExtra("FId");
        String Name=getIntent().getStringExtra("_name");
        String Admin=getIntent().getStringExtra("_admin");
        String Contact=getIntent().getStringExtra("_contact");
        String Whatsapp=getIntent().getStringExtra("_whatsapp");
        String Cabin=getIntent().getStringExtra("_cabin");
        String Extension=getIntent().getStringExtra("_extension");
        String Email=getIntent().getStringExtra("_email");
        String L1=getIntent().getStringExtra("L1");
        String L2=getIntent().getStringExtra("L2");
        String L3=getIntent().getStringExtra("L3");
        if(Name!=null){
            DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child(L1).child(L2).child(L3);
            reference1.child("name").setValue(Name);
            reference1.child("admin").setValue(Admin);
            reference1.child("contact").setValue(Contact);
            reference1.child("whatsapp").setValue(Whatsapp);
            reference1.child("cabin").setValue(Cabin);
            reference1.child("extension").setValue(Extension);
            reference1.child("email").setValue(Email);
        }
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
                builder.setTitle("Enter FId");
                final EditText input = new EditText(UpdateData.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id=input.getText().toString().toUpperCase(Locale.ROOT);
                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
                        final boolean[] v = {false};
                        ref.addListenerForSingleValueEvent(new ValueEventListener(){
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                                    for(DataSnapshot c2: c1.getChildren()){
                                        for(DataSnapshot c3: c2.getChildren()){
                                            if(id.equals(c3.getKey())){
                                                v[0] =true;
                                                Intent intent = new Intent(getApplicationContext(),Modify.class);
                                                intent.putExtra("L3",id);
                                                intent.putExtra("L1",c1.getKey());
                                                intent.putExtra("L2",c2.getKey());
                                                intent.putExtra("Name",n);
                                                intent.putExtra("Pos",pos);
                                                intent.putExtra("FId",id);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }
                                }
                                if(!v[0]){
                                    Toast.makeText(getApplicationContext(),"Invalid Id",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
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
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Add_new.class);
                intent.putExtra("FId",id);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
                builder.setTitle("Enter Faculty Id to be deleted");
                final EditText input = new EditText(UpdateData.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final int[] f = {0};
                        if(input.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Data field cannot be empty",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            final String[] d = {"",""};
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                                        for (DataSnapshot c2 : c1.getChildren()) {
                                            for (DataSnapshot c3 : c2.getChildren()) {
                                                if (input.getText().toString().toUpperCase(Locale.ROOT).equals(c3.getKey())) {
                                                    d[0] = c1.getKey();
                                                    d[1] = c2.getKey();
                                                    f[0] =1;
                                                }
                                            }
                                        }
                                    }
                                    if(f[0]==0){
                                        Toast.makeText(getApplicationContext(), "Invalid Faculty Id", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        String check=input.getText().toString().toUpperCase(Locale.ROOT);
                                        final DatabaseReference[] reference = {FirebaseDatabase.getInstance().getReference().child(d[0]).child(d[1]).child(check)};
                                        reference[0].removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getApplicationContext(), "Deletion Successful!", Toast.LENGTH_SHORT).show();
                                                    if (check.equals(id)) {
                                                        SharedPreferences preferences = getSharedPreferences("filename1", Context.MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = preferences.edit();
                                                        editor.clear();
                                                        editor.commit();
                                                        Intent intent=new Intent(UpdateData.this,Login.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
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
        reset_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
                builder.setTitle("Enter Faculty Id to be reset password");
                final EditText input = new EditText(UpdateData.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final int[] f = {0};
                        if(input.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Data field cannot be empty",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            final String[] d={"",""};
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                                        for (DataSnapshot c2 : c1.getChildren()) {
                                            for (DataSnapshot c3 : c2.getChildren()) {
                                                if (input.getText().toString().toUpperCase(Locale.ROOT).equals(c3.getKey())) {
                                                    d[0] = c1.getKey();
                                                    d[1] = c2.getKey();
                                                    f[0] =1;
                                                }
                                            }
                                        }
                                    }
                                    if(f[0]==0){
                                        Toast.makeText(getApplicationContext(), "Invalid Faculty Id", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        final DatabaseReference[] reference = {FirebaseDatabase.getInstance().getReference().child(d[0]).child(d[1]).child(input.getText().toString().toUpperCase(Locale.ROOT)).child("password")};
                                        reference[0].setValue("abc123");
                                        Toast.makeText(getApplicationContext(), "Password reset successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
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
}
