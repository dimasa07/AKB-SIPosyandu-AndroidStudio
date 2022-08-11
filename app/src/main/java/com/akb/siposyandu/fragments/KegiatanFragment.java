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

public class KegiatanFragment extends Fragment
{
	public BerandaActivity activity;
	public RecyclerView recyclerView;
	private List<Kegiatan> kegiatan;
	AdapterKegiatan adapterKegiatan;
	
	public KegiatanFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_kegiatan,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		kegiatan = new ArrayList<>();

		recyclerView = view.findViewById(R.id.data_kegiatan_recyclerView);
		RecyclerView.LayoutManager lm = new LinearLayoutManager(activity.getApplicationContext());
		recyclerView.setLayoutManager(lm);
		adapterKegiatan = new AdapterKegiatan(this, kegiatan);
		recyclerView.setAdapter(adapterKegiatan);
		
		FloatingActionButton fab = view.findViewById(R.id.kegiatan_tambah);
		if(ConstantVariables.APP_PREF.getString("level","").equals("PESERTA")){
			fab.setVisibility(View.GONE);
		}else{
			fab.setVisibility(View.VISIBLE);
		}
		fab.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1){
					activity.setFragment(TambahKegiatanFragment.class);
				}
			});
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		restoreData();
	}
	
	public void restoreData(){
		kegiatan.clear();
		AndroidNetworking.get(ConstantVariables.API+"data_kegiatan.php")
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONArray(new JSONArrayRequestListener(){
				@Override
				public void onResponse(JSONArray p1){
					for(int i=0; i<p1.length();i++){
						String idKegiatan = "";
						String nama = "";
						String deskripsi = "";
						String tanggal = "";
						
						try{
							idKegiatan = p1.getJSONObject(i).getString("id_kegiatan");
							nama = p1.getJSONObject(i).getString("nama");
						  deskripsi = p1.getJSONObject(i).getString("deskripsi");
							tanggal = p1.getJSONObject(i).getString("tanggal");
						}catch(JSONException e){
							Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
						}
						Kegiatan k = new Kegiatan();
						k.setIdKegiatan(idKegiatan);
						k.setNama(nama);
						k.setDeskripsi(deskripsi);
						k.setTanggal(tanggal);
						kegiatan.add(k);
					}
					adapterKegiatan.notifyDataSetChanged();
				}

				@Override
				public void onError(ANError p1){
					Toast.makeText(activity.getApplicationContext(),p1.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
	}
}
