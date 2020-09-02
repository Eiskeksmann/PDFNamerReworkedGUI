package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Constants;
import util.ExcelReader;
import util.NameSheme;
import util.PathReader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //MEMBER
    private File home;
    private Constants c;
    private Stage s;
    private double pos_x, pos_y;
    private NavDescManager navdesc;
    private DescriptionContentPackage dcp_settings, dcp_pdfnamer, dcp_alfrescoimport;
    private String loc;
    private PathReader pr;
    private ExcelReader er;
    private DC dc;
    private FC fc;
    private ScrollableVBoxManager<File> svm_pdf;
    private ScrollableVBoxManager<NameSheme> svm_excel;

    @FXML private ToggleButton cmd_nav_settings, cmd_nav_pdfnamer, cmd_nav_alfrescoimport,
            cmd_settings_description_allgemein, cmd_settings_description_pdfnamer,
            cmd_settings_description_alfrescoimport, cmd_pdfnamer_description_scans, cmd_pdfnamer_description_excel,
            cmd_pdfnamer_description_rename, cmd_alfrescoimport_description_import;
    @FXML private Button cmd_settings_allgemein_settings_path, cmd_settings_allgemein_log_path;
    @FXML private VBox pan_settings_description, pan_pdfnamer_description, pan_alfrescoimport_description;
    @FXML private Pane pan_settings_allgemein_content, pan_settings_pdfnamer_content,
            pan_settings_alfrescoimport_content, pan_pdfnamer_scans_content, pan_pdfnamer_excel_content,
            pan_pdfnamer_rename_content, pan_alfrescoimport_import_content;
    @FXML private ChoiceBox<String> cb_settings_pdfnamer_billtype, cb_settings_alfrescoimport_trate;

    @FXML private VBox vbx_pdfnamer_scans_content, vbx_pdfnamer_excel_content;
    @FXML private Button cmd_pdfnamer_import_scans, cmd_pdfnamer_excel_import;


    //INIT
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        c = new Constants();
        loc = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        home = new File(loc);
        dc = new DC("Folder Open", home);
        fc = new FC("File Open",c.getPdffilter(),home);
        //Navigation Logic
        dcp_settings = new DescriptionContentPackage(pan_settings_description);
        dcp_settings.addPackage(cmd_settings_description_allgemein, pan_settings_allgemein_content);
        dcp_settings.addPackage(cmd_settings_description_pdfnamer, pan_settings_pdfnamer_content);
        dcp_settings.addPackage(cmd_settings_description_alfrescoimport, pan_settings_alfrescoimport_content);
        dcp_settings.selectContent(cmd_settings_description_allgemein);

        dcp_pdfnamer = new DescriptionContentPackage(pan_pdfnamer_description);
        dcp_pdfnamer.addPackage(cmd_pdfnamer_description_scans, pan_pdfnamer_scans_content);
        dcp_pdfnamer.addPackage(cmd_pdfnamer_description_excel, pan_pdfnamer_excel_content);
        dcp_pdfnamer.addPackage(cmd_pdfnamer_description_rename, pan_pdfnamer_rename_content);
        dcp_pdfnamer.selectContent(cmd_pdfnamer_description_scans);

        dcp_alfrescoimport = new DescriptionContentPackage(pan_alfrescoimport_description);
        dcp_alfrescoimport.addPackage(cmd_alfrescoimport_description_import, pan_alfrescoimport_import_content);
        dcp_alfrescoimport.selectContent(cmd_alfrescoimport_description_import);

        navdesc = new NavDescManager();
        navdesc.addNavDesc(cmd_nav_settings, dcp_settings);
        navdesc.addNavDesc(cmd_nav_pdfnamer,dcp_pdfnamer);
        navdesc.addNavDesc(cmd_nav_alfrescoimport,dcp_alfrescoimport);
        navdesc.setSelected(cmd_nav_settings);

        //Content Initiation
        //Settings
        cb_settings_alfrescoimport_trate.getItems().add("10");
        cb_settings_alfrescoimport_trate.getItems().add("50");
        cb_settings_alfrescoimport_trate.getItems().add("100");

        cb_settings_pdfnamer_billtype.getItems().add("ER");
        cb_settings_pdfnamer_billtype.getItems().add("KK");
        cb_settings_pdfnamer_billtype.setValue("ER");
        //PDFNamer
        svm_pdf = new ScrollableVBoxManager<File>(vbx_pdfnamer_scans_content);
        svm_excel = new ScrollableVBoxManager<NameSheme>(vbx_pdfnamer_excel_content);
    }

    //GETTER - SETTER
    public void setStage(Stage s){
        this.s = s;
    }
    //TITLE
    public void hbxTitlePressed(MouseEvent e){

        pos_x = e.getSceneX();
        pos_y = e.getSceneY();
    }
    public void hbxTitleDragged(MouseEvent e){

        //Stage s = (Stage) ((Node) e.getSource()).getScene().getWindow();
        s.setX(e.getScreenX()- pos_x);
        s.setY(e.getScreenY() - pos_y);

    }
    public void cmdExitClicked(){

        s.close();
    }
    //NAV
    public void cmdNavSettingsClicked(){

        navdesc.setSelected(cmd_nav_settings);
    }
    public void cmdNavPDFNamerClicked(){

        navdesc.setSelected(cmd_nav_pdfnamer);
    }
    public void cmdNavAlfrescoImportClicked(){

        navdesc.setSelected(cmd_nav_alfrescoimport);
    }
    //DESC - SETTINGS
    public void cmdSettingsDescriptionAllgemeinClicked(){

        dcp_settings.selectContent(cmd_settings_description_allgemein);
    }
    public void cmdSettingsDescriptionPDFNamerClicked(){

        dcp_settings.selectContent(cmd_settings_description_pdfnamer);
    }
    public void cmdSettingsDescriptionAlfrescoImportClicked(){

        dcp_settings.selectContent(cmd_settings_description_alfrescoimport);
    }
    //DESC - PDFNAMER
    public void cmdPDFNamerDescriptionScansClicked(){

        dcp_pdfnamer.selectContent(cmd_pdfnamer_description_scans);
    }
    public void cmdPDFNamerDescriptionExcelClicked(){

        dcp_pdfnamer.selectContent(cmd_pdfnamer_description_excel);
    }
    public void cmdPDFNamerDescriptionRenameClicked(){

        dcp_pdfnamer.selectContent(cmd_pdfnamer_description_rename);
    }
    //DESC - ALFRESCOIMPORT
    public void cmdAlfrescoImportDescriptionImportClicked(){

        dcp_alfrescoimport.selectContent(cmd_alfrescoimport_description_import);
    }
    //CONTENT - SETTINGS - ALLGEMEIN
    public void cmdSettingsAllgemeinSettingsPathClicked(){

    }
    public void cmdSettingsAllgemeinLogPathClicked(){

    }
    public void cmdSettingsAllgemeinApplyClicked(){

    }

    //CONTENT - SETTINGS - PDFNAMER
    public void cmdSettingsPDFNamerOutputPathClicked(){

    }
    public void cmdSettingsPDFNamerApplyClicked(){

    }

    //CONTENT - SETTINGS - ALFRESCOIMPORT
    public void cmdSettingsAlfrescoImportApplyClicked(){

    }

    //CONTENT - PDFNAMER - SCAN
    public void cmdPDFNamerImportScansClicked(){

        File f = dc.getDc().showDialog(s);
        if(f != null){
            //scan_path = f.getPath();
            //txt_scan_path.setText(scan_path);
            pr = new PathReader(f,"PDF", cb_settings_pdfnamer_billtype.getValue(), "SCAN");
            if(pr.getProcces() != null && pr.getProcces().length > 0){

                for(File pdf : pr.getProcces()) svm_pdf.addLink(pdf, pdf.getName());

                //am.setScan_condition(true);
                //if(cb_scan_setouput.isSelected()) {
                //    am.setOutput_condition(true);
                //    output_path = scan_path;
                //}
                //else am.setOutput_condition(false);
                //cmd_sheet_path.setDisable(false);
            } else {

                //scan_path = "";
                System.out.println("Pfad enthält nur ungültige Dokumente");
                //lst_scan.getItems().clear();
                pr = null;
                //am.setScan_condition(false);
            }
        }
    }

    //CONTENT - PDFNAMER - EXCEL
    public void cmdPDFNamerImportExcelClicked() throws IOException {

        fc.ChangeFilter(c.getXlsfilter());
        File f1 = fc.getFc().showOpenDialog(s);
        if(f1 != null) {
            if(new File(f1.getParent() +"\\GUIExport.xlsx").exists()) {

                File f2 = new File(f1.getParent() + "\\GUIExport.xlsx");
                ExcelReader er = new ExcelReader(f2,f1, "File", cb_settings_pdfnamer_billtype.getValue());
                er.formContent();
                er.sortInput();
                for(NameSheme ns : er.getShemes()){
                    svm_excel.addLink(ns, ns.getName());
                }

            } else System.out.println("GUIExport.xlsx - not found or renamed in Directory | " + f1.getPath());
        }
        // f1 Path : C:\Users\Administrator\Desktop\TEST\GUIExport.xlsx

    }

    //CONTENT - PDFNAMER - RENAME

    //CONTENT - ALFRESCOIMPORT - IMPORT
}
