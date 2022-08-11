<?php
include "connect.php";//include file connect.php untuk menyambungkan file create.php dengan database

if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	
  $query = "SELECT * FROM kader ORDER BY nama ASC";
$sql = mysqli_query($connect, $query);//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result

  while($row = mysqli_fetch_array($sql)){
	$kader["nik"] = $row[0];
	$kader["nama"] = $row[1];
	$kader["jenis_kelamin"] = $row[2];
	$kader["no_telepon"] = $row[3];
	$kader["alamat"] = $row[4];
	$kader["status"] = $row[5];
    array_push($result, $kader);
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($result); //mengeluarkan data dalam bentuk json
  mysqli_close($connect); 
//tutup koneksi
}
?>