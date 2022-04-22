package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaTelefoniaFija {

    @Before
    public void iniciar()
    {
        Util.Inicio("RecaudaTelefoniaFija");
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
        Reporte.setNombreReporte("Validaci�n del formato del suministro en la matriculaci�n");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
            matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeFormatoSuministro();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL ALIAS
    @Test
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validaci�n sin alias en la matriculaci�n");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinAlias();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL CODIGO
    @Test
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validaci�n sin suministro en la matriculaci�n");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarAlias(dato[4]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinSuministro();
        }
    }

    //MATRICULAR SUMINISTRO CON ALIAS YA REGISTRADO
    @Test
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculaci�n de ALIAS ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

    //MATRICULAR SUMINISTRO CON C�DIGO YA REGISTRADO
    @Test
    public void MatricularCodigoYaRegistrado() {
        Reporte.setNombreReporte("Matriculaci�n de SUMINISTRO ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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
        Reporte.setNombreReporte("Matriculaci�n y Consulta de suministro sin deuda");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

            //Validaci�n del mensaje de suministro sin deuda
            ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
            consulta.VP_MensajeSuministroCancelada();
        }
    }

    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO
    @Test
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculaci�n y Consulta de suministro no existe");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

            //Validaci�n del mensaje de suministro no v�lido
            ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
            consulta.VP_MensajeClienteNoExiste();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA BLOQUEADA
    @Test
    public void MatriculacionConsultaPagoConCuentaBloqueada() {
        Reporte.setNombreReporte("Matriculaci�n, Consulta y Pago con cuenta bloqueada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

            //Validaci�n del mensaje de la cuenta bloqueada
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();
            coord.VP_MensajeCuentaBloqueada();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA CON FONDOS INSUFICIENTES
    @Test
    public void MatriculacionConsultaPagoConCuentaSinFondo() {
        Reporte.setNombreReporte("Matriculaci�n, Consulta y Pago con cuenta con fondos insuficientes");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

            //Validaci�n del mensaje de fondos insuficientes
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();
            coord.VP_MensajeFondoInsuficiente();
        }
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON MONTO A PAGAR SUPERIOR A LA DEUDA
    @Test
    public void MatriculacionyConsultaConMontoSuperior() {
        Reporte.setNombreReporte("Matriculaci�n, Consulta y Pago de suministro con monto superior");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi	2Alias	3Empresa	4Monto	5Tipo	6Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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
    public void MatriculacionConsultayPagoTelefono()  {
        //*****
        //
        //*****
        Reporte.setNombreReporte("Matriculaci�n, Consulta y Pago de Tel�fono Exitoso");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTelefono.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Regional   2TipId  3Identi 4Alias	5Empresa	6Monto	7Tipo	8Pago
            if (linea == 1)
                continue;

            if (dato[8].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTelefoniaFija matri = new MatriculacionTelefoniaFija();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Telefono();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[5]);

            try {
                matri.IngresarRegional(dato[1]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar regional");
                regional = false;
            }
            try {
                matri.SeleccionarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No aparece campo para seleccionar Tipo de Identificacion");
                TipoId = false;
            }

            matri.IngresarIdentificacion(dato[3]);
            matri.IngresarAlias(dato[4]);
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

            //Confirmar el pago en la pantalla de confirmaci�n de pago
            coord.VP_BotonVolver();
            coord.Click_BotonPagar();

            //S�lo se puede validar los botones del Comprobante de pago
            comprob.Boton_PosicionConsolidada();
            comprob.Boton_ServiciosMatriculados();
        }
    }

    //FLUJO EXITOSO DE CONSULTA (DE SUMINISTRO MATRICULADO) Y PAGO
    @Test
    public void ConsultayPagoTelefono()  {
        Reporte.setNombreReporte("Consulta y Pago de Tel�fono Exitoso");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoConSaldo();
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
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoSinSaldo();
        consulta.VP_MensajeSuministroCancelada();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) INVALIDO
    @Test
    public void ConsultaSuministroIncorrecto()  {
        Reporte.setNombreReporte("Consulta de suministro inv�lido");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoNoExistente();
        consulta.VP_MensajeClienteNoExiste();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) Y TRATAR DE PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoConSaldo();
        consulta.Click_menuFlotante(); //se debe modificar el n�mero del row para seleccionar el men� correcto
        consulta.Click_BotonPagar();
        consulta.VP_MensajeSinSeleccionarCuenta();
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ConsultaValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Consulta y Validaci�n de cuenta bloqueada");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoConSaldo();
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
        Reporte.setNombreReporte("Consulta y Validaci�n de fondos insuficientes");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoConSaldo();
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
        Reporte.setNombreReporte("Consulta y Validaci�n de fondos insuficientes");

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_TelefoniaFija();

        ConsultaPrincipalTelefono consulta = new ConsultaPrincipalTelefono();
        consulta.Click_opcionTelefono();
        consulta.Click_registroTelefonoConSaldo();
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
