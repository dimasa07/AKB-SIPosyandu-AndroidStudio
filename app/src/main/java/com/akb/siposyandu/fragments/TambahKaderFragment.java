package com.akb.siposyandu.fragments;

import com.akb.siposyandu.R;
import android.support.v4.app.Fragment;
import com.akb.siposyandu.activities.*;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.*;
import android.widget.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;
import com.androidnetworking.common.*;
import com.akb.siposyandu.models.*;
import android.app.*;


public class TambahKaderFragment extends Fragment implements View.OnClickListener{
	public BerandaActivity activity;
	private View root;
	private TextView txtTitle;
	private Button btnTambah,btnKembali;
	private EditText edtNik,edtNama,edtNoTelepon,edtAlamat,edtJK,edtStatus,
	edtUsername,edtPassword,edtRePassword;
	private TableRow rowUsername,rowPassword,rowRePassword;
	private RadioGroup rgJK,rgStatus;
	private RadioButton rbMale,rbFemale,rbAktif,rbTidakAktif;
	private Kader kader;
	private String mode = "";

	public TambahKaderFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_tambah_kader, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		root = view;
		txtTitle = view.findViewById(R.id.tambah_kader_title);
		btnKembali = view.findViewById(R.id.tambah_kader_btn_kembali);
		btnTambah = view.findViewById(R.id.tambah_kader_btn_tambah);
		edtNik = view.findViewById(R.id.tambah_kader_edt_nik);
		edtNama = view.findViewById(R.id.tambah_kader_edt_nama);
		edtNoTelepon = view.findViewById(R.id.tambah_kader_edt_notel);
		edtAlamat = view.findViewById(R.id.tambah_kader_edt_alamat);
		edtJK = view.findViewById(R.id.tambah_kader_edt_jk);
		edtStatus = view.findViewById(R.id.tambah_kader_edt_status);
		edtUsername = view.findViewById(R.id.tambah_kader_edt_username);
		edtPassword = view.findViewById(R.id.tambah_kader_edt_password);
		edtRePassword = view.findViewById(R.id.tambah_kader_edt_repassword);
		rowUsername = view.findViewById(R.id.tambah_kader_row_username);
		rowPassword = view.findViewById(R.id.tambah_kader_row_password);
		rowRePassword = view.findViewById(R.id.tambah_kader_row_repassword);
		rgJK = view.findViewById(R.id.tambah_kader_radio_jk);
		rgStatus = view.findViewById(R.id.tambah_kader_radio_status);
		rbMale = view.findViewById(R.id.tambah_kader_radio_male);
		rbFemale = view.findViewById(R.id.tambah_kader_radio_female);
		rbAktif = view.findViewById(R.id.tambah_kader_radio_aktif);
		rbTidakAktif = view.findViewById(R.id.tambah_kader_radio_tidakaktif);

		btnTambah.setOnClickListener(this);
		btnKembali.setOnClickListener(this);

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(mode.equals("VIEW")){
			btnTambah.setVisibility(View.GONE);
			edtNik.setEnabled(false);
			edtNama.setEnabled(false);
			edtAlamat.setEnabled(false);
			edtNoTelepon.setEnabled(false);
			rowUsername.setVisibility(View.GONE);
			rowPassword.setVisibility(View.GONE);
			rowRePassword.setVisibility(View.GONE);
			rgJK.setVisibility(View.GONE);
			rgStatus.setVisibility(View.GONE);
			edtJK.setVisibility(View.VISIBLE);
			edtStatus.setVisibility(View.VISIBLE);
			edtJK.setText(kader.getJenisKelamin());
			edtStatus.setText(kader.getStatus());

		}else{
			btnTambah.setVisibility(View.VISIBLE);
			edtNik.setEnabled(true);
			edtNama.setEnabled(true);
			edtAlamat.setEnabled(true);
			edtNoTelepon.setEnabled(true);
			rgJK.setVisibility(View.VISIBLE);
			rgStatus.setVisibility(View.VISIBLE);
			edtJK.setVisibility(View.GONE);
			edtStatus.setVisibility(View.GONE);
		}
		if(kader != null){
			edtNik.setText(kader.getNik());
			edtNama.setText(kader.getNama());
			edtNoTelepon.setText(kader.getNoTelepon());
			edtAlamat.setText(kader.getAlamat());
			if(mode.equals("EDIT")){
				edtNik.setEnabled(false);
				txtTitle.setText("EDIT KADER");
				btnTambah.setText("SIMPAN");
				rowUsername.setVisibility(View.GONE);
			rowPassword.setVisibility(View.GONE);
			rowRePassword.setVisibility(View.GONE);
			}else{
				txtTitle.setText("PROFIL KADER");
			}
		}else{
			edtNik.setText("");
			edtNama.setText("");
			edtNoTelepon.setText("");
			edtAlamat.setText("");
			txtTitle.setText("TAMBAH KADER");
			btnTambah.setText("TAMBAH");
			rowUsername.setVisibility(View.VISIBLE);
			rowPassword.setVisibility(View.VISIBLE);
			rowRePassword.setVisibility(View.VISIBLE);
		}
	}


	@Override
	public void onDetach(){
		super.onDetach();
		kader = null;
		mode = "";
	}

	@Override
	public void onClick(View p1){
		switch(p1.getId()){
			case R.id.tambah_kader_btn_tambah:
				String nik = edtNik.getText().toString();
				String nama = edtNama.getText().toString();
				String noTel = edtNoTelepon.getText().toString();
				String alamat = edtAlamat.getText().toString();	
				int jkId = rgJK.getCheckedRadioButtonId();
				int statusId = rgStatus.getCheckedRadioButtonId();
				String jenisKelamin = ((RadioButton)root.findViewById(jkId)).getText().toString();
				String status = ((RadioButton)root.findViewById(statusId)).getText().toString();
				String username = edtUsername.getText().toString();
				String password = edtPassword.getText().toString();
				String rePassword = edtRePassword.getText().toString();
				if(nik.isEmpty() || nama.isEmpty() || noTel.isEmpty() || alamat.isEmpty()){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					if(!mode.equals("EDIT")&&(username.isEmpty() || password.isEmpty() || rePassword.isEmpty())){
						Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
					}else if(!password.equals(rePassword)){
						Toast.makeText(activity.getApplicationContext(), "Password dan Re-Password tidak sesuai", Toast.LENGTH_LONG).show();
					}else{
						tambah(nik, nama, noTel, alamat, jenisKelamin, status, username, password);
					}
				}
				break;
			case R.id.tambah_kader_btn_kembali:
				String level = ConstantVariables.APP_PREF.getString("level", "");
				if(level.equals("ADMIN")){
					activity.setFragment(DataKaderFragment.class);
				}else if(level.equals("KADER")){
					activity.setFragment(ProfilKaderFragment.class);
				}
				break;
		}
	}

	public void tambah(String nik, final String nama, String noTel, String alamat, String jk, String status, String username, String password){
		String action  = "tambah_kader.php";
		if(mode.equals("EDIT")){
			action = "edit_kader.php";
		}
		AndroidNetworking.post(ConstantVariables.API + action)
			.addBodyParameter("nik", nik)
			.addBodyParameter("nama", nama)
			.addBodyParameter("jenis_kelamin", jk)
			.addBodyParameter("no_telepon", noTel)
			.addBodyParameter("alamat", alamat)
			.addBodyParameter("status", status)
			.addBodyParameter("username", username)
			.addBodyParameter("password", password)
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONObject(new JSONObjectRequestListener(){

				@Override
				public void onResponse(JSONObject p1){
					try{
						int value = p1.getInt("value");
						String message = p1.getString("message");

						Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
						if(value == 1){
							String level = ConstantVariables.APP_PREF.getString("level", "");
							if(level.equals("ADMIN")){
								activity.setFragment(DataKaderFragment.class);
							}else if(level.equals("KADER")){
								activity.setFragment(ProfilKaderFragment.class);
							}
							ConstantVariables.EDIT_PREF.putString("nama",nama);
							ConstantVariables.EDIT_PREF.commit();
						}
					}catch(JSONException e){
						Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}

				@Override
				public void onError(ANError e){
					Toast.makeText(activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

				}
			});
	}

	public void setKader(Kader kader, String mode){
		this.kader = kader;
		this.mode = mode;
	}

}
