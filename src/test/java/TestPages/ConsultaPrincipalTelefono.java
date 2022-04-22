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

public class ConsultaPrincipalTelefono {
    public ConsultaPrincipalTelefono() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="titulo_TELEFONIA_FIJA")
    WebElement opcionTelefono = null;

    @FindBy(id="TELEFONIA_FIJA_0")
    WebElement registroTelefono1 = null;

    @FindBy(id="TELEFONIA_FIJA_1")
    WebElement registroTelefono2 = null;

    @FindBy(id="TELEFONIA_FIJA_2")
    WebElement registroTelefono3 = null;

    @FindBy(id="mapaServiciosTELEFONIA_FIJA0.item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(xpath="//option[@value='282955910']")
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//*[@id=\"table_5_TELEFONIA_FIJA_row2\"]/td[10]/ul/li/i")
    List<WebElement> menu_flotante = null;

    @FindBy(linkText="Pagar")
    WebElement click_BtnPagar = null;

    //Variables de Mensajes de validaci�n
    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement deudaCancelada = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_clienteNoExiste = null;

    @FindBy(xpath = "//li[@class='severity-ERROR']")
    WebElement mensaje_sinCuenta = null;

    public void Click_opcionTelefono()
    {
        opcionTelefono.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Hacer click en la opci�n Telefon�a Fija", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTelefonoConSaldo()
    {
        registroTelefono1.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Seleccionar suministro con saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTelefonoSinSaldo()
    {
        registroTelefono2.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Seleccionar suministro sin saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTelefonoNoExistente()
    {
        registroTelefono3.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Seleccionar suministro no existente para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Seleccionar_cuentaBancaria(String numcuenta)
    {
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
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Hacer click en el campo cuenta bancaria", numcuenta, "", true, "N");
    }


    //Hacer clic en men� flotante (...) y presionar el bot�n Pagar
    public void Click_menuFlotante() {
        menu_flotante.get(1).click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Hacer click en el men� flotante (...)", "Men� ...", "", true, "N");
    }

    public void Click_BotonPagar() {
        click_BtnPagar.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA FIJA", "Hacer click en el bot�n Pagar", Util.getDataCliente()[1], "", true, "N");
    }

    //Acci�n para verificar mensajes de validaci�n
    public void VP_MensajeSuministroCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("PAGO DE TELEFONIA FIJA", "Se debe mostrar el mensaje de error Deuda cancelada", actual,"Deuda cancelada", true, "C");
    }

    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_igual("PAGO DE TELEFONIA FIJA", "Verificar que se muestre el mensaje �CLIENTE NO EXISTE EN LA BASE�", actual, "CLIENTE NO EXISTE EN LA BASE", true, "C");
    }

    public void VP_MensajeSinSeleccionarCuenta() {
        mensaje_sinCuenta.click();
        String actual = mensaje_sinCuenta.getText();
        Util.assert_contiene("PAGO DE TELEFONIA FIJA", "Verificar que se muestre el mensaje de validaci�n correspondiente", actual,"Debe seleccionar una cuenta d�bito para el servicio", true, "C");
    }

}
