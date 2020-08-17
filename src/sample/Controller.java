package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //MEMBER
    private Stage s;
    private double pos_x, pos_y;
    private NavDescManager navdesc;
    private DescriptionContentPackage dcp_settings, dcp_pdfnamer, dcp_alfrescoimport;

    @FXML private ToggleButton cmd_nav_settings, cmd_nav_pdfnamer, cmd_nav_alfrescoimport,
            cmd_settings_description_allgemein, cmd_settings_description_pdfnamer,
            cmd_settings_description_alfrescoimport, cmd_pdfnamer_description_scans, cmd_pdfnamer_description_excel,
            cmd_pdfnamer_description_rename, cmd_alfrescoimport_description_import;
    @FXML private Button cmd_settings_allgemein_settings_path, cmd_settings_allgemein_log_path;
    @FXML private VBox pan_settings_description, pan_pdfnamer_description, pan_alfrescoimport_description;
    @FXML private Pane pan_settings_allgemein_content, pan_settings_pdfnamer_content,
            pan_settings_alfrescoimport_content, pan_pdfnamer_scans_content, pan_pdfnamer_excel_content,
            pan_pdfnamer_rename_content, pan_alfrescoimport_import_content;
    @FXML private ChoiceBox cb_settings_pdfnamer_billtype, cb_settings_alfrescoimport_trate;

    @FXML private VBox vbx_pdfnamer_scans_content;

    //INIT
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        cb_settings_alfrescoimport_trate.getItems().add("10");
        cb_settings_alfrescoimport_trate.getItems().add("50");
        cb_settings_alfrescoimport_trate.getItems().add("100");

        cb_settings_pdfnamer_billtype.getItems().add("ER");
        cb_settings_pdfnamer_billtype.getItems().add("KK");

        //		Working Example for Automated Buttons

        for(int i = 0; i < 100; i++) {
            ToggleButton copybutton = new ToggleButton("YOUR PDF COULD BE RENAMED HERE" + (i + 1));
            copybutton.setMaxSize(270,40);
            copybutton.setMinSize(270,40);
            copybutton.setPrefSize(270,40);
            copybutton.setId("testcopybutton");
            vbx_pdfnamer_scans_content.getChildren().add(copybutton);
        }
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

    public void VBoxClicked(){

    }

    //CONTENT - PDFNAMER - EXCEL

    //CONTENT - PDFNAMER - RENAME

    //CONTENT - ALFRESCOIMPORT - IMPORT
}
