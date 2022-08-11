package com.akb.siposyandu.models;

public class Peserta
{
	private String nik;
	private String nama;
	private String namaSuami;
	private String status;
	private String tanggalLahir;
	private String golDarah;
	private String noTelepon;
	private String alamat;

	public void setNoTelepon(String noTelepon){
		this.noTelepon = noTelepon;
	}

	public String getNoTelepon(){
		return noTelepon;
	}

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

	public void setNamaSuami(String namaSuami){
		this.namaSuami = namaSuami;
	}

	public String getNamaSuami(){
		return namaSuami;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTanggalLahir(String tanggalLahir){
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahir(){
		return tanggalLahir;
	}

	public void setGolDarah(String golDarah){
		this.golDarah = golDarah;
	}

	public String getGolDarah(){
		return golDarah;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}}
