package com.kyle.ie.property.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kyle.ie.R;
import com.kyle.ie.auth.model.JwtToken;
import com.kyle.ie.auth.persistence.IAuthPersistenceConstants;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.controller.ControllerFactory;
import com.kyle.ie.common.persistence.IPersistenceHandler;
import com.kyle.ie.common.persistence.PersistenceHandler;
import com.kyle.ie.property.controller.IPropertyController;
import com.kyle.ie.property.model.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyListActivity extends AppCompatActivity {

    private List<Property> _propertyList;

    private IPropertyController _propertyController;

    private PropertyListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        if(_propertyList == null) {
            _propertyList = new ArrayList<>();
        }
        setupListView();

        _propertyController = ControllerFactory.getInstance().createPropertyController();

        IPersistenceHandler persHandler = new PersistenceHandler();
        String token = persHandler.getSettings(IAuthPersistenceConstants.AUTH_PREFS_NAME).getString(IAuthPersistenceConstants.JWT_TOKEN_KEY, "");

        _propertyController.getPropertiesForManager(new JwtToken(token), new ICallback<List<String>>() {
            @Override
            public void callback(Payload<List<String>> payload) {
                _propertyList = _propertyController.getDAO().getAll();
                _adapter.clear();
                _adapter.addAll(_propertyList);
                _adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return false;
    }

    private void setupListView() {
        final ListView listView = (ListView) findViewById(R.id.propertylist_lv_properties);
        listView.setAdapter(_adapter = new PropertyListAdapter(this, _propertyList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Property item = (Property) listView.getItemAtPosition(i);
                Intent intent = new Intent(PropertyListActivity.this, PropertyActivity.class);
                intent.putExtra(IPropertyConstants.ID_KEY, item.getId());
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
    }
}
