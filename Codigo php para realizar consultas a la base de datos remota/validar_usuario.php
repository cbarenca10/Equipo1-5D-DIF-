<?php
session_start();
include 'conexion.php';

$correo=$_POST["correo"];
$password=$_POST["password"];


$query = "SELECT * FROM usuarios WHERE correo = '$correo'";

$tbl = mysqli_query($conexion,$query);

//check the result
if(mysqli_num_rows($tbl)>0){
	$row = mysqli_fetch_assoc($tbl);
	$password_hash = $row['password'];

	if(password_verify($password,$password_hash))
	{
	    	
		$nombres = $row["nombres"];
		$AP = $row["AP"];
		$AM = $row["AM"];
		$correo = $row["correo"];
		$rol = $row["rol"];
		$direccion = $row["direccion"];
		$municipio = $row["municipio"];
		$estado = $row["estado"];
		$cp = $row["cp"];
		$tel = $row["tel"];
		$profesion = $row["profesion"];
		

		echo "true,".$nombres.",".$AP.",".$AM.",".$correo.",".$rol.",".$direccion.",".$municipio.",".$estado.",".$cp.",".$tel.",".$profesion;
	}
	else{
    	echo "false";
    }
}
else{
	echo "false";
}

?>