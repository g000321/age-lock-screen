package com.gym.agelockscan.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gym.APP;

/**
 * 配置信息保存文件
 */
public abstract class ConfigSharedPreferences {
	/**
	 * 存放基本信息的文件名
	 */
	public static final String CONFIG_SETTING = "ConfigSetting";

	public static final String BirthDay="BirthDay";
	public static final String bgColor="bgColor";
	public static final String textColor="textColor";
	public static final String swAll="swAll";


	
	private static ConfigSharedPreferences instance = null;

	public static ConfigSharedPreferences getInstance() {
		if (instance == null) {
			instance = new ConfigSharedPreferences() {

				@Override
				public String getFilename() {
					// TODO Auto-generated method stub
					return CONFIG_SETTING;
				}
			};
		}
		return instance;
	}

	public abstract String getFilename();

	public void set(String key, String value) {
		set(key, value, getFilename());
	}

	public void set(String key, String value, String filename) {
		if (key == null)
			return;

		if (value == null) {
			value = "";
		}

		SharedPreferences pref = APP.getInstance().getSharedPreferences(
				filename, android.content.Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String get(String key) {
		return get(key, getFilename());
	}
	public void set(String key, boolean value, String filename) {
		if (key == null)
			return;

		SharedPreferences pref = APP.getInstance().getSharedPreferences(
				filename, android.content.Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, getFilename());
	}
	public String get(String key, String filename) {
		if (key == null)
			return "";
		SharedPreferences pref = APP.getInstance().getSharedPreferences(
				filename, android.content.Context.MODE_PRIVATE);
		return pref.getString(key, "");
	}
	public boolean getBoolean(String key, String filename) {
		if (key == null)
			return false;
		SharedPreferences pref = APP.getInstance().getSharedPreferences(
				filename, android.content.Context.MODE_PRIVATE);
		return pref.getBoolean(key,false);
	}
	public void setBirthDay(String value) {
		set(BirthDay, value, CONFIG_SETTING);
	}

	public String getBirthDay() {
		return get(BirthDay, CONFIG_SETTING);
	}

	public void setbgColor(String value) {
		set(bgColor, value, CONFIG_SETTING);
	}

	public String getbgColor() {
		return get(bgColor, CONFIG_SETTING);
	}
	public void settextColor(String value) {
        set(textColor, value, CONFIG_SETTING);
    }

    public String gettextColor() {
        return get(textColor, CONFIG_SETTING);
    }
	public void setSwAll(boolean value) {
		set(swAll, value, CONFIG_SETTING);
	}

	public boolean getSwAll() {
		return getBoolean(swAll, CONFIG_SETTING);
	}
}
