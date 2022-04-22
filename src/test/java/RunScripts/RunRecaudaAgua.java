package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaAgua {

    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaAgua");
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


    //MATRICULAR SUMINISTRO QUE NO CUMPLE FORMATO
    @Test
    //Solo toma los registros con estos valores: Esc 2 -> Matricula  S
    public void MatricularCodigoInvalido() {
        Reporte.setNombreReporte("Validación del formato del suministro en la matriculación");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("2")) //Escenario 2
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeFormatoSuministro();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL ALIAS
    @Test
    //Solo toma los registros con estos valores: Esc 1 -> Matricula  S
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validación sin alias en la matriculación");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinAlias();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL CODIGO
    @Test
    //Solo toma los registros con estos valores: Esc 1 -> Matricula  S
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validación sin suministro en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinSuministro();
        }
    }
    
    //MATRICULAR SUMINISTRO CON ALIAS YA REGISTRADO
    @Test
    //Solo toma los registros con estos valores: Esc 1 -> Matricula en S
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de ALIAS ya matriculado");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (dato[6].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            }
            catch(Exception e)
            {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();
        }
        Coordenadas coord = new Coordenadas();
        coord.VP_MensajeAliasYaMatriculado();
    }

    //MATRICULAR SUMINISTRO CON CÓDIGO YA REGISTRADO
    @Test
    //Solo toma los registros con estos valores: Esc 1 -> Matricula en S
    public void MatricularCodigoYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de SUMINISTRO ya matriculado");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            }
            catch(Exception e)
            {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }
            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();
            Util.RetrocederPagina();
            coord.VP_MensajeCodigoYaMatriculado();
        }
    }

    //MATRICULAR y CONSULTAR SUMINISTRO SIN DEUDA
    @Test
    //Solo toma los registros con estos valores: Esc 3 -> Matricula en S
    public void MatriculacionyConsultaSinDeuda() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro sin deuda");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("3")) //Escenario 3
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

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
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            //Validación del mensaje de suministro sin deuda
            ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
            consulta.VP_MensajeSuministroCancelada();
        }
    }

    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO
    @Test
    //Solo toma los registros con estos valores: Esc 4 -> Matricula en S
    //Se deben eliminar esas matriculaciones si ya existen matriculadas
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Identi	2Alias	3Empresa	4Monto	5Matricula	6Elimina	7Consulta	8Pago	9TipoPago
            if (!dato[0].equals("4")) //Escenario 4
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

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
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            //Validación del mensaje de suministro no válido
            ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
            consulta.VP_MensajeClienteNoExiste();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA BLOQUEADA
    @Test
    public void MatriculacionConsultaPagoConCuentaBloqueada() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[6].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

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
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.VP_EtiquetaMontoAPagar();
            valor.ingreso_valor();
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            //Validación del mensaje de la cuenta bloqueada
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();
            coord.VP_MensajeCuentaBloqueada();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA CON FONDOS INSUFICIENTES
    @Test
    public void MatriculacionConsultaPagoConCuentaSinFondo() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta con fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            }
            catch(Exception e)
            {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.VP_EtiquetaMontoAPagar();
            valor.ingreso_valor(dato[4]);
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            //Validación del mensaje de fondos insuficientes
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();
            coord.VP_MensajeFondoInsuficiente();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON MONTO A PAGAR SUPERIOR A LA DEUDA
    @Test
    public void MatriculacionyConsultaConMontoSuperior() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de suministro con monto superior");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[6].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

            Coordenadas coord = new Coordenadas();
            coord.vp_etiqueta_dispositivo();
            coord.VP_TextoIngresaCoordenadas();
            coord.escribe_desafio0();
            coord.escribe_desafio1();
            coord.escribe_desafio2();
            coord.VP_MensajeCoordenadasCorrectas();

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.VP_EtiquetaMontoAPagar();
            valor.ingreso_valor(dato[4]);
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
            valor.VP_MensajeMontoSuperior();
        }
    }

    //FLUJO EXITOSO DE MATRICULACION, CONSULTA Y PAGO DEL SUMINISTRO
    @Test
    //Solo toma los registros con estos valores: Esc 5 -> Matricula en S
    public void MatriculacionConsultayPagoAgua()  {
        //*****
        //
        //*****
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de Agua Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroAgua.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (!dato[0].equals("5")) //Escenario
                continue;

            if (dato[5].equals("N")) //No se matricula
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionAgua matri = new MatriculacionAgua();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Agua();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();

            Coordenadas coord = new Coordenadas();
            try {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
            }
            catch(Exception e)
            {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
            comprob.VP_BotonPagar();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            valor.CargaDatosSuministro();
            valor.VP_EtiquetaMontoAPagar();
            valor.ingreso_valor(dato[4]);
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            //Confirmar el pago en la pantalla de confirmación de pago
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();

            //Sólo se puede validar los botones del Comprobante de pago
            comprob.Boton_PosicionConsolidada();
            comprob.Boton_ServiciosMatriculados();
        }
    }

    //FLUJO EXITOSO DE CONSULTA (DE SUMINISTRO MATRICULADO) Y PAGO
    @Test
    public void ConsultayPagoAgua()  {
        Reporte.setNombreReporte("Consulta y Pago de Agua Exitoso");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Agua();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaConSaldo();
        consulta.Seleccionar_cuentaBancaria("448");

        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) SIN DEUDA
    @Test
    public void ConsultaSuministroSinDeuda()  {
        Reporte.setNombreReporte("Consulta de suministro sin deuda");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Agua();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaSinSaldo();
        consulta.VP_MensajeSuministroCancelada();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) INVALIDO
    @Test
    public void ConsultaSuministroIncorrecto()  {
        Reporte.setNombreReporte("Consulta de suministro inválido");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Agua();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaNoExistente();
        consulta.VP_MensajeClienteNoExiste();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) Y TRATAR DE PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Agua();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaConSaldo();
        consulta.Click_menuFlotante(); //se debe modificar el número del row para seleccionar el menú correcto
        consulta.Click_BotonPagar();
        consulta.VP_MensajeSinSeleccionarCuenta();
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validación de cuenta bloqueada");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaConSaldo();
        consulta.Seleccionar_cuentaBancaria("448");

        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();
        coord.VP_MensajeCuentaBloqueada();
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ConsultaValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaConSaldo();
        consulta.Seleccionar_cuentaBancaria("448");

        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();
        coord.VP_MensajeFondoInsuficiente();
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR SUMINISTRO (YA MATRICULADO)
    @Test
    public void ConsultaValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Consulta y Validación de coordenadas incorrectas");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalAgua consulta = new ConsultaPrincipalAgua();
        consulta.Click_opcionAgua();
        consulta.Click_registroAguaConSaldo();
        consulta.Seleccionar_cuentaBancaria("448");

        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.IngresarDesafio1();
        coord.IngresarDesafio2();
        coord.IngresarDesafio3();
        coord.VP_MensajeCoordenadasIncorrectas();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
