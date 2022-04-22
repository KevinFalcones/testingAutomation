package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCambioAvatar {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_CambioDeAvatar");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Reporte.setNombreReporte("Cambio De Avatar.");
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

    @Test //Cambio de avatar exitoso
    public void test(){
        CambioAvatar ca = new CambioAvatar();
        ca.clickMenuUsuario();
        ca.clickAvatar();

        Coordenadas coor = new Coordenadas();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        coor.vp_etiqueta_dispositivo();
        coor.VP_TextoIngresaCoordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        coor.Click_BotonCambiar();

        ca.vp_comprobanteAvatar();

    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }


}
