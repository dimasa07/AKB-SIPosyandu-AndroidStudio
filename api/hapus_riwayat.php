<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$id_riwayat = $_POST['id'];
	
	$query = "DELETE FROM riwayat_ibu WHERE id_riwayat=$id_riwayat";
	
	$sql = mysqli_query($connect, $query);
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses hapus riwayat ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal hapus riwayat ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>