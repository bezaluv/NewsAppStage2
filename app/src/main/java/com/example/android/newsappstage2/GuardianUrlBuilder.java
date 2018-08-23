package com.example.android.newsappstage2;

import android.support.annotation.Nullable;

public final class GuardianUrlBuilder {


    // Sections
    public static final String SECTION_GAMES = "&section=games",
            SECTION_SPORTS = "&section=sport",
            SECTION_CULTURE = "&section=culture",
            SECTION_POLITICS = "&section=politics",
            SECTION_BOOKS = "&section=books",
            SECTION_TECHNOLOGY = "&section=technology";
    private static final String LOG_TAG = GuardianUrlBuilder.class.getSimpleName();

    // empty constructor.
    private GuardianUrlBuilder() {
    }

    // strings for the sections that will be in each tab. these are constants.
    private static final String SHOW_FIELDS = "show-fields=thumbnail,byline";
    private static final String ORDERBY_PREFIX = "&order-by=";
    private static final String PAGE_SIZE_PREFIX = "&page-size=";
    private static final String KEYWORD_PREFIX = "$keyword";



    // Base of URL
    private static final String URL_BASE = " https://content.guardianapis.com/";
    // API Key for Guardian
    private static final String URL_API_KEY = "&api-key=2c69e5a3-208c-4e7c-ada4-44e284bc77c9";

    /**
     * Returns a Guardian API URL string
     *
     * @param section section in Guardian
     * @return URL string
     */
    public static String buildUrl(@Nullable String section,String orderBy, String pageSize, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();

        // Add the URL Base
        stringBuilder.append(URL_BASE);

        // If the section isn't null then add that
        if (section != null) {
            stringBuilder.append(section);

            // add the fields
            stringBuilder.append(SHOW_FIELDS);
            if (orderBy != null) {
                stringBuilder.append(ORDERBY_PREFIX);
                stringBuilder.append(orderBy);
            }

            if (pageSize != null){
                stringBuilder.append(PAGE_SIZE_PREFIX);
                stringBuilder.append(pageSize);
            }

            if (keyword != null){
                stringBuilder.append(KEYWORD_PREFIX);
                stringBuilder.append(keyword);
            }
        }


            // Add the API Key
        stringBuilder.append(URL_API_KEY);

        return stringBuilder.toString();
    }

}
