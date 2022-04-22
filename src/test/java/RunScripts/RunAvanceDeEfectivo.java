package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunAvanceDeEfectivo {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_AvanceDeEfectivo");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Reporte.setNombreReporte("Avance de efectivo.");
        Login login = new Login();
        login.Ingresar("19");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Avance de efectivo (corriente o diferido segun este configurado en datapool) exitoso
    public void test(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_avanceDeEfectivo.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            AvanceDeEfectivo ae = new AvanceDeEfectivo();
            ae.seleccionTC(dato[1],dato[2],dato[3]);

            Coordenadas coor = new Coordenadas();
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coor.Click_BotonRealizarAvance();
            ae.vp_transaccionOK();


        }

    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
