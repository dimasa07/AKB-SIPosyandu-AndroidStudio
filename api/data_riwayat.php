<?php
include "connect.php";//include file connect.php untuk menyambungkan file create.php dengan database

if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	
	$nik_ibu = $_GET["nik_ibu"];
  $query = "SELECT * FROM riwayat_ibu WHERE nik_ibu='$nik_ibu'";
$sql = mysqli_query($connect, $query);//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result

  while($row = mysqli_fetch_array($sql)){
	$riwayat["id_riwayat"] = $row[0];
	$riwayat["nik_ibu"] = $row[1];
	$riwayat["metode_kb"] = $row[2];
	$riwayat["komplikasi_kb"] = $row[3];
	$riwayat["kegagalan_kb"] = $row[4];
	$riwayat["hamil_ke"] = $row[5];
	$riwayat["tanggal"] = $row[6];
    array_push($result, $riwayat);
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($result); //mengeluarkan data dalam bentuk json
  mysqli_close($connect); 
//tutup koneksi
}
?>