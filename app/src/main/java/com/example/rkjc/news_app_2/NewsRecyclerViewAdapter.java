package com.example.rkjc.news_app_2;

import android.content.Context;
<<<<<<< HEAD
=======
import android.content.Intent;
import android.net.Uri;
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD
import com.example.rkjc.news_app_2.database.NewsItem;

=======
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {

    public static final String TAG = NewsRecyclerViewAdapter.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    private int nNumberItems;

<<<<<<< HEAD
    public List<NewsItem> list_items;
=======
    private List<NewsItem> list_items;
>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public NewsRecyclerViewAdapter(List<NewsItem> items, ListItemClickListener listener) {
        nNumberItems = 100;
        list_items = items;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public void updateList(ArrayList<NewsItem> items){
        list_items = items;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);

<<<<<<< HEAD
        if(!list_items.isEmpty()) {
            viewHolder.newsTextTitle.setText("Title: " + list_items.get(viewHolderCount).getTitle());
            viewHolder.newsTextDescription.setText("Description: " + list_items.get(viewHolderCount).getDescription());
            viewHolder.newsTextTime.setText("Time: " + String.valueOf(list_items.get(viewHolderCount).getPublishedAt()));
        }
=======
        viewHolder.newsTextTitle.setText("Title: "+list_items.get(viewHolderCount).getTitle());
        viewHolder.newsTextDescription.setText("Description: "+list_items.get(viewHolderCount).getDescription());
        viewHolder.newsTextTime.setText("Time: "+String.valueOf(list_items.get(viewHolderCount).getPublishedAt()));

>>>>>>> 1b5044fa0cc288143e9761e8f3b6a36153b9a5a3
        viewHolderCount++;

        Log.i(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return nNumberItems;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView newsTextTitle;
        TextView newsTextDescription;
        TextView newsTextTime;

        public NewsItemViewHolder(View itemView) {
            super(itemView);

            newsTextTitle = itemView.findViewById(R.id.newsTextTitle);
            newsTextDescription = itemView.findViewById(R.id.newsTextDescription);
            newsTextTime = itemView.findViewById(R.id.newsTextTime);

            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
