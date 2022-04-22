package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CambioAvatar {

    public CambioAvatar() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="menuMiPerfil")
    WebElement menuMiPerfil = null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-avatar")
    WebElement avatar = null;

    @FindBy(id="avatar46")
    WebElement avatar46 = null;

    @FindBy(className="title-funcionalidad")
    WebElement titulo = null;

    @FindBy(xpath="//div[@class='font-14 font-rblack color-4']")
    WebElement comprobante = null;


    public void clickMenuUsuario (){
        menuMiPerfil.click();
        btn_configuracion.click();
        avatar.click();

        Reporte.agregarPaso("CAMBIO DE AVATAR", "Ingresa a la funcionalidad 'Cambio de Avatar'", "Cambio de avatar", "", true, "N");



    }

    public void clickAvatar(){

        vp_etiqueta_titulo();
        avatar46.click();
    }

    public void vp_etiqueta_titulo()
    {
        String actual = titulo.getText();
        Util.assert_contiene("CAMBIO DE AVATAR", "Nombre de funcionalidad", actual,"Coloca un avatar para tu perfil", true, "N");
    }

    public void vp_comprobanteAvatar(){
        String actual = comprobante.getText();
        Util.assert_contiene("CAMBIO DE AVATAR", "Se verifica el mensaje exitoso una vez realizada la trx.", actual,"¡Listo!", true, "N");

    }

}
