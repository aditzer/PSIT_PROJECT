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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new extends AppCompatActivity {
    EditText fid1, name1, contact1, whatsapp1, cabin1, extension1, email1;
    Button done;
    Spinner department1,designation_title1,designation1,admin1;
    String intent_id;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent=new Intent(Add_new.this,UpdateData.class);
        intent.putExtra("FId",intent_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        intent_id=getIntent().getStringExtra("FId");
        fid1 =findViewById(R.id.add_fid);
        name1 =findViewById(R.id.add_name);
        department1 =findViewById(R.id.add_department);
        designation1 =findViewById(R.id.add_designation);
        designation_title1 =findViewById(R.id.add_designation_title);
        contact1 =findViewById(R.id.add_contact);
        whatsapp1 =findViewById(R.id.add_whatsapp);
        cabin1 =findViewById(R.id.add_cabin);
        extension1 =findViewById(R.id.add_extension);
        email1 =findViewById(R.id.add_email);
        admin1=findViewById(R.id.add_admin);
        done=findViewById(R.id.add_done);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department1.setAdapter(adapter);
        final String[] s = new String[4];
        department1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s[0] =parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s[0]="";
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.designation_title_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designation_title1.setAdapter(adapter1);
        designation_title1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s[1] =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s[1]="";
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.designation_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designation1.setAdapter(adapter2);
        designation1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s[2] =parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s[2]="";
            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.admin_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        admin1.setAdapter(adapter3);
        admin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s[3] =parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                s[3]="";
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fid= fid1.getText().toString();
                String Name= name1.getText().toString();
                String Department= s[0];
                String Designation= s[2];
                String _designation_title= s[1];
                String Contact= contact1.getText().toString();
                String Whatsapp= whatsapp1.getText().toString();
                String Cabin= cabin1.getText().toString();
                String Extension= extension1.getText().toString();
                String Email= email1.getText().toString();
                String Admin=s[3];
                if(Fid.equals("") || Name.equals("") || Department.equals("") || Designation.equals("") || _designation_title.equals("") || Contact.equals("") || Whatsapp.equals("") || Cabin.equals("") || Extension.equals("") || Email.equals("")){
                    Toast.makeText(getApplicationContext(),"Data Field cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    NewData newData= new NewData(Admin,Cabin,Contact,Department,Designation,Email,Extension,Fid,Name,"abc123",Whatsapp);
                    DatabaseReference reference;
                    if(_designation_title.equals("Faculty") || _designation_title.equals("HOD")){
                        reference= FirebaseDatabase.getInstance().getReference().child(Department).child(_designation_title);
                    }
                    else{
                        reference= FirebaseDatabase.getInstance().getReference().child("Administration").child(_designation_title);
                    }
                    reference.child(Fid).setValue(newData);
                    Toast.makeText(getApplicationContext(),"Data added", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Add_new.this,UpdateData.class);
                    intent.putExtra("FId",intent_id);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}