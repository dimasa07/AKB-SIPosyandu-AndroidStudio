package com.akb.siposyandu.models;

public class Kader
{
	private String nik;
	private String nama;
	private String jenisKelamin;
	private String noTelepon;
	private String alamat;
	private String status;


	public void setNik(String nik){
		this.nik = nik;
	}

	public String getNik(){
		return nik;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setJenisKelamin(String jenisKelamin){
		this.jenisKelamin = jenisKelamin;
	}

	public String getJenisKelamin(){
		return jenisKelamin;
	}

	public void setNoTelepon(String noTelepon){
		this.noTelepon = noTelepon;
	}

	public String getNoTelepon(){
		return noTelepon;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}}
