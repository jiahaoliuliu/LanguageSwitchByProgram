package com.jiahaoliuliu.languageswitchbyprogramcode;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import com.jiahaoliuliu.languageswitchbyprogramcode.Preferences.StringId;

public class MainActivity extends AppCompatActivity implements IOnNewLanguageSelected{

    private static final String TAG = "MainActivity";

    public enum Language {
        English("en"), Español("es"), Svenska("se"), العربية("ar"), Deutsch("de"), Tagalog("tl");
        private String mIsoCode;

        Language(String isoCode) {
            this.mIsoCode = isoCode;
        }

        public String getIsoCode() {
            return mIsoCode;
        }
    }

    // Internal variables
    private Context mContext;
    private Preferences mPreferences;

    // Views
    private RecyclerView mLanguageSelectionRecyclerView;
    private RecyclerView.Adapter mLanguageSelectionAdapter;
    private RecyclerView.LayoutManager mLanguageSelectionLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = this;
        this.mPreferences = new Preferences(mContext);

        // The locale should be set before setting the content view
        if (mPreferences.contains(StringId.LANGUAGE_ISO_CODE)) {
            setLocale(mPreferences.get(StringId.LANGUAGE_ISO_CODE));
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title
        getSupportActionBar().setTitle(getString(R.string.current_language));

        // Link the views
        mLanguageSelectionRecyclerView = (RecyclerView) findViewById(R.id.language_selection_recycler_view);

        // This will improve the performance
        mLanguageSelectionRecyclerView.setHasFixedSize(true);

        // Using linear layout
        mLanguageSelectionLayoutManager = new LinearLayoutManager(this);
        mLanguageSelectionRecyclerView.setLayoutManager(mLanguageSelectionLayoutManager);

        // Set the adapter
        mLanguageSelectionAdapter = new LanguageSelectionAdapter(this, Language.values(), this);
        mLanguageSelectionRecyclerView.setAdapter(mLanguageSelectionAdapter);
    }

    @Override
    public void selectNewLanguage(Language newLanguage) {
        mPreferences.set(StringId.LANGUAGE_ISO_CODE, newLanguage.getIsoCode());
        restartApp();
    }

    private void setLocale(String isoCode) {
        Log.v(TAG, "Set the new language to " + isoCode);
        Resources res = mContext.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(isoCode);
        res.updateConfiguration(conf, dm);
    }

    private void restartApp() {
        finish();
    }

    @Override
    protected void onDestroy() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Intent for the pending intent
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Set the pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Wait for 500ms before start
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 500, pendingIntent);
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }
}
