package com.akb.siposyandu.services;
import com.akb.siposyandu.models.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import com.androidnetworking.error.*;
import org.json.*;
import android.util.*;
import com.akb.siposyandu.activities.*;
import android.content.*;
import android.widget.*;

public class LoginService implements JSONObjectRequestListener,JSONArrayRequestListener
{
	
	private LoginActivity activity;
	
	public LoginService(LoginActivity activity){
		this.activity = activity;
	}

	@Override
	public void onResponse(JSONArray ja){
	}

	@Override
	public void onResponse(JSONObject jo){
		String request ="";
		try{
			request = jo.getString("request");
		}catch(JSONException e){
			Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
		}
		
		switch(request){
			case "login":loginResponse(jo);break;
		}
	}

	@Override
	public void onError(ANError e){
		Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
	}
	
	
	public void login(String username, String password){
		AndroidNetworking.post(ConstantVariables.API+"login.php")
		.addBodyParameter("username",username)
		.addBodyParameter("password",password)
		.setPriority(Priority.MEDIUM)
		.build()
		.getAsJSONObject(this);
	}
	
	public void loginResponse(JSONObject jo){
		String status = "";
		String message = "";
		String username ="";
		String password = "";
		String nama ="";
		String level = "";
		String id = "";

		try{
			status = jo.getString("status");
			message = jo.getString("message");
			if(status.equals("sukses")){
				JSONObject value = jo.getJSONObject("value");
				username = value.getString("username");
				password = value.getString("password");
				nama = value.getString("nama");
				level = value.getString("level");
				id = value.getString("id");
				SharedPreferences.Editor editPref = ConstantVariables.EDIT_PREF;
				editPref.putString("username",username);
				editPref.putString("password",password);
				editPref.putString("nama",nama);
				editPref.putString("level",level);
				editPref.putString("id",id);
				editPref.commit();
				activity.suksesLogin();
			}else{
				Toast.makeText(activity.getApplicationContext(),message,Toast.LENGTH_LONG).show();
			}
		}catch(JSONException e){
			Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}

}
