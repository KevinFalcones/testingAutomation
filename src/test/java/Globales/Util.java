package Globales;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class Util {

    public static WebDriver driver;
    public static WebDriver driverF;
    public static WebDriverWait wait;
    static JavascriptExecutor js;
    public static Properties prop = null;
    private static String[] dataCliente;
    private static String[] dataLatinia;

    public static String[] getDataCliente() {
        return dataCliente;
    }
    public static void setDataCliente(String[] datos) {
        Util.dataCliente = datos;
    }

    public static String[] getDataLatinia() {
        return dataLatinia;
    }
    public static void setDataLatinia(String[] datos) {
        Util.dataLatinia = datos;
    }

    public static File captura = null;

    static void InicioConfig() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir")+"/config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getCamposDataPool(String fileName)
    {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    public static String[] getCamposDataPool(String fileName, String numregistro )
    {
        List<String> lines = Collections.emptyList();
        String[] campos = null;
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            Iterator<String> itr = lines.iterator();

            while (itr.hasNext())
            {
                campos = itr.next().split("\t");
                if (campos[0].equals(numregistro))
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return campos;
    }

    public static void Inicio(String nom_archivo) {
        InicioConfig();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+prop.getProperty("ruta_driverchrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url =prop.getProperty("url_sitio");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Reporte.setNombreArchivo(nom_archivo);
        Reporte.setNombreReporte("");
        Reporte.setEntorno("");
    }

    public static void Inicio_Latinia(String nom_archivo) {
        InicioConfig();
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+prop.getProperty("ruta_driverfirefox"));

        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setProxyType(Proxy.ProxyType.DIRECT);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        driverF = new FirefoxDriver(cap);
        driverF.manage().window().maximize();

        String url =prop.getProperty("url_latinia");
        driverF.get(url);
        driverF.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        if (!nom_archivo.equals(""))
        {
            Reporte.setNombreArchivo("Latinia");
            Reporte.setNombreReporte("");
            Reporte.setEntorno("");
        }
    }

    public static void CapturarImagen()
    {
        Util.captura = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        Path source = Paths.get(Util.captura.getPath());
        Path target = Paths.get(System.getProperty("user.dir")+"/resize.png");

        InputStream is = null;
        try {
            is = new FileInputStream(source.toFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Util.resize(is, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void resize(InputStream input, Path target) throws IOException {
        int width = 0;
        int height = 0;
        // read an image to BufferedImage for processing
        BufferedImage originalImage = ImageIO.read(input);

        height = originalImage.getHeight() * 70 /100;
        width = originalImage.getWidth() * 70 /100;
        // create a new BufferedImage for drawing
        BufferedImage newResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newResizedImage.createGraphics();

        // background transparent
        g.setComposite(AlphaComposite.Src);
        g.fillRect(0, 0, width, height);

        Map<RenderingHints.Key,Object> hints = new HashMap<>();
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.addRenderingHints(hints);

        // puts the original image into the newResizedImage
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        // get file extension
        String s = target.getFileName().toString();
        String fileExtension = s.substring(s.lastIndexOf(".") + 1);

        // we want image in png format
        Util.captura = target.toFile();
        ImageIO.write(newResizedImage, fileExtension, Util.captura);
    }

    public static void AvanzarPagina()
    {
        JavascriptExecutor js  = (JavascriptExecutor) Util.driver;
        js.executeScript("window.scrollBy(0,400)");
    }

    public static void AvanzarPagina(WebElement element)
    {
        element.click();
        JavascriptExecutor js  = (JavascriptExecutor) Util.driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void RetrocederPagina()
    {
        JavascriptExecutor js  = (JavascriptExecutor) Util.driver;
        js.executeScript("window.scrollBy(0,0)");
    }

    public static void RetrocederPagina(WebElement element)
    {
        JavascriptExecutor js  = (JavascriptExecutor) Util.driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void assert_igual(String caso, String detalle, String actual, String esperado, Boolean captura)
    {
        Util.assert_igual(caso,detalle,actual,esperado,captura,"");
    }
    public static void assert_igual(String caso, String detalle, String actual, String esperado, Boolean captura, String categoria)
    {
        try
        {
            assertThat(actual, is(esperado));
            Reporte.agregarPaso(caso, detalle, actual, esperado, captura, categoria);
        }
        catch (AssertionError e) {
            Reporte.agregarPaso(caso, detalle, e.getMessage(), esperado, captura, 1, categoria);
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    public static void assert_contiene(String caso, String detalle, String actual, String esperado, Boolean captura)
    {
        Util.assert_contiene(caso,detalle,actual,esperado,captura,"");
    }

    public static void assert_contiene (String caso, String detalle, String actual, String esperado, Boolean captura, String categoria)
    {
        try
        {
            assertThat(actual, CoreMatchers.containsString(esperado));
            Reporte.agregarPaso(caso, detalle, actual, esperado, captura, categoria);
        }
        catch (AssertionError e) {
            Reporte.agregarPaso(caso, detalle, e.getMessage(), esperado, captura, 1, categoria);
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    public static void assert_contiene(String caso, String detalle, String actual, String esperado1, String esperado2, Boolean captura, String categoria)
    {
        try
        {
            assertThat(actual, either(containsString(esperado1)).or(containsString(esperado2)));
            Reporte.agregarPaso(caso, detalle, actual, "", captura, categoria);
        }
        catch (AssertionError e) {
            Reporte.agregarPaso(caso, detalle, e.getMessage(), "", captura, 1, categoria);
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    public static void openTab()
    {
        JavascriptExecutor js = (JavascriptExecutor) Util.driver;
        js.executeScript("window.open()");
    }

    public static void waitForElementToBeClickable(By locator) {
        wait = new WebDriverWait(Util.driver,30,100);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    public static String obtieneVentanaActual()
    {
        String winHandle = Util.driver.getWindowHandle();
        System.out.println(winHandle);
        return winHandle;
    }

    public static void regresaDeOTP(String ventanaAnterior)
    {
        Util.driverF.close();
        Set <String> allWindow = Util.driver.getWindowHandles (); // Obtener todos los identificadores de ventana
        System.out.println (allWindow.size ());

        for(String i:allWindow) {
            System.out.println ("i:"+i);
            if (i != ventanaAnterior) {// Juzgar si es la primera ventana
                Util.driver.switchTo ().window (i); // Si no es así, cambia a la ventana actual
                System.out.println ("Con éxito cambiado a:" + Util.driver.getTitle ()); // Para demostrar que está cambiado, imprima el título de la ventana
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                //Util.driver.close();
            }
        }

    }
}
