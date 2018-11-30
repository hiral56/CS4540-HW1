package com.example.rkjc.news_app_2.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rkjc.news_app_2.JsonUtils;
import com.example.rkjc.news_app_2.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {

    private static NewsItemDao sNewsItemDao;
    private LiveData<List<NewsItem>> sAllNewsItems;

    public NewsRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        sNewsItemDao = db.newsItemDao();
        sAllNewsItems = sNewsItemDao.loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getAllNewsItems() {
        return sAllNewsItems;
    }

    public static void syncNews() {
        new syncNewsTask(sNewsItemDao).execute();
    }

    private static class syncNewsTask extends AsyncTask<Void, Void, Void> {
        private NewsItemDao sNewsItemDao;

        syncNewsTask(NewsItemDao dao) {
            sNewsItemDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sNewsItemDao.clearAll();

            URL searchUrl = NetworkUtils.buildURL(NetworkUtils.base_url, NetworkUtils.query_parameter);

            String results = null;

            try {
                results = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<NewsItem> newsItems = JsonUtils.parseNews(results);
            sNewsItemDao.insert(newsItems);

            return null;
        }
    }

}
