<?php
include "connect.php";

$username = $_POST['username'];
$password = $_POST['password'];
$password1 = md5($password);

$data = mysqli_query($connect,"SELECT * FROM tbl_mahasiswa where nim_mahasiswa='$username'");
$cek = mysqli_num_rows($data);

$response['type'] = "register";

if($cek > 0){
	$response['message'] = "Gagal register: username sudah terdaftar";
	$response['status'] = "gagal";
	echo json_encode($response);
}else{
	//inisialiasi query INSERT	
	$query = "INSERT INTO tbl_mahasiswa(nim_mahasiswa,nama_mahasiswa) VALUES('$username','$password1')";
	$sql = mysqli_query($connect, $query);
	//pengkondisian saat fungsi mysqli_query berhasil atau gagal dieksekusi
	if($sql){
		$response['message'] = "Sukses register";
		$response['status'] = "sukses";
		$response['value'] = array(
			'username' => $username,
			'password' => $password
		);
		echo json_encode($response);
	}else{
		$response['message'] = "Gagal register: SQL error";
		$response['status'] = "gagal";
		echo json_encode($response);
	}

}
?>