<?php

$all_files = glob("uploads/*.*");
$images = array();
 $id = 0 ;
  for ($i=0; $i<count($all_files); $i++)
    {
      $image_name = $all_files[$i];
      $supported_format = array('gif','jpg','jpeg','png');
      $ext = strtolower(pathinfo($image_name, PATHINFO_EXTENSION));
	  
	 
      if (in_array($ext, $supported_format)){
		//echo $image_name."<br>"; //'<img src="'.$image_name .'" alt="'.$image_name.'" />'."<br /><br />";
		$id++;
		array_push($images, array(
			'id'=>"$id",
			'url'=>"$image_name"
		));
      } else {
        continue;
     }
    }
	echo json_encode($images);

?>