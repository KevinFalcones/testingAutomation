package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import javax.swing.*;
import java.util.List;
import java.util.Random;


public class CambioClaveTD {
    public CambioClaveTD () {PageFactory.initElements(Util.driver, this);}

    @FindBy(id ="TARJETAS")
    WebElement menuTarjetas = null;
    @FindBy(id="EB_CAMBIO_DE_CLAVE_DE_TARJETA_DE_DEBITO")
    WebElement cambioClave = null;
    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'de débito')]")
    WebElement tituloPantalla = null;
    @FindBy(id="claveActual")
    WebElement claveActual = null;
    @FindBy(id="claveNueva")
    WebElement claveNueva = null;
    @FindBy(id="claveNuevaParaVerificacion")
    WebElement claveNuevaRepetir = null;
    @FindBy(name = "_eventId_aceptar")
    WebElement btnAceptar = null;

    public void menuTarjetas(){
        menuTarjetas.click();
        cambioClave.click();
        vp_tituloPantalla();
        Util.AvanzarPagina();
    }

    public void vp_tituloPantalla(){
        String actual = tituloPantalla.getText();
        Util.assert_contiene("CAMBIO CLAVE TD", "Se verifica el titulo", actual,"Cambio de clave de tarjeta de débito", true, "N");
    }

    public void setClaveActual() {
        claveActual.click();
        claveActual.sendKeys("1111");
    }

    public void setClaveNueva(){
        claveNueva.click();
        claveNueva.sendKeys("7896");
    }

    public void setClaveNuevaRepetir(){
        claveNuevaRepetir.click();
        claveNuevaRepetir.sendKeys("7896");
    }

    public void setBtnAceptar()
    {
        String actual = btnAceptar.getText();
        Util.assert_contiene("ACEPTAR INGRESO DE CLAVE NUEVA", "Click en botón", actual, "Aceptar", true, "N");
        btnAceptar.click();
    }

}

/*
    public void setCambioClave(){
        claveActual.click();
        claveActual.sendKeys("1111");
        claveNueva.click();
        claveNueva.sendKeys("7896");
        claveNuevaRepetir.click();
        claveNuevaRepetir.sendKeys("7896");
    }
*/

