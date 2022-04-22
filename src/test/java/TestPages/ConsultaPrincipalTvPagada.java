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

public class ConsultaPrincipalTvPagada {
    public ConsultaPrincipalTvPagada() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="titulo_TELEVISION_PAGADA")
    WebElement opcionTV = null;

    @FindBy(id="TELEVISION_PAGADA_0")
    WebElement registroTV1 = null;

    @FindBy(id="TELEVISION_PAGADA_1")
    WebElement registroTV2 = null;

    @FindBy(id="TELEVISION_PAGADA_2")
    WebElement registroTV3 = null;

    @FindBy(id="mapaServiciosTELEVISION_PAGADA0.item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(xpath="//option[@value='282955910']")
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//*[@id=\"table_5_TELEVISION_PAGADA_row1\"]/td[10]/ul/li/i")
    List<WebElement> menu_flotante = null;

    @FindBy(linkText="Pagar")
    WebElement click_BtnPagar = null;

    //Variables de Mensajes de validación
    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement deudaCancelada = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_clienteNoExiste = null;

    @FindBy(xpath = "//li[@class='severity-ERROR']")
    WebElement mensaje_sinCuenta = null;

    public void Click_opcionTV()
    {
        opcionTV.click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Hacer click en la opción TV Pagada", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTVConSaldo()
    {
        registroTV1.click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Seleccionar suministro con saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTVSinSaldo()
    {
        registroTV2.click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Seleccionar suministro sin saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroTVNoExistente()
    {
        registroTV3.click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Seleccionar suministro no existente para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
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
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Hacer click en el campo cuenta bancaria", numcuenta, "", true, "N");
    }


    //Hacer clic en menú flotante (...) y presionar el botón Pagar
    public void Click_menuFlotante() {
        menu_flotante.get(0).click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Hacer click en el menú flotante (...)", "Menú ...", "", true, "N");
    }

    public void Click_BotonPagar() {
        click_BtnPagar.click();
        Reporte.agregarPaso("PAGO DE TV PAGADA", "Hacer click en el botón Pagar", Util.getDataCliente()[1], "", true, "N");
    }

    //Acción para verificar mensajes de validación
    public void VP_MensajeSuministroCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("PAGO DE TV PAGADA", "Se debe mostrar el mensaje de error Deuda cancelada", actual,"Deuda cancelada", true, "C");
    }

    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_igual("PAGO DE TV PAGADA", "Verificar que se muestre el mensaje «CLIENTE NO EXISTE EN LA BASE»", actual, "CLIENTE NO EXISTE EN LA BASE", true, "C");
    }

    public void VP_MensajeSinSeleccionarCuenta() {
        mensaje_sinCuenta.click();
        String actual = mensaje_sinCuenta.getText();
        Util.assert_contiene("PAGO DE TV PAGADA", "Verificar que se muestre el mensaje de validación correspondiente", actual,"Debe seleccionar una cuenta débito para el servicio", true, "C");
    }
}
