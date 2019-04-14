package com.example.healdyoilnp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawer;
    ViewFlipper v_flipper;

    String[] waterCategory={"Water treatment","Distribution"};

    // WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.almondiv,R.drawable.coconutsiv,R.drawable.peanutiv,R.drawable.sunfloweriv,};
        v_flipper = findViewById(R.id.v_flipper);

        for(int image: images){
            flipperImages(image);
        }

//        webview = findViewById(R.id.webview);
//        WebSettings ws = webview.getSettings();
//        ws.setJavaScriptEnabled(true);
//        webview.loadUrl("file:///android_assets/almond_oil.html");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Login) {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.action_Logout){
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        switch(id) {
            case R.id.nav_home:
                 Intent i= new Intent(this,MainActivity.class);
                 startActivity(i);

//                    setTitle("Home");
                break;
            case R.id.nav_profile:
                Intent i1=new Intent(this,ProfileActivity.class);
                startActivity(i1);
           break;
            case R.id.nav_mywishlist:

                break;
            case R.id.nav_chat:

                break;
            case R.id.nav_cart:

                break;
            case R.id.nav_myorder:

                break;
            case R.id.nav_myaddress:
                break;
            case R.id.nav_share:
                break;

            case R.id.nav_send:

                break;
            case R.id.nav_rateus:
                break;


        }

        return true;

    }

   private void flipperImages(int image) {
       ImageView imageView = new ImageView(this);
       imageView.setBackgroundResource(image);
       v_flipper.addView(imageView);
       v_flipper.setFlipInterval(3000); // 3 sec
       v_flipper.setAutoStart(true);

       // animation

       v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
       v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

   }
}
