package TestPages;

import Globales.*;
import Globales.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static Globales.Util.driver;

public class ActivacionDepositoExpress {
    public ActivacionDepositoExpress (){PageFactory.initElements(Util.driver, this);}
        @FindBy(id ="SOLICITUDES")
        WebElement menuSolicitar = null;

        @FindBy(id ="EB_ACTIVACION_DE_DEPOSITO_EXPRESS")
        WebElement depExpress = null;

        @FindBy(xpath="//div[@class='titulo-seccion' and contains(., 'express')]")
        WebElement tituloPantalla = null;

        @FindBy(id="checkConfirm")
        WebElement checkTerm = null;

        @FindBy(name = "_eventId_aceptar")
        WebElement btnAceptar = null;

        @FindBy(name ="_eventId_rechazar")
        WebElement btnRechazar = null;

        @FindBy(xpath="//div[@class='mensaje-tiene-dep-express' and contains(., 'activado')]")
        WebElement servicioActivo = null;

        @FindBy(xpath="//div[@class='encabezado' and contains(., 'La activaci�n')]")
        WebElement tranOK = null;

        public void MenuSolicitar(){
            menuSolicitar.click();
            depExpress.click();
            vp_tituloPantalla();
            Util.AvanzarPagina();
        }

        public void vp_tituloPantalla(){
            String actual = tituloPantalla.getText();
            Util.assert_contiene("DEPOSITO EXPRESS", "Se verifica el titulo", actual,"Dep�sito express", false, "N");
        }

        public void vp_servicioActivo(){
            String actual = servicioActivo.getText();
            Util.assert_contiene("DEPOSITO EXPRESS ACTIVADO", "Se verifica servicio activado", actual,"Tu Dep�sito Express est� activado", true, "N");
        }

        public void vp_tranExitosa(){
            String actual = tranOK.getText();
            Util.assert_contiene("DEPOSITO EXPRESS ACTIVADO", "Se verifica mensaje de transacci�n exitosa", actual,"La activaci�n de su dep�sito express se ha realizado con �xito.", true, "N");
        }

        public void chk_terminos(){
            checkTerm.click();
            Reporte.agregarPaso("ACTIVAR DEPOSITO EXPRESS", "Click en ", "He le�do y acepto los t�rminos y condiciones\n" + "\t\t\t\t\t", "", false, "N");
            Util.AvanzarPagina();
        }

        public void setBtnAceptar(){
            String actual = btnAceptar.getText();
            Util.assert_contiene("ACTIVAR DEPOSITO EXPRESS", "Click en bot�n", actual, "Aceptar", true, "N");
            btnAceptar.click();
        }

        public void setBtnRechazar(){
            String actual = btnRechazar.getText();
            Util.assert_contiene("ACTIVAR DEPOSITO EXPRESS", "Click en bot�n", actual, "Rechazar", true, "N");
            btnRechazar.click();
        }




}



