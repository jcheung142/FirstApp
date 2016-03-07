//package com.example.jonathancheung.firstapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Layout;
//import android.util.SparseBooleanArray;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.SearchView;
//
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
///*
//1) SET TIMER TO START NEW ACTIVITY
//2) FIX TIMER NOT SHOWING RIGHT NUMBER
//*/
///**
// * // * Created by Victor on 3/5/2016.
// * //
// */
//public class Groups extends AppCompatActivity {
//    EditText group_text, group_name;
//    ListView member_list, UserList;
//    SearchView member_search;
//    ArrayList<String> CurrentUsers = new ArrayList<>();
//    ArrayList<Integer> Checked = new ArrayList<>();
//    ArrayAdapter<String> DisplayUsers_toAdd;
//
//    public final static String member_key = "com.example.jonathancheung.firstap.member_key";
//    public final static String group_key = "com.example.jonathancheung.firstap.group_key";
//    public final static String groupName_key = "com.example.jonathancheung.firstap.group_key";
//    /**
//     * ATTENTION: This was auto-generated to implement the App Indexing API.
//     * See https://g.co/AppIndexing/AndroidStudio for more information.
//     */
//    private GoogleApiClient client;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_groups);
//        Firebase.setAndroidContext(this);
//        final Firebase ref = new Firebase("https://csm117-brainstorm.firebaseio.com/");
//        group_name = (EditText) findViewById(R.id.group_text);
//        member_list=(ListView) findViewById(R.id.UsersList);
//        member_search=(SearchView) findViewById(R.id.member_search);
//
//        //USER SELECTS USERS TO ADD FROM LISTVIEW
//        UserList = (ListView) findViewById(R.id.UsersList);
//        final Firebase UsernameList = ref.child("users");
//        UsernameList.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot child : dataSnapshot.getChildren())
//                    CurrentUsers.add(child.getValue().toString());
//
//                DisplayUsers_toAdd = new ArrayAdapter<String>(Groups.this, R.layout.group_item, R.id.UserCheckBox_Button, CurrentUsers);
//                UserList.setAdapter(DisplayUsers_toAdd);
//
////                member_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////                    @Override
////                    public boolean onQueryTextSubmit(String query) {
////                        return false;
////                    }
////                    @Override
////                    public boolean onQueryTextChange(String newText) {
////                        DisplayUsers_toAdd.getFilter().filter(newText);
////                        return false;
////                    }
////                });
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });
//
//
//        //go to new activity and create a new group object
//        Button createGroup = (Button) findViewById(R.id.create_group);
//        createGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                System.out.println("Checked items: " +UserList.getCheckedItemCount());
//                String groupName = group_name.getText().toString();
//                if (groupName.length() == 0) {
//                    group_name.setError("invalid group name");
//                }
//                else {
//                    //add new group to firebase along with associated users
////                    SparseBooleanArray checked = UserList.getCheckedItemPositions();
////                    if (checked != null) {
////                        for (int i = 0; i < UserList.getAdapter().getCount(); i++) {
////                            System.out.printf(String.valueOf(checked.get(i)));
////                            if (checked.get(i)) {
////                                ref.child("groups").child(group_name.getText().toString()).setValue("the");
////                            }
////                        }
////                    }
//
//                    Intent newGroup = new Intent(Groups.this, New_Session.class);
//                    newGroup.putExtra(groupName_key, groupName);
//                    startActivity(newGroup);
//                }
//            }
//        });
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//    }
//
////    public void search(View view) {
////        member_search = (EditText) findViewById(R.id.member_search);
////        group_text = (EditText) findViewById(R.id.group_text);
////
////        String group = group_text.getText().toString();
////    }
//
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Groups Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.jonathancheung.firstapp/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "Groups Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app deep link URI is correct.
//                Uri.parse("android-app://com.example.jonathancheung.firstapp/http/host/path")
//        );
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }
//}
