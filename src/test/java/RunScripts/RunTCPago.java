package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunTCPago {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("TCPago");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
        Login login = new Login();
        //[clip2.now 10 | cquito_07 14 | dbravo20 4 | cta1.ahob 13 | cli1.aho 11
        login.Ingresar("14");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    //Solo toma los registros con estos valores: Esc->1 Paga->S
    public void pagoTCtercerosExitoso(){
        Reporte.setNombreReporte("Pago de TC Terceros");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcPago.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Paga	2login	3tc	4cta_origen	5monto	6Via	7concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;

            if (dato[1].equals("N")) //No se paga
                continue;

            MenuTarjetas tc = new MenuTarjetas();
            tc.click_smenu_PagoTCTerceros();

            PagoTarjetas pt = new PagoTarjetas();
            pt.vp_tituloTCterceros();
            pt.selecciona_TC(dato[3]);
            //tc.click_ctaOrigen();
            pt.seleccionar_CuentaOrigen(dato[4].substring(7,10));
            pt.ingresa_monto0(dato[5]);
            pt.verificaTCdestino();
            pt.ingresa_concepto0(dato[7]);
            pt.click_boton_Pagar();

            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }

    @Test
    //Solo toma los registros con estos valores: Esc->2 Paga->S
    public void pagoTCOtrosBancosExitoso(){
        Reporte.setNombreReporte("Pago de TC Otros Bancos");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcPago.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Paga	2login	3tc	4cta_origen	5monto	6Via	7concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("2")) //Escenario 2
                continue;

            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;

            if (dato[1].equals("N")) //No se paga
                continue;

            MenuTarjetas tc = new MenuTarjetas();
            tc.click_smenu_PagoTCTerceros();

            PagoTarjetas pt = new PagoTarjetas();
            pt.vp_tituloTCterceros();
            pt.selecciona_TC(dato[3]);
            //tc.click_ctaOrigen();
            pt.seleccionar_CuentaOrigen(dato[4].substring(7,10));
            pt.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                pt.selecciona_via(dato[6]);
            }

            pt.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                pt.click_check_email();
                pt.ingresa_email(dato[9]);
                pt.ingresa_mensajeEmail(dato[7]);
            }

            pt.click_boton_Pagar();

            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }


    public void cierreSession(){
        Logout lo = new Logout();
        lo.CerrarSesion();
        lo.VP_textosDeDespedida();
        lo.iniciarSesion();
    }

    @Test
    //Solo toma los registros con estos valores: Esc->0 y Paga->S
    public void pagoTCpropiaExitoso (){
        Reporte.setNombreReporte("Pago de TC Propias");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcPago.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Paga	2login	3tc	4cta_origen	5monto	6Via	7concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("0")) //Escenario 0
                continue;

            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;

            if (dato[6].equals("N")) //No se Paga
                continue;

            MenuTarjetas tc = new MenuTarjetas();
            tc.click_smenu_PagoTCPropias();

            Campanias camp =  new Campanias();
            camp.click_boton_X();

            PagoTarjetas pt = new PagoTarjetas();
            pt.selecciona_tarjeta(dato[3]); //se envía en el archivo los 4 últimos dígitos de la TC
            pt.vp_tarjeta_seleccionada(dato[3]);
            pt.selecciona_ctaDebito(dato[4]);
            pt.ingresa_monto(dato[5]);
            pt.ingresa_concepto(dato[7]);
            pt.click_boton_Continuar();
            pt.click_boton_Pagar();

            ComprobanteTC ct = new ComprobanteTC();
            ct.vp_mensaje_pago_exitoso();
            ct.vp_monto_pagado(dato[5]);
            ct.click_boton_nuevopagotc();
        }
    }

    @Test
    //Solo toma los registros con estos valores: Esc->3 Paga->S
    //El dato 1Login es informativo para saber con qué usuario se está trabajando
    public void programacionTCtercerosExitoso(){
        Reporte.setNombreReporte("Pago de TC Terceros - Programacion");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcPago.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1login	2tc	3cta_origen	4monto	5concepto	6Paga	7Frecuencia	8FechaFin	9DiasFin	10Ejecuciones
            if (!dato[0].equals("3")) //Escenario 3
                continue;

            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;

            if (dato[6].equals("N")) //No se paga
                continue;

            MenuTarjetas tc = new MenuTarjetas();
            tc.click_smenu_PagoTCTerceros();

            PagoTarjetas pt = new PagoTarjetas();
            pt.vp_tituloTCterceros();
            pt.selecciona_TC(dato[2]);
            //tc.click_ctaOrigen();
            pt.seleccionar_CuentaOrigen(dato[3].substring(7,10));
            pt.ingresa_monto0(dato[4]);
            pt.verificaTCdestino();
            pt.ingresa_concepto0(dato[5]);

            pt.click_boton_Programar();
            pt.selecciona_frecuencia(dato[7]);

            if (dato[8].equals("S")) //Se escoge fecha fin
                pt.ingresa_fechaFin(dato[9]);

            if (!dato[10].equals("0")) //Se escribe la cantidad de ejecuciones si el valor es mayor a 0
                pt.ingresa_periodos(dato[10]);

            pt.click_boton_Pagar();

            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
