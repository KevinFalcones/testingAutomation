package TestPages;

import Globales.Util;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EliminacionTCmatriculada {

    public EliminacionTCmatriculada() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menutarjetas = null;

    @FindBy(id="EB_AGENDA_DE_TARJETAS_DE_CREDITO")
    WebElement EB_AGENDA_DE_TARJETAS_DE_CREDITO = null;

    @FindBy(xpath="//div[@class='encabezado' and contains(., 'NO')]")
    WebElement panelComunicacion = null;

    @FindBy(id="table_1_row1")
    WebElement table_1_row1 = null;

    @FindBy(name="_eventId_confirmar")
    WebElement btnConfirmar = null;

    @FindBy(name="_eventId_eliminar")
    WebElement btnEliminar = null;

    @FindBy(name="_eventId_nuevaTarjetaInt")
    WebElement btnNuevaTC = null;



    public void click_smenu_eliminacionTC(){
        menutarjetas.click();
        vp_submenu();
        EB_AGENDA_DE_TARJETAS_DE_CREDITO.click();
        try {
            if (panelComunicacion.isDisplayed()){
                vp_panelComunicacion();
            }
        }catch (NoSuchElementException n){
            vp_btnNuevaTC();
            table_1_row1.click();
            vp_btnEliminarTC();
            btnEliminar.click();
            vp_btnConfirmarTC();
            btnConfirmar.click();
        }


    }


    //Puntos de verificacion
    public void vp_panelComunicacion(){
        String actual = panelComunicacion.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se verifica el mensaje de informacion de no tener TC matriculadas.", actual,"NO TIENES TARJETAS MATRICULADAS / REGISTRADAS PARA PODER TRANSACCIONAR", true, "N");

    }
    public void vp_submenu(){
        String actual2 = EB_AGENDA_DE_TARJETAS_DE_CREDITO.getText();
        Util.assert_contiene("Agenda de TC local - Otros bancos", "Se ingresa la funcionalidad para matricular la nueva TC local.", actual2,"Tarjetas locales", true, "N");

    }
    public void vp_btnNuevaTC(){
        String actual2 = btnNuevaTC.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se selecciona el primer registro de la tabla de TC matriculadas.", actual2,"Nueva Tarjeta", true, "N");

    }

    public void vp_btnEliminarTC(){
        String actual2 = btnEliminar.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se cliquea en 'Eliminar'.", actual2,"Eliminar", true, "N");

    }

    public void vp_btnConfirmarTC(){
        String actual2 = btnConfirmar.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se confirma la eliminacion.", actual2,"Confirmar", true, "N");

    }


}
