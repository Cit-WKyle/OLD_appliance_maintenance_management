package com.kyle.ie.manager.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kyle.ie.R;
import com.kyle.ie.auth.persistence.IAuthPersistenceConstants;
import com.kyle.ie.common.persistence.PersistenceHandler;
import com.kyle.ie.property.view.PropertyListActivity;

import java.util.ArrayList;
import java.util.List;

public class PropertyManagerDashboardActivity extends AppCompatActivity {

    private List<String> _viewList;

    private static final String PROPERTIES_VIEW = "PROPERTIES";
    private static final String PENDING_TENANTS = "PENDING_TENANTS";
    private static final String MAINTENANCE_REPORTS = "MAINTENANCE_REPORTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_manager_dashboard);

        PersistenceHandler pHandler = new PersistenceHandler();
        TextView username = (TextView) findViewById(R.id.pm_d_tv_username);
        username.setText(pHandler.getSettings(IAuthPersistenceConstants.AUTH_PREFS_NAME).getString(IAuthPersistenceConstants.USERNAME_KEY, ""));

        _viewList = generateList();
        setupListView();
    }

    private List<String> generateList() {
        List<String> list = new ArrayList<>();
        list.add(PROPERTIES_VIEW);
        list.add(PENDING_TENANTS);
        list.add(MAINTENANCE_REPORTS);

        return list;
    }

    private void setupListView() {
        final ListView listView = (ListView) findViewById(R.id.pm_d_lv_views);
        listView.setAdapter(new PMViewListAdapter(this, _viewList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) (listView.getItemAtPosition(i));

                if(item.equals(PROPERTIES_VIEW)) {
                    Intent propertyListIntent = new Intent(PropertyManagerDashboardActivity.this, PropertyListActivity.class);
                    startActivity(propertyListIntent);
                }
            }
        });
        registerForContextMenu(listView);
    }
}
