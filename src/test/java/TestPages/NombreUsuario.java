package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NombreUsuario {
    public NombreUsuario() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//*[@id=\"menuMiPerfil\"]/div/span")
    WebElement btn_menu= null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-user")
    WebElement btn_nombreusuario= null;

    @FindBy(xpath="//div[@class='title-autoadhesion'")
    WebElement titulo= null;

    @FindBy(id="nuevoNombreUsuario")
    WebElement txt_nuevousuario= null;

    @FindBy(id="btnContinuar")
    WebElement btn_continuar= null;

    public void ingresa_opcion_perfil()
    {
        btn_menu.click();
        btn_configuracion.click();
        btn_nombreusuario.click();
    }

    public void vp_etiqueta_titulo()
    {
        String actual = titulo.getText();
        Util.assert_contiene("CAMBIO DE USUARIO", "Verificación de título", actual,"Ingresa tu nuevo nombre de usuario", false, "N");
    }

    public void ingresa_usuario(String nuevousuario)
    {
        txt_nuevousuario.sendKeys(nuevousuario);
        Reporte.agregarPaso("CAMBIO DE USUARIO", "Ingresa nuevo usuario", nuevousuario, "", true, "N");
    }

    public void click_btn_continuar()
    {
        String actual = btn_continuar.getText();
        Util.assert_contiene("CAMBIO DE USUARIO", "Click en botón", actual,"Continuar", false, "N");
        btn_continuar.click();
    }
}
