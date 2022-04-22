package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/*Kevin Falcones - Gestion de Automatizacion y Pruebas
  Automatizacion de Solicitudes de Chequera - KFA_004_CYBERBANK-3697
  Condiciones: Happy Path
 */
//INI-->KFA_004_CYBERBANK-3697

public class SolicitarChequera {
    public SolicitarChequera() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="SOLICITUDES")
    WebElement vwe_menuSolicitudes = null;

    @FindBy(id="EB_SOLICITUD_CHEQUERA")
    WebElement vwe_btnMenuSolicitarChequera = null;

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'Solicitud de chequera')]")
    WebElement vwe_lblSolicitarChequera = null;

    @FindBy(id="_select_cuentaAsociada")
    WebElement vwe_cbxSelectCuentaChequera = null;

    @FindBy(name="tipoChequera")
    WebElement vwe_cbxSelectTipoChequera = null;

    @FindBy(id="select2-sucursal-container")
    WebElement vwe_cbxSelectOficinaEntrega = null;

    @FindBy(id="sucursal")
    WebElement vwe_lbxSelectOficinaEntrega = null;

    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement vwe_lbxSearchOficinaEntrega = null;

    @FindBy(name="retiraTercero")
    WebElement vwe_cbxSelectRetiroTercero = null;

    @FindBy(id="acceptCosto")
    WebElement vwe_ckbAceptaCargo = null;

    @FindBy(id="cuentasCliente")
    WebElement  vwe_cbxCuentaDebitar = null;

    @FindBy(id="btnContinuar")
    WebElement vwe_btnContinuar = null;

    @FindBy(name="personaAutorizada")
    WebElement  vwe_txtPersonaAutorizada = null;

    @FindBy(id="tipodocumento")
    WebElement  vwe_cbxTipodocumento = null;

    @FindBy(name="cedulaPersonaAutorizada")
    WebElement  vwe_txtCedulaPersonaAutorizada = null;

    @FindBy(xpath="//div[@class='titulo-mensaje']")
    WebElement vwe_txtMensajeExito2 = null;

    @FindBy(xpath="//div[@class='panelComunicacion iconoOk ']")
    WebElement vwe_txtMensajeExito = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    WebElement vwe_txtMensajeError = null;


    public void click_menuSolicitarChequera(){
        vwe_menuSolicitudes.click();
        vwe_btnMenuSolicitarChequera.click();
    }

    public void get_validaPantallaSolicitarChequera()
    {
        String actual = vwe_lblSolicitarChequera.getText();
        System.out.println(actual);
        Util.assert_igual("Validacion de primera pantalla.", "Se valida el titulo de la pagina", actual,"Solicitud de chequera", true, "N");
    }

    public void click_selectCuentaChequera(String cuentaChequera){
        vwe_cbxSelectCuentaChequera.click();
        vwe_cbxSelectCuentaChequera.sendKeys(cuentaChequera);
        String actual = vwe_cbxSelectCuentaChequera.getText();
        Util.assert_contiene("Se Selecciona la cuenta de la que se solicita la chequera", "Click en combo box", actual, cuentaChequera, false, "N");
    }

    public void click_selectTipoChequera(String tipoChequera){
        vwe_cbxSelectTipoChequera.click();
        vwe_cbxSelectTipoChequera.sendKeys(tipoChequera);

        try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }

        String actual = vwe_cbxSelectTipoChequera.getText();
        Util.assert_contiene("Se Selecciona el tipo de chequera", "Click en combo box", actual,tipoChequera, false, "N");
    }

    public void set_selectOficinaEntregar(String oficinaEntregar)
    {
        vwe_cbxSelectOficinaEntrega.click();
        vwe_lbxSearchOficinaEntrega.sendKeys(oficinaEntregar);
        vwe_lbxSearchOficinaEntrega.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("Se Selecciona la Oficina a Entregar", "Texto en list box", oficinaEntregar, "", false, "N");
    }

    public void click_selectRetiroTercero(String retiroTercero){
        vwe_cbxSelectRetiroTercero.click();
        vwe_cbxSelectRetiroTercero.sendKeys(retiroTercero);
        vwe_cbxSelectRetiroTercero.click();
        String actual = vwe_cbxSelectRetiroTercero.getText();
        Util.assert_contiene("Se Selecciona si un Tercero Retira o no", "Click en combo box", actual,retiroTercero, true, "N");
    }

    public void set_selectCuentaDebitar(String cuentaDebitar){
        vwe_ckbAceptaCargo.click();
        vwe_cbxCuentaDebitar.click();
        vwe_cbxCuentaDebitar.sendKeys(cuentaDebitar);

        try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }
        String actual = vwe_cbxCuentaDebitar.getText();
        Util.assert_contiene("Se Selecciona la cuenta de la que se debitara el valor de la chequera", "Click en combo box", actual, "CORRIENTE", true, "N");
    }

    public void click_btnContinuar(){
        Util.AvanzarPagina();
        try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }
        vwe_btnContinuar.click();
    }

    public void set_datosRetiroTercero(String nombreCompleto, String tipoDocumento, String numeroDocumento){
        Util.AvanzarPagina();

        vwe_txtPersonaAutorizada.click();
        try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }

        vwe_txtPersonaAutorizada.sendKeys(nombreCompleto);

        try { Thread.sleep(2000); } catch (InterruptedException exception) { exception.printStackTrace(); }
        vwe_cbxTipodocumento.click();
        vwe_cbxTipodocumento.sendKeys(tipoDocumento);
        String actual = vwe_cbxTipodocumento.getText();

        vwe_txtCedulaPersonaAutorizada.click();
        vwe_txtCedulaPersonaAutorizada.sendKeys(numeroDocumento);

        Util.assert_contiene("Se Ingresan datos del Tercero que Retira", "Click en combo box", actual,"Seleccionar", true, "N");
    }

    public String get_mensajeResultadoTrx()
    {
        String tmp_msj;
        try {
            tmp_msj = vwe_txtMensajeExito.getText();
        }
        catch (Exception e)
        {
            tmp_msj = vwe_txtMensajeError.getText();
        }
        return tmp_msj;
    }


    public void post_mensajeResultadoTrx(String msjResultado, String msjDetResultado)
    {
        Util.assert_contiene("Comprobacion de Resultado de Transaccion", msjResultado, msjDetResultado, msjDetResultado, true, "N");
    }

//FIN-->KFA_003_CYBERBANK-4060

}
