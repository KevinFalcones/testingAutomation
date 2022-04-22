package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunEliminarTC {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RunLogin");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Reporte.setNombreReporte("Eliminacion de TC de terceros matriculada.");

        Login login = new Login();
        login.Ingresar("2");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
/*
        Logout lo = new Logout();
        lo.CerrarSesion();
        lo.VP_textosDeDespedida();
        lo.iniciarSesion();
*/
    }

    @Test
    public void eliminaTCExitosamente(){
        for (int i =0; i<10;i++){
            EliminacionTCmatriculada e = new EliminacionTCmatriculada();
            e.click_smenu_eliminacionTC();

            ComprobanteTC c = new ComprobanteTC();
            c.VP_eliminacionTCmatriculada();
        }


    }

    /*@Test
    public void PC(){
        for(int i = 1; i<=5; i++){


        }


    }*/

    //@Test
    /*
    public void matriculaTClocalOtrosBancosExitosamente(){
        MenuMatricularTC mtc = new MenuMatricularTC();
        mtc.click_smenu_agendaTClocales();
        mtc.datosTarjetabiente();

        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        coor.VP_MensajeCoordenadasCorrectas();
        coor.Click_BotonConfirmar();

        ComprobanteTC ct = new ComprobanteTC();
        ct.VP_matriculacionTClocal();

    }
*/
    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
