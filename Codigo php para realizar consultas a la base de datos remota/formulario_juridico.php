<?php

require  ("conexion.php");

// RECIBE LOS DATOS DE LA APP

$procedencia = $_POST["procedencia"];
$contacto_alternativo = $_POST["contacto_alternativo"];
$procedencia = $_POST["procedencia"];
$descripcion_detallada = $_POST["descripcion_detallada"];
$proceso_actual_iniciado = $_POST["proceso_actual_iniciado"];



// VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
if(empty($procedencia) || empty($contacto_alternativo) || empty($procedencia || empty($descripcion_detallada) || empty($proceso_actual_iniciado)) {

    // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
    //echo "Se deben llenar los dos campos";
    echo "ERROR 1";

} else {

    // CREAMOS LA CONSULTA
    $sql = "INSERT INTO juridico(procedencia,contacto_alternativo,procedencia,descripcion_detallada,proceso_actual_iniciado) VALUES ('$procedencia','$contacto_alternativo','$procedencia','$descripcion_detallada','$proceso_actual_iniciado')";



    $query = $mysqli->query($sql);

    if($query === TRUE) {

        echo "MENSAJE";

    }
    
}

?>