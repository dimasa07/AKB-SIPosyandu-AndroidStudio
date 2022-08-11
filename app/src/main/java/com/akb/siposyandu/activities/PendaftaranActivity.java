package com.akb.siposyandu.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.*;
import android.os.*;
import com.akb.siposyandu.R;
import android.widget.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;

public class PendaftaranActivity extends AppCompatActivity implements Button.OnClickListener{

	private Button btnDaftar, btnKembali;
	private EditText edtNik, edtNama, edtNamaSuami,edtNotel,edtAlamat,
	edtTanggal,edtBulan,edtTahun,edtUsername,edtPassword,edtRePassword;
	private RadioGroup rgStatus,rgGolDarah;
	private RadioButton rbHamil,rbKB,rbBelumDitentukan,rbA,rbB,rbAB,rbO;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pendaftaran);

		edtNik = findViewById(R.id.pendaftaran_nik);
		edtNama = findViewById(R.id.pendaftaran_nama);
		edtNamaSuami = findViewById(R.id.pendaftaran_nama_suami);
		edtNotel = findViewById(R.id.pendaftaran_notel);
		edtAlamat = findViewById(R.id.pendaftaran_alamat);
		edtTanggal = findViewById(R.id.pendaftaran_tanggal);
		edtBulan = findViewById(R.id.pendaftaran_bulan);
		edtTahun = findViewById(R.id.pendaftaran_tahun);
		edtUsername = findViewById(R.id.pendaftaran_username);
		edtPassword =  findViewById(R.id.pendaftaran_password);
		edtRePassword = findViewById(R.id.pendaftaran_repassword);
		btnDaftar = findViewById(R.id.pendaftaran_daftar);
		btnKembali = findViewById(R.id.pendaftaran_kembali);
		rgStatus = findViewById(R.id.pendaftaran_rg_status);
		rgGolDarah = findViewById(R.id.pendaftaran_rg_goldarah);

		btnDaftar.setOnClickListener(this);
		btnKembali.setOnClickListener(this);
	}

	@Override
	public void onClick(View p1){
		switch(p1.getId()){
			case R.id.pendaftaran_daftar:
				String nik = edtNik.getText().toString();
				String nama = edtNama.getText().toString();
				String namaSuami = edtNamaSuami.getText().toString();
				String noTel = edtNotel.getText().toString();
				String alamat = edtAlamat.getText().toString();
				String tanggal = edtTanggal.getText().toString();
				String bulan = edtBulan.getText().toString();
				String tahun = edtTahun.getText().toString();
				String username = edtUsername.getText().toString();
				String password = edtPassword.getText().toString();
				String rePassword = edtRePassword.getText().toString();
				if(nik.isEmpty() || nama.isEmpty() || namaSuami.isEmpty() || noTel.isEmpty()
					 || alamat.isEmpty() || tanggal.isEmpty() || bulan.isEmpty() || tahun.isEmpty()
					 || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
					Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					if(!password.equals(rePassword)){
						Toast.makeText(this, "Password dan Re-Password tidak sesuai", Toast.LENGTH_LONG).show();
					}else{
						String tanggal_lahir = tanggal + "/" + bulan + "/" + tahun;
						int golDarahId = rgGolDarah.getCheckedRadioButtonId();
						int statusId = rgStatus.getCheckedRadioButtonId();
						String golDarah = ((RadioButton)findViewById(golDarahId)).getText().toString();
						String status = ((RadioButton)findViewById(statusId)).getText().toString();
						daftar(nik, nama, namaSuami, noTel, alamat, tanggal_lahir, golDarah, status,username,password);
					}
				}
				break;
			case R.id.pendaftaran_kembali:
				finish();
				break;
		}
	}

	public void daftar(String nik, String nama, String namaSuami, String noTel,
										 String alamat, String tanggalLahir, String golDarah, String status,
										 String username,String password){
		AndroidNetworking.post(ConstantVariables.API + "tambah_peserta.php")
			.addBodyParameter("nik_ibu", nik)
			.addBodyParameter("nama", nama)
			.addBodyParameter("nama_suami", namaSuami)
			.addBodyParameter("no_telepon", noTel)
			.addBodyParameter("alamat", alamat)
			.addBodyParameter("tanggal_lahir",tanggalLahir)
			.addBodyParameter("gol_darah",golDarah)
			.addBodyParameter("status", status)
			.addBodyParameter("username",username)
			.addBodyParameter("password",password)
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONObject(new JSONObjectRequestListener(){

				@Override
				public void onResponse(JSONObject p1){
					try{
						int value = p1.getInt("value");
						String message = p1.getString("message");

						Toast.makeText(PendaftaranActivity.this.getApplicationContext(), message, Toast.LENGTH_LONG).show();
						if(value == 1){
							finish();
						}
					}catch(JSONException e){
						Toast.makeText(PendaftaranActivity.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}

				@Override
				public void onError(ANError e){
					Toast.makeText(PendaftaranActivity.this.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

				}
			});
	}
		

}
