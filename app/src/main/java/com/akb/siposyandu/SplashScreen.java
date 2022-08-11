package com.akb.siposyandu;

import android.support.v7.app.*;
import android.os.*;
import android.content.*;
import com.akb.siposyandu.activities.*;
import com.akb.siposyandu.constants.*;


public class SplashScreen extends AppCompatActivity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		SharedPreferences pref = getSharedPreferences(ConstantVariables.APP_PREFERENCESS,MODE_PRIVATE);
		ConstantVariables.initPreferences(pref);
		
		String username = pref.getString("username","guest");

		Intent berandaIntent = new Intent(this,BerandaActivity.class);
		Intent loginIntent = new Intent(this,LoginActivity.class);
		
		if(!username.equals("guest")){
			start(berandaIntent);
		}else{
			start(loginIntent);
		}
	}

	public void start(final Intent intent){
		new Handler().postDelayed(new Runnable(){

				@Override
				public void run(){
					startActivity(intent);
					finish();
				}
			}, 2000);
	}

}
