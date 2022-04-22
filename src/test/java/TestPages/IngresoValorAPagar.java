package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class IngresoValorAPagar {
    public IngresoValorAPagar() {PageFactory.initElements(Util.driver, this);}

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_montoAPagar = null;

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_campoCuenta = null;

    @FindBy(id = "inputMontoAPagar")
    WebElement campo_montoAPagar = null;

    @FindBy(id = "montoPago_TELEFONIA_CELULAR_0")
    WebElement campo_IngresarMontoAPagarCelular = null;

    @FindBy(id = "montoPago_TELEVISION_PAGADA_0")
    WebElement campo_IngresarMontoAPagarTV = null;

    @FindBy(id = "montoCuentaDebio")
    WebElement campo_IngresarMontoSENAE = null;

    @FindBy(id = "cuentasCliente")
    WebElement seleccionar_montoAPagar = null;

    @FindBy(id = "cuentaSeleccionada")
    WebElement click_cuenta = null;

    @FindBy(xpath="//option[@value='-126417734']")
    WebElement seleccionar_cuenta = null;

    @FindBy(xpath="//option[@value='-126417734']")
    WebElement seleccionar_cuentaSinFondo = null;

    @FindBy(xpath="//option[@value='282955910']")
    WebElement seleccionar_cuentaBloqueada = null;

    @FindBy(id = "descripcion")
    WebElement campo_descripcion = null;

    @FindBy(name = "_eventId_cancelar")
    WebElement btn_cancelar = null;

    @FindBy(name = "_eventId_pagar")
    WebElement btn_pagar = null;

    //PARAMETRO PARA VALIDAR
    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement msj_montoSuperior = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement msj_IngresarCuenta = null;

    @FindBy(xpath = "//li[@class='severity-ERROR']")
    WebElement mensaje_DeudaMenor = null;


    String[] datos = null;

    public void CargaDatosSuministro() {
        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroLuz.txt", "1");
    }

    public void VP_EtiquetaMontoAPagar()
    {
        String actual = etiq_montoAPagar.getText();
        Util.assert_igual("PANTALLA DE CONFECCION", "Verificar que la etiqueta se llame «Monto a pagar»", actual,"Monto a pagar", false, "N");
    }

    public void VP_CampoCuenta()
    {
        String actual = etiq_campoCuenta.getText();
        Util.assert_igual("PANTALLA DE CONFECCION", "Verificar que la etiqueta del campo cuenta es «Cuenta débito»", actual,"Cuenta débito", true, "N");
    }

    public void ingreso_valor()
    {
        campo_montoAPagar.click();
        campo_montoAPagar.sendKeys(datos[4]);
    }

    public void ingreso_valor(String dato)
    {
        campo_montoAPagar.click();
        campo_montoAPagar.sendKeys(dato);
    }

    public void ingreso_valorAPagar()
    {
        campo_IngresarMontoAPagarCelular.click();
        campo_IngresarMontoAPagarCelular.sendKeys(datos[4]);
    }

    public void ingreso_valorAPagar(String dato)
    {
       // Util.AvanzarPagina();
        campo_IngresarMontoAPagarCelular.click();
        campo_IngresarMontoAPagarCelular.clear();
        campo_IngresarMontoAPagarCelular.sendKeys(dato);
    }

    public void ingreso_valorAPagarTV()
    {
        campo_IngresarMontoAPagarTV.click();
        campo_IngresarMontoAPagarTV.sendKeys(datos[4]);
    }

    public void ingreso_valorAPagarTV(String dato)
    {
        campo_IngresarMontoAPagarTV.click();
        campo_IngresarMontoAPagarTV.sendKeys(dato);
    }

    public void seleccionar_valorARecargar()
    {
        seleccionar_montoAPagar.click();
        seleccionar_montoAPagar.sendKeys(datos[5]);
    }

    public void seleccionar_valorARecargar(String dato)
    {
        seleccionar_montoAPagar.click();
        seleccionar_montoAPagar.sendKeys(dato);
    }

    public void ingreso_valorSENAE(String dato)
    {
        campo_IngresarMontoSENAE.click();
        campo_IngresarMontoSENAE.clear();
        campo_IngresarMontoSENAE.sendKeys(dato);
    }

    public void Click_CampoCuenta()
    {
        click_cuenta.click();
    }

    public void Click_CampoCuentaAduana()
    {
        Util.AvanzarPagina();
        click_cuenta.click();
    }

    public void Seleccionar_cuentaBancaria()
    {
        seleccionar_cuenta.click();
        Reporte.agregarPaso("PANTALLA DE CONFECCION", "Seleccionar la cuenta bancaria", Util.getDataCliente()[1], "", true, "N");
        Util.AvanzarPagina();
    }

    public void Seleccionar_cuentaBancariaSinFondo()
    {
        seleccionar_cuentaSinFondo.click();
        Reporte.agregarPaso("PANTALLA DE CONFECCION", "Seleccionar la cuenta bancaria sin fondo", Util.getDataCliente()[1], "", true, "N");
        Util.AvanzarPagina();
    }

    public void Seleccionar_cuentaBancariaBloqueada()
    {
        seleccionar_cuentaBloqueada.click();
        Reporte.agregarPaso("PANTALLA DE CONFECCION", "Seleccionar la cuenta bancaria bloqueada", Util.getDataCliente()[1], "", true, "N");
        Util.AvanzarPagina();
    }

    public void VP_BotonCancelar()
    {
        Util.AvanzarPagina();
        String actual = btn_cancelar.getText();
        Util.assert_igual("PANTALLA DE CONFECCION", "Verificar que la etiqueta del botón se llame «Cancelar»", actual,"Cancelar", true, "N");
    }

    public void VP_BotonPagar()
    {
        String actual = btn_pagar.getText();
        Util.assert_igual("PANTALLA DE CONFECCION", "Verificar que la etiqueta del botón se llame «Pagar»", actual,"Pagar", true, "N");
        btn_pagar.click();
    }

    //VALIDACION DE MENSAJES
    public void VP_MensajeMontoSuperior()
    {
        String actual = msj_montoSuperior.getText();
        Util.assert_contiene("PANTALLA DE CONFECCION", "Se debe mostrar el mensaje «Campo Monto a pagar no puede ser mayor que»", actual,"Campo \"Monto a pagar\" no puede ser mayor que", true, "C");
    }

    public void VP_MensajeSeleccionarCuenta()
    {
        String actual = msj_IngresarCuenta.getText();
        Util.assert_contiene("PANTALLA DE CONFECCION", "Se debe mostrar el mensaje «Favor, seleccione Cuenta débito»", actual,"Favor, seleccione \"Cuenta", true, "C");
    }

    public void VP_MensajeDeudaMenor() {
        mensaje_DeudaMenor.click();
        String actual = mensaje_DeudaMenor.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «El pago es menor al total de la deuda»", actual, "El pago es menor al total de la deuda", true, "C");
    }

    public void VP_MensajeDeudaMayor() {
        msj_montoSuperior.click();
        String actual = msj_montoSuperior.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «El pago es mayor al total de la deuda»", actual, "El pago es mayor al total de la deuda", true, "C");
    }

}
