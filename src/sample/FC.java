package sample;

import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;

public class FC {

    private FileChooser fc;

    public FC(String title, ArrayList<Pair<String, String>> filter, File f){

        fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(f);
        fc.getExtensionFilters().clear();
        for(Pair<String, String> p : filter){
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(p.getKey(), p.getValue()));
        }
    }

    public FileChooser getFc() {
        return fc;
    }
    public void ChangeFilter(ArrayList<Pair<String, String>> f){
        fc.getExtensionFilters().clear();
        for(Pair<String, String> p : f){
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(p.getKey(), p.getValue()));
        }
    }
    public void ChangeTitle(String t){
        fc.setTitle(t);
    }
}
