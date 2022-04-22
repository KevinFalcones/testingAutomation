package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaIESS {
    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaIESS");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    //CONSULTA Y PAGO EXITOSO
    @Test
    public void ConsultayPagoExitoso() {
        Reporte.setNombreReporte("Pago de IESS Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajePagoExitosa();
            comprob.Boton_PosicionConsolidada();
            comprob.Boton_ServiciosMatriculados();
        }

    }

    //CONSULTAR IDENTIFICACIÓN QUE NO CUMPLE FORMATO
    @Test
    public void ConsultaCodigoSinFormato() {
        Reporte.setNombreReporte("Consulta de código sin cumplir formato");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnConsultar();
            iess.VP_mensajeErrorFormatoCodigo();
        }
    }

    //CONSULTAR SIN INGRESAR LA IDENTIFICACIÓN
    @Test
    public void ConsultaSinIngresarCodigo() {
        Reporte.setNombreReporte("Consultar sin ingresar código");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnConsultar();
            iess.VP_mensajeSinIngresarCodigo();
        }
    }

    //CONSULTAR IDENTIFICACIÓN SIN DEUDA
    @Test
    public void ConsultaIdentificacionSinDeuda()  {
        Reporte.setNombreReporte("Consulta de identificación sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();
            iess.VP_MensajeSuministroCancelada();
        }
    }

    //CONSULTAR Y PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
            valor.VP_MensajeSeleccionarCuenta();
        }
    }

    //CONSULTAR Y PAGAR CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validación de cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();
            coord.VP_MensajeCuentaBloqueada();
        }

    }

    //CONSULTAR Y PAGAR CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ConsultaValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.vp_etiqueta_dispositivo();
            coord.VP_TextoIngresaCoordenadas();
            coord.escribe_desafio0();
            coord.escribe_desafio1();
            coord.escribe_desafio2();
            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();
            coord.VP_MensajeFondoInsuficiente();
        }
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR
    @Test
    public void ConsultaValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Validación de coordenadas incorrectas");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroIESS.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Concepto	3Sucursal	4Tipo	5Pago
            if (linea == 1)
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SeguroSocial();
            menu.Opcion_SeguroSocial();

            ConsultaPagoIESS iess = new ConsultaPagoIESS();
            iess.VP_TipoServicio();
            iess.IngresarIdentificacion(dato[1]);
            iess.SeleccionarTipoConcepto(dato[2]);
            iess.IngresarSucursal(dato[3]);
            iess.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.vp_etiqueta_dispositivo();
            coord.VP_TextoIngresaCoordenadas();
            coord.IngresarDesafio1();
            coord.IngresarDesafio2();
            coord.IngresarDesafio3();
            coord.VP_MensajeCoordenadasIncorrectas();
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
