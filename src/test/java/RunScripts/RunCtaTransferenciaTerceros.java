package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCtaTransferenciaTerceros {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("TransferenciasTerceros");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 90.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        //[clip2.now 10 | cquito_07 14 | dbravo20 4 | cta1.ahob 13 | cli1.aho 11
        login.Ingresar("10");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test//Tranferencia a tercero con monto mayor a saldo
    public void tranfMontoMayor(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Origen	2Destino 3Monto   4Medio	5Concepto
            if (linea == 1)
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();
            mt.click_ctaOrigen2(dato[1]);
            mt.click_ctaDestino2(dato[2]);
            mt.click_monto2("100000");
            mt.vp_montoMayor();
        }
    }

    @Test//Tranferencia a tercero con Coordenadas Incorrectas
    public void tranfCoordenadasIncorrectas(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Origen	2Destino 3Monto   4Medio	5Concepto
            if (linea == 1)
                continue;
            //Ventana de confeccion
            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();
            mt.click_ctaOrigen2(dato[1]);
            mt.click_ctaDestino2(dato[2]);
            mt.click_monto2(dato[3]);
            mt.verificaCuentaDestino(dato[4]);
            mt.click_concepto2(dato[5]);
            mt.click_btnContinuar();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();

            coor.coordenadasIncorrectas();
            coor.VP_MensajeCoordenadasIncorrectas();
        }
    }

    @Test//Tranferencia a terceros BB exitosa
    //Solo toma los registros con estos valores: Esc->0 Transfiere->S
    public void esc0_transferenciaTercerosBBExitosa() {
        Reporte.setNombreReporte("Transferencias Terceros BB Exitosas");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("0")) //Escenario 0
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);
            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Continuar_old();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }

    @Test//Tranferencia Otros Bancos exitosa
    //Solo toma los registros con estos valores: Esc->1 Transfiere->S
    public void esc1_transferenciaOtrosBancosExitosa_old() {
        Reporte.setNombreReporte("Transferencias Otros Bancos Exitosas");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("1")) //Escenario 1
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen_old(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Continuar_old();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }

    @Test//Tranferencia Otros Bancos exitosa
    //Solo toma los registros con estos valores: Esc->1 Transfiere->S
    public void esc1_transferenciaOtrosBancosExitosa() {
        Reporte.setNombreReporte("Transferencias Otros Bancos Exitosas");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("1")) //Escenario 1
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            //transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino(dato[4]);
            transf.ingresa_monto0(dato[5]);

            transf.ingresa_concepto0(dato[7]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            /*if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }*/

            transf.click_boton_Continuar();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.vp_etiqueta_dispositivo();
                coor.autenticacion(true);

                if (coor.existe_msj_claveIncorrecta()) {
                    coor.autenticacion(false);

                    if (!coor.existe_msj_claveIncorrecta())
                        primeravez = false;
                }
                else
                    primeravez = false;
            }

            transf.click_boton_Transferir();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_exitoso("se ha realizado con");
        }
    }

    @Test
    //Solo toma los registros con estos valores: Esc->2 Transfiere->S
    public void esc2_programacionTercerosExitoso(){
        Reporte.setNombreReporte("Transferencia a Terceros - Programacion");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("2")) //Escenario 2
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Programar();
            transf.selecciona_frecuencia(dato[10]);

            if (dato[11].equals("S")) //Se escoge fecha fin
                transf.ingresa_fechaFin(dato[12]);

            if (!dato[13].equals("0")) //Se escribe la cantidad de ejecuciones si el valor es mayor a 0
                transf.ingresa_periodos(dato[13]);

            transf.click_boton_Continuar_old();

            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            comp.vp_mensaje_error("se ha programado exitosamente");
        }
    }

    @Test//Tranferencia desde cuenta origen bloqueaaa
    //Solo toma los registros con estos valores: Esc->3 Transfiere->S
    public void esc3_transferenciaCtaBBOrigenBloqueada() {
        Reporte.setNombreReporte("Transferencias desde Cta Origen Bloqueada");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("3")) //Escenario 3
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Continuar_old();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            //CUENTA BLOQUEADA PARA MOVIMIENTOS
            //(31008)Cuenta bloqueada: CONTRA DEPOSITO Y RETIRO
            comp.vp_mensaje_error("CUENTA BLOQUEADA");
        }
    }

    @Test//Tranferencia Pago Directo mínimo permitido
    //Solo toma los registros con estos valores: Esc->4 Transfiere->S
    public void esc4_transferenciaPDMinimoPermitido() {
        Reporte.setNombreReporte("Transferencias Mínimo Permitido");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("4")) //Escenario 4
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Continuar_old();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            //(41216)VALOR ES MENOR AL MONTO MINIMO PERMITIDO
            comp.vp_mensaje_error("VALOR ES MENOR AL MONTO");
        }
    }

    @Test//Tranferencia desde cuenta origen bloqueaaa
    //Solo toma los registros con estos valores: Esc->3 Transfiere->S
    public void esc5_transferenciaCtaBBDestinoBloqueada() {
        Reporte.setNombreReporte("Transferencias hacia Cta Destino Bloqueada");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Transfiere	2login	3Origen	4Destino	5Monto	6Via	7Concepto	8Enviomail	9mail	10Frecuencia	11FechaFin	12DiasFin	13Ejecuciones
            if (!dato[0].equals("5")) //Escenario 5
                continue;
            if (!dato[2].equals(Util.getDataCliente()[1])) //Si el login no corresponde
                continue;
            if (dato[1].equals("N")) //No se transfiere
                continue;

            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();

            Transferencia transf = new Transferencia();
            transf.vp_tituloTCterceros();
            transf.selecciona_CuentaOrigen(dato[3]);
            transf.selecciona_CuentaDestino_old(dato[4]);
            transf.ingresa_monto0(dato[5]);

            if (!dato[6].equals("bb")) //Via de transferencia
            {
                //Otros Bancos Pago Inmediato
                //Otros Bancos
                transf.selecciona_via(dato[6]);
            }

            transf.ingresa_concepto0(dato[7]);

            if (dato[8].equals("S")) //Envia email
            {
                transf.click_check_email();
                transf.ingresa_email(dato[9]);
                transf.ingresa_mensajeEmail(dato[7]);
            }

            transf.click_boton_Continuar_old();
            //ventana de confirmacion
            Coordenadas coor = new Coordenadas();
            //if (primeravez)
            //{
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            //primeravez = false;
            //}
            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();

            ComprobanteTC comp = new ComprobanteTC();
            //CUENTA BLOQUEADA PARA MOVIMIENTOS
            //(31008)Cuenta bloqueada: CONTRA DEPOSITO Y RETIRO
            comp.vp_mensaje_error("CUENTA BLOQUEADA");
        }
    }

    @Test
    public void tranfMultiple(){
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_transferenciaTercerosMultiples.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Origen	2Destino 3Monto   4Medio	5Concepto   6numTrans
            if (linea == 1)
                continue;

            //Ventana de confeccion
            MenuTransferir mt = new MenuTransferir();
            mt.click_smenu_ctasTerceros();
            mt.confeccionTransfTerceros(dato[1], dato[2], dato[3], dato[4], dato[5]);

            if (Integer.parseInt(dato[6])>1){

                int transferenciasPorProcesar=Integer.parseInt(dato[6])-1;
                int transferenciaActual=7;


                for (int i=1;i<=transferenciasPorProcesar;i++){//=3

                    mt.transTercerosMultiples(i,dato[transferenciaActual], dato[transferenciaActual+1], dato[transferenciaActual+2],dato[transferenciaActual+3]);
                    transferenciaActual+=4;
                }

            }
            mt.btnContinuar();

            Coordenadas coor = new Coordenadas();
            coor.escribe_desafio0();
            coor.escribe_desafio1();
            coor.escribe_desafio2();
            coor.VP_MensajeCoordenadasCorrectas();
            coor.Click_BotonConfirmar();

            //Ventana de comprobante
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
