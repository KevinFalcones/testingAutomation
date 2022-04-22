package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PosicionConsolidada {
    public PosicionConsolidada() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//span[@class='font-rblack font-14 color-4']")
    WebElement etiq_saludo = null;

    public void vp_etiqueta_saludo() {
        String actual = etiq_saludo.getText();
        Util.assert_contiene("POSICIÓN CONSOLIDADA", "Verificación de etiqueta Saludo", actual,"Buen", true, "N");
    }


}
