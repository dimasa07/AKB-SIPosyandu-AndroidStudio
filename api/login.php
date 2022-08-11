<?php
include "connect.php";

$username = $_POST['username'];
$password = $_POST['password'];

$data = mysqli_query($connect,"SELECT * FROM akun where username='$username' AND password='$password'");
$cek = mysqli_num_rows($data);

$response['request'] = "login";


if($cek > 0){
	while($row = mysqli_fetch_array($data)){
    
    	$akun['username']=$row[0]; 
    	$akun['password']=$row[1]; 
    	$akun['level']=$row[2];
		if($row[2]=="PESERTA"){
			$akun['id'] = $row[4];
		}else if($row[2]=="KADER"){
			$akun['id'] = $row[3];
		}
		$akun['nama'] = $row[5];
	
}
	$response['message'] = "Sukses login";
	$response['status'] = "sukses";
	$response['value'] = $akun;
	echo json_encode($response);
}else{
	$response['message'] = "Gagal login: username atau password tidak valid";
	$response['status'] = "gagal";
	echo json_encode($response);
	
}
?>