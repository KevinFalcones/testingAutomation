package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ComprobanteCta
{
    public ComprobanteCta(){PageFactory.initElements(Util.driver, this);}

    //matriculación exitosa
    @FindBy(xpath="//div[@class='font-14 font-rblack color-4']")
    List<WebElement> msj_ok = null;

    @FindBy(xpath="//div[@class='titulo-mensaje']")
    List<WebElement> msj_ok_old = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    List<WebElement> mensaje_error = null;

    @FindBy (id = "modalExitoNuevaCuentaBtnSec1")
    WebElement btn_matricular_nueva = null;

    //eliminación de matriculaciones
    @FindBy (xpath = "//a[@class='botones-resultado-item' and contains(., 'Nueva')]")
    WebElement linkNuevaMatriculacion = null;

    @FindBy(name="_eventId_matricular")
    WebElement btnNuevaMatriculacion = null;

    @FindBy(id="action-close-exito-nueva-ag")
    WebElement btn_x = null;

    @FindBy(id="cuentas")
    WebElement btn_ver_miscuentas = null;

    public void vp_mensaje_exitoso()
    {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for(int i = 0; i < msj_ok.size(); i++) {
            System.out.println(i+": "+msj_ok.get(i).getText());
        }

        String msjok = null;
        String msjerror = null;
        String actual = null;
        msjok = msj_ok.get(2).getText();
        msjerror = msj_ok.get(3).getText();

        if (!msjerror.equals(""))
            actual = msjerror;
        else
            actual = msjok;

        Util.assert_contiene("COMPROBANTE MATRICULACION CUENTA", "Verificación de mensaje exitoso", actual,"cuenta matriculada", true, "N");
    }


    public void vp_mensajeTransaccion_exitoso(String esperado)
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = null;
        try {
            actual = msj_ok_old.get(1).getText();
            Util.assert_contiene("COMPROBANTE MATRICULACION CUENTA", "Verificación de mensaje exitoso", actual, esperado, true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("COMPROBANTE MATRICULACION CUENTA", "Verificación de mensaje", actual, "", true, "N");
        }
    }
    public void vp_msjCtaEliminada()
    {
        String actual = msj_ok.get(1).getText();
        String actual1 = linkNuevaMatriculacion.getText();
        System.out.println(actual);
        System.out.println(actual1);
        Util.assert_contiene("Comprobante cuenta eliminada", "Eliminacion exitosa",actual,"Listo",false, "N");
        Util.assert_contiene("Comprobante cuenta eliminada", "Eliminacion exitosa",actual1,"Nueva",false, "N");
    }

    public void click_boton_nuevamatriculacion()
    {
        String actual = btn_matricular_nueva.getText();
        Util.assert_contiene("COMPROBANTE MATRICULACION CUENTA", "Click en botón", actual,"Matricular nueva cuenta", false, "N");
        btn_matricular_nueva.click();
    }

    public void click_boton_X()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        Reporte.agregarPaso("COMPROBANTE MATRICULACION CUENTA", "Click en botón", "X", "", false, "N");
        btn_x.click();
    }

    public void click_boton_nuevamatriculacion_old()
    {
        String actual = btnNuevaMatriculacion.getText();
        Util.assert_contiene("COMPROBANTE MATRICULACION TARJETAS", "Click en botón", actual,"Nueva matriculaci", false, "N");
        btnNuevaMatriculacion.click();
    }

    public void click_boton_misctasmatriculadas()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = btn_ver_miscuentas.getText();
        Util.assert_contiene("COMPROBANTE MATRICULACION CUENTA", "Click en botón", actual,"Ver mis cuentas matriculadas", true, "N");
        btn_ver_miscuentas.click();
    }

}
