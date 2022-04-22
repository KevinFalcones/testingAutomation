package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunPerfilEquipos {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("PerfilEquipos");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)");

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

    @Test
    public void eliminarDispositivo() {
        Reporte.setNombreReporte("Eliminar Equipos Registrados");
        RegistroEquipo equipo = new RegistroEquipo();
        equipo.ingresa_opcion_perfil();
        int num_equipos = equipo.obtiene_num_equipos();

        while (num_equipos>0)
        {
            equipo.click_btn_eliminar(0);
            Boolean primeravez=true;

            Coordenadas coord = new Coordenadas();
            if (primeravez)
            {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }
            coord.click_btn_eliminar();

            ComprobantePerfil comp = new ComprobantePerfil();
            comp.vp_mensaje_eliminaequipo_exitoso();
            comp.click_btn_VerEquipos();

            num_equipos = equipo.obtiene_num_equipos();
        }

        equipo.vp_mensaje_sinequipos();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
