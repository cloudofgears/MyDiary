package com.kiminonawa.mydiary.shared;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Locale;

/**
 * Created by daxia on 2017/1/10.
 */

public class MyDiaryApplication extends Application {

    boolean hasPassword = false;

    @Override
    public void onCreate() {
        super.onCreate();
        //Use Fresco
        Fresco.initialize(this);

        //To fix bug : spinner bg is dark when mode is night.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Check password
        if (SPFManager.getPassword(this).equals("")) {
            hasPassword = false;
        } else {
            hasPassword = true;
        }

        //init Theme & language
        initTheme();
        setLocaleLanguage();

    }

    private void initTheme() {
        ThemeManager themeManager = ThemeManager.getInstance();
        themeManager.setCurrentTheme(SPFManager.getTheme(this));
    }

    private void setLocaleLanguage() {
        Locale locale;
        switch (SPFManager.getLocalLanguageCode(this)) {
            case 1:
                locale = Locale.ENGLISH;
                break;
            case 2:
                locale = Locale.JAPANESE;
                break;
            case 3:
                locale = Locale.TRADITIONAL_CHINESE;
                break;
            case 4:
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case 5:
                locale = Locale.KOREAN;
                break;
            case 6:
                locale = new Locale("th", "");
                break;
            case 7:
                locale = Locale.FRENCH;
                break;
            case 8:
                locale = new Locale("es", "");
                break;
            // 0 = default = language of system
            default:
                locale = Locale.getDefault();
                break;
        }
        Locale.setDefault(locale);
        Configuration config = getBaseContext().getResources().getConfiguration();
        overwriteConfigurationLocale(config, locale);
    }

    private void overwriteConfigurationLocale(Configuration config, Locale locale) {
        config.locale = locale;
        getBaseContext().getResources()
                .updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }


    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }
}
