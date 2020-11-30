<?php

       $servername = "localhost";
        $username = "chilaqu3_cap_root";
        $password = "5ZdPKw!Zszc^";
        $bdname = "chilaqu3_cap";
// Create connection

$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT id_estado, estado FROM estados";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "id: " . $row["id"_estado];
  }
} else {
  echo "0 results";
}
$conn->close();
?>