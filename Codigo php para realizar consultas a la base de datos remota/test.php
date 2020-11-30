<?php
$servername = "localhost";
$username = "chilaqu3_cap_root";
$password = "5ZdPKw!Zszc^";
$dbname = "chilaqu3_cap";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM usuarios";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "id: " . $row["id_usuario"]. " - Name: " . $row["nombres"]. " " . $row["AP"]. "<br>";
  }
} else {
  echo "0 results";
}
$conn->close();