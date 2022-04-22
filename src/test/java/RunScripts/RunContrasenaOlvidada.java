package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.ContrasenaOlvidada;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunContrasenaOlvidada {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("OlidoContrasena");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    @Test
    public void OlvidoContrasenaExitoso() throws InterruptedException {
        Reporte.setNombreReporte("Olvido de Contrasena Exitoso");
        ContrasenaOlvidada contrasenaOlvidada = new ContrasenaOlvidada();
        contrasenaOlvidada.Ingresar();
    }

    @After
    public void salir()
    {
        //Util.driver.quit();
        Reporte.finReporte();
    }
}
