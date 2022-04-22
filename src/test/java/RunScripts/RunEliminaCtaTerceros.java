package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunEliminaCtaTerceros {
    @Before
    public void iniciar_Chrome()
    {
        Util.Inicio("RunLogin");
        Reporte.setNombreReporte("Eliminar cuentas matriculadas");
        Login login = new Login();
        login.Ingresar("4");
        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();
        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();
        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    public void matriculaCta() {
        //Ventana de cuentas matriculadas
        EliminarCtaTercerosMatriculada del = new EliminarCtaTercerosMatriculada();
        del.click_smenu_eliminacionCta();
        //Ventana de comprobante
        ComprobanteCta ct = new ComprobanteCta();
        ct.vp_msjCtaEliminada();
    }

    @After
    public void salir() {Reporte.finReporte();}

}
