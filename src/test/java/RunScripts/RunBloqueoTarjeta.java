package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Iterator;
import java.util.List;

public class RunBloqueoTarjeta {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_BloqueoTemporal");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        //Reporte.setNombreReporte("Administracion de Cupos de Tarj. de Credito/Debito.");
        Login login = new Login();
        login.Ingresar("20");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //bloqueo temporal exitoso
    public void bloqueo(){

        Reporte.setNombreReporte("Bloqueo temporal de tarjetas");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_bloqueoTemporal.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TC
            if (linea == 1)
                continue;

            bloqueoTarjeta bt = new bloqueoTarjeta();
            bt.bloqueo();
            bt.bloqueoTarjeta(dato[1]);
<<<<<<< HEAD
<<<<<<< HEAD
=======
            //bt.vp_bloqueoExitoso();
>>>>>>> Autotest menu tarjetas
=======
            //bt.vp_bloqueoExitoso();
>>>>>>> 9da2184c14e14197b64cda091af52d20e7fbc73a

        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }


}
