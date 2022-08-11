<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik = $_POST['nik'];
	$nama = $_POST['nama'];
	$jenis_kelamin = $_POST['jenis_kelamin'];
	$no_telepon = $_POST['no_telepon'];
	$alamat = $_POST['alamat'];
	$status = $_POST['status'];
	
	$query = "UPDATE kader SET nik_kader='$nik',nama='$nama',jenis_kelamin='$jenis_kelamin',no_telepon='$no_telepon',
	alamat='$alamat',status='$status' WHERE nik_kader='$nik'";
	$query1 = "UPDATE akun SET nama='$nama' WHERE nik_kader='$nik'";
	$sql = mysqli_query($connect, $query);
	$sql1 = mysqli_query($connect, $query1);
	if($sql&& $sql1){
		 $response["value"] = 1;
		 $response["message"] = "Sukses edit data " ;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal edit data ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>