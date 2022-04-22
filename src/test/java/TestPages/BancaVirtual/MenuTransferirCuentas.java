package TestPages.BancaVirtual;

import Globales.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Kevin Falcones - Senior Testing Automation
  Automatizacion de Validaciones Antes de Pase a Produccion - KFA_007_CYBERBANK-4678
  Condiciones: Validar Ingreso a cada una de las opciones del Canal
 */
//INI-->KFA_007_CYBERBANK-4678

public class MenuTransferirCuentas {

    public MenuTransferirCuentas() {
        PageFactory.initElements(Util.driver, this);
    }

    //Variables Generales
    String vGral_menu = "MENU TRANSFERIR CUENTAS";
    String vGral_err = "ERR: Hay un error en ";
    String vGral_msjReporte = "Verificacion de etiqueta de menu";
    int vGral_sleep = 5000;

    //Variables Especificas
    @FindBy(id="EB_TRANSFERENCIA_CUENTAS_PROPIAS")
    WebElement vwe_btnFncTransferirCuentasPropias = null;

    @FindBy(id="EB_TRANSFERENCIAS_A_TERCEROS")
    WebElement vwe_btnFncTransferirCuentasTerceros = null;

    @FindBy(id="EB_TRANSFERENCIAS_A_TERCEROS_EN_EL_EXTERIOR")
    WebElement vwe_btnFncTransferirCuentasInternacionales = null;

    @FindBy(id="tituloSuperior")
    WebElement vwe_lblTituloSuperior = null;


    public void click_btnFncTransferirCuentasPropias(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, "Verificacion de etiqueta de menu", vwe_btnFncTransferirCuentasPropias.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTransferirCuentasPropias.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTransferirCuentasTerceros(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, "Verificacion de etiqueta de menu", vwe_btnFncTransferirCuentasTerceros.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTransferirCuentasTerceros.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTransferirCuentasInternacionales(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, "Verificacion de etiqueta de menu", vwe_btnFncTransferirCuentasInternacionales.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTransferirCuentasInternacionales.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }


    public void validate_EtiquetaMenu(String tituloSeccion) {
        try {
            WebElement vwe_lblTituloSeccion = Util.driver.findElement(By.xpath("//div[@class='titulo-seccion' and contains(., '"+ tituloSeccion + "')]"));
            Util.assert_contiene("Menu Transferir Cuentas", "Verificacion de Titulo de menu", vwe_lblTituloSeccion.getText(), tituloSeccion, true, "N");
        } catch ( NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    private void validate_Error(String error, String tituloSeccion) {
        System.out.println(vGral_err + tituloSeccion);
        System.out.println(error);
        Util.assert_contiene("Menu " + vGral_menu, "ERROR NO SE COMPROBO LA ETIQUETA DEL MENU", tituloSeccion, tituloSeccion, false, "C");
    }

}
