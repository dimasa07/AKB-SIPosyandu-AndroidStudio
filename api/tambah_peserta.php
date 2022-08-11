<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik_ibu = $_POST['nik_ibu'];
	$nama = $_POST['nama'];
	$nama_suami = $_POST['nama_suami'];
	$no_telepon = $_POST['no_telepon'];
	$alamat = $_POST['alamat'];
	$tanggal_lahir = $_POST['tanggal_lahir'];
	$gol_darah = $_POST['gol_darah'];
	$status = $_POST['status'];
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$query = "INSERT INTO ibu(nik_ibu,nama,nama_suami,no_telepon,alamat,tanggal_lahir,gol_darah,status) 
	VALUES('$nik_ibu','$nama','$nama_suami','$no_telepon','$alamat','$tanggal_lahir','$gol_darah','$status')";
	$query1 = "INSERT INTO akun(username,password,level,nik_ibu,nama) 
	VALUES('$username','$password','PESERTA','$nik_ibu','$nama')";
	$sql = mysqli_query($connect, $query);
	$sql1 = mysqli_query($connect, $query1);
	
	if($sql && $sql1){
		 $response["value"] = 1;
		 $response["message"] = "Sukses daftar " ;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal daftar ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>