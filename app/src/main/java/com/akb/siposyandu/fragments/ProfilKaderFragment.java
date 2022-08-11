package com.akb.siposyandu.fragments;

import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.akb.siposyandu.R;
import com.akb.siposyandu.activities.*;
import com.akb.siposyandu.models.*;
import android.widget.*;
import com.androidnetworking.*;
import com.akb.siposyandu.constants.*;
import com.androidnetworking.common.*;
import com.androidnetworking.interfaces.*;
import org.json.*;
import com.androidnetworking.error.*;

public class ProfilKaderFragment extends Fragment
{

	public BerandaActivity activity;
	public Kader kader;
	TextView txtNik,txtNama,txtStatus,txtJenisKelamin,txtNoTel,txtAlamat;

	public ProfilKaderFragment(BerandaActivity activity){
		this.activity = activity;
		kader = new Kader();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_profil_kader,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		txtNik = view.findViewById(R.id.profil_kader_txt_nik);
		txtNama = view.findViewById(R.id.profil_kader_txt_nama);
		txtStatus = view.findViewById(R.id.profil_kader_txt_status);
		txtNoTel = view.findViewById(R.id.profil_kader_txt_no_telepon);
		txtJenisKelamin = view.findViewById(R.id.profil_kader_txt_jk);
		txtAlamat = view.findViewById(R.id.profil_kader_txt_alamat);
		Button btnEdit = view.findViewById(R.id.profil_kader_btn_edit);
		btnEdit.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1){
					((TambahKaderFragment)activity.fragments.get(TambahKaderFragment.class)).setKader(kader,"EDIT");
					activity.setFragment(TambahKaderFragment.class);
				}
			});
		String nikKader = ConstantVariables.APP_PREF.getString("id","");
		AndroidNetworking.get(ConstantVariables.API+"profil_kader.php?nik_kader="+nikKader)
			.setPriority(Priority.MEDIUM)
			.build()
			.getAsJSONObject(new JSONObjectRequestListener(){

				@Override
				public void onResponse(JSONObject p1){
					try{
						String nik = p1.getString("nik_kader");
						String nama = p1.getString("nama");
						String jenisKelamin = p1.getString("jenis_kelamin");
						String noTel = p1.getString("no_telepon");
						String alamat = p1.getString("alamat");
						String status = p1.getString("status");
						kader.setNik(nik);
						kader.setNama(nama);
						kader.setJenisKelamin(jenisKelamin);
						kader.setNoTelepon(noTel);
						kader.setAlamat(alamat);
						kader.setStatus(status);
						
						txtNik.setText(nik);
						txtNama.setText(nama);
						txtJenisKelamin.setText(jenisKelamin);
						txtNoTel.setText(noTel);
						txtAlamat.setText(alamat);
						txtStatus.setText(status);
						
					}catch(JSONException e){
						Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
					}
				}

				@Override
				public void onError(ANError e){
					Toast.makeText(activity.getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

				}
			});
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
	}
	
	}
