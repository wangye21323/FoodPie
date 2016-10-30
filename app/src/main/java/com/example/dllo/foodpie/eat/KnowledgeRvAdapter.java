package com.example.dllo.foodpie.eat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.KnowledgeBean;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class KnowledgeRvAdapter extends RecyclerView.Adapter<KnowledgeRvAdapter.MyViewHolder>{
    private KnowledgeBean knowledgeBean;
    private Context context;

    public KnowledgeRvAdapter(Context context) {
        this.context = context;
    }

    public void setKnowledgeBean(KnowledgeBean knowledgeBean) {
        this.knowledgeBean = knowledgeBean;
        notifyDataSetChanged();
    }

    public void addBeanData(KnowledgeBean knowledgeBean){
        this.knowledgeBean.addData(knowledgeBean.getFeeds());
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eat_item_knowledge, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.source.setText(knowledgeBean.getFeeds().get(position).getSource());
        holder.tail.setText(knowledgeBean.getFeeds().get(position).getTail());
        holder.title.setText(knowledgeBean.getFeeds().get(position).getTitle());
        String imgUrlCard = knowledgeBean.getFeeds().get(position).getImages().get(0);
        VolleySingleton.getInstance().getImage(imgUrlCard, holder.images);
    }

    @Override
    public int getItemCount() {
        return knowledgeBean.getFeeds() == null ? 0 : knowledgeBean.getFeeds().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView source;
        private TextView title;
        private TextView tail;
        private ImageView images;

        public MyViewHolder(View itemView) {
            super(itemView);
            source = (TextView) itemView.findViewById(R.id.tv_eat_knowledge_source);
            title = (TextView) itemView.findViewById(R.id.tv_eat_knowledge_title);
            tail = (TextView) itemView.findViewById(R.id.tv_eat_knowledge_tail);
            images = (ImageView) itemView.findViewById(R.id.img_eat_knowledge_images);
        }
    }
}
