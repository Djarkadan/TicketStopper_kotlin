<?php
    class Client
    {
        public $_idClient;
        public $_nom;
        public $_prenom;
        public $_email;
        public $_tel;
   

        public function __construct($id,$nom,$prenom,$email,$tel)
        {
            $this->_idClient=$id; 
           $this->_nom=$nom; 
           $this->_prenom=$prenom; 
           $this->_email=$email; 
           $this->_tel=$tel; 
        }


    }

?>