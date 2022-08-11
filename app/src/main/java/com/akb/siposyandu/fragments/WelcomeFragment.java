package com.akb.siposyandu.fragments;

import com.akb.siposyandu.activities.*;
import android.support.v4.app.*;
import android.view.*;
import android.os.*;
import com.akb.siposyandu.R;
import com.akb.siposyandu.constants.*;
import android.widget.*;

public class WelcomeFragment extends Fragment
{
	
	public WelcomeFragment(BerandaActivity activity){
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_welcome,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		String nama =ConstantVariables.APP_PREF.getString("nama","");
		String level =ConstantVariables.APP_PREF.getString("level","");
		TextView txtTitle = view.findViewById(R.id.fragment_welcome_title);
		txtTitle.setText("Welcome "+nama+" ("+level+")");
	}

	@Override
	public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
	}
	
	
}
