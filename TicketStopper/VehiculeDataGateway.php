<?php
include_once("Vehicule.php");
class VehiculeDataGateway
{   
    // info de connexion
    private $host='127.0.0.1';
    private $user='root';
    private $pass='';
    private $charset='utf8';
    private $dbName='ticketstopper';

    private $conn;


    // les requetes
    private $selectQuery="SELECT * FROM vehicule";
    private $userInfoPreparedStatement;



    private $insertQuery="INSERT INTO vehicule (idClient,immatriculation,modele) VALUES(
        ?,
        ?,
        ? 
    )";
    private $insertQueryPreparedStatement;
   
    private $editQuery="UPDATE Vehicule
                         SET immatriculation=?modele=?
                         WHERE idClient=? AND idVehicule=?";

    private $editQueryPreparedStatement;



    public function __construct(){
        $connectionString="mysql:host=$this->host;dbname=$this->dbName;charset=$this->charset";
        try{
            $this->conn=new PDO($connectionString,$this->user,$this->pass);
           
            
            
            $this->userInfoPreparedStatement=$this->conn->prepare($this->selectQuery);
            $this->insertQueryPreparedStatement=$this->conn->prepare($this->insertQuery);
            $this->editQueryPreparedStatement=$this->conn->prepare($this->editQuery);

        }catch(Exception $e)
        {
            die($e->getMessage());
        }
      

    }

    public function getTableData()
    {   
        $tempArray=array();
        $this->userInfoPreparedStatement->execute();
        while($ligne=$this->userInfoPreparedStatement->fetch())
        {
            $vehicule=new Vehicule($ligne[0],$ligne[1],$ligne[2],$ligne[3]);
            array_push($tempArray,$vehicule);

        }
        return $tempArray;
    }


    public function add(Vehicule $vehicule)
    {   
        $this->insertQueryPreparedStatement->execute([$vehicule->_idCLient,$vehicule->_immatriculation,$vehicule->_modele]);
       return $this->insertQueryPreparedStatement->rowCount();
    }
    
    public function edit($id,Client $client){
        $this->editQueryPreparedStatement->execute([$client->_nom,$client->_prenom,$client->_email,$client->_email,$id]);
        return $this->editQueryPreparedStatement->rowCount();

    }

    


}


?>