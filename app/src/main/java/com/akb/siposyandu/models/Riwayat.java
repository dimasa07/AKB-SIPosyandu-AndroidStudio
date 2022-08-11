package com.akb.siposyandu.models;

public class Riwayat
{
	private String id;
	private String nama;
	private String nikIbu;
	private String metodeKB;
	private String komplikasiKB;
	private String kegagalanKB;
	private String hamilKe;
	private String tanggal;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNikIbu(String nikIbu){
		this.nikIbu = nikIbu;
	}

	public String getNikIbu(){
		return nikIbu;
	}

	public void setMetodeKB(String metodeKB){
		this.metodeKB = metodeKB;
	}

	public String getMetodeKB(){
		return metodeKB;
	}

	public void setKomplikasiKB(String komplikasiKB){
		this.komplikasiKB = komplikasiKB;
	}

	public String getKomplikasiKB(){
		return komplikasiKB;
	}

	public void setKegagalanKB(String kegagalanKB){
		this.kegagalanKB = kegagalanKB;
	}

	public String getKegagalanKB(){
		return kegagalanKB;
	}

	public void setHamilKe(String hamilKe){
		this.hamilKe = hamilKe;
	}

	public String getHamilKe(){
		return hamilKe;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}}
