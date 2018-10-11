<?php


    $idVehicule=$_GET['idVehicule'];
    $idClient=$_GET['idClient'];
    $immatriculation=$_GET['immatriculation'];
    $modele=$_GET['modele'];


    
    include("VehiculeDataGateway.php");
    $dbContext= new VehiculeDataGateway();
    $Vehicule=new Vehicule(Null,$idClient,$immatriculation,$modele);

    $rowAdded=$dbContext->add($Vehicule);
 
    echo $rowAdded;  
?>