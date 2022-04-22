package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPagoServicios {
    public MenuPagoServicios() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(css="#PAGOS p")
    WebElement menupagos = null;

    @FindBy(id="EB_PAGO_DE_SERVICIOS")
    WebElement submenupagos = null;

    @FindBy(id = "btnContinuar")
    WebElement Btn_matricular = null;

    @FindBy(id="titulo_PAGO_IESS")
    WebElement opcionIess = null;

    @FindBy(id="espacioBuscar")
    WebElement Btn_cancelarIess = null;
    WebElement Btn_cancelarSENAE = null;

    @FindBy(name="_eventId_continuar")
    WebElement Btn_aceptarIess = null;

    @FindBy(name="_eventId_continuarAduana")
    WebElement Btn_aceptarSENAE = null;

    @FindBy(id="titulo_LUZ")
    WebElement opcionLuz = null;

    @FindBy(id="titulo_AGUA")
    WebElement opcionAgua = null;

    @FindBy(id="titulo_TELEFONIA_FIJA")
    WebElement opcionTelefoniaFija = null;

    @FindBy(id="titulo_TELEFONIA_CELULAR")
    WebElement opcionTelefoniaCelular = null;

    @FindBy(id="titulo_TELEVISION_PAGADA")
    WebElement opcionTelevisionPagada = null;

    @FindBy(id="titulo_INTERNET")
    WebElement opcionInternet = null;

    @FindBy(id="titulo_PAGO_ADUANA")
    WebElement opcionPagoAduana = null;

    @FindBy(id="titulo_IMPUESTOS_OBLIGACIONES")
    WebElement opcionImpuestosObligaciones = null;

    @FindBy(id="titulo_EDUCACION")
    WebElement opcionEducacion = null;

    @FindBy(id="titulo_AUTOMOTORES_PEATONES")
    WebElement opcionAutomotoresPeatones = null;

    @FindBy(id="titulo_CASAS_TARJETAS_COMERCIALES")
    WebElement opcionCasasTarjetasComerciales = null;

    @FindBy(id="titulo_TRANSFERENCIA_ESPECIAL")
    WebElement opcionTransferenciaEspecial = null;

    @FindBy(name="_eventId_posicionConsolidada")
    WebElement Btn_PosicionConsolidada = null;

    String[] datos = null;

    public void click_menu_pagos()
    {
        menupagos.click();
        String actual = menupagos.getText();
        Util.assert_igual("MENU PAGAR SERVICIO", "Verificación de menú", actual,"Pagar", false, "N");
    }

    public void click_submenu_pagos()
    {
        submenupagos.click();
        String actual = submenupagos.getText();
        Reporte.agregarPaso("MENU PAGAR SERVICIO", "Verificación de submenú", actual,"Pagar", true, "N");
    }

    public void BotonMatricular()
    {
        String actual = Btn_matricular.getText().trim();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del botón «Matricular servicios»", actual,"Matricular servicios", false, "N");
    }

    public void Click_BotonMatricular()
    {
        String actual = Btn_matricular.getText().trim();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del botón «Matricular servicios»", actual,"Matricular servicios", false, "N");
        Btn_matricular.click();
    }

    public void click_SeguroSocial()
    {
        opcionIess.click();
        String actual = opcionIess.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio IESS", actual,"Seguro Social (IESS)", true, "N");
    }

    public void BotonCancelar_SeguroSocial()
    {
        String actual = Btn_cancelarIess.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Validar botón «Cancelar» dentro del servicio IESS", actual,"Cancelar", false, "N");
    }

    public void BotonAceptar_SeguroSocial()
    {
        String actual = Btn_aceptarIess.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Validar botón «Sí, continuar» dentro de la opción", actual,"continuar", false, "N");
    }

    public void Opcion_SeguroSocial()
    {
        Btn_aceptarIess.click();
    }

    public void Opcion_Luz() {
        String actual = opcionLuz.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio LUZ", actual,"Luz", false, "N");
    }

    public void Opcion_Agua() {
        String actual = opcionAgua.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio AGUA", actual,"Agua", false, "N");
    }

    public void Opcion_TelefoniaFija() {
        String actual = opcionTelefoniaFija.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio TELEFONIA FIJA", actual,"Fija", false, "N");
    }

    public void Opcion_TelefoniaCelular() {
        String actual = opcionTelefoniaCelular.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio TELEFONIA CELULAR", actual,"Celular", false, "N");
    }

    public void Opcion_TelevisionPagada() {
        String actual = opcionTelevisionPagada.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio  TELEVISION PAGADA", actual,"pagada", false, "N");
    }

    public void Opcion_Internet() {
        String actual = opcionInternet.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio INTERNET", actual,"Internet", false, "N");
        Util.AvanzarPagina();
    }

    public void Opcion_ImpuestosAduaneros() {
        String actual = opcionPagoAduana.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio IMPUESTOS ADUANEROS (SENAE)", actual,"Impuestos Aduaneros", true, "N");
    }

    public void click_SENAE()
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.AvanzarPagina();
        /*Util.AvanzarPagina();
        Util.AvanzarPagina();*/
        opcionPagoAduana.click();
        String actual = opcionPagoAduana.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta y dar click en la opción ImpuestosAduaneros", actual,"Impuestos Aduaneros (SENAE)", true, "N");
        Util.AvanzarPagina();
    }

    public void BotonCancelar_SENAE()
    {
        String actual = Btn_cancelarSENAE.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Validar botón «Cancelar» dentro del servicio IESS", actual,"Cancelar", false, "N");
    }

    public void BotonAceptar_SENAE()
    {
        String actual = Btn_aceptarSENAE.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Validar botón «Sí, continuar» dentro de la opción", actual,"continuar", false, "N");
    }

    public void Opcion_SENAE()
    {
        Util.AvanzarPagina();
        Btn_aceptarSENAE.click();
    }


    public void Opcion_ImpuestosObligaciones() {
        String actual = opcionImpuestosObligaciones.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio IMPUESTOS Y OBLIGACIONES", actual,"Impuestos y obligaciones", false, "N");
    }

    public void Opcion_Educacion() {
        String actual = opcionEducacion.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio EDUCACION", actual,"Educaci", false, "N");
    }

    public void Opcion_AutomotoresPeatones() {
        String actual = opcionAutomotoresPeatones.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio AUTOMOTORES Y PEATONES", actual,"Automotores y peatones", false, "N");
    }

    public void Opcion_TarjetasComerciales() {
        String actual = opcionCasasTarjetasComerciales.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio TARJETAS COMERCIALES / ALMACENES", actual,"Tarjetas Comerciales / Almacenes", false, "N");
    }

    public void Opcion_TransferenciasEspeciales() {
        String actual = opcionTransferenciaEspecial.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del servicio TRANSFERENIAS ESPECIALES", actual,"Transferencias especiales", false, "N");
    }

    public void BotonPosicionConsolidada() {
        String actual = Btn_PosicionConsolidada.getText();
        Util.assert_contiene("PANTALLA ADMINISTRACION DE PAGOS", "Verificar etiqueta del botón «Posición Consolidada»", actual,"consolidada", false, "C");
    }

}
