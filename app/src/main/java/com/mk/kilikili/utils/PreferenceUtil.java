package com.mk.kilikili.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.mk.kilikili.App;

import static com.mk.skinloader.utils.PreferencesUtils.PREFERENCE_NAME;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * SP缓存工具类
 */
public final class PreferenceUtil {

  public static void reset(final Context context) {

    Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
    edit.clear();
    edit.apply();
  }


  public static String getString(String key, String defValue) {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance())
        .getString(key, defValue);
  }


  public static long getLong(String key, long defValue) {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance())
        .getLong(key, defValue);
  }


  public static float getFloat(String key, float defValue) {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance())
        .getFloat(key, defValue);
  }


  public static void put(String key, String value) {

    putString(key, value);
  }


  public static void put(String key, int value) {

    putInt(key, value);
  }


  public static void put(String key, float value) {

    putFloat(key, value);
  }


  public static void put(String key, boolean value) {

    putBoolean(key, value);
  }


  private static void putFloat(String key, float value) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    Editor editor = sharedPreferences.edit();
    editor.putFloat(key, value);
    editor.apply();
  }


  public static SharedPreferences getPreferences() {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance());
  }


  public static int getInt(String key, int defValue) {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance())
        .getInt(key, defValue);
  }


  public static boolean getBoolean(String key, boolean defValue) {

    return PreferenceManager.getDefaultSharedPreferences(App.getInstance())
        .getBoolean(key, defValue);
  }


  public static void putStringProcess(String key, String value) {

    SharedPreferences sharedPreferences = App.getInstance()
        .getSharedPreferences("preference_mu", Context.MODE_MULTI_PROCESS);
    Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.apply();
  }


  public static String getStringProcess(String key, String defValue) {

    SharedPreferences sharedPreferences = App.getInstance()
        .getSharedPreferences("preference_mu", Context.MODE_MULTI_PROCESS);
    return sharedPreferences.getString(key, defValue);
  }


  public static boolean hasString(String key) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    return sharedPreferences.contains(key);
  }


  private static void putString(String key, String value) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.apply();
  }


  public static void putLong(String key, long value) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    Editor editor = sharedPreferences.edit();
    editor.putLong(key, value);
    editor.apply();
  }


  public static void putBoolean(String key, boolean value) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    Editor editor = sharedPreferences.edit();
    editor.putBoolean(key, value);
    editor.apply();
  }


  private static void putInt(String key, int value) {

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            App.getInstance());
    Editor editor = sharedPreferences.edit();
    editor.putInt(key, value);
    editor.apply();
  }


  public static void remove(String... keys) {

    if (keys != null) {
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
              App.getInstance());
      Editor editor = sharedPreferences.edit();
      for (String key : keys) {
        editor.remove(key);
      }
      editor.apply();
    }
  }


  /*****************************skinchange**********************/
  /**
   * put int preferences
   *
   * @param context
   * @param key The name of the preference to modify
   * @param value The new value for the preference
   * @return True if the new values were successfully written to persistent storage.
   */
  public static boolean putInt(Context context, String key, int value) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();
    editor.putInt(key, value);
    return editor.commit();
  }

  /**
   * get int preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
   *         name that is not a int
   * @see #getInt(Context, String, int)
   */
  public static int getInt(Context context, String key) {
    return getInt(context, key, -1);
  }

  /**
   * get int preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @param defaultValue Value to return if this preference does not exist
   * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
   *         this name that is not a int
   */
  public static int getInt(Context context, String key, int defaultValue) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    return settings.getInt(key, defaultValue);
  }

  /**
   * put long preferences
   *
   * @param context
   * @param key The name of the preference to modify
   * @param value The new value for the preference
   * @return True if the new values were successfully written to persistent storage.
   */
  public static boolean putLong(Context context, String key, long value) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();
    editor.putLong(key, value);
    return editor.commit();
  }

  /**
   * get long preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
   *         name that is not a long
   * @see #getLong(Context, String, long)
   */
  public static long getLong(Context context, String key) {
    return getLong(context, key, -1);
  }

  /**
   * get long preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @param defaultValue Value to return if this preference does not exist
   * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
   *         this name that is not a long
   */
  public static long getLong(Context context, String key, long defaultValue) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    return settings.getLong(key, defaultValue);
  }

  /**
   * put float preferences
   *
   * @param context
   * @param key The name of the preference to modify
   * @param value The new value for the preference
   * @return True if the new values were successfully written to persistent storage.
   */
  public static boolean putFloat(Context context, String key, float value) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();
    editor.putFloat(key, value);
    return editor.commit();
  }

  /**
   * get float preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
   *         name that is not a float
   * @see #getFloat(Context, String, float)
   */
  public static float getFloat(Context context, String key) {
    return getFloat(context, key, -1);
  }

  /**
   * get float preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @param defaultValue Value to return if this preference does not exist
   * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
   *         this name that is not a float
   */
  public static float getFloat(Context context, String key, float defaultValue) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    return settings.getFloat(key, defaultValue);
  }

  /**
   * put boolean preferences
   *
   * @param context
   * @param key The name of the preference to modify
   * @param value The new value for the preference
   * @return True if the new values were successfully written to persistent storage.
   */
  public static boolean putBoolean(Context context, String key, boolean value) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = settings.edit();
    editor.putBoolean(key, value);
    return editor.commit();
  }

  /**
   * get boolean preferences, default is false
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
   *         name that is not a boolean
   * @see #getBoolean(Context, String, boolean)
   */
  public static boolean getBoolean(Context context, String key) {
    return getBoolean(context, key, false);
  }

  /**
   * get boolean preferences
   *
   * @param context
   * @param key The name of the preference to retrieve
   * @param defaultValue Value to return if this preference does not exist
   * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
   *         this name that is not a boolean
   */
  public static boolean getBoolean(Context context, String key, boolean defaultValue) {
    SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    return settings.getBoolean(key, defaultValue);
  }
}
