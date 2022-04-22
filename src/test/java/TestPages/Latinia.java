package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class Latinia {
    public Latinia() {
        PageFactory.initElements(Util.driverF, this);
    }

    @FindBy(xpath="//*[@class='login-wrapper-v2__header__logo__context']")
    WebElement titulo = null;

    //@FindBy(css="body > app-root > app-login-page > app-login > div > form > div > div > div:nth-child(2) > input")
    @FindBy(xpath="//input[@formcontrolname='enterprise']")
    WebElement txt_companyLatinia = null;

    //@FindBy(css="body > app-root > app-login-page > app-login > div > form > div > div > div:nth-child(3) > input")
    @FindBy(xpath="//input[@formcontrolname='username']")
    WebElement txt_userLatinia = null;

    //@FindBy(xpath="/html/body/app-root/app-login-page/app-login/div/form/div/div/div[4]/input")
    @FindBy(xpath="//input[@formcontrolname='password']")
    WebElement txt_passwordLatinia = null;

    @FindBy(css="body > app-root > app-login-page > app-login > div > form > div > div > div.login-wrapper-v2__content__container__form__button > button")
    WebElement btn_accessLatinia = null;

    //@FindBy(xpath="//*[@id=\"cdk-drop-list-6\"]/div/app-contract-page/app-contract/a/div")
    @FindBy(xpath="//span[@class='contract-title']")
    WebElement btn_detalleMensajes = null;

    @FindBy(id="msg-details-filter")
    WebElement btn_filtroMensajes = null;

    @FindBy(id="search-bar-input")
    WebElement txt_buscarMensaje = null;

    @FindBy(css="body > app-root > app-message-details > mat-sidenav-container > mat-sidenav-content > app-message-details-mt-page > app-message-details-mt > div > div > div > mat-card > table > tbody > tr:nth-child(1) > td.mat-cell.cdk-column-message.mat-column-message.ng-star-inserted > div > span")
    WebElement label_otp_Latinia = null;

    @FindBy(id="em-monitor-refresh-button")
    WebElement btn_refresh = null;


    String[] datos = null;

    String otp=null;

    void CargaDatosLogin(String seclogin) {
        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_login_latinia.txt", seclogin);
        if (datos == null)
            System.out.println("No hay datos en datapool");
        else
        {
            Util.setDataLatinia(datos);
            System.out.println("setUp==>Datos Leidos de datapool");
        }
    }

    void open_Latinia(){
        /*Util.openTab();
        ArrayList<String> tabs = new ArrayList<String>(Util.driver.getWindowHandles());
        Util.driver.switchTo().window(tabs.get(1));
        Util.driver.get("http://internal-aa6b053ca1b4c11ea94d90eaeb2d65b9-985227668.us-east-1.elb.amazonaws.com:9081/limsp-ui/");*/

    }

    void vp_titulo()
    {
        try {
            Thread.sleep(5000); //darle tiempo para que Latinia presente la nueva OTP...
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = titulo.getText();
        Util.assert_contiene("LATINIA", "Verificación de título", actual, "The Context Banking Company", false, "N");
    }

    void login_Latinia() {
        vp_titulo();
        System.out.println(Util.getDataLatinia()[1]);
        System.out.println(Util.getDataLatinia()[2]);
        System.out.println(Util.getDataLatinia()[3]);
        txt_companyLatinia.sendKeys(Util.getDataLatinia()[1]);
        txt_userLatinia.sendKeys(Util.getDataLatinia()[2]);
        txt_passwordLatinia.sendKeys(Util.getDataLatinia()[3]);
        btn_accessLatinia.click();
        Reporte.agregarPaso("LATINIA", "Ingreso a Latinia", Util.getDataLatinia()[2] + "::" + Util.getDataLatinia()[3], "", false, "N");
    }

    void opcion_detalle_mensajes_Latinia()  {
        btn_detalleMensajes.click();
    }

    void refrescar()
    {
        btn_refresh.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public String filtrar_mensajes_Latinia(String medioEnvio) {
        //Util.waitForElementToBeClickable(By.id("msg-details-filter")); //esperar a que boton filtro aparezca
        try {
            Thread.sleep(4000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        btn_filtroMensajes.click();
        txt_buscarMensaje.sendKeys(medioEnvio);
        this.refrescar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        this.refrescar();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        this.refrescar();

        otp = label_otp_Latinia.getText().substring(42);
        otp = Util.removeLastChars(otp, 81);
        Reporte.agregarPaso("LATINIA", "OTP en Latinia", otp, "", false, "N");
        System.out.println(otp);
        return otp;
    }

    public void login(String secloginlat)
    {
        this.CargaDatosLogin(secloginlat);
        this.login_Latinia();
    }

    public String getOTP(String medioEnvio) {
        opcion_detalle_mensajes_Latinia();
        String codigootp = filtrar_mensajes_Latinia(medioEnvio);
        return codigootp;
    }
}
