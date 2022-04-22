package TestPages.BancaVirtual;

import Globales.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*Kevin Falcones - Senior Testing Automation
  Automatizacion de Validaciones Antes de Pase a Produccion - KFA_009_CYBERBANK-4818
  Condiciones: Validar Ingreso a cada una de las opciones del Canal
 */
//INI-->KFA_009_CYBERBANK-4818

public class MenuTarjetasOperaciones {

    public MenuTarjetasOperaciones() {
        PageFactory.initElements(Util.driver, this);
    }

    //Variables Generales
    String vGral_menu = "MENU TARJETAS OPERACIONES";
    String vGral_err = "ERR: Hay un error en ";
    String vGral_msjReporte = "Verificacion de etiqueta de menu";
    int vGral_sleep = 3000;

    //Variables Especificas
    @FindBy(id="EB_AVANCE_DE_EFECTIVO_A_CUENTA_ASOCIADA")
    WebElement vwe_btnFncTarjetasOperacionesAvance = null;

    @FindBy(id="EB_DIFERIR_CONSUMOS")
    WebElement vwe_btnFncTarjetasOperacionesDiferir = null;

    @FindBy(id="EB_PRE_CANCELACION_DE_DIFERIDOS")
    WebElement vwe_btnFncTarjetasOperacionesPreNew = null;

    @FindBy(id="EB_TARJETA_PLAN_PROGRAMADO")
    WebElement vwe_btnFncTarjetasOperacionesPlan = null;

    @FindBy(id="EB_SOLICITUD_TARJETA_ADICIONAL")
    WebElement vwe_btnFncTarjetasOperacionesAdicional = null;

    @FindBy(id="tituloSuperior")
    WebElement vwe_lblTituloSuperior = null;


    public void click_btnFncTarjetasOperacionesAvance(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOperacionesAvance.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOperacionesAvance.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOperacionesDiferir(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOperacionesDiferir.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOperacionesDiferir.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOperacionesPreNew(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOperacionesPreNew.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOperacionesPreNew.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOperacionesPlan(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOperacionesPlan.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOperacionesPlan.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOperacionesAdicional(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOperacionesAdicional.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOperacionesAdicional.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void validate_EtiquetaMenu(String tituloSeccion) {
        try {
            WebElement vwe_lblTituloSeccion = Util.driver.findElement(By.xpath("//div[@class='titulo-seccion' and contains(., '"+ tituloSeccion + "')]"));
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_lblTituloSeccion.getText(), tituloSeccion, true, "N");
        } catch (NoSuchElementException e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    private void validate_Error(String error, String tituloSeccion) {
        System.out.println(vGral_err + tituloSeccion);
        System.out.println(error);
        Util.assert_contiene("Menu " + vGral_menu, "ERROR NO SE COMPROBO LA ETIQUETA DEL MENU", tituloSeccion, tituloSeccion, false, "C");
    }

}
