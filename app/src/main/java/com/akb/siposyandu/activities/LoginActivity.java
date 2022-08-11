package com.akb.siposyandu.activities;

import android.support.v7.app.*;
import android.os.*;
import com.akb.siposyandu.R;
import com.androidnetworking.AndroidNetworking;
import android.widget.*;
import android.view.View;
import com.akb.siposyandu.constants.*;
import android.content.*;
import com.akb.siposyandu.services.*;
import android.util.*;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener{

	private EditText edtUsername, edtPassword;
	private Button btnLogin, btnPendaftaran;
	private LoginService loginService;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		edtUsername = findViewById(R.id.login_editText_username);
		edtPassword = findViewById(R.id.login_editText_password);
		btnLogin = findViewById(R.id.login_button_login);
		btnPendaftaran = findViewById(R.id.login_button_pendaftaran);
		loginService = new LoginService(this);
		btnLogin.setOnClickListener(this);
		btnPendaftaran.setOnClickListener(this);

		AndroidNetworking.initialize(getApplicationContext());

	}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
			case R.id.login_button_login:
				login();
				break;
			case R.id.login_button_pendaftaran:
				pendaftaran();
				break;
		}
	}

	@Override
	public void onBackPressed(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Keluar Aplikasi");
		alert.setMessage("Yakin ingin keluar ?");
		alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2){
					keluar();
				}
			});
		alert.setNegativeButton(android.R.string.no,null);
		alert.show();
	}
	
	public void login(){
		String username = edtUsername.getText().toString();
		String password = edtPassword.getText().toString();

		if(username.isEmpty() || password.isEmpty()){
			Toast.makeText(this, "Username dan Password tidak boleh kosong", Toast.LENGTH_LONG).show();
		}else{
			String level = "";
			if(username.equals("admin") && username.equals("admin")){
				level = Level.ADMIN;
				SharedPreferences.Editor editPref = ConstantVariables.EDIT_PREF;
				editPref.putString("username", username);
				editPref.putString("level", level.toString());
				editPref.putString("nama", "Admin");
				editPref.commit();
				suksesLogin();
			}else{
				loginService.login(username,password);
			}
		}
	}
	
	public void suksesLogin(){
		startActivity(new Intent(this, BerandaActivity.class));
		finish();
	}

	public void pendaftaran(){
		startActivity(new Intent(this, PendaftaranActivity.class));
	}
	
	public void keluar(){
		super.onBackPressed();
	}
}
