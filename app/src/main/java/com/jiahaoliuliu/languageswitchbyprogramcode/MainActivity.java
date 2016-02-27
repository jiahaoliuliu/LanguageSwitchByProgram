package com.jiahaoliuliu.languageswitchbyprogramcode;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private String[] mLanguagesList = {"English", "Spanish", "Swedish", "Arabic", "German", "Tagaloc"};

    // Views
    private RecyclerView mLanguageSelectionRecyclerView;
    private RecyclerView.Adapter mLanguageSelectionAdapter;
    private RecyclerView.LayoutManager mLanguageSelectionLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mLanguageSelectionAdapter = new LanguageSelectionAdapter(this, mLanguagesList);
        mLanguageSelectionRecyclerView.setAdapter(mLanguageSelectionAdapter);
    }
}
