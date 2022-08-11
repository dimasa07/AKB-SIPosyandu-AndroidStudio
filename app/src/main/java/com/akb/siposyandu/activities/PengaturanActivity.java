package com.akb.siposyandu.activities;
import android.support.v7.app.*;
import android.os.*;
import com.akb.siposyandu.R;
import android.widget.*;
import com.akb.siposyandu.constants.*;
import android.view.*;

public class PengaturanActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pengaturan);
		
		final EditText edtAPI = findViewById(R.id.pengaturan_edt_api);
		edtAPI.setText(ConstantVariables.API);
		Button btnSimpan = findViewById(R.id.pengaturan_btn_simpan);
		btnSimpan.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1){
					ConstantVariables.API = edtAPI.getText().toString();
					finish();
				}
			});
	}
	
}
