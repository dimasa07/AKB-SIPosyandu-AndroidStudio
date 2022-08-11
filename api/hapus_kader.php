<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik_kader = $_POST['nik_kader'];
	
	$query = "DELETE FROM kader WHERE nik_kader=$nik_kader";
	
	$sql = mysqli_query($connect, $query);
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses hapus kader ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal hapus kader ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>