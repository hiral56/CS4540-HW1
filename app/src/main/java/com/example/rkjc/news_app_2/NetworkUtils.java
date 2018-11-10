package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NetworkUtils {
    final static String base_url = "https://newsapi.org/v1/articles";

    public static Map<String, String> query_parameter;
    static {
        Map<String, String> params = new HashMap<>();

        params.put("source", "the-next-web");
        params.put("sortBy", "latest");
        params.put("apiKey", "9852a10f87bc400285ef98372b5c73a4");

        query_parameter = Collections.unmodifiableMap(params);
    }

    public static URL buildURL(String base_url, Map<String, String> query_parameter){

        if(query_parameter == null || query_parameter.isEmpty())
            return null;

        Uri.Builder builder = Uri.parse(base_url).buildUpon();
        Set<String> keys = query_parameter.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, query_parameter.get(key));
        }

        URL url = null;
        try {
            url = new URL(builder.build().toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }else{
                return null;
            }
        }finally{
            urlConnection.disconnect();
        }
    }
}
