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

public class DataKaderFragment extends Fragment
{
	public BerandaActivity activity;
	public RecyclerView recyclerView;
	private List<Kader> kader;
	AdapterKader adapterKader;
	
	public DataKaderFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_data_kader,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		
		kader = new ArrayList<>();

		recyclerView = view.findViewById(R.id.data_kader_recyclerView);
		RecyclerView.LayoutManager lm = new LinearLayoutManager(activity.getApplicationContext());
		recyclerView.setLayoutManager(lm);
		adapterKader = new AdapterKader(this, kader);
		recyclerView.setAdapter(adapterKader);
		
		FloatingActionButton fab = view.findViewById(R.id.kader_tambah);
		fab.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1){
					activity.setFragment(TambahKaderFragment.class);
				}
			});
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		restoreData();
	}
	
	public void restoreData(){
		kader.clear();
		AndroidNetworking.get(ConstantVariables.API+"data_kader.php")
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONArray(new JSONArrayRequestListener(){
				@Override
				public void onResponse(JSONArray p1){
					for(int i=0; i<p1.length();i++){
						String nik = "";
						String nama = "";
						String jenisKelamin = "";
						String noTelepon = "";
						String alamat = "";
						String status = "";
						try{
							nik = p1.getJSONObject(i).getString("nik");
							nama = p1.getJSONObject(i).getString("nama");
							jenisKelamin = p1.getJSONObject(i).getString("jenis_kelamin");
							status = p1.getJSONObject(i).getString("status");				
							alamat = p1.getJSONObject(i).getString("alamat");
							noTelepon = p1.getJSONObject(i).getString("no_telepon");
						}catch(JSONException e){
							Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
						}
						Kader k = new Kader();
						k.setNik(nik);
						k.setNama(nama);
						k.setJenisKelamin(jenisKelamin);
						k.setNoTelepon(noTelepon);
						k.setAlamat(alamat);
						k.setStatus(status);
						kader.add(k);
					}
					adapterKader.notifyDataSetChanged();
				}

				@Override
				public void onError(ANError p1){
					Toast.makeText(activity.getApplicationContext(),p1.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
	}
}
