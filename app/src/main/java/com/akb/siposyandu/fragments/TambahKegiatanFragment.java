package com.akb.siposyandu.fragments;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import com.akb.siposyandu.R;
import android.support.v7.widget.*;
import com.akb.siposyandu.models.*;
import java.util.*;
import com.akb.siposyandu.adapters.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import android.widget.*;
import com.androidnetworking.error.*;
import android.support.design.widget.*;
import com.akb.siposyandu.activities.*;

public class TambahKegiatanFragment extends Fragment implements View.OnClickListener{

	public BerandaActivity activity;
	EditText editNamaKegiatan, editDeskripsiKegiatan;
	DatePicker dpTanggalKegiatan;

	public TambahKegiatanFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_tambah_kegiatan, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		Button btnKembali = view.findViewById(R.id.tambah_kegiatan_btn_kembali);
		Button btnTambah = view.findViewById(R.id.tambah_kegiatan_btn_tambah);
		editNamaKegiatan = view.findViewById(R.id.tambah_kegitan_edt_nama);
		editDeskripsiKegiatan = view.findViewById(R.id.tambah_kegiatan_edt_deskripsi);
		dpTanggalKegiatan = view.findViewById(R.id.tambah_kegiatan_date_picker);

		btnTambah.setOnClickListener(this);
		btnKembali.setOnClickListener(this);

	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);

	}

	@Override
	public void onClick(View p1){
		switch(p1.getId()){
			case R.id.tambah_kegiatan_btn_tambah:
				String namaKegiatan = editNamaKegiatan.getText().toString();
				String deskripsiKegiatan = editDeskripsiKegiatan.getText().toString();
				int tahun = dpTanggalKegiatan.getYear();
				int bulan = dpTanggalKegiatan.getMonth();
				int tanggal = dpTanggalKegiatan.getDayOfMonth();
				String tanggalKegiatan = tanggal + "/" + bulan + "/" + tahun;
				if(namaKegiatan.isEmpty() || deskripsiKegiatan.isEmpty()){
					Toast.makeText(activity.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
				}else{
					tambah(namaKegiatan, deskripsiKegiatan, tanggalKegiatan);
				}
				break;
			case R.id.tambah_kegiatan_btn_kembali:
				activity.setFragment(KegiatanFragment.class);
				break;
		}
	}

	public void tambah(String nama, String deskrpsi, String tanggal){
		AndroidNetworking.post(ConstantVariables.API + "tambah_kegiatan.php")
			.addBodyParameter("nama", nama)
			.addBodyParameter("deskripsi", deskrpsi)
			.addBodyParameter("tanggal", tanggal)
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
							activity.setFragment(KegiatanFragment.class);
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
}
