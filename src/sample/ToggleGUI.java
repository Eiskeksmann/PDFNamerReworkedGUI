package sample;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class ToggleGUI {

    public ArrayList<CheckBox> cbl;

    public ToggleGUI(CheckBox... cbs){

        cbl = new ArrayList<CheckBox>();
        for(CheckBox c : cbs){
            cbl.add(c);
        }
    }

    public void toggle(CheckBox selection){

        for(CheckBox c : cbl){
            if(c.isSelected() && !c.equals(selection)) c.setSelected(false);
            else if(c.equals(selection)) c.setSelected(true);
        }
    }

    public void reset(){

        for(CheckBox c : cbl){
            c.setSelected(false);
        }
    }
}
