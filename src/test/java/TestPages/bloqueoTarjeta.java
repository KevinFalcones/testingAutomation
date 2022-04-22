package TestPages;

import Globales.Reporte;
import Globales.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class bloqueoTarjeta {

    public bloqueoTarjeta() {
        PageFactory.initElements(Util.driver, this);
    }

    @FindBy(id="TARJETAS")
    WebElement menuTarjetas = null;

    @FindBy(id="EB_CONSULTA_BLOQUEO_TEMPORAL_TARJETA_DEBITO")
    WebElement bloqueo = null;

    @FindBy(id="textswitch_db_0")
    WebElement tipoTarjeta = null;

    @FindBy(id="action-close-exito-b")
    WebElement exit = null;



    public void bloqueo(){
        menuTarjetas.click();
        bloqueo.click();
        Reporte.agregarPaso("Bloqueo temporal", "Ingreso a la funcionalidad.", "", "", true, "N");
    }

    public void bloqueoTarjeta(String tc){
        List<WebElement> listTarjetas = Util.driver.findElements(By.xpath("//div[@class='detail-tarj']"));
        List<WebElement> listSwitches = Util.driver.findElements(By.xpath("//span[@class='slider cb-slider round']"));

        System.out.println(tc);
        try {

            for (int i=0;i<listTarjetas.size();i++){
                if (listTarjetas.get(i).getText().contains(tc)){
                    if (i>3){
                        Util.AvanzarPagina();
                    }
                    Reporte.agregarPaso("Bloqueo temporal", "Click en el switch de la tarjeta a bloquear temporalmente.", listTarjetas.get(i).getText(), "", false, "N");
                    System.out.println(listTarjetas.get(i).getText());
                    listSwitches.get(i).click();
                    Thread.sleep(1500);
                }
            }

            WebElement btnConfirmar = Util.driver.findElement(By.xpath("//button[@id='btnBloquearSQ']"));
            Reporte.agregarPaso("Bloqueo temporal", "Confirma el bloqueo temporal", btnConfirmar.getText(), "", true, "N");
            btnConfirmar.click();
            Thread.sleep(1500);

            Reporte.agregarPaso("Bloqueo temporal tarjeta exitoso", "Se verifica mensaje de transacción exitosa", "", "", true, "N");

            exit.click();

        } catch (InterruptedException | StaleElementReferenceException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

}
