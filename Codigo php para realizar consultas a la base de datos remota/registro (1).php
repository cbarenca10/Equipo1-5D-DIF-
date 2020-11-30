<?php
$host_database = 'mysql:dbname=relacional;host=localhost';
$username = "root";
$password = "12345";

 try{ $pdo = new PDO($host_database,$username,$password); }
  catch(PDOException $e){ echo 'Error: ' . $e->getMessage(); }

 if ($_SERVER['REQUEST_METHOD'] == 'POST'){

 $nombre = $_POST['nombre'];
 $Curso = $_POST['id_cursos'];
 //Recibimos los valores de los cursos seleccionados
 $sentencia  = $pdo->prepare( 'INSERT INTO estudiante (id_estudiante,nombre) VALUES (null, :nombre)' );
 $sentencia ->execute(array( ':nombre' => $nombre, )); 
//Después del insert consultamos el id insertado:
 $sentencia  = $pdo->prepare("SELECT @@identity AS id");
 $sentencia ->execute();
 $resultado = $sentencia ->fetchAll();
 $id = 0;
  foreach ($resultado as $row) {
         $id = $row["id"];
     }

//Ya tenemos el último id insertado, ahora hacemos el insert en la tabla students_courses

 $sentencia = $pdo->prepare( 'INSERT INTO cursos (id_cursos,id_estudiante) VALUES (:id_cursos, :id_estudiante)' );
 $sentencia ->bindParam(':id_cursos', $idCurso);
 $sentencia ->bindParam(':id_estudiante', $id); //Variable del último id registrado en la tabla de students

//Insertamos dependiendo de los id's elegidos
  foreach ($Curso as $option_value)