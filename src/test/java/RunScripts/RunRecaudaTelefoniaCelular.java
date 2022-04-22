package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaTelefoniaCelular {
    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaCelular");
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
    public void MatricularCodigoInvalido() {
        Reporte.setNombreReporte("Validación del formato del suministro en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validación sin alias en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validación sin suministro en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de ALIAS ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
    public void MatricularCodigoYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de SUMINISTRO ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
        coord.VP_MensajeCodigoYaMatriculado();
    }

    //MATRICULAR y CONSULTAR SUMINISTRO SIN DEUDA
    @Test
    public void MatriculacionyConsultaSinDeuda() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean ingresarValor = true;
        Boolean valorARecargar = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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

            //Validación del mensaje de suministro sin deuda
            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.VP_MensajeSuministroCancelada();
        }
    } //No se puede probar si el ambiente está virtualizado.


    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO
    @Test
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean ingresarValor = true;
        Boolean valorARecargar = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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

            //Validación del mensaje de suministro no válido
            ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
            consulta.VP_MensajeClienteNoExiste();
        }
    } //No se puede probar si el ambiente está virtualizado.


    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA BLOQUEADA
    @Test
    public void MatriculacionConsultaPagoConCuentaBloqueada() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean ingresarValor = true;
        Boolean valorARecargar = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[0].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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

            try {
                valor.ingreso_valor(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }

            try {
                valor.seleccionar_valorARecargar(dato[5]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }

            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancariaBloqueada();
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean ingresarValor = true;
        Boolean valorARecargar = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto  5Valor a Recargar	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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

            try {
                valor.ingreso_valor(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }

            try {
                valor.seleccionar_valorARecargar(dato[5]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }

            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancariaSinFondo();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

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

            //Validación del mensaje de fondos insuficientes
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();
            coord.VP_MensajeFondoInsuficiente();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON MONTO A PAGAR SUPERIOR A LA DEUDA
    /*@Test
    public void MatriculacionyConsultaConMontoSuperior() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de suministro con monto superior");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
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

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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
    }*/

    //FLUJO EXITOSO DE MATRICULACION, CONSULTA Y PAGO DEL SUMINISTRO
    @Test
    public void MatriculacionConsultayPagoExistoso()  {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de Celular Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean ingresarValor = true;
        Boolean valorARecargar = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaCelular matri = new MatriculacionTelefoniaCelular();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Celular();
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

            try {
                valor.ingreso_valor(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }

            try {
                valor.seleccionar_valorARecargar(dato[5]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }

            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            //Confirmar el pago en la pantalla de confirmación de pago
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();

            //Validar mensaje exitoso y los botones del Comprobante de pago
            comprob.VP_MensajePagoExitosa();
            comprob.Boton_PosicionConsolidada();
            comprob.Boton_ServiciosMatriculados();
        }
    }

    //FLUJO EXITOSO DE CONSULTA (DE SUMINISTRO MATRICULADO) Y PAGO
    @Test
    public void ConsultayPagoExitoso()  {
        Reporte.setNombreReporte("Consulta y Pago de Agua Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;


        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

        MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularConSaldo();
            try {
                consulta.seleccionar_valorARecargar(dato[5]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }
            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagar(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
            consulta.Seleccionar_cuentaBancaria("448");
            consulta.Click_menuFlotante();
            consulta.Click_BotonPagar();

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
            comprob.VP_BotonPosicionConsolidada();
            comprob.VP_BotonServiciosMatriculados();
        }
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) SIN DEUDA
    @Test
    public void ConsultaSuministroSinDeuda()  {
        Reporte.setNombreReporte("Consulta de suministro sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularSinSaldo();
            consulta.VP_MensajeSuministroCancelada();
        }
    } //No se puede probar si el ambiente está virtualizado.

    //CONSULTAR SUMINISTRO (MATRICULADO) INVALIDO
    @Test
    public void ConsultaSuministroIncorrecto()  {
        Reporte.setNombreReporte("Consulta de suministro inválido");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularNoExistente();
            consulta.VP_MensajeClienteNoExiste();
        }
    } //No se puede probar si el ambiente está virtualizado.

    //CONSULTAR SUMINISTRO (MATRICULADO) Y TRATAR DE PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;


        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularConSaldo();
            try {
                consulta.seleccionar_valorARecargar(dato[5]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }
            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagar(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
            //consulta.Seleccionar_cuentaBancaria("448");
            consulta.Click_menuFlotante1();
            consulta.Click_BotonPagar();
            consulta.VP_MensajeSinSeleccionarCuenta();
        }
    } //No se selecciona el menú flotante.

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validación de cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularConSaldo();
            try {
                consulta.seleccionar_valorARecargar(dato[5]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }
            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagar(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
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
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ConsultaValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularConSaldo();
            try {
                consulta.seleccionar_valorARecargar(dato[5]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }
            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagar(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
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
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR SUMINISTRO (YA MATRICULADO)
    @Test
    public void ConsultaValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Consulta y Validación de Coordenadas Incorrectas");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroCelular.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean valorARecargar = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Valor a Recargar   6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("2")) //Especificar Escenario
            //    continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelefoniaCelular();

            ConsultaPrincipalCelular consulta = new ConsultaPrincipalCelular();
            consulta.Click_opcionCelular();
            consulta.Click_registroCelularConSaldo();
            try {
                consulta.seleccionar_valorARecargar(dato[5]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar valor a recargar");
                valorARecargar = false;
            }
            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagar(dato[4]);
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
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
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }


}
