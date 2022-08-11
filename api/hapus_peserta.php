<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$nik_ibu = $_POST['nik_ibu'];
	
	$query = "DELETE FROM ibu WHERE nik_ibu=$nik_ibu";
	
	$sql = mysqli_query($connect, $query);
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses hapus peserta ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal hapus peserta ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>