package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunAdmCupos {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_AdministracionDeCupos");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        //Reporte.setNombreReporte("Administracion de Cupos de Tarj. de Credito/Debito.");
        Login login = new Login();
        login.Ingresar("2");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Adm. de cupos de TC exitoso
    public void credito(){

        Reporte.setNombreReporte("Administracion de Cupos de Tarj. de Credito.");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_admCupoCredito.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            AdmCupos ac = new AdmCupos();
            ac.cupoCredito();
            ac.switches(1, dato[8],dato[9],dato[10],dato[11],dato[12],dato[13]);
            ac.confeccionCupoCredito(1, dato[2],dato[3],dato[4],dato[5],dato[6],dato[7]);
            ac.guardar(1);
            ac.estoyDeAcuerdo();
            //ac.switches("n","y","y","y","y","n");
            Coordenadas coor = new Coordenadas();
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coor.Click_BotonConfirmar();
            ac.msjEsxitoso(1);


        }
    }

    @Test//Adm. de cupos de TD exitoso
    public void debito(){
        Reporte.setNombreReporte("Administracion de Cupos de Tarj. de Debito.");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_admCupoDebito.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            AdmCupos ac = new AdmCupos();
            ac.cupoDebito();
            ac.switches(0, dato[8],dato[9],dato[10],dato[11],dato[12],dato[13]);
            ac.confeccionCupoCredito(0, dato[2],dato[3],dato[4],dato[5],dato[6],dato[7]);
            ac.guardar(0);

            Coordenadas coor = new Coordenadas();
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coor.Click_BotonConfirmar();
            ac.msjEsxitoso(0);


        }

    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }


}
