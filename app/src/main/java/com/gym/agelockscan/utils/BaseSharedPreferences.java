package com.gym.agelockscan.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.gym.APP;


public abstract class BaseSharedPreferences {
	/**
	 * 存放基本信息的文件名
	 */
	public static final String GENERAL_SETTING = "GeneralSetting";
	/**
     * 版本号
     */
    public static final String VEISION = "Version";
	/**
	 * 登陆界面 用于 记住账户
	 */
	public static final String PHONE_NUM = "phoneNum";
	/**
	 * 登陆界面用于 记住密码
	 */
	public static final String PWD = "passWord";
	/**
	 * 记住 自动登陆按钮
	 */
	public static final String autoLogin = "autoLogin";
	/**
	 * 记住 记住密码按钮
	 */
	public static final String remandPwd = "remandPwd";
	/**
	 * 记住该设备的签名
	 */
	public static final String SIGN = "sign";//token
	public static final String USERID="usersid";
	/**
	 * 记住该设备的时间与服务器的时间差 每次启动app校准
	 */
	public static final String timeDisparity = "timeDisparity";
	/**
	 * 记住该设备uuid
	 */
	public static final String uid = "uid";

	/**
	 * 登陆时间
	 */
	public static final String LoginTime = "loginTime";
	private static BaseSharedPreferences instance = null;

	public static BaseSharedPreferences getInstance() {
		if (instance == null) {
			instance = new BaseSharedPreferences() {

				@Override
				public String getFilename() {
					// TODO Auto-generated method stub
					return GENERAL_SETTING;
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

	public String get(String key, String filename) {
		if (key == null)
			return "";
		SharedPreferences pref = APP.getInstance().getSharedPreferences(
				filename, android.content.Context.MODE_PRIVATE);
		return pref.getString(key, "");
	}

	public void setPassWord(String value) {
		set(PWD, value, GENERAL_SETTING);
	}

	public String getPassWord() {
		return get(PWD, GENERAL_SETTING);
	}

	public void setPhoneNum(String value) {
		set(PHONE_NUM, value, GENERAL_SETTING);
	}

	public String getPhoneNum() {
		return get(PHONE_NUM, GENERAL_SETTING);
	}
	public void setVeision(String value) {
        set(VEISION, value, GENERAL_SETTING);
    }

    public String getVersion() {
        return get(VEISION, GENERAL_SETTING);
    }
	public void setTimeDisparity(String value) {
		set(timeDisparity, value,GENERAL_SETTING);
	}

	public String getTimeDisparity() {
		if (get(timeDisparity,GENERAL_SETTING).isEmpty()) {
			set(timeDisparity, "0",GENERAL_SETTING);
		}
		return get(timeDisparity,GENERAL_SETTING);
	}

	public boolean getRemandpwd() {
		if (get(remandPwd,GENERAL_SETTING).isEmpty()) {
			return false;
		}
		if (get(remandPwd,GENERAL_SETTING).equals("1")) {
			return true;
		} else {
			return false;

		}
	}

	public void setRemandpwd(Boolean value) {
		if (value) {
			set(remandPwd, "1",GENERAL_SETTING);
		} else {
			set(remandPwd, "0",GENERAL_SETTING);
		}
	}

	public boolean getAutoLog() {
		if (get(autoLogin,GENERAL_SETTING).isEmpty()) {
			return false;
		}
		if (get(autoLogin,GENERAL_SETTING).equals("1")) {
			return true;
		} else {
			return false;

		}
	}

	public void setAutoLog(Boolean value) {
		if (value) {
			set(autoLogin, "1",GENERAL_SETTING);
		} else {
			set(autoLogin, "0",GENERAL_SETTING);
		}
	}

	public String getSign() {
		return get(SIGN,GENERAL_SETTING);
	}

	public void setSign(String value) {
		set(SIGN, value,GENERAL_SETTING);
	}

	public String getUid() {
		return get(uid,GENERAL_SETTING);
	}

	public void setUid(String value) {
		set(uid, value,GENERAL_SETTING);
	}

	public String getLoginTime() {
		if (get(LoginTime,GENERAL_SETTING).isEmpty()) {
			set(LoginTime, System.currentTimeMillis() + "",GENERAL_SETTING);
		}
		return get(LoginTime,GENERAL_SETTING);
	}

	public void setLoginTime(String value) {
		set(uid, value,GENERAL_SETTING);
	}
	
	public String getUserId(){
		if (get(USERID,GENERAL_SETTING).isEmpty()) {
			return "";
		}else {
			return get(USERID,GENERAL_SETTING);
		}
	}
	public void setUserId(String token){
		set(USERID,token,GENERAL_SETTING);
	}
	public boolean isLogin(){
		return !TextUtils.isEmpty(getUserId());
	}
}
