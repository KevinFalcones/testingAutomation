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

public class ConsultaPrincipalCelular {
    public ConsultaPrincipalCelular() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="titulo_TELEFONIA_CELULAR")
    WebElement opcionCelular = null;

    @FindBy(id="TELEFONIA_CELULAR_0")
    WebElement registroCelular1 = null;

    @FindBy(id="TELEFONIA_CELULAR_1")
    WebElement registroCelular2 = null;

    @FindBy(id="TELEFONIA_CELULAR_1")
    WebElement registroCelular3 = null;

    @FindBy(id="mapaServiciosTELEFONIA_CELULAR0.item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(name = "mapaServicios[TELEFONIA_CELULAR][0].item.detalleDeuda.reciboSeleccionado")
    WebElement seleccionar_montoAPagar = null;

    @FindBy(xpath="//option[@value='282955910']")
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//*[@id=\"table_5_TELEFONIA_CELULAR_row1\"]/td[10]/ul/li/i")
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

    String[] datos = null;

    public void Click_opcionCelular()
    {
        opcionCelular.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Hacer click en la opción Telefonía Celular", Util.getDataCliente()[1], "", true, "N");
        Util.AvanzarPagina();
    }

    public void Click_registroCelularConSaldo()
    {
        //Util.AvanzarPagina();
        registroCelular1.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Seleccionar suministro con saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
        Util.AvanzarPagina();
    }

    public void Click_registroCelularSinSaldo()
    {
        registroCelular2.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Seleccionar suministro sin saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroCelularNoExistente()
    {
        registroCelular3.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Seleccionar suministro no existente para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void seleccionar_valorARecargar()
    {
        seleccionar_montoAPagar.click();
        seleccionar_montoAPagar.sendKeys(datos[5]);
        Util.AvanzarPagina();
    }

    public void seleccionar_valorARecargar(String dato)
    {
        seleccionar_montoAPagar.click();
        seleccionar_montoAPagar.sendKeys(dato);
        //Util.AvanzarPagina();
    }

    public void Seleccionar_cuentaBancaria(String numcuenta)
    {
        //Util.AvanzarPagina();
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
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Hacer click en el campo cuenta bancaria", numcuenta, "", true, "N");
    }


    //Hacer clic en menú flotante (...) y presionar el botón Pagar
    public void Click_menuFlotante() {
        Util.AvanzarPagina();
        menu_flotante.get(0).click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Hacer click en el menú flotante (...)", "Menú ...", "", true, "N");
    }

    public void Click_menuFlotante1() {
        menu_flotante.get(0).click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Hacer click en el menú flotante (...)", "Menú ...", "", true, "N");
    }

    public void Click_BotonPagar() {
        //Util.AvanzarPagina();
        click_BtnPagar.click();
        Reporte.agregarPaso("PAGO DE TELEFONIA CELULAR", "Hacer click en el botón Pagar", Util.getDataCliente()[1], "", true, "N");
    }

    //Acción para verificar mensajes de validación
    public void VP_MensajeSuministroCancelada()
    {
        deudaCancelada.click();
        String actual = deudaCancelada.getText();
        Util.assert_igual("PAGO DE TELEFONIA CELULAR", "Se debe mostrar el mensaje de error Deuda cancelada", actual,"Deuda cancelada", true, "C");
    }

    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_contiene("PAGO DE TELEFONIA CELULAR", "Verificar que se muestre el mensaje «NO EXISTE CONTRATO ACTIVO PARA ESTA IDENTIFICACION»", actual, "NO EXISTE CONTRATO ACTIVO PARA", true, "C");
    }

    public void VP_MensajeSinSeleccionarCuenta() {
        mensaje_sinCuenta.click();
        String actual = mensaje_sinCuenta.getText();
        Util.assert_contiene("PAGO DE TELEFONIA CELULAR", "Verificar que se muestre el mensaje de validación correspondiente", actual,"Debe seleccionar una cuenta débito para el servicio", true, "C");
    }

}
