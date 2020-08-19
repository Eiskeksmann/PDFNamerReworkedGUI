package util;

import javafx.util.Pair;
import java.util.ArrayList;

public class Constants {

    private ArrayList<Pair<String, String>> pdffilter;
    private ArrayList<Pair<String, String>> xlsfilter;

    public Constants(){

        pdffilter = new ArrayList<Pair<String, String>>();
        xlsfilter = new ArrayList<Pair<String, String>>();

        pdffilter.add(new Pair<String, String>("Scans","*.pdf"));
        xlsfilter.add(new Pair<String, String>("Excel","*.xlsx"));

    }
    public ArrayList<Pair<String, String>> getPdffilter(){
        return pdffilter;
    }
    public ArrayList<Pair<String, String>> getXlsfilter(){
        return xlsfilter;
    }


}
