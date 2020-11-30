<?php

    // VARIABLES QUE ALMACENAN LA CONEXION A LA BASE DE DATOS
    $mysqli = new mysqli(
        "localhost",
        "chilaqu3_cap_root",
        "5ZdPKw!Zszc^",
        "chilaqu3_labuena"
    );
	$conn = mysqli_connect("localhost", "chilaqu3_cap_root", "5ZdPKw!Zszc^", "chilaqu3_labuena");

    // COMPROBAMOS LA CONEXION
    if($mysqli->connect_errno) {
        die("Fallo la conexion");
    } else {
        // echo "Conexion exitosa";
    }

?>