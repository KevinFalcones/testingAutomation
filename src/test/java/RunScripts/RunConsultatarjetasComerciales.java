package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunConsultatarjetasComerciales {

    @Before
    public void iniciar() {
        Util.Inicio("RecaudaConsultaTarjetaComercial");
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

    //MATRICULAR y CONSULTAR SUMINISTRO SIN DEUDA // ESCENARIO #7
    @Test
    public void MatriculacionyConsultaSinDeuda() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro sin deuda");

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

            if (!dato[0].equals("7")) //Especificar Escenario
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            ConsultaTarjetaComerciales matri = new ConsultaTarjetaComerciales();
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
            ConsultaTarjetaComerciales consulta = new ConsultaTarjetaComerciales();
            //consulta.VP_MensajeSuministroCancelada();
        }
    }

    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO // ESCENARIO #8
    @Test
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");
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

            if (!dato[0].equals("8")) //Especificar Escenario
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            ConsultaTarjetaComerciales matri = new ConsultaTarjetaComerciales();
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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;

                coord.VP_BotonVolver();
                coord.Click_BotonConfirmar();

                Comprobante comprob = new Comprobante();
                comprob.VP_BotonPosicionConsolidada();
                comprob.VP_BotonServiciosMatriculados();
                comprob.VP_BotonPagar();

                ConsultaTarjetaComerciales consulta = new ConsultaTarjetaComerciales();
                consulta.VP_MensajeClienteNoExiste();
            }

        }
    }

    //FLUJO EXITOSO DE MATRICULACION Y CONSULTA // ESCENARIO #9
    @Test
    public void MatriculacionConsulta() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");
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

            if (!dato[0].equals("9")) //Especificar Escenario
                continue;

            MenuPagoServicios menu = new MenuPagoServicios();
            menu.click_menu_pagos();
            menu.click_submenu_pagos();
            menu.Click_BotonMatricular();

            ConsultaTarjetaComerciales matri = new ConsultaTarjetaComerciales();
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
            } catch (Exception e) {
                System.out.println("No aparece pantalla de coordenadas");
                coordenadas = false;

                coord.VP_BotonVolver();
                coord.Click_BotonConfirmar();

                //Confirmar el pago en la pantalla de confirmación de pago
                coord.VP_BotonVolver();
                coord.Click_BotonPagar();


            }
        }
    }
}