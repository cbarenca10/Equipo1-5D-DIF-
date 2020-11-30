<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $id = $_POST['id_usuario'];

    require_once 'conexion.php';

    $sql = "SELECT * FROM usuarios WHERE id_usuario='$id' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['read'] = array();

    if( mysqli_num_rows($response) === 1 ) {
        
        if ($row = mysqli_fetch_assoc($response)) {
 
             $h['nombres']        = $row['nombres'] ;
             $h['AP']       = $row['AP'] ;
             $h['AM']       = $row['AM'] ;
             $h['rol']       = $row['rol'] ;
             $h['profesion']       = $row['profesion'] ;
 
             array_push($result["read"], $h);
 
             $result["success"] = "1";
             echo json_encode($result);
        }
 
   }
 
 }else {
 
     $result["success"] = "0";
     $result["message"] = "Error!";
     echo json_encode($result);
 
     mysqli_close($conn);
 }
 
 ?>