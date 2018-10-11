<?php
    class Vehicule
    {
        public $_idVehicule;
        public $_idCLient;
        public $_immatriculation;
        public $_modele;
   

        public function __construct($idVehicule,$idCLient,$immatriculation,$modele)
        {
            $this->_idVehicule=$idVehicule; 
           $this->_idCLient=$idCLient; 
           $this->_immatriculation=$immatriculation; 
           $this->_modele=$modele; 
        }


    }

?>