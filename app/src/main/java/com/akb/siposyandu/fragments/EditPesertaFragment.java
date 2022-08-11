package com.akb.siposyandu.fragments;

import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.akb.siposyandu.R;
import com.akb.siposyandu.activities.*;
import android.widget.*;
import com.akb.siposyandu.models.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;

public class EditPesertaFragment extends Fragment implements Button.OnClickListener{

	public BerandaActivity activity;
	public Peserta peserta;
	private TextView txtTitle;
	private Button btnDaftar, btnKembali,btnRiwayat;
	private EditText edtNik, edtNama, edtNamaSuami,edtNotel,edtAlamat,
	edtTanggal,edtBulan,edtTahun,edtUsername,edtPassword,edtRePassword,
	edtStatus,edtGolDarah,edtTanggalLahir;
	private RadioGroup rgStatus,rgGolDarah;
	private TableRow rowUsername,rowPassword,rowRePassword;
	private View root;
	private String mode;
	private LinearLayout layTgl;

	public EditPesertaFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.pendaftaran, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		root = view;
		txtTitle = view.findViewById(R.id.pendaftaran_title);
		edtNik = view.findViewById(R.id.pendaftaran_nik);
		edtNama = view.findViewById(R.id.pendaftaran_nama);
		edtNamaSuami = view.findViewById(R.id.pendaftaran_nama_suami);
		edtNotel = view.findViewById(R.id.pendaftaran_notel);
		edtAlamat = view.findViewById(R.id.pendaftaran_alamat);
		edtTanggal = view.findViewById(R.id.pendaftaran_tanggal);
		edtBulan = view.findViewById(R.id.pendaftaran_bulan);
		edtTahun = view.findViewById(R.id.pendaftaran_tahun);
		edtUsername = view.findViewById(R.id.pendaftaran_username);
		edtPassword =  view.findViewById(R.id.pendaftaran_password);
		edtRePassword = view.findViewById(R.id.pendaftaran_repassword);
		edtStatus = view.findViewById(R.id.pendaftaran_edt_status);
		edtGolDarah = view.findViewById(R.id.pendaftaran_edt_goldarah);
		edtTanggalLahir = view.findViewById(R.id.pendaftaran_edt_tanggal);
		btnDaftar = view.findViewById(R.id.pendaftaran_daftar);
		btnKembali = view.findViewById(R.id.pendaftaran_kembali);
		btnRiwayat = view.findViewById(R.id.pendaftaran_riwayat);
		rgStatus = view.findViewById(R.id.pendaftaran_rg_status);
		rgGolDarah = view.findViewById(R.id.pendaftaran_rg_goldarah);
		rowUsername = view.findViewById(R.id.pendaftaran_row_username);
		rowPassword = view.findViewById(R.id.pendaftaran_row_password);
		rowRePassword = view.findViewById(R.id.pendaftaran_row_repassword);
		layTgl = view.findViewById(R.id.pendaftaran_lay_tanggal);
		btnDaftar.setOnClickListener(this);
		btnKembali.setOnClickListener(this);
		btnRiwayat.setOnClickListener(this);

		btnDaftar.setText("SIMPAN");

		edtNik.setEnabled(false);
		rowUsername.setVisibility(View.GONE);
		rowPassword.setVisibility(View.GONE);
		rowRePassword.setVisibility(View.GONE);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(peserta != null){
			edtNik.setText(peserta.getNik());
			edtNama.setText(peserta.getNama());
			edtNamaSuami.setText(peserta.getNamaSuami());
			edtNotel.setText(peserta.getNoTelepon());
			edtAlamat.setText(peserta.getAlamat());

			if(mode.equals("VIEW")){
				edtNik.setEnabled(false);
				edtNama.setEnabled(false);
				edtNamaSuami.setEnabled(false);
				edtNotel.setEnabled(false);
				edtAlamat.setEnabled(false);
				edtStatus.setEnabled(false);
				edtGolDarah.setEnabled(false);
				edtTanggalLahir.setEnabled(false);
				layTgl.setVisibility(View.GONE);
				edtStatus.setVisibility(View.VISIBLE);
				edtGolDarah.setVisibility(View.VISIBLE);
				edtTanggalLahir.setVisibility(View.VISIBLE);
				btnRiwayat.setVisibility(View.VISIBLE);
				rgStatus.setVisibility(View.GONE);
				rgGolDarah.setVisibility(View.GONE);
				btnDaftar.setVisibility(View.GONE);
				edtStatus.setText(peserta.getStatus());
				edtGolDarah.setText(peserta.getGolDarah());
				edtTanggalLahir.setText(peserta.getTanggalLahir());
				txtTitle.setText("PROFIL PESERTA");
			}else{
				edtNik.setEnabled(true);
				edtNama.setEnabled(true);
				edtNamaSuami.setEnabled(true);
				edtNotel.setEnabled(true);
				edtAlamat.setEnabled(true);
				layTgl.setVisibility(View.VISIBLE);
				rgStatus.setVisibility(View.VISIBLE);
				rgGolDarah.setVisibility(View.VISIBLE);
				btnDaftar.setVisibility(View.VISIBLE);
				edtStatus.setVisibility(View.GONE);
				edtGolDarah.setVisibility(View.GONE);
				edtTanggalLahir.setVisibility(View.GONE);
				btnRiwayat.setVisibility(View.GONE);
				txtTitle.setText("EDIT PROFIL");
			}
		}
	}

	@Override
	public void onDetach(){
		super.onDetach();
		peserta = null;
		mode = "";
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

				if(nik.isEmpty() || nama.isEmpty() || namaSuami.isEmpty() || noTel.isEmpty()
					 || alamat.isEmpty() || tanggal.isEmpty() || bulan.isEmpty() || tahun.isEmpty()
					 ){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{

					String tanggal_lahir = tanggal + "/" + bulan + "/" + tahun;
					int golDarahId = rgGolDarah.getCheckedRadioButtonId();
					int statusId = rgStatus.getCheckedRadioButtonId();
					String golDarah = ((RadioButton)root.findViewById(golDarahId)).getText().toString();
					String status = ((RadioButton)root.findViewById(statusId)).getText().toString();
					simpan(nik, nama, namaSuami, noTel, alamat, tanggal_lahir, golDarah, status);

				}
				break;
			case R.id.pendaftaran_kembali:
				if(mode.equals("VIEW")){
					activity.setFragment(DataPesertaFragment.class);
				}else{
					activity.setFragment(ProfilPesertaFragment.class);
				}
				break;
			case R.id.pendaftaran_riwayat:
				((DataRiwayatFragment)activity.fragments.get(DataRiwayatFragment.class)).setPeserta(peserta);
				activity.setFragment(DataRiwayatFragment.class);
				break;
		}
	}

	private void simpan(String nik, final String nama, String namaSuami, String noTel, String alamat, String tanggalLahir, String golDarah, String status){
		AndroidNetworking.post(ConstantVariables.API + "edit_peserta.php")
			.addBodyParameter("nik_ibu", nik)
			.addBodyParameter("nama", nama)
			.addBodyParameter("nama_suami", namaSuami)
			.addBodyParameter("no_telepon", noTel)
			.addBodyParameter("alamat", alamat)
			.addBodyParameter("tanggal_lahir", tanggalLahir)
			.addBodyParameter("gol_darah", golDarah)
			.addBodyParameter("status", status)
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
							activity.setFragment(ProfilPesertaFragment.class);
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

	public void setPeserta(Peserta peserta, String mode){
		this.peserta = peserta;
		this.mode = mode;
	}

}
