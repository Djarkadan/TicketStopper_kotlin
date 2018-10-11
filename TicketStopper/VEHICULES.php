<?php 

include("VehiculeDataGateway.php");
$dbContext= new VehiculeDataGateway();
$data=$dbContext->getTableData();

$cnt=0;
while($cnt <count($data))
{   
    
    echo $data[$cnt]->_idVehicule.' '.
    $data[$cnt]->_idCLient.' '.
    $data[$cnt]->_immatriculation.' '.
    $data[$cnt]->_modele.' ';
    echo '<br>';
    $cnt++;
}
?>