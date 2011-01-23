<html>
  <head>
  	<link rel="stylesheet" type="text/css" href="<?=base_url()?>application/estilos.css" media="screen" />
    <title> Ideas </title>
  </head>

  <body>

	<?php //== formulario de alta ======================================================================?>
    <div id='contenedor' style='position:relative'>
      <div id='formularioAlta'>
        <?php
	      //--------------------------------------------------------------------------------------
          // formulario para dar de alta una idea
          // @todo: enviar el formulario cuando se pulse intro y eliminar el botón de envío
	      //--------------------------------------------------------------------------------------
          echo form_open('idea/altaIdea', 'name="frmAltaIdea"');
          $data = array(
          			'name' => 'txtIdea',
          			'maxlength' => '256',
          			'rows' => '4',
          			'cols' => '80');
		  echo '<p>¿Cuál es tú idea? (256 carácteres)</p> ';
		  echo form_textarea($data).'<br/>';
          echo form_submit('cmdEnviar', 'Enviar');
          echo form_close();
          
        ?>
        
      </div>
	<?php //============================================================================================?>


  
	<?php //== listado de ideas ========================================================================
	  // si nos llegaron datos los mostramos (ideas de la bd)
	  if($ideas_creadas != ''){
	   	foreach ($ideas_creadas->result() as $row) {
          echo '<div id="ideasParaVotar">';  // capa con la idea

          echo '<div id="capaVotos">';  // capa con el número de votos
	   	  echo $row->votos;
          echo "</div>";  // capa con el número de votos
          
          echo '<div id="textoIdea">';  // capa con el número de votos
          echo '<p>Descripción: '.$row->descripcion.'</p>';
	   	  echo '<p>Usuario: '.$row->usuario.'</p>';
	   	  echo '<p>Fecha: '.$row->fecha.'</p>';
          echo "</div>";  // texto idea
	   	  
	   	  // link para votar 
	   	  echo "<div id='linkVotar'>";
	   	  
	   	  // @todo: definir un anchor para volver al mismo sitio de la página en el que votaste al refrescarla
          echo anchor('idea/votarIdea/'.$row->id_idea, '<img src="'.base_url().'application/images/flecha_arriba_64x64.png" alt="votar">');
          echo "</div>";  // capa para el link de votar
          echo "</div>";  // capa con la idea
              
          echo '<br/>';
	  	}
	  }else{
	    echo '<p>No hay datos</p>';
	  }
      
    ?>
	
	<?php //============================================================================================?>
  
    </div>
    

  </body>
</html>
