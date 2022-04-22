package TestPages;

import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

/*Kevin Falcones - Gestion de Automatizacion y Pruebas
  Automatizacion de Transferencias por Donaciones - KFA_003_CYBERBANK-4060
  Condiciones: Happy Path
 */
//INI-->KFA_003_CYBERBANK-4060

public class Donaciones {
    public Donaciones() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TRANSFERENCIAS")
    WebElement vwe_menuTransferir = null;

    @FindBy(id="EB_DONACIONES")
    WebElement vwe_btnMenuDonaciones = null;

    @FindBy(xpath="//div[@class='titulo-seccion'and contains(., 'Donaciones')]")
    WebElement vwe_lblDonaciones = null;

    @FindBy(id="familiaDeProducto")
    WebElement vwe_cbxSelectFormaPago = null;

    @FindBy(name="_eventId_continuar")
    WebElement vwe_btnFormaPagoContinuar = null;

    @FindBy(name="_eventId_confirmar")
    WebElement vwe_btnFormaPagoConfirmar = null;

    @FindBy(id="cuentaOrigenSeleccionada")
    WebElement vwe_cbxCuentaOrigen = null;

    @FindBy(xpath="//option[contains(., 'XXXX')]")
    WebElement vwe_cbxOpcionCuentaOrigen = null;
    //List<WebElement> vwe_cbxOpcionCuentaOrigen = null;

    @FindBy(xpath="//option[contains(., 'CRISTO')]")
    WebElement vwe_cbxOpcionFundacion = null;

    @FindBy(id="importeDonacion")
    WebElement vwe_tbxMonto = null;

    @FindBy(id="botonSwitch")
    WebElement vwe_btnProgramarDonacion = null;

    @FindBy(name="programador.periodo")
    WebElement vwe_cbxOpcionProgramar = null;

    @FindBy(xpath="//option[@value='periodicidadProgramadas,UNICO']")
    WebElement vwe_cbxOpcionProgramarUnico = null;


    public void get_validaPantallaDonacion()
    {
        String actual = vwe_lblDonaciones.getText();
        System.out.println(actual);
        Util.assert_igual("Validacion de primera pantalla.", "Se valida el titulo de la pagina", actual,"Donaciones", true, "N");
    }

    public void click_menuDonaciones(){
        vwe_menuTransferir.click();
        vwe_btnMenuDonaciones.click();
    }

    public void click_formaDePagoDonacion(){
        vwe_cbxSelectFormaPago.click();
        vwe_cbxSelectFormaPago.sendKeys("C");
        String actual = vwe_cbxSelectFormaPago.getText();
        Util.assert_contiene("Se Selecciona la forma de Pago", "Click en combo box", actual,"CUENTAS", true, "N");
        vwe_btnFormaPagoContinuar.click();
    }

    public void set_datosDonacion(){
        vwe_cbxCuentaOrigen.click();
        vwe_cbxOpcionCuentaOrigen.click();
        vwe_cbxOpcionFundacion.click();
        vwe_tbxMonto.sendKeys("1");
        Util.AvanzarPagina();
        String actual = vwe_cbxOpcionFundacion.getText();
        Util.assert_contiene("Se Seleccionan los datos de la donacion", "Click en combo box", actual,"CRISTO", true, "N");
    }

    public void click_formaPagoConfirmar(){
        vwe_btnFormaPagoConfirmar.click();
    }

    public void click_btnProgramarDonacion(){
        vwe_btnProgramarDonacion.click();
    }

    public void set_programarDonacion(){
        vwe_cbxOpcionProgramar.click();
        vwe_cbxOpcionProgramarUnico.click();
        vwe_cbxOpcionProgramar.click();
        Util.AvanzarPagina();
        String actual = vwe_btnProgramarDonacion.getText();
        Util.assert_contiene("Se establecen los datos para programar la donacion", "Click en combo box", actual,"Programar donac", true, "N");
    }

//FIN-->KFA_003_CYBERBANK-4060

/*
    //System.out.println("::"+lst_bancos.size());
        for(int i = 0; i < lst_bancos.size(); i++){
        System.out.println(i+": "+lst_bancos.get(i).getText());
        if (lst_bancos.get(i).getText().contains(dato)) {
            actual = lst_bancos.get(i).getText();
            idx = i;
            existe_etiq = true;
            break;
        }

    }
*/

}
