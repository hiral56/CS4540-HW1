package com.example.rkjc.news_app_2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@SuppressWarnings("unchecked")
@Dao
public interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY id")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Insert
    void insert(List<NewsItem> items);

    @Query("DELETE FROM news_item")
    void clearAll();
}
