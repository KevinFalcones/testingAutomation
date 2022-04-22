package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/*
script para consultar y eliminar el depósito express activo.

--OBTENER MIS
select * from cobis..cl_ente where en_ced_ruc='#########'

--CONSULTAR EN TABLA
select * from cob_internet.dbo.cash_t_cuenta_matriculada
where  cm_ente=### and  cm_agrupacion='DEPOSITO' and cm_transaccion='MOVIL' and cm_estado='V'

--ELIMINAR ACTIVACIÓN DE DEPÓSITO EXPRESS
delete  from cob    _internet.dbo.cash_t_cuenta_matriculada
where  cm_ente=### and  cm_agrupacion='DEPOSITO' and cm_transaccion='MOVIL' and cm_estado='V'
*/

public class RunActivaDepositoExpress {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("Run_DepExpressRechazoActivacion");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 94.0.4606.81 (Official Build) (64-bit)");

        Login login = new Login();
        login.Ingresar("14");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Activa deposito express
    public void activacion(){
        ActivacionDepositoExpress dExpress = new ActivacionDepositoExpress();
        dExpress.MenuSolicitar();
        dExpress.chk_terminos();
        dExpress.setBtnAceptar();

        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        coor.Click_BotonConfirmar();
        dExpress.vp_tranExitosa();
    }

    @Test //Rechaza la activación deposito express, se direcciona a posición consolidada
    public void rechazoActivacion() {
        ActivacionDepositoExpress dExpress = new ActivacionDepositoExpress();
        dExpress.MenuSolicitar();
        dExpress.setBtnRechazar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test //Cliente cliente con servicio previamente activado
    public void servicioActivado(){
        ActivacionDepositoExpress dExpress = new ActivacionDepositoExpress();
        dExpress.MenuSolicitar();
        dExpress.vp_servicioActivo();
    }

    @After
    public void salir(){Reporte.finReporte();}

}

