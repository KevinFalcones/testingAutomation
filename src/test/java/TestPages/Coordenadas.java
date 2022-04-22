package TestPages;

import Globales.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class Coordenadas {
    public Coordenadas() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath = "//div[@class='title-2dof inc-sec-2dof']")
    WebElement etiq_dispositivo = null;

    @FindBy(xpath = "//div[@class='campoReadonly coord']")
    List<WebElement> etiq_coordenada = null;

    @FindBy(xpath = "//div[@class='info-2dof']")
    WebElement textoIngresaCoordenada = null;

    @FindBy(xpath = "//input[@id='desafio0']")
    WebElement txt_desafio0 = null;

    @FindBy(xpath = "//input[@id='desafio1']")
    WebElement txt_desafio1 = null;

    @FindBy(xpath = "//input[@id='desafio2']")
    WebElement txt_desafio2 = null;

    @FindBy(xpath = "//input[@id='desafio3']")
    WebElement txt_desafio3 = null;

    @FindBy(xpath = "//input[@id='desafio4']")
    WebElement txt_desafio4 = null;

    @FindBy(xpath = "//input[@id='desafio5']")
    WebElement txt_desafio5 = null;

    @FindBy(xpath = "//input[@id='desafio6']")
    WebElement txt_desafio6 = null;

    @FindBy(xpath = "//input[@id='desafio7']")
    WebElement txt_desafio7 = null;

    @FindBy(xpath = "//input[@id='desafio8']")
    WebElement txt_desafio8 = null;

    @FindBy(id = "btnContinuar")
    WebElement btn_confirmar = null;

    @FindBy(name = "_eventId_volver")
    WebElement btn_volver = null;

    @FindBy(name = "_eventId_cancelar")
    WebElement btn_cancelar = null;

    @FindBy(id = "checkBox")
    WebElement checkBox = null;

    @FindBy(xpath = "//label[@class='eb-check']")
    WebElement etiqueta_checkBox = null;

    @FindBy(xpath = "//div[@class='check-message']")
    WebElement leyenda_checkBox = null;

    //Variables de Mensajes de validación
    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_fondoInsuficiente = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    WebElement mensaje_cuentaBloqueada = null;

    @FindBy(id = "error-clave-incorrecta")
    WebElement mensaje_claveIncorrecta = null;

    @FindBy(id = "error-clave-incorrecta")
    List<WebElement> msj_claveIncorrecta = null;

    @FindBy(id = "exito")
    WebElement mensaje_claveCorrecta = null;

    @FindBy(xpath = "//li[@class='severity-FATAL']")
    //WebElement mensaje_YaMatriculado = null;
    List<WebElement> mensaje_YaMatriculado = null;

    @FindBy(xpath = "//div[@class='otp-medio']")
    List<WebElement> medio_envio = null;

    @FindBy(xpath = "//span[@class='medio-select']")
    WebElement medio_envio_select = null;

    @FindBy(id = "btnEliminar")
    WebElement btnEliminar = null;

    @FindBy(xpath = "//div[@class='info-2dof']")
    List<WebElement> etiqueta_ingreso = null;

    @FindBy(xpath = "//a[@class='btn-v2 btn-s btn-v2-submit blockUi']")
    WebElement btnDiferir = null;

    public Boolean disp_coord = false;
    public Boolean disp_otp = false;

    public void vp_etiqueta_dispositivo() {
        String actual = etiq_dispositivo.getText();
        String esperado = null;

        if (actual.contains("Clave24")) {
            disp_coord = true;
            esperado = "Clave24";
        }
        else if (actual.contains("Clave temporal")) {
            disp_otp = true;
            esperado = "Clave temporal";
        }
        Util.assert_contiene("PANTALLA DE CONFIRMACION", "Verificación de etiqueta de Dispositivo", actual, esperado, false, "N");
    }

    public void lee_coordenadas() {
        System.out.println(etiq_coordenada.get(0).getText());
        System.out.println(etiq_coordenada.get(1).getText());
        System.out.println(etiq_coordenada.get(2).getText());
    }

    public void coordenadasIncorrectas(){
        txt_desafio0.sendKeys("00");
        txt_desafio1.sendKeys("00");
        txt_desafio2.sendKeys("00");
    }

    public void escribe_desafio0() {
        ExcelFile archivo = new ExcelFile();
        try {
            String coordenada = archivo.obtenerValorCoordenada(System.getProperty("user.dir") + "/archivos/Coordenadas.xlsx", "Dato", etiq_coordenada.get(0).getText());
            txt_desafio0.sendKeys(coordenada);
            Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de coordenadas desafío 0", etiq_coordenada.get(0).getText() + ": " + coordenada, "", false, "N");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribe_desafio1() {
        ExcelFile archivo = new ExcelFile();
        try {
            String coordenada = archivo.obtenerValorCoordenada(System.getProperty("user.dir") + "/archivos/Coordenadas.xlsx", "Dato", etiq_coordenada.get(1).getText());
            txt_desafio1.sendKeys(coordenada);
            Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de coordenadas desafío 1", etiq_coordenada.get(1).getText() + ": " + coordenada, "", false, "N");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribe_desafio2() {
        ExcelFile archivo = new ExcelFile();
        try {
            String coordenada = archivo.obtenerValorCoordenada(System.getProperty("user.dir") + "/archivos/Coordenadas.xlsx", "Dato", etiq_coordenada.get(2).getText());
            txt_desafio2.sendKeys(coordenada);
            Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de coordenadas de desafío 2", etiq_coordenada.get(2).getText() + ": " + coordenada, "", false, "N");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ACCION DEL BOTON CONFIRMAR PARA MATRICULAR SUMINISTRO
    public void Click_BotonConfirmar() {
        String actual = btn_confirmar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Confirmar» y dar click.", actual, "Confirmar", false, "N");
        btn_confirmar.click();
    }

    //ACCION DEL BOTON CONFIRMAR PARA MATRICULAR SUMINISTRO
    public void Click_BotonDiferir() {
        Util.AvanzarPagina();
        String actual = btnDiferir.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Diferir» y dar click.", actual, "Diferir", false, "N");
        btnDiferir.click();
    }

    //ACCION DEL BOTON CAMBIAR PARA CAMBIAR EL AVATAR
    public void Click_BotonCambiar() {
        String actual = btn_confirmar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Cambiar» y dar click.", actual, "Cambiar", false, "N");
        btn_confirmar.click();
    }

    //ACCION DEL BOTON CONFIRMAR PARA MATRICULAR SUMINISTRO
    public void Click_BotonRealizarAvance() {
        String actual = btn_confirmar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Realizar avance» y dar click.", actual, "Realizar avance", false, "N");
        btn_confirmar.click();
    }

    //ACCION DEL BOTON CONFIRMAR PARA PAGAR SUMINISTRO
    public void Click_BotonPagar() {
        String actual = btn_confirmar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Servicios matriculados» y dar click.", actual, "Confirmar", false, "N");
        btn_confirmar.click();
    }

    //ACCION DEL BOTON CONFIRMAR PARA PAGAR SUMINISTRO
    public void Click_BotonPagarTC() {
        String actual = btn_confirmar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Pagar» y dar click.", actual, "Pagar", true, "N");
        btn_confirmar.click();
    }


    //PUNTOS DE VERIFICACION DE LA PANTALLA DE INGRESO DE COORDENADAS
    public void VP_TituloClave24() {
        Util.AvanzarPagina();
        etiq_dispositivo.click();
        String actual = etiq_dispositivo.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el título «Clave24 (Tarjeta de coordenadas)»", actual, "Clave24 (Tarjeta de coordenadas)", true, "N");
        Util.AvanzarPagina();
    }

    public void VP_TextoIngresaCoordenadas() {
        Util.AvanzarPagina();
        textoIngresaCoordenada.click();
        String actual = textoIngresaCoordenada.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el texto «Ingresa las coordenadas como lo indica tu tarjeta.»", actual, "Ingresa las coordenadas como lo indica tu tarjeta.", false, "N");
    }

    public void VP_MensajeCoordenadasCorrectas() {
        mensaje_claveCorrecta.click();
        String actual = mensaje_claveCorrecta.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Clave24 correcta.»", actual, "Clave24 correcta.", false, "N");
    }

    public void VP_MensajeCoordenadasIncorrectas() {
        mensaje_claveIncorrecta.click();
        String actual = mensaje_claveIncorrecta.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Clave24 incorrecta, inténtalo nuevamente»", actual, "Clave24 incorrecta, inténtalo nuevamente", false, "C");
    }

    public void VP_BotonVolver() {
        String actual = btn_volver.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Volver»", actual, "Volver", true, "N");
    }

    public void vp_botonCancelar() {
        String actual = btn_cancelar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que la etiqueta del botón se llame «Cancelar»", actual, "Cancelar", false, "N");
    }

    public void VP_MensajeFondoInsuficiente() {
        mensaje_fondoInsuficiente.click();
        String actual = mensaje_fondoInsuficiente.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «FONDOS INSUFICIENTES»", actual, "FONDOS INSUFICIENTES", true, "C");
    }

    public void VP_MensajeFondoInsuficienteSENAE() {
        mensaje_fondoInsuficiente.click();
        String actual = mensaje_fondoInsuficiente.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Error cuenta sin fondos»", actual, "Error cuenta sin fondos", true, "C");
    }

    public void VP_MensajeCuentaBloqueada() {
        mensaje_cuentaBloqueada.click();
        String actual = mensaje_cuentaBloqueada.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «CUENTA BLOQUEADA PARA MOVIMIENTOS»", actual, "CUENTA BLOQUEADA PARA MOVIMIENTOS", true, "C");
    }

    public void VP_MensajeCuentaBloqueadaSENAE() {
        mensaje_cuentaBloqueada.click();
        String actual = mensaje_cuentaBloqueada.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Cuenta bloqueada: CONTRA RETIRO»", actual, "Cuenta bloqueada: CONTRA RETIRO", true, "C");
    }

    public void VP_MensajeAliasYaMatriculado() {
        mensaje_YaMatriculado.get(0).click();
        String actual = mensaje_YaMatriculado.get(0).getText();
        Util.assert_contiene("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Alías ya se encuentra matriculado anteriormente.»", actual, "ya se encuentra matriculado anteriormente.", true, "C");
    }

    public void VP_MensajeCodigoYaMatriculado() {
        Util.RetrocederPagina();
        mensaje_YaMatriculado.get(0).click();
        String actual = mensaje_YaMatriculado.get(0).getText();
        Util.assert_contiene("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «IDENTIFICACIÓN YA SE ENCUENTRA MATRICULADO ANTERIORMENTE.»", actual, "IDENTIFICACIÓN YA SE ENCUENTRA MATRICULADO ANTERIORMENTE.", true, "C");
    }

    //INGRESO INCORRECTO DE COORDENADAS
    public void IngresarDesafio1() {
        txt_desafio0.click();
        txt_desafio0.sendKeys("55");
    }

    public void IngresarDesafio2() {
        txt_desafio1.click();
        txt_desafio1.sendKeys("55");
    }

    public void IngresarDesafio3() {
        txt_desafio2.click();
        txt_desafio2.sendKeys("55");
    }

    //Hacer click en el casillero Estoy de acuerdo - servicio SENAE
    public void VP_LeyendaCheck_Box() {
        leyenda_checkBox.click();
        String actual = leyenda_checkBox.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre la leyenda correcta", actual, "Esta transacción corresponde a un pago por una liquidación a terceros desde mi cuenta.", true, "C");
        Util.AvanzarPagina();
    }

    public void VP_Check_Box() {
        String actual = etiqueta_checkBox.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Verificar que se muestre el mensaje «Estoy de acuerdo»", actual, "Estoy de acuerdo", true, "C");
        Util.AvanzarPagina();
    }

    public void Check_Box() {
        checkBox.click();
    }

    public void click_btn_eliminar() {
        String actual = btnEliminar.getText();
        Util.assert_igual("PANTALLA DE CONFIRMACION", "Da click en botón", actual, "Si, eliminar", false, "N");
        btnEliminar.click();
    }

    public String selecciona_medio()
    {
        String medio = medio_envio.get(0).getText();
        medio_envio.get(0).click();
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Selecciona medio de envío", medio, "", true, "N");
        return medio;
    }

    public void vp_etiqueta_ingreso() {
        String actual = etiqueta_ingreso.get(1).getText();
        Util.assert_contiene("PANTALLA DE CONFIRMACION", "Verificar etiqueta", actual, "Introduce el c", false, "N");
    }

    public void escribe_desafio1_otp(String digito) {
        txt_desafio1.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 1", digito, "", false, "N");
    }

    public void escribe_desafio2_otp(String digito) {
        txt_desafio2.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 2", digito, "", false, "N");
    }

    public void escribe_desafio3_otp(String digito) {
        txt_desafio3.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 3", digito, "", false, "N");
    }

    public void escribe_desafio4_otp(String digito) {
        txt_desafio4.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 4", digito, "", false, "N");
    }

    public void escribe_desafio5_otp(String digito) {
        txt_desafio5.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 5", digito, "", false, "N");
    }

    public void escribe_desafio6_otp(String digito) {
        txt_desafio6.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 6", digito, "", false, "N");
    }

    public void escribe_desafio7_otp(String digito) {
        txt_desafio7.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 7", digito, "", false, "N");
    }

    public void escribe_desafio8_otp(String digito) {
        txt_desafio8.sendKeys(digito);
        Reporte.agregarPaso("PANTALLA DE CONFIRMACION", "Ingreso de otp desafío 8", digito, "", false, "N");
    }

    public Boolean existe_msj_claveIncorrecta() {
        String actual = null;
        Boolean existe = false;
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        Util.AvanzarPagina(etiq_dispositivo);
        for(int i = 0; i < msj_claveIncorrecta.size(); i++) {
            System.out.println(i+": "+msj_claveIncorrecta.get(i).getText());
        }

        try {
            actual = mensaje_claveIncorrecta.getText();
            System.out.println("msj inco: " + actual);
            if (actual.contains("Clave temporal incorrecta"))
                existe = true;
        }catch (Exception e){
            existe = false;
        }
       return existe;
    }

    public void autenticacion(Boolean selecciona_medios)
    {
        //Ingreso de Coordenadas
        if (this.disp_coord) {
            this.escribe_desafio0();
            this.escribe_desafio1();
            this.escribe_desafio2();
        }

        //Ingreso de Otp
        if (this.disp_otp) {
            if (selecciona_medios) {
                String medio_envio = this.selecciona_medio();
                System.out.println(medio_envio);
                this.vp_etiqueta_ingreso();
            }

            String ventanaAnterior = Util.obtieneVentanaActual();
            Util.Inicio_Latinia("");
            Latinia latinia = new Latinia();
            latinia.login("2");
            String otp = latinia.getOTP(medio_envio_select.getText().replace("*",""));

            Util.regresaDeOTP(ventanaAnterior);
            System.out.print(otp + "::"+otp.length());

            Util.AvanzarPagina(txt_desafio1);
            this.escribe_desafio1_otp(String.valueOf(otp.charAt(0)));
            this.escribe_desafio2_otp(String.valueOf(otp.charAt(1)));
            this.escribe_desafio3_otp(String.valueOf(otp.charAt(2)));
            this.escribe_desafio4_otp(String.valueOf(otp.charAt(3)));
            this.escribe_desafio5_otp(String.valueOf(otp.charAt(4)));
            this.escribe_desafio6_otp(String.valueOf(otp.charAt(5)));
            //this.escribe_desafio7_otp(String.valueOf(otp.charAt(6)));
            //this.escribe_desafio8_otp(String.valueOf(otp.charAt(7)));
            Util.AvanzarPagina(txt_desafio1);
        }
    }
}


