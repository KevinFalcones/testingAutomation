package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunDiferirSaldos {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_DiferirSaldos");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Reporte.setNombreReporte("Diferir saldos.");
        Login login = new Login();
        login.Ingresar("8");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Diferir saldos exitoso
    public void test(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_diferirSaldos.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1numTC	2monto	3plazo
            if (linea == 1)
                continue;

            DiferirSaldos ds = new DiferirSaldos();
            ds.click_smenu_DiferirSaldos();
            ds.selecciona_tarjeta(dato[1]);
            ds.montoYplazo(dato[2],dato[3]);
            ds.confirmacion();

            Coordenadas coor = new Coordenadas();
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();

            //coor.Click_BotonDiferir();

        }



    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
