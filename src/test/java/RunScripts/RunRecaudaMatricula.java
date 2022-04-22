package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunRecaudaMatricula {

    @Before
    public void iniciar() {
        Util.Inicio("RecaudaMatriculacion");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)");

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

    //MATRICULAR SUMINISTRO - TELEFONO
    @Test
    public void MatriculacionExitosaTelefono() {
        Reporte.setNombreReporte("Matriculación de Teléfono");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTelefono.txt");
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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
        }
    }

    //MATRICULAR SUMINISTRO - CELULAR
    @Test
    public void MatriculacionExitosaCelular() {
        Reporte.setNombreReporte("Matriculación de Celular");

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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            //Validación del comprobante de matriculación
            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
        }
    }

    //MATRICULAR SUMINISTRO - TV PAGADA
    @Test
    public void MatriculacionExitosaTvPagada() {
        Reporte.setNombreReporte("Matriculación de Tv Pagada");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTvPagada.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Monto	5Empresa	6Tipo	7Pago
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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
        }
    }

    //MATRICULAR SUMINISTRO - INTERNET
    @Test
    public void MatriculacionExitosaInternet() {
        Reporte.setNombreReporte("Matriculación de Internet");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroInternet.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean TipoId = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1TipoID 2Identi	3Alias	4Monto	5Empresa	6Tipo	7Pago
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

            MatriculacionInternet matri = new MatriculacionInternet();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_Internet();
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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            Comprobante comprob = new Comprobante();
            comprob.VP_MensajeMatriculacionExitosa();
        }
    }

    //MATRICULAR SUMINISTRO - MUNICIPIOS
    @Test
    public void MatriculacionExitosaMunicipios() {
        Reporte.setNombreReporte("Matriculación de Municipios");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroMunicipio.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean coordenadas = true;
        Boolean regional = true;
        Boolean Numero = true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            //0sec	1Identi 2TipoIden  3Número  4Alias   5TipoPago    6Empresa	7Monto	8Tipo	9Pago
            if (linea == 1)
                continue;

            if (dato[9].equals("N")) //No se matricula
                continue;

            //if (!dato[1].equals("1")) //Escenario
            //continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionMunicipio matri = new MatriculacionMunicipio();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_ImpuestosObligaciones();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[6]);
            matri.Seleccionar_TipoDePago(dato[5]);
            try {
                matri.IngresarTipoIdentificacion(dato[2]);
            } catch (Exception e) {
                System.out.println("No seleccionar Tipo de identificación");
                Numero = false;
            }
            matri.IngresarIdentificacion(dato[1]);
            try {
                matri.IngresarNumero(dato[3]);
            } catch (Exception e) {
                System.out.println("No aparece campo para ingresar Número");
                Numero = false;
            }
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
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}