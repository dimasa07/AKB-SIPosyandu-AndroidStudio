package com.akb.siposyandu.fragments;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import com.akb.siposyandu.R;
import android.support.v7.widget.*;
import java.util.*;
import com.akb.siposyandu.models.*;
import com.akb.siposyandu.adapters.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;
import android.widget.*;
import com.akb.siposyandu.activities.*;

public class DataPesertaFragment extends Fragment
{
	public BerandaActivity activity;
	public RecyclerView recyclerView;
	private List<Peserta> peserta;
	AdapterPeserta adapterPeserta;

	public DataPesertaFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_data_peserta,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		peserta = new ArrayList<>();
		
		recyclerView = view.findViewById(R.id.data_peserta_recyclerView);
		RecyclerView.LayoutManager lm = new LinearLayoutManager(activity.getApplicationContext());
		recyclerView.setLayoutManager(lm);
		adapterPeserta = new AdapterPeserta(this, peserta);
		recyclerView.setAdapter(adapterPeserta);
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		restoreData();
	}

	public void restoreData(){
		peserta.clear();
		AndroidNetworking.get(ConstantVariables.API+"data_peserta.php")
		.setPriority(Priority.MEDIUM)
		.build()
			.getAsJSONArray(new JSONArrayRequestListener(){
				@Override
				public void onResponse(JSONArray p1){
					for(int i=0; i<p1.length();i++){
						String nik = "";
						String nama = "";
						String namaSuami = "";
						String status = "";
						String tanggalLahir = "";
						String noTelepon = "";
						String golDarah = "";
						String alamat = "";
						try{
							nik = p1.getJSONObject(i).getString("nik");
							nama = p1.getJSONObject(i).getString("nama");
							namaSuami = p1.getJSONObject(i).getString("nama_suami");
							status = p1.getJSONObject(i).getString("status");
							tanggalLahir = p1.getJSONObject(i).getString("tanggal_lahir");
							golDarah = p1.getJSONObject(i).getString("gol_darah");
							alamat = p1.getJSONObject(i).getString("alamat");
							noTelepon = p1.getJSONObject(i).getString("no_telepon");
						}catch(JSONException e){
							Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
						}
						Peserta p = new Peserta();
						p.setNik(nik);
						p.setNama(nama);
						p.setNamaSuami(namaSuami);
						p.setStatus(status);
						p.setGolDarah(golDarah);
						p.setNoTelepon(noTelepon);
						p.setAlamat(alamat);
						p.setTanggalLahir(tanggalLahir);
						peserta.add(p);
					}
					adapterPeserta.notifyDataSetChanged();
				}

				@Override
				public void onError(ANError p1){
					Toast.makeText(activity.getApplicationContext(),p1.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
	}


}
