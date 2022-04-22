package TestPages;

import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MatriculacionLuz {

    public MatriculacionLuz() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//label[@class='obligatorio']")
    WebElement etiq_TipoServicio = null;

    @FindBy(id="comboTipoServicio")
    WebElement TipoServicio = null;

    @FindBy(xpath="//option[. = 'Luz']")
    WebElement OpcionLuz = null;

    @FindBy(id="comboEmpresaServicio")
    WebElement EmpresaServicio = null;

    @FindBy(xpath="//option[. = 'Corporación Nacional de Electricidad']")
    WebElement OpcionEmpresa = null;

    @FindBy(id="convenioSeleccionadoMnemonic")
    WebElement TipodePago = null;

    @FindBy(xpath="//option[. = 'Cnel Guayas - Los Ríos']")
    WebElement TipoEmpresa = null;

    /*@FindBy(xpath="//option[. = " + datos[1] + "]")
    WebElement TipoEmpresa = null;*/

    @FindBy(id="tipoIdentificadorSeleccionado.identificador")
    WebElement Identificacion = null;

    @FindBy(id="empresaSeleccionada.alias")
    WebElement Alias = null;

    @FindBy(name="_eventId_cancelar")
    WebElement BotonCancelar = null;

    @FindBy(id="btnContinuar")
    WebElement BotonMatricular = null;

    //Variables de Mensajes de validación
    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_formatoIncorrecto = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_sinAlias = null;

    @FindBy(xpath="//li[@class='severity-ERROR']")
    WebElement mensaje_sinSuministro = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement mensaje_noExiste = null;


    String[] datos = null;

    public void CargaDatosSuministro() {
        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_suministroLuz.txt", "1");
    }

    public void VP_TipoServicio()
    {
        String actual = etiq_TipoServicio.getText();
        Util.assert_igual("Etiqueta Tipo de Servicio", "Verificar la etiqueta del Tipo de Servicio", actual,"Tipo de servicio", false, "N");
    }

    public void click_TipoServicio()
    {
        TipoServicio.click();
    }

    public void Seleccionar_Luz()
    {
        OpcionLuz.click();
        String actual = OpcionLuz.getText();
        Util.assert_igual("Opción Tipo de Servicio", "Verificar que se seleccione el tipo LUZ", actual,"Luz", true, "N");
    }

    public void click_EmpresaServicio()
    {
        EmpresaServicio.click();
    }

    public void Seleccionar_EmpresaServicio()
    {
        OpcionEmpresa.click();
        String actual = OpcionEmpresa.getText();
        Util.assert_igual("Opción Empresa/Servicio", "Verificar que se seleccione la Empresa", actual,"Corporación Nacional de Electricidad", false, "N");
    }

    public void click_TipoPago()
    {
        TipodePago.click();
    }

    public void Seleccionar_Empresa()
    {
        TipoEmpresa.click();
        String actual = TipoEmpresa.getText();
        Util.assert_igual("Opción Tipo de Servicio", "Verificar que se seleccione el tipo de Empresa", actual,"Cnel Guayas - Los Ríos", true, "N");
    }

    public void IngresarIdentificacion() {
        Identificacion.click();
        Identificacion.sendKeys(datos[2]);
        Util.CapturarImagen();
        Util.AvanzarPagina();
    }

    public void IngresarAlias() {
        Util.AvanzarPagina();
        Alias.click();
        Alias.sendKeys(datos[3]);
        Util.CapturarImagen();
    }

    public void VP_BotonCancelar()
    {
        String actual = BotonCancelar.getText();
        Util.assert_igual("Botón Cancelar", "Verificar la etiqueta del botón Cancelar", actual,"Cancelar", false, "N");
    }

    public void VP_BotonMatricular()
    {
        String actual = BotonMatricular.getText();
        Util.assert_igual("Botón Matricular", "Verificar la etiqueta del botón Matricular y dar click", actual,"Matricular", true, "N");
        BotonMatricular.click();
    }

    //Acción para verificar mensajes de validación
    public void VP_MensajeFormatoSuministro()
    {
        mensaje_formatoIncorrecto.click();
        String actual = mensaje_formatoIncorrecto.getText();
        Util.assert_contiene("Validar el formato del suministro", "Verificar que se muestre el mensaje de error «Campo \"Identificación\" inválido. Ej: 000000000000»", actual,"Campo \"Identificación\" inválido. Ej: 0000000", true, "N");
    }

    public void VP_MensajeSinAlias()
    {
        mensaje_sinAlias.click();
        String actual = mensaje_sinAlias.getText();
        Util.assert_igual("Tratar de matricular sin igresar el alias", "Verificar que se muestre el mensaje de error «Campo \"Alias\" vacío o inválido. Favor, ingrese algún valor.»", actual,"Campo \"Alias\" vacío o inválido. Favor, ingrese algún valor.", true, "N");
    }

    public void VP_MensajeSinSuministro()
    {
        mensaje_sinSuministro.click();
        String actual = mensaje_sinSuministro.getText();
        Util.assert_igual("Tratar de matricular sin igresar el suministro", "Verificar que se muestre el mensaje de error «Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.»", actual,"Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.", true, "N");
    }

    public void VP_MensajeCodigoNoExiste()
    {
        String actual = mensaje_noExiste.getText();
        Util.assert_igual("Tratar de matricular sin igresar el suministro", "Verificar que se muestre el mensaje de error «Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.»", actual,"Campo \"Identificación\" vacío o inválido. Favor, ingrese algún valor.", true, "N");
    }

}
