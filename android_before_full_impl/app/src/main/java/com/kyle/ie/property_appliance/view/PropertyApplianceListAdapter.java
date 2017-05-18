package com.kyle.ie.property_appliance.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kyle.ie.R;
import com.kyle.ie.property_appliance.model.PropertyAppliance;

import java.util.List;

/**
 * Created by kyle_williamson on 18/02/2017.
 */

public class PropertyApplianceListAdapter extends ArrayAdapter<PropertyAppliance> {
    public PropertyApplianceListAdapter(Context context, List<PropertyAppliance> objects) {
        super(context, 0, objects);
    }


    @Override
    public  View getView(int position, View convertView, ViewGroup parent) {
        PropertyAppliance propertyAppliance = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.property_appliance_list_item, parent, false);
        }

        TextView propName = (TextView) convertView.findViewById(R.id.p_appl_tv_name);
        propName.setText(propertyAppliance.getName());
        return convertView;
    }
}
