package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunPerfilDesbloqueoDispositivo {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("DesbloqueoDispositivo");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

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

    @Test //El disposititivo debe estar previamente bloqueado
    public void actualizacionPreguntas() {
        DesbloqueoDispositivo desbloqueo = new DesbloqueoDispositivo();
        desbloqueo.ingresa_opcion_perfil();
        desbloqueo.vp_etiqueta_desbloqueo();
        desbloqueo.click_btn_desbloquear();

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        ComprobantePerfil comp = new ComprobantePerfil();
        comp.vp_mensaje_desbloqueadispo_exitoso();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
