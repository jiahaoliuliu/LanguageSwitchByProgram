package com.jiahaoliuliu.languageswitchbyprogramcode;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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

    // Views
    private RecyclerView mLanguageSelectionRecyclerView;
    private RecyclerView.Adapter mLanguageSelectionAdapter;
    private RecyclerView.LayoutManager mLanguageSelectionLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

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
        mLanguageSelectionAdapter = new LanguageSelectionAdapter(this, Language.values());
        mLanguageSelectionRecyclerView.setAdapter(mLanguageSelectionAdapter);
    }
}
