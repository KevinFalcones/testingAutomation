package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConsultaPrincipalLuz {
    public ConsultaPrincipalLuz() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="titulo_LUZ")
    WebElement opcionLuz = null;

    @FindBy(id="LUZ_0")
    WebElement registroLuz1 = null;

    @FindBy(id="LUZ_1")
    WebElement registroLuz2 = null;

    @FindBy(id="LUZ_2")
    WebElement registroLuz3 = null;

    @FindBy(id="mapaServiciosLUZ0.item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(xpath="//option[@value='282955910']") //cambiar el número de cuenta
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//i[@class='fa fa-ellipsis-h button edit link']")
    WebElement click_menu_flotante = null;

    @FindBy(linkText="Pagar")
    WebElement click_BtnPagar = null;

    //Variables de Mensajes de validación
    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement facturaCancelada = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_clienteNoExiste = null;

    @FindBy(xpath = "//li[@class='severity-ERROR']")
    WebElement mensaje_sinCuenta = null;


    public void Click_opcionLuz()
    {
        opcionLuz.click();
        Reporte.agregarPaso("Opción Luz", "Hacer click en la opción Luz", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroLuzConSaldo()
    {
        registroLuz1.click();
        Reporte.agregarPaso("Consulta de suministro con saldo", "Seleccionar suministro con saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroLuzSinSaldo()
    {
        registroLuz2.click();
        Reporte.agregarPaso("Consulta de suministro sin saldo", "Seleccionar suministro sin saldo para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_registroLuzNoExistente()
    {
        registroLuz3.click();
        Reporte.agregarPaso("Consulta de suministro no existente", "Seleccionar suministro no existente para realizar la consulta", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_campoCuenta()
    {
        cuentaBancaria.click();
        Reporte.agregarPaso("Campo cuenta bancaria", "Hacer click en el campo cuenta bancaria", Util.getDataCliente()[1], "", true, "N");
    }

    public void Seleccionar_cuentaBancaria()
    {
        click_ctaBancaria.click();
        Reporte.agregarPaso("Número cuenta bancaria", "Seleccionar la cuenta bancaria", Util.getDataCliente()[1], "", true, "N");
    }

    //Hacer clic en menú flotante (...) y presionar el botón Pagar
    public void Click_menuFlotante() {
        click_menu_flotante.click();
        Reporte.agregarPaso("Menú Flotante", "Hacer click en el menú flotante (...)", Util.getDataCliente()[1], "", true, "N");
    }

    public void Click_BotonPagar() {
        click_BtnPagar.click();
        Reporte.agregarPaso("Botón Pagar", "Hacer click en el botón Pagar", Util.getDataCliente()[1], "", true, "N");
    }

    //Acción para verificar mensajes de validación
    public void VP_MensajeSuministroCancelada()
    {
        facturaCancelada.click();
        String actual = facturaCancelada.getText();
        Util.assert_igual("Validar mensaje de error", "Se debe mostrar el mensaje de error FACTURA HA SIDO CANCELADA", actual,"FACTURA HA SIDO CANCELADA", true, "C");
    }

    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_igual("Consultar suministro que no existe", "Verificar que se muestre el mensaje «CODIGO DE CLIENTE NO EXISTE»", actual, "CODIGO DE CLIENTE NO EXISTE", true, "C");
    }

    public void VP_MensajeSinSeleccionarCuenta() {
        mensaje_sinCuenta.click();
        String actual = mensaje_sinCuenta.getText();
        Util.assert_contiene("Tratar de hacer el pago sin seleccionar la cuenta", "Verificar que se muestre el mensaje de validación correspondiente", actual,"Debe seleccionar una cuenta débito para el servicio Corporación Nacional de Electricidad/Cnel", true, "C");
    }
}
