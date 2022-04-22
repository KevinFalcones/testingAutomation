package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout {

    public Logout(){
        PageFactory.initElements(Util.driver, this);

    }

    @FindBy(id="menuMiPerfil")
    WebElement menuMiPerfil = null;

    @FindBy(xpath="//p[@class='logout-ult-fecha']")
    WebElement cerrarSesion = null;

    @FindBy(xpath="//div[@class='texto1']")
    WebElement texto1 = null;

    @FindBy(xpath="//div[@class='texto2']")
    WebElement texto2 = null;

    @FindBy(xpath="//button[@type='submit']")
    WebElement iniciarSesion = null;

    public void CerrarSesion(){
        menuMiPerfil.click();
        /*try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        cerrarSesion.click();

    }

    public void VP_textosDeDespedida(){
        String actual1 = texto1.getText();
        String actual2 = texto2.getText();
        Util.assert_contiene("Cierre de sesion", "Se verifica primer texto de despedida.", actual1,"Hasta luego", true, "N");
        Util.assert_contiene("Cierre de sesion", "Se verifica segundo texto de despedida.", actual2,"Tu sesión fue cerrada exitosamente.", true, "N");

    }

    public void iniciarSesion(){
        iniciarSesion.click();
    }


}
