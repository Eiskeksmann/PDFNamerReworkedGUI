package sample;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import util.NameSheme;
import java.util.ArrayList;

public class GUIImportExcel extends ImportManager{

    private ScrollableVBoxManager<NameSheme> vb;

    public GUIImportExcel(Button cmd_del, Button cmd_send, CheckBox sa, CheckBox da , VBox parent){

        super.tgui = new ToggleGUI(sa, da);
        this.vb = new ScrollableVBoxManager<NameSheme>(parent, this);
        super.isLinked = false;
        super.del = cmd_del;
        super.send = cmd_send;
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
            send.setDisable(false);
        }
    }

    @Override
    public void deselectAll() {

        if(isLinked) {
            vb.deselectAll();
            tgui.toggle(cbx_da);
            del.setDisable(true);
            send.setDisable(true);
        }
    }

    @Override
    public void removeSelected() {

        if(isLinked) {
            vb.removeSelected();
            if (vb.getLink().size() == 0) {

                disableGUI();
                isLinked = false;

            } else {

                tgui.reset();
                del.setDisable(true);
                send.setDisable(true);
            }
        }
    }

    @Override
    public void enableGUI() {

        if(isLinked) {
            cbx_da.setDisable(false);
            cbx_sa.setDisable(false);
        }
    }

    @Override
    public void disableGUI() {

        cbx_da.setDisable(true);
        cbx_sa.setDisable(true);
        del.setDisable(true);
        send.setDisable(true);
    }

    public ArrayList<NameSheme> sendSelected(){

        return vb.getSelectedItems();
    }

    public ScrollableVBoxManager<NameSheme> getVb(){
        return this.vb;
    }

    public ArrayList<NameSheme> getSelectedData(){

        ArrayList<NameSheme> ret = new ArrayList<NameSheme>();
        for(Pair<NameSheme, ToggleButton> p : vb.getLink()){

            if(p.getValue().isSelected()) ret.add(p.getKey());
        }
        return ret;
    }
}
