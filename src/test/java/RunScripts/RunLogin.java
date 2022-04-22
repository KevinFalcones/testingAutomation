package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunLogin {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RunLogin");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    @Test
    public void LoginExitoso() {
        Reporte.setNombreReporte("LoginExitoso");

        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
