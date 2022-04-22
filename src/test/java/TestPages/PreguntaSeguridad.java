package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;
import java.util.Iterator;
import java.util.List;

public class PreguntaSeguridad {
    public PreguntaSeguridad() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="varInt")
    WebElement btn_internacional = null;

    @FindBy(xpath="//div[@class='title-2dof inc-sec-2dof']")
    WebElement etiq_pregunta = null;

    @FindBy(className="info-2dof")
    WebElement lbl_pregunta = null;

    @FindBy(id="respuesta")
    WebElement txt_respuesta = null;

    @FindBy(id="btnValidarRespuesta")
    WebElement btn_val_respuesta = null;

    @FindBy(xpath="//*[@id=\"contenidoAuth\"]/div[5]")
    WebElement msg_ok = null;

    @FindBy(xpath="//*[@id=\"contenidoAuth\"]/div[6]")
    WebElement msg_error = null;

    @FindBy(id="btnContinuar")
    WebElement btn_continuar= null;

    @FindBy(xpath="//*[@id=\"centrecontent\"]/div[2]/div")
    WebElement etiq_pregunta_perfil= null;

    @FindBy(xpath="//*[@id=\"menuMiPerfil\"]/div/span")
    WebElement btn_menu= null;

    @FindBy(xpath="//*[@id=\"menuOperador\"]/li[1]")
    WebElement btn_configuracion= null;

    @FindBy(id="conf-preguntas")
    WebElement btn_preguntas= null;

    @FindBy(xpath="//*[@id=\"seleccionRespuestasSecretas0.idPreguntaSecreta\"]")
    WebElement cb_pregunta0= null;

    @FindBy(xpath="//*[@id=\"seleccionRespuestasSecretas1.idPreguntaSecreta\"]")
    WebElement cb_pregunta1= null;

    @FindBy(xpath="//*[@id=\"seleccionRespuestasSecretas2.idPreguntaSecreta\"]")
    WebElement cb_pregunta2= null;

    @FindBy(xpath="//*[@id=\"seleccionRespuestasSecretas3.idPreguntaSecreta\"]")
    WebElement cb_pregunta3= null;

    @FindBy(xpath="//*[@id=\"seleccionRespuestasSecretas4.idPreguntaSecreta\"]")
    WebElement cb_pregunta4= null;

    @FindBy(id="respuestas0")
    WebElement respuesta0= null;

    @FindBy(id="respuestas1")
    WebElement respuesta1= null;

    @FindBy(id="respuestas2")
    WebElement respuesta2= null;

    @FindBy(id="respuestas3")
    WebElement respuesta3= null;

    @FindBy(id="respuestas4")
    WebElement respuesta4= null;

    @FindBy(id="btnContinuar")
    WebElement btn_actualizar= null;

    @FindBy(xpath="//div[@class='icon-otp-medio-CELULAR']")
    List<WebElement> medios_celular = null;

    @FindBy(xpath="//div[@class='icon-otp-medio-EMAIL']")
    List<WebElement> medios_email = null;

    void click_internacional()
    {
        btn_internacional.click();
    }

    void vp_etiqueta_pregunta()
    {
        String actual = etiq_pregunta.getText();
        Util.assert_contiene("PREGUNTA SEGURIDAD", "Verificación de título", actual,"Pregunta de seguridad", false, "N");
    }

    void contestar_pregunta() {
        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_preguntas_seguridad.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        String pregunta = lbl_pregunta.getText();
        String respuesta = null;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            if (pregunta.contains(dato[0])) {
                respuesta = dato[1];
                break;
            }
        }

        this.escribe_respuesta(respuesta);
    }

    void escribe_respuesta(String respuesta)
    {
        if (respuesta == null)
            respuesta = "amarillo";

        txt_respuesta.click();
        txt_respuesta.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Ingreso de respuesta", respuesta, "", true, "N");
    }

    void click_validarespuesta()
    {
        btn_val_respuesta.click();
        Boolean existe_error = false;

        try {
            if (msg_ok.getText().contains("Respuesta correcta"))
                existe_error = false;
            else if (msg_error.getText().contains("Respuesta incorrecta"))
                existe_error = true;
        }
        catch (Exception e){
        }

        if (existe_error) {
            this.escribe_respuesta(null);
            this.click_validarespuesta();
        }

        Util.AvanzarPagina();
    }

    void click_continuar()
    {
        if (btn_continuar.isEnabled())
            btn_continuar.click();
        //else
            //Util.assert_igual("PREGUNTA SEGURIDAD", "Verificación de respuesta", "Respuesta incorrecta, inténtalo nuevamente", "Respuesta correcta",true, "N");
    }

    public void ResponderCorrectamente()
    {
        this.click_internacional();
        this.vp_etiqueta_pregunta();
        this.contestar_pregunta();
        this.click_validarespuesta();
        this.click_continuar();
    }

    public void ingresa_opcion_perfil()
    {
        btn_menu.click();
        btn_configuracion.click();
        btn_preguntas.click();
    }

    public void vp_etiqueta_preguntaperfil()
    {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = etiq_pregunta_perfil.getText();
        Util.assert_contiene("PREGUNTA SEGURIDAD", "Verificación de título", actual,"Preguntas de seguridad", false, "N");
    }

    public void selecciona_pregunta0(String pregunta)
    {
        cb_pregunta0.click();
        List<WebElement> preguntas = Util.driver.findElements(By.xpath("//*[@id=\"seleccionRespuestasSecretas0.idPreguntaSecreta\"]/option"));
        this.busca_pregunta(preguntas, pregunta);
        cb_pregunta0.click();
    }

    public void selecciona_pregunta1(String pregunta)
    {
        cb_pregunta1.click();
        List<WebElement> preguntas = Util.driver.findElements(By.xpath("//*[@id=\"seleccionRespuestasSecretas1.idPreguntaSecreta\"]/option"));
        this.busca_pregunta(preguntas, pregunta);
        cb_pregunta1.click();
    }

    public void selecciona_pregunta2(String pregunta)
    {
        cb_pregunta2.click();
        List<WebElement> preguntas = Util.driver.findElements(By.xpath("//*[@id=\"seleccionRespuestasSecretas2.idPreguntaSecreta\"]/option"));
        this.busca_pregunta(preguntas, pregunta);
        cb_pregunta2.click();
    }

    public void selecciona_pregunta3(String pregunta)
    {
        cb_pregunta3.click();
        List<WebElement> preguntas = Util.driver.findElements(By.xpath("//*[@id=\"seleccionRespuestasSecretas3.idPreguntaSecreta\"]/option"));
        this.busca_pregunta(preguntas, pregunta);
        cb_pregunta3.click();
    }

    public void selecciona_pregunta4(String pregunta)
    {
        cb_pregunta4.click();
        List<WebElement> preguntas = Util.driver.findElements(By.xpath("//*[@id=\"seleccionRespuestasSecretas4.idPreguntaSecreta\"]/option"));
        this.busca_pregunta(preguntas, pregunta);
        cb_pregunta4.click();
    }

    void busca_pregunta(List<WebElement> preguntas, String pregunta)
    {
        for(int i = 0; i < preguntas.size(); i++){
            if (preguntas.get(i).getText().contains(pregunta)) {
                System.out.println("::" + preguntas.get(i).getText());
                preguntas.get(i).click();
                Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Selecciona pregunta", preguntas.get(i).getText(),"", false, "N");
                break;
            }
        }
    }

    public void responde_pregunta0(String respuesta)
    {
        respuesta0.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Responde pregunta 1", respuesta,"", false, "N");
    }

    public void responde_pregunta1(String respuesta)
    {
        respuesta1.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Responde pregunta 2", respuesta,"", true, "N");
        Util.AvanzarPagina();
    }

    public void responde_pregunta2(String respuesta)
    {
        respuesta2.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Responde pregunta 3", respuesta,"", false, "N");
    }

    public void responde_pregunta3(String respuesta)
    {
        respuesta3.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Responde pregunta 4", respuesta,"", false, "N");
        Util.AvanzarPagina();
    }

    public void responde_pregunta4(String respuesta)
    {
        respuesta4.sendKeys(respuesta);
        Reporte.agregarPaso("PREGUNTA SEGURIDAD", "Responde pregunta 5", respuesta,"", true, "N");
    }

    public void click_btn_actualizar()
    {
        String actual = btn_actualizar.getText();
        btn_actualizar.click();
        Util.assert_contiene("PREGUNTA SEGURIDAD", "Click en botón", actual,"Actualizar", false, "N");
    }

    public void selecciona_celular()
    {
        medios_celular.get(0).click();
    }
}
