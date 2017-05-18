package com.kyle.ie.property.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kyle.ie.R;
import com.kyle.ie.property.model.Property;

import java.util.List;

/**
 * Created by kyle_williamson on 12/02/2017.
 */

public class PropertyListAdapter extends ArrayAdapter<Property> {
    public PropertyListAdapter(Context context, List<Property> objects) {
        super(context, 0, objects);
    }

    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        Property property = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.property_list_item, parent, false);
        }

        TextView propName = (TextView) convertView.findViewById(R.id.plist_item_tv_name);
        propName.setText(property.getAddress().getAddressLine1());
        return convertView;
    }
}
