package com.example.dllo.foodpie.eat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.HomePageBean;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class HomePageRvAdapter extends RecyclerView.Adapter<HomePageRvAdapter.MyViewHolder>{
    private HomePageBean arrayList;
    private Context context;
    private MyViewHolder viewHolder;
    private OnClickItem onClickItem;

    public HomePageRvAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public void setArrayList(HomePageBean arrayList) {
        this.arrayList = arrayList;
        notifyItemChanged(this.arrayList.getFeeds().size());
    }

    public void addBeanData(HomePageBean arrayList){
        this.arrayList.addData(arrayList.getFeeds());
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.eat_item_homepage, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.publisher.setText(arrayList.getFeeds().get(position).getPublisher());
        holder.title.setText(arrayList.getFeeds().get(position).getTitle());
        holder.like.setText(String.valueOf(arrayList.getFeeds().get(position).getLike_ct()));
        holder.desciption.setText(arrayList.getFeeds().get(position).getDescription());

        String imgUrlCard = arrayList.getFeeds().get(position).getCard_image();
        VolleySingleton.getInstance().getImage(imgUrlCard, holder.card);
        String imgUrlAvatar = arrayList.getFeeds().get(position).getPublisher_avatar();
        VolleySingleton.getInstance().getImage(imgUrlAvatar, holder.avatar);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(arrayList.getFeeds().get(position).getCard_image());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.getFeeds() == null ? 0 : arrayList.getFeeds().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView card;
        private TextView title;
        private ImageView avatar;
        private TextView publisher;
        private TextView like;
        private TextView desciption;
        private RelativeLayout rl;

        public MyViewHolder(View itemView) {
            super(itemView);
            card = (ImageView) itemView.findViewById(R.id.img_eat_homepage);
            title = (TextView) itemView.findViewById(R.id.tv_eat_homepage_title);
            avatar = (ImageView) itemView.findViewById(R.id.img_eat_homepage_avatar);
            publisher = (TextView) itemView.findViewById(R.id.tv_eat_homepage_publisher);
            like = (TextView) itemView.findViewById(R.id.tv_eat_homepage_like);
            desciption = (TextView) itemView.findViewById(R.id.tv_eat_homepage_description);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_eat_homepage);
        }
    }
}
