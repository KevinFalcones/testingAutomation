package TestPages;

import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuTarjetas {

    public MenuTarjetas() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menuTarjetas = null;

    @FindBy(id="EB_PAGO_DE_TARJETA_DE_TERCEROS")
    WebElement pagarTCterceros = null;

    @FindBy(id="EB_PAGO_DE_TARJETA_PROPIA_CON_DEBITO_EN_CUENTA")
    WebElement pagarTCpropia = null;

    @FindBy(id="EB_AGENDA_DE_TARJETAS_DE_CREDITO")
    WebElement matricularTCLocales_old = null;

    @FindBy(id="EB_TARJETA_PLAN_PROGRAMADO")
    WebElement planProgramado = null;

    @FindBy(name="transferencias/agendaTarjetaCredito/matriculacionTarjetaCreditoV2.htm")
    WebElement matricularTCLocales = null;

    public void click_smenu_PagoTCTerceros(){
        menuTarjetas.click();
        String actual = pagarTCterceros.getText();
        Util.assert_contiene("MENU TARJETAS", "Verificacion de etiqueta de menu", actual,"Terceros", true, "N");
        pagarTCterceros.click();
    }

    public void click_smenu_PagoTCPropias(){
        menuTarjetas.click();
        String actual = pagarTCpropia.getText();
        Util.assert_contiene("MENU TARJETAS", "Verificacion de etiqueta de menu", actual,"Propias", true, "N");
        pagarTCpropia.click();
    }

    public void click_smenu_MatricularTcLocales_old(){
        menuTarjetas.click();
        String actual = matricularTCLocales_old.getText();
        Util.assert_contiene("MENU TARJETAS", "Verificacion de etiqueta de menu", actual,"Tarjetas locales", true, "N");
        matricularTCLocales_old.click();
    }

    public void click_smenu_MatricularTcLocales(){
        menuTarjetas.click();
        String actual = matricularTCLocales.getText();
        Util.assert_contiene("MENU TARJETAS", "Verificacion de etiqueta de menu", actual,"Tarjetas locales", true, "N");
        matricularTCLocales.click();
    }

    public void click_smenu_PlanProgramado()
    {
        menuTarjetas.click();
        String actual = planProgramado.getText();
        Util.assert_contiene("MENU TARJETAS", "Verificacion de etiqueta de menu", actual,"Plan programado", true, "N");
        planProgramado.click();

    }

}
