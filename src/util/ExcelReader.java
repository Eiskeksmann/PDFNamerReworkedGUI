package util;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.Na;
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
import java.util.*;

public class ExcelReader {


    private File gui_export, bereiter; //File we try to read from
    private ArrayList<NameSheme> shemes; //NameList we get after we successfully read from File
    private ArrayList<String> input;

    private XSSFWorkbook wb_gui, wb_bereiter;
    private XSSFSheet sheet;
    private Map<String, FormulaEvaluator> wbs;
    private FormulaEvaluator fe_gui, fe_bereiter;
    private Cell cell;
    private Row row;
    private CellReference ref;
    private int col, filenames;

    private ArrayList<String> content;

    public ExcelReader(File gui_export, File bereiter, String col_name, String sheet_name) throws IOException {

        this.content = new ArrayList<String>();
        this.gui_export = gui_export;
        this.bereiter = bereiter;
        this.filenames = 0;
        this.input = new ArrayList<String>();

        this.wb_bereiter = new XSSFWorkbook(new FileInputStream(this.bereiter));
        this.fe_bereiter = wb_bereiter.getCreationHelper().createFormulaEvaluator();

        this.wb_gui = new XSSFWorkbook(new FileInputStream(this.gui_export));
        this.fe_gui = wb_gui.getCreationHelper().createFormulaEvaluator();

        wbs = new HashMap<String, FormulaEvaluator>();
        wbs.put(bereiter.getName(), fe_bereiter);
        wbs.put(gui_export.getName(), fe_gui);
        fe_bereiter.setupReferencedWorkbooks(wbs);
        //fe_bereiter.evaluateAll();

        this.sheet = wb_bereiter.getSheet(sheet_name);
        this.col = getRowIndexByName(col_name);
        System.out.println("Col:" + col);
        System.out.println("Sheet: " + sheet.getSheetName());
        //this.col = 1;
        //this.path = path;
        //this.wb = new XSSFWorkbook(new FileInputStream(this.path));
        //this.sheet = wb.getSheet(tab);
        //this.column = getRowIndexByName(col);
        //if(this.col == -1) return;
        this.shemes = new ArrayList<NameSheme>();
    }

    public void formContent(){

        int i = 0;
        for(Row r : sheet){

            Cell c = r.getCell(col);
            if(c != null) {
                switch (c.getCellType()) {
                    case STRING:
                        if (c.getStringCellValue().equals("")) return;
                        break;
                    case FORMULA:
                        if(c.getStringCellValue().equals("")) {
                            System.out.println("ended after: " + i + "lines :D");
                            filenames = i -1;
                            return;
                        }
                        input.add(c.getStringCellValue());
                        System.out.println(c.getStringCellValue());
                        i++;
                        break;
                }
            } else return;
        }
    }

    public void sortInput(){

        Collections.sort(input);
        System.out.println(input);
        CreateNameShemeList();
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

    private void CreateNameShemeList(){

        for(String s : input){
            shemes.add(new NameSheme(s));
        }
    }
    public NameSheme getPivot(){
        return shemes.get(0);
    }

    public ArrayList<NameSheme> getShemes(){
        return shemes;
    }
    public ArrayList<NameSheme> trimShemes(int pivot){

        ArrayList<NameSheme> trimmed = new ArrayList<NameSheme>();

        for(NameSheme ns : shemes){

            if(ns.getIdAsInt() > pivot){

                trimmed.add(ns);
            }
        }
        System.out.println("Removed " + (shemes.size() - trimmed.size()) + " Items from NameShemeList");
        shemes = trimmed;
        return trimmed;
    }
}
