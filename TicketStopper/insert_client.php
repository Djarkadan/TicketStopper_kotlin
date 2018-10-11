<?php


    $nom=$_GET['nom'];
    $prenom=$_GET['prenom'];
    $email=$_GET['email'];
    $tel=$_GET['tel'];


    
    include("ClientDataGateway.php");
    $dbContext= new ClientDataGateway();
    $Client=new Client(Null,$nom,$prenom,$email,$tel);

    $rowAdded=$dbContext->add($Client);
 
    echo $rowAdded;  
?>