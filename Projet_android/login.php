<?php
$db="Projet_android";
$user = $_POST["user"];
$pass = $_POST["pass"];
$host = "localhost";
$conn = mysqli_connect($host,"root","",$db);
if ($conn){
    $q = "select * from users where login like '$user' and password like '$pass'";
    $resulat = mysqli_query($conn,$q);
    if (mysqli_num_rows($resulat)>0){
      echo "sucess";
    } else {
        echo "echec";
    }mysqli_close($conn);
  } else {
      echo "Probleme de connexion";
  }


?>