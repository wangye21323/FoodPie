package com.example.dllo.foodpie.eat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.Web.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class HomePageRvAdapter extends RecyclerView.Adapter<HomePageRvAdapter.MyViewHolder>{
    private HomePageBean arrayList;
    private Context context;
    private MyViewHolder viewHolder;

    public HomePageRvAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(HomePageBean arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eat_item_homepage, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.publisher.setText(arrayList.getFeeds().get(position).getPublisher());
        holder.title.setText(arrayList.getFeeds().get(position).getTitle());

        // Like_ct 是个int形的数据  你的like只能放String型的数据  你可以把int型转换成String型  代码百度上有的是
        holder.like.setText(String.valueOf(arrayList.getFeeds().get(position).getLike_ct()));

        String imgUrl = arrayList.getFeeds().get(position).getCard_image();
        VolleySingleton.getInstance().getImage(imgUrl, holder.card);

    }

    @Override
    public int getItemCount() {
        return arrayList.getFeeds().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView card;
        private final TextView title;
        private final ImageView avatar;
        private final TextView publisher;
        private final TextView like;

        public MyViewHolder(View itemView) {
            super(itemView);
            card = (ImageView) itemView.findViewById(R.id.img_eat_homepage);
            title = (TextView) itemView.findViewById(R.id.tv_eat_homepage_title);
            avatar = (ImageView) itemView.findViewById(R.id.img_eat_homepage_avatar);
            publisher = (TextView) itemView.findViewById(R.id.tv_eat_homepage_publisher);
            like = (TextView) itemView.findViewById(R.id.tv_eat_homepage_like);
        }
    }
}
