package com.example.psit_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Modify extends AppCompatActivity {
    TextView fid,department,designation;
    EditText name,contact,whatsapp,cabin,extension,email;
    Button done;
    String Id;
    Spinner admin;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent=new Intent(Modify.this,UpdateData.class);
        intent.putExtra("FId",Id);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        fid=findViewById(R.id.modify_fid);
        name=findViewById(R.id.modify_name);
        department=findViewById(R.id.modify_department);
        designation=findViewById(R.id.modify_designation);
        contact=findViewById(R.id.modify_contact);
        whatsapp=findViewById(R.id.modify_whatsapp);
        cabin=findViewById(R.id.modify_cabin);
        extension=findViewById(R.id.modify_extension);
        email=findViewById(R.id.modify_email);
        admin=findViewById(R.id.modify_admin);
        done=findViewById(R.id.modify_done);
        Id=getIntent().getStringExtra("L3");
        String L1=getIntent().getStringExtra("L1");
        String L2=getIntent().getStringExtra("L2");
        DatabaseReference reference1= FirebaseDatabase.getInstance().getReference().child(L1).child(L2).child(Id);
        fid.setText(Id);
        final String[] s = new String[1];
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.admin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        admin.setAdapter(adapter);
        admin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s[0] =parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s[0]="no";
            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("name").getValue(String.class);
                name.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("department").getValue(String.class);
                department.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("cabin").getValue(String.class);
                cabin.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("designation").getValue(String.class);
                designation.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("contact").getValue(String.class);
                contact.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("email").getValue(String.class);
                email.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("extension").getValue(String.class);
                extension.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sk= snapshot.child("whatsapp").getValue(String.class);
                whatsapp.setText(sk);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final int[] f = {0};
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("") || department.getText().toString().equals("") || designation.getText().toString().equals("") || contact.getText().toString().equals("") || whatsapp.getText().toString().equals("") || cabin.getText().toString().equals("") || extension.getText().toString().equals("") || email.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data Field cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Data Modified!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),UpdateData.class);
                    intent.putExtra("_name",name.getText().toString());
                    intent.putExtra("_contact",contact.getText().toString());
                    intent.putExtra("_whatsapp",whatsapp.getText().toString());
                    intent.putExtra("_cabin",cabin.getText().toString());
                    intent.putExtra("_extension",extension.getText().toString());
                    intent.putExtra("_email",email.getText().toString());
                    intent.putExtra("_admin",s[0]);
                    intent.putExtra("L1",L1);
                    intent.putExtra("L2",L2);
                    intent.putExtra("L3",Id);
                    intent.putExtra("FId",Id);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
