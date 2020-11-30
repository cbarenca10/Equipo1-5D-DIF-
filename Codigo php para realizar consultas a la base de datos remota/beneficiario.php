<?php

require("conexion.php");

// RECIBE LOS DATOS DE LA APP

$nombres = $_POST["nombres"];
$AP = $_POST["AP"];
$AM = $_POST["AM"];
$curp = $_POST["curp"];
$telefono = $_POST["telefono"];
$estado = $_POST["estado"];
$municipio = $_POST["municipio"];
$domicilio = $_POST["domicilio"];
$cp = $_POST["cp"];
$sexo = $_POST["sexo"];
$fecha_nacimiento = $_POST["fecha_nacimiento"];
$lugar_nacimiento = $_POST["lugar_nacimiento"];
$fecha_registro = $_POST["fecha_registro"];
$estado_civil = $_POST["estado_civil"];
$escolaridad = $_POST["escolaridad"];
$gradoescolar = $_POST["gradoescolar"];
$nombre_escuela = $_POST["nombre_escuela"];
$ocupacion = $_POST["ocupacion"];





// VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
if(empty($nombres) || empty($AP) || empty($AM)|| empty($curp)|| empty($telefono)||  empty($estado)|| empty($municipio)|| empty($domicilio)|| empty($cp)|| empty($sexo)|| empty($fecha_nacimiento)|| empty($lugar_nacimiento) || empty($fecha_registro) || empty($estado_civil)|| empty($escolaridad)|| empty($gradoescolar)|| empty($nombre_escuela)|| empty($ocupacion)) {

    // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
    //echo "Se deben llenar los dos campos";
    echo "ERROR 1";

} else {

    // CREAMOS LA CONSULTA
    $sql = "INSERT INTO beneficiarios(nombres,AP,AM,curp,telefono,estado,municipio,domicilio,cp,sexo,fecha_nacimiento,lugar_nacimiento,fecha_registro,estado_civil,escolaridad,gradoescolar,nombre_escuela,ocupacion) VALUES ('$nombres','$AP','$AM','$curp','$telefono','$estado','$municipio','$domicilio','$cp','$sexo','$fecha_nacimiento','$lugar_nacimiento','$fecha_registro','$estado_civil','$escolaridad','$gradoescolar','$nombre_escuela','$ocupacion')";

    $query = $mysqli->query($sql);

    if($query === TRUE) {

        echo "MENSAJE";

    }
    
}

?>