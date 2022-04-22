package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaSENAE {

    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaSENAE");
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
        Reporte.setNombreReporte("Pago de SENAE Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();

            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.VP_LeyendaCheck_Box();
            coord.VP_Check_Box();
            coord.Check_Box();
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnConsultar();
            senae.VP_mensajeErrorFormatoCodigo();
        }
    }

    //CONSULTAR SIN INGRESAR LA IDENTIFICACIÓN
    @Test
    public void ConsultaSinIngresarCodigo() {
        Reporte.setNombreReporte("Consultar sin ingresar código");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();


            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.click_BtnConsultar();
            senae.VP_mensajeSinIngresarCodigo();
        }
    }

    //CONSULTAR IDENTIFICACIÓN SIN DEUDA  //no se puede aprobar porque es ambiente virtualizado
    @Test
    public void ConsultaIdentificacionSinDeuda()  {
        Reporte.setNombreReporte("Consulta de identificación sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnConsultar();
            senae.VP_MensajeSuministroCancelada();
        }
    }

    //CONSULTAR Y PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
            valor.VP_MensajeSeleccionarCuenta();
        }
    }

    //CONSULTAR Y PAGAR CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validación de cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();

            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.VP_LeyendaCheck_Box();
            coord.VP_Check_Box();
            coord.Check_Box();
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
            coord.VP_MensajeCuentaBloqueadaSENAE();
        }

    }

    //CONSULTAR Y PAGAR CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ConsultaValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();

            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.VP_LeyendaCheck_Box();
            coord.VP_Check_Box();
            coord.Check_Box();
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
            coord.VP_MensajeFondoInsuficienteSENAE();
        }
    }

    //CONSULTAR Y PAGAR MENOR A LA DEUDA
    @Test
    public void ConsultaValidacionMenorDeuda()  {
        Reporte.setNombreReporte("Consulta y Validación de pago menor deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
           valor.VP_MensajeDeudaMenor();
        }
    }

    //CONSULTAR Y PAGAR MAYOR A LA DEUDA
    @Test
    public void ConsultaValidacionMayorDeuda()  {
        Reporte.setNombreReporte("Consulta y Validación de pago mayor deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
            valor.VP_MensajeDeudaMayor();
        }
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR
    @Test
    public void ConsultaValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Validación de coordenadas incorrectas");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Valor	3Tipo	4Pago
            if (linea == 1)
                continue;

            if (dato[4].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();

            menu.click_SENAE();
            menu.Opcion_SENAE();

            ConsultaPagoSENAE senae = new ConsultaPagoSENAE();
            senae.VP_TipoServicio();
            senae.IngresarIdentificacion(dato[1]);
            senae.click_BtnPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.Click_CampoCuentaAduana();
            valor.Seleccionar_cuentaBancaria();
            valor.ingreso_valorSENAE(dato[2]);
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            Coordenadas coord = new Coordenadas();
            coord.VP_LeyendaCheck_Box();
            coord.VP_Check_Box();
            coord.Check_Box();
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
