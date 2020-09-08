package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.util.Pair;
import java.io.File;
import java.util.ArrayList;

public class GUIImportScans extends ImportManager{

    private ScrollableVBoxManager<File> vb;
    private boolean isLinked;
    private ToggleGUI tgui;

    public GUIImportScans(Button tb_del, CheckBox sa, CheckBox da, ScrollableVBoxManager vb){

        this.tgui = new ToggleGUI(sa, da);
        this.vb = vb;
        this.isLinked = false;
        super.del = tb_del;
        this.vb.addDelButton(super.del);
        super.cbx_sa = sa;
        super.cbx_sa.setDisable(true);
        super.cbx_da = da;
        super.cbx_da.setDisable(true);
    }

    public void setLinked(){

        isLinked = true;
    }

    public boolean isLinked() {
        return isLinked;
    }

    @Override
    public void selectAll() {
        if(isLinked) {
            vb.selectAll();
            tgui.toggle(cbx_sa);
            del.setDisable(false);
        }
    }

    @Override
    public void deselectAll() {
        if(isLinked) {
            vb.deselectAll();
            tgui.toggle(cbx_da);
            del.setDisable(true);
        }
    }

    @Override
    public void removeSelected() {
        if(isLinked)
        vb.removeSelected();
        if(vb.getLink().size() == 0) {

            disableGUI();
            isLinked = false;
            del.setDisable(true);
        }
    }

    @Override
    public void enableGUI() {

        cbx_sa.setDisable(false);
        cbx_da.setDisable(false);
    }

    private void disableGUI(){

        cbx_da.setDisable(true);
        cbx_sa.setDisable(true);
    }

    public ArrayList<File> getSelectedData(){

        if(isLinked) {
            ArrayList<File> ret = new ArrayList<File>();
            for (Pair<File, ToggleButton> p : vb.getLink()) {

                if (p.getValue().isSelected()) ret.add(p.getKey());
            }
            return ret;
        } return null;
    }
}
