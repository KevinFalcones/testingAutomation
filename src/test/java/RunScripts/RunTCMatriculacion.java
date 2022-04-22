package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;


public class RunTCMatriculacion {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("TCMatriculacion");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        //[clip2.now 10 | cquito_07 14 | dbravo20 4 | cta1.ahob 13 | cli1.aho 11
        //7 alberto.villa1
        login.Ingresar("8");

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
    public void matriculaTClocales(){
        Reporte.setNombreReporte("Matriculacion de TC terceros");

        MenuTarjetas menu = new MenuTarjetas();
        menu.click_smenu_MatricularTcLocales_old();

        MatricularTC mtc = new MatricularTC();
        mtc.click_boton_nuevatc();

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcLocales.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2banco	3tipo	4numtc	5tipoid	6identificacion	7titular	8alias	9favorito	10mail	11alias2	12mail2	13descripcion
            if (!dato[0].equals("0")) //Escenario 0
                continue;

            if (dato[1].equals("N")) //No se matricula
                continue;

            mtc.ingresa_alias(dato[8]);
            mtc.selecciona_tipo(dato[3]);
            mtc.selecciona_banco(dato[2]);
            mtc.ingresa_numerotc(dato[4]);
            mtc.click_boton_continuar();
            mtc.vp_etiqueta_emailbenef();

            if (!dato[2].equals("Bolivariano"))
            {
                mtc.ingresa_beneficiario(dato[7]);
                mtc.selecciona_tipodoc(dato[5]);
                mtc.ingresa_numerodoc(dato[6]);
            }

            mtc.ingresa_mail(dato[10]);
            mtc.click_boton_continuar();

            Coordenadas coord = new Coordenadas();
            if (primeravez)
            {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            ComprobanteTC ct = new ComprobanteTC();
            ct.vp_mensajeTransaccion_exitoso(0,"COMPROBANTE MATRICULACION TARJETAS","");
            ct.click_boton_nuevamatriculacion();
        }
    }

    @Test
    public void matriculaTClocalesOtrosBancosMasivo() {
        Reporte.setNombreReporte("Matriculacion masiva de TC Otros Bancos");

        MenuTarjetas menu = new MenuTarjetas();
        menu.click_smenu_MatricularTcLocales_old();

        MatricularTC mtc = new MatricularTC();
        mtc.click_boton_nuevatc();

        Boolean primeravez=true;

        for (int i = 1; i <= 4; i++) {
            mtc.ingresa_alias("Auto Test "  + "00" + i);
            mtc.selecciona_tipo("American Express");
            mtc.selecciona_banco("Guayaquil");
            mtc.ingresa_numerotc();
            mtc.click_boton_continuar();
            mtc.vp_etiqueta_emailbenef();

            mtc.ingresa_beneficiario("Prueba Automatizada");
            mtc.selecciona_tipodoc("C");
            mtc.ingresa_numerodoc("0900206988");
            mtc.ingresa_mail("jpererov@bolivariano.com");
            mtc.click_boton_continuar();

            Coordenadas coord = new Coordenadas();
            if (primeravez)
            {
                coord.vp_etiqueta_dispositivo();
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }

            coord.VP_BotonVolver();
            coord.Click_BotonConfirmar();

            ComprobanteTC ct = new ComprobanteTC();
            ct.vp_mensajeTransaccion_exitoso(0, "COMPROBANTE MATRICULACION TARJETAS","");
            ct.click_boton_nuevamatriculacion();
        }
    }


    @Test
    //Solo toma los registros con estos valores: Matricula->S
    public void edicionExitosa(){
        Reporte.setNombreReporte("Edicion exitosa de Tarjetas Terceros y Otros Bancos");
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_tcLocales.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        MenuTarjetas menu = new MenuTarjetas();
        menu.click_smenu_MatricularTcLocales();

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            //0Esc	1Matricula	2banco	3tipo	4numtc	5tipoid	6identificacion	7titular	8alias	9favorito	10mail	11alias2	12mail2	13descripcion
            if (dato[1].equals("N")) //No se matricula
                continue;

            MatricularTC mtc = new MatricularTC();
            mtc.selecciona_agenda(dato[8]);
            mtc.click_btn_editar();
            mtc.ingresa_alias_edit(dato[11]);
            mtc.ingresa_mail_edit(dato[12]);

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
            mtc.click_btn_guardar();
            //Ventana de comprobante
            ComprobanteTC ct = new ComprobanteTC();
            //ct.vp_mensaje_exitoso();
            ct.click_boton_mistcsmatriculadas();
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}
