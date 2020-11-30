<?php
require_once 'conexion.php';
$sql = "SELECT * FROM estados";
if(!$conn->query($sql)){
	echo "Error";
}
else{
	$return_arr['estado'] = array();
	$result = $conn->query($sql);
	if($result->num_rows > 0){
		
		while($row = $result->fetch_array()){
			array_push($return_arr['estado'], array(
				'id_estado'=>utf8_encode($row['id_estado']),
				'estado'=>utf8_encode($row['estado'])
			));
		}	
		echo json_encode($return_arr);
	}
}
		

