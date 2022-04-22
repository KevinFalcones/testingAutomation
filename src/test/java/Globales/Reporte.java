package Globales;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

public class Reporte {
    static String nombrereporte = null;
    static String nombrearchivo = null;
    static String entorno = null;
    static String pasos = "";

    public static String getNombreReporte() {
        return nombrereporte;
    }
    public static void setNombreReporte(String nombrereporte) {
        Reporte.nombrereporte = nombrereporte;
    }

    public static void setEntorno(String entorno) {
        Reporte.entorno = entorno;
    }
    public static String getEntorno() {
        return entorno;
    }

    public static String getPasos() {
        return pasos;
    }

    public static String getNombreArchivo() { return nombrearchivo; }
    public static void setNombreArchivo(String nombreArchivo)
    {
        TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yy_HHmmss");
        df.setTimeZone(tz);
        String currentTime = df.format(now);
        Reporte.nombrearchivo = nombreArchivo + "_" + currentTime;
    }

    public static void agregarPaso(String caso, String detalle, String actual, String esperado, Boolean captura, String categoria)
    {
        if (captura)
        {Util.CapturarImagen();}

        TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
        Date now = new Date();
        //DateFormat df = new SimpleDateFormat(" hh: mm: ss a dd-MMM-yyyy");
        DateFormat df = new SimpleDateFormat("hh: mm: ss");
        df.setTimeZone(tz);
        String currentTime = df.format(now);

        actual = currentTime + "</b><br>" + actual;
        String filai= "<tr class=\"test-result-step-row test-result-step-row-alttwo\">";

        String col1 = Reporte.reporteColumnaDesc("test-result-step-command-cell", caso);
        String col2 = Reporte.reporteColumnaDesc("test-result-step-description-cell", detalle);
        switch (categoria) {
            case "N": categoria = "Normal"; break;
            case "C": categoria = "De Control"; break;
            default:  categoria = "Normal"; break;
        }
        String col3 = Reporte.reporteColumnaDesc("test-result-step-description-cell", categoria);
        String col4 = Reporte.reporteColumnaDesc("test-result-step-result-cell-ok", actual);

        String col5 = "";
        if (captura)
            col5 = Reporte.reporteColumnaImg("test-result-step-description-cell");
        else
            col5 = Reporte.reporteColumnaDesc("test-result-step-description-cell", "");

        String filaf= "</tr>";

        Reporte.pasos = Reporte.pasos + filai + col1 + col2 + col3 + col4 + col5 + filaf;
    }

    public static void agregarPaso(String caso, String detalle, String actual, String esperado, Boolean captura, int result, String categoria)
    {
        if (captura)
        {Util.CapturarImagen();}

        String filai= "<tr class=\"test-result-step-row test-result-step-row-alttwo\">";

        String col1 = Reporte.reporteColumnaDesc("test-result-step-command-cell", caso);
        String col2 = Reporte.reporteColumnaDesc("test-result-step-description-cell", detalle);


        switch (categoria) {
            case "N": categoria = "Normal"; break;
            case "C": categoria = "De Control"; break;
            default:  categoria = "Normal"; break;
        }
        String col3 = Reporte.reporteColumnaDesc("test-result-step-description-cell", categoria);

        String col4 = "";
        if (result == 0)
            col4 = Reporte.reporteColumnaDesc("test-result-step-result-cell-ok", actual);
        else
            col4 = Reporte.reporteColumnaDesc("test-result-step-result-cell-failure", actual);

        String col5 = "";
        if (captura)
            col5 = Reporte.reporteColumnaImg("test-result-step-description-cell");
        else
            col5 = Reporte.reporteColumnaDesc("test-result-step-description-cell", "");

        String filaf= "</tr>";

        Reporte.pasos = Reporte.pasos + filai + col1 + col2 + col3 + col4 + col5 + filaf;
    }

    static String reporteColumnaDesc (String clase, String descripcion)
    {
        String col = "<td class=\"" + clase + "\">";
        col = col + descripcion;
        col = col + "</td>";
        return col;
    }

    static String reporteColumnaImg (String clase)
    {
        String col = "<td class=\"" + clase + "\">";
        //col = col + "<img src=\".." + Util.ArchivoCaptura + "\" width=\"300\" height=\"550\">";
        try {
            col = col +
                    "<div class=\"dropdown\"><img class=\"dropdown\" src=\"" + "data:image/jpg;base64," +
                    Reporte.getBase64String() + "\"" + "width=\"135\" height=\"90\"><div class=\"dropdown-content\">"+
                    "<img class=\"dropdown-content\" src=\"" + "data:image/jpg;base64," +
                    Reporte.getBase64String() + "\"" + "width=\"700\" height=\"350\"></div></div>";
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        col = col + "</td>";
        return col;
    }

    static String getBase64String() throws IOException {
        //ruta = ruta.replace("\\","//");
        byte[] fileContent = FileUtils.readFileToByteArray(Util.captura);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return encodedString;
    }

    public static void finReporte () {
        TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        df.setTimeZone(tz);
        String currentTime = df.format(now);

        File htmlTemplateFile = new File(System.getProperty("user.dir") + "/archivos/template.html");
        try {
            String htmlString = FileUtils.readFileToString(htmlTemplateFile);
            htmlString = htmlString.replace("$Titulo", Reporte.getNombreReporte());
            htmlString = htmlString.replace("$Fecha", currentTime);
            htmlString = htmlString.replace("$Entorno", Reporte.getEntorno());
            htmlString = htmlString.replace("$Pasos", Reporte.getPasos());
            File newHtmlFile = new File(System.getProperty("user.dir") + "/Reportes/" + Reporte.getNombreArchivo() + ".html");
            //System.out.println(htmlString);
            FileUtils.writeStringToFile(newHtmlFile, htmlString);
            //Reporte.reportepdf.close();
        }
        catch (Exception e)
        {
            //Util.logbm.warning(e.getMessage());
        }
        //Util.driver.quit();
    }


}
