package TestPages;

import Globales.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    public Login() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="tituloIngreseUsername")
    WebElement etiq_usuario = null;

    @FindBy(id="username")
    WebElement txt_username = null;

    @FindBy(id="botonSendUsernamesadasd")
    WebElement btn_user = null;

    @FindBy(id="tituloIngresePassword")
    WebElement etiq_password = null;

    @FindBy(id="password")
    WebElement txt_password = null;

    @FindBy(id="botonSendPassword")
    WebElement btn_password = null;



    String[] datos = null;

    void CargaDatosLogin(String seclogin) {
        if (seclogin.equals("0"))
            seclogin = Util.prop.getProperty("sec_login");

        datos = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_login.txt", seclogin);
        if (datos == null)
            System.out.println("No hay datos en datapool");
        else
        {
            Util.setDataCliente(datos);
            System.out.println("setUp==>Datos Leidos de datapool");
        }
    }

    public void vp_etiqueta_usuario() {
        String actual = etiq_usuario.getText();
        Util.assert_contiene("LOGIN", "Verificación de etiqueta Usuario", actual,"Usuario", false, "N");
    }

    void ingreso_usuario()
    {
        txt_username.sendKeys(Util.getDataCliente()[1]);
        Reporte.agregarPaso("LOGIN", "Ingreso de usuario", Util.getDataCliente()[1], "", false, "N");
    }

    void click_user()
    {
        String actual = btn_user.getAttribute("value");
        Util.assert_contiene("LOGIN", "Click en botón", actual,"Ingresar", false, "N");
        btn_user.click();
    }

    void vp_etiqueta_password() {
        String actual = etiq_password.getText();
        Util.assert_contiene("LOGIN", "Verificación de etiqueta Password", actual,"Contras", false, "N");
    }

    void ingreso_password()
    {
        txt_password.sendKeys(Util.getDataCliente()[2]);
        Reporte.agregarPaso("LOGIN", "Ingreso de contraseña", Util.getDataCliente()[2], "", true, "N");
    }

    void click_passw()
    {
        btn_password.click();
    }

    public void Ingresar()
    {
        Ingresar("0");
    }
    public void Ingresar(String seclogin)
    {
        this.CargaDatosLogin(seclogin);
        this.vp_etiqueta_usuario();
        this.ingreso_usuario();
        this.click_user();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.vp_etiqueta_password();
        this.ingreso_password();
        this.click_passw();

    }
}
