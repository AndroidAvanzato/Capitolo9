package it.androidavanzato.daggerandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class MyAdapter extends BaseAdapter {
    @Inject LayoutInflater layoutInflater;

    @Inject Provider<MyViewHolder> viewHolderProvider;

    private final List<String> items;

    @Inject public MyAdapter(ItemsService itemsService) {
        items = itemsService.loadItems();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = viewHolderProvider.get();
            viewHolder.init((TextView) convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }

        viewHolder.populate(getItem(position));

        return convertView;
    }

    @Override public int getCount() {
        return items.size();
    }

    @Override public String getItem(int position) {
        return items.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

}
