package com.jiahaoliuliu.languageswitchbyprogramcode;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jiahaoliuliu.languageswitchbyprogramcode.MainActivity.Language;

import java.util.Locale;

/**
 * Created by Jiahao on 2/27/16.
 */
public class LanguageSelectionAdapter extends RecyclerView.Adapter<LanguageSelectionAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    private Context mContext;
    private Language[] mLanguagesList;

    public LanguageSelectionAdapter(Context context, Language[] languagesList) {
        this.mContext = context;
        this.mLanguagesList = languagesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        TextView v = (TextView)LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mLanguagesList[position].toString());

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Position clicked
                Toast.makeText(mContext, "Setting the new language to " + mLanguagesList[position].toString(),
                        Toast.LENGTH_SHORT).show();
                setLocale(mLanguagesList[position].getIsoCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguagesList.length;
    }

    private void setLocale(String isoCode) {
        Resources res = mContext.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(isoCode);
        res.updateConfiguration(conf, dm);
    }
}
