<?php
include "connect.php";//include file connect.php untuk menyambungkan file create.php dengan database

if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	
  $query = "SELECT * FROM kegiatan ORDER BY nama ASC";
$sql = mysqli_query($connect, $query);//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result

  while($row = mysqli_fetch_array($sql)){
	$kegiatan["id_kegiatan"] = $row[0];
	$kegiatan["nama"] = $row[1];
	$kegiatan["deskripsi"] = $row[2];
	$kegiatan["tanggal"] = $row[3];
    array_push($result, $kegiatan);
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($result); //mengeluarkan data dalam bentuk json
  mysqli_close($connect); 
//tutup koneksi
}
?>