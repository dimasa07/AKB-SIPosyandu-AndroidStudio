package com.akb.siposyandu.models;

public class Kegiatan
{
	private String idKegiatan;
	private String nama;
	private String deskripsi;
	private String tanggal;

	public void setIdKegiatan(String idKegiatan){
		this.idKegiatan = idKegiatan;
	}

	public String getIdKegiatan(){
		return idKegiatan;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}}
