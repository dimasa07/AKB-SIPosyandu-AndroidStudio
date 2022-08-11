<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik = $_POST['nik'];
	$nama = $_POST['nama'];
	$jenis_kelamin = $_POST['jenis_kelamin'];
	$no_telepon = $_POST['no_telepon'];
	$alamat = $_POST['alamat'];
	$status = $_POST['status'];
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$query = "INSERT INTO kader(nik_kader,nama,jenis_kelamin,no_telepon,alamat,status) 
	VALUES('$nik','$nama','$jenis_kelamin','$no_telepon','$alamat','$status')";
	$query1 = "INSERT INTO akun(username,password,level,nik_kader,nama) 
	VALUES('$username','$password','KADER','$nik','$nama')";
	$sql = mysqli_query($connect, $query);
	$sql1 = mysqli_query($connect, $query1);
	if($sql && $sql1){
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