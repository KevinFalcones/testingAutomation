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

public class ConsultaTarjetaComerciales {
    private Object actual;

    public ConsultaTarjetaComerciales() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_TipoServicio = null;

    @FindBy(id="comboTipoServicio")
    WebElement TipoServicio = null;

    @FindBy(id="Tarjetas Comerciales / Almacenes")
    WebElement opcionTarjComer = null;

    @FindBy(xpath="//option[. = 'Tarjetas comerciales / almacenes']")
    WebElement OpcionTarjetasComerciales = null;

    @FindBy(id="tipoIdentificadorSeleccionado.identificador")
    WebElement Identificacion = null;

    @FindBy(id="empresaSeleccionada.alias")
    WebElement Alias = null;

    @FindBy(name="_eventId_cancelar")
    WebElement BotonCancelar = null;

    @FindBy(id="btnContinuar")
    WebElement BotonMatricular = null;

    @FindBy(id="CASAS_TARJETAS_COMERCIALES_0")
    WebElement registroTarjComer1 = null;

    @FindBy(id="CASAS_TARJETAS_COMERCIALES_1")
    WebElement registroTarjComer2 = null;

    @FindBy(id="CASAS_TARJETAS_COMERCIALES_2")
    WebElement registroTarjComer3 = null;

    @FindBy(id="comboEmpresaServicio")
    WebElement EmpresaServicio = null;

    WebElement OpcionEmpresa = null;

    @FindBy(id="mapaServicios[CASAS_TARJETAS_COMERCIALES][0].item.productoDebito")
    WebElement cuentaBancaria = null;

    @FindBy(xpath="//option[@value='490566115']")
    WebElement click_ctaBancaria = null;

    @FindBy(xpath="//*[@id=\"table_5_AGUA_row2\"]/td[10]/ul/li/i")
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

    @FindBy(xpath = "class=\"severity-FATAL\"")
            WebElement mensaje_sinDeuda = null;

    public void VP_TipoServicio()
    {
        String actual = etiq_TipoServicio.getText();

        Util.assert_igual("Etiqueta Tipo de Servicio", "Verificar la etiqueta del Tipo de Servicio",
                actual,"Tipo de servicio", false);

    }
    public void click_TipoServicio()
    {
        TipoServicio.click();
    }

    public void Seleccionar_TarjetasComerciales()
    {
        OpcionTarjetasComerciales.click();
        String actual = OpcionTarjetasComerciales.getText();
        Util.assert_igual("PANTALLA DE INGRESO DE DATOS", "Verificar que se seleccione el tipo Tarjetas comerciales / almacenes", actual,"Tarjetas comerciales / almacenes", true, "N");
    }

    public void IngresarIdentificacion(String dato) {
        Identificacion.click();
        Identificacion.sendKeys(dato);
        Util.CapturarImagen();
        Util.AvanzarPagina();
    }

    public void IngresarAlias(String dato) {
        Alias.click();
        Alias.sendKeys(dato);
        Util.CapturarImagen();
    }

    public void VP_BotonCancelar()
    {
        String actual = BotonCancelar.getText();
        Util.assert_igual("PANTALLA DE INGRESO DE DATOS", "Verificar la etiqueta del botón Cancelar", actual,"Cancelar", true, "N");
    }

    public void VP_BotonMatricular()
    {
        String actual = BotonMatricular.getText();
        Util.assert_igual("PANTALLA DE INGRESO DE DATOS", "Verificar la etiqueta del botón Matricular", actual,"Matricular", true, "N");
        Util.AvanzarPagina();
        BotonMatricular.click();
    }
    public void Seleccionar_Empresa(String dato)
    {
        OpcionEmpresa = Util.driver.findElement(By.xpath("//option[. = '"+ dato + "']"));
        String actual = OpcionEmpresa.getText();
        Util.assert_igual("PANTALLA DE INGRESO DE DATOS", "Verificar que se seleccione el tipo Pycca", actual,dato, true, "N");
        OpcionEmpresa.click();
    }

    public void click_EmpresaServicio()
    {
        EmpresaServicio.click();
    }

    public void VP_MensajeSinSuministro()
    {
        mensaje_sinDeuda.click();
        String actual = mensaje_sinDeuda.getText();
        Util.assert_contiene("Cliente no tiene deuda", "Empresa destino", actual,"Identificaci", true, "N");
    }
    public void VP_MensajeClienteNoExiste() {
        mensaje_clienteNoExiste.click();
        String actual = mensaje_clienteNoExiste.getText();
        Util.assert_igual("Consultar suministro que no existe", "Verificar que se muestre el mensaje «CODIGO DE CLIENTE NO EXISTE»", actual, "CODIGO DE CLIENTE NO EXISTE", true, "C");
    }

}
