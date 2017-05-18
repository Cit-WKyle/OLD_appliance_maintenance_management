package com.kyle.ie.common.persistence;

import android.content.SharedPreferences;

/**
 * Created by Kyle on 21/01/2017.
 */
public interface IPersistenceHandler {

    SharedPreferences getSettings(String prefsName);

    SharedPreferences.Editor getEditor(String prefsName);

    void commit();
}
