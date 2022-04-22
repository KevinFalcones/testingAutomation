package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContrasenaOlvidada {
    public ContrasenaOlvidada() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="usuario-olvidado")
    WebElement lnk_olvido = null;

    @FindBy(css="#olvidoClaveCommand > div.contenedor-titulo-seccion > div")
    WebElement label_olvido_contrasena = null;

    @FindBy(css="#formShow > div.title-autoadhesion")
    WebElement label_crear_contrasena = null;

    @FindBy(css="label:nth-child(2)")
    WebElement btn_cedula = null;

    @FindBy(id="numDocumento")
    WebElement txt_cedula = null;

    @FindBy(id="inputCaptcha")
    WebElement txt_captcha = null;

    @FindBy(id="btnContinuar")
    WebElement btn_continuar = null;


    @FindBy(css="#contentStep2 > ul:nth-child(8) > div > div.header-2dof > div.title-2dof.inc-sec-2dof")
    WebElement label_otp = null;

    @FindBy(xpath="//*[@id=\"optionListMedios\"]/div[1]")
    WebElement medioCelular = null;

    @FindBy(xpath="//*[@id=\"optionListMedios\"]/div[1]/div[2]")
    WebElement label_medioDeEnvioSeleccionado = null;

    @FindBy(id="desafio1") WebElement txt_otp1 = null;
    @FindBy(id="desafio2") WebElement txt_otp2 = null;
    @FindBy(id="desafio3") WebElement txt_otp3 = null;
    @FindBy(id="desafio4") WebElement txt_otp4 = null;
    @FindBy(id="desafio5") WebElement txt_otp5 = null;
    @FindBy(id="desafio6") WebElement txt_otp6 = null;
    @FindBy(id="desafio7") WebElement txt_otp7 = null;
    @FindBy(id="desafio8") WebElement txt_otp8 = null;

    @FindBy(id="usuario")
    WebElement txt_usuario = null;

    @FindBy(id="claveNueva")
    WebElement txt_claveNueva = null;

    @FindBy(id="claveNuevaParaVerificacion")
    WebElement txt_claveNuevaVerificacion = null;

    @FindBy(id="btnEncuesta")
    WebElement btn_irA24Online = null;

    String[] datos = null;

    String medioEnvio = null;

    String otp = null;

    void CargaDatosLogin() {
        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_contrasenaOlvidada.txt", Util.prop.getProperty("sec_login"));
        if (datos == null)
            System.out.println("No hay datos en datapool");
        else
        {
            Util.setDataCliente(datos);
            System.out.println("setUp==>Datos Leidos de datapool");
        }
    }

    public void ingreso_usuario(){
        Login login = new Login();
        login.ingreso_usuario();
        login.click_user();
    }
    public void vp_link_olvidocontrasena() {
        String actual = lnk_olvido.getText();
        Util.assert_contiene("CONTRASEÑA OLVIDADA", "Verificación de link de Olvido de Contraseña", actual,"¿Olvidaste tu contraseña?", true, "C");
    }

    void click_olvido_contrasena()
    {
        lnk_olvido.click();
    }

    void vp_label_olvidocontrasena() {
        String actual = label_olvido_contrasena.getText();
        Util.assert_contiene("CONTRASEÑA OLVIDADA", "Verificación de etiqueta título Olvido de Contraseña", actual,"Olvido de Contraseña", true, "C");
    }

    void click_btn_cedula()
    {
        btn_cedula.click();
    }

    void ingreso_cedula()
    {
        txt_cedula.sendKeys(Util.getDataCliente()[2]);
        Reporte.agregarPaso("CONTRASEÑA OLVIDADA", "Ingreso de cédula", Util.getDataCliente()[2], "", true, "N");
    }

    void ingreso_captcha() throws InterruptedException {
        Util.AvanzarPagina();
        Thread.sleep(10000);
        Reporte.agregarPaso("CONTRASEÑA OLVIDADA", "Ingreso de captcha", txt_captcha.getText(), "", true, "N");
    }

    void click_btn_continuar()
    {
        Util.waitForElementToBeClickable(By.id("btnContinuar"));
        btn_continuar.click();
    }

    void vp_label_otp(){
        Util.waitForElementToBeClickable(By.cssSelector("#contentStep2 > ul:nth-child(8) > div > div.header-2dof > div.title-2dof.inc-sec-2dof")); //esperar a que medio celular sea clickable
        String actual = label_otp.getText();
        Util.assert_contiene("USUARIO OLVIDADO", "Verificación de etiqueta de clave temporal OTP", actual,"Clave temporal (OTP)", true, "C");
    }

    void click_medio_envio(){
        medioEnvio = label_medioDeEnvioSeleccionado.getText();
        medioCelular.click();
    }

    void get_otp_Latinia() {
        Latinia latinia = new Latinia();
        latinia.login("2");
        otp = latinia.getOTP(medioEnvio);
    }

    void ingresar_otp(){
        txt_otp1.sendKeys(String.valueOf(otp.charAt(0)));
        txt_otp2.sendKeys(String.valueOf(otp.charAt(1)));
        txt_otp3.sendKeys(String.valueOf(otp.charAt(2)));
        txt_otp4.sendKeys(String.valueOf(otp.charAt(3)));
        txt_otp5.sendKeys(String.valueOf(otp.charAt(4)));
        txt_otp6.sendKeys(String.valueOf(otp.charAt(5)));
        txt_otp7.sendKeys(String.valueOf(otp.charAt(6)));
        txt_otp8.sendKeys(String.valueOf(otp.charAt(7)));
    }



    void screenshot_otp_exitosa(){
        Reporte.agregarPaso("CONTRASEÑA OLVIDADA", "Ingreso de OTP", "", "", true, "N");
    }

    void vp_crear_contrasena(){
        String actual = label_crear_contrasena.getText();
        Util.assert_contiene("CONTRASEÑA OLVIDADA", "Crea tu nueva contraseña", actual,"Crea tu nueva contraseña", true, "C");
    }

    void crear_nueva_contrasena() {
        txt_usuario.sendKeys(Util.getDataCliente()[1]);
        txt_claveNueva.sendKeys(Util.getDataCliente()[3]);
        txt_claveNuevaVerificacion.sendKeys(Util.getDataCliente()[3]);
        Util.AvanzarPagina();
        Reporte.agregarPaso("CONTRASEÑA OLVIDADA", "Ingreso de nuevo contraseña", "", "",  true, "N");
    }

    void vp_nueva_contrasena_creada(){
        String actual = btn_irA24Online.getText();
        Util.assert_contiene("CONTRASEÑA OLVIDADA", "Contraseña nueva creada", actual,"Ingresar a 24online", true, "C");
    }

    public void Ingresar() throws InterruptedException {
        this.CargaDatosLogin();
        this.ingreso_usuario();
        this.vp_link_olvidocontrasena();
        this.click_olvido_contrasena();
        this.vp_label_olvidocontrasena();
        this.click_btn_cedula();
        this.ingreso_cedula();
        this.ingreso_captcha();
        this.click_btn_continuar();
        this.vp_label_otp();
        this.click_medio_envio();
        this.get_otp_Latinia();
        this.ingresar_otp();
        this.screenshot_otp_exitosa();
        this.click_btn_continuar();
        this.vp_crear_contrasena();
        this.CargaDatosLogin();
        this.crear_nueva_contrasena();
        this.click_btn_continuar();
        this.vp_nueva_contrasena_creada();
    }
}
