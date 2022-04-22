package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunMatricTarjetasComerciales {

    @Before
    public void iniciar() {
        Util.Inicio("RecaudaTarjetaComerciales");
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

    //MATRICULAR SUMINISTRO QUE NO CUMPLE FORMATO // ESCENARIO #1
    @Test
    public void MatricularCodigoInvalido() {
        Reporte.setNombreReporte("Matriculación Código Invalido");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_suministroTarjetasComerciales.txt");
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

            if (!dato[0].equals("1")) //Escenario
            continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTarjetasComerciales matri = new MatriculacionTarjetasComerciales();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TarjetasComerciales();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeFormatoSuministro();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL ALIAS // ESCENARIO #2
    @Test
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validación sin alias en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTarjetasComerciales.txt");
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

            if (!dato[0].equals("2")) //Escenario
            continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTarjetasComerciales matri = new MatriculacionTarjetasComerciales();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TarjetasComerciales();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarIdentificacion(dato[1]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinAlias();
        }
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL CODIGO // ESCENARIO #3
    @Test
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validación sin suministro en la matriculación");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTarjetasComerciales.txt");
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

            if (!dato[0].equals("3")) //Escenario
            continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTarjetasComerciales matri = new MatriculacionTarjetasComerciales();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TarjetasComerciales();
            matri.click_EmpresaServicio();
            matri.Seleccionar_Empresa(dato[3]);
            matri.IngresarAlias(dato[2]);
            matri.VP_BotonCancelar();
            matri.VP_BotonMatricular();
            matri.VP_MensajeSinSuministro();
        }
    }

    //MATRICULAR SUMINISTRO CON ALIAS YA REGISTRADO // ESCENARIO #4
    @Test
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de ALIAS ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTarjetasComerciales.txt");
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

            if (!dato[0].equals("4")) //Escenario
            continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTarjetasComerciales matri = new MatriculacionTarjetasComerciales();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TarjetasComerciales();
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

    //MATRICULAR SUMINISTRO CON CÓDIGO YA REGISTRADO // ESCENARIO #5
    @Test
    public void MatricularCodigoYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de SUMINISTRO ya matriculado");

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroTarjetasComerciales.txt");
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

            if (!dato[0].equals("5")) //Escenario
            continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            MatriculacionTarjetasComerciales matri = new MatriculacionTarjetasComerciales();
            matri.VP_TipoServicio();
            matri.click_TipoServicio();
            matri.Seleccionar_TarjetasComerciales();
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
    } // ESCENARIO #6

    {
        Reporte.finReporte();
    }

}
