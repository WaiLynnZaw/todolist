package com.wlz.getshitdone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlz.getshitdone.R;
import com.wlz.getshitdone.model.Notes;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WaiLynnZaw on 4/18/15.
 */
public class ListViewAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Notes> al;

    public ListViewAdapter(Context ctx,ArrayList<Notes>al){
        this.ctx = ctx;
        this.al = al;
    }
    @Override
    public int getCount() {
        return al.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder vh;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item,viewGroup,false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.list_notes.setText(al.get(i).title);

        return convertView;
    }

    static class ViewHolder{
        @InjectView(R.id.list_notes)
        TextView list_notes;
        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}
