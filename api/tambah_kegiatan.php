<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nama = $_POST['nama'];
	$deskripsi = $_POST['deskripsi'];
	$tanggal = $_POST['tanggal'];
	
	$query = "INSERT INTO kegiatan(nama,deskripsi,tanggal) 
	VALUES('$nama','$deskripsi','$tanggal')";
	
	$sql = mysqli_query($connect, $query);
	
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses tambah data " ;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal tambah data ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>