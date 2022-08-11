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

public class DataRiwayatFragment extends Fragment
{
	public BerandaActivity activity;
	public RecyclerView recyclerView;
	private List<Riwayat> riwayat;
	AdapterRiwayat adapterRiwayat;
	Peserta peserta;

	public DataRiwayatFragment(BerandaActivity activity){
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_data_riwayat,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		riwayat = new ArrayList<>();

		recyclerView = view.findViewById(R.id.data_riwayat_recyclerView);
		RecyclerView.LayoutManager lm = new LinearLayoutManager(activity.getApplicationContext());
		recyclerView.setLayoutManager(lm);
		adapterRiwayat = new AdapterRiwayat(this, riwayat);
		recyclerView.setAdapter(adapterRiwayat);

		FloatingActionButton fab = view.findViewById(R.id.riwayat_tambah);
		FloatingActionButton fabKembali = view.findViewById(R.id.riwayat_kembali);
		if(ConstantVariables.APP_PREF.getString("level","").equals("PESERTA")){
			fab.setVisibility(View.GONE);
			fabKembali.setVisibility(View.GONE);
		}else{
			fab.setVisibility(View.VISIBLE);
			fabKembali.setVisibility(View.VISIBLE);
		}
		fab.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1){
					((TambahRiwayatFragment)activity.fragments.get(TambahRiwayatFragment.class)).setPeserta(peserta);
					activity.setFragment(TambahRiwayatFragment.class);
				}
			});
		fabKembali.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1){
					((EditPesertaFragment)activity.fragments.get(EditPesertaFragment.class)).setPeserta(peserta,"VIEW");
					activity.setFragment(EditPesertaFragment.class);
				}
			});
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(peserta != null)
		restoreData();
	}

	public void restoreData(){
		riwayat.clear();
		AndroidNetworking.get(ConstantVariables.API+"data_riwayat.php?nik_ibu="+peserta.getNik())
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONArray(new JSONArrayRequestListener(){
				@Override
				public void onResponse(JSONArray p1){
					for(int i=0; i<p1.length();i++){
						String idRiwayat = "";
						String nikIbu = "";
						String metodeKB ="";
						String komplikasiKB = "";
						String kegagalanKB = "";
						String hamilKe = "";
						String tanggal = "";

						try{
							idRiwayat = p1.getJSONObject(i).getString("id_riwayat");
							nikIbu = p1.getJSONObject(i).getString("nik_ibu");
						  metodeKB = p1.getJSONObject(i).getString("metode_kb");
							komplikasiKB = p1.getJSONObject(i).getString("komplikasi_kb");
							kegagalanKB = p1.getJSONObject(i).getString("kegagalan_kb");
							hamilKe = p1.getJSONObject(i).getString("hamil_ke");
							tanggal = p1.getJSONObject(i).getString("tanggal");
						}catch(JSONException e){
							Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
						}
						Riwayat r = new Riwayat();
						r.setId(idRiwayat);
						r.setNikIbu(nikIbu);
						r.setNama(peserta.getNama());
						r.setMetodeKB(metodeKB);
						r.setKomplikasiKB(komplikasiKB);
						r.setKegagalanKB(kegagalanKB);
						r.setHamilKe(hamilKe);
						r.setTanggal(tanggal);
						riwayat.add(r);
					}
					adapterRiwayat.notifyDataSetChanged();
				}

				@Override
				public void onError(ANError p1){
					Toast.makeText(activity.getApplicationContext(),p1.getMessage(),Toast.LENGTH_LONG).show();
				}
			});
	}
	
	public void setPeserta(Peserta peserta){
		this.peserta = peserta;
	}
}
