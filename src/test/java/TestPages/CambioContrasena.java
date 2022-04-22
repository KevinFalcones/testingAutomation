package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CambioContrasena {

    public CambioContrasena() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="menuMiPerfil")
    WebElement menuMiPerfil = null;

    @FindBy(xpath="//*[@id=\"menuMiPerfil\"]/div/span")
    WebElement btn_menu= null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-clave")
    WebElement clave = null;

    @FindBy(className="title-autoadhesion")
    WebElement titulo = null;

    @FindBy(id="claveActual")
    WebElement claveActual = null;

    @FindBy(id="claveNueva")
    WebElement claveNueva = null;

    @FindBy(id="claveNuevaParaVerificacion")
    WebElement claveNuevaParaVerificacion = null;

    @FindBy(id="btnContinuar")
    WebElement btnContinuar = null;

    @FindBy(xpath="//div[@class='font-14 font-rblack color-4']")
    WebElement comprobante = null;

    public void clickMenuUsuario (){

        menuMiPerfil.click();
        btn_configuracion.click();
        clave.click();

        Reporte.agregarPaso("CAMBIO DE CONTRASENA", "Ingresa a la funcionalidad 'Cambio de Contrasena'", "Cambio de Contrasena", "", true, "N");

    }

    public void ingresoNuevaContrasena(String s1, String s2){

        System.out.println(s1);
        System.out.println(s2);
        vp_etiqueta_titulo();
        claveActual.sendKeys(s1);
        Reporte.agregarPaso("CAMBIO DE CONTRASENA", "Ingreso de la actual contrasena", s1, "", true, "N");
        claveNueva.sendKeys(s2);
        Reporte.agregarPaso("CAMBIO DE CONTRASENA", "Ingreso de la nueva contrasena", s2, "", true, "N");
        Util.AvanzarPagina();
        claveNuevaParaVerificacion.sendKeys(s2);
        Reporte.agregarPaso("CAMBIO DE CONTRASENA", "Verificacion de la nueva contrasena", s2, "", true, "N");

        btnContinuar.click();

        vp_comprobanteContrasena();

    }

    public void vp_etiqueta_titulo()
    {
        String actual = titulo.getText();
        Util.assert_contiene("CAMBIO DE CONTRASENA", "Nombre de funcionalidad", actual,"Crea tu nueva", true, "N");
    }

    public void vp_comprobanteContrasena(){
        String actual = comprobante.getText();
        Util.assert_contiene("CAMBIO DE CONTRASENA", "Se verifica el mensaje exitoso una vez realizada la trx.", actual,"¡Listo!", true, "N");

    }
}
