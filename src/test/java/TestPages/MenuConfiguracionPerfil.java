package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.LocalDateTime;
import java.util.List;

/*Kevin Falcones - Gestion de Automatizacion y Pruebas - 2021/11/08
  Comprobar visualizacion de opciones de configuracion de perfil segun segmento - KFA_002_CfgPerfil_CyberBank-63
  Condiciones: Probar con distintos segmentos de clientes
*/
//INI-->KFA_002_CfgPerfil_CyberBank-63

public class MenuConfiguracionPerfil {
    public MenuConfiguracionPerfil() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="menuMiPerfil")
    WebElement vwe_btnMenuMiPerfil = null;

    @FindBy(id="menuOperador")
    WebElement vwe_btnMenuOperador = null;

    @FindBy(xpath="//a[@href='/BOLI-ebanking/usuario/miPerfil.htm']")
    WebElement vwe_btnMenuCfgPerfil = null;

    @FindBy(xpath="//a[@href='/BOLI-ebanking/usuario/eliminarToken.htm']")
    WebElement vwe_btnCerrarSesion = null;

    @FindBy(id="nombUserCab")
    WebElement vwe_btnCfgNombreUsuario = null;

    @FindBy(name="_eventId_cancelar")
    WebElement vwe_btnCfgNombreUsuarioExit = null;

    @FindBy(id="claveCab")
    WebElement vwe_btnCfgClaveUsuario = null;

    //@FindBy(name="_eventId_cancelar")
    //WebElement vwe_btnCfgClaveUsuarioExit = null;


    public void click_btnMenuMiPerfil()
    {
        try { Thread.sleep(1500); } catch (InterruptedException exception) { exception.printStackTrace(); }
        String actual = vwe_btnMenuMiPerfil.getText();
        vwe_btnMenuMiPerfil.click();
        Util.assert_contiene("Se accede a Menu Mi Perfil", "Click en boton", actual,"Nombre.:", true, "N");
    }

    public void click_btnMenuCfgPerfil()
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnMenuCfgPerfil.getText();
        Util.assert_contiene("Se accede a Menu Usuario", "Click en boton", actual,"cambio de usuario", true, "N");
        vwe_btnMenuCfgPerfil.click();
        Util.assert_contiene("Se accede a Menu Configuracion de Perfil", "Click en boton", actual,"cambio de usuario", true, "N");

    }

    public void click_btnCfgNombreUsuario()
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnCfgNombreUsuario.getText();
        vwe_btnCfgNombreUsuario.click();
        Util.assert_contiene("Se accede a Funcionalidad Nombre Usuario", "Click en boton", actual,"Nombre de usuario", true, "N");
    }

    public void click_btnCfgNombreUsuarioExit()
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnCfgNombreUsuarioExit.getText();
        vwe_btnCfgNombreUsuarioExit.click();
        Util.assert_contiene("Se sale de la Funcionalidad Nombre Usuario", "Click en boton", actual,"Cancelar", true, "N");

    }

    public void click_btnCfgClaveUsuario()
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnCfgClaveUsuario.getText();
        Util.assert_contiene("Se sale de la Funcionalidad Contrasenia", "Click en botón", actual,"Contrase", false, "N");
        vwe_btnCfgClaveUsuario.click();
    }

    public void click_btnCerrarSesion()
    {
        try { Thread.sleep(1500); } catch (InterruptedException exception) { exception.printStackTrace(); }
        String actual = vwe_btnCerrarSesion.getText();
        Util.assert_contiene("Se Cierra Sesion", "Click en boton", actual,"", true, "N");
        vwe_btnCerrarSesion.click();
        Util.assert_contiene("Se accede a Menu Mi Perfil", "Click en boton", actual,"", true, "N");
    }


//FIN-->KFA_002_CfgPerfil_CyberBank-63

}
