package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunPerfilNombreUsuario {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("PerfilNombreUsuario");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)");

        Login login = new Login();
        login.Ingresar("9");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    public void cambiarUsuario() {
        Reporte.setNombreReporte("Cambio de Nombre de Usuario");

        NombreUsuario usuario = new NombreUsuario();
        usuario.ingresa_opcion_perfil();
        usuario.ingresa_usuario(Util.getDataCliente()[4]);
        usuario.click_btn_continuar();
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

        coord.Click_BotonConfirmar();
        
        ComprobantePerfil comp = new ComprobantePerfil();
        comp.vp_mensaje_cambiousuario_exitoso();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
