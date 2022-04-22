package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunRecaudaPrincipal {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RecaudacionPrincipal");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    @Test
    public void PantallaPrincipal()  {
        Reporte.setNombreReporte("Validaci�n Pantalla Pago de Servicios");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        /*RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();*/

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.BotonMatricular();
        menu.click_SeguroSocial();
        menu.BotonCancelar_SeguroSocial();
        menu.BotonAceptar_SeguroSocial();
        menu.Opcion_Luz();
        menu.Opcion_Agua();
        menu.Opcion_TelefoniaFija();
        menu.Opcion_TelefoniaCelular();
        menu.Opcion_TelevisionPagada();
        menu.Opcion_Internet();
        menu.Opcion_ImpuestosAduaneros();
        menu.Opcion_ImpuestosObligaciones();
        menu.Opcion_Educacion();
        menu.Opcion_AutomotoresPeatones();
        menu.Opcion_TarjetasComerciales();
        menu.Opcion_TransferenciasEspeciales();
        menu.BotonPosicionConsolidada();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
