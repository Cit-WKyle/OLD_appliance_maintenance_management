package com.kyle.ie.property.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.kyle.ie.R;
import com.kyle.ie.common.callback.ICallback;
import com.kyle.ie.common.callback.Payload;
import com.kyle.ie.common.controller.ControllerFactory;
import com.kyle.ie.property.model.Property;
import com.kyle.ie.property_appliance.controller.IPropertyApplianceController;
import com.kyle.ie.property_appliance.model.PropertyAppliance;
import com.kyle.ie.property_appliance.view.PropertyApplianceListAdapter;

import java.util.ArrayList;
import java.util.List;

public class  PropertyActivity extends AppCompatActivity {

    private IPropertyApplianceController _propApplController;

    private Property _property;
    private List<PropertyAppliance> _propApplList;

    private PropertyApplianceListAdapter _propApplAdapter;

    private static final String COMMA = ",";

    private ListView _propApplLV;

    private TextView _idTV;
    private TextView _ageTV;
    private TextView _bedCntTV;
    private TextView _bathCntTV;

    private TextView _line1TV;
    private TextView _line2TV;
    private TextView _cityTV;
    private TextView _stateTV;
    private TextView _countryTV;
    private TextView _pCodeTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        if(_propApplList == null) {
            _propApplList = new ArrayList<>();
        }

        _propApplController = ControllerFactory.getInstance().createPropertyApplianceController();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String propertyId = extras.getString(IPropertyConstants.ID_KEY);
            _property = ControllerFactory.getInstance().createPropertyController().getDAO().getForId(propertyId);
        }

        _idTV = (TextView) findViewById(R.id.property_tv_idvalue);
        _ageTV = (TextView) findViewById(R.id.property_tv_agevalue);
        _bedCntTV = (TextView) findViewById(R.id.property_tv_bedcntvalue);
        _bathCntTV = (TextView) findViewById(R.id.property_tv_bathcntval);
        _line1TV  = (TextView) findViewById(R.id.property_tv_addr_line1);
        _line2TV = (TextView) findViewById(R.id.property_tv_addr_line2);
        _cityTV = (TextView) findViewById(R.id.property_tv_addr_city);
        _stateTV = (TextView) findViewById(R.id.property_tv_addr_state);
        _countryTV = (TextView) findViewById(R.id.property_tv_addr_country);
        _pCodeTV = (TextView) findViewById(R.id.property_tv_addr_pcode);
        _propApplLV = (ListView) findViewById(R.id.property_lv_appl);

        _idTV.setText(_property.getId());
        _ageTV.setText(String.valueOf(_property.getAge()));
        _bedCntTV.setText(String.valueOf(_property.getBedCount()));
        _bathCntTV.setText(String.valueOf(_property.getBathroomCount()));
        _line1TV.setText(_property.getAddress().getAddressLine1() + COMMA);
        _line2TV.setText(_property.getAddress().getAddressLine2()+ COMMA);
        _cityTV.setText(_property.getAddress().getCity()+ COMMA);
        _stateTV.setText(_property.getAddress().getState()+ COMMA);
        _countryTV.setText(_property.getAddress().getCountry()+ COMMA);
        _pCodeTV.setText(_property.getAddress().getPostalCode());

        _propApplLV.setAdapter(_propApplAdapter = new PropertyApplianceListAdapter(this, _propApplList));

        _propApplController.getProperyAppliancesForProperty(_property, new ICallback<List<String>>() {
            @Override
            public void callback(Payload<List<String>> payload) {
                _propApplList = _propApplController.getDAO().getAll();
                _propApplAdapter.clear();
                _propApplAdapter.addAll(_propApplList);
                _propApplAdapter.notifyDataSetChanged();
            }
        });
    }
}
