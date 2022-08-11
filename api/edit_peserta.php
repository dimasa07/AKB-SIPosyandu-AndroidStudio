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
	
	$query = "UPDATE ibu SET nik_ibu='$nik_ibu',nama='$nama',nama_suami='$nama_suami',no_telepon='$no_telepon',
	alamat='$alamat',tanggal_lahir='$tanggal_lahir',gol_darah='$gol_darah',status='$status' WHERE nik_ibu='$nik_ibu'";
	$query1 = "UPDATE akun SET nama='$nama' WHERE nik_ibu='$nik_ibu'";
	$sql = mysqli_query($connect, $query);
	$sql1 = mysqli_query($connect, $query1);
	if($sql && $sql1){
		 $response["value"] = 1;
		 $response["message"] = "Sukses edit " ;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal edit ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>