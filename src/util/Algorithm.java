package util;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Algorithm {

    private String outpath;
    private File log, output;
    private int pivot;

    private VBox pdf_view, excel_view;

    private ArrayList<File> pdf;
    private ArrayList<NameSheme> excel;

    public Algorithm(VBox pdf_view, VBox excel_view) throws IOException {

        pdf = new ArrayList<File>();
        excel = new ArrayList<NameSheme>();
        this.pdf_view = pdf_view;
        this.excel_view = excel_view;
    }

    public void setPdf(ArrayList<File> setter){ this.pdf = setter; }
    public void setExcel(ArrayList<NameSheme> setter){ this.excel = setter; }

    public void showRenamePreview(){

    }
    //public void createFileStructure(File dir){
    //
    //    log = new File(dir.getPath()+"/LOG");
    //    output = new File(dir.getPath()+"/OUTPUT");
    //    if(!log.exists()) log.mkdir();
    //    else System.out.println("LOG already exists");
    //    if(!output.exists()) output.mkdir();
    //    else System.out.println("OUTPUT already exists");
    //}

    private void createFileStructure(String path){

        //log = new File(path + "/LOG");
        output = new File(path + "/FORGE");
        //if(!log.exists()) log.mkdir();
        //else System.out.println("LOG already exists");
        if(!output.exists()) output.mkdir();
        else System.out.println("FORGE already exists");
    }

    //public void startProcess() throws IOException {
    //
    //    //Calculate ProgressBarSteps
    //    int iterator = 0;
    //    double progessvalue = er.getShemes().size() / 100;
    //    //pb.setProgress(0);
    //
    //    File fcurrent = null;
    //    for(NameSheme ns : er.getShemes()){
    //
    //        iterator++;
    //        fcurrent = pr.getProcessFileById(ns.getIdAsInt());
    //        if(fcurrent != null){
    //            try {
    //
    //                PdfCopier.copyFile(fcurrent, new File(output.getPath() + "/" + ns.getName()));
    //                //pb.setProgress(iterator * progessvalue);
    //
    //            } catch (IOException io){
    //
    //                iterator--;
    //                //pb.setProgress(iterator * progessvalue);
    //                System.out.println(fcurrent.getPath() + " seems not copieable ...");
    //            }
    //        }
    //    }
    //}
}
