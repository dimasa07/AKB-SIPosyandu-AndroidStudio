<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	include "connect.php";
	
	$id = $_POST['id'];
	
	$query = "DELETE FROM kegiatan WHERE id_kegiatan=$id";
	
	$sql = mysqli_query($connect, $query);
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses hapus kegiatan ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal hapus kegiatan ";
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>