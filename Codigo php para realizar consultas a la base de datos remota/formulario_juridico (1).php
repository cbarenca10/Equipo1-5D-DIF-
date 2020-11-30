<?php

require  ("conexion.php");

// RECIBE LOS DATOS DE LA APP

$procedencia = $_POST["procedencia"];
$contacto_alternativo = $_POST["contacto_alternativo"];
$observaciones_relevantes = $_POST["observaciones_relevantes"];
$descripcion_detallada = $_POST["descripcion_detallada"];
$proceso_actual_iniciado = $_POST["proceso_actual_iniciado"];



// VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
if(empty($procedencia) || empty($contacto_alternativo) || empty($observaciones_relevantes) || empty($descripcion_detallada) || empty($proceso_actual_iniciado)) {

    // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
    //echo "Se deben llenar los dos campos";
    echo "ERROR 1";

} else {

    // CREAMOS LA CONSULTA
    $sql = "INSERT INTO juridico(id_atencion,usuario,caso,beneficiario,procedencia,observaciones_relevantes,contacto_alternativo,proceso_actual_iniciado,descripcion_detallada) VALUES ('1','1','1','1',$procedencia','$observaciones_relevantes','$contacto_alternativo','$proceso_actual_iniciado','$descripcion_detallada')";



    $query = $mysqli->query($sql);

    if($query === TRUE) {

        echo "MENSAJE";

    }
    
}

?>