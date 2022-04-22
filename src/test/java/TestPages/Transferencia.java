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

public class Transferencia {
    public Transferencia() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'Terceros')]")
    WebElement tituloTerceros = null;

    @FindBy(id="cuentaOrigenSeleccionada")
    WebElement cboCtaOrigen = null;

    @FindBy(xpath="//div[@class='select-cuenta-desc']")
    List<WebElement> ctasOrigen = null;

    @FindBy(xpath="//input[@class='buscar-cuenta']")
    WebElement txtBusqueda = null;

    @FindBy(xpath="//div[@class='select-agenda']")
    List<WebElement> ctasDestino = null;

    @FindBy(id="select2-datosDestinoTransferencia0cuentaDestinoTerceros-container")
    WebElement cboCtaDestino = null;

    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement cboCtaDestinoSearch = null;

    @FindBy(id="importe-0")
    WebElement monto0 = null;

    //@FindBy(id="tipoTransaccionSeleccionada")  _old
    @FindBy(id="tipoTransaccionSeleccionada0")
    WebElement cboViaTransf = null;

    @FindBy(id="concepto-0")
    WebElement concepto0 = null;

    @FindBy(id="btnContinuar")
    WebElement btnContinuar_old = null;

    @FindBy(id="btnContinuarOperacion2")
    WebElement btnContinuar = null;

    @FindBy(id="btnContinuar")
    WebElement btnTransferir = null;

    @FindBy(id="checkbox_0")
    WebElement chkEmail = null;

    @FindBy(id="datosDestinoTransferencia0.cuentaDestinoTerceros.cliente.email")
    WebElement email = null;

    @FindBy(id="datosDestinoTransferencia0.mensajeEmail")
    WebElement mensajeEmail = null;

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

    public void vp_tituloTCterceros(){
        String actual = tituloTerceros.getText();
        Util.assert_contiene("TRANSFERENCIA TERCEROS", "Se verifica el titulo", actual,"Terceros", false, "N");
    }

    public void selecciona_CuentaOrigen_old(String numcuenta)
    {
        cboCtaOrigen.click();
        Select lst_cuentas = new Select(cboCtaOrigen);
        List<WebElement> cuentas = lst_cuentas.getOptions();
        for (WebElement cuenta : cuentas) {
            System.out.println(cuenta.getText());
            if (cuenta.getText().contains(numcuenta))
            {
                cuenta.click();
                break;
            }
        }
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Seleccionar cuenta de origen", numcuenta, "", false, "N");
    }

    public void selecciona_CuentaOrigen(String numcuenta){
        String actual = "";
        for(int i = 0; i < ctasOrigen.size(); i++)
        {
            actual = ctasOrigen.get(i).getText();
            System.out.println("cta orig"+i+": "+actual);
            if (actual.contains(numcuenta)) {
                //WebElement p = tarjetas.get(i).findElement(By.xpath("./.././.././.././.."));//cada padre ->  ./..
                ctasOrigen.get(i).click();
                break;
            }
        }
        Reporte.agregarPaso("PAGO TC PROPIAS", "Selecciona tarjeta", numcuenta, "", true, "N");
    }

    public void selecciona_CuentaDestino_old(String numcuenta){
        cboCtaDestino.click();
        cboCtaDestinoSearch.sendKeys(numcuenta);//Terceros
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Selecciona cuenta destino", numcuenta, "", true, "N");
        cboCtaDestinoSearch.sendKeys(Keys.ENTER);
    }

    public void selecciona_CuentaDestino(String numcuenta){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        txtBusqueda.click();
        txtBusqueda.sendKeys(numcuenta);//Terceros
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Selecciona cuenta destino", numcuenta, "", true, "N");
        txtBusqueda.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for(int i = 0; i < ctasDestino.size(); i++)
        {
            String actual = ctasDestino.get(i).getText();
            System.out.println("cta dest" + i + ": " + actual);
            if (actual.contains(numcuenta)) {
                //WebElement p = tarjetas.get(i).findElement(By.xpath("./.././.././.././.."));//cada padre ->  ./..
                ctasDestino.get(i).click();
                break;
            }
        }
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
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Ingresa monto", monto_pagar, "", false, "N");
    }

    public void selecciona_via(String nombvia)
    {
        cboViaTransf.click();
        Select lst_vias = new Select(cboViaTransf);
        List<WebElement> vias = lst_vias.getOptions();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        for (WebElement via : vias) {
            System.out.println(via.getText());
            if (via.getText().equals(nombvia))
            {
                via.click();
                break;
            }
        }
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Selecciona via de la transferencia", nombvia, "", false, "N");
    }

    public void ingresa_concepto0(String concepto){
        Util.AvanzarPagina(monto0);
        concepto0.sendKeys(concepto);
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Ingresa concepto", concepto, "", true, "N");
    }

    public void click_boton_Continuar_old() {
        Util.AvanzarPagina();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        Util.AvanzarPagina();
        String actual = btnContinuar_old.getText();
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Click en botón", actual, "", false, "N");
        btnContinuar_old.click();
    }

    public void click_boton_Continuar() {
        Util.AvanzarPagina(monto0);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        Util.AvanzarPagina(monto0);
        String actual = btnContinuar.getText();
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Click en botón", actual, "", false, "N");
        btnContinuar.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public void click_boton_Transferir() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String actual = btnTransferir.getText();
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Click en botón", actual, "", false, "N");
        btnTransferir.click();

        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }*/
    }

    public void click_check_email() {
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Click en check", "Enviar e-mail de destinatario", "", false, "N");
        chkEmail.click();
    }

    public void ingresa_email(String emaildest){
        email.clear();
        email.sendKeys(emaildest);
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Ingresa email destinatario", emaildest, "", false, "N");
    }

    public void ingresa_mensajeEmail(String emailmensaje){
        mensajeEmail.sendKeys(emailmensaje);
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS", "Ingresa mensaje de email destinatario", emailmensaje, "", true, "N");
        Util.AvanzarPagina();
    }

    public void click_boton_Programar() {
        String actual = btnProgramar.getText();
        Util.assert_contiene("TRANSFERENCIA TERCEROS - PROGRAMACION", "Click en botón", actual, "Programar", false, "N");
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
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS - PROGRAMACION", "Seleccionar frecuencia", frecuencia, "", false, "N");
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
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS - PROGRAMACION", "Ingresa Fecha Hasta", String.valueOf(fechafin), "", false, "N");
    }

    public void ingresa_periodos(String cantidad)
    {
        rbCantPeriodos.click();
        txtCantEjecuciones.clear();
        txtCantEjecuciones.sendKeys(cantidad);
        Reporte.agregarPaso("TRANSFERENCIA TERCEROS - PROGRAMACION", "Ingresa Periodos", cantidad, "", false, "N");
    }


}
