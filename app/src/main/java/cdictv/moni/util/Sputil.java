package cdictv.moni.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cdictv.moni.App;

public class Sputil {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences getSharedPreferences(){
        if(mSharedPreferences==null){
            mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(App.instance);
        }
        return mSharedPreferences;
    }
    public static void putString(String key,String value){
        getSharedPreferences().edit().putString(key,value).apply();
    }
    public static void putBoolean(String key,Boolean value){
        getSharedPreferences().edit().putBoolean(key,value).apply();
    }
    public static void revome(String key){
        getSharedPreferences().edit().remove(key).apply();
    }
    public static String getString(String key){
        return getSharedPreferences().getString(key,null);
    }
   public static Boolean getBoolean(String key){
        return getSharedPreferences().getBoolean(key,false);
    }
}
