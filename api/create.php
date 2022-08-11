<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	//include file connect.php untuk menyambungkan file create.php dengan database
	include "connect.php";
	//inisialisasi variabel yang akan ditampung dan diolah dengan query
	//$id = $_POST['id_mahasiswa'];
	$nama = $_POST['nama_mahasiswa'];
	$nim = $_POST['nim_mahasiswa'];
	$kelas = $_POST['kelas_mahasiswa'];
	//inisialiasi query INSERT	
	$query = "INSERT INTO tbl_mahasiswa(nama_mahasiswa,nim_mahasiswa,kelas_mahasiswa) 
	VALUES('$nama','$nim','$kelas')";
	//pemanggilan fungsi mysqli_query untuk mengirimkan perintah sesuai parameter yang diisi
	$sql = mysqli_query($connect, $query);
	$data = $nim." ".$nama." ".$kelas;
	//pengkondisian saat fungsi mysqli_query berhasil atau gagal dieksekusi
	if($sql){
		 $response["value"] = 1;
		 $response["message"] = "Sukses tambah data :".$data ;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}else{
		 $response["value"] = 0;
		 $response["message"] = "Gagal tambah data : ".$data;
		 echo json_encode($response); //merubah respone menjadi JsonObject
	}
}

?>