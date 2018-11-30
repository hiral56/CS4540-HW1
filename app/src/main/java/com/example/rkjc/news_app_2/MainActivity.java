package com.example.rkjc.news_app_2;

<<<<<<< HEAD
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
=======
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;

<<<<<<< HEAD
import com.example.rkjc.news_app_2.database.NewsItem;

import java.util.List;

=======
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListener{

    private NewsRecyclerViewAdapter newsAdapter;
    private RecyclerView numberList;
<<<<<<< HEAD
    private NewsItemViewModel sNewsItemViewModel;
=======
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3

    List<NewsItem> list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberList = findViewById(R.id.news_recyclerview);
<<<<<<< HEAD
        newsAdapter = new NewsRecyclerViewAdapter(list_items, this);
=======
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);

        numberList.setHasFixedSize(true);

<<<<<<< HEAD
        sNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);
        sNewsItemViewModel.set(this.getApplication());
        sNewsItemViewModel.getAllNewsItems().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                newsAdapter.list_items = newsItems;
                newsAdapter.notifyDataSetChanged();
                list_items = newsItems;
            }
        });
        numberList.setAdapter(newsAdapter);
=======
        makeNewsFeedGetQuery();

        newsAdapter = new NewsRecyclerViewAdapter(list_items, this);
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
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
<<<<<<< HEAD
                sNewsItemViewModel.syncNews();
=======
                makeNewsFeedGetQuery();
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
        }
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.i("","index >>> "+clickedItemIndex);
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(list_items.get(clickedItemIndex).getUrl())));
    }
<<<<<<< HEAD
=======

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
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
}
