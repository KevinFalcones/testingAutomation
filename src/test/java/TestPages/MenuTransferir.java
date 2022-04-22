package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.LocalDateTime;
import java.util.List;


public class MenuTransferir {
    public MenuTransferir() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TRANSFERENCIAS")
    WebElement menutransferir = null;

    @FindBy(id="EB_TRANSFERENCIA_CUENTAS_PROPIAS")
    WebElement smenuCtasPropias = null;

    @FindBy(id="EB_TRANSFERENCIAS_A_TERCEROS")
    WebElement smenuCtasTerceros = null;

    @FindBy(id = "EB_CONSULTA_AGENDAS_DE_CUENTAS")
    WebElement smenuAgendaCtaTerceros = null;

    @FindBy(id = "EB_AGENDA_CUENTAS_TERCEROS")
    WebElement smenuAgendaCtaTerceros_old = null;

    @FindBy(xpath="//div[@class='grid-item-cuentaDestino_0 grid-item-cuentaDestino_0-2 item item-2']")
    WebElement ctaDestino = null;

    @FindBy(id="input-material-monto_editando_0")
    WebElement monto = null;

    @FindBy(id="conceptoTransferenciaEditando-0")
    WebElement concepto = null;

    @FindBy(id="importe-0")
    WebElement monto2 = null;

    //Elementos de transferencia a cuentas de terceros
    @FindBy(id="concepto-0")
    WebElement concepto2 = null;

    @FindBy(id="cuentaOrigenSeleccionada")
    WebElement comboCtaOrigen = null;

    @FindBy(id="select2-datosDestinoTransferencia0cuentaDestinoTerceros-container")
    WebElement comboCtaDestino = null;

    @FindBy(xpath="//input[@class='select2-search__field']")
    WebElement comboSearchCtaDestino = null;

    @FindBy(id="tipoTransaccionSeleccionada")
    WebElement comboViaTranf = null;

    @FindBy(xpath="//option[@value='TipoTransaccionCuentas,SPI']")
    WebElement spi = null;

    @FindBy(xpath="//option[@value='TipoTransaccionCuentas,PAGO_DIRECTO']")
    WebElement banred = null;

    @FindBy(id="btnContinuarOperacion2")
    WebElement btnContinuarPropias = null;

    @FindBy(id="btnContinuar")
    WebElement btnContinuarTerceros = null;

    @FindBy(id="btnTransferir")
    WebElement btnTransferir = null;

    @FindBy(xpath="//li[@class='row detalle' and contains(., 'BANCO')]")
    WebElement lblBcoBolivariano = null;

    @FindBy(id="validacion-importe-0")
    WebElement validacionMonto = null;

    @FindBy(xpath="//div[@class='destino-tipo-value']")
    List<WebElement> ctasDestino = null;

    //switch para programacion de transferencias
    @FindBy(xpath="//span[@class='slider round']")
    WebElement switchProgramacion = null;

    @FindBy(xpath="//input[@id='fechaHasta']")
    WebElement datePickerHasta = null;

    //Click en submenu Terceros
    public void click_smenu_matTerceros() {
        menutransferir.click();
        String actual = smenuAgendaCtaTerceros.getText();
        Util.assert_contiene("MENU TRANSFERIR", "Verificacion de etiqueta de menu", actual,"terceros", true, "N");
        smenuAgendaCtaTerceros.click();
    }

    public void click_smenu_matTerceros_old() {
        menutransferir.click();
        String actual = smenuAgendaCtaTerceros_old.getText();
        Util.assert_contiene("MENU TRANSFERIR", "Verificacion de etiqueta de menu", actual,"terceros", true, "N");
        smenuAgendaCtaTerceros_old.click();
    }

    //Click en submenu Propias
    public void click_smenu_ctasPropias(){
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Click en submenu Propias", "Propias", "", true, "N");
        menutransferir.click();
        smenuCtasPropias.click();
    }

    //Click en submenu Terceros/Otros bancos
    public void click_smenu_ctasTerceros(){
        menutransferir.click();
        String actual = smenuCtasTerceros.getText();
        Util.assert_contiene("MENU TRANSFERIR", "Verificacion de etiqueta de menu", actual,"Terceros", true, "N");
        smenuCtasTerceros.click();
    }

    //Confeccion para transferencia entre cuentas porpias exitosa
    public void confeccionTrasnfPropias(String s1, String s2, String s3, String s4){
        //PASO #1
        WebElement ctaOrigen = Util.driver.findElement(By.xpath("//div[@class='tipo-value' and contains(., '"+ s1 + "')]"));
        vp_tituloCtaPropia();
        ctaOrigen.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Click en la cuenta de origen", ctaOrigen.getText(), "", true, "N");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //PASO #2
        WebElement ctaDestino = Util.driver.findElement(By.xpath("//div[@class='destino-tipo-value' and contains(., '"+ s2 + "')]"));
        ctaDestino.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Ingresa la cuenta destino.", ctaDestino.getText(), "", true, "N");
        ingresoMonto(s3);
        ingresoConcepto(s4);
        vp_tituloCtaDestino();
        Util.AvanzarPagina();

    }

    public void transPropiasMultiples(int s5, String s6, String s7, String s8){

        WebElement btnAgregarNuevaTransferencia = Util.driver.findElement(By.xpath("//a[@id='btnAgregarNuevaTransferencia']"));
        btnAgregarNuevaTransferencia.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - multiples", "Cliquea en nueva transferencia.", "+ Agregar Transferencia", "", true, "N");
        List<WebElement> listctas = Util.driver.findElements(By.xpath("//div[@class='destino-tipo-value' and contains(., '"+ s6 + "')]"));
        listctas.get(s5).click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - multiples", "Ingresa la cuenta destino.", ctaDestino.getText(), "", true, "N");
        WebElement montomulti = Util.driver.findElement(By.id("input-material-monto_editando_" + s5));
        montomulti.sendKeys(s7);
        Reporte.agregarPaso("Transferencias entre cuentas propias - multiples", "Ingresa el monto de la trx.", s7, "", true, "N");
        WebElement conceptomulti = Util.driver.findElement(By.id("conceptoTransferenciaEditando-" + s5));
        conceptomulti.sendKeys(s8);
        Reporte.agregarPaso("Transferencias entre cuentas propias - multiples", "Ingresa el concepto de la trx.", s8, "", true, "N");

        Util.AvanzarPagina();

    }

    public void confirmacionTranf(){
        btnContinuarPropias.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Click en 'Continuar' para seguir con el paso #3 de la transferencia.", "Botón 'Continuar'", "", true, "N");

        Util.AvanzarPagina();
        Util.AvanzarPagina();
        Util.AvanzarPagina();
        Util.AvanzarPagina();
        vp_btnTransferir();
        btnTransferir.click();
    }

    public void ingresoMonto(String s){
        monto.sendKeys(s);
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Ingresa el monto de la trx.", s, "", true, "N");

    }
    public void ingresoConcepto(String s){
        concepto.sendKeys(s);
        concepto.sendKeys(Keys.TAB);
        Reporte.agregarPaso("Transferencias entre cuentas propias", "Ingresa el concepto de la trx.", s, "", true, "N");
    }

    //Confeccion para transferencia a terceros exitosa



    public void confeccionTransfTerceros(String s1, String s2, String s3, String s4, String s5){
        //Se selecciona la cuenta de origen
        WebElement origen = Util.driver.findElement(By.xpath("//option[contains(., '" + s1 +"')]"));
        origen.click();
        //Se selecciona la cuenta destino
        comboCtaDestino.click();
        comboSearchCtaDestino.sendKeys(s2);
        comboSearchCtaDestino.sendKeys(Keys.ENTER);
        //Se ingresa el monto de la trx
        Util.AvanzarPagina();
        monto2.sendKeys(s3);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Se ingresa la via del pago si es que esta disponible (cuentas BB no tienen esta opcion)
        verificaCuentaDestino(s4);
        //Se ingresa el concepto de la trx.
        concepto2.sendKeys(s5);


    }

    public void btnContinuar(){
        Util.AvanzarPagina();
        vp_btnContinuar();
        btnContinuarTerceros.click();

    }

    public void transTercerosMultiples(int s5, String s6, String s7, String s8, String s9) {
        WebElement btnAgregarNuevaTransferencia = Util.driver.findElement(By.xpath("//button[@id='agregarNuevaTransferencia']"));
        btnAgregarNuevaTransferencia.click();
        Reporte.agregarPaso("Transferencias a terceros - multiples", "Cliquea en nueva transferencia.", "+ Agregar Transferencia", "", true, "N");
        Util.AvanzarPagina();

        List<WebElement> listctas = Util.driver.findElements(By.xpath("//span[@class='select2-selection__rendered']"));
        listctas.get(s5).click();
        comboSearchCtaDestino.sendKeys(s6);
        comboSearchCtaDestino.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("Transferencias a terceros - multiples", "Ingresa la cuenta destino.", s6, "", true, "N");
        Util.AvanzarPagina();

        WebElement montomulti = Util.driver.findElement(By.id("importe-" + s5));
        montomulti.sendKeys(s7);
        Reporte.agregarPaso("Transferencias a terceros - multiples", "Ingresa el monto de la trx.", s7, "", true, "N");
        Util.AvanzarPagina();

        //List<WebElement> listViaDePago = Util.driver.findElements(By.xpath("//select[@id='tipoTransaccionSeleccionada']"));
        //listViaDePago.get(s5).click();
        //List<WebElement> listBancos = Util.driver.findElements(By.xpath("li[@class='row detalle' and contains(., 'BANCO')]"));

        List<WebElement> listspi = Util.driver.findElements(By.xpath("//option[@value='TipoTransaccionCuentas,SPI']"));
        List<WebElement> listbr = Util.driver.findElements(By.xpath("//option[@value='TipoTransaccionCuentas,PAGO_DIRECTO']"));
        if (!(s8.equals("-"))){
            verificaCtasDestinoMulti(listspi, listbr, s5,s8);
        }

        WebElement conceptomulti = Util.driver.findElement(By.id("concepto-" + s5));
        conceptomulti.sendKeys(s9);
        Reporte.agregarPaso("Transferencias a terceros - multiples", "Ingresa el concepto de la trx.", s9, "", true, "N");
        Util.AvanzarPagina();
    }

    ///Los métodos siguientes son llamados para los casos de prueba de coordenada incorrectas y monto>saldo.
    //Click en cuenta de origen para transferencias a otros bancos
    public void click_ctaOrigen2(String s1){
        ////option[contains(., '000XXXX237')]
        comboCtaOrigen.click();
        comboCtaOrigen.sendKeys(s1);
        comboCtaOrigen.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("Transferencias a terceros - coor. incorrectas", "Ingresa la cuenta de origen.", s1, "", true, "N");

    }

    //Click en cuenta destino para transferencias a otros bancos
    public void click_ctaDestino2(String s1){
        comboCtaDestino.click();
        comboSearchCtaDestino.sendKeys(s1);//otros bancos
        comboSearchCtaDestino.sendKeys(Keys.ENTER);
        Reporte.agregarPaso("Transferencias a terceros - coor. incorrectas", "Ingresa la cuenta destino.", s1, "", true, "N");


    }
    //verifica el banco destino y si se habilita, cliquea la via de pago pasaeda por parametro
    public void verificaCuentaDestino(String s1){

        if (!(lblBcoBolivariano.getText().contains("BOLIVARIANO"))){
            comboViaTranf.click();
            if(s1.equals("spi")){
                spi.click();//Otros bancos

            }
            if(s1.equals("br")){
                banred.click();//Otros bancos pago inmediato

            }

        }
        vp_bcoDestino();
    }
    //verifica el banco destino y si se habilita, cliquea la via de pago pasaeda por parametro en tranfer4encias multiples
    public void verificaCtasDestinoMulti(List<WebElement> list1,List<WebElement> list2, int s5, String s8){

            if(s8.equals("spi")){
                list1.get(s5);
                list1.get(s5).click();
                Reporte.agregarPaso("Transferencias a terceros multiples", "Ingresa como via de pago 'Otros Bancos'.", "Otros Bancos", "", true, "N");

                //spi.click();//Otros bancos
            }
            if(s8.equals("br")){
                list2.get(s5);
                list2.get(s5).click();
                Reporte.agregarPaso("Transferencias a terceros multiples", "Ingresa como via de pago 'Otros Bancos Pago Inmediato'.", "Otros Bancos Pago Inmediato", "", true, "N");

                //banred.click();//Otros bancos pago inmediato
            }


        //vp_bcoDestino();


    }

    public void click_monto2(String s1){
        Util.AvanzarPagina();
        monto2.sendKeys(s1);
        Reporte.agregarPaso("Transferencias a terceros", "Ingresa el monto de la trx.", s1, "", true, "N");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void click_concepto2(String s1){

        concepto2.sendKeys(s1);
        Reporte.agregarPaso("Transferencias a terceros", "Ingresa el concepto de la trx.", s1, "", true, "N");

    }

    public void click_btnContinuar(){
        Util.AvanzarPagina();
        vp_btnContinuar();
        btnContinuarTerceros.click();
    }

    public void confeccionProgramacionPropias(String s1, String s2, String s3, String s4, String s5, String s6, String s7,String s8){
        //PASO #1
        WebElement ctaOrigen = Util.driver.findElement(By.xpath("//div[@class='tipo-value' and contains(., '"+ s1 + "')]"));
        vp_tituloCtaPropia();
        ctaOrigen.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Click en la cuenta de origen", ctaOrigen.getText(), "", true, "N");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //PASO #2
        WebElement ctaDestino = Util.driver.findElement(By.xpath("//div[@class='destino-tipo-value' and contains(., '"+ s2 + "')]"));
        ctaDestino.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Ingresa la cuenta destino.", ctaDestino.getText(), "", true, "N");
        ingresoMonto(s3);
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Ingresa el monto de la transferencia.", s3, "", true, "N");
        ingresoConcepto(s4);
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Ingresa el concepto de la transferencia.", s4, "", true, "N");

        switchProgramacion.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Activa el switch para programr la transferencia.", "", "", true, "N");
        Util.AvanzarPagina();

        WebElement frecuencia = Util.driver.findElement(By.xpath("//label[@for='"+ s5 +"']"));
        frecuencia.click();
        Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Selecciona la frencuencia de la programacion.", s5, "", true, "N");

        if(s6.equals("1")){
            LocalDateTime today = LocalDateTime.now();//Today
            LocalDateTime fecha = today.plusDays(Long.parseLong(s7));//Plus numdias day
            String fechafin = String.valueOf(fecha.getDayOfMonth()) + "/" + String.valueOf(fecha.getMonthValue()) + "/" + String.valueOf(fecha.getYear());

            datePickerHasta.click();
            datePickerHasta.clear();
            datePickerHasta.sendKeys(fechafin);
            Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Ingresa la fecha fin de la programacion.", fechafin, "", true, "N");
        }

        if(s6.equals("0")){
            WebElement cantidadEjecuciones = Util.driver.findElement(By.xpath("//input[@id='cantidadEjecuciones']"));
            cantidadEjecuciones.sendKeys(s8);
            Reporte.agregarPaso("Transferencias entre cuentas propias - programadas", "Ingresa el numero de pagos a realizar en la programacion.", s8, "", true, "N");




        }

        confirmacionTranf();


    }


    //PUNTOS DE VERIFICACIÓN
    //Transferencias a terceros
    public void vp_bcoDestino(){
        String actual = lblBcoBolivariano.getText();
        Util.assert_contiene("Transferencias a terceros - Pago directo", "Se verifica el banco de destino.", actual,"BANCO PICHINCHA", true, "N");
    }

    public void vp_btnContinuar(){
        String actual = btnContinuarTerceros.getText();
        Util.assert_contiene("Transferencias a terceros - Pago directo", "Se verifica la configuracion de pago directo en la tranferencias.", actual,"Continuar", true, "N");
    }

    public void vp_montoMayor(){
        String actual = validacionMonto.getText();
        Util.assert_contiene("Transferencias a terceros - Pago directo", "Se verifica mensaje de validacion cuando el monto es mayor al saldo de la cuenta de origen", actual,"El monto a transferir excede el saldo de tu cuenta", true, "N");
    }
    //Transferencias propias
    @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'propias')]")
    WebElement tituloCtaPropia = null;
    public void vp_tituloCtaPropia(){
        String actual2 = tituloCtaPropia.getText();
        Util.assert_contiene("Transferencias entre cuentas propias, paso #1", "Se verifica el titulo de la ventana.", actual2,"Transferir a cuentas propias", true, "N");
    }

    @FindBy(xpath="//label[@class='grid-titulo-cuentaDestino_0']")
    WebElement tituloCtaDestino = null;
    public void vp_tituloCtaDestino(){
        String actual2 = tituloCtaDestino.getText();
        Util.assert_contiene("Transferencias entre cuentas propias,  paso #2", "Se verifica la confeccion de la cuenta destino.", actual2,"Cuenta destino", true, "N");
    }

    public void vp_btnTransferir(){
        String actual2 = btnTransferir.getText();
        Util.assert_contiene("Transferencias entre cuentas propias, paso #3", "Se verifica el botón 'Transferir'.", actual2,"Transferir", true, "N");
    }



}
