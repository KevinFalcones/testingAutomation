package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunUsuarioOlvidado {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("UsuarioOlvidado");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    @Test
    public void OlvidoUsuarioExitoso() throws InterruptedException {
        Reporte.setNombreReporte("Olvido de Usuario Exitoso");
        UsuarioOlvidado usuarioOlvidado = new UsuarioOlvidado();
        usuarioOlvidado.Ingresar();
    }

    @After
    public void salir()
    {
        //Util.driver.quit();
        Reporte.finReporte();
    }
}
