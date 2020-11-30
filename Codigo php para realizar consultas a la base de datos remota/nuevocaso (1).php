<?php

require  ("conexion.php");

// RECIBE LOS DATOS DE LA APP

$fecha_apertura = $_POST["fecha_apertura"];
$descripcion_general = $_POST["descripcion_general"];
$estado = $_POST["estado"];




// VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
if(empty($fecha_apertura) || empty($descripcion_general) || empty($estado)) {

    // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
    //echo "Se deben llenar los dos campos";
    echo "ERROR 1";

} else {

    // CREAMOS LA CONSULTA
    $sql = "INSERT INTO casos(fecha_apertura,descripcion_general,estado) VALUES ('$fecha_apertura','$descripcion_general','$estado')";

    $query = $mysqli->query($sql);

    if($query === TRUE) {

        echo "MENSAJE";

    }
    
}

?>