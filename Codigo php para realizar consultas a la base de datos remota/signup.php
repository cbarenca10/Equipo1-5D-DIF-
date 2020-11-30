<?php

require "conexion.php";

   

$nombres = $_POST["identifier_name"];
$AP = $_POST["identifier_AP"];
$AM = $_POST["identifier_AM"];
$correo = $_POST["identifier_correo"];
$password = $_POST["identifier_password"];
$rol = $_POST["identifier_rol"];
$direccion = $_POST["identifier_direccion"];
$municipio = $_POST["identifier_municipio"];
$estado = $_POST["identifier_estado"];
$cp = $_POST["identifier_cp"];
$tel = $_POST["identifier_tel"];
$profesion = $_POST["identifier_profesion"];
$cedulaprof = $_POST["identifier_cedulaprof"];
//HASH de password
$passHash = password_hash($password, PASSWORD_BCRYPT);


//test variables

//$nombres = "Test";
//$AP = "Test1";
//$AM = "Test2";
//$correo = "Test3@correo.com";
//$password = "1234";
//$rol = "1";



$query = "INSERT INTO usuarios(nombres,AP,AM,correo,password,rol,direccion,municipio,estado,cp,tel,profesion,cedulaprof) VALUES ('$nombres','$AP','$AM','$correo','$passHash','$rol','$direccion','$municipio','$estado','$cp','$tel','$profesion','$cedulaprof')";

if(mysqli_query($conexion,$query)){
	//echo "<h2>Información Correctamente Guardada</h2>";
}
else{
	//echo "<h2>Error en Registro :(.</h2>";
}



?>