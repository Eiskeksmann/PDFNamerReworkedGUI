package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.util.Pair;
import util.NameSheme;

import java.util.ArrayList;

public class GUIImportExcel extends ImportManager{

    private ArrayList<Pair<NameSheme, ToggleButton>> link;
    private ScrollableVBoxManager<NameSheme> vb;
    private ToggleGUI tgui;

    public GUIImportExcel(Button tb_del, CheckBox sa, CheckBox da , ScrollableVBoxManager<NameSheme> vb){

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

        if(isLinked) {
            vb.removeSelected();
        }
        if(vb.getLink().size() == 0) {

            disableGUI();
            isLinked = false;
            del.setDisable(true);
        }
    }

    private void disableGUI(){

        cbx_da.setDisable(true);
        cbx_sa.setDisable(true);
    }

    @Override
    public void enableGUI() {

        cbx_da.setDisable(false);
        cbx_sa.setDisable(false);
    }

    public ArrayList<NameSheme> getSelectedData(){

        ArrayList<NameSheme> ret = new ArrayList<NameSheme>();
        for(Pair<NameSheme, ToggleButton> p : vb.getLink()){

            if(p.getValue().isSelected()) ret.add(p.getKey());
        }
        return ret;
    }
}
