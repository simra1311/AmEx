package com.example.android.amex;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OptionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_INVITE = 10;
    private boolean flip = false;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    float[] value1  = {800,100,900,200,300,300,400,500,200,200};
    float[] value2 = new float[6] ;//= {800,100,900,200,300,300,400,500,200,200};
    float[] value3 = new float[6] ;//= {800,100,900,200,300,300,400,500,200,200};

    String uName;


    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        uName = intent.getStringExtra("NAME");
        Log.i("OptionsActivity",uName);
        String[] part = uName.split("@");
        uName = part[0];
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Options Activity","calling");
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        flip = false;



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //makePie();
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
//
        Data data1 = new Data(value1);
//        Data data2 = new Data(value2);
//        Data data3 = new Data(value3);
//
        pieChart.setData(data1.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        pieChart.setDescription("Your budget statistics");
//
//        PieChart pieChart1 = (PieChart) findViewById(R.id.piechart1);
//        pieChart1.setUsePercentValues(true);
//        pieChart1.setData(data2.getPieData());
//        pieChart1.setDrawHoleEnabled(true);
//        pieChart1.setTransparentCircleRadius(30f);
//        pieChart1.setHoleRadius(30f);
//
//        PieChart pieChart2 = (PieChart) findViewById(R.id.piechart2);
//        pieChart2.setUsePercentValues(true);
//        pieChart2.setData(data3.getPieData());
//        pieChart2.setDrawHoleEnabled(true);
//        pieChart2.setTransparentCircleRadius(30f);
//        pieChart2.setHoleRadius(30f);
//
//        if (flip == true){
//            pieChart1.setVisibility(View.VISIBLE);
//            pieChart2.setVisibility(View.VISIBLE);
//        }
//        else{
//            pieChart1.setVisibility(View.INVISIBLE);
//            pieChart2.setVisibility(View.INVISIBLE);
//        }

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren())
        {
            String str = ds.getValue().toString();

            String[] s = str.split(",");
            String name;
            int j = s[s.length-1].indexOf('=');
            name = s[s.length-1].substring(j+1,s[s.length-1].length()-1);

            if(name.equals(uName)) {
                //float[] value = new float[18];
                for (int i = 0; i+2 < s.length - 1; i+=3) {

                    // System.out.println(s[i]);

                    j = s[i].indexOf('=');
                    value1[i/3] = Float.parseFloat(s[i].substring(j + 1));

                    j = s[i+1].indexOf('=');
                    value2[i/3] = Float.parseFloat(s[i+1].substring(j + 1));

                    j = s[i+2].indexOf('=');
                    value3[i/3] = Float.parseFloat(s[i+2].substring(j + 1));
                    Log.i("OptionsActivity","value: "+value1[i/3]);

                   // System.out.println(value[i]);

                }
                makePie();
                break;
            }

         //   uInfo.setFood1(ds.getValue(UserInformation.class).getFood1());
//            uInfo.setFood2(ds.child(userID).getValue(UserInformation.class).getFood2());
//            uInfo.setFood3(ds.child(userID).getValue(UserInformation.class).getFood3());
//            uInfo.setLuxury1(ds.child(userID).getValue(UserInformation.class).getLuxury1());
//            uInfo.setLuxury2(ds.child(userID).getValue(UserInformation.class).getLuxury2());
//            uInfo.setLuxury3(ds.child(userID).getValue(UserInformation.class).getLuxury3());
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
//            uInfo.setClothes1(ds.child(userID).getValue(UserInformation.class).getClothes1());
//            uInfo.setClothes2(ds.child(userID).getValue(UserInformation.class).getClothes2());
//            uInfo.setClothes3(ds.child(userID).getValue(UserInformation.class).getClothes3());
//            uInfo.setHealth1(ds.child(userID).getValue(UserInformation.class).getHealth1());
//            uInfo.setHealth2(ds.child(userID).getValue(UserInformation.class).getHealth2());
//            uInfo.setHealth3(ds.child(userID).getValue(UserInformation.class).getHealth3());
//            uInfo.setHouse1(ds.child(userID).getValue(UserInformation.class).getHouse1());
//            uInfo.setHouse2(ds.child(userID).getValue(UserInformation.class).getHouse2());
//            uInfo.setHouse3(ds.child(userID).getValue(UserInformation.class).getHouse3());
//            uInfo.setTransport1(ds.child(userID).getValue(UserInformation.class).getTransport1());
//            uInfo.setTransport2(ds.child(userID).getValue(UserInformation.class).getTransport2());
//            uInfo.setTransport3(ds.child(userID).getValue(UserInformation.class).getTransport3());

           // toastMessage(uInfo.getFood1());
            //toastMessage(uInfo.getName());
        }
    }

    public void makePie(){
        Log.i("OPtions","Makepie called");
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        Data data1 = new Data(value1);
        Data data2 = new Data(value2);
        Data data3 = new Data(value3);

        pieChart.setData(data1.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        pieChart.setDescription("Your budget statistics");

        PieChart pieChart1 = (PieChart) findViewById(R.id.piechart1);
        pieChart1.setUsePercentValues(true);
        pieChart1.setData(data2.getPieData());
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setTransparentCircleRadius(30f);
        pieChart1.setHoleRadius(30f);

        PieChart pieChart2 = (PieChart) findViewById(R.id.piechart2);
        pieChart2.setUsePercentValues(true);
        pieChart2.setData(data3.getPieData());
        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setTransparentCircleRadius(30f);
        pieChart2.setHoleRadius(30f);

        if (flip == true){
            pieChart1.setVisibility(View.VISIBLE);
            pieChart2.setVisibility(View.VISIBLE);
            pieChart1.getLayoutParams().height = 1000;
            pieChart1.requestLayout();
            pieChart2.getLayoutParams().height = 1000;
            pieChart2.requestLayout();
        }
        else{
            pieChart1.setVisibility(View.INVISIBLE);
            pieChart2.setVisibility(View.INVISIBLE);
            pieChart1.getLayoutParams().height = 00;
            pieChart1.requestLayout();
            pieChart2.getLayoutParams().height = 00;
            pieChart2.requestLayout();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.past){
            flip = true;
            makePie();
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_expenses) {
            Intent intent = new Intent(OptionsActivity.this, OptionsActivity.class);
            intent.putExtra("NAME",uName);
            startActivity(intent);

        } else if (id == R.id.nav_budget) {
            Intent intent = new Intent(OptionsActivity.this, RecommendedActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_contact) {
            Toast.makeText(OptionsActivity.this,"Contact",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_share) {
            //onInviteClicked();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Download American Express App from play store");
            intent.setType("text/plain");

// Verify that the intent will resolve to an activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                // Get the invitation IDs of all sent messages
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d("TAG", "onActivityResult: sent invitation " + id);
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }
}

//{health(1)=50, clothes(1)=55, Food(1)=45, transport(3)=20, house rental and maintenance(2)=105, house rental and maintenance(3)=115, house rental and maintenance (1)=95, Luxury(2)=50, transport(2)=20, transport(1)=5, Food(2)=55, Food(3)=66, Luxury(3)=57, Luxury(1)=40, health(3)=75, health(2)=60, clothes(3)=75, clothes(2)=65, Name=Estella}