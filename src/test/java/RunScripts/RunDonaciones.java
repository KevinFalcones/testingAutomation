package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/*Kevin Falcones - Gestion de Automatizacion y Pruebas
  Automatizacion de Transferencias por Donaciones - KFA_003_CYBERBANK-4060
  Condiciones: Happy Path
 */
//INI-->KFA_003_CYBERBANK-4060

public class RunDonaciones {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Donaciones");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 90.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        //7 carla.loor1 ->tarjeta
        //10 clip2.now  ->otp
        //11 cli1.aho
        //9 angel.navia3
        //12 cta1.cteb -> cta cte bloqueada por deposito y retiro
        //13 cta1.ahob -> cta cte y cta aho bloqueada por deposito y retiro
        login.Ingresar("4");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    //INI-->KFA_003_CYBERBANK-4060
    @Test
    public void run_donacionSimpleExitosa(){
        Donaciones d = new Donaciones();
        d.click_menuDonaciones();
        d.get_validaPantallaDonacion();
        d.click_formaDePagoDonacion();
        d.set_datosDonacion();
        d.click_formaPagoConfirmar();

        //ventana de confirmacion
        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        coor.VP_MensajeCoordenadasCorrectas();
        coor.Click_BotonConfirmar();

        ComprobanteTC comp = new ComprobanteTC();
        comp.vp_mensaje_exitoso("se ha realizado con");
    }

    @Test
    public void run_donacionProgramadaUnica(){
        Donaciones d = new Donaciones();
        d.click_menuDonaciones();
        d.get_validaPantallaDonacion();
        d.click_formaDePagoDonacion();
        d.set_datosDonacion();
        d.click_btnProgramarDonacion();
        d.set_programarDonacion();
        d.click_formaPagoConfirmar();

        //ventana de confirmacion
        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        coor.VP_MensajeCoordenadasCorrectas();
        coor.Click_BotonConfirmar();

        ComprobanteTC comp = new ComprobanteTC();
        comp.vp_mensaje_exitoso("se ha programado con");
    }
    //FIN-->KFA_003_CYBERBANK-4060

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
