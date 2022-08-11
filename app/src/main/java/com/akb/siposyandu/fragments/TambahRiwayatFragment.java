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


public class TambahRiwayatFragment extends Fragment implements View.OnClickListener{
	public BerandaActivity activity;
	private View root;
	private TextView txtTitle;
	private Button btnTambah,btnKembali;
	private EditText edtNik,edtNama,edtMetodeKB,edtHamilKe,
	edtTanggal,edtBulan,edtTahun;
	private RadioGroup rgKomplikasi,rgKegagalan;
	
	private Peserta peserta;

	public TambahRiwayatFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_tambah_riwayat, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		root = view;
		txtTitle = view.findViewById(R.id.tambah_riwayat_title);
		btnKembali = view.findViewById(R.id.tambah_riwayat_btn_kembali);
		btnTambah = view.findViewById(R.id.tambah_riwayat_btn_tambah);
		edtNik = view.findViewById(R.id.tambah_riwayat_edt_nik);
		edtNama = view.findViewById(R.id.tambah_riwayat_edt_nama);
		edtMetodeKB=  view.findViewById(R.id.tambah_riwayat_edt_metode_kb);
		edtHamilKe = view.findViewById(R.id.tambah_riwayat_edt_hamil_ke);
		edtTanggal = view.findViewById(R.id.tambah_riwayat_edt_tanggal);
		edtBulan = view.findViewById(R.id.tambah_riwayat_edt_bulan);
		edtTahun = view.findViewById(R.id.tambah_riwayat_edt_tahun);
		rgKomplikasi = view.findViewById(R.id.tambah_riwayat_rg_komplikasi);
		rgKegagalan = view.findViewById(R.id.tambah_riwayat_rg_kegagalan);
		btnTambah.setOnClickListener(this);
		btnKembali.setOnClickListener(this);

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(peserta != null){
			edtNik.setText(peserta.getNik());
			edtNama.setText(peserta.getNama());
		}

	}


	@Override
	public void onDetach(){
		super.onDetach();
		peserta = null;
	}

	@Override
	public void onClick(View p1){
		switch(p1.getId()){
			case R.id.tambah_riwayat_btn_tambah:
				String nik = edtNik.getText().toString();
				String nama = edtNama.getText().toString();
				String metodeKB = edtMetodeKB.getText().toString();
				String hamilKe = edtHamilKe.getText().toString();
				int komplikasiId = rgKomplikasi.getCheckedRadioButtonId();
				int kegagalanId = rgKegagalan.getCheckedRadioButtonId();
				String komplikasiKB = ((RadioButton)root.findViewById(komplikasiId)).getText().toString();
				String kegagalanKB = ((RadioButton)root.findViewById(kegagalanId)).getText().toString();
				String tanggal = edtTanggal.getText().toString();
				String bulan = edtBulan.getText().toString();
				String tahun = edtTahun.getText().toString();
				if(nik.isEmpty() || nama.isEmpty() || metodeKB.isEmpty() || hamilKe.isEmpty()
					||komplikasiKB.isEmpty()||kegagalanKB.isEmpty()||tanggal.isEmpty()
					||bulan.isEmpty()||tahun.isEmpty()){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					String tanggalRiwayat = tanggal+"/"+bulan+"/"+tahun;
						tambah(nik,metodeKB,komplikasiKB,kegagalanKB,hamilKe,tanggalRiwayat);
				}
				break;
			case R.id.tambah_riwayat_btn_kembali:
				((DataRiwayatFragment)activity.fragments.get(DataRiwayatFragment.class)).setPeserta(peserta);
				activity.setFragment(DataRiwayatFragment.class);
				break;
		}
	}

	

	private void tambah(String nik, String metodeKB, String komplikasiKB,String kegagalanKB,String hamilKe, String tanggalRiwayat){
		AndroidNetworking.post(ConstantVariables.API + "tambah_riwayat.php")
			.addBodyParameter("nik_ibu", nik)
			.addBodyParameter("metode_kb", metodeKB)
			.addBodyParameter("komplikasi_kb", komplikasiKB)
			.addBodyParameter("kegagalan_kb", kegagalanKB)
			.addBodyParameter("hamil_ke", hamilKe)
			.addBodyParameter("tanggal", tanggalRiwayat)
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
							((DataRiwayatFragment)activity.fragments.get(DataRiwayatFragment.class)).setPeserta(peserta);
							activity.setFragment(DataRiwayatFragment.class);
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

	public void setPeserta(Peserta peserta){
		this.peserta = peserta;
	}

}
