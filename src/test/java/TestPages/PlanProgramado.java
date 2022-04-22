package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlanProgramado {
    public PlanProgramado() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//div[@class='number-card']")
    List<WebElement> tarjetas = null;

    @FindBy(xpath="//span[@class='checkmark']")
    List<WebElement> chkTarjetas = null;

    @FindBy(id="btnContinuarOperacion")
    WebElement btnContinuarOperacion = null;

    @FindBy(xpath="//label[@id='name-1']")
    WebElement tarjetaSeleccionada = null;

    @FindBy(id="visualizarMontoProgramado")
    WebElement txtRangoMonto = null;

    @FindBy(id="concepto")
    WebElement concepto = null;

    @FindBy(id="btnContinuarOperacion2")
    WebElement btnContinuarOperacion2 = null;

    @FindBy(xpath="//div[@class='numeroPlazo']")
    List<WebElement> plazos = null;

    @FindBy(xpath="//span[@class='checkmarkPlazo']")
    List<WebElement> chkPlazos = null;

    @FindBy(id="chkvisible")
    WebElement chkTerminos = null;

    @FindBy(id="confirmar-plan-programado")
    WebElement btnSolicitar = null;

    public void selecciona_tarjeta(String numtc){
        String actual = "";
        for(int i=0; i<tarjetas.size();i++)
        {
            actual = tarjetas.get(i).getText();
            System.out.println("tc "+i+": "+actual);
            if (actual.equals(numtc)) {
                chkTarjetas.get(i).click();
                break;
            }
        }
        Reporte.agregarPaso("PLAN PROGRAMADO", "Selecciona tarjeta", numtc, "", true, "N");
    }

    public void click_boton_continuar() {
        String actual = btnContinuarOperacion.getText();
        Util.assert_contiene("PLAN PROGRAMADO", "Click en botón", actual, "Continuar", false, "N");
        btnContinuarOperacion.click();
    }

    public void vp_tarjeta_seleccionada(String numtc){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = tarjetaSeleccionada.getText();
        Util.assert_contiene("PLAN PROGRAMADO", "Tarjeta seleccionada", actual, numtc, false, "N");
    }

    public void ingresa_monto(String monto)
    {
        txtRangoMonto.clear();
        txtRangoMonto.sendKeys(monto);
        Reporte.agregarPaso("PLAN PROGRAMADO", "Ingresa monto", monto, "", true, "N");
        Util.AvanzarPagina();
    }

    public void ingresa_concepto(String descConcepto){
        concepto.sendKeys(descConcepto);
        Reporte.agregarPaso("PLAN PROGRAMADO", "Ingresa concepto", descConcepto, "", false, "N");
    }

    public void selecciona_plazo(String plazo)
    {
        String actual = "";
        for(int i=0; i<plazos.size();i++)
        {
            actual = plazos.get(i).getText();
            System.out.println("tc "+i+": "+actual);
            if (actual.equals(plazo)) {
                chkPlazos.get(i).click();
                break;
            }
        }
        Reporte.agregarPaso("PLAN PROGRAMADO", "Selecciona plazo", plazo, "", true, "N");
    }

    public void click_boton_continuar2() {
        String actual = btnContinuarOperacion2.getText();
        Util.assert_contiene("PLAN PROGRAMADO", "Click en botón", actual, "Continuar", false, "N");
        btnContinuarOperacion2.click();
        Util.AvanzarPagina();
    }

    public void click_terminos()
    {
        chkTerminos.click();
        Reporte.agregarPaso("PLAN PROGRAMADO", "Click en ", "Terminos y Condiciones", "", false, "N");
        Util.AvanzarPagina();
    }

    public void click_boton_solicitar() {
        String actual = btnSolicitar.getText();
        Util.assert_contiene("PLAN PROGRAMADO", "Click en botón", actual, "Solicitar", true, "N");
        btnSolicitar.click();
    }

}
