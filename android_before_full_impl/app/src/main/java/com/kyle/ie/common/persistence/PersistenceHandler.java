package com.kyle.ie.common.persistence;

import android.app.Activity;
import android.content.SharedPreferences;

import com.kyle.ie.MainActivity;

/**
 * Created by Kyle on 31/01/2017.
 */
public class PersistenceHandler implements IPersistenceHandler {

    private Activity _activity;
    private SharedPreferences.Editor _editor;

    public PersistenceHandler() {
        _activity = MainActivity.getInstance();
    }

    @Override
    public SharedPreferences getSettings(String prefsName) {
        return _activity.getSharedPreferences(prefsName, 0);
    }

    @Override
    public SharedPreferences.Editor getEditor(String prefsName) {
        return _editor = getSettings(prefsName).edit();
    }

    @Override
    public void commit() {
        if(_editor != null) {
            _editor.commit();
        }
    }
}
