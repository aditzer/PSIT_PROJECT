package com.example.psit_project;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Department extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentRowList = new ArrayList<ParentRow>();
    String Department;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        Department=getIntent().getStringExtra("department");
        displayList();
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LayoutInflater layoutInflater = (LayoutInflater) com.example.psit_project.Department.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View customView = layoutInflater.inflate(R.layout.popup, null);
                Button closePopupBtn = (Button) customView.findViewById(R.id.fac_close);
                PopupWindow popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                TextView grp_id = v.findViewById(R.id.grp_id);
                TextView hidden_id=v.findViewById(R.id.hidden_id);

                int q = Integer.parseInt(grp_id.getText().toString());
                String h_id=hidden_id.getText().toString();

                DatabaseReference reference1;
                if(q==0){
                    reference1=FirebaseDatabase.getInstance().getReference().child(Department).child("HOD").child(h_id);
                }
                else{
                    reference1=FirebaseDatabase.getInstance().getReference().child(Department).child("Faculty").child(h_id);
                }
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("name").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_name)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("department").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_department)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("cabin").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_cabin)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("designation").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_designation)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("contact").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_contact)).setText(sk);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_contact)).setPaintFlags(((TextView) popupWindow.getContentView().findViewById(R.id.fac_contact)).getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("email").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_email)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("fid").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_code)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("extension").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_extension)).setText(sk);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String sk= snapshot.child("whatsapp").getValue(String.class);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).setText(sk);
                        ((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).setPaintFlags(((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                ((TextView) popupWindow.getContentView().findViewById(R.id.fac_contact)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String p=((TextView) popupWindow.getContentView().findViewById(R.id.fac_contact)).getText().toString();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+p));
                        startActivity(intent);
                    }
                });
                ((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWhatsAppConversationUsingUri(com.example.psit_project.Department.this,((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).getText().toString(),"");
                    }
                });
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setContentView(R.layout.activity_department);
        return true;
    }

    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    private void displayList() {
        loadSomeData();

        myList = (ExpandableListView) findViewById(R.id.expandableList);
        listAdapter = new MyListAdapter(com.example.psit_project.Department.this, parentRowList);
        myList.setAdapter(listAdapter);

    }
    public static void openWhatsAppConversationUsingUri(Context context, String numberWithCountryCode, String message) {

        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + message);

        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);

        context.startActivity(sendIntent);
    }
    private void loadSomeData(){
        DatabaseReference Hod= FirebaseDatabase.getInstance().getReference().child(Department).child("HOD");
        ArrayList<ChildRow> hodlist = new ArrayList<ChildRow>();
        hodlist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalhodlist = hodlist;
        final int[] k = {0};
        Hod.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String facultyId= snapshot.getKey();
                    String hodName=snapshot.child("name").getValue(String.class);
                    //Log.d("ddddddddddddd",hodName+" "+facultyId);
                    ChildRow childRow = new ChildRow(0, k[0], hodName,facultyId);
                    finalhodlist.add(childRow);
                    k[0]++;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ParentRow parentRowhod = new ParentRow("HOD", finalhodlist);
        parentRowList.add(parentRowhod);

        DatabaseReference Faculty= FirebaseDatabase.getInstance().getReference().child(Department).child("Faculty");
        ArrayList<ChildRow> Facultylist = new ArrayList<ChildRow>();
        Facultylist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalFacultylist = Facultylist;
        final int[] kf = {0};
        Faculty.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String facultyId= snapshot.getKey();
                    String facultyName=snapshot.child("name").getValue(String.class);
                    //Log.d("ddddddddddddd",facultyName+" "+facultyId);
                    ChildRow childRow = new ChildRow(1, kf[0], facultyName,facultyId);
                    finalFacultylist.add(childRow);
                    kf[0]++;
                }
                Collections.sort(finalFacultylist, new Comparator<ChildRow>() {
                    @Override
                    public int compare(ChildRow o1, ChildRow o2) {
                        return o1.getCode().compareTo(o2.getCode());
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ParentRow parentRowfaculty = new ParentRow("Faculty", finalFacultylist);
        parentRowList.add(parentRowfaculty);
    }
    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

}
