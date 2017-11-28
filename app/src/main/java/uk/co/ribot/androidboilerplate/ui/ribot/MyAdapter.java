package uk.co.ribot.androidboilerplate.ui.ribot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.injection.ApplicationContext;

/**
 * Created by wangweijun on 2017/11/28.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    List<Contributor> list;
    Context context;

    @Inject
    public MyAdapter(@ApplicationContext Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void addContributors(List<Contributor> newList) {
        list.addAll(list.size(), newList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot2, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contributor contributor = list.get(position);
        holder.nameTextView.setText(contributor.login);
        Glide.with(context).load(contributor.avatar_url).into(holder.avatarView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_hex_color) ImageView avatarView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
