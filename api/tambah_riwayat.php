<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik_ibu = $_POST['nik_ibu'];
	$metode_kb = $_POST['metode_kb'];
	$komplikasi_kb = $_POST['komplikasi_kb'];
	$kegagalan_kb = $_POST['kegagalan_kb'];
	$hamil_ke = $_POST['hamil_ke'];
	$tanggal = $_POST['tanggal'];
	
	$query = "INSERT INTO riwayat_ibu(nik_ibu,metode_kb,komplikasi_kb,kegagalan_kb,hamil_ke,tanggal) 
	VALUES('$nik_ibu','$metode_kb','$komplikasi_kb','$kegagalan_kb','$hamil_ke','$tanggal')";
	
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