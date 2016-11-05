package com.example.dllo.foodpie.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.MySearchViewHolder>{
    private SearchBean searchBean;
    private Context context;
    private MySearchViewHolder viewHolder;
    private SearchOnClickListener onClickListener;

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
    public MySearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        viewHolder = new MySearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MySearchViewHolder holder, final int position) {
        holder.name.setText(searchBean.getKeywords().get(position));
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickSearch(searchBean.getKeywords().get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchBean.getKeywords() == null ? 0 : searchBean.getKeywords().size();
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
