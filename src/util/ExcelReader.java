package util;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {


    private File gui_export, bereiter; //File we try to read from
    private ArrayList<NameSheme> shemes; //NameList we get after we successfully read from File

    private XSSFWorkbook wb_gui, wb_bereiter;
    private XSSFSheet sheet;
    private Map<String, FormulaEvaluator> wbs;
    private FormulaEvaluator fe_gui, fe_bereiter;
    private Cell cell;
    private Row row;
    private CellReference ref;
    private int col; //test

    private ArrayList<String> content;

    public ExcelReader(File gui_export, File bereiter, String col_name) throws IOException {

        this.content = new ArrayList<String>();
        this.gui_export = gui_export;
        this.bereiter = bereiter;

        this.wb_bereiter = new XSSFWorkbook(new FileInputStream(this.bereiter));
        this.fe_bereiter = wb_bereiter.getCreationHelper().createFormulaEvaluator();

        this.wb_gui = new XSSFWorkbook(new FileInputStream(this.gui_export));
        this.fe_gui = wb_gui.getCreationHelper().createFormulaEvaluator();

        wbs = new HashMap<String, FormulaEvaluator>();
        wbs.put(bereiter.getName(), fe_bereiter);
        wbs.put(gui_export.getName(), fe_gui);
        fe_bereiter.setupReferencedWorkbooks(wbs);
        fe_bereiter.evaluateAll();

        this.sheet = wb_bereiter.getSheetAt(0);
        //this.col = getRowIndexByName(col_name);
        this.col = 1;
        //this.path = path;
        //this.wb = new XSSFWorkbook(new FileInputStream(this.path));
        //this.sheet = wb.getSheet(tab);
        //this.column = getRowIndexByName(col);
        //if(this.col == -1) return;
        this.shemes = new ArrayList<NameSheme>();
    }

    public void formContent(){

        for(Row r : sheet){

            Cell c = r.getCell(col - 1);
            if(c != null) {
                switch (c.getCellType()) {
                    case STRING:
                        if (c.getStringCellValue().equals("")) return;
                        break;
                    case FORMULA:
                        System.out.println(c.getStringCellValue());
                        break;
                }
            } else return;
        }
    }

    private int getRowIndexByName(String column){

        row = sheet.getRow(0);
        for(Cell c : row){

            switch(c.getCellType()){

                case STRING:
                    if(c.getStringCellValue().equals(column)) return c.getAddress().getColumn();
                    else if(c.getStringCellValue().equals("")) return -1;
                    break;
                default:
                    break;
            }
        }
        return -1;
    }

    public boolean CreateNameShemeList(){

        boolean condition = false;
        if(sheet != null) {
            for (int i = 2; !condition; i++) {

                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(col);
                } else return false;
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                } else return false;

                if (cell.getStringCellValue().equals("")) {
                    condition = true;
                    return true;
                } else shemes.add(new NameSheme(cell.getStringCellValue()));
            }
        } else return false;
        return false;
    }
    public NameSheme getPivot(){
        return shemes.get(0);
    }

    public ArrayList<NameSheme> getShemes(){
        return shemes;
    }
    public ArrayList<NameSheme> trimShemes(int pivot){

        int old = shemes.size();
        for(NameSheme ns : shemes)
            if(ns.getIdAsInt() <= pivot)
                shemes.remove(ns);

        int diff = old - shemes.size();
        System.out.println("Removed " + diff + " Items from NameShemeList");
        return shemes;
    }

}
