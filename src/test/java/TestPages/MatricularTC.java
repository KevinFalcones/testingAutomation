package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class MatricularTC {

    public MatricularTC() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(name="_eventId_nuevaTarjetaInt")
    WebElement btnNuevaTC = null;

    @FindBy(id="alias")
    WebElement txtAlias = null;

    @FindBy(id="selectCreditCardType")
    WebElement cboTipoTC = null;

    @FindBy(id="select2-selectBanco-container")
    WebElement bcoEmisor = null;

    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement comboSearchBcoEmisor = null;

    @FindBy(id="numeroTarjeta")
    WebElement numeroTarjeta = null;

    @FindBy(name="_eventId_continuar")
    WebElement btnContinuar = null;

    @FindBy(id="mail")
    WebElement mail = null;

    @FindBy(name="username")
    WebElement txtBeneficiario = null;

    @FindBy(id="tipoDocumento")
    WebElement cboTipoDocumento = null;

    @FindBy(id="numDocumento")
    WebElement txtNumDocumento = null;

    @FindBy(tagName = "label")
    List<WebElement> etiquetas;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    List<WebElement> mensaje_error = null;

    @FindBy(xpath="//div[@class='e-bank-eb']")
    List<WebElement> lst_agendas = null;

    @FindBy(id = "search")
    WebElement txt_search = null;

    @FindBy(id = "btnEditar")
    WebElement btn_editar = null;

    @FindBy(id = "inpAlias")
    WebElement txt_alias_edt = null;

    @FindBy(id = "inpCorreo")
    WebElement txt_mail_edt = null;

    @FindBy(id = "btnContinuar")
    WebElement btn_matricular = null;

    //Ingresa a la opcion de matricular TC local y completa el primer formulario de confeccion
    public void click_boton_nuevatc()
    {
        String actual = btnNuevaTC.getText();
        Util.assert_contiene("MATRICULAR TARJETAS", "Click en botón", actual,"Nueva Tarjeta", true, "N");
        btnNuevaTC.click();
    }

    public void ingresa_alias(String dato)
    {
        txtAlias.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Ingresa alias", dato, "", false, "N");
    }

    public void selecciona_tipo(String dato)
    {
        cboTipoTC.click();
        cboTipoTC.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Selecciona tipo tc", dato, "", false, "N");
        cboTipoTC.sendKeys(Keys.ENTER);
    }

    public void selecciona_banco(String dato)
    {
        bcoEmisor.click();
        comboSearchBcoEmisor.sendKeys(dato);
        comboSearchBcoEmisor.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Selecciona banco", dato, "", false, "N");
    }

    public void ingresa_numerotc(String dato)
    {
        numeroTarjeta.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Ingresa número de TC", dato, "", false, "N");
    }

    int aleatoreo(int vMin, int vMax) {
        Random r = new Random();
        return vMin + r.nextInt(vMax - vMin + 1);
    }

    public void ingresa_numerotc() {
        for (int i = 1; i <= 16; i++) {
            numeroTarjeta.sendKeys(String.valueOf(aleatoreo(0, 9)));
        }
    }

    public void click_boton_continuar()
    {
        String actual = btnContinuar.getText();
        Util.assert_contiene("MATRICULAR TARJETAS", "Click en botón", actual,"Continuar", true, "N");
        Util.AvanzarPagina();
        btnContinuar.click();
    }

    public void vp_etiqueta_emailbenef()
    {
        Boolean existe_etiq = false;
        String actual = "No existe etiqueta";

        for(int i = 0; i < etiquetas.size(); i++){
            //System.out.println(i+": "+etiquetas.get(i).getText());
            if (etiquetas.get(i).getText().equals("E-mail del beneficiario")) {
                actual = etiquetas.get(i).getText();
                existe_etiq = true;
            }
        }

        if (existe_etiq)
            Reporte.agregarPaso("MATRICULAR TARJETAS", "Verifica etiquetas del Beneficiario", actual, "", false, "N");
        else {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("MATRICULAR TARJETAS", "Verifica etiquetas del Beneficiario", actual, "", true, "N");
        }
    }

    public void ingresa_mail(String dato)
    {
        mail.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Ingresa mail de beneficiario", dato, "", false, "N");
    }

    public void ingresa_beneficiario(String dato)
    {
        txtBeneficiario.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Ingresa nombre del beneficiario", dato, "", false, "N");
    }

    public void selecciona_tipodoc(String dato)
    {
        cboTipoDocumento.click();
        cboTipoDocumento.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Selecciona tipo de documento", dato, "", false, "N");
    }

    public void ingresa_numerodoc(String dato)
    {
        txtNumDocumento.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR TARJETAS", "Ingresa número de documento", dato, "", false, "N");
    }

    public void selecciona_agenda(String dato)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        if (dato.length()>16)
            dato=dato.substring(0,16).trim();

        dato=dato.trim();

        Util.RetrocederPagina(txt_search);
        txt_search.click();
        txt_search.sendKeys(dato.toLowerCase());
        //cmb_searchBcoDestino.sendKeys(Keys.ENTER);
        Boolean existe_etiq = false;
        String actual = "No existe etiqueta";
        int idx=0;

        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        //System.out.println("::"+lst_agendas.size());
        for(int i = 0; i < lst_agendas.size(); i++){
            System.out.println(i+": "+lst_agendas.get(i).getText());
            if (lst_agendas.get(i).getText().contains(dato.toUpperCase())) {
                actual = lst_agendas.get(i).getText();
                idx = i;
                existe_etiq = true;
                break;
            }
        }
        //System.out.println("*"+lst_agendas.get(idx).getText());
        if (existe_etiq){
            Reporte.agregarPaso("MATRICULAR CUENTAS", "Selecciona agenda", actual, "", true, "N");
            lst_agendas.get(idx).click();
        }
    }

    public void click_btn_editar()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = btn_editar.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual,"Editar", false, "N");
        btn_editar.click();
    }

    public void ingresa_alias_edit(String dato)
    {
        txt_alias_edt.clear();

        if (dato.length()>16)
            dato=dato.substring(0,16).trim();

        dato=dato.trim();

        txt_alias_edt.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa alias", dato, "", false, "N");
    }

    public void ingresa_mail_edit(String dato)
    {
        txt_mail_edt.clear();
        txt_mail_edt.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa correo", dato, "", false, "N");
    }

    public void click_btn_guardar()
    {
        String actual = btn_matricular.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual, "Guardar", true, "N");
        btn_matricular.click();
    }
}
