package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Globales.Util.driver;

public class AvanceDeEfectivo {
    public AvanceDeEfectivo() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menutarjetas = null;

    @FindBy(id="EB_AVANCE_DE_EFECTIVO_A_CUENTA_ASOCIADA")
    WebElement EB_AVANCE_DE_EFECTIVO_A_CUENTA_ASOCIADA = null;

    @FindBy(xpath="//div[@onclick='seleccionTarjetaGrid(0)']")
    WebElement tc = null;

    @FindBy(id="btnContinuarOperacion")
    WebElement btnContinuarOperacion = null;

    @FindBy(id="montoAvance")
    WebElement montoAvance = null;

    @FindBy(id="gridItemCuenta-0")
    WebElement destino = null;

    @FindBy(id="btnContinuarOperacion2")
    WebElement btnContinuarOperacion2 = null;

    @FindBy(xpath="//span[@class='off-switch']")
    WebElement switchDif = null;

    @FindBy(xpath="//div[@class='numero-plazo' and contains(., '6')]")
    WebElement lblBcoBolivariano = null;

    @FindBy(xpath="//div[@class='titulo-mensaje' and contains(., 'acreditado')]")
    WebElement tranOK = null;


    public void seleccionTC(String s1, String s2, String s3){
        //Paso 1
        menutarjetas.click();
        EB_AVANCE_DE_EFECTIVO_A_CUENTA_ASOCIADA.click();
        tc.click();
        //btnContinuarOperacion.click();
        vp_paso1();
        Util.AvanzarPagina();
        //Paso 2
        montoAvance.click();
        montoAvance.sendKeys(s1);
        if(s2.equals("Y")){
            System.out.println("entro!");
            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switchDif.click();

            WebElement meses = Util.driver.findElement(By.xpath("//div[@class='numero-plazo' and contains(., '"+ s3 + "')]"));
            meses.click();
        }
        destino.click();
        //btnContinuarOperacion2.click();
        vp_paso2();
        Util.AvanzarPagina();
    }

    public void vp_paso1() {
        String actual = btnContinuarOperacion.getText();
        Util.assert_igual("Paso 1", "Selección de TC para realizar el Avance de Efectivo.", actual, "Continuar", true, "N");
        btnContinuarOperacion.click();
    }

    public void vp_paso2() {
        String actual = btnContinuarOperacion2.getText();
        Util.assert_igual("Paso 2", "Confección de monto, cuenta destino y diferido/corriente.", actual, "Continuar", true, "N");
        btnContinuarOperacion2.click();
    }

    public void vp_transaccionOK(){
        String actual = tranOK.getText();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.assert_contiene("Transacción exitosa", "Pantalla de transacción exitosa y descarga de comprobante", actual, "", true, "N");
    }



}
