package com.floydd.instagramapp;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("EJavSN8fLglFAOOiBURR2QAeBdB5LDXSjCu9NL63")
                // if defined
                .clientKey("xSkk4Vs4ntI2qpSAwGiyFzQdmOxQRQHbRTwBq9Bg")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
