package com.akb.siposyandu.constants;
import com.akb.siposyandu.services.*;
import android.content.*;
import com.akb.siposyandu.activities.*;
import com.akb.siposyandu.models.*;

public class ConstantVariables
{
	public static final String LOG_TAG = "SIPosyandu";
	public static final String APP_PREFERENCESS ="app_preferences";
	public static String API = "https://onschool-learning.000webhostapp.com/api/";
	
	public static SharedPreferences APP_PREF;
	public static SharedPreferences.Editor EDIT_PREF;
	public static User USER;

	
	public static void initPreferences(SharedPreferences pref){
		APP_PREF = pref;
		EDIT_PREF = pref.edit();
	}
}
