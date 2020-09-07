package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.util.Pair;
import java.io.File;
import java.util.ArrayList;

public class GUIImportScans extends ImportManager{

    private ScrollableVBoxManager<File> vb;
    private boolean isLinked;
    public GUIImportScans(ToggleButton tb_del, CheckBox sa, CheckBox da, ScrollableVBoxManager vb){

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
        if(isLinked)
        vb.selectAll();
    }

    @Override
    public void deselectAll() {
        if(isLinked)
        vb.deselectAll();
    }

    @Override
    public void removeSelected() {
        if(isLinked)
        vb.removeSelected();
    }

    @Override
    public void enableGUI() {

        cbx_sa.setDisable(false);
        cbx_da.setDisable(false);
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
