<?php 

$email=$_GET['email'];

include("ClientDataGateway.php");
$dbContext= new ClientDataGateway();
$data=$dbContext->isRegistred($email);
echo $data;
?>