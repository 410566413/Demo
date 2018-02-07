package com.hawker.yangtianqi.demo.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawker.yangtianqi.demo.R;
import com.hawker.yangtianqi.demo.model.Beauty;

/**
 * Created by yangtianqi on 2018/2/4.
 */
public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.ViewHolder> {
    private String[] captions;
    private int[] imageIds;
    public BeautyAdapter(String[] captions,int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    @Override
    public BeautyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       CardView cv = (CardView)LayoutInflater.from(parent.getContext())
               .inflate(R.layout.layout_beauty,parent,false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(BeautyAdapter.ViewHolder holder, int position) {

        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.beauty_image);
        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.beauty_text);
        textView.setText(captions[position]);
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }
}
