<?php
require 'conexion.php';
$sql = "SELECT * FROM profesion";
if(!$conexion->query($sql)){
	echo "Error";
}
else{	echo ¨done";
	$result = $conexion->query($sql);
	if($result->num_rows > 0){
		echo ¨done";
		$return_arr['profesion'] = array();
		while($row = $result->fetch_array()){
			array_push($return_arr['profesion'], array(
				'id_profesion'=>$row['id_profesion'],
				'profesion'=>$row['profesion']
			));
		}	
		echo json_encode($return_arr);
		echo ¨done";
	}
}