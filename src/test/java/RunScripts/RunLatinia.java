package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunLatinia {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio_Latinia("Latinia");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    @Test
    public void LoginExitoso() {
        Reporte.setNombreReporte("Consulta en Latinia");
        Latinia latinia = new Latinia();
        latinia.login("2");
        String otp = latinia.getOTP("0988950350");
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
    