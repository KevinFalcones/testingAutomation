package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatriculacionTarjetasComerciales {

    public MatriculacionTarjetasComerciales () {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_TipoServicio = null;

    @FindBy(id="comboTipoServicio")
    WebElement TipoServicio = null;

    @FindBy(xpath="//option[. = 'Tarjetas comerciales / almacenes']")
    WebElement OpcionTarjetasComerciales = null;

    @FindBy(id="comboEmpresaServicio")
    WebElement EmpresaServicio = null;

    WebElement OpcionEmpresa = null;

    @FindBy(id="tipoIdentificadorSeleccionado.identificador")
    WebElement Identificacion = null;

    @FindBy(id="empresaSeleccionada.alias")
    WebElement Alias = null;

    @FindBy(name="_eventId_cancelar")
    WebElement BotonCancelar = null;

    @FindBy(id="btnContinuar")
    WebElement BotonMatricular = null;

    //Variables de Mensajes de validación
    @FindBy(xpath="//li[@class = 'severity-ERROR']")
    WebElement mensaje_formatoIncorrecto = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_sinAlias = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_sinSuministro = null;

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

    public void click_EmpresaServicio()
    {
        EmpresaServicio.click();
    }

    public void Seleccionar_Empresa(String dato)
    {
        OpcionEmpresa = Util.driver.findElement(By.xpath("//option[. = '"+ dato + "']"));
        String actual = OpcionEmpresa.getText();
        Util.assert_igual("PANTALLA DE INGRESO DE DATOS", "Verificar que se seleccione el tipo Pycca", actual,dato, true, "N");
        OpcionEmpresa.click();
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

    //Acción para verificar mensajes de validación
    public void VP_MensajeFormatoSuministro()
    {
        mensaje_formatoIncorrecto.click();
        String actual = mensaje_formatoIncorrecto.getText();
        Util.assert_contiene("PANTALLA DE INGRESO DE DATOS", "Campo \"Identificación\" inválido. Ej: 0000000000", actual,"Ej: 0000000000", true, "N");
    }

    public void VP_MensajeSinAlias()
    {
        mensaje_sinAlias.click();
        String actual = mensaje_sinAlias.getText();
        Util.assert_contiene("PANTALLA DE INGRESO DE DATOS", "Campo \"Alias\" vacío o inválido. Favor ingrese algún valor ", actual,"Alias", true, "N");
    }

    public void VP_MensajeSinSuministro()
    {
        mensaje_sinSuministro.click();
        String actual = mensaje_sinSuministro.getText();
        Util.assert_contiene("PANTALLA DE INGRESO DE DATOS", "Verificar que se muestre el mensaje de error «Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.»", actual,"Identificaci", true, "N");
    }

}
