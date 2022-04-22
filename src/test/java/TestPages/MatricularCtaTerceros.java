package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

import static Globales.Util.driver;

public class MatricularCtaTerceros {
    public MatricularCtaTerceros() {
        PageFactory.initElements(driver, this);
    }

    //@FindBy(id = "btnContinuar")
    @FindBy(id = "btnNuevaAgenda")
    WebElement btn_nuevaCuenta = null;

    @FindBy(id = "btnContinuar")
    WebElement btn_nuevaCuenta_old = null;

    @FindBy(id = "selectBank")
    WebElement cmb_banco_destino = null;

    @FindBy(id = "select2-Banco-container")
    WebElement cmb_banco_destino_old = null;

    @FindBy(className = "select-filter")
    WebElement cmb_searchBcoDestino = null;

    @FindBy(className = "select2-search__field")
    WebElement cmb_searchBcoDestino_old = null;

    @FindBy(xpath="//div[@class='text']")
    List<WebElement> lst_bancos = null;

    @FindBy(id = "tipoOtro")
    WebElement cmb_tipoOtro = null;

    @FindBy(id = "tipoBoli")
    WebElement cmb_tipoBoli = null;

    @FindBy(name = "numeroCuenta")
    WebElement txt_nroCuenta = null;

    //@FindBy(id = "AHORRO")
    @FindBy(xpath="//label[@for='AHORRO']")
    WebElement chk_tipo_aho = null;

    @FindBy(xpath="//label[@for='CORRIENTE']")
    WebElement chk_tipo_cte = null;

    @FindBy(id = "EFECTIVA")
    WebElement chk_tipo_efe = null;

    @FindBy(id = "CONTABLE")
    WebElement chk_tipo_con = null;

    //@FindBy(id = "beneficiario")
    @FindBy(name = "nombreBeneficiario")
    WebElement txt_nombreBeneficiario = null;

    @FindBy(id = "tipoDocCualquiera")
    WebElement tipoID = null;

    //@FindBy(id = "nroDocumento")
    @FindBy(id = "documentoId")
    WebElement txt_Identificacion = null;

    @FindBy(id = "showPasaporte")
    WebElement lnk_pasaporte = null;

    //@FindBy(name = "_eventId_continuar")
    @FindBy(id = "btnContinuarNuevo")
    WebElement btnContinuar = null;

    @FindBy(name = "_eventId_continuar")
    WebElement btnContinuar_old = null;

    @FindBy(id = "btnCancelarNuevo")
    WebElement btnCancelar = null;

    @FindBy(id = "mailBeneficiario")
    WebElement txt_mail = null;

    @FindBy(id = "email")
    WebElement txt_mail_old = null;

    @FindBy(id = "aliasVerificar")
    WebElement txt_alias = null;

    @FindBy(id = "alias")
    WebElement txt_alias_old = null;

    @FindBy(id = "btnContinuar")
    WebElement btn_matricular = null;

    //Objetos para puntos de verificación
    @FindBy(id = "tituloSuperior")
    WebElement rutaPantalla = null;

    @FindBy(xpath = "/html/body/div[3]/div[4]/div/div/form/div[3]/div[1]")
    WebElement subtitulo = null;

    @FindBy(xpath="//div[@class='e-bank-eb']")
    List<WebElement> lst_agendas = null;

    @FindBy(id = "search")
    WebElement txt_search = null;

    @FindBy(id = "btnEditar")
    WebElement btn_editar = null;

    //INI-->KFA_001_TestEliminarCta
    @FindBy(id = "btnEliminarAg")
    List<WebElement> vwe_btnEliminarCtaMenu = null;

    @FindBy(id = "btnCancelarSQ")
    WebElement vwe_btnEliminarCtaMenuNo = null;

    @FindBy(id = "btnEliminarSQ")
    WebElement vwe_btnEliminarCtaMenuSi = null;

    @FindBy(id = "action-close")
    WebElement vwe_btnEliminarCtaMenuX = null;

    @FindBy(xpath="//div[@class='font-14 font-rblack color-4']")
    List<WebElement> vwe_msjEliminadaCta = null;

    @FindBy(id="cuentasE")
    WebElement vwe_btnVerCtasMatriculadas = null;

    @FindBy(className="titulo-mensaje")
    WebElement vwe_msjEncuestaSatisfaccion = null;

    @FindBy(id="04")
    WebElement vwe_iconEmojiSatisfecho = null;

    @FindBy(id="btnEncEnviar")
    WebElement vwe_btnEnviarEncuesta = null;

    //FIN-->KFA_001_TestEliminarCta
    @FindBy(id = "inpAlias")
    WebElement txt_alias_edt = null;

    @FindBy(id = "inpCorreo")
    WebElement txt_mail_edt = null;

    //Elementos para eliminación masiva
    @FindBy(id="check0")
    WebElement chk_check00 = null;

    @FindBy(id="textEliminar")
    WebElement checkSeleccion = null;

    @FindBy(id="eliminarSeleccionados")
    WebElement btneliminaSeleccion = null;

    @FindBy(id="btnEliminarQ")
    WebElement btnConfirmaEliminacion = null;


    public void click_btn_nuevaCuenta()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = btn_nuevaCuenta.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual,"+ Nueva cuenta", false, "N");
        btn_nuevaCuenta.click();
    }

    public void click_btn_nuevaCuenta_old()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = btn_nuevaCuenta_old.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual,"Nueva Cuenta", false, "N");
        btn_nuevaCuenta_old.click();
    }

    public void selecciona_banco_old(String dato)
    {
        cmb_banco_destino_old.click();
        cmb_searchBcoDestino_old.sendKeys(dato);
        cmb_searchBcoDestino_old.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Selecciona banco", dato, "", false, "N");
    }

    public void selecciona_banco(String dato)
    {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        cmb_banco_destino.click();
        cmb_searchBcoDestino.sendKeys(dato.toLowerCase());
        //cmb_searchBcoDestino.sendKeys(Keys.ENTER);
        Boolean existe_etiq = false;
        String actual = "No existe etiqueta";
        int idx=0;

        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

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

        if (existe_etiq){
            Reporte.agregarPaso("MATRICULAR CUENTAS", "Selecciona banco", actual, "", true, "N");
            lst_bancos.get(idx).click();
        }
    }

    public void selecciona_tipo_cta_old(String tipo, String bco){
        if(bco.equals("Bolivariano")){
            cmb_tipoBoli.click();
            cmb_tipoBoli.sendKeys(tipo);
            cmb_tipoBoli.sendKeys(Keys.ENTER);

        }else{
            cmb_tipoOtro.click();
            cmb_tipoOtro.sendKeys(tipo);
            cmb_tipoOtro.sendKeys(Keys.ENTER);
        }
    }

    public void selecciona_tipo_cta(String tipo){
        switch (tipo)
        {
            case "AHORRO": chk_tipo_aho.click();break;
            case "CORRIENTE": chk_tipo_cte.click();break;
        }
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Selecciona tipo de cuenta", tipo, "", false, "N");
    }

    int aleatoreo(int vMin, int vMax) {
        Random r = new Random();
        return vMin + r.nextInt(vMax - vMin + 1);
    }

    public void ingresa_num_cta(String s){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        txt_nroCuenta.click();

        if(s.equals("-")){
            for(int i = 1; i<=10; i++){
                txt_nroCuenta.sendKeys(String.valueOf(aleatoreo(0,9)));
            }
            s = txt_nroCuenta.getText();
        }else {
            txt_nroCuenta.sendKeys(s);
        }
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa número de cuenta", s, "", false, "N");
    }

    public void ingresa_beneficiario(String dato)
    {
        txt_nombreBeneficiario.sendKeys(dato);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa nombre de beneficiario", dato, "", false, "N");
    }

    public void selecciona_tipo_identif(String dato)
    {
        if (dato.equals("P")){
            lnk_pasaporte.click();
        }
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Selecciona tipo de identificación", dato, "", false, "N");
        Util.AvanzarPagina();
    }

    public void ingresa_identificacion (String identificacion)
    {
        txt_Identificacion.sendKeys(identificacion);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa identificación", identificacion, "", false, "N");
    }

    public void click_btn_continuar()
    {
        String actual = btnContinuar.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual, "Continuar", true, "N");
        btnContinuar.click();
    }

    public void click_btn_continuar_old()
    {
        String actual = btnContinuar_old.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual, "Continuar", true, "N");
        btnContinuar_old.click();
    }

    public void ingresar_alias(String alias) {
        txt_alias.sendKeys(alias);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa alias", alias, "", false, "N");
    }

    public void ingresar_alias_old(String alias) {
        txt_alias_old.sendKeys(alias);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa alias", alias, "", false, "N");
    }

    public void ingresa_mail_beneficiario(String email) {
        Util.AvanzarPagina(txt_mail);
        txt_mail.sendKeys(email);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa identificación", email, "", true, "N");
        Util.AvanzarPagina(txt_mail);
    }

    public void ingresa_mail_beneficiario_old(String email) {
        txt_mail_old.sendKeys(email);
        Reporte.agregarPaso("MATRICULAR CUENTAS", "Ingresa identificación", email, "", false, "N");
        Util.AvanzarPagina();
    }

    public void avanza_pagina() {
        Util.AvanzarPagina(txt_mail);
    }

    public void click_btn_matricular()
    {
        Util.AvanzarPagina(txt_mail);
        String actual = btn_matricular.getText();
        Util.assert_contiene("MATRICULAR CUENTAS", "Click en botón", actual, "Matricular", true, "N");
        btn_matricular.click();
    }

    public void VP_NombreFuncionalidad() {
        String actual = rutaPantalla.getText();
        Util.assert_igual("MATRICULAR CUENTAS", "Verifica el nombre de la funcionalidad accesada", actual, "Transferir | Matricular", false, "N");
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

        txt_search.click();
        txt_search.clear();//KFA_001_TestEliminarCta
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

    /*Kevin Falcones - Gestion de Automatizacion y Pruebas
      Eliminacion Exitosa de Cuentas Terceros y Otros Bancos - KFA_001_TestEliminarCta
      Condiciones: Solo toma los registros con estos valores: Matricula->S
     */
    //INI-->KFA_001_TestEliminarCta
    public void click_btnEliminarCtaMenu()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for(int i = 0; i < vwe_btnEliminarCtaMenu.size(); i++) {
            System.out.println(i+": "+vwe_btnEliminarCtaMenu.get(i).getText());
        }

        String actual = vwe_btnEliminarCtaMenu.get(0).getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS", "Click en botón", actual,"Eliminar cuenta", true, "N");
        vwe_btnEliminarCtaMenu.get(0).click();
    }

    public void click_btnEliminarCtaMenuNo()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnEliminarCtaMenuNo.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS NO", "Click en botón", actual,"No, cancelar", false, "N");
        vwe_btnEliminarCtaMenuNo.click();
    }

    public void click_btnEliminarCtaMenuX()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnEliminarCtaMenuX.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SALIR", "Click en botón", actual,"", false, "N");
        vwe_btnEliminarCtaMenuX.click();
    }

    public void click_btnEliminarCtaMenuSi()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = vwe_btnEliminarCtaMenuSi.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI", "Click en botón", actual,"Sí, eliminar", false, "N");
        vwe_btnEliminarCtaMenuSi.click();
    }

    public void get_msjEliminadaCta()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for(int i = 0; i < vwe_msjEliminadaCta.size(); i++) {
            System.out.println(i+": "+vwe_msjEliminadaCta.get(i).getText());
        }

        String msjok = null;
        String msjerror = null;
        String actual = null;
        msjok = vwe_msjEliminadaCta.get(0).getText();
        msjerror = vwe_msjEliminadaCta.get(1).getText();

        if (!msjerror.equals(""))
            actual = msjerror;
        else
            actual = msjok;

        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI MSJ", "Verificación de mensaje exitoso", actual,"¡Listo! matriculación eliminada exitosamente.", true, "N");
    }

    public void click_btnVerCtasMatriculadas()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = vwe_btnVerCtasMatriculadas.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI VER", "Click en botón", actual,"Ver mis cuentas matriculadas", false, "N");
        vwe_btnVerCtasMatriculadas.click();
    }

    public void get_msjEncuestaSatisfaccion()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = vwe_btnVerCtasMatriculadas.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI MSJ", "Verificación de mensaje exitoso", actual,"¿Qué tan satisfecho estás con esta transacción?", true, "N");
    }

    public void click_iconEmojiSatisfecho()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = vwe_iconEmojiSatisfecho.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI VER", "Click en botón", actual,"", true, "N");
        vwe_iconEmojiSatisfecho.click();
    }

    public void click_btnEnviarEncuesta()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String actual = vwe_btnEnviarEncuesta.getText();
        Util.assert_contiene("ELIMINACION DE CUENTAS MATRICULADAS SI VER", "Click en botón", actual,"Enviar", true, "N");
        vwe_btnEnviarEncuesta.click();
    }
    //FIN-->KFA_001_TestEliminarCta


    public void ingresa_alias_edit(String dato)
    {
        txt_alias_edt.clear();
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

    public void chk_eliminar(){
        Actions action = new Actions(driver);

        if (!chk_check00.isSelected())
        {
            try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
            action.moveToElement(chk_check00).click().build().perform();
        }
    }

    public void chk_seleccion(){checkSeleccion.click();}

    public void click_btnEliminaSeleccion(){
        String actual = btneliminaSeleccion.getText();
        Util.assert_contiene("Eliminar matriculaciones de forma masiva", "Click en botón", actual, "Eliminar cuentas", true, "N");
        btneliminaSeleccion.click();
    }

    public void click_btnConfirmaEliminacion(){
        String actual = btnConfirmaEliminacion.getText();
        Util.assert_contiene("Confirmar eliminación masiva", "Click en botón", actual, "", true, "N");
        btnConfirmaEliminacion.click();
    }



}


