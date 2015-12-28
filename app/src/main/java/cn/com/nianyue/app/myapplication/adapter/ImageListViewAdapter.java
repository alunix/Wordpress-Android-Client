package cn.com.nianyue.app.myapplication.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.nianyue.app.myapplication.R;
import cn.com.nianyue.app.myapplication.listener.ItemClickListener;
import cn.com.nianyue.app.myapplication.listener.ItemLongClickListener;
import cn.com.nianyue.app.myapplication.model.Post;

/**
 * Created by lu on 2015/12/24.
 */
public class ImageListViewAdapter extends RecyclerView.Adapter<ImageListViewAdapter.ViewHolder> {
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        CardView cv;
        TextView name, age;
        ImageView photo;
        ItemClickListener clickListener;
        ItemLongClickListener longClickListener;

        public ViewHolder(View itemView, ItemClickListener clickListener, ItemLongClickListener longClickListener){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            name = (TextView) itemView.findViewById(R.id.post_title);
            age = (TextView) itemView.findViewById(R.id.post_date);
            photo = (ImageView) itemView.findViewById(R.id.post_thumbnail);

            this.clickListener = clickListener;
            this.longClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null) clickListener.onItemClick(v, getPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            if(longClickListener != null) longClickListener.onItemLongClick(v, getPosition());
            return true;
        }
    }

    private List<Post> posts;
    public ImageListViewAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder vh = new ViewHolder(v, itemClickListener, itemLongClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(posts.get(position).name);
        holder.age.setText(posts.get(position).age);
        //holder.photo.setImageResource(posts.get(position).photoId);
        holder.photo.setImageBitmap(posts.get(position).photo);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setItemLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}
