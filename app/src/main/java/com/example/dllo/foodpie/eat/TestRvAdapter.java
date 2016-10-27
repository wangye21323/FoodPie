package com.example.dllo.foodpie.eat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.example.dllo.foodpie.databean.TestBean;

/**
 * Created by dllo on 16/10/26.
 */
public class TestRvAdapter extends RecyclerView.Adapter<TestRvAdapter.MyViewHolder>{
    private TestBean testBean;
    private Context context;
    private MyViewHolder viewHolder;

    public TestRvAdapter(Context context) {
        this.context = context;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eat_item_test, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.source.setText(testBean.getFeeds().get(position).getSource());
        holder.tail.setText(testBean.getFeeds().get(position).getTail());
        holder.title.setText(testBean.getFeeds().get(position).getTitle());

        String imgUrlBackground = testBean.getFeeds().get(position).getBackground();
        VolleySingleton.getInstance().getImage(imgUrlBackground, holder.background);

    }

    @Override
    public int getItemCount() {
        return testBean.getFeeds() == null ? 0 : testBean.getFeeds().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView background;
        private final TextView source;
        private final TextView title;
        private final TextView tail;

        public MyViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.img_eat_test_background);
            source = (TextView) itemView.findViewById(R.id.tv_eat_test_source);
            title = (TextView) itemView.findViewById(R.id.tv_eat_test_title);
            tail = (TextView) itemView.findViewById(R.id.tv_eat_test_tail);
        }
    }
}
