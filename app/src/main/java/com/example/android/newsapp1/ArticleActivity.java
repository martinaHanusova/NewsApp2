package com.example.android.newsapp1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    public static final String LOG_TAG = ArticleActivity.class.getSimpleName();
    private static final String URL_REQUEST = "http://content.guardianapis.com/search?section=technology&show-fields=thumbnail&show-tags=contributor&api-key=93fdb283-039e-4d6f-880b-826e2b09337b";
    private static final int ARTICLE_LOADER_ID = 1;
    private ArticleAdapter articleAdapter;
    private ProgressBar progressBar;
    private TextView messageView;
    private ImageView backgroundImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ListView listView = findViewById(R.id.list);
        //progressBar = findViewById(R.id.loading);
       // messageView = findViewById(R.id.message_view);
        //listView.setEmptyView(messageView);

        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        listView.setAdapter(articleAdapter);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(ARTICLE_LOADER_ID, null, this);
        } else {
            //progressBar.setVisibility(View.GONE);
           // messageView.setText(R.string.no_internet);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(articleAdapter.getItem(position).getUrl()));
                startActivity(intent);
            }
        });


    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        return new ArticleLoader(this, URL_REQUEST);
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> data) {
        //messageView.setText(R.string.no_articles);
       // progressBar.setVisibility(View.GONE);
        articleAdapter.clear();

        if (data != null && !data.isEmpty()) {
            articleAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        articleAdapter.clear();
    }

}