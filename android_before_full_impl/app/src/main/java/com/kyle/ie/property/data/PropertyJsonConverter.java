package com.kyle.ie.property.data;

import com.kyle.ie.common.data.IJSONConverter;
import com.kyle.ie.property.model.Address;
import com.kyle.ie.property.model.Property;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kyle_williamson on 13/02/2017.
 */

public class PropertyJsonConverter implements IJSONConverter<Property> {

    private static final String ID_KEY = "id";
    private static final String ADDR_KEY = "address";
    private static final String AGE_KEY = "age";
    private static final String BEDCOUNT_KEY ="bedCount";
    private static final String BATHCOUNT_KEY = "bathroomCount";
    private static final String MNGER_ID_KEY = "managerId";

    private AddressJsonConverter _addrConv;

    public PropertyJsonConverter() {
        _addrConv = new AddressJsonConverter();
    }

    @Override
    public Property convertJSON(JSONObject json) throws JSONException {
        Property p = new Property();
        p.setId(json.getString(ID_KEY));
        p.setAge(json.getInt(AGE_KEY));
        p.setBedCount(json.getInt(BEDCOUNT_KEY));
        p.setBathroomCount(json.getInt(BATHCOUNT_KEY));
        p.setManagerId(json.getInt(MNGER_ID_KEY));
        p.setAddress(_addrConv.convertJSON(json.getJSONObject(ADDR_KEY)));
        return p;
    }

    class AddressJsonConverter implements IJSONConverter<Address> {

        private static final String LINE1_KEY = "addressLine1";
        private static final String LINE2_KEY = "addressLine2";
        private static final String CITY_KEY = "city";
        private static final String STATE_KEY = "state";
        private static final String COUNTRY_KEY = "country";
        private static final String POSTALCODE_KEY = "postalCode";
        private static final String LONG_KEY = "longitude";
        private static final String LAT_KEY = "latitude";

        @Override
        public Address convertJSON(JSONObject json) throws JSONException {
            Address addr = new Address();
            addr.setAddressLine1(json.getString(LINE1_KEY));
            addr.setAddressLine2(json.getString(LINE2_KEY));
            addr.setCity(json.getString(CITY_KEY));
            addr.setState(json.getString(STATE_KEY));
            addr.setCountry(json.getString(COUNTRY_KEY));
            addr.setPostalCode(json.getString(POSTALCODE_KEY));
            addr.setLongitude(json.getInt(LONG_KEY));
            addr.setLatitude(json.getInt(LAT_KEY));
            return addr;
        }
    }
}
