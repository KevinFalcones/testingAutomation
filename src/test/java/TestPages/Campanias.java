package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Campanias {
    public Campanias() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="img-close-pagoTarjetaPropia")
    WebElement btnX = null;

    public void click_boton_X() {
        try {
            btnX.click();
        }
        catch (Exception e){}
    }
}
