<?php

$target_dir = "uploads/";
$target_file_name = $target_dir.basename($_FILES["file"]["name"]);
$response = array();

if(isset($_FILES["file"])){
	if(move_uploaded_file($_FILES["file"]["tmp_name"],$target_file_name)){
		$success = true;
		$message = "Uploaded!!!";
	}else{
		$success = false;
		$message = "NOT Uploaded!!! _ Error While Uploading";
	}
}else{
	$success = false;
	$message = "missing field";
}

$response["success"] = $success;
$response["message"] = $message;

echo json_encode($response);
?>