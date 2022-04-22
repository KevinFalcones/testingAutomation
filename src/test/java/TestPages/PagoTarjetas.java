package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDateTime;
import java.util.List;

public class PagoTarjetas {
    public PagoTarjetas() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="select2-tarjetaCreditoSeleccionada-container")
    WebElement comboTC = null;

    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement comboSearchTC = null;

    @FindBy(id="cuentaOrigenPagoSeleccionada")
    WebElement cboCuenta = null;

    @FindBy(id="tipoTransaccionSeleccionada")
    WebElement cboVia = null;

    @FindBy(id="checkbox_0")
    WebElement chkEmail = null;

    @FindBy(id="datosDestinoPagoTarjetaTerceros0.email")
    WebElement email = null;

    @FindBy(id="datosDestinoPagoTarjetaTerceros0.mensajeEmail")
    WebElement mensajeEmail = null;

    @FindBy(xpath="//option[@value='TipoTransaccionCuentas,SPI']")
    WebElement spi = null;

    @FindBy(xpath="//li[@class='row detalle' and contains(., 'BANCO')]")
    WebElement lblBcoBolivariano = null;

    @FindBy(id="importe-0")
    WebElement monto0 = null;

    @FindBy(id="concepto-0")
    WebElement concepto = null;

    @FindBy(xpath="//div[@id='productT2']")
    WebElement tcPropia = null;

    @FindBy(xpath="//div[@id='productC0']")
    WebElement cuentaAdebitar = null;

    @FindBy(id="montoPagoTarjeta")
    WebElement montoTCpropia = null;

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'Tarjetas')]")
    WebElement tituloTCpropia = null;

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'Terceros')]")
    WebElement tituloTCterceros = null;

    @FindBy(xpath="//div[@id='title-1']")
    WebElement subtitulo1 = null;

    @FindBy(xpath="//div[@id='title-2']")
    WebElement subtitulo2 = null;

    @FindBy(xpath="//div[@id='title-3']")
    WebElement subtitulo3 = null;

    @FindBy(xpath="//button[@class='btn-eb btn-eb-3']")
    WebElement btnEditar = null;

    @FindBy(xpath="//div[@class='number-card']")
    List<WebElement> tarjetas = null;

    @FindBy(xpath="//div[@class='content-hover']")
    List<WebElement> tarjetas_datos = null;

    @FindBy(xpath="//label[@id='name-1']")
    WebElement tarjeta_seleccionada = null;

    @FindBy(xpath="//div[@class='label-prod-numero']")
    List<WebElement> cuentas = null;

    @FindBy(id="montoPagoTarjeta")
    WebElement monto = null;

    @FindBy(id="referenciaPagoTarjeta")
    WebElement referenciaPagoTarjeta = null;

    @FindBy(id="btnContinuarOperacion2")
    WebElement btnContinuarOperacion2 = null;

    @FindBy(id="btnContinuar")
    WebElement btnContinuar = null;

    @FindBy(id="botonSwitch")
    WebElement btnProgramar = null;

    @FindBy(id="fechaDesde")
    WebElement txtFechaDesde = null;

    @FindBy(id="fechaHasta")
    WebElement txtFechaHasta = null;

    @FindBy(name="programador.periodo")
    WebElement cboFrecuencia = null;

    @FindBy(id="radioFechaFin")
    WebElement rbFechaFin = null;

    @FindBy(id="radioCantidadPeriodos")
    WebElement rbCantPeriodos = null;

    @FindBy(id="cantidadEjecuciones")
    WebElement txtCantEjecuciones = null;

    public void selecciona_TC(String numtc){
        comboTC.click();
        comboSearchTC.sendKeys(numtc);//Terceros
        Reporte.agregarPaso("PAGO TC TERCEROS", "Selecciona Tarjeta", numtc, "", true, "N");
        comboSearchTC.sendKeys(Keys.ENTER);
    }

    public void selecciona_tarjeta(String numtc){
        String actual = "";
        for(int i=0; i<tarjetas.size();i++)
        {
            actual = tarjetas.get(i).getText();
            System.out.println("tc "+i+": "+actual);
            if (actual.equals(numtc)) {
                //WebElement p = tarjetas.get(i).findElement(By.xpath("./.././.././.././.."));//cada padre ->  ./..
                tarjetas_datos.get(i).click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC PROPIAS", "Selecciona tarjeta", numtc, "", true, "N");
    }

    public void vp_btnEditar(){
        String actual = btnEditar.getText();
        Util.assert_contiene("PAGO TC PROPIAS", "Verificación de botón", actual,"Editar", false, "N");
    }

    public void vp_tarjeta_seleccionada(String numtc){
        try {
            Thread.sleep(2500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = tarjeta_seleccionada.getText();
        Util.assert_contiene("PAGO TC PROPIAS", "Tarjeta seleccionada", actual, numtc, false, "N");
    }

    public void selecciona_ctaDebito(String numcta){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = "";
        for(int i=0; i<cuentas.size();i++)
        {
            actual = cuentas.get(i).getText();
            System.out.println("cta "+i+": "+actual);
            if (actual.equals(numcta)) {
                cuentas.get(i).click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC PROPIAS", "Selecciona cuenta de debito", numcta, "", false, "N");
    }

    public void ingresa_monto(String monto_pagar)
    {
        Util.AvanzarPagina();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        monto.sendKeys(monto_pagar);
        Reporte.agregarPaso("PAGO TC PROPIAS", "Ingresa monto", monto_pagar, "", false, "N");
    }

    public void ingresa_monto0(String monto_pagar)
    {
        Util.AvanzarPagina();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        monto0.sendKeys(monto_pagar);
        Reporte.agregarPaso("PAGO TC TERCEROS", "Ingresa monto", monto_pagar, "", false, "N");
    }

    public void selecciona_via(String nombvia)
    {
        cboVia.click();
        Select lst_vias = new Select(cboVia);
        List<WebElement> vias = lst_vias.getOptions();
        for (WebElement via : vias) {
            System.out.println(via.getText());
            if (via.getText().equals(nombvia))
            {
                via.click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC TERCEROS", "Selecciona via de la transferencia", nombvia, "", false, "N");
    }

    public void click_check_email() {
        Reporte.agregarPaso("PAGO TC TERCEROS", "Click en check", "Enviar e-mail de destinatario", "", false, "N");
        chkEmail.click();
    }

    public void ingresa_email(String emaildest){
        email.clear();
        email.sendKeys(emaildest);
        Reporte.agregarPaso("PAGO TC TERCEROS", "Ingresa email destinatario", emaildest, "", false, "N");
    }

    public void ingresa_mensajeEmail(String emailmensaje){
        mensajeEmail.sendKeys(emailmensaje);
        Reporte.agregarPaso("PAGO TC TERCEROS", "Ingresa mensaje de email destinatario", emailmensaje, "", true, "N");
        Util.AvanzarPagina();
    }

    public void ingresa_concepto(String concepto)
    {
        referenciaPagoTarjeta.sendKeys(concepto);
        Reporte.agregarPaso("PAGO TC PROPIAS", "Ingresa concepto", concepto, "", true, "N");
    }

    public void ingresa_concepto0(String concepto0){
        concepto.sendKeys(concepto0);
        Reporte.agregarPaso("PAGO TC TERCEROS", "Ingresa concepto", concepto0, "", true, "N");
    }

    public void click_boton_Continuar() {
        Util.AvanzarPagina();
        String actual = btnContinuarOperacion2.getText();
        Util.assert_contiene("PAGO TC PROPIAS", "Tarjeta seleccionada", actual, "Continuar", false, "N");
        btnContinuarOperacion2.click();
    }

    public void click_boton_Pagar() {
        Util.AvanzarPagina();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        Util.AvanzarPagina();
        String actual = btnContinuar.getText();
        Reporte.agregarPaso("PAGO TC", "Click en botón", actual, "", true, "N");
        btnContinuar.click();
    }

    public void seleccionar_CuentaOrigen(String numcuenta)
    {
        Util.AvanzarPagina();
        cboCuenta.click();
        Select lst_cuentas = new Select(cboCuenta);
        List<WebElement> cuentas = lst_cuentas.getOptions();
        for (WebElement cuenta : cuentas) {
            System.out.println(cuenta.getText());
            if (cuenta.getText().contains(numcuenta))
            {
                cuenta.click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC TERCEROS", "Seleccionar cuenta de origen", numcuenta, "", true, "N");
    }

    public void verificaTCdestino(){
        if (!(lblBcoBolivariano.getText().contains("BOLIVARIANO"))){
            System.out.println("no es del boli");
            cboVia.click();
            spi.click();
        }
    }

    public void ventanaConfeccion()  {
        vp_subtitulo1TCPropia();
        tcPropia.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vp_subtitulo2TCPropia();
        cuentaAdebitar.click();
        Util.AvanzarPagina();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        montoTCpropia.sendKeys("1");
        referenciaPagoTarjeta.sendKeys("Auto test");
        Util.AvanzarPagina();
        btnContinuarOperacion2.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Util.AvanzarPagina();
        vp_subtitulo3TCPropia();
    }

    public void vp_tituloTCPropia(){
        String actual2 = tituloTCpropia.getText();
        Util.assert_contiene("Pago de TC propias", "Se verifica el titulo de la ventana.", actual2,"Tarjetas propias", true, "N");
    }

    public void vp_subtitulo1TCPropia(){
        String actual2 = subtitulo1.getText();
        Util.assert_contiene("Pago de TC propias - ventana de confeccion, paso #1", "Se verifica el subtitulo 1 de la ventana de confeccion.", actual2,"Tarjeta", true, "N");
    }

    public void vp_subtitulo2TCPropia(){
        String actual2 = subtitulo2.getText();
        Util.assert_contiene("Pago de TC propias - ventana de confeccion, paso #2", "Se verifica el subtitulo 2 de la ventana de confeccion.", actual2,"Cuenta a debitar y Monto", true, "N");
    }

    public void vp_subtitulo3TCPropia(){
        String actual2 = subtitulo3.getText();
        Util.assert_contiene("Pago de TC propias - ventana de confeccion, paso #3", "Se verifica el subtitulo 3 de la ventana de confeccion.", actual2,"Confirmación", true, "N");
    }


    public void vp_tituloTCterceros(){
        String actual2 = tituloTCterceros.getText();
        Util.assert_contiene("PAGO TC TERCEROS", "Se verifica el titulo de la ventana.", actual2,"Terceros", true, "N");
    }

    public void vp_TCbb(){
        String actual2 = lblBcoBolivariano.getText();
        Util.assert_contiene("PAGO TC TERCEROS", "Se verifica la TC pertenece al Banco Bolivariano.", actual2,"BANCO BOLIVARIANO", false, "N");
    }

    public void vp_btnContinuar(){
        String actual2 = btnContinuar.getText();
        Util.assert_contiene("PAGO TC TERCEROS", "Se verifica el botón 'Continuar'.", actual2,"Continuar", true, "N");
    }

    public void click_boton_Programar() {
        String actual = btnProgramar.getText();
        Util.assert_contiene("PAGO TC TERCEROS - PROGRAMACION", "Click en botón", actual, "Programar", false, "N");
        btnProgramar.click();
        Util.AvanzarPagina();
    }

    public void selecciona_frecuencia(String frecuencia){
        //Util.AvanzarPagina();
        cboFrecuencia.click();
        Select lst_frec = new Select(cboFrecuencia);
        List<WebElement> frecuencias = lst_frec.getOptions();
        for (WebElement freq : frecuencias) {
            System.out.println(freq.getText());
            if (freq.getText().contains(frecuencia))
            {
                freq.click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC TERCEROS - PROGRAMACION", "Selecciona frecuencia", frecuencia, "", false, "N");
    }

    public void ingresa_fechaFin(String numdias) //dd/mm/aaaa
    {
        LocalDateTime today = LocalDateTime.now();//Today
        LocalDateTime fecha = today.plusDays(Long.parseLong(numdias));//Plus numdias day
        String fechafin = String.valueOf(fecha.getDayOfMonth()) + "/" + String.valueOf(fecha.getMonthValue()) + "/" + String.valueOf(fecha.getYear());
        System.out.println(fechafin);
        rbFechaFin.click();
        txtFechaHasta.clear();
        txtFechaHasta.sendKeys(fechafin);
        Reporte.agregarPaso("PAGO TC TERCEROS - PROGRAMACION", "Ingresa Fecha Hasta", String.valueOf(fechafin), "", false, "N");
    }

    public void ingresa_periodos(String cantidad)
    {
        rbCantPeriodos.click();
        txtCantEjecuciones.clear();
        txtCantEjecuciones.sendKeys(cantidad);
        Reporte.agregarPaso("PAGO TC TERCEROS - PROGRAMACION", "Ingresa Periodos", cantidad, "", false, "N");
    }

}

