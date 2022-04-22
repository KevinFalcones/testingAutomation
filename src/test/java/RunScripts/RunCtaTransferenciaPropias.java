package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCtaTransferenciaPropias {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RunTransferenciasPropias");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        login.Ingresar("2");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }
    //Transferencia entre cuentas propias exitosa
    @Test
    public void Exitoso() {
        Reporte.setNombreReporte("Transferencias Propias - exitoso");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaPropia2.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Origen	2Destino 3Monto   4Concepto 5NumTrans, las siguientes columnas es la confeccion de las transf. multiples en caso que el escenario este configurado para realizarlo asi.
            if (linea == 1)
                continue;

            MenuTransferir mt = new MenuTransferir();

            mt.click_smenu_ctasPropias();
            mt.confeccionTrasnfPropias(dato[1], dato[2], dato[3], dato[4]);

            if (Integer.parseInt(dato[5])>1){

                int transferenciasPorProcesar=Integer.parseInt(dato[5])-1;
                int transferenciaActual=6;


                for (int i=1;i<=transferenciasPorProcesar;i++){//=3

                    mt.transPropiasMultiples(i,dato[transferenciaActual], dato[transferenciaActual+1], dato[transferenciaActual+2]);
                    transferenciaActual+=3;
                }


            }
            mt.confirmacionTranf();

            Comprobante c = new Comprobante();
            c.VP_transferenciaPropia();

        }

    }

    @Test//Transferencia entre cuentas propias programadas exitosas
    public void transfPropiasProgramadas(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaPropiaProgramada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        //1Origen	2Destino 3Monto	4Concepto	5Frec	6Fecha(1)/pagos(0)	7FechaFin	8numPagos
        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;

            if (linea == 1)
                continue;

            MenuTransferir mt = new MenuTransferir();

            mt.click_smenu_ctasPropias();
            mt.confeccionProgramacionPropias(dato[1], dato[2], dato[3], dato[4], dato[5], dato[6], dato[7], dato[8]);

            /*Comprobante c = new Comprobante();
            c.VP_transferenciaPropia();*/

        }

    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
