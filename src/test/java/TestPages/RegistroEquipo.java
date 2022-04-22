package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistroEquipo {
    public RegistroEquipo() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//div[@class='title-eb-g white']")
    WebElement etiq_nomatriculado = null;

    @FindBy(id="btnContinuar")
    WebElement btn_continuar = null;

    @FindBy(xpath="//*[@id=\"menuMiPerfil\"]/div/span")
    WebElement btn_menu= null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-equip")
    WebElement btn_equip= null;

    @FindBy(xpath="//img[@class='ico-action-row eliminar']")
    List<WebElement> dispositivos = null;

    @FindBy(xpath="//div[@class='eb-description-footer']")
    WebElement msjSinEquipos = null;

    public void ingresa_opcion_perfil()
    {
        btn_menu.click();
        btn_configuracion.click();
        Util.AvanzarPagina();
        btn_equip.click();
    }

    public void vp_etiqueta_nomatriculado() {
        String actual = etiq_nomatriculado.getText();
        Util.assert_contiene("REGISTRO DE EQUIPO", "Verificación de etiqueta Saludo", actual,"ingresando desde un equipo no matriculado", false, "N");
    }

    public void click_continuar()
    {
        String actual = btn_continuar.getText();
        Util.assert_contiene("REGISTRO DE EQUIPO", "Verificación de etiqueta equipo no matriculado", actual,"Continuar sin matricular", true, "N");
        btn_continuar.click();
        Util.CapturarImagen();
    }

    public int obtiene_num_equipos()
    {
        int cantidad = 0;
        try {
            cantidad = dispositivos.size();
        }
        catch(Exception e)
        {}
        Reporte.agregarPaso("REGISTRO DE EQUIPO", "Cantidad de equipos registrados",String.valueOf(cantidad),"", false, "N");
        return cantidad;
    }

    public void click_btn_eliminar(int idx)
    {
        Reporte.agregarPaso("REGISTRO DE EQUIPO", "Click en botón", "Eliminar equipo", "", true, "N");
        dispositivos.get(idx).click();
    }

    public void vp_mensaje_sinequipos()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = msjSinEquipos.getText();
            Util.assert_contiene("ADMINISTRACION DE EQUIPOS", "Verificación de mensaje", actual, "No cuentas con equipos matriculados", true, "N");
    }
}
