<?php

    require("conexion.php");

    // RECIBE LOS DATOS DE LA APP
    
	$nombres = $_POST["nombres"];
	$AP = $_POST["AP"];
	$AM = $_POST["AM"];
	$correo = $_POST["correo"];
	$password = $_POST["password"];
	$direccion = $_POST["direccion"];
	$municipio = $_POST["municipio"];
	$rol = $_POST["rol"];
	$cp = $_POST["cp"];
	$tel = $_POST["tel"];
	$estado = $_POST["estado"];
	$cedulaprof = $_POST["cedulaprof"];
	$profesion = $_POST["profesion"];
	$password = password_hash($password, PASSWORD_BCRYPT);

	//RecolocarPOstPruebas
	//$rol = 1;
	//$estado = 1;
	//$profesion = 1;

   	//$rol = (int)$rol;
	//$profesion = (int)$profesion;
	//$estado = (int)$estado;

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($nombres) || empty($AP) || empty($AM)|| empty($correo)|| empty($password)||  empty($direccion)|| empty($municipio)|| empty($cp)|| empty($tel)|| empty($cedulaprof)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo "ERROR 1";

    } else {

        // CREAMOS LA CONSULTA
        $sql = "INSERT INTO usuarios(nombres,AP,AM,correo,password,rol,direccion,municipio,estado,cp,tel,profesion,cedulaprof) VALUES ('$nombres','$AP','$AM','$correo','$password','$rol','$direccion','$municipio','$estado','$cp','$tel','$profesion','$cedulaprof')";

        $query = $mysqli->query($sql);

        if($query === TRUE) {

            echo "MENSAJE";

        }
        
    }

?>