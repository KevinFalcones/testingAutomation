package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ConsultaPagoSENAE {
    public ConsultaPagoSENAE() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_TipoServicio = null;

    @FindBy(id="tipoIdentificadorSeleccionado.identificador")
    WebElement Identificacion = null;

    @FindBy(id="tipoIdentificadorSeleccionado.datosAdicionales0.valor")
    WebElement TipoConcepto = null;

    @FindBy(id="tipoIdentificadorSeleccionado.datosAdicionales1.valor")
    WebElement Sucursal = null;

    @FindBy(id="btnContinuar")
    WebElement BtnConsultar = null;

   /* @FindBy(id="btnContinuar")
    WebElement BtnConsultar = null;*/

    //Variables de validación
    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_codigoError = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_sinIngresarCodigo = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement deudaCancelada = null;


    String[] datos = null;

    public void CargaDatosSuministro() {
        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroSENAE.txt", "1");
    }


    public void VP_TipoServicio()
    {
        Util.AvanzarPagina();
        //Util.AvanzarPagina();
        etiq_TipoServicio.click();
        String actual = etiq_TipoServicio.getText();
        Util.assert_igual("INGRESO DE DATOS", "Verificar la etiqueta del Tipo de Servicio", actual,"Tipo de servicio", true, "N");
        Util.AvanzarPagina();
        Util.AvanzarPagina();
    }

    public void IngresarIdentificacion(String dato) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.AvanzarPagina();
        Identificacion.click();
        Identificacion.sendKeys(dato);
        Util.CapturarImagen();
        Util.AvanzarPagina();
    }

    public void SeleccionarTipoConcepto(String dato) {
        TipoConcepto.click();
        TipoConcepto.sendKeys(dato);
        TipoConcepto.click();
        Util.CapturarImagen();
        Util.AvanzarPagina();
    }

    public void IngresarSucursal(String dato) {
        Sucursal.click();
        Sucursal.clear();
        Sucursal.sendKeys(dato);
        Util.CapturarImagen();
    }

    public void click_BtnConsultar()
    {
        BtnConsultar.click();
        String actual = BtnConsultar.getText();
        Util.assert_igual("INGRESO DE DATOS", "Validar el botón «Consultar» y dar click", actual,"Consultar", true, "N");
    }

    public void click_BtnPagar()
    {
        BtnConsultar.click();
        String actual = BtnConsultar.getText();
        Util.assert_igual("INGRESO DE DATOS", "Validar el botón «Consultar» y dar click", actual,"Pagar", true, "N");
    }


    //Validación de mensajes
    public void VP_mensajeErrorFormatoCodigo()
    {
        mensaje_codigoError.click();
        String actual = mensaje_codigoError.getText();
        Util.assert_igual("VALIDACION DE MENSAJE DE ERROR", "Se debe mostrar el mensaje de validación «Campo \"Identificación\" inválido. Ej: 00000000»", actual,"Campo \"Identificación\" inválido. Ej: 00000000", true, "C");
    }

    public void VP_mensajeSinIngresarCodigo()
    {
        mensaje_sinIngresarCodigo.click();
        String actual = mensaje_sinIngresarCodigo.getText();
        Util.assert_igual("VALIDACION DE MENSAJE DE ERROR", "Se debe mostrar el mensaje de validación «Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.»", actual,"Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.", true, "C");
    }

    //Acción para verificar mensajes de validación
    public void VP_MensajeIdentificacionCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("VALIDACION DE MENSAJE DE ERROR", "Se debe mostrar el mensaje de validación «No existe deuda»", actual,"No existe deuda", true, "C");
    }

    public void VP_MensajeSuministroCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("VALIDACION DE MENSAJE DE ERROR", "Se debe mostrar el mensaje de validación «No existe deuda»", actual,"No existe deuda", true, "C");
    }

}
