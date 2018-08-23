package com.example.android.newsappstage2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class NewsActivity extends AppCompatActivity {

    public static final String LOG_TAG = NewsActivity.class.getSimpleName();
    public SharedPreferences sharedPrefs;
    private String SELECTED_TAB = "selectedtab";
    int selectedTabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Find the view pager
        ViewPager viewPager = findViewById(R.id.view_pager);

        // shared preferences
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Create an adapter that knows which fragment should be shown on each page
        NewsActivityPagerAdapter adapter = new com.example.android.newsappstage2.NewsActivityPagerAdapter(getSupportFragmentManager(),sharedPrefs, this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);


        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // get the tab index when selecting the tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTabPosition = tab.getPosition();
                sharedPrefs.edit().putInt(SELECTED_TAB, selectedTabPosition).apply();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // sets the current tab back to what the user was on before they left for the settings
        // without this the app would go back to the first tab when you refresh.
        TabLayout.Tab tab = tabLayout.getTabAt(sharedPrefs.getInt(SELECTED_TAB,0));
        tab.select();

    }

    @Override
    // This method initialize the contents of the Activity's options menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    // This method is called whenever an item in the options menu is selected.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Returns completed URI

}
