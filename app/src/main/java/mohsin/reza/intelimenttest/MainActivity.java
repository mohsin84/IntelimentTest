package mohsin.reza.intelimenttest;

import android.arch.lifecycle.LifecycleRegistry;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import mohsin.reza.intelimenttest.databinding.ActivityMainBinding;
import mohsin.reza.intelimenttest.ui.NavAdapter;
import mohsin.reza.intelimenttest.ui.Test1Fragment;
import mohsin.reza.intelimenttest.ui.Test2Fragment;

public class MainActivity extends AppCompatActivity  implements NavAdapter.OnItemClickListener,HasSupportFragmentInjector{

    private static final String STATE_TITLE = "title";
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mTestTitles;

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mTestTitles=getResources().getStringArray(R.array.test_names);

        //binding.drawerLayout.setDrawerShadow(R.mipmap.drawer_shadow,GravityCompat.END);
        binding.drawerLayout.setDrawerShadow(R.mipmap.drawer_shadow,GravityCompat.START);
        binding.leftDrawer.setHasFixedSize(true);

        binding.leftDrawer.setAdapter(new NavAdapter(mTestTitles,this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toggle=new ActionBarDrawerToggle(this,binding.drawerLayout,R.string.drawer_open,R.string.drawer_close){//check this
            public void onDrawerClosed(View view) {
                setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        binding.drawerLayout.addDrawerListener(toggle);
        if (savedInstanceState == null) {
            selectItem("Test Two");
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(STATE_TITLE, getSupportActionBar().getTitle());
    }

    public void selectItem(String position){
        Fragment fragment=null;
        switch(position){
            case "Test One":
                fragment=new Test1Fragment();
                break;
            case "Test Two":
                fragment=new Test2Fragment();
                break;
            default:
                binding.drawerLayout.closeDrawers();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
        setTitle(position);
        binding.drawerLayout.closeDrawers();
    }
    @Override
    public void onClick(String position) {
        selectItem(position);
    }
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            /*case R.id.action_websearch:
                // create intent to perform web search for this planet
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
                // catch event that there's no activity to handle intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
                }
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector(){
        return dispatchingAndroidInjector;
    }
    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

}
