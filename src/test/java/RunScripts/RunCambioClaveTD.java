package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCambioClaveTD {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RunCambioClaveTD");

        Login login = new Login();
        login.Ingresar("14");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    public void cambioClaveTD()
    {
        Reporte.setNombreReporte("Evento Cambio ClaveTD");

        CambioClaveTD claveTD = new CambioClaveTD();
        claveTD.menuTarjetas();
        claveTD.setClaveActual();
        claveTD.setClaveNueva();
        claveTD.setClaveNuevaRepetir();
        claveTD.setBtnAceptar();
    }

    @After
    public void salir()
    {
        //Util.driver.quit();
        Reporte.finReporte();
    }

}
