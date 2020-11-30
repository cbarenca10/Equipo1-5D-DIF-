<?php
    //$correo = $_POST['correo'];
    //$password = $_POST['password'];
	$correo = "mail";
    $password = "1234";

    require_once 'conexion.php';

    $sql = "SELECT * FROM usuarios WHERE correo='$correo' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) > 0 ) {
        
        $row = mysqli_fetch_assoc($response);

        if ( password_verify($password, $row['password']) ) {
            
	    $index['id_usuario'] = utf8_encode($row['id_usuario']);
            $index['nombres'] = utf8_encode($row['nombres']);
	$index['AP'] = utf8_encode($row['AP']);
	$index['AM'] = utf8_encode($row['AM']);
            $index['correo'] = utf8_encode($row['correo']);
	    $index['rol'] = utf8_encode($row['rol']);
	$index['profesion'] = utf8_encode($row['profesion']);	
	


            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }



?>