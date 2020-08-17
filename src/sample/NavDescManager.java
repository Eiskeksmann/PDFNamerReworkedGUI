package sample;

import javafx.scene.control.ToggleButton;
import javafx.util.Pair;

import java.util.ArrayList;

public class NavDescManager {

    private ArrayList<Pair<ToggleButton, DescriptionContentPackage>> root;

    public NavDescManager(){

        root = new ArrayList<Pair<ToggleButton, DescriptionContentPackage>>();
    }

    public void addNavDesc(ToggleButton nav, DescriptionContentPackage dcp){

        root.add(new Pair<ToggleButton, DescriptionContentPackage>(nav, dcp));
    }

    public void setSelected(ToggleButton selection){

        for(Pair<ToggleButton, DescriptionContentPackage> p: root) {

            if(p.getKey().equals(selection)){
                p.getKey().setSelected(true);
                p.getValue().setVisible();
            } else {

                p.getKey().setSelected(false);
                p.getValue().setInVisible();
            }
        }
    }
}
