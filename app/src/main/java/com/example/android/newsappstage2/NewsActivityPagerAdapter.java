package com.example.android.newsappstage2;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsActivityPagerAdapter extends FragmentPagerAdapter {
    // Context
    Context context;
    SharedPreferences sharedPrefs;

    public NewsActivityPagerAdapter(FragmentManager fm, SharedPreferences sharedPrefs, Context context) {
        super(fm);
        this.context = context;
        this.sharedPrefs = sharedPrefs;
    }

    // Return the title for the current section
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.title_games);
            case 1:
                return context.getString(R.string.title_sports);
            case 2:
                return context.getString(R.string.title_culture);
            case 3:
                return context.getString(R.string.title_politics);
            case 4:
                return context.getString(R.string.title_books);
            case 5:
                return context.getString(R.string.title_technology);
            default:
                return context.getString(R.string.title_news);
        }
    }

    // Return the correct section for the current position
    @Override
    public Fragment getItem(int position) {

        String orderby = sharedPrefs.getString(context.getString(R.string.settings_order_by_key),context.getString(R.string.settings_order_by_default));
        String pageSize = sharedPrefs.getString(context.getString(R.string.settings_page_size_key),context.getString(R.string.settings_page_size_default));
        String keyword = sharedPrefs.getString(context.getString(R.string.settings_keyword_key),context.getString(R.string.settings_keyword_default));

        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                // Games
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_GAMES, orderby, pageSize, keyword));
                break;
            case 1:
                // Sports
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_SPORTS, orderby, pageSize, keyword));
                break;
            case 2:
                // Culture
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_CULTURE, orderby, pageSize, keyword));
                break;
            case 3:
                // Politics
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_POLITICS, orderby, pageSize, keyword));
                break;
            case 4:
                // Books
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_BOOKS, orderby, pageSize, keyword));
                break;
            case 5:
                // Technology
                bundle.putString("url", GuardianUrlBuilder.buildUrl(GuardianUrlBuilder.SECTION_TECHNOLOGY, orderby, pageSize, keyword));
                break;
            default:
                // News
                bundle.putString("url", GuardianUrlBuilder.buildUrl(null,null, null, null));
        }

        // Attach the bundle to the fragment and return that
        NewsSectionFragment newsSectionFragment = new NewsSectionFragment();
        newsSectionFragment.setArguments(bundle);
        return newsSectionFragment;
    }

    // Return the number of tabs
    @Override
    public int getCount() {
        return 6;
    }
}
