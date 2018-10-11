<?php 

include("ClientDataGateway.php");
$dbContext= new ClientDataGateway();
$data=$dbContext->getTableData();

$cnt=0;
while($cnt <count($data))
{   
    
    echo $data[$cnt]->_idClient.' '.
    $data[$cnt]->_nom.' '.
    $data[$cnt]->_prenom.' '.
    $data[$cnt]->_email.' '.
    $data[$cnt]->_tel.' ';
    echo '<br>';
    $cnt++;
}
?>