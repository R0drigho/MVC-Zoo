package Reportes;

//@author Rodrigo

import Model.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class ReporteAnimal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException {
        try{     
         Conexion conn=new Conexion();
         String  reporte="C:\\Users\\Rodrigo\\JaspersoftWorkspace\\MyReports\\listadoZoo.jasper";

      JasperPrint jasperPrintWindow =JasperFillManager.fillReport(reporte, null,conn.getConexion());
      JasperViewer jasperviewer=new JasperViewer(jasperPrintWindow);
      jasperviewer.setVisible(true);
     }
          catch(Exception e){
                  System.out.println(e.getMessage());
                  }
    }
    
}
