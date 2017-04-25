package com.example.ryo.gshellfragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        fragmentDialogHostEntry.HostEntryDialogListener,
        fragmentDialogHostDelete.HostDeleteDialogListener,
        fragmentDialogHostEditList.HostEditListDialogListener,
        fragmentDialogHostEdit.HostEditDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        //db.addHost(new Host("Tycho","apollo","apollo@tycho.xxx.xxx","password"));
        //db.addHost(new Host("Hex","ceto","ceto@hex.zzz.zzz","password2"));
        //db.addHost(new Host("Zeus","simon","simon@zeus.www.www","password3"));
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new fragmentHostList())
                    .commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
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
        FragmentManager manager = getSupportFragmentManager();
        if (id == R.id.nav_add) {
            fragmentDialogHostEntry testfrag = new fragmentDialogHostEntry();
            testfrag.show(manager,"fragment_host_entry");
            // Handle the camera action
        } else if (id == R.id.nav_edit) {
            fragmentDialogHostEditList testfrag = new fragmentDialogHostEditList();
            testfrag.show(manager,"fragment_host_edit_list");

        } else if (id == R.id.nav_delete) {
            fragmentDialogHostDelete testfrag = new fragmentDialogHostDelete();
            testfrag.show(manager,"fragment_host_delete");

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onHostEntryDialogPositiveClick(Host newEntry) {
        // user touched the dialog's positive button
        DatabaseHandler db = new DatabaseHandler(this);
        db.addHost(newEntry);
        //db.addHost(new Host("asssdss","apollo","apollo@tycho.xxx.xxx","password"));

        // calling the list fragment again so that it updates with the new db
        // kind of messy, probably a cleaner way of doing this
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new fragmentHostList())
                .commit();
    }

    @Override
    public void onHostDeleteDialogPositiveClick(List<Integer> checkedHosts, List<Host> hostData ) {
        DatabaseHandler db = new DatabaseHandler(this);
        //Toast.makeText(this,"clicked delete",Toast.LENGTH_SHORT).show();

        for (Integer id:checkedHosts) {
            Host host = hostData.get(id);
            //Toast.makeText(this,"deleted " + host._alias, Toast.LENGTH_SHORT).show();
            db.deleteHost(host);
        }
        // calling the list fragment again so that it updates with the new db
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new fragmentHostList())
                .commit();


    }

    @Override
    public void onHostEditDialogPositiveClick(Host host){
        DatabaseHandler db = new DatabaseHandler(this);
        db.updateHost(host);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new fragmentHostList())
                .commit();

    }

    @Override
    public void onHostEditListDialogPositiveClick(Host host) {
            FragmentManager manager = getSupportFragmentManager();
            fragmentDialogHostEdit testfrag = fragmentDialogHostEdit.newInstance(host._id);
        testfrag.show(manager,"fragment_host_edit");
    }

}
