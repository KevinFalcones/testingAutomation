package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Comprobante {
    public Comprobante() {PageFactory.initElements(Util.driver, this);}

    @FindBy(xpath="//div[@class='encabezado']")
    //WebElement mensaje_matriculacionExitosa = null;
    List<WebElement> mensaje_matriculacionExitosa = null;

    @FindBy(name = "_eventId_posicionConsolidada")
    WebElement btn_PosicionConsolidada = null;

    @FindBy(name = "_eventId_aceptar")
    WebElement btn_ServMatriculado = null;

    @FindBy(name = "_eventId_nuevaOperacion")
    WebElement btn_Pagar = null;

    //Transferencia entre cuentas propias
    @FindBy(xpath="//a[@id='btnDescargar']")
    WebElement btnDescargar = null;

    @FindBy(xpath="//div[@class='titulo-mensaje']")
    List<WebElement> msjOKtransnferenciaCtaPropia = null;

    public void VP_MensajeMatriculacionExitosa()
    {
        Util.RetrocederPagina();
        //System.out.println(mensaje_matriculacionExitosa.size());
        String actual = mensaje_matriculacionExitosa.get(1).getText().replaceAll("\r", "").replaceAll("\n", "").trim();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar el mensaje de matriculación exitosa", actual,"La matriculación del servicio se ha realizado con éxito.", true, "N");
        Util.AvanzarPagina();
    }

    public void VP_MensajePagoExitosa()
    {
        Util.RetrocederPagina();
        //System.out.println(mensaje_matriculacionExitosa.size());
        String actual = mensaje_matriculacionExitosa.get(1).getText().replaceAll("\r", "").replaceAll("\n", "").trim();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar el mensaje de pago exitoso", actual,"El pago del servicio se ha realizado con éxito", true, "N");
        Util.AvanzarPagina();
        Util.AvanzarPagina();
    }

    public void VP_BotonPosicionConsolidada()
    {
        Util.AvanzarPagina();
        String actual = btn_PosicionConsolidada.getText();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar la etiqueta del botón «Posición consolidada».", actual,"Posición consolidada", true, "N");
    }

    public void Clic_BotonPosicionConsolidada()
    {
        Util.AvanzarPagina();
        btn_PosicionConsolidada.click();
    }

    public void VP_BotonServiciosMatriculados()
    {
        String actual = btn_ServMatriculado.getText();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar la etiqueta del botón «Servicios matriculados»", actual,"Servicios matriculados", false, "N");
    }

    public void VP_BotonPagar()
    {
        Util.AvanzarPagina();
        Util.AvanzarPagina();
        String actual = btn_Pagar.getText();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar la etiqueta del botón «Pagar»", actual,"Pagar", false, "N");
        btn_Pagar.click();
    }

    //PARA VALIDAR BOTONES DEL COMPROBANTE DEL PAGO EN FLUJO NORMAL EXITOSO

    public void Boton_PosicionConsolidada()
    {
        Util.AvanzarPagina();
        String actual = btn_ServMatriculado.getText();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar la etiqueta del botón «Posición consolidada».", actual,"Posición consolidada", true, "N");
    }

    public void Boton_ServiciosMatriculados()
    {
        String actual = btn_Pagar.getText();
        Util.assert_igual("PANTALLA DE COMPROBANTE", "Verificar la etiqueta del botón «Servicios matriculados»", actual,"Servicios matriculados", false, "N");
    }


    public void VP_transferenciaPropia()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        //String actual1 = btnDescargar.getText();
        String actual2 = msjOKtransnferenciaCtaPropia.get(1).getText();
        //Util.assert_contiene("Transferencias entre cuentas propias", "Se verifica el boton para descargar el comprobante.", actual1,"Descargar comprobante", true, "N");
        Util.assert_contiene("Transferencias entre cuentas propias", "Se verifica el mensaje exitoso una vez realizada la trx.", actual2,"¡Listo!", true, "N");
    }

}
