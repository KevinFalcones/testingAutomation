package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunTCPlanProgramado {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("TCPlanProgramado");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
        Login login = new Login();
        login.Ingresar("7");
        //9 angel.navia3  1 tc
        //7 carla.loor1  2 tc

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    //Solo toma los registros con estos valores: Esc->0 Plan->S
    public void planProgramadoExitoso(){
        Reporte.setNombreReporte("Plan Programado Exitoso");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcPlan.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTarjetas tc = new MenuTarjetas();
        tc.click_smenu_PlanProgramado();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Plan	2login	3tc	4monto	5concepto	6plazo
            if (!dato[0].equals("0")) //Escenario 0
                continue;

            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;

            if (dato[1].equals("N")) //No se planea
                continue;

            PlanProgramado plan = new PlanProgramado();
            plan.selecciona_tarjeta(dato[3]);
            plan.click_boton_continuar();
            plan.vp_tarjeta_seleccionada(dato[3]);
            plan.ingresa_monto(dato[4]);
            plan.ingresa_concepto(dato[5]);
            plan.selecciona_plazo(dato[6]);
            plan.click_boton_continuar2();
            plan.click_terminos();

            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            if (primeravez) {
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            plan.click_boton_solicitar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensajeTransaccion_exitoso(1,"COMPROBANTE PLAN PROGRAMADO", "tu Plan Programado ya fue solicitado");
            comp.click_boton_nuevoplan();
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
