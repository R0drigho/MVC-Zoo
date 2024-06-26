package Controler;

//@author Rodrigo

import Model.EntidadAnimal;
import Model.ModelAnimal;
import View.Login;
import View.frmAnimal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//import javax.swing.ImageIcon;

public class AnimalControler implements ActionListener {
    
    private EntidadAnimal objentidad;
    private ModelAnimal objmodelanimal;
    private frmAnimal frm;
    private Login login;
    
    public AnimalControler(EntidadAnimal entidad, ModelAnimal modelo, frmAnimal frm, Login login ){
        this.objentidad = entidad;
        this.objmodelanimal = modelo;
        this.frm = frm;
        this.login = login;
        
        //invocando BOTONES
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnActualizar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        
        this.login.btnIngresar.addActionListener(this);//No lo olvides PNDJO x esto perdiste toda una tarde
    }
// Para el Login PUSE COMO comentario lo de abajo
    public void inicio(){
        login.setTitle("Iniciar Sesión");
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        //                           Al parecer debes anotar la ruta de almacenamiento de la PC 
        login.Imagen(login.lblImagen, "C:\\Users\\Rodrigo\\Documents\\NetBeansProjects\\MvcZoo\\Samurai-Doge.gif");
        //login.Imagen(login.lblImagen, "src/Imagen/Ganzo Facha.jpg");
    }
    /*
    public void iniciar() {
        //Ocultamos la caja de Texto
        frm.setTitle("Registro de Animales"); //Aqui ponemos titulo al frmAnimales
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == login.btnIngresar){
            String usuCont = "admin";
            String pass = new String(login.txtContraseña.getPassword());
            
            if(login.txtUsuario.getText().equals(usuCont)&& pass.equals(usuCont)){
                frm.setTitle("Registro de Animales");
                frm.setLocationRelativeTo(null);
                frm.txtId.setVisible(false);
                
                login.setVisible(false);
                frm.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Error   X_X");
                limpiarLog();//LLamada al MEtodo
            }
        }
        //Guardar Datos
        if (e.getSource() == frm.btnGuardar) {

            String xcodigo=frm.txtCodigo.getText().trim();
            String xnombre=frm.txtNombre.getText().trim();
            String xanimal=frm.txtAnimal.getText().trim();
          
            if(xcodigo.equals("")||xnombre.equals("")||xanimal.equals("") ){
               JOptionPane.showMessageDialog(null, "Campos Vacios");
            }
            else{
            objentidad.setCodigo(Integer.parseInt(frm.txtCodigo.getText().trim()));
            objentidad.setNombre(frm.txtNombre.getText().trim());
            objentidad.setAnimal(frm.txtAnimal.getText().trim());
            objentidad.setGenero(frm.cmbGenero.getSelectedItem().toString());
            objentidad.setTipoA(frm.cmbTipoA.getSelectedItem().toString());
            objentidad.setPais(frm.cmbPais.getSelectedItem().toString());
            
            if(objmodelanimal.registrar(objentidad))
            {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            }else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
            cargardatos();
        }
    }
        //Metodo Actualizar
        if (e.getSource() == frm.btnActualizar) {
            
            String xcodigo=frm.txtCodigo.getText().trim();
            String xnombre=frm.txtNombre.getText().trim();
            String xanimal=frm.txtAnimal.getText().trim();
          
            if(xcodigo.equals("")||xnombre.equals("")||xanimal.equals("") ){
               JOptionPane.showMessageDialog(null, "Debe seleccionar los datos que actulizara"); 
            }
            else{
                objentidad.setId(Integer.parseInt(frm.txtId.getText()));
                objentidad.setCodigo(Integer.parseInt(frm.txtCodigo.getText()));
                objentidad.setNombre(frm.txtNombre.getText());
                objentidad.setAnimal(frm.txtAnimal.getText());
                objentidad.setGenero(frm.cmbGenero.getSelectedItem().toString());
                objentidad.setTipoA(frm.cmbTipoA.getSelectedItem().toString());
                objentidad.setPais(frm.cmbPais.getSelectedItem().toString());
                
                if(objmodelanimal.modificar(objentidad))
            {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
            cargardatos();
            }
        }
        //Metodo Buscar
        if (e.getSource() == frm.btnBuscar) {
            
            String xcodigo=frm.txtCodigo.getText().trim();
            
            if(xcodigo.equals("")){
                JOptionPane.showMessageDialog(null, "Debe Ingresar un codigo");
            }
            else{
                objentidad.setCodigo(Integer.parseInt(frm.txtCodigo.getText()));
            
            if(objmodelanimal.buscar(objentidad))
            {
                frm.txtId.setText(String.valueOf(objentidad.getId()));
                frm.txtCodigo.setText(String.valueOf(objentidad.getCodigo()));
                frm.txtNombre.setText(objentidad.getNombre());
                frm.txtAnimal.setText(objentidad.getAnimal());
                frm.cmbGenero.setSelectedItem(objentidad.getGenero());
                frm.cmbTipoA.setSelectedItem(objentidad.getTipoA());
                frm.cmbPais.setSelectedItem(objentidad.getPais());

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                limpiar();
            }
            }
        }
        //Metodo Eliminar
        if (e.getSource() == frm.btnEliminar) {
            
            String xcodigo=frm.txtCodigo.getText().trim();
            String xnombre=frm.txtNombre.getText().trim();
            String xanimal=frm.txtAnimal.getText().trim();
          
            if(xcodigo.equals("")||xnombre.equals("")||xanimal.equals("") ){
               JOptionPane.showMessageDialog(null, "Registro no encontrado");
            }
            else{
                objentidad.setId(Integer.parseInt(frm.txtId.getText()));
            
            if(objmodelanimal.eliminar(objentidad))
            {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
            }
            cargardatos();
            }
            
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }
    
    public void cargardatos(){
   try{
        DefaultTableModel modelo=new DefaultTableModel();
        frm.tablaAnimal.setModel(modelo);
        ResultSet  rs=objmodelanimal.listar();
        
        //Vaciar la CONSULTA A LA TABLA
        ResultSetMetaData rsMd=rs.getMetaData(); //se lo pasa a la tabla a traves de getMetadata
        int cantcolumna=rsMd.getColumnCount(); // gracias a los framworks nos ahorramos todo esto
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("ANIMAL");
        modelo.addColumn("GENERO");
        modelo.addColumn("TIPO");
        modelo.addColumn("PAIS");
        
        while(rs.next()){
           Object[]filas=new Object [cantcolumna];
            for(int i=0;i<cantcolumna;i++){
                filas[i]=rs.getObject(i+1);
              }
            modelo.addRow(filas);
          }
         }catch(SQLException ex){
            System.err.println(ex.toString());
         }
    
}
    public void limpiar()
    {
        frm.txtId.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtAnimal.setText(null);
        
        frm.txtCodigo.requestFocus();// posicionarce en la casilla Codigo
    }
    
    public void limpiarLog(){
        login.txtUsuario.setText(null);
        login.txtContraseña.setText(null);
        
        login.txtUsuario.requestFocus();//Posicionarse en usuario
    }
}

