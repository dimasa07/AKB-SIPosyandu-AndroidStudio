package com.akb.siposyandu.adapters;

import android.support.v7.widget.RecyclerView;
import java.util.List;
import com.akb.siposyandu.models.*;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.akb.siposyandu.R;
import java.text.NumberFormat;
import java.util.Locale;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.content.Context;
import android.util.Log;
import com.akb.siposyandu.constants.*;
import com.akb.siposyandu.fragments.*;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import android.os.Environment;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.*;
import org.json.*;
import android.app.*;
import android.content.*;
import android.widget.*;

public class AdapterRiwayat extends RecyclerView.Adapter<AdapterRiwayat.ViewHolderData>{

	private List<Riwayat> dataList;
	DataRiwayatFragment fragment;

	public AdapterRiwayat(DataRiwayatFragment fragment, List<Riwayat> dataList){
		this.dataList = dataList;
		this.fragment = fragment;
		Log.d(getClass().toString(), "Riwayat list: " + dataList.size());
	}

	@Override
	public AdapterRiwayat.ViewHolderData onCreateViewHolder(ViewGroup p1, int p2){
		LayoutInflater inflater = LayoutInflater.from(p1.getContext());
		View view = inflater.inflate(R.layout.card_riwayat, p1, false);

		return new ViewHolderData(view);
	}

	@Override
	public void onBindViewHolder(AdapterRiwayat.ViewHolderData holder, final int position){
		holder.txtTitle.setText("Riwayat "+(position+1));
		holder.txtNama.setText("Nama : "+dataList.get(position).getNama());
		holder.txtNik.setText("NIK : " + dataList.get(position).getNikIbu());
		holder.txtMetodeKB.setText("Metode KB : "+dataList.get(position).getMetodeKB());
		holder.txtKomplikasiKB.setText("Komplikasi KB : "+dataList.get(position).getKomplikasiKB());
		holder.txtKegagalanKB.setText("Kegagalan KB : "+dataList.get(position).getKegagalanKB());
		holder.txtHamilKe.setText("Hamil Ke : "+dataList.get(position).getHamilKe());
		holder.txtTanggal.setText("Tanggal : " + dataList.get(position).getTanggal());
		holder.btnHapus.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View p1){
					AlertDialog.Builder alert = new AlertDialog.Builder(fragment.getActivity());
					alert.setTitle("Hapus Riwayat");
					alert.setMessage("Yakin ingin hapus ?");
					alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2){
								AndroidNetworking.post(ConstantVariables.API + "hapus_riwayat.php")
									.addBodyParameter("id", dataList.get(position).getId())
									.setPriority(Priority.MEDIUM)
									.build()
									.getAsJSONObject((new JSONObjectRequestListener(){
																		 @Override
																		 public void onResponse(JSONObject p1){
																			 try{
																				 String message = p1.getString("message");
																				 fragment.restoreData();
																				 Toast.makeText(fragment.activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
																			 }catch(JSONException e){
																				 Toast.makeText(fragment.activity.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();			
																			 }
																		 }

																		 @Override
																		 public void onError(ANError p1){
																			 Toast.makeText(fragment.activity.getApplicationContext(), p1.getMessage(), Toast.LENGTH_LONG).show();
																		 }
																	 }));
							}
						});
					alert.setNegativeButton(android.R.string.no, null);
					alert.show();
				}
			});
	}

	@Override
	public int getItemCount(){
		return (dataList != null) ?dataList.size() : 0;
	}

	class ViewHolderData extends RecyclerView.ViewHolder{

		private TextView txtTitle;
		private TextView txtNama;
		private TextView txtNik;
		private TextView txtMetodeKB;
		private TextView txtKomplikasiKB;
		private TextView txtKegagalanKB;
		private TextView txtHamilKe;
		private TextView txtTanggal;

		private Button btnHapus;
		private CardView card_riwayat;

		ViewHolderData(View itemView){
			super(itemView);
			txtTitle = itemView.findViewById(R.id.card_riwayat_title);
			txtNama = itemView.findViewById(R.id.card_riwayat_nama);
			txtNik = itemView.findViewById(R.id.card_riwayat_nik);
			txtMetodeKB = itemView.findViewById(R.id.card_riwayat_metode_kb);
			txtKomplikasiKB = itemView.findViewById(R.id.card_riwayat_komplikasi_kb);
			txtKegagalanKB = itemView.findViewById(R.id.card_riwayat_kegagalan_kb);
			txtHamilKe = itemView.findViewById(R.id.card_riwayat_hamil_ke);
			txtTanggal = itemView.findViewById(R.id.card_riwayat_tanggal);
			btnHapus = itemView.findViewById(R.id.card_riwayat_hapus);
			


			card_riwayat = itemView.findViewById(R.id.card_riwayat);
		}
	}
}

