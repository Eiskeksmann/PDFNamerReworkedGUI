package sample;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.util.Pair;
import util.NameSheme;

import java.util.ArrayList;

public class GUIImportExcel extends ImportManager{

    private ArrayList<Pair<NameSheme, ToggleButton>> link;
    private ScrollableVBoxManager<NameSheme> vb;

    public GUIImportExcel(ToggleButton tb_del, CheckBox sa, CheckBox da ,ScrollableVBoxManager<NameSheme> vb){

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

        vb.selectAll();
    }

    @Override
    public void deselectAll() {

        vb.deselectAll();
    }

    @Override
    public void removeSelected() {

        vb.removeSelected();
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
