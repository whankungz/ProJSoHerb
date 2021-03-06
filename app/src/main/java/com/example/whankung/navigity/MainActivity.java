package com.example.whankung.navigity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.whankung.navigity.adapter.AppState;
import com.example.whankung.navigity.adapter.HideKeyboard;

import com.facebook.stetho.Stetho;
import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tv, pro;
    ConnectionClass connectionClass;
    private View nav_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);


        Storage storage = SimpleStorage.getInternalStorage(getApplicationContext());


        connectionClass = new ConnectionClass();
        tv = (TextView) findViewById(R.id.toolbar_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_);

        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, new MainHerb());
        transaction.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_herb:
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.container, new MainHerb());
                                transaction.commit();


                                //     tv.setText("สมุนไพร");
                                break;
                            case R.id.action_disea:
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.container, new MainDisease());
                                transaction.commit();
//                                tv.setText("อาการ/โรค");
                                break;
                            case R.id.action_arti:
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.container, new MainArticle());
                                transaction.commit();
                                //   tv.setText("บทความ");
                                break;
                            case R.id.action_info:
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();
                                transaction.replace(R.id.container, new MainInfo());
                                transaction.commit();
                                //     tv.setText("รายการโปรด");
                                break;
                            case R.id.action_fav:
                                manager = getSupportFragmentManager();
                                transaction = manager.beginTransaction();

                                transaction.replace(R.id.container, new MainFavorite());
                                transaction.commit();
                                AppState.getSingleInstance().getDataHerb();
                                //     tv.setText("รายการโปรด");
                                break;
                        }
                        return true;
                    }
                });
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                supportFinishAfterTransition();
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
        navigationView.getMenu().findItem(R.id.nav_invite).setVisible(false);
        nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);

//     navigationView.addHeaderView(nav_header);


        if (AppState.getSingleInstance().isLogin()) {

            pro = (TextView) nav_header.findViewById(R.id.namePro);
            navigationView.addHeaderView(nav_header);

            pro.setText(AppState.getSingleInstance().getNamePhama());
            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_invite).setVisible(true);
            AppState.getSingleInstance().setLogin(false);

//            Singleton.getInstance().setYourFormattedString(yourFormattedString);
//            Singleton.getInstance().setYourFormattedString2(yourFormattedString2);
//            Singleton.getInstance().setYourFormattedString3(yourFormattedString3);
        }
//setLogin();
    }

    private void setLogin() {
        if (AppState.getSingleInstance().setLoggingOut(true)) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_invite).setVisible(false);
        } else {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.nav_invite).setVisible(true);

            //   navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        HideKeyboard.hideKeyboard(this);
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


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        HideKeyboard.hideKeyboard(this);
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
        HideKeyboard.hideKeyboard(this);
        if (id == R.id.nav_login) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        } else if (id == R.id.nav_food) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, new MainFood());
            transaction.commit();



        } else if (id == R.id.nav_office) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, new MainOffice());
            transaction.commit();


        } else if (id == R.id.nav_share) {
            //facebook
            String urlToShare = "https://play.google.com/store/apps";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
            intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

// See if official Facebook app is found
            boolean facebookAppFound = false;
            List<ResolveInfo> matches = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
            for (ResolveInfo info : matches) {
                if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                    intent.setPackage(info.activityInfo.packageName);
                    facebookAppFound = true;
                    break;
                }
            }

// As fallback, launch sharer.php in a browser
            if (!facebookAppFound) {
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));


            }
            startActivity(intent);
        } else if (id == R.id.nav_invite) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Social Herb for Android!");
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Application about Herb for Android");
            Intent chooserIntent = Intent.createChooser(shareIntent, "Share with");
            chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(chooserIntent);

        } else if (id == R.id.nav_logout) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("ออกจากระบบ")
                    .setMessage("คุณต้องการออกจากระบบหรือไม่ ?")
                    .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences myPrefs = getSharedPreferences("MY",
                                    MODE_PRIVATE);
                            SharedPreferences.Editor editor = myPrefs.edit();
                            editor.clear();
                            editor.commit();
                            AppState.getSingleInstance().setLoggingOut(true);
                            String TAG = "";
                            Log.d(TAG, "Now log out and start the activity login");
                            Intent intent = new Intent(MainActivity.this,
                                    Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                           // navigationView.setNavigationItemSelectedListener(this);
                            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_logout).setVisible(false);
                            navigationView.getMenu().findItem(R.id.nav_invite).setVisible(false);

                            // navigationView.removeHeaderView(nav_header);
                            pro.setVisibility(View.GONE);
                            AppState.getSingleInstance().setRating(false);
                            startActivity(intent);
                        }
                    }).setNegativeButton("ให้ฉันอยู่ต่อ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
