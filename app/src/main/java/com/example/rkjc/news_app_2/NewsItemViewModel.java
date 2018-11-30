package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.example.rkjc.news_app_2.database.NewsItem;
import com.example.rkjc.news_app_2.database.NewsRepository;

import java.util.List;

public class NewsItemViewModel extends ViewModel {

   private NewsRepository sNewsRepository;

   private LiveData<List<NewsItem>> sAllNewsItem;

   public void set(Application application){
        this.sNewsRepository = new NewsRepository(application);
        this.sAllNewsItem = sNewsRepository.getAllNewsItems();
   }

   public LiveData<List<NewsItem>> getAllNewsItems() {return sAllNewsItem;}

   public void syncNews() {sNewsRepository.syncNews();}

}