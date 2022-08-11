<?php
include "connect.php";

$nik_ibu = $_GET['nik_ibu'];

$data = mysqli_query($connect,"SELECT * FROM ibu where nik_ibu='$nik_ibu'");
$cek = mysqli_num_rows($data);

if($cek > 0){
	while($row = mysqli_fetch_array($data)){
    
    	$profil['nik_ibu']=$row[0]; 
    	$profil['nama']=$row[1]; 
    	$profil['nama_suami']=$row[2];
		$profil['status']=$row[3];
		$profil['tanggal_lahir']=$row[4];
		$profil['gol_darah']=$row[5];
		$profil['no_telepon']=$row[6];
		$profil['alamat']=$row[7];
	}

	echo json_encode($profil);
}
?>