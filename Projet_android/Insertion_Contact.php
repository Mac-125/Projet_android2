<?php
$db="Projet_android";
$Nom = $_POST["Nom"];
$Adresse = $_POST["Adresse"];
$Tel1 = $_POST["Tel1"];
$Tel2 = $_POST["Tel2"];
$Entreprise = $_POST["Entreprise"];
$host = "localhost";
$conn = mysqli_connect($host,"root","",$db);
if ($conn){
    $q = "insert into Contact (Nom,Adresse,Tel1,Tel2,Entreprise) values ('Nom','Adresse','Tel1','Tel2','Entreprise')";
    if (mysqli_query($conn,$q)) {

      echo "sucess";

    } else {
        echo "echec";
    }mysqli_close($conn);
  } else {
      echo "Probleme de connexion";
  }


?>

