<?php

class Idea extends Controller {

	function Idea()
	{
		parent::Controller();	
		$this->load->helper(array('form', 'url', 'html'));
	}
	
	function index()
	{
	  $this->db->select('*')->from('idea'); 
      $ideasCreadas = $this->db->get();

      $data = array('ideas_creadas' => $ideasCreadas);
      $this->load->view('idea_formulario', $data);
	}
	
	//--------------------------------------------------------------------------------------
	// Votar una idea
	// ==============
	//--------------------------------------------------------------------------------------
	function votarIdea()
	{
       // Sumar 1 al número de votos de la idea. La clave nos llega en la url (id_idea)      
       $idIdea = $this->uri->segment(3);

       // borramos el registro de la tabla de juevebes
       $query = $this->db->query('update idea set votos = votos + 1 where id_idea = '.$idIdea); 
       

	   // recuperamos las ideas que ya están en la bd para enviárselas a la vista inicial
       $this->db->select('*')->from('idea')->order_by('votos', 'desc'); 
       $ideasCreadas = $this->db->get();
        
       $data = array('ideas_creadas' => $ideasCreadas);
       $this->load->view('idea_formulario', $data);   
	 }
	
	
	//--------------------------------------------------------------------------------------
	// Alta de una idea
	// ================
	//  - Recuperamos el texto enviado desde el formulario
	//  - Lo damos de alta en la bd
	//  - Redireccionamos a la vista inicial para mostrar la nueva idea
	//--------------------------------------------------------------------------------------
	function altaIdea()
	{
		// Recuperamos el texto de la idea tecleado en el formulario
		$textoIdea = $this->input->post('txtIdea');		
		
		if($textoIdea != ''){
		  $data = array(
		    	'descripcion' => $textoIdea,
  		  		'votos' => '0');

          $this->db->insert('idea', $data);
		}
          
        // recuperamos las ideas que ya están en la bd para enviárselas a la vista inicial
	    // @todo: comprobar si se puede invocar el método index de este controlador en lugar de cargar la vista
        $this->db->select('*')->from('idea')->order_by('votos', 'desc'); 
        $ideasCreadas = $this->db->get();
    
        $data = array('ideas_creadas' => $ideasCreadas);
        $this->load->view('idea_formulario', $data);   
	}
	
}
