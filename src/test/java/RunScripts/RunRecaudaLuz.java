package RunScripts;

import Globales.Reporte;
import Globales.Util;
import TestPages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunRecaudaLuz {

    @Before
    public void iniciar_Chrome() {
        Util.Inicio("RecaudaLuz");
        Reporte.setEntorno("Ambiente: Desarrollo2" + "</b><br>" + "Navegador: Chrome; " + " Version: 88.0.4324.190 (Official Build) (64-bit)"  );
    }

    //MATRICULAR SUMINISTRO QUE NO CUMPLE FORMATO
    @Test
    public void MatricularCodigoInvalido() {
        Reporte.setNombreReporte("Validación del formato del suministro en la matriculación");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();
        matri.VP_MensajeFormatoSuministro();
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL ALIAS
    @Test
    public void MatricularSinAlias() {
        Reporte.setNombreReporte("Validación sin alias en la matriculación");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();
        matri.VP_MensajeSinAlias();
    }

    //MATRICULAR SUMINISTRO SIN INGRESAR EL CODIGO
    @Test
    public void MatricularSinCodigo() {
        Reporte.setNombreReporte("Validación sin suministro en la matriculación");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();
        matri.VP_MensajeSinSuministro();
    }

    //MATRICULAR SUMINISTRO CON ALIAS YA REGISTRADO
    @Test
    public void MatricularAliasYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de ALIAS ya matriculado");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();
        coord.VP_MensajeAliasYaMatriculado();
    }

    //MATRICULAR SUMINISTRO CON SUMINISTRO YA REGISTRADO
    @Test
    public void MatricularCodigoYaRegistrado() {
        Reporte.setNombreReporte("Matriculación de SUMINISTRO ya matriculado");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();
        coord.VP_MensajeCodigoYaMatriculado();
    }

    //MATRICULAR y CONSULTAR DE SUMINISTRO SIN DEUDA
    @Test
    public void MatriculacionyConsultaSinDeuda() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro sin deuda");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        //Validación del mensaje de suministro sin deuda
        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.VP_MensajeSuministroCancelada();
    }


    //MATRICULAR Y CONSULTAR CODIGO DEL SUMINISTRO INVALIDO
    @Test
    public void MatriculacionyConsultaCodigoNoExiste() {
        Reporte.setNombreReporte("Matriculación y Consulta de suministro no existe");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.VP_MensajeClienteNoExiste();
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA BLOQUEADA
    @Test
    public void MatriculacionConsultaPagoConCuentaBloqueada() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta bloqueada");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        IngresoValorAPagar valor = new IngresoValorAPagar();
        valor.CargaDatosSuministro();
        valor.VP_EtiquetaMontoAPagar();
        valor.ingreso_valor();
        valor.Click_CampoCuenta();
        valor.Seleccionar_cuentaBancaria();
        valor.VP_BotonCancelar();
        valor.VP_BotonPagar();

        coord.VP_BotonVolver();
        coord.Click_BotonPagar();
        coord.VP_MensajeCuentaBloqueada();
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON CUENTA CON FONDOS INSUFICIENTES
    @Test
    public void MatriculacionConsultaPagoConCuentaSinFondo() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago con cuenta con fondos insuficientes");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        IngresoValorAPagar valor = new IngresoValorAPagar();
        valor.CargaDatosSuministro();
        valor.VP_EtiquetaMontoAPagar();
        valor.ingreso_valor();
        valor.Click_CampoCuenta();
        valor.Seleccionar_cuentaBancaria();
        valor.VP_BotonCancelar();
        valor.VP_BotonPagar();

        coord.VP_BotonVolver();
        coord.Click_BotonPagar();
        coord.VP_MensajeFondoInsuficiente();
    }

    //MATRICULAR, CONSULTAR Y PAGAR SUMINISTRO CON MONTO A PAGAR SUPERIOR A LA DEUDA
    @Test
    public void MatriculacionyConsultaConMontoSuperior() {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de suministro con monto superior");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        IngresoValorAPagar valor = new IngresoValorAPagar();
        valor.CargaDatosSuministro();
        valor.VP_EtiquetaMontoAPagar();
        valor.ingreso_valor();
        valor.Click_CampoCuenta();
        valor.Seleccionar_cuentaBancaria();
        valor.VP_BotonCancelar();
        valor.VP_BotonPagar();
        valor.VP_MensajeMontoSuperior();
    }


    //FLUJO EXITOSO DE MATRICULACION, CONSULTA Y PAGO DEL SUMINISTRO
    @Test
    public void MatriculacionConsultayPagoLuz()  {
        Reporte.setNombreReporte("Matriculación, Consulta y Pago de luz Exitoso");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Click_BotonMatricular();

        MatriculacionLuz matri = new MatriculacionLuz();
        matri.CargaDatosSuministro();
        matri.VP_TipoServicio();
        matri.click_TipoServicio();
        matri.Seleccionar_Luz();
        matri.click_EmpresaServicio();
        matri.Seleccionar_EmpresaServicio();
        matri.click_TipoPago();
        matri.Seleccionar_Empresa();
        matri.IngresarIdentificacion();
        matri.IngresarAlias();
        matri.VP_BotonCancelar();
        matri.VP_BotonMatricular();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
        comprob.VP_BotonPagar();

        IngresoValorAPagar valor = new IngresoValorAPagar();
        valor.CargaDatosSuministro();
        valor.VP_EtiquetaMontoAPagar();
        valor.ingreso_valor();
        valor.Click_CampoCuenta();
        valor.Seleccionar_cuentaBancaria();
        valor.VP_BotonCancelar();
        valor.VP_BotonPagar();

        //Confirmar el pago en la pantalla de confirmación de pago
        coord.VP_BotonVolver();
        coord.Click_BotonPagar();

        //Sólo se puede validar los botones del Comprobante de pago
        comprob.Boton_PosicionConsolidada();
        comprob.Boton_ServiciosMatriculados();
    }


    //FLUJO EXITOSO DE CONSULTA (DE SUMINISTRO MATRICULADO) Y PAGO
    @Test
    public void ConsultayPagoLuz()  {
        Reporte.setNombreReporte("Consulta y Pago de luz Exitoso");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.Click_opcionLuz();
        consulta.Click_registroLuzConSaldo();
        consulta.Click_campoCuenta();
        consulta.Seleccionar_cuentaBancaria();
        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonConfirmar();

        Comprobante comprob = new Comprobante();
        //comprob.VP_MensajeMatriculacionExitosa();
        comprob.VP_BotonPosicionConsolidada();
        comprob.VP_BotonServiciosMatriculados();
    }


    //CONSULTAR SUMINISTRO (MATRICULADO) SIN DEUDA
    @Test
    public void ConsultaSuministroSinDeuda()  {
        Reporte.setNombreReporte("Consulta de suministro sin deuda");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.Click_opcionLuz();
        consulta.Click_registroLuzSinSaldo();
        consulta.VP_MensajeSuministroCancelada();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) INVALIDO
    @Test
    public void ConsultaSuministroIncorrecto()  {
        Reporte.setNombreReporte("Consulta de suministro inválido");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.Click_opcionLuz();
        consulta.Click_registroLuzNoExistente();
        consulta.VP_MensajeClienteNoExiste();
    }

    //CONSULTAR SUMINISTRO (MATRICULADO) Y TRATAR DE PAGAR SIN SELECCIONAR LA CUENTA BANCARIA
    @Test
    public void ConsultayPagoSinSeleccionarCuenta()  {
        Reporte.setNombreReporte("Consulta y pago sin seleccionar la cuenta");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consulta = new ConsultaPrincipalLuz();
        consulta.Click_opcionLuz();
        consulta.Click_registroLuzConSaldo();
        consulta.Click_menuFlotante();
        consulta.Click_BotonPagar();
        consulta.VP_MensajeSinSeleccionarCuenta();
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA BLOQUEADA
    @Test
    public void ValidacionCuentaBloqueada()  {
        Reporte.setNombreReporte("Validación de cuenta bloqueada");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consultaL = new ConsultaPrincipalLuz();
        consultaL.Click_opcionLuz();
        consultaL.Click_registroLuzConSaldo();
        consultaL.Click_campoCuenta();
        consultaL.Seleccionar_cuentaBancaria();
        consultaL.Click_menuFlotante();
        consultaL.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonPagar();
        coord.VP_MensajeCuentaBloqueada();
    }

    //CONSULTAR Y PAGAR SUMINISTRO (YA MATRICULADO) CON CUENTA BANCARIA CON FONDOS INSUFICIENTES
    @Test
    public void ValidacionFondoInsuficiente()  {
        Reporte.setNombreReporte("Validación de fondos insuficientes");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consultaL = new ConsultaPrincipalLuz();
        consultaL.Click_opcionLuz();
        consultaL.Click_registroLuzConSaldo();
        consultaL.Click_campoCuenta();
        consultaL.Seleccionar_cuentaBancaria();
        consultaL.Click_menuFlotante();
        consultaL.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.escribe_desafio0();
        coord.escribe_desafio1();
        coord.escribe_desafio2();
        coord.VP_MensajeCoordenadasCorrectas();
        coord.VP_BotonVolver();
        coord.Click_BotonPagar();
        coord.VP_MensajeFondoInsuficiente();
    }

    //INGRESO INCORRECTO DE COORDENADAS PARA PAGAR SUMINISTRO (YA MATRICULADO)
      @Test
    public void ValidacionCoordenadasIncorrectas()  {
        Reporte.setNombreReporte("Validación de fondos insuficientes");
        Login login = new Login();
        login.Ingresar("0");

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.ResponderCorrectamente();

        RegistroEquipo equipo = new RegistroEquipo();
        equipo.vp_etiqueta_nomatriculado();
        equipo.click_continuar();

        PosicionConsolidada posicion = new PosicionConsolidada();
        posicion.vp_etiqueta_saludo();

        MenuPagoServicios menu = new MenuPagoServicios();
        menu.click_menu_pagos();
        menu.click_submenu_pagos();
        menu.Opcion_Luz();

        ConsultaPrincipalLuz consultaL = new ConsultaPrincipalLuz();
        consultaL.Click_opcionLuz();
        consultaL.Click_registroLuzConSaldo();
        consultaL.Click_campoCuenta();
        consultaL.Seleccionar_cuentaBancaria();
        consultaL.Click_menuFlotante();
        consultaL.Click_BotonPagar();

        Coordenadas coord = new Coordenadas();
        coord.vp_etiqueta_dispositivo();
        coord.VP_TextoIngresaCoordenadas();
        coord.IngresarDesafio1();
        coord.IngresarDesafio2();
        coord.IngresarDesafio3();
        coord.VP_MensajeCoordenadasIncorrectas();
    }

    @After
    public void salir()
    {
        Reporte.finReporte();
    }


}
