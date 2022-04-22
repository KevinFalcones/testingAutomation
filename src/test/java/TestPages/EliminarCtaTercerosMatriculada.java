package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Globales.Util.driver;

public class EliminarCtaTercerosMatriculada {

    public EliminarCtaTercerosMatriculada() {
        PageFactory.initElements(driver, this);
    }

    //Menu "Transferir"
    @FindBy(id = "TRANSFERENCIAS")
    WebElement menutransferir = null;

    //Sub-menu de "Matricular"
    @FindBy(id = "EB_AGENDA_CUENTAS_TERCEROS")
    WebElement smenuAgendaCtaTerceros = null;

    //Datos para eliminación de cuentas matriculadas
    @FindBy(id = "table_1_row1")
    WebElement matriculaEliminada = null;
    @FindBy(name = "_eventId_eliminar")
    WebElement btnEliminar = null;
    @FindBy(name = "_eventId_confirmar")
    WebElement btnConfirmar = null;
    @FindBy(name = "_eventId_nuevaCuenta")
    WebElement btnNuevaCta = null;

    @FindBy(id = "divTransaccionesInexistentes")
    WebElement panelComunicacion = null;

    public void click_smenu_eliminacionCta() {
        menutransferir.click();
        vp_submenu();
        smenuAgendaCtaTerceros.click();
        try {
            if (panelComunicacion.isDisplayed()) {
                vp_panelComunicacion();
            }
        } catch (NoSuchElementException n) {
            vp_btnNuevaCta();
            matriculaEliminada.click();
            vp_btnEliminarCta();
            btnEliminar.click();
            vp_btnConfirmar();
            btnConfirmar.click();
        }
    }

    //Puntos de verificacion
    public void vp_panelComunicacion() {
        String actual = panelComunicacion.getText();
        Util.assert_contiene("Eliminacion de cuentas terceros", "Mensaje informativo tener cuentas matriculadas.", actual, "No tienes cuentas matriculadas", true, "N");
    }

    public void vp_submenu(){
        String actual2 = smenuAgendaCtaTerceros.getText();
        Util.assert_contiene("Agenda de Cuentas - Otros bancos", "Se ingresa la funcionalidad para matricular la nueva cuenta.", actual2,"Cuentas de terceros", true, "N");
    }

    public void vp_btnNuevaCta(){
        String actual2 = btnNuevaCta.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se selecciona el primer registro de la tabla de TC matriculadas.", actual2,"Nueva Cuenta", true, "N");
    }

    public void vp_btnEliminarCta(){
        String actual2 = btnEliminar.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se cliquea en 'Eliminar'.", actual2,"Eliminar", true, "N");
    }

    public void vp_btnConfirmar(){
        String actual2 = btnConfirmar.getText();
        Util.assert_contiene("Eliminacion de TC de terceros", "Se confirma la eliminacion.", actual2,"Confirmar", true, "N");
    }

}

