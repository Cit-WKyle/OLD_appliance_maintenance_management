package com.kyle.ie.manager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kyle.ie.R;

import java.util.List;

/**
 * Created by kyle_williamson on 11/02/2017.
 */

public class PMViewListAdapter extends ArrayAdapter<String> {
    public PMViewListAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dashboard_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.db_item_tv_name);
        itemName.setText(item);

        return convertView;
    }
}
