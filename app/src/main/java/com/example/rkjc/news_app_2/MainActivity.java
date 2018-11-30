package com.example.rkjc.news_app_2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;

import com.example.rkjc.news_app_2.database.NewsItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsRecyclerViewAdapter.ListItemClickListener{

    private NewsRecyclerViewAdapter newsAdapter;
    private RecyclerView numberList;
    private NewsItemViewModel sNewsItemViewModel;

    List<NewsItem> list_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberList = findViewById(R.id.news_recyclerview);
        newsAdapter = new NewsRecyclerViewAdapter(list_items, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);

        numberList.setHasFixedSize(true);

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
                sNewsItemViewModel.syncNews();
        }
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.i("","index >>> "+clickedItemIndex);
        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(list_items.get(clickedItemIndex).getUrl())));
    }
}
