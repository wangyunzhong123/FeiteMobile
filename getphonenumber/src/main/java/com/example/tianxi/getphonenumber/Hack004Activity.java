package com.example.tianxi.getphonenumber;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;


public class Hack004Activity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

        Preference sharePref = findPreference("pref_share");
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check this app!");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                "Check this awesome app at: ...");
        sharePref.setIntent(shareIntent);

        Preference ratePref = findPreference("pref_rate");
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        ratePref.setIntent(goToMarket);

        updateUserText();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key) {

        if (key.equals("pref_username")) {
            updateUserText();
        }
    }

    private void updateUserText() {
        EditTextPreference pref;
        pref = (EditTextPreference) findPreference("pref_username");
        String user = pref.getText();

        if (user == null) {
            user = "?";
        }

        pref.setSummary(String.format("Username: %s", user));
    }
}