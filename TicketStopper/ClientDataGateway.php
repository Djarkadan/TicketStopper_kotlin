<?php
include_once("Client.php");
class ClientDataGateway
{   
    // info de connexion
    private $host='127.0.0.1';
    private $user='root';
    private $pass='';
    private $charset='utf8';
    private $dbName='ticketstopper';

    private $conn;


    // les requetes
    private $selectQuery="SELECT * FROM client";
    private $userInfoPreparedStatement;



    private $insertQuery="INSERT INTO client (nom,prenom,email,tel) VALUES(
        ?,
        ?,
        ?,
        ?
        
    )";
    private $insertQueryPreparedStatement;
   
    private $editQuery="UPDATE client
                         SET nom=?,prenom=?,email=?,tel=?
                         WHERE idClient=?";

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
            $client=new Client($ligne[0],$ligne[1],$ligne[2],$ligne[3],$ligne[4]);
            array_push($tempArray,$client);

        }
        return $tempArray;
    }


    public function add(Client $client)
    {   
        $this->insertQueryPreparedStatement->execute([$client->_nom,$client->_prenom,$client->_email,$client->_tel]);
       return $this->insertQueryPreparedStatement->rowCount();
    }
    
    public function edit($id,Client $client){
        $this->editQueryPreparedStatement->execute([$client->_nom,$client->_prenom,$client->_email,$client->_email,$id]);
        return $this->editQueryPreparedStatement->rowCount();

    }

    public function isRegistred($email){
        $isRegistred=false;
       
        $query='SELECT COUNT(idCLient) FROM CLIENT WHERE email=?';
        $stmt=$this->conn->prepare($query);
        $stmt->execute([$email]);
        $count=0;
        if($ligne=$stmt->fetch())
        {
            $count=$ligne[0];

        }
        return ($count==1)?1:0;

    }

    


}


?>