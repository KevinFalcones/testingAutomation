package TestPages.BancaVirtual;

import Globales.Util;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Kevin Falcones - Senior Testing Automation
  Automatizacion de Validaciones Antes de Pase a Produccion - KFA_009_CYBERBANK-4818
  Condiciones: Validar Ingreso a cada una de las opciones del Canal
 */
//INI-->KFA_009_CYBERBANK-4818

public class MenuPrincipalTarjetas {

    @FindBy(id="TARJETAS")
    WebElement vwe_btnMenuPrincipalTarjetas = null;

    //Variables Generales
    String vGral_menu = "Menu Principal ";
    String vGral_detalleMenu = "Verificacion de etiqueta de menu ";

    public MenuPrincipalTarjetas() {
        PageFactory.initElements(Util.driver, this);
    }

    public void click_btnMenuPrincipalTransferir(String tituloMenu) {
        try {
            String textVwe = vwe_btnMenuPrincipalTarjetas.getText();
            Thread.sleep(2500);
            vwe_btnMenuPrincipalTarjetas.click();
            Util.assert_contiene(vGral_menu+tituloMenu,  vGral_detalleMenu, textVwe, tituloMenu, false, "N");
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            e.printStackTrace();
        }
    }

}
