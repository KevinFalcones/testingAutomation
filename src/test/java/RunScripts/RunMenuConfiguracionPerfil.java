package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*Kevin Falcones - Gestion de Automatizacion y Pruebas - 2021/11/08
  Comprobar visualizacion de opciones de configuracion de perfil segun segmento - KFA_002_CfgPerfil_CyberBank-63
  Condiciones: Probar con distintos segmentos de clientes
*/
//INI-->KFA_002_CfgPerfil_CyberBank-63

public class RunMenuConfiguracionPerfil {
    @Before
    public void iniciar_Chrome()
    {
        Util.Inicio("RunConfiguracionPerfil");
        Login login = new Login();
        //7 carla.loor1 ->tarjeta
        //10 clip2.now  ->otp
        //11 cli1.aho
        //9 angel.navia3
        login.Ingresar("6");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }

    @Test
    public void verificaMenuMiPerfil(){
        Reporte.setNombreReporte("Verifica Menu Mi Perfil");

        MenuConfiguracionPerfil mcp = new MenuConfiguracionPerfil();
        mcp.click_btnMenuMiPerfil();
        mcp.click_btnMenuMiPerfil();
    }

    @Test
    public void verificaCerrarSesion(){
        Reporte.setNombreReporte("Verifica Funcionalidad Cerrar Sesion");

        MenuConfiguracionPerfil mcp = new MenuConfiguracionPerfil();
        mcp.click_btnMenuMiPerfil();
        mcp.click_btnCerrarSesion();
    }

    @Test
    //Solo toma los registros con estos valores: Esc->1 Matricula->S
    public void verificaFncNombreUsuario(){
        Reporte.setNombreReporte("Verifica Funcionalidad de Nombre de Usuario");

        MenuConfiguracionPerfil mcp = new MenuConfiguracionPerfil();
        mcp.click_btnMenuMiPerfil();
        mcp.click_btnMenuCfgPerfil();
        mcp.click_btnCfgNombreUsuario();
        mcp.click_btnCfgNombreUsuarioExit();

    }

    @Test
    //Solo toma los registros con estos valores: Esc->1 Matricula->S
    public void verificaFncContraseniaUsuario(){
        Reporte.setNombreReporte("Verifica Funcionalidad de Contraseña de Usuario");

        MenuConfiguracionPerfil mcp = new MenuConfiguracionPerfil();
        mcp.click_btnMenuMiPerfil();
        mcp.click_btnMenuCfgPerfil();
        mcp.click_btnCfgClaveUsuario();

    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }

//FIN-->KFA_002_CfgPerfil_CyberBank-63
}
