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

public class AdmCupos {

    public AdmCupos() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menuTarjetas = null;

    @FindBy(id="EB_GESTION_LIMITE_TARJETA_DEBITO")
    WebElement admCupos = null;

    @FindBy(id="solapa_tabDebito")
    WebElement debito = null;

    @FindBy(id="solapa_tabCredito")
    WebElement credito = null;

    public void cupoCredito(){
        menuTarjetas.click();
        admCupos.click();
        credito.click();
        Reporte.agregarPaso("Adm. de cupo de TC", "Ingreso a la funcionalidad.", "", "", true, "N");

        Util.AvanzarPagina();
    }

    public void cupoDebito(){
        menuTarjetas.click();
        admCupos.click();
        //credito.click();
        Reporte.agregarPaso("Adm. de cupo de TD", "Ingreso a la funcionalidad.", "", "", true, "N");

        Util.AvanzarPagina();
    }

    public void confeccionCupoCredito(int tipoTarj, String ... s1){
        List<WebElement> listCupos = Util.driver.findElements(By.xpath("//input[@class='validate-cash']"));

        for (int i = 0; i<s1.length;i++){
            if (s1[i].equals("-")){
                continue;
            }else {
                listCupos.get(i).sendKeys(s1[i]);
                if (tipoTarj==1){
                    Reporte.agregarPaso("Adm. de cupo de TC", "Ingreso de los nuevos cupos.", s1[i], "", true, "N");
                }
                if (tipoTarj==0){
                    Reporte.agregarPaso("Adm. de cupo de TD", "Ingreso de los nuevos cupos.", s1[i], "", true, "N");
                }

            }
        }


    }

    public void switches (int tipoTarj, String ... s1){
        List<WebElement> listSwitches = Util.driver.findElements(By.xpath("//span[@class='slider round']"));

        for (int i = 0; i<s1.length;i++){
            //System.out.println(s1[i]);
            if (s1[i].equals("n")){
                listSwitches.get(i).click();
                if (tipoTarj==1){
                    Reporte.agregarPaso("Adm. de cupo de TC", "Desactivacion de los cupos.", s1[i], "", true, "N");
                }
                if (tipoTarj==0){
                    Reporte.agregarPaso("Adm. de cupo de TD", "Desactivacion de los cupos.", s1[i], "", true, "N");
                }

            }

        }

    }
    public void guardar(int tipoTarj){
        WebElement btnGuardar = Util.driver.findElement(By.xpath("//button[@name='_eventId_guardar']"));
        if (tipoTarj==1){
            Reporte.agregarPaso("Adm. de cupo de TC", "Guarda la confeccion de los cupos.", "", "", true, "N");
        }
        if (tipoTarj==0){
            Reporte.agregarPaso("Adm. de cupo de TD", "Guarda la confeccion de los cupos.", "", "", true, "N");

        }
        btnGuardar.click();

    }

    public void estoyDeAcuerdo(){
        WebElement checkEstoyDeAcuerdo = Util.driver.findElement(By.xpath("//input[@name='chkvisible']"));
        checkEstoyDeAcuerdo.click();

    }

    public void msjEsxitoso(int tipoTarj){
        WebElement msjExitoso = Util.driver.findElement(By.xpath("//div[@class='cuerpo']"));
        if (tipoTarj==1){
            Reporte.agregarPaso("Adm. de cupo de TC", "Cambio exitoso.", msjExitoso.getText(), "", true, "N");
        }
        if (tipoTarj==0){
            Reporte.agregarPaso("Adm. de cupo de TD", "Cambio exitoso.", msjExitoso.getText(), "", true, "N");
        }

    }













}
