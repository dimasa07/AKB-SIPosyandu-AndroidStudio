package com.akb.siposyandu.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.akb.siposyandu.R;

import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import com.akb.siposyandu.R;
import android.view.MenuItem;
import android.content.SharedPreferences;
import com.akb.siposyandu.constants.*;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.NavigationView;
import java.util.HashMap;
import android.support.v4.app.Fragment;
import com.akb.siposyandu.fragments.*;
import android.support.v4.app.ActivityCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.support.v4.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import com.akb.siposyandu.models.*;


public class BerandaActivity extends AppCompatActivity 
implements NavigationView.OnNavigationItemSelectedListener{

	private SharedPreferences pref;
	private SharedPreferences.Editor editPref;
	private DrawerLayout drawer;
	public HashMap<Class<? extends Fragment>, Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		pref = ConstantVariables.APP_PREF;
		editPref = ConstantVariables.EDIT_PREF;
		
		String username = pref.getString("username","guest");
		String nama = pref.getString("nama","Guest");
		String level = pref.getString("level","Guest");
		Toast.makeText(this,"Selamat datang "+nama+" ("+level+")",Toast.LENGTH_LONG).show();

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
			
		inisialisasiFragments();
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setNavigationItemSelectedListener(this);
		navView.getMenu().clear();
		if(level.equals(Level.ADMIN)){
			navView.inflateMenu(R.menu.drawer_menu_admin);
		}else if(level.equals(Level.KADER)){
			navView.inflateMenu(R.menu.drawer_menu_kader);
		}else if(level.equals(Level.PESERTA)){
			navView.inflateMenu(R.menu.drawer_menu_peserta);	
		}
		MenuItem title = navView.getMenu().findItem(R.id.nav_menu_title);
		title.setTitle(nama+" ("+level+")");
		title.setEnabled(false);
		setFragment(WelcomeFragment.class);
		//onNavigationItemSelected(navView.getMenu().getItem(0).getSubMenu().getItem(0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.menu_item_pengaturan:
				startActivity(new Intent(this,PengaturanActivity.class));
				break;
			case R.id.menu_item_logout:
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle("Logout");
				alert.setMessage("Yakin ingin logout ?");
				alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

						@Override
						public void onClick(DialogInterface p1, int p2){
							logout();
						}
					});
				alert.setNegativeButton(android.R.string.no,null);
				alert.show();
				break;
			case R.id.menu_item_keluar:
				onBackPressed();
				break;
		}
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item){
		Class<? extends Fragment> key = null;
		switch(item.getItemId()){
			case R.id.nav_admin_dataKader:
				key = DataKaderFragment.class;
				break;
			case R.id.nav_admin_dataPerserta:
				key = DataPesertaFragment.class;
				break;
			case R.id.nav_admin_kegiatan:
				key = KegiatanFragment.class;
				break;
			case R.id.nav_kader_profil:
				key = ProfilKaderFragment.class;
				break;
			case R.id.nav_peserta_profil:
				key =  ProfilPesertaFragment.class;
				break;
			case R.id.nav_peserta_riwayat:
				String nik = ConstantVariables.APP_PREF.getString("id","");
				String nama = ConstantVariables.APP_PREF.getString("nama","");
				Peserta p = new Peserta();
				p.setNik(nik);
				p.setNama(nama);
				((DataRiwayatFragment)fragments.get(DataRiwayatFragment.class)).setPeserta(p);
				key = DataRiwayatFragment.class;
				break;
		}
		
		if(key != null){
			setFragment(key);
		}
		item.setChecked(true);
		drawer.closeDrawers();
		return true;
	}

	@Override
	public void onBackPressed(){
		if(drawer.isDrawerOpen(GravityCompat.START)){
			drawer.closeDrawer(GravityCompat.START);
		}else{
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Keluar Aplikasi");
			alert.setMessage("Yakin ingin keluar ?");
			alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2){
						keluar();
					}
				});
			alert.setNegativeButton(android.R.string.no,null);
			alert.show();
		}
	}
	
	private void inisialisasiFragments(){
		fragments = new HashMap<>();
		DataKaderFragment dataKaderFragment = new DataKaderFragment(this);
		DataPesertaFragment dataPesertaFragment = new DataPesertaFragment(this);
		DataRiwayatFragment dataRiwayatFragment = new DataRiwayatFragment(this);
		KegiatanFragment kegiatanFragment = new KegiatanFragment(this);
		TambahKegiatanFragment tambahKegiatanFragment = new TambahKegiatanFragment(this);
		TambahKaderFragment tambahKaderFragment = new TambahKaderFragment(this);
		TambahRiwayatFragment tambahRiwayatFragment = new TambahRiwayatFragment(this);
		ProfilPesertaFragment profilPesertaFragment = new ProfilPesertaFragment(this);
		ProfilKaderFragment profilKaderFragment = new ProfilKaderFragment(this);
		EditPesertaFragment editPesertaFragment = new EditPesertaFragment(this);
		WelcomeFragment welcomeFragment = new WelcomeFragment(this);
		
		fragments.put(DataKaderFragment.class, dataKaderFragment);
		fragments.put(DataPesertaFragment.class, dataPesertaFragment);
		fragments.put(KegiatanFragment.class, kegiatanFragment);
		fragments.put(TambahKegiatanFragment.class, tambahKegiatanFragment);
		fragments.put(TambahKaderFragment.class, tambahKaderFragment);
		fragments.put(ProfilPesertaFragment.class,profilPesertaFragment);
		fragments.put(ProfilKaderFragment.class,profilKaderFragment);
		fragments.put(EditPesertaFragment.class,editPesertaFragment);
		fragments.put(WelcomeFragment.class,welcomeFragment);
		fragments.put(DataRiwayatFragment.class,dataRiwayatFragment);
		fragments.put(TambahRiwayatFragment.class,tambahRiwayatFragment);
	}
	
	public void setFragment(Class<? extends Fragment> key){
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction()
			.replace(R.id.beranda_frameLayout,fragments.get(key))
			.commit();
	}

	public void logout(){
		editPref.remove("username");
		editPref.commit();
		startActivity(new Intent(this, LoginActivity.class));
		finish();
	}
	
	public void keluar(){
		super.onBackPressed();
	}

	
}
