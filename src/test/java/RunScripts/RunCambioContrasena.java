package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCambioContrasena {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_CambioDeContraseña");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Reporte.setNombreReporte("Cambio De Contraseña.");
        Login login = new Login();
        login.Ingresar("3");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Cambio de contrasena exitoso
    public void test(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_cambioContrasena.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Actual	2Nueva	3Verif
            if (linea == 1)
                continue;


            CambioContrasena cc = new CambioContrasena();
            cc.clickMenuUsuario();
            cc.ingresoNuevaContrasena(dato[1], dato[2]);

        }



    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
