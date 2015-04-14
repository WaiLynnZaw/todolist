package com.wlz.getshitdone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlz.getshitdone.R;
import com.wlz.getshitdone.model.Diary;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 4/14/15.
 */
public class GridViewAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Diary>al;
    public GridViewAdapter(Context ctx, ArrayList<Diary>al){
        this.ctx = ctx;
        this.al = al;
    }
    @Override
    public int getCount() {
        return al == null? 0 : al.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_item,parent,false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else{
            vh = (ViewHolder)convertView.getTag();
        }

        vh.title.setText(al.get(position).title);
        vh.desc.setText(al.get(position).content);

        return convertView;
    }

    static class ViewHolder{
        @InjectView(R.id.diary_image)
        ImageView diary_image;
        @InjectView(R.id.txt_title)
        TextView title;
        @InjectView(R.id.txt_desc)
        TextView desc;

        public ViewHolder(View view){
            ButterKnife.inject(this,view);
        }
    }
}
