package com.example.omarahmed.msecg;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Omar Ahmed on 12/25/2018.
 */

public class Preference_Settings extends PreferenceFragmentCompat {



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.preferences);
        //setPreferencesFromResource(R.xml.preferences,rootKey);






     /*   CheckBoxPreference power_switcher= (CheckBoxPreference) findPreference("power_switch");
        power_switcher.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.i("power",newValue.toString());
                return false;
            }
        });
        CheckBoxPreference led= (CheckBoxPreference) findPreference("led_switch");
        led.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.i("led",newValue.toString());
                return (boolean)newValue;
            }
        });
        CheckBoxPreference buzzer= (CheckBoxPreference) findPreference("buzzer_switch");
        buzzer.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.i("buzzer",newValue.toString());
                return (boolean)newValue;

            }
        });
        ListPreference listPreference= (ListPreference) findPreference("list_of_modes");
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.i("list",newValue.toString());
                return (boolean)newValue;
            }
        });
*/





    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }





}
