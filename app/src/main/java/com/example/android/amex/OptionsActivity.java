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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.google.android.gms.appinvite.AppInviteInvitation;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_INVITE = 10;
    private boolean flip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flip = false;

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

        //makePie();
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        yvalues.add(new Entry(17f, 5));

        PieDataSet dataSet = new PieDataSet(yvalues, "Budget");

        ArrayList<String> xVals = new ArrayList<String>();

//        xVals.add("January");
//        xVals.add("February");
//        xVals.add("March");
//        xVals.add("April");
//        xVals.add("May");
//        xVals.add("June");
//
//        PieData data = new PieData(xVals, dataSet);
//        // In percentage Term
//        data.setValueFormatter(new PercentFormatter());
//        // Default value
//        //data.setValueFormatter(new DefaultValueFormatter(0));
//
//        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        float[] expense = {800,100,900,200,300,300,400,500,200,200};
        Data data = new Data(expense);
        pieChart.setData(data.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        pieChart.setDescription("Your budget statistics");

        PieChart pieChart1 = (PieChart) findViewById(R.id.piechart1);
        pieChart1.setUsePercentValues(true);
        pieChart1.setData(data.getPieData());
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setTransparentCircleRadius(30f);
        pieChart1.setHoleRadius(30f);

        PieChart pieChart2 = (PieChart) findViewById(R.id.piechart2);
        pieChart2.setUsePercentValues(true);
        pieChart2.setData(data.getPieData());
        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setTransparentCircleRadius(30f);
        pieChart2.setHoleRadius(30f);

        PieChart pieChart3 = (PieChart) findViewById(R.id.piechart3);
        pieChart3.setUsePercentValues(true);
        pieChart3.setData(data.getPieData());
        pieChart3.setDrawHoleEnabled(true);
        pieChart3.setTransparentCircleRadius(30f);
        pieChart3.setHoleRadius(30f);

        if (flip == true){
            pieChart1.setVisibility(View.VISIBLE);
            pieChart2.setVisibility(View.VISIBLE);
            pieChart3.setVisibility(View.VISIBLE);
        }
        else{
            pieChart1.setVisibility(View.INVISIBLE);
            pieChart2.setVisibility(View.INVISIBLE);
            pieChart3.setVisibility(View.INVISIBLE);
        }

    }

    public void makePie(){
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);

//        ArrayList<Entry> yvalues = new ArrayList<Entry>();
//        yvalues.add(new Entry(8f, 0));
//        yvalues.add(new Entry(15f, 1));
//        yvalues.add(new Entry(12f, 2));
//        yvalues.add(new Entry(25f, 3));
//        yvalues.add(new Entry(23f, 4));
//        yvalues.add(new Entry(17f, 5));
//
//        PieDataSet dataSet = new PieDataSet(yvalues, "Budget");
//
//        ArrayList<String> xVals = new ArrayList<String>();
//
//        xVals.add("January");
//        xVals.add("February");
//        xVals.add("March");
//        xVals.add("April");
//        xVals.add("May");
//        xVals.add("June");
//
//        PieData data = new PieData(xVals, dataSet);
//        // In percentage Term
//        data.setValueFormatter(new PercentFormatter());
//        // Default value
//        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setUsePercentValues(true);
        float[] expense = {800,100,900,200,300,300,400,500,200,200};
        Data data = new Data(expense);
        pieChart.setData(data.getPieData());
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setHoleRadius(30f);

        pieChart.setDescription("Your budget statistics");

        PieChart pieChart1 = (PieChart) findViewById(R.id.piechart1);
        pieChart1.setUsePercentValues(true);
        pieChart1.setData(data.getPieData());
        pieChart1.setDrawHoleEnabled(true);
        pieChart1.setTransparentCircleRadius(30f);
        pieChart1.setHoleRadius(30f);

        PieChart pieChart2 = (PieChart) findViewById(R.id.piechart2);
        pieChart2.setUsePercentValues(true);
        pieChart2.setData(data.getPieData());
        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setTransparentCircleRadius(30f);
        pieChart2.setHoleRadius(30f);

        PieChart pieChart3 = (PieChart) findViewById(R.id.piechart3);
        pieChart3.setUsePercentValues(true);
        pieChart3.setData(data.getPieData());
        pieChart3.setDrawHoleEnabled(true);
        pieChart3.setTransparentCircleRadius(30f);
        pieChart3.setHoleRadius(30f);

        if (flip == true){
           pieChart1.setVisibility(View.VISIBLE);
           pieChart2.setVisibility(View.VISIBLE);
           pieChart3.setVisibility(View.VISIBLE);
           pieChart1.getLayoutParams().height = 1000;
           pieChart1.requestLayout();
           pieChart2.getLayoutParams().height = 1000;
           pieChart2.requestLayout();
           pieChart3.getLayoutParams().height = 1000;
           pieChart3.requestLayout();

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
            startActivity(intent);

        } else if (id == R.id.nav_budget) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_share) {
            onInviteClicked();

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
