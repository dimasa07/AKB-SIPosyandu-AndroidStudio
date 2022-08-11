<?php
include "connect.php";

$nik_kader = $_GET['nik_kader'];

$data = mysqli_query($connect,"SELECT * FROM kader where nik_kader='$nik_kader'");
$cek = mysqli_num_rows($data);

if($cek > 0){
	while($row = mysqli_fetch_array($data)){
    
    	$profil['nik_kader']=$row[0]; 
    	$profil['nama']=$row[1]; 
    	$profil['jenis_kelamin']=$row[2];
		$profil['no_telepon']=$row[3];
		$profil['alamat']=$row[4];
		$profil['status']=$row[5];
	}

	echo json_encode($profil);
}
?>