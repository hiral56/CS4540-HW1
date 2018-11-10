package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListener{

    private NewsRecyclerViewAdapter newsAdapter;
    private RecyclerView numberList;

    List<NewsItem> list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberList = findViewById(R.id.news_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);

        numberList.setHasFixedSize(true);

        makeNewsFeedGetQuery();

        newsAdapter = new NewsRecyclerViewAdapter(list_items, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_search:
                makeNewsFeedGetQuery();
        }
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.i("","index >>> "+clickedItemIndex);
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(list_items.get(clickedItemIndex).getUrl())));
    }

    private void makeNewsFeedGetQuery() {
        URL newsUrl = NetworkUtils.buildURL(NetworkUtils.base_url, NetworkUtils.query_parameter);
        new GetNewsFeed().execute(newsUrl);
    }

    public class GetNewsFeed extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {

            URL searchUrl = params[0];
            String results = null;
            try {
                results = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<NewsItem> items = JsonUtils.parseNews(result);

            list_items = items;

            newsAdapter.updateList(items);
            numberList.setAdapter(newsAdapter);
        }
    }
}
