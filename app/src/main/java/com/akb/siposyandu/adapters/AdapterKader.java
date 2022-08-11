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
import org.json.*;
import com.androidnetworking.interfaces.*;

public class AdapterKader extends RecyclerView.Adapter<AdapterKader.ViewHolderData> implements View.OnClickListener{

	private List<Kader> dataList;
	DataKaderFragment fragment;

	public AdapterKader(DataKaderFragment fragment,List<Kader> dataList){
		this.dataList = dataList;
		this.fragment = fragment;
		Log.d(getClass().toString(),"Kader list: "+dataList.size());
	}

	@Override
	public AdapterKader.ViewHolderData onCreateViewHolder(ViewGroup p1, int p2){
		LayoutInflater inflater = LayoutInflater.from(p1.getContext());
		View view = inflater.inflate(R.layout.card_kader,p1,false);
		view.setOnClickListener(this);
		return new ViewHolderData(view);
	}

	@Override
	public void onBindViewHolder(AdapterKader.ViewHolderData holder, final int position){
		holder.txtTitle.setText("Kader "+(position+1));
		holder.txtNama.setText("Nama : "+dataList.get(position).getNama());
		holder.txtNik.setText("NIK : "+dataList.get(position).getNik());
		holder.txtStatus.setText("Status : "+dataList.get(position).getStatus());
		holder.btnEdit.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1){
					((TambahKaderFragment)fragment.activity.fragments.get(TambahKaderFragment.class)).setKader(dataList.get(position),"EDIT");
					fragment.activity.setFragment(TambahKaderFragment.class);
				}
			});
		holder.btnHapus.setOnClickListener(new Button.OnClickListener(){
				@Override
				public void onClick(View p1){
					AlertDialog.Builder alert = new AlertDialog.Builder(fragment.getActivity());
					alert.setTitle("Hapus Kader");
					alert.setMessage("Yakin ingin hapus ?");
					alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2){
								AndroidNetworking.post(ConstantVariables.API + "hapus_kader.php")
									.addBodyParameter("nik_kader", dataList.get(position).getNik())
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
					((TambahKaderFragment)fragment.activity.fragments.get(TambahKaderFragment.class)).setKader(dataList.get(position),"VIEW");
					fragment.activity.setFragment(TambahKaderFragment.class);
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
//		((TambahKaderFragment)fragment.activity.fragments.get(TambahKaderFragment.class)).setKader(dataList.get(itemPosition),"VIEW");
//		fragment.activity.setFragment(TambahKaderFragment.class);
//		
	}

	class ViewHolderData extends RecyclerView.ViewHolder{

		private TextView txtTitle;
		private TextView txtNama;
		private TextView txtNik;
		private TextView txtStatus;
		private Button btnEdit,btnHapus,btnLihat;
		private CardView card_kader;

		ViewHolderData(View itemView){
			super(itemView);
			txtTitle = itemView.findViewById(R.id.card_kader_title);
			txtNama = itemView.findViewById(R.id.card_kader_nama);
			txtNik = itemView.findViewById(R.id.card_kader_nik);
			txtStatus = itemView.findViewById(R.id.card_kader_status);
			btnHapus = itemView.findViewById(R.id.card_kader_hapus);
			btnEdit = itemView.findViewById(R.id.card_kader_edit);
			btnLihat = itemView.findViewById(R.id.card_kader_lihat);
			card_kader = itemView.findViewById(R.id.card_kader);
		}
	}
}

