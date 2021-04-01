package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.VBox;
import util.NameSheme;
import java.io.File;
import java.util.ArrayList;

public class GUIImportRename extends ImportManager{

    private GUIImportExcel gui_excel;
    private GUIImportScans gui_scans;

    public GUIImportRename(Button cmd_del, Button cmd_send, CheckBox sa, CheckBox da , VBox parent_excel, VBox parent_scans){

        System.out.println(cmd_del + "," + cmd_send  + "," + sa + "," + da + "," + parent_excel + ", " + parent_scans);
        super.del = cmd_del;
        super.send = cmd_send;
        super.cbx_sa = sa;
        super.cbx_da = da;
        super.tgui = new ToggleGUI(sa, da);
        gui_excel = new GUIImportExcel(cmd_del, cmd_send, sa, da, parent_excel);
        gui_scans = new GUIImportScans(cmd_del, cmd_send, sa, da, parent_scans);

    }

    @Override
    public void selectAll() {

        gui_excel.selectAll();
        gui_scans.selectAll();
    }

    @Override
    public void deselectAll() {

        gui_excel.deselectAll();
        gui_scans.deselectAll();
    }

    @Override
    public void removeSelected() {

        gui_excel.removeSelected();
        gui_scans.removeSelected();
    }

    @Override
    public void enableGUI() {

        gui_excel.enableGUI();
        gui_excel.enableGUI();
    }

    @Override
    public void disableGUI() {

        gui_excel.disableGUI();
        gui_scans.disableGUI();
    }

    public void sendInScans(ArrayList<File> sent){

        if(sent != null) {
            for (File f : sent)
                gui_scans.getVb().addLink(f, f.getName(), 135, 40,
                        new FontAwesomeIconView(FontAwesomeIcon.CUBE), Pos.CENTER_RIGHT, ContentDisplay.RIGHT);

            gui_scans.setLinked(true);
            if(gui_excel.isLinked) enableGUI();
        }
    }

    public void sendInNameShemes(ArrayList<NameSheme> sent) {

        if (sent != null) {
            for (NameSheme ns : sent)
                gui_excel.getVb().addLink(ns, ns.getName(), 135, 40,
                        new FontAwesomeIconView(FontAwesomeIcon.CUBES), Pos.CENTER_LEFT, ContentDisplay.LEFT);

            gui_excel.setLinked(true);
            if(gui_scans.isLinked) enableGUI();
        }

    }
}
