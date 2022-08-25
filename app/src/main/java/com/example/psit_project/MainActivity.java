package com.example.psit_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView it,cse,administration,fac_name;
    CardView CSE_DEP,IT_DEP,CS_DEP,ADMIN_DEP,ME_DEP,ECE_DEP,ELE_DEP,PH_DEP,MBA_DEP,MCA_DEP;
    RelativeLayout main_search;
    Button log_out,reset_password;
    String Id;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        intent.putExtra("FId",Id);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.my_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getTitle().equals("Check for Update")){
                    Toast.makeText(getApplicationContext(),"No Update",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        Id=getIntent().getStringExtra("FId");
        final String[] d={"",""};
        final String[] name = new String[1];
        final String[] pos = new String[1];
        CardView admin_update=findViewById(R.id.admin_update);
        fac_name=findViewById(R.id.name);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot c1 : dataSnapshot.getChildren()) {
                    for(DataSnapshot c2: c1.getChildren()){
                        for(DataSnapshot c3: c2.getChildren()){
                            if(Id.equals(c3.getKey())){
                                d[0]=c1.getKey();
                                d[1]=c2.getKey();
                                name[0] =c3.child("name").getValue(String.class);
                                pos[0] =c3.child("admin").getValue(String.class);
                            }
                        }
                    }
                }
                //pos[0]="no";
                if(pos[0].equals("no")){
                    admin_update.setVisibility(View.GONE);
                }
                fac_name.setText(name[0]);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        main_search=findViewById(R.id.main_search);
        log_out=findViewById(R.id.log_out);
        reset_password=findViewById(R.id.reset_password);
        cse=findViewById(R.id.cse);
        it=findViewById(R.id.it);
        administration=findViewById(R.id.administration);
        CSE_DEP=findViewById(R.id.CSE_DEP);
        IT_DEP=findViewById(R.id.IT_DEP);
        CS_DEP=findViewById(R.id.CS_DEP);
        ADMIN_DEP=findViewById(R.id.ADMIN);
        ME_DEP=findViewById(R.id.ME_DEP);
        ECE_DEP=findViewById(R.id.ECE_DEP);
        ELE_DEP=findViewById(R.id.ELE_DEP);
        PH_DEP=findViewById(R.id.PH_DEP);
        MBA_DEP=findViewById(R.id.MBA_DEP);
        MCA_DEP=findViewById(R.id.MCA_DEP);
        Button update=findViewById(R.id.update);

        final String[] new_pwd = {""};
        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("New Password");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_pwd[0] = input.getText().toString();
                        ref.child(d[0]).child(d[1]).child(Id).child("password").setValue(new_pwd[0]);
                        Toast.makeText(getApplicationContext(),"Password Reset Successful!",Toast.LENGTH_SHORT).show();
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
        ADMIN_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Administration.class);
                startActivity(intent);
            }
        });

        CSE_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","CSE");
                startActivity(intent);
            }
        });

        IT_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","IT");
                startActivity(intent);
            }
        });
        CS_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","CS");
                startActivity(intent);
            }
        });
        ME_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","MECHANICAL");
                startActivity(intent);
            }
        });
        ECE_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","ECE");
                startActivity(intent);
            }
        });
        ELE_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","ELECTRICAL");
                startActivity(intent);
            }
        });
        PH_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","PHARMACY");
                startActivity(intent);
            }
        });
        MBA_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","MBA");
                startActivity(intent);
            }
        });
        MCA_DEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Department.class);
                intent.putExtra("department","MCA");
                startActivity(intent);
            }
        });

        main_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainSearch.class);
                startActivity(intent);
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("filename1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UpdateData.class);
                intent.putExtra("FId",Id);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}