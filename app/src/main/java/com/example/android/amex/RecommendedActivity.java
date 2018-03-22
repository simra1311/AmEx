package com.example.android.amex;

import android.content.Intent;
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
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecommendedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_INVITE = 10;
    private boolean flip = false;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    float[] value1 = new float [6];
    float[] value2 = new float[6] ;//= {800,100,900,200,300,300,400,500,200,200};
    float[] value3 = new float[6] ;//= {800,100,900,200,300,300,400,500,200,200};
    float[] average  = {800,100,900,200,300,300,400,500,200,200};
    String uName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        uName = intent.getStringExtra("NAME");
        Log.i("OptionsActivity",UserInformation.getName());
        uName = UserInformation.getName();
        //String[] part = uName.split("@");
        //uName = part[0];
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

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
//
        Data data1 = new Data(average);
//        Data data2 = new Data(value2);
//        Data data3 = new Data(value3);
//
        pieChart.setData(data1.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);
        //pieChart.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setDescription("Your budget statistics");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            String str = ds.getValue().toString();

            String[] s = str.split(",");
            String name;
            int j = s[s.length - 1].indexOf('=');
            name = s[s.length - 1].substring(j + 1, s[s.length - 1].length() - 1);

            if (name.equals(uName)) {
                //float[] value = new float[18];
                for (int i = 0; i + 2 < s.length - 1; i += 3) {

                    // System.out.println(s[i]);

                    j = s[i].indexOf('=');
                    value1[i / 3] = Float.parseFloat(s[i].substring(j + 1));

                    j = s[i + 1].indexOf('=');
                    value2[i / 3] = Float.parseFloat(s[i + 1].substring(j + 1));

                    j = s[i + 2].indexOf('=');
                    value3[i / 3] = Float.parseFloat(s[i + 2].substring(j + 1));
                    Log.i("OptionsActivity", "value: " + value1[i / 3]);

                    // System.out.println(value[i]);

                }

                for(int i = 0; i<6; i++)
                {
                    average[i/3] = (value1[i]+value1[i]+value1[i])/3;
                   // System.out.println(average[i/3]);

                }
                makePie();
                break;
            }
        }}
    public void makePie(){
        Log.i("Options","Makepie called");
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        Data data1 = new Data(average);

        pieChart.setData(data1.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        pieChart.setDescription("Your budget statistics");

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
        getMenuInflater().inflate(R.menu.recommended, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
            Intent intent = new Intent(RecommendedActivity.this, OptionsActivity.class);
            //intent.putExtra("NAME",uName);
            startActivity(intent);

        } else if (id == R.id.nav_budget) {
            Intent intent = new Intent(RecommendedActivity.this, RecommendedActivity.class);
            //intent.putExtra("NAME",uName);
            startActivity(intent);

        } else if (id == R.id.nav_contact) {
            Toast.makeText(RecommendedActivity.this,"Contact",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RecommendedActivity.this,ContactActivity.class);
            //intent.putExtra("NAME",uName);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            //onInviteClicked();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Download American Express App from play store");
            intent.putExtra("NAME",uName);
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
}
