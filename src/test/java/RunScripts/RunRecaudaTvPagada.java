package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaTvPagada {
    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaTvPagada");
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeFormatoSuministro();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL ALIAS
    @Test
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validación sin alias en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinAlias();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL CODIGO
    @Test
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validación sin suministro en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarAlias(dato[3]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinSuministro();
        }
    }

    //MATRICULAR SUMINISTRO CON ALIAS YA REGISTRADO
    @Test
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de ALIAS ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
    //**No se puede usar este test si el ambiente está virtualizado.
    @Test
    public void MatriculacionyConsultaSinDeuda() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.VP_MensajeSuministroCancelada();
        }
    } // No se puede probar en ambiente virtualizado

    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO
    //**No se puede usar este test si el ambiente está virtualizado.
    @Test
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.VP_MensajeClienteNoExiste();
        }
    } // No se puede probar en ambiente virtualizado

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA BLOQUEADA
    @Test
    public void MatriculacionConsultaPagoConCuentaBloqueada() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
        //** CLaro Tv Satelital permite pagos menores a la deuda.
    @Test
    public void MatriculacionyConsultaConMontoSuperior() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de suministro con monto superior");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();
            valor.VP_MensajeMontoSuperior();

            /*//Confirmar el pago en la Pantalla de Confirmación
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

            valor.VP_MensajeMontoSuperior();*/
        }
    }

    //FLUJO EXITOSO DE MATRICULACION, CONSULTA Y PAGO DEL SUMINISTRO
    @Test
    public void MatriculacionConsultayPagoExistoso()  {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
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

            MatriculacionTvPagada matri = new MatriculacionTvPagada();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TVPagada();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);
            try {
                matri.SeleccionarTipoIdentificacion(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }
            matri.IngresarIdentificacion(dato[2]);
            matri.IngresarAlias(dato[3]);
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
            valor.Click_CampoCuenta();
            valor.Seleccionar_cuentaBancaria();
            valor.VP_BotonCancelar();
            valor.VP_BotonPagar();

            //Confirmar el pago en la Pantalla de Confirmación
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

            //Confirmar el pago en la pantalla de confirmación de pago
            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            //Validar mensaje exitoso y los botones del Comprobante de pago
            comprob.VP_MensajePagoExitosa();
            comprob.Boton_PosicionConsolidada();
            comprob.Boton_ServiciosMatriculados();
        }
    }

    //FLUJO EXITOSO DE CONSULTA (DE SUMINISTRO MATRICULADO) Y PAGO
    @Test
    public void ConsultayPagoExitoso()  {
        Reporte.setNombreReporte("Consulta y Pago Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVConSaldo();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagarTV(dato[4]);           //Para Claro TV Satelital se necesita borrar primero el valor
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

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVSinSaldo();
            consulta.VP_MensajeSuministroCancelada();
        }
    } //No se puede probar si el ambiente está virtualizado.

    //CONSULTAR SUMINISTRO (MATRICULADO) INVALIDO
    @Test
    public void ConsultaSuministroIncorrecto()  {
        Reporte.setNombreReporte("Consulta de suministro inválido");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVNoExistente();
            consulta.VP_MensajeClienteNoExiste();
        }
    } //No se puede probar si el ambiente está virtualizado.

    //CONSULTAR SUMINISTRO (MATRICULADO) Y TRATAR DE PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVConSaldo();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagarTV(dato[4]);           //Para Claro TV Satelital se necesita borrar primero el valor
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }

            consulta.Click_menuFlotante(); //se debe modificar el número del row para seleccionar el menú correcto
            consulta.Click_BotonPagar();
            consulta.VP_MensajeSinSeleccionarCuenta();
        }
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validación de cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVConSaldo();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagarTV(dato[4]);           //Para Claro TV Satelital se necesita borrar primero el valor
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
            consulta.Seleccionar_cuentaBancaria("448");
            consulta.Click_menuFlotante(); //se debe modificar el número del row para seleccionar el menú correcto
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
            coord.VP_MensajeCuentaBloqueada();
        }
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ConsultaValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVConSaldo();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagarTV(dato[4]);           //Para Claro TV Satelital se necesita borrar primero el valor
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
            consulta.Seleccionar_cuentaBancaria("448");
            consulta.Click_menuFlotante(); //se debe modificar el número del row para seleccionar el menú correcto
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
            coord.VP_MensajeFondoInsuficiente();
        }
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR SUMINISTRO (YA MATRICULADO)
    @Test
    public void ConsultaValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Consulta y Validación de fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;
        Boolean ingresarValor = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Empresa	5Monto	6Tipo	7Pago
            if (linea == 1)
                continue;

            if (dato[7].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Opcion_TelevisionPagada();

            ConsultaPrincipalTvPagada consulta = new ConsultaPrincipalTvPagada();
            consulta.Click_opcionTV();
            consulta.Click_registroTVConSaldo();

            IngresoValorAPagar valor = new IngresoValorAPagar();
            try {
                valor.ingreso_valorAPagarTV(dato[4]);           //Para Claro TV Satelital se necesita borrar primero el valor
            }
            catch(Exception e)
            {
                System.out.println("No aparece campo para ingresar monto");
                ingresarValor = false;
            }
            consulta.Seleccionar_cuentaBancaria("448");
            consulta.Click_menuFlotante(); //se debe modificar el número del row para seleccionar el menú correcto
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


}
