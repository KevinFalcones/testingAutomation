package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DesbloqueoDispositivo {
    public DesbloqueoDispositivo() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//*[@id=\"menuMiPerfil\"]/div/span")
    WebElement btn_menu= null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-dispositivo")
    WebElement btn_dispositivos= null;

    @FindBy(xpath="//div[@class='contenedor-centrado-titulo']")
    List<WebElement> etiq_desbloqueo= null;

    @FindBy(xpath="//span[@class='lb-limite']")
    List<WebElement> etiq_bloqueado= null;

    @FindBy(xpath="//a[@class='btn-desbloquear']")
    WebElement btn_desbloquear = null;

    public void ingresa_opcion_perfil()
    {
        btn_menu.click();
        btn_configuracion.click();
        Util.AvanzarPagina();
        btn_dispositivos.click();
    }

    public void vp_etiqueta_desbloqueo()
    {
        String actual = etiq_desbloqueo.get(0).getText();
        Util.assert_contiene("DESBLOQUEO DE DISPOSITIVO", "Verificación de título", actual,"Realiza el desbloqueo de tu dispositivo de seguridad", false, "N");
    }

    public void click_btn_desbloquear()
    {
        Actions action = new Actions(Util.driver);
        action.moveToElement(etiq_bloqueado.get(1)).perform();

        String actual = btn_desbloquear.getText();
        Util.assert_contiene("DESBLOQUEO DE DISPOSITIVO", "Click en botón", actual,"Desbloquear", true, "N");
        btn_desbloquear.click();
        Util.AvanzarPagina();
    }
}
