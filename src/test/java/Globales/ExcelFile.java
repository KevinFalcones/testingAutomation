package Globales;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFile {

    public String obtenerValorCoordenada(String filepath, String sheetname, String desafio) throws IOException
    {
        File file = new File(filepath);
        FileInputStream inputStream =  new FileInputStream(file);
        XSSFWorkbook newWrkb =  new XSSFWorkbook(inputStream);
        XSSFSheet newSheet = newWrkb.getSheet(sheetname);
        XSSFRow row = null;
        int rowCount = newSheet.getLastRowNum() - newSheet.getFirstRowNum();
        int filacoord = -1;
        int columncoord = -1;

        //Buscar la columna del desafío en la fila 0
        row = newSheet.getRow(0);
        for (Cell cell : row) {
            if(cell.getStringCellValue().equals(desafio)) {
                columncoord = cell.getColumnIndex();
                break;
            }
        }

        //Buscar la fila del usuario para el desafío
        for (int i = 0; i <= rowCount; i++)
        {
            row = newSheet.getRow(i);
            XSSFCell cell = row.getCell(0);
            if (cell.getStringCellValue().toUpperCase().equals(Util.getDataCliente()[1].toUpperCase())) {
                filacoord = cell.getRowIndex();
                break;
            }
        }

        XSSFRow rowcoord = newSheet.getRow(filacoord);
        //return String.valueOf((int)rowcoord.getCell(columncoord).getNumericCellValue());
        return rowcoord.getCell(columncoord).getStringCellValue();
    }
}
