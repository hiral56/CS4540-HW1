package com.example.rkjc.news_app_2;

<<<<<<< HEAD
import com.example.rkjc.news_app_2.database.NewsItem;
=======
import android.util.Log;
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {

    public static ArrayList<NewsItem> parseNews(String result){

        ArrayList<NewsItem> result_list = new ArrayList<>();

        try{
            JSONObject response = new JSONObject(result);

            JSONArray articles = response.getJSONArray("articles");

            for(int i=0; i<articles.length(); i++){

                JSONObject obj = articles.getJSONObject(i);

                NewsItem n = new NewsItem();
                n.setAuthor(obj.getString("author"));
                n.setTitle(obj.getString("title"));
                n.setDescription(obj.getString("description"));
                n.setUrl(obj.getString("url"));
                n.setUrlToImage(obj.getString("urlToImage"));
                n.setPublishedAt(obj.getString("publishedAt"));

                result_list.add(n);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result_list;
    }
}


