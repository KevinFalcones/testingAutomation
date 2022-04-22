package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunCtaMatriculacion {
    @Before
    public void iniciar_Chrome()
    {
        Util.Inicio("RunMatriculaCuentaTerceros");
        Login login = new Login();
        //[clip2.now 10 | cquito_07 14 | dbravo20 4 | cta1.ahob 13 | cli1.aho 11
        //7 alberto.villa1
        login.Ingresar("19");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    //Solo toma los registros con estos valores: Esc->0 Matricula->S
    public void matriculacionTercerosBBExitosa_old(){
        Reporte.setNombreReporte("Matriculacion exitosa de Cuentas Terceros BB");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros_old();

        MatricularCtaTerceros mt = new MatricularCtaTerceros();
        mt.VP_NombreFuncionalidad();
        mt.click_btn_nuevaCuenta_old();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2BcoDestino	3TipoCta	4NumCta	5Beneficiario	6TipoID	7NumID	8Mail	9Alias	10Alias2	11Mail2	12Descripcion
            if (!dato[0].equals("0")) //Escenario 0
                continue;

            if (dato[1].equals("N")) //No se matricula
                continue;

            //Ventana de cuentas matriculadas
            mt.selecciona_banco_old(dato[2]);
            mt.selecciona_tipo_cta_old(dato[3], dato[2]);
            mt.ingresa_num_cta(dato[4]);

            mt.click_btn_continuar_old();

            if (!dato[2].equals("Bolivariano")) {
                mt.ingresa_beneficiario(dato[5]);
                mt.selecciona_tipo_identif(dato[6]);
                mt.ingresa_identificacion(dato[7]);
            }

            mt.ingresa_mail_beneficiario_old(dato[8]);
            mt.ingresar_alias_old(dato[9]);
            mt.click_btn_continuar_old();

            //Segundo factor
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.escribe_desafio0();
                coor.escribe_desafio1();
                coor.escribe_desafio2();
                coor.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coor.VP_BotonVolver();
            coor.Click_BotonConfirmar();
            //Ventana de comprobante
            ComprobanteCta ct = new ComprobanteCta();
            ct.vp_mensajeTransaccion_exitoso("ya puedes transferir dinero a ");
            ct.click_boton_nuevamatriculacion_old();

            mt = new MatricularCtaTerceros();
        }
    }

    @Test
    //Solo toma los registros con estos valores: Esc->0 Matricula->S
    public void esc0_matriculacionTercerosBBExitosa() {
        Reporte.setNombreReporte("Matriculacion exitosa de Cuentas Terceros BB");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        MatricularCtaTerceros mt = new MatricularCtaTerceros();
        mt.VP_NombreFuncionalidad();
        mt.click_btn_nuevaCuenta();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2BcoDestino	3TipoCta	4NumCta	5Beneficiario	6TipoID	7NumID	8Mail	9Alias	10Alias2	11Mail2	12Descripcion
            if (!dato[0].equals("0")) //Escenario 0
                continue;

            if (dato[1].equals("N")) //No se matricula
                continue;

            //Ventana de cuentas matriculadas
            mt.selecciona_banco(dato[2]);
            mt.ingresa_num_cta(dato[4]);
            mt.selecciona_tipo_cta(dato[3]);

            mt.click_btn_continuar();
            mt.ingresar_alias(dato[9]);
            mt.ingresa_mail_beneficiario(dato[8]);

            //Segundo factor
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

            mt.click_btn_matricular();
            //Ventana de comprobante
            ComprobanteCta ct = new ComprobanteCta();
            ct.vp_mensaje_exitoso();
            //ct.click_boton_X();
            //mt.retrocede_nueva_cuenta();
            ct.click_boton_nuevamatriculacion();
            mt = new MatricularCtaTerceros();
        }
    }

    @Test
    //Solo toma los registros con estos valores: Esc->1 Matricula->S
    public void esc1_matriculacionOtrosBancosExitosa(){
        Reporte.setNombreReporte("Matriculacion exitosa de Cuentas Otros Bancos BB");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        MatricularCtaTerceros mt = new MatricularCtaTerceros();
        mt.VP_NombreFuncionalidad();
        mt.click_btn_nuevaCuenta();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2BcoDestino	3TipoCta	4NumCta	5Beneficiario	6TipoID	7NumID	8Mail	9Alias	10Alias2	11Mail2	12Descripcion
            if (!dato[0].equals("1")) //Escenario 1
                continue;

            if (dato[1].equals("N")) //No se matricula
                continue;

            //Ventana de cuentas matriculadas
            mt.selecciona_banco(dato[2]);
            mt.ingresa_num_cta(dato[4]);
            mt.selecciona_tipo_cta(dato[3]);

            if (!dato[2].equals("Bolivariano")) {
                mt.ingresa_beneficiario(dato[5]);
                mt.selecciona_tipo_identif(dato[6]);
                mt.ingresa_identificacion(dato[7]);
            }

            mt.click_btn_continuar();
            mt.ingresar_alias(dato[9]);
            mt.ingresa_mail_beneficiario(dato[8]);

            //Segundo factor
            Coordenadas coor = new Coordenadas();
            if (primeravez)
            {
                coor.vp_etiqueta_dispositivo();
                mt.avanza_pagina();
                coor.autenticacion(true);
                mt.avanza_pagina();
                if (coor.existe_msj_claveIncorrecta()) {

                    coor.autenticacion(false);

                    if (!coor.existe_msj_claveIncorrecta())
                        primeravez = false;
                }
                else
                    primeravez = false;
            }

            mt.click_btn_matricular();
            //Ventana de comprobante
            ComprobanteCta ct = new ComprobanteCta();
            ct.vp_mensaje_exitoso();
            //ct.click_boton_X();
            //mt.retrocede_nueva_cuenta();
            ct.click_boton_nuevamatriculacion();
            mt = new MatricularCtaTerceros();
        }
    }

    //Escenario de matriculacion masiva de cuentas de terceros otros bancos.
    @Test
    public void matriculacionMasivaCta()
    {
        Reporte.setNombreReporte("Matriculacion Masiva exitosa de Cuentas Terceros y Otros Bancos");
        Boolean primeravez=true;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        MatricularCtaTerceros mt = new MatricularCtaTerceros();
        mt.VP_NombreFuncionalidad();
        mt.click_btn_nuevaCuenta();

        for (int i = 1;i<3;i++) {
            mt.selecciona_banco("Pichincha");
            mt.ingresa_num_cta("-");

            if ( i % 2 == 0 )
                mt.selecciona_tipo_cta("AHORRO");

            if ( i % 2 != 0 )
                mt.selecciona_tipo_cta("CORRIENTE");

            mt.ingresa_beneficiario("Beneficiario");
            mt.selecciona_tipo_identif("C");
            mt.ingresa_identificacion("0923139539");
            mt.click_btn_continuar();

            mt.ingresar_alias("Auto Test "  + "00" + i);
            mt.ingresa_mail_beneficiario("jpererov@bolivariano.com");

            //Ventana de confirmacion
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

            mt.click_btn_matricular();
            //Ventana de comprobante
            ComprobanteCta ct = new ComprobanteCta();
            ct.vp_mensaje_exitoso();
            ct.click_boton_nuevamatriculacion();

            mt = new MatricularCtaTerceros();
        }
    }

    @Test
    //Solo toma los registros con estos valores: Matricula->S
    public void edicionExitosa(){
        Reporte.setNombreReporte("Edicion exitosa de Cuentas Terceros y Otros Bancos");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2BcoDestino	3TipoCta	4NumCta	5Beneficiario	6TipoID	7NumID	8Mail	9Alias	10Alias2	11Mail2	12Descripcion
            if (dato[1].equals("N")) //No se matricula
                continue;

            MatricularCtaTerceros mt = new MatricularCtaTerceros();
            mt.VP_NombreFuncionalidad();
            mt.selecciona_agenda(dato[9]);
            mt.click_btn_editar();
            mt.ingresa_alias_edit(dato[10]);
            mt.ingresa_mail_edit(dato[11]);

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
            mt.click_btn_guardar();
            //Ventana de comprobante
            ComprobanteCta ct = new ComprobanteCta();
            //ct.vp_mensaje_exitoso();
            ct.click_boton_misctasmatriculadas();
        }
    }

    /*Kevin Falcones - Gestion de Automatizacion y Pruebas
      Eliminacion Exitosa de Cuentas Terceros y Otros Bancos - KFA_001_TestEliminarCta
      Condiciones: Solo toma los registros con estos valores: Matricula->S
     */
    //INI-->KFA_001_TestEliminarCta
    @Test
    public void run_eliminacionCancelada() {
        Reporte.setNombreReporte("Eliminacion Cancelada de Cuentas Terceros y Otros Bancos");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            if (dato[1].equals("N")) //No se elimina
                continue;

            MatricularCtaTerceros mt = new MatricularCtaTerceros();
            mt.VP_NombreFuncionalidad();
            mt.selecciona_agenda(dato[9]);
            mt.click_btnEliminarCtaMenu();
            mt.click_btnEliminarCtaMenuNo();
            mt.click_btnEliminarCtaMenuX();
        }
    }

    @Test
    public void run_eliminacionExitosaVerCtas() {
        Reporte.setNombreReporte("Eliminacion Exitosa de Cuentas Terceros y Otros Bancos y Ver");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir") + "/archivos/dp_ctasTerceros.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;

        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            if (dato[1].equals("N")) //No se matricula
                continue;

            MatricularCtaTerceros mt = new MatricularCtaTerceros();
            mt.VP_NombreFuncionalidad();
            mt.selecciona_agenda(dato[9]);
            mt.click_btnEliminarCtaMenu();
            mt.click_btnEliminarCtaMenuSi();
            mt.get_msjEliminadaCta();
            mt.click_btnVerCtasMatriculadas();
        }
    }

    @Test
    public void run_eliminacionMasiva(){
        Reporte.setNombreReporte("Eliminacion Exitosa de Cuentas Terceros y Otros Bancos");
        MenuTransferir menu = new MenuTransferir();
        menu.click_smenu_matTerceros();

        MatricularCtaTerceros del = new MatricularCtaTerceros();
        try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
        del.chk_eliminar();
        try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
        del.chk_seleccion();
        del.click_btnEliminaSeleccion();
        del.click_btnConfirmaEliminacion();
    }



    //FIN-->KFA_001_TestEliminarCta



//<div id="error" class="error-ac error-ac-show">No hay cambios para guardar</div>
    @After
    public void salir()
    {
        //Util.driver.quit();
        Reporte.finReporte();
    }
}
