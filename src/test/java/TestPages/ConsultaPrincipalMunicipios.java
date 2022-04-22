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

public class ConsultaPrincipalMunicipios {
    public ConsultaPrincipalMunicipios() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="titulo_IMPUESTOS_OBLIGACIONES")
    WebElement opcionImpuestoObligaciones = null;

    @FindBy(id="IMPUESTOS_OBLIGACIONES_0")
    WebElement registroMunicipio1 = null;

    @FindBy(id="IMPUESTOS_OBLIGACIONES_1")
    WebElement registroMunicipio2 = null;

    @FindBy(id="IMPUESTOS_OBLIGACIONES_2")
    WebElement registroMunicipio3 = null;

    @FindBy(id="mapaServiciosIMPUESTOS_OBLIGACIONES0.item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(xpath="//option[@value='282955910']")
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//*[@id=\"table_5_IMPUESTOS_OBLIGACIONES_row1\"]/td[10]/ul/li/i")
    List<WebElement> menu_flotante = null;

    @FindBy(linkText="Pagar")
    WebElement click_BtnPagar = null;

    @FindBy(name="_eventId_aceptar")
    WebElement click_BtnAceptar = null;

    //Variables de Mensajes de validaci�n
    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement deudaCancelada = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_clienteNoExiste = null;

    @FindBy(xpath = "//li[@class='severity-ERROR']")
    WebElement mensaje_sinCuenta = null;

    public void Click_opcionMunicipio()
    {
        Util.AvanzarPagina();
        opcionImpuestoObligaciones.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Hacer click en la opci�n Impuestos y Obligaciones", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroMunicipioConSaldo()
    {
        Util.AvanzarPagina();
        registroMunicipio1.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Seleccionar suministro con saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroMunicipioSinSaldo()
    {
        Util.AvanzarPagina();
        registroMunicipio2.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Seleccionar suministro sin saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroMunicipioNoExistente()
    {
        Util.AvanzarPagina();
        registroMunicipio2.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Seleccionar suministro no existente para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Seleccionar_cuentaBancaria(String numcuenta)
    {
        Util.AvanzarPagina();
        cuentaBancaria.click();
        Util.AvanzarPagina();
        Select lst_cuentas = new Select(cuentaBancaria);
        List<WebElement> cuentas = lst_cuentas.getOptions();
        for (WebElement cuenta : cuentas) {
            System.out.println(cuenta.getText());
            if (cuenta.getText().contains(numcuenta))
            {
                cuenta.click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Hacer click en el campo cuenta bancaria", numcuenta, "", true, "N");
    }

    public void Click_BotonAceptar()
    {
        Util.AvanzarPagina();
        click_BtnAceptar.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Se debe mostrar la pantalla Configuraci�n de Pago", Util.getDataCliente()[1], "", true, "N");
    }


    //Hacer clic en men� flotante (...) y presionar el bot�n Pagar
    public void Click_menuFlotante() {
        Util.AvanzarPagina();
        menu_flotante.get(0).click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Hacer click en el men� flotante (...)", "Men� ...", "", true, "N");
    }

    public void Click_BotonPagar() {
        click_BtnPagar.click();
        Reporte.agregarPaso("PAGO DE MUNICIPIO", "Hacer click en el bot�n �Pagar�", Util.getDataCliente()[1], "", true, "N");
    }

    //Acci�n para verificar mensajes de validaci�n
    public void VP_MensajeSuministroCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("PAGO DE MUNICIPIO", "Se debe mostrar el mensaje de error �NO EXISTE DEUDA�", actual,"NO EXISTE DEUDA", true, "C");
    }

    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_igual("PAGO DE MUNICIPIO", "Verificar que se muestre el mensaje �CLIENTE NO EXISTE EN LA BASE�", actual, "CLIENTE NO EXISTE EN LA BASE", true, "C");
    }

    public void VP_MensajeSinSeleccionarCuenta() {
        mensaje_sinCuenta.click();
        String actual = mensaje_sinCuenta.getText();
        Util.assert_contiene("PAGO DE MUNICIPIO", "Verificar que se muestre el mensaje de validaci�n correspondiente", actual,"Debe seleccionar una cuenta d�bito para el servicio", true, "C");
    }



}
