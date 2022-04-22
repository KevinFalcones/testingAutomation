package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/*Kevin Falcones - Gestion de Automatizacion y Pruebas
  Automatizacion de Solicitudes de Chequera - KFA_004_CYBERBANK-3697
  Condiciones: Happy Path
 */
//INI-->KFA_004_CYBERBANK-3697

public class RunSolicitarChequera {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("SolicitarChequera");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 96.0.4664.45 (Official Build) (64-bit)"  );

        Login login = new Login();
        login.Ingresar("8");

        PreguntaSeguridad preSeg = new PreguntaSeguridad();
        preSeg.ResponderCorrectamente();

        RegistroEquipo regEqu = new RegistroEquipo();
        regEqu.vp_etiqueta_nomatriculado();
        regEqu.click_continuar();

        PosicionConsolidada posCon = new PosicionConsolidada();
        posCon.vp_etiqueta_saludo();
    }

    @Test
    public void run_solicitarChequeraCliente(){
        Reporte.setNombreReporte("Solicitar Chequera Retira Cliente");

        SolicitarChequera s = new SolicitarChequera();
        s.click_menuSolicitarChequera();
        s.get_validaPantallaSolicitarChequera();
        s.click_selectCuentaChequera("000XXXX185-CORRIENTE-USD");
        s.click_selectTipoChequera("50 CHEQUES CON TALON");
        s.set_selectOficinaEntregar("MATRIZ");
        s.click_selectRetiroTercero("No");
        s.set_selectCuentaDebitar("000XXXX185-CORRIENTE-USD");
        s.click_btnContinuar();
        s.click_btnContinuar();

        //Ingreso de Coordenadas
        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        coor.VP_MensajeCoordenadasCorrectas();
        coor.Click_BotonConfirmar();

        if (s.get_mensajeResultadoTrx().contains("se ha realizado con"))
        {
            s.post_mensajeResultadoTrx("Verificacion de mensaje exitoso", s.get_mensajeResultadoTrx());
        }
        else
        {
            s.post_mensajeResultadoTrx("Verificacion de mensaje NO exitoso", s.get_mensajeResultadoTrx());
        }
    }

    @Test
    public void run_solicitarChequeraTercero(){
        Reporte.setNombreReporte("Solicitar Chequera Retira Tercero");

        SolicitarChequera s = new SolicitarChequera();
        s.click_menuSolicitarChequera();
        s.get_validaPantallaSolicitarChequera();
        s.click_selectCuentaChequera("000XXXX185-CORRIENTE-USD");
        s.click_selectTipoChequera("100 CHEQUES CON TALON");
        s.set_selectOficinaEntregar("MATRIZ");
        s.click_selectRetiroTercero("Si");
        s.set_datosRetiroTercero("Kevin Falcones", "Cedula", "0930033196");
        s.set_selectCuentaDebitar("000XXXX185-CORRIENTE-USD");
        s.click_btnContinuar();
        s.click_btnContinuar();

        //Ingreso de Coordenadas
        Coordenadas coor = new Coordenadas();
        coor.escribe_desafio0();
        coor.escribe_desafio1();
        coor.escribe_desafio2();
        coor.VP_MensajeCoordenadasCorrectas();
        coor.Click_BotonConfirmar();

        if (s.get_mensajeResultadoTrx().contains("se ha realizado con"))
        {
            s.post_mensajeResultadoTrx("Verificacion de mensaje exitoso", s.get_mensajeResultadoTrx());
        }
        else
        {
            s.post_mensajeResultadoTrx("Verificacion de mensaje NO exitoso", s.get_mensajeResultadoTrx());
        }
    }

    @Test
    public void run_solicitarChequeraDataPool(){

        Reporte.setNombreReporte("Solicitar Chequera con DataPool");
        List<String> lst_dataPool = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_solicitarChequera.txt");
        Iterator<String> lst_dp = lst_dataPool.iterator();
        String[] lst_dpDato;
        int linea = 0;
        Boolean unicaAutenticacion=true;

        SolicitarChequera s = new SolicitarChequera();

        while (lst_dp.hasNext()) {
            lst_dpDato = lst_dp.next().split("\t");
            linea = linea + 1;
            if (linea == 1) continue;

            //sec	Cuenta	Tipo	Oficina	RetiroSoN	Nombre	Documento	Numero
            s.click_menuSolicitarChequera();
            s.get_validaPantallaSolicitarChequera();
            s.click_selectCuentaChequera(lst_dpDato[1]);
            s.click_selectTipoChequera(lst_dpDato[2]);
            s.set_selectOficinaEntregar(lst_dpDato[3]);
            s.click_selectRetiroTercero(lst_dpDato[4]);

            if (lst_dpDato[4].contentEquals("Si") )
            {
                s.set_datosRetiroTercero(lst_dpDato[5], lst_dpDato[6], lst_dpDato[7]);
            }

            s.set_selectCuentaDebitar(lst_dpDato[1]);
            s.click_btnContinuar();
            s.click_btnContinuar();

            if (unicaAutenticacion)
            {
                //Ingreso de Coordenadas
                Coordenadas coordenada = new Coordenadas();
                coordenada.escribe_desafio0();
                coordenada.escribe_desafio1();
                coordenada.escribe_desafio2();
                coordenada.VP_MensajeCoordenadasCorrectas();
                coordenada.Click_BotonConfirmar();

                unicaAutenticacion = false;
            }
            else
            {
                s.click_btnContinuar();
            }

            try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }


            if (s.get_mensajeResultadoTrx().contains("se ha realizado con"))
            {
                s.post_mensajeResultadoTrx("Verificacion de mensaje exitoso", s.get_mensajeResultadoTrx());
            }
            else
            {
                s.post_mensajeResultadoTrx("Verificacion de mensaje NO exitoso", s.get_mensajeResultadoTrx());
            }
        }
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
