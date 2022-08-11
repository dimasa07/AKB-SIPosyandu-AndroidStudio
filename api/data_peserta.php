<?php
include "connect.php";//include file connect.php untuk menyambungkan file create.php dengan database

if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	
  $query = "SELECT * FROM ibu ORDER BY nama ASC";
$sql = mysqli_query($connect, $query);//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result

  while($row = mysqli_fetch_array($sql)){
	$peserta["nik"] = $row[0];
	$peserta["nama"] = $row[1];
	$peserta["nama_suami"] = $row[2];
	$peserta["status"] = $row[3];
	$peserta["tanggal_lahir"] = $row[4];
	$peserta["gol_darah"] = $row[5];
	$peserta["no_telepon"] = $row[6];
	$peserta["alamat"] = $row[7];
    array_push($result, $peserta);
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($result); //mengeluarkan data dalam bentuk json
  mysqli_close($connect); 
//tutup koneksi
}
?>