package TestPages.BancaVirtual;

import Globales.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Kevin Falcones - Senior Testing Automation
  Automatizacion de Validaciones Antes de Pase a Produccion - KFA_008_CYBERBANK-4817
  Condiciones: Validar Ingreso a cada una de las opciones del Canal
 */
//INI-->KFA_008_CYBERBANK-4817

public class MenuPrincipalPagar {

    public MenuPrincipalPagar() {
        PageFactory.initElements(Util.driver, this);
    }

    //Variables Generales
    String vGral_menu = "Menu Principal Tarjetas";

    @FindBy(id="PAGOS")
    WebElement vwe_btnMenuPrincipalPagar = null;

    @FindBy(id="EB_PAGO_DE_SERVICIOS")
    WebElement vwe_btnFncPagarServicios = null;

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'Terceros')]")
    WebElement vwe_lblTituloSeccion = null;


    public void click_btnMenuPrincipalPagar(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, "Verificacion de etiqueta de menu", vwe_btnMenuPrincipalPagar.getText(), tituloSeccion, true, "N");
            Thread.sleep(5000);
            vwe_btnMenuPrincipalPagar.click();
        } catch (InterruptedException | NoSuchElementException e) {
            validate_Error(e.getMessage(), "click_btnMenuPrincipalPagar", vGral_menu, tituloSeccion);
        }
    }

    public void click_btnFncPagarServicios(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, "Verificacion de etiqueta de menu", vwe_btnFncPagarServicios.getText(), tituloSeccion, true, "N");
            Thread.sleep(5000);
            vwe_btnFncPagarServicios.click();
        } catch (InterruptedException | NoSuchElementException | ElementNotInteractableException e) {
            validate_Error(e.getMessage(), "click_btnFncPagarServicios", vGral_menu, tituloSeccion);
        }
    }

    public void validate_EtiquetaMenu(String tituloSeccion) {
        try {
            WebElement vwe_lblTituloSeccion = Util.driver.findElement(By.xpath("//div[@class='titulo-seccion' and contains(., '"+ tituloSeccion + "')]"));
            Util.assert_contiene(vGral_menu, "Verificacion de Titulo de menu", vwe_lblTituloSeccion.getText(), tituloSeccion, true, "N");
        } catch (NoSuchElementException e) {
            validate_Error(e.getMessage(), "validate_EtiquetaMenu", vGral_menu, tituloSeccion);
        }
    }

    public void validate_Error(String error, String method, String menu, String tituloSeccion) {
        System.out.println("ERR: Hay un error en el metodo " + method);
        System.out.println(error);
        Util.assert_contiene("Menu " + menu, "ERROR NO SE COMPROBO LA ETIQUETA DEL MENU", tituloSeccion, tituloSeccion, false, "C");
    }

}
