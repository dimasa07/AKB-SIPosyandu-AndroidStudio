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
import android.widget.*;
import android.app.*;
import android.content.*;
import com.androidnetworking.interfaces.*;
import org.json.*;

public class AdapterPeserta extends RecyclerView.Adapter<AdapterPeserta.ViewHolderData> implements View.OnClickListener{

	private List<Peserta> dataList;
	DataPesertaFragment fragment;

	public AdapterPeserta(DataPesertaFragment fragment,List<Peserta> dataList){
		this.dataList = dataList;
		this.fragment = fragment;
		Log.d(getClass().toString(),"Peserta list: "+dataList.size());
	}

	@Override
	public AdapterPeserta.ViewHolderData onCreateViewHolder(ViewGroup p1, int p2){
		LayoutInflater inflater = LayoutInflater.from(p1.getContext());
		View view = inflater.inflate(R.layout.card_peserta,p1,false);
		view.setOnClickListener(this);
		return new ViewHolderData(view);
	}

	@Override
	public void onBindViewHolder(AdapterPeserta.ViewHolderData holder, final int position){
		holder.txtTitle.setText("Peserta "+(position+1));
		holder.txtNama.setText("Nama : "+dataList.get(position).getNama());
		holder.txtNik.setText("NIK : "+dataList.get(position).getNik());
		holder.txtNamaSuami.setText("Nama Suami : "+dataList.get(position).getNamaSuami());
		holder.btnHapus.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View p1){
					AlertDialog.Builder alert = new AlertDialog.Builder(fragment.getActivity());
					alert.setTitle("Hapus Peserta");
					alert.setMessage("Yakin ingin hapus ?");
					alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2){
								AndroidNetworking.post(ConstantVariables.API + "hapus_peserta.php")
									.addBodyParameter("nik_ibu", dataList.get(position).getNik())
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
		holder.btnLihat.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1){
					((EditPesertaFragment)fragment.activity.fragments.get(EditPesertaFragment.class)).setPeserta((dataList.get(position)),"VIEW");
					fragment.activity.setFragment(EditPesertaFragment.class);
				}
			});
	}

	@Override
	public int getItemCount(){
		return (dataList != null)?dataList.size() : 0;
	}

	@Override
	public void onClick(View p1){
//		int itemPosition = fragment.recyclerView.getChildAdapterPosition(p1);
//		((EditPesertaFragment)fragment.activity.fragments.get(EditPesertaFragment.class)).setPeserta((dataList.get(itemPosition)),"VIEW");
//		fragment.activity.setFragment(EditPesertaFragment.class);
//		
	}

	class ViewHolderData extends RecyclerView.ViewHolder{

		private TextView txtTitle;
		private TextView txtNik;
		private TextView txtNama;
		private TextView txtNamaSuami;
		private Button btnHapus,btnLihat;
		private CardView card_peserta;

		ViewHolderData(View itemView){
			super(itemView);
			txtTitle = itemView.findViewById(R.id.card_peserta_title);
			txtNik = itemView.findViewById(R.id.card_peserta_nik);
			txtNama = itemView.findViewById(R.id.card_peserta_nama);
			txtNamaSuami = itemView.findViewById(R.id.card_peserta_nama_suami);
			btnHapus = itemView.findViewById(R.id.card_peserta_hapus);
			btnLihat = itemView.findViewById(R.id.card_peserta_lihat);
			card_peserta = itemView.findViewById(R.id.card_peserta);
		}
	}
}

