package TestPages;

import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ComprobantePerfil {
    public ComprobantePerfil() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//*[@id=\"actualizacionPreguntasSecretasCommand\"]/div[1]/div[1]")
    WebElement msjOKPreguntas = null;

    @FindBy(xpath="//*[@id=\"centrecontent\"]/div[3]/div[1]/div[1]")
    WebElement msjOKUsuario = null;

    @FindBy(xpath="//div[@class='modal-eliminar-dispositivo-content success']")
    WebElement msjOKEquipo = null;

    @FindBy(xpath="//div[@class='encabezado']")
    List<WebElement> msjOKDesbloqueoDisp = null;

    @FindBy(xpath="//li[@class='severity-FATAL']")
    List<WebElement> mensaje_error = null;

    @FindBy(id="btnRegresar")
    WebElement btnVerEquipos = null;

    public void vp_mensaje_actpreguntas_exitoso()
    {
        String actual = null;
        try {
            actual = msjOKPreguntas.getText();
            Util.assert_contiene("PREGUNTA SEGURIDAD", "Verificación de mensaje exitoso", actual, "Preguntas de seguridad cambiadas de manera exitosa", true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("PREGUNTA SEGURIDAD", "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void vp_mensaje_cambiousuario_exitoso()
    {
        String actual = null;
        try {
            actual = msjOKUsuario.getText();
            Util.assert_contiene("CAMBIO DE USUARIO", "Verificación de mensaje exitoso", actual, "Ahora tu nombre de usuario es", true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("CAMBIO DE USUARIO", "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void vp_mensaje_eliminaequipo_exitoso()
    {

        String actual = null;
        try {
            Thread.sleep(3000);
            actual = msjOKEquipo.getText();
            Util.assert_contiene("ADMINISTRACION DE EQUIPOS", "Verificación de mensaje exitoso", actual, "fue eliminado de tus equipos", true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("ADMINISTRACION DE EQUIPOS", "Verificación de mensaje", actual, "", true, "N");
        }
    }

    public void click_btn_VerEquipos()
    {
        String actual = btnVerEquipos.getText();
        Util.assert_contiene("ADMINISTRACION DE EQUIPOS", "Da click en botón", actual, "Ver mis equipos", false, "N");
        btnVerEquipos.click();
    }

    public void vp_mensaje_desbloqueadispo_exitoso()
    {
        String actual = null;
        try {
            Thread.sleep(3000);
            System.out.println(msjOKDesbloqueoDisp.size());
            actual = msjOKDesbloqueoDisp.get(1).getText();
            Util.assert_contiene("DESBLOQUEO DE DISPOSITIVO", "Verificación de mensaje exitoso", actual, "Tu dispositivo de seguridad se encuentra desbloqueado", true, "N");
        }
        catch (Exception e)
        {
            actual = mensaje_error.get(0).getText();
            Util.assert_igual("DESBLOQUEO DE DISPOSITIVO", "Verificación de mensaje", actual, "", true, "N");
        }
    }
}

