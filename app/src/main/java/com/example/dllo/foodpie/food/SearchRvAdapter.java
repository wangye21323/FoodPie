package com.example.dllo.foodpie.food;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.CommonViewHolder;
import com.example.dllo.foodpie.databean.HistoryBean;
import com.example.dllo.foodpie.databean.SearchBean;
import com.example.dllo.foodpie.databean.SearchData;
import com.example.dllo.foodpie.dbtool.DBTool;

import java.util.List;

/**
 * Created by dllo on 16/11/5.
 */
public class SearchRvAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private SearchBean searchBean;
    private List<HistoryBean> historyBeen;//历史记录
    private SearchOnClickListener onClickListener;

    private static final int TYPE_HISTORY = 3;
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_SEARCH_KEY = 1;
    private static final int TYPE_CLEAR_HISTORY = 4;


    //设置历史记录
    public void setHistoryBeen(List<HistoryBean> historyBeen) {
        this.historyBeen = historyBeen;
        notifyDataSetChanged();
    }

    public void setOnClickListener(SearchOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    //设置网上获取的 大家都在搜
    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        notifyDataSetChanged();
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        switch (viewType) {
            case TYPE_TITLE:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_search_title);
                break;
            case TYPE_HISTORY:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_history);
                break;
            case TYPE_CLEAR_HISTORY:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_search_clear_history);
                break;
            case TYPE_SEARCH_KEY:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_search);
                break;
        }

        return commonViewHolder;
    }

    //判断是否有历史记录
    private boolean hasHead() {
        return !(historyBeen == null || historyBeen.size() == 0);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type) {
            case TYPE_TITLE:
                if (position == 0 && hasHead()) {
                    holder.setText(R.id.item_search_title_tv, "最近搜过");
                } else {
                    holder.setText(R.id.item_search_title_tv, "大家都在搜");
                }
                break;
            case TYPE_HISTORY:
                int historyPos = position - 1;//-1 因为上面有个标题 历史记录
                String historyName = historyBeen.get(historyPos).getName();
                holder.setText(R.id.tv_history, historyName);
                holder.setItemClick(new NameClickListener(historyName));
                break;
            case TYPE_CLEAR_HISTORY:
                holder.setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //adapter里的历史记录清空

                        SearchRvAdapter.this.historyBeen = null;
                        notifyDataSetChanged();
                        DBTool.getInstance().deleteSearchData(SearchData.class);

                    }
                });
                //清除历史记录
                break;
            case TYPE_SEARCH_KEY:
                int keyPos = 0;
                if (hasHead()) {
                    keyPos = position - 1 - historyBeen.size() - 1 - 1;
                } else {
                    keyPos = position - 1;
                }
                String name = searchBean.getKeywords().get(keyPos);

                holder.setText(R.id.tv_food_search_name, name);
                holder.setItemClick(new NameClickListener(name));
                break;
        }


    }

    //点击监听
    class NameClickListener implements View.OnClickListener {
        private String name;

        public NameClickListener(String name) {
            this.name = name;
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onClickSearch(name);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasHead()) {
            //有历史记录
            if (position == 0) {
                return TYPE_TITLE;
            } else if (position > 0 && position <= historyBeen.size()) {
                return TYPE_HISTORY;
            } else if (position == historyBeen.size() + 1) {
                return TYPE_CLEAR_HISTORY;//大家都在搜
            } else if (position == historyBeen.size() + 2) {
                return TYPE_TITLE;
            } else {
                return TYPE_SEARCH_KEY;
            }
        } else {
            if (position == 0) {
                return TYPE_TITLE;
            } else {
                return TYPE_SEARCH_KEY;
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        final int spanCount = gridLayoutManager.getSpanCount();

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = getItemViewType(position);
                if (type == TYPE_SEARCH_KEY) {
                    return 1;
                } else {
                    return spanCount;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        int count = 0;
        if (searchBean != null) {
            count += searchBean.getKeywords().size() + 1;
        }
        if (hasHead()) {
            count += historyBeen.size() + 2;//标题 和清空历史记录
        }

        return count;
    }


}
