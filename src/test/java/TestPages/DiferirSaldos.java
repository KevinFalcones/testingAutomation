package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Globales.Util.driver;

public class DiferirSaldos {

    public DiferirSaldos() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menuTarjetas = null;

    @FindBy(id="EB_DIFERIR_CONSUMOS")
    WebElement diferirSaldos = null;

    @FindBy(xpath="//div[@class='number-card']")
    List<WebElement> tarjetas = null;

    //@FindBy(xpath="//input[@id='number-card']")
    //List<WebElement> tarjetas = null;



    public void click_smenu_DiferirSaldos(){
        menuTarjetas.click();
        String actual = diferirSaldos.getText();
        Reporte.agregarPaso("Diferir Saldos", "Ingreso a la funcionalidad", actual, "", true, "N");
        diferirSaldos.click();
    }

    public void selecciona_tarjeta(String numtc){
        WebElement tc = Util.driver.findElement(By.xpath("//div[@class='number-card' and contains(., '" + numtc +"')]"));
        tc.click();
        Reporte.agregarPaso("Diferir Saldos", "Selecciona tarjeta", numtc, "", true, "N");
        WebElement alert = Util.driver.findElement(By.xpath("//a[@class='alert-img' and contains(.,'Entendido')]"));
        alert.click();

        Util.AvanzarPagina();
    }

    public void montoYplazo (String s1, String s2){
        WebElement btnContinuar = Util.driver.findElement(By.xpath("//a[@id='btnContinuarOperacion']"));
        btnContinuar.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement plazo = Util.driver.findElement(By.xpath("//div[@class='numero-plazo' and contains (.,'" + s2 +"')]"));
        plazo.click();

        Reporte.agregarPaso("Diferir Saldos", "Selecciona el plazo.", s2, "", true, "N");

    }

    public void confirmacion(){
        WebElement btnContinuar = Util.driver.findElement(By.xpath("//a[@id='btnContinuarOperacion2']"));
        btnContinuar.click();

        //Util.AvanzarPagina();

    }


}
