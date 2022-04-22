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

public class MenuTarjetasMatricular {

    public MenuTarjetasMatricular() {
        PageFactory.initElements(Util.driver, this);
    }

    //Variables Generales
    String vGral_menu = "MENU TARJETAS MATRICULAR";
    String vGral_err = "ERR: Hay un error en ";
    String vGral_msjReporte = "Verificacion de etiqueta de menu";
    int vGral_sleep = 3000;

    //Variables Especificas
    @FindBy(id="EB_CONSULTA_DE_AGENDAS_DE_TARJETAS_DE_CREDITO")
    WebElement vwe_btnFncTarjetasMatricularLocales = null;

    @FindBy(id="EB_CONSULTA_DE_AGENDA_DE_TARJETAS_INTERNACIONALES")
    WebElement vwe_btnFncTarjetasMatricularInternacionales = null;

    @FindBy(id="tituloSuperior")
    WebElement vwe_lblTituloSuperior = null;


    public void click_btnFncTarjetasMatricularLocales(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasMatricularLocales.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasMatricularLocales.click();
        } catch (InterruptedException | NoSuchElementException | AssertionError e) {
            validate_Error(e.getMessage(), tituloSeccion);
        }
    }

    public void click_btnFncTarjetasMatricularInternacionales(String tituloSeccion) {
        try {
            Util.assert_contiene(vGral_menu, vGral_msjReporte, vwe_btnFncTarjetasMatricularInternacionales.getText(), tituloSeccion, true, "N");
            Thread.sleep(vGral_sleep);
            vwe_btnFncTarjetasMatricularInternacionales.click();
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
