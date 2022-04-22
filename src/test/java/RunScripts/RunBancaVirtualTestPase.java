package RunScripts;

import Globales.*;
import TestPages.*;
import TestPages.BancaVirtual.*;
import org.junit.*;

/*Kevin Falcones - Senior Testing Automation
  Automatizacion de Validaciones Antes de Pase a Produccion - KFA_007_CYBERBANK-4678
  Condiciones: Validar Ingreso a cada una de las opciones del Canal
 */
//INI-->KFA_007_CYBERBANK-4678

public class RunBancaVirtualTestPase {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("BancaVirtualTestPase");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 90.0.4324.190 (Official Build) (64-bit)");

        Login login = new Login();
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
    public void run_menuPrincipalTransferir() throws InterruptedException {
        MenuPrincipalTransferir menuPrincipalTransferir = new MenuPrincipalTransferir();
        MenuTransferirCuentas menuTransferirCuentas = new MenuTransferirCuentas();
        MenuTransferirMatricular menuTransferirMatricular = new MenuTransferirMatricular();

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirCuentas.click_btnFncTransferirCuentasPropias("propias");
        menuTransferirCuentas.validate_EtiquetaMenu("propias");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirCuentas.click_btnFncTransferirCuentasTerceros("Terceros");
        menuTransferirCuentas.validate_EtiquetaMenu("Terceros");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirCuentas.click_btnFncTransferirCuentasInternacionales("Internacionales");
        menuTransferirCuentas.validate_EtiquetaMenu("Internacionales");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirMatricular.click_btnFncTransferirMatricularTercerosNew("terceros");
        menuTransferirMatricular.validate_EtiquetaMenu("matriculaciones");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirMatricular.click_btnFncTransferirMatricularExterior("exterior");
        menuTransferirMatricular.validate_EtiquetaMenu("exterior");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirMatricular.click_btnFncTransferirMatricularExteriorNew("exterior");
        menuTransferirMatricular.validate_EtiquetaMenu("matriculaciones");

        menuPrincipalTransferir.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTransferirMatricular.click_btnFncTransferirMatricularDonaciones("Donaciones");
        menuTransferirMatricular.validate_EtiquetaMenu("Donaciones");
    }

    @Test
    public void run_menuPrincipalPagar() throws InterruptedException {
        MenuPrincipalPagar menuPrincipalPagar = new MenuPrincipalPagar();

        menuPrincipalPagar.click_btnMenuPrincipalPagar("Pagar");
        menuPrincipalPagar.click_btnFncPagarServicios("Pagar");
        menuPrincipalPagar.validate_EtiquetaMenu("pagos");
    }

    @Test
    public void run_menuPrincipalTarjetas() throws InterruptedException {
        MenuPrincipalTarjetas menuPrincipalTarjetas = new MenuPrincipalTarjetas();
        MenuTarjetasOperaciones menuTarjetasOperaciones = new MenuTarjetasOperaciones();
        MenuTarjetasPagar menuTarjetasPagar = new MenuTarjetasPagar();
        MenuTarjetasMatricular menuTarjetasMatricular = new MenuTarjetasMatricular();
        MenuTarjetasOtros menuTarjetasOtros = new MenuTarjetasOtros();


        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasPagar.click_btnFncTarjetasPagarPropias("Propias");
        menuTarjetasPagar.validate_EtiquetaMenu("propias");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasPagar.click_btnFncTarjetasPagarTerceros("Terceros");
        menuTarjetasPagar.validate_EtiquetaMenu("Terceros");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasPagar.click_btnFncTarjetasPagarInternacional("Internacionales");
        menuTarjetasPagar.validate_EtiquetaMenu("internacionales");


        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasMatricular.click_btnFncTarjetasMatricularLocales("locales");
        menuTarjetasMatricular.validate_EtiquetaMenu("matriculaciones");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasMatricular.click_btnFncTarjetasMatricularInternacionales("internacionales");
        menuTarjetasMatricular.validate_EtiquetaMenu("internacional");


        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOperaciones.click_btnFncTarjetasOperacionesAvance("Avance");
        menuTarjetasOperaciones.validate_EtiquetaMenu("Avance");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOperaciones.click_btnFncTarjetasOperacionesDiferir("Diferir");
        menuTarjetasOperaciones.validate_EtiquetaMenu("Diferir");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOperaciones.click_btnFncTarjetasOperacionesPlan("Plan");
        menuTarjetasOperaciones.validate_EtiquetaMenu("Plan");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOperaciones.click_btnFncTarjetasOperacionesAdicional("Tarjeta adicional");
        menuTarjetasOperaciones.validate_EtiquetaMenu("Tarjeta adicional");


        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosBloquear("Bloquear");
        menuTarjetasOtros.validate_EtiquetaMenu("Bloquear");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosPuntos("puntos");
        menuTarjetasOtros.validate_EtiquetaMenu("puntos");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosClaveTD("clave");
        menuTarjetasOtros.validate_EtiquetaMenu("clave");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosCupos("cupos");
        menuTarjetasOtros.validate_EtiquetaMenu("cupos");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosCorporativas("Corporativas");
        menuTarjetasOtros.validate_EtiquetaMenu("Corporativas");

        menuPrincipalTarjetas.click_btnMenuPrincipalTransferir("Tarjetas");
        menuTarjetasOtros.click_btnFncTarjetasOtrosUso("uso");
        menuTarjetasOtros.validate_EtiquetaMenu("uso");

    }

        @After
    public void salir()
    {
        Reporte.finReporte();
    }

}
