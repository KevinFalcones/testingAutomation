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

public class MenuTarjetasOtros {

    public MenuTarjetasOtros() {
        PageFactory.initElements(Util.driver, this);
    }

    //Variables Generales
    String vGral_menu = "MENU TARJETAS OTROS";
    String vGral_err = "ERR: Hay un error en ";
    String vGral_msjReporte = "Verificacion de etiqueta de menu";
    int vGral_sleep = 3000;

    //Variables Especificas
    @FindBy(id="EB_CONSULTA_BLOQUEO_TEMPORAL_TARJETA_DEBITO")
    WebElement vwe_btnFncTarjetasOtrosBloquear = null;

    @FindBy(id="EB_CONSULTA_DE_PUNTOS")
    WebElement vwe_btnFncTarjetasOtrosPuntos = null;

    @FindBy(id="EB_CAMBIO_DE_CLAVE_DE_TARJETA_DE_DEBITO")
    WebElement vwe_btnFncTarjetasOtrosClaveTD = null;

    @FindBy(id="EB_GESTION_LIMITE_TARJETA_DEBITO")
    WebElement vwe_btnFncTarjetasOtrosCupos = null;

    @FindBy(id="EB_TARJETAS_CORPORATIVAS")
    WebElement vwe_btnFncTarjetasOtrosCorporativas = null;

    @FindBy(id="EB_GESTION_INTERNACIONAL_TARJETAS_HABILITADAS")
    WebElement vwe_btnFncTarjetasOtrosUso = null;

    @FindBy(id="tituloSuperior")
    WebElement vwe_lblTituloSuperior = null;


    public void click_btnFncTarjetasOtrosBloquear(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosBloquear.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosBloquear.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOtrosPuntos(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosPuntos.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosPuntos.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOtrosClaveTD(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosClaveTD.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosClaveTD.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOtrosCupos(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosCupos.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosCupos.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOtrosCorporativas(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosCorporativas.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosCorporativas.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasOtrosUso(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasOtrosUso.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasOtrosUso.click();
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
