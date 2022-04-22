package TestPages;

import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/*Contiene los comprobantes de las trx posibles de realizar con una TC*/
public class ComprobanteTC {
    public ComprobanteTC() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//div[@class='panelComunicacion iconoOk ']")
    WebElement msjOK = null;

    @FindBy(xpath="//button[@name='_eventId_exportarTicket']")
    WebElement btnDescargar = null;

    @FindBy(xpath="//div[@class='font-14 font-rblack color-4 text-title-eb']")
    WebElement msjOKpagoTC = null;

    @FindBy(xpath="//div[@class='font-14 font-rblack color-4']")
    WebElement msjErrorpagoTC = null;

    @FindBy(xpath="//div[@class='font-rregular font-13 color-8 texto-descrip']")
    WebElement motivoErrorpagoTC = null;

    @FindBy(xpath="//a[@class='btn-v2 btn-v2-submit']")
    WebElement btnComprobanteMatriculacionTC = null;

    @FindBy(xpath="//div[@class='titulo-mensaje']")
    List<WebElement> msjOkNuevo = null;

    @FindBy(name="_eventId_matricular")
    WebElement btnNuevaMatriculacion = null;

    @FindBy(name="_eventId_eventoBtn1")
    WebElement btnNuevoPagoTC = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    List<WebElement> mensaje_error = null;

    @FindBy(xpath="//div[@class='font-lregular font-26 color-eb']")
    WebElement monto = null;

    @FindBy(xpath="//a[@class='botones-resultado-item']")
    WebElement btnNuevoPlan = null;

    @FindBy(id="tarjetasEd")
    WebElement btnVerMisTarjetas = null;

    public void vp_mensaje_exitoso(String esperado)
    {
        String actual = null;
        try {
            actual = msjOK.getText();
            Util.assert_contiene("COMPROBANTE TERCEROS", "Verificación de mensaje exitoso", actual, esperado, true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("COMPROBANTE TERCEROS", "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void vp_mensaje_error(String esperado)
    {
        String actual = null;
        actual = mensaje_error.get(0).getText().toUpperCase();
        Util.AvanzarPagina();
        Util.assert_contiene("COMPROBANTE TERCEROS", "Verificación de mensaje error", actual, esperado, true, "C");
    }

    public void VP_eliminacionTCmatriculada()
    {
        String actual1 = btnComprobanteMatriculacionTC.getText();
        String actual2 = msjOkNuevo.get(1).getText();

        System.out.println(actual1);
        System.out.println(actual2);
        Util.assert_contiene("Eliminacion Exitosa de TC matriculada", "Se verifica el boton para descargar el comprobante.", actual1,"Descargar comprobante", true, "N");
        Util.assert_contiene("Eliminacion Exitosa de TC matriculada", "Se verifica el mensaje exitoso una vez realizada la trx.", actual2,"¡Listo!", false, "N");

    }

    public void vp_mensajeTransaccion_exitoso(int idx, String caso, String esperado)
    {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = null;
        try {
            actual = msjOkNuevo.get(idx).getText();
            Util.assert_contiene(caso, "Verificación de mensaje exitoso", actual, esperado, true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual(caso, "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void vp_mensaje_pago_exitoso()
    {
        String actual = null;
        try {
            actual = msjOKpagoTC.getText();
            Util.assert_contiene("COMPROBANTE PAGO TC", "Verificación de mensaje exitoso", actual,"Tu pago fue realizado", true, "N");
        }
        catch (Exception e)
        {
            actual = msjErrorpagoTC.getText() + " - " + motivoErrorpagoTC.getText();
            Util.assert_igual("COMPROBANTE PAGO TC", "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void click_boton_nuevamatriculacion()
    {
        String actual = btnNuevaMatriculacion.getText();
        Util.assert_contiene("COMPROBANTE MATRICULACION TARJETAS", "Click en botón", actual,"Nueva matriculación", false, "N");
        Util.AvanzarPagina();
        btnNuevaMatriculacion.click();
    }

    public void vp_monto_pagado(String montopagado)
    {
        String actual = monto.getText();
        Util.assert_contiene("COMPROBANTE PAGO TC", "Verificación de monto pagado", actual, montopagado, false, "N");
    }

    public void click_boton_nuevopagotc()
    {
        String actual = btnNuevoPagoTC.getText();
        Util.assert_contiene("COMPROBANTE PAGO TC", "Click en botón", actual,"Nuevo pago de tarjeta", false, "N");
        btnNuevoPagoTC.click();
    }

    public void click_boton_nuevoplan()
    {
        String actual = btnNuevoPlan.getText();
        Util.assert_contiene("COMPROBANTE PLAN PROGRAMADO", "Click en botón", actual,"Nuevo PlanProgramado", false, "N");
        btnNuevoPlan.click();
    }

    public void click_boton_mistcsmatriculadas()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = btnVerMisTarjetas.getText();
        Util.assert_contiene("COMPROBANTE MATRICULACION CUENTA", "Click en botón", actual,"Ver mis tarjetas matriculadas", true, "N");
        btnVerMisTarjetas.click();
    }

}
