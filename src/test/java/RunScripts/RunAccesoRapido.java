package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunAccesoRapido {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Accesos Rápidos");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)");
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
    @Test //cliente no tiene datos de pago servicios frecuentes, datapool 16 pvelaz
    public void sinpagoFrecuente(){
        AccesosRapidos sCut = new AccesosRapidos();
        sCut.vp_sinPagosFrec();
        sCut.vp_matriculaSevicios();
    }

    @Test //cliente no tiene datos de pago servicios frecuentes, datapool 19 pteran20
    public void conpagoFrecuente(){
        AccesosRapidos sCut = new AccesosRapidos();
        sCut.vp_conPagosFrec();
        sCut.vp_matriculaSevicios();
    }

    @Test //Cliente con pago frecuente tarjetas, pteranr20
    public void conpagoTCFrecuente(){
        AccesosRapidos sCut = new AccesosRapidos();
        sCut.vp_conPagosTCFrec();
        sCut.vp_matriculaTarjetas();
    }

    @After
    public void salir(){Reporte.finReporte();}


}
