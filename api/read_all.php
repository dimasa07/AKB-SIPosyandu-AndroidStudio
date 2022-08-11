<?php
include "connect.php";//include file connect.php untuk menyambungkan file create.php dengan database

if($_SERVER['REQUEST_METHOD']=='GET') {

//inisialiasi query READ	
  $query = "SELECT * FROM tbl_mahasiswa ORDER BY nama_mahasiswa ASC";
$sql = mysqli_query($connect, $query);//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
  $result = array(); //inisialisasi array dengan variabel $result

  while($row = mysqli_fetch_array($sql)){
    array_push($result, array(
    	'id_mahasiswa'=>$row[0], 
    	'nim_mahasiswa'=>$row[1], 
    	'nama_mahasiswa'=>$row[2], 
    	'kelas_mahasiswa'=>$row[3]));
  }//melakukan pengulangan dengan while untuk membaca seluruh data pada tabel mahasiswa, dan disimpan didalam row/baris. urutan row harus sesuai urutan pada database
  echo json_encode($result); //mengeluarkan data dalam bentuk json
  mysqli_close($connect); 
//tutup koneksi
}
?>