package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AccesosRapidos {
    public AccesosRapidos ()
    {PageFactory.initElements(Util.driver, this);}

    @FindBy(xpath="//div[@class='text-no-f' and contains(., 'servicios')]")
    WebElement sinServicios = null;

    @FindBy(xpath="//span[@class='pc-link-todos tooltip-help-link-eb']")
    WebElement matriculaciones = null;

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'pagos')]")
    WebElement pagoServicios = null;

    @FindBy(xpath="//div[@class='item-pc-op act-op' and contains(., 'Servicios')]")
    WebElement conServicios = null;

    @FindBy(xpath="//div[@class='item-pc-op act-op' and contains(., 'Tarjetas')]")
    WebElement conTarjetasAct = null;

    @FindBy(xpath="//div[@class='item-pc-op' and contains(., 'Tarjetas')]")
    WebElement conTarjetasIna = null;


    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'matriculaciones')]")
    WebElement matriculaTC = null;

    public void vp_sinPagosFrec (){
        String actual = sinServicios.getText();
        Util.assert_contiene("Cliente sin pagos frecuentes", "Se verifica el titulo", actual,"", true, "N");
        matriculaciones.click();
    }

    public void vp_conPagosFrec(){
        String actual = conServicios.getText();
        Util.assert_contiene("Cliente con pagos frecuentes", "Se verifica el titulo", actual,"", true, "N");
        matriculaciones.click();
    }

    public void vp_matriculaSevicios(){
        String actual2 = pagoServicios.getText();
        Util.assert_contiene("Direccionamiento matriculación de servicios", "Se verifica el titulo", actual2,"", true, "N");
    }

    public void vp_conPagosTCFrec(){
        conTarjetasIna.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String actual = conTarjetasAct.getText();
        Util.assert_contiene("Cliente con pagos tarjetas frecuentes", "Cliente registra pago de tc frecuente", actual,"", true, "N");
        matriculaciones.click();
    }

    public void vp_matriculaTarjetas(){
        String actual = matriculaTC.getText();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.assert_contiene("Direccionamiento matriculación de tarjetas", "Se verifica el titulo", actual,"", true, "N");
    }


}


