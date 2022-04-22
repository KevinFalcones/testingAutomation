package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class RunPerfilPreguntas {
    @Before
    public void iniciar_Chrome() {
        Util.Inicio("PerfilPreguntas");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );

        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();
    }


    @Test
    public void actualizacionPreguntas() {
        Reporte.setNombreReporte("Actualizacion de Preguntas de Seguridad");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ingresa_opcion_perfil();
        pregunta.vp_etiqueta_preguntaperfil();

        List<String> suministros = Util.getCamposDataPool(System.getProperty("user.dir")+"/archivos/dp_preguntas_seguridad.txt");
        Iterator<String> suministro = suministros.iterator();
        String[] dato = null;
        int linea = 0;
        Boolean primeravez=true;

        while (suministro.hasNext()) {
            dato = suministro.next().split("\t");
            linea = linea + 1;
            if (linea == 1)
                continue;

            pregunta.selecciona_pregunta0(dato[0]);
            pregunta.responde_pregunta0(dato[1]);

            dato = suministro.next().split("\t");
            pregunta.selecciona_pregunta1(dato[0]);
            pregunta.responde_pregunta1(dato[1]);

            dato = suministro.next().split("\t");
            pregunta.selecciona_pregunta2(dato[0]);
            pregunta.responde_pregunta2(dato[1]);

            dato = suministro.next().split("\t");
            pregunta.selecciona_pregunta3(dato[0]);
            pregunta.responde_pregunta3(dato[1]);

            dato = suministro.next().split("\t");
            pregunta.selecciona_pregunta4(dato[0]);
            pregunta.responde_pregunta4(dato[1]);
        }

        pregunta.click_btn_actualizar();
        //pregunta.selecciona_celular();
        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        if (coord.disp_coord) {
            if (primeravez) {
                coord.VP_TextoIngresaCoordenadas();
                coord.escribe_desafio0();
                coord.escribe_desafio1();
                coord.escribe_desafio2();
                coord.VP_MensajeCoordenadasCorrectas();
                primeravez = false;
            }
        }

        if (coord.disp_otp) {
            String medio_envio = coord.selecciona_medio();
            System.out.println(medio_envio);
            coord.vp_etiqueta_ingreso();

            String ventanaAnterior = Util.obtieneVentanaActual();
            Util.Inicio_Latinia("");
            Latinia latinia = new Latinia();
            latinia.login("2");
            String otp = latinia.getOTP(medio_envio.replace("*",""));

            Util.regresaDeOTP(ventanaAnterior);

            coord.escribe_desafio1_otp(String.valueOf(otp.charAt(0)));
            coord.escribe_desafio2_otp(String.valueOf(otp.charAt(1)));
            coord.escribe_desafio3_otp(String.valueOf(otp.charAt(2)));
            coord.escribe_desafio4_otp(String.valueOf(otp.charAt(3)));
            coord.escribe_desafio5_otp(String.valueOf(otp.charAt(4)));
            coord.escribe_desafio6_otp(String.valueOf(otp.charAt(5)));
            coord.escribe_desafio7_otp(String.valueOf(otp.charAt(6)));
            coord.escribe_desafio8_otp(String.valueOf(otp.charAt(7)));
        }

        coord.vp_etiqueta_dispositivo();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        ComprobantePerfil comp = new ComprobantePerfil();
        comp.vp_mensaje_actpreguntas_exitoso();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }
}


/*
�En qu� calle viv�as cuando ten�as 10 a�os de edad?
�Cu�l es tu animal favorito?
Cuando eras ni�o, �qu� quer�as ser cuando fueras grande?
�Cu�l es el apodo de tu abuelo?
�En qu� calle viv�a tu mejor amigo de la infancia?
�Cu�l es el nombre del menor de tus hermanos?
�Cu�l es tu deporte favorito?
�Cu�l es el segundo nombre de tu abuelo paterno?
�Cu�l es el nombre de tu primera novia / novio?
�Cu�l es el autom�vil de tus sue�os?
�Cu�l es el nombre de tu colegio?
�Cu�l es el nombre de tu colegio?
�Cu�l es el apodo de tu hermano menor?
�Cu�l era la marca de tu primer carro?
�Cu�l es el nombre de tu mejor amigo de la infancia?
�Cu�l es el nombre del hermano menor de tu madre?
�Cu�l es tu personaje de dibujos animados favorito?
�Cu�l es el autom�vil de tus sue�os?
�Cu�l era tu equipo favorito de la infancia?
�Cu�l era tu equipo favorito de la infancia?
�Qu� carrera estudiaste en la universidad?
�Cu�l es el nombre de tu abuela materna?
�Cu�l es el nombre de tu mejor amigo?
�Cu�l es el apodo de tu hermano menor?
�Cu�l es el apodo de tu hermano mayor?
�Cu�l es el nombre del hermano menor de tu madre?
�Cu�l es el nombre de tu sobrino mayor?
�Cu�l era el nombre de tu primera mascota?
�Cu�l es el nombre de tu abuelo materno?
�Cu�l es el nombre de la universidad en la que te graduaste?
�Cu�l es el segundo nombre de tu hijo mayor?
�En qu� calle viv�a tu mejor amigo del colegio?
�Cu�l es el nombre de tu primo favorito?
�Cu�l es el nombre de la dama de honor en tu boda?
�Cu�l fue el nombre de tu primer jefe?
�Cu�l es tu pasatiempo favorito?
�Cu�l es el apodo de tu hijo menor?
�Cu�l es el nombre del menor de los hermanos de tu padre?
�Cu�l es el nombre de tu mejor amigo?
�Cu�l es el nombre de tu abuela paterna?
�Cu�l es el apodo de tu hermano mayor?
�Cu�l es tu personaje de ficci�n favorito?
�Cu�l es el nombre de tu abuelo materno?
�Cu�l es el nombre de la universidad en la que te graduaste?
�Cu�l es el nombre de tu sobrino mayor?
�Cu�l es tu personaje de dibujos animados favorito?
�Cu�l era el nombre de tu primera mascota?
�Cu�l es el nombre de tu primo favorito?
�Cu�l es el nombre de tu mejor amigo de la infancia?
�Cu�l es el segundo nombre de tu hijo mayor?
�Cu�l es el apodo de tu abuelo?
�En qu� calle viv�a tu mejor amigo del colegio?
�En qu� calle viv�as cuando ten�as 10 a�os de edad?
�Cu�l es el nombre de tu primera novia / novio?
�Cu�l es el nombre de la dama de honor en tu boda?
�Cu�l es el nombre del menor de tus hermanos?
�Cu�l es el nombre del menor de los hermanos de tu padre?
Cuando eras ni�o, �qu� quer�as ser cuando fueras grande?
�Cu�l es tu deporte favorito?
�En qu� calle viv�a tu mejor amigo de la infancia?
�Cu�l es el nombre de tu abuela paterna?
�Cu�l es tu personaje de ficci�n favorito?
�Cu�l es el segundo nombre de tu abuela materna?
�Cu�l es el nombre de tu abuela materna?
�Cu�l era la marca de tu primer carro?
�Una fecha a recordar?
�Cu�l es el segundo nombre de tu abuela materna?
�Nombre de su lugar favorito?
�Cu�l fue el nombre de tu primer jefe?
�Cu�l es tu pasatiempo favorito?
�Qui�n es tu deportista favorito?
�Nombre de su libro preferido?
�Nombre de su autor preferido?
�Nombre de un amigo/a entra�able?
�Nombre de su juego de infancia preferido?
�Cu�l es el apodo de tu hijo menor?
�Cu�l es el segundo nombre de tu abuelo paterno?
�Nombre de su cancion preferida?
�Nombre de la ciudad que desea conocer?
�Qu� carrera estudiaste en la universidad?
 */