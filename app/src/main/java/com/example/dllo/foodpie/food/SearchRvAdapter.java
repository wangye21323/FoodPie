package com.example.dllo.foodpie.food;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.SearchBean;

/**
 * Created by dllo on 16/11/5.
 */
public class SearchRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SearchBean searchBean;
    private Context context;
    private MySearchViewHolder viewHolder;
    private SearchOnClickListener onClickListener;

    private static final int TYPE_FIRST = 0;
    private static final int TYPE_OTHER = 1;

    public void setOnClickListener(SearchOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        notifyDataSetChanged();
    }

    public SearchRvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_OTHER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
            viewHolder = new MySearchViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_all, parent, false);
            RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view) {
            };
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_OTHER) {
            final int pos = position - 1;
            MySearchViewHolder viewHolder = (MySearchViewHolder) holder;
            viewHolder.name.setText(searchBean.getKeywords().get(pos));
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickSearch(searchBean.getKeywords().get(pos));
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        return position == 0 ? TYPE_FIRST : TYPE_OTHER;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Log.d("zzz", "----------");
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();


        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Log.d("zzz", "position:" + position);
                if (position == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (searchBean == null) {
            return 0;
        }
        return searchBean.getKeywords() == null ? 1 : searchBean.getKeywords().size() + 1;
    }

    public class MySearchViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private LinearLayout ll;

        public MySearchViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_food_search_name);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_food_search);
        }
    }
}
