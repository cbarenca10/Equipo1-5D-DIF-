<?php
require_once 'conexion.php';
$sql = "SELECT * FROM roles";
if(!$conexion->query($sql)){
	echo "Error";
}
else{
	$result = $conexion->query($sql);
	if($result->num_rows > 0){
		$return_arr['roles'] = array();
		while($row = $result->fetch_array()){
			array_push($return_arr['roles'], array(
				'id_rol'=>$row['id_rol'],
				'rol'=>$row['rol']
			));
		}	
		echo json_encode($return_arr);
	}
}
		