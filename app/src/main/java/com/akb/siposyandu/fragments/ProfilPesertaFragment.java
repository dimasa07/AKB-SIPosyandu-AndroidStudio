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

public class ProfilPesertaFragment extends Fragment
{

	public BerandaActivity activity;
	public Peserta peserta;
	TextView txtNik,txtNama,txtNamaSuami,txtStatus,txtTglLahir,txtGolDarah,
	txtNoTel,txtAlamat;
	
	public ProfilPesertaFragment(BerandaActivity activity){
		this.activity = activity;
		peserta = new Peserta();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_profil_peserta,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		txtNik = view.findViewById(R.id.profil_peserta_txt_nik);
		txtNama = view.findViewById(R.id.profil_peserta_txt_nama);
		txtNamaSuami = view.findViewById(R.id.profil_peserta_txt_nama_suami);
		txtStatus = view.findViewById(R.id.profil_peserta_txt_status);
		txtTglLahir = view.findViewById(R.id.profil_peserta_txt_tgl_lahir);
		txtGolDarah = view.findViewById(R.id.profil_peserta_txt_gol_darah);
		txtNoTel = view.findViewById(R.id.profil_peserta_txt_no_telepon);
		txtAlamat = view.findViewById(R.id.profil_peserta_txt_alamat);
		Button btnEdit = view.findViewById(R.id.profil_peserta_btn_edit);
		btnEdit.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1){
					((EditPesertaFragment)activity.fragments.get(EditPesertaFragment.class)).setPeserta(peserta,"EDIT");
					activity.setFragment(EditPesertaFragment.class);
				}
			});
		String nikIbu = ConstantVariables.APP_PREF.getString("id","");
		AndroidNetworking.get(ConstantVariables.API+"profil_peserta.php?nik_ibu="+nikIbu)
		.setPriority(Priority.MEDIUM)
		.build()
			.getAsJSONObject(new JSONObjectRequestListener(){

				@Override
				public void onResponse(JSONObject p1){
					try{
						String nik = p1.getString("nik_ibu");
						String nama = p1.getString("nama");
						String namaSuami = p1.getString("nama_suami");
						String status = p1.getString("status");
						String tanggalLahir = p1.getString("tanggal_lahir");
						String golDarah = p1.getString("gol_darah");
						String noTel = p1.getString("no_telepon");
						String alamat = p1.getString("alamat");
						peserta.setNik(nik);
						peserta.setNama(nama);
						peserta.setNamaSuami(namaSuami);
						peserta.setStatus(status);
						peserta.setTanggalLahir(tanggalLahir);
						peserta.setGolDarah(golDarah);
						peserta.setNoTelepon(noTel);
						peserta.setAlamat(alamat);
						txtNik.setText(nik);
						txtNama.setText(nama);
						txtNamaSuami.setText(namaSuami);
						txtStatus.setText(status);
						txtTglLahir.setText(tanggalLahir);
						txtGolDarah.setText(golDarah);
						txtNoTel.setText(noTel);
						txtAlamat.setText(alamat);
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
