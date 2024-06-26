package mvczoo;

//@author Rodrigo

import Controler.AnimalControler;
import Model.EntidadAnimal;
import Model.ModelAnimal;
import View.Login;
import View.frmAnimal;

 
public class MvcZoo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntidadAnimal entidad = new EntidadAnimal();
        ModelAnimal modelo = new ModelAnimal();
        frmAnimal frm = new frmAnimal();
        Login log = new Login();
        
        AnimalControler objanimal = new AnimalControler(entidad,modelo,frm,log);
        
        objanimal.inicio();
        objanimal.cargardatos();
        frm.setVisible(false);//si lo marco como comentario da error asi que lo puse como FALSO
        log.setVisible(true);
    }
}
