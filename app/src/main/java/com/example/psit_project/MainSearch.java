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
import java.util.HashMap;

public class MainSearch extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private SearchView search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentRowList = new ArrayList<ParentRow>();
    private ArrayList<String>S=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        displayList();

        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LayoutInflater layoutInflater = (LayoutInflater) MainSearch.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                TextView dep_id = v.findViewById(R.id.grp_id);
                int q = Integer.parseInt(dep_id.getText().toString());

                TextView ch_id = v.findViewById(R.id.ch_id);
                int p = Integer.parseInt(ch_id.getText().toString());

                TextView hidden_id=v.findViewById(R.id.hidden_id);
                String h_id=hidden_id.getText().toString();

                TextView code=v.findViewById(R.id.code);
                String s=code.getText().toString();

                DatabaseReference reference1;
                if(q<0) {
                    //for administration
                    View customView = layoutInflater.inflate(R.layout.activity_admin, null);
                    Button closePopupBtn = (Button) customView.findViewById(R.id.ad_close);
                    PopupWindow popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    if (p == 0) {
                        reference1 = FirebaseDatabase.getInstance().getReference().child("Administration").child("Director").child(h_id);
                    }
                    else if(p==1){
                        reference1 = FirebaseDatabase.getInstance().getReference().child("Administration").child("Managing Director").child(h_id);
                    }
                    else {
                        reference1 = FirebaseDatabase.getInstance().getReference().child("Administration").child("Dean").child(h_id);
                    }
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("name").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_name)).setText(sk);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("designation").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_designation)).setText(sk);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("contact").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_contact)).setText(sk);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_contact)).setPaintFlags(((TextView) popupWindow.getContentView().findViewById(R.id.ad_contact)).getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("cabin").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_cabin)).setText(sk);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("email").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_email)).setText(sk);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String sk= snapshot.child("whatsapp").getValue(String.class);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_whatsapp)).setText(sk);
                            ((TextView) popupWindow.getContentView().findViewById(R.id.ad_whatsapp)).setPaintFlags(((TextView) popupWindow.getContentView().findViewById(R.id.ad_whatsapp)).getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);
                    ((TextView) popupWindow.getContentView().findViewById(R.id.ad_contact)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String p=((TextView) popupWindow.getContentView().findViewById(R.id.ad_contact)).getText().toString();
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:"+p));
                            startActivity(intent);
                        }
                    });
                    ((TextView) popupWindow.getContentView().findViewById(R.id.ad_whatsapp)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openWhatsAppConversationUsingUri(MainSearch.this,((TextView) popupWindow.getContentView().findViewById(R.id.ad_whatsapp)).getText().toString(),"");
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
                else {
                    //for rest faculty
                    View customView = layoutInflater.inflate(R.layout.popup, null);
                    Button closePopupBtn = (Button) customView.findViewById(R.id.fac_close);
                    PopupWindow popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    if (p == 0) {
                        reference1 = FirebaseDatabase.getInstance().getReference().child(S.get(q)).child("HOD").child(h_id);
                    }
                    else {
                        reference1 = FirebaseDatabase.getInstance().getReference().child(S.get(q)).child("Faculty").child(h_id);
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
                            openWhatsAppConversationUsingUri(MainSearch.this,((TextView) popupWindow.getContentView().findViewById(R.id.fac_whatsapp)).getText().toString(),"");
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
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        setContentView(R.layout.activity_main_search);
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
        myList.setVisibility(View.INVISIBLE);
        listAdapter = new MyListAdapter(MainSearch.this, parentRowList);
        myList.setAdapter(listAdapter);
    }
    public static void openWhatsAppConversationUsingUri(Context context, String numberWithCountryCode, String message) {
        Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=" + numberWithCountryCode + "&text=" + message);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(sendIntent);
    }
    private void loadSomeData(){

    //Adding Hod list:

        DatabaseReference Department= FirebaseDatabase.getInstance().getReference();
        ArrayList<ChildRow> hodlist = new ArrayList<ChildRow>();
        hodlist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalhodlist = hodlist;
        HashMap<String, Integer> map = new HashMap<>();

        DatabaseReference Directors= FirebaseDatabase.getInstance().getReference().child("Administration").child("Director");
        ArrayList<ChildRow> directorlist = new ArrayList<ChildRow>();
        directorlist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finaldirectorlist = directorlist;
        final int[] k = {0};
        Directors.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String directorId=snapshot.getKey();
                    String directorName=snapshot.child("name").getValue(String.class);
                    ChildRow childRow = new ChildRow(-1, 0, directorName,directorId);
                    finaldirectorlist.add(childRow);
                    k[0]++;
                }
                Collections.sort(finaldirectorlist, new Comparator<ChildRow>() {
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
        ParentRow parentRowDirectors = new ParentRow("Director", finaldirectorlist);
        parentRowList.add(parentRowDirectors);

        /*final int[] md = {0};
        DatabaseReference managingDirectors= FirebaseDatabase.getInstance().getReference().child("Administration").child("Managing Director");
        ArrayList<ChildRow> managingdirectorlist = new ArrayList<ChildRow>();
        managingdirectorlist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalmanagingdirectorlist = managingdirectorlist;
        managingDirectors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String directorId=snapshot.getKey();
                    String directorName=snapshot.child("name").getValue(String.class);
                    ChildRow childRow = new ChildRow(-1, 1, directorName,directorId);
                    finalmanagingdirectorlist.add(childRow);
                    md[0]++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ParentRow parentRowManagingDirectors = new ParentRow("Managing Director", finalmanagingdirectorlist);
        parentRowList.add(parentRowManagingDirectors);*/

        DatabaseReference Dean= FirebaseDatabase.getInstance().getReference().child("Administration").child("Dean");
        ArrayList<ChildRow> Deanlist = new ArrayList<ChildRow>();
        Deanlist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalDeanlist = Deanlist;
        final int[] kf = {0};
        Dean.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String deanID=snapshot.getKey();
                    String deanName=snapshot.child("name").getValue(String.class);
                    ChildRow childRow = new ChildRow(-1, 2, deanName,deanID);
                    finalDeanlist.add(childRow);
                    kf[0]++;
                }
                Collections.sort(finalDeanlist, new Comparator<ChildRow>() {
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
        ParentRow parentRowdean = new ParentRow("Dean", finalDeanlist);
        parentRowList.add(parentRowdean);

        Department.addListenerForSingleValueEvent(new ValueEventListener(){
            ArrayList<String>dep_names=new ArrayList<String>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    //k[0]++;
                    String DEP=snapshot.getKey();
                    if(DEP.equals("Administration") || DEP.equals("Venue")){
                        continue;
                    }
                    dep_names.add(DEP);
                    map.put(DEP,map.size());
                    DatabaseReference HOD=FirebaseDatabase.getInstance().getReference().child(DEP).child("HOD");
                    HOD.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                            for(DataSnapshot snapshot1:dataSnapshot1.getChildren()){
                                String facultyId=snapshot1.getKey();
                                String hodName=snapshot1.child("name").getValue(String.class);
                                ChildRow childRow = new ChildRow(map.get(DEP),0, hodName,facultyId);
                                finalhodlist.add(childRow);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                Collections.sort(finalhodlist, new Comparator<ChildRow>() {
                    @Override
                    public int compare(ChildRow o1, ChildRow o2) {
                        return o1.getCode().compareTo(o2.getCode());
                    }
                });
                S=dep_names;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ParentRow parentRowhod = new ParentRow("HOD", finalhodlist);
        parentRowList.add(parentRowhod);
    //Adding Faculty List:

        ArrayList<ChildRow> Facultylist = new ArrayList<ChildRow>();
        Facultylist = new ArrayList<ChildRow>();
        ArrayList<ChildRow> finalFacultylist = Facultylist;
        Department.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String DEP = snapshot.getKey();
                    if(DEP.equals("Administration") || DEP.equals("Venue")){
                        continue;
                    }
                    DatabaseReference Faculty= FirebaseDatabase.getInstance().getReference().child(DEP).child("Faculty");
                    Faculty.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1){
                            for(DataSnapshot snapshot1:dataSnapshot1.getChildren()){
                                String facultyId=snapshot1.getKey();
                                String facultyName=snapshot1.child("name").getValue(String.class);
                                ChildRow childRow = new ChildRow(map.get(DEP),1, facultyName,facultyId);
                                finalFacultylist.add(childRow);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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
    public boolean onClose(){
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        if(query.equals("")){
            myList.setVisibility(View.INVISIBLE);
        }
        else {
            myList.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        if(query.equals("")){
            myList.setVisibility(View.INVISIBLE);
        }
        else {
            myList.setVisibility(View.VISIBLE);
        }
        return false;
    }
}