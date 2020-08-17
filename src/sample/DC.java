package sample;

import javafx.stage.DirectoryChooser;

import java.io.File;

public class DC {

    private DirectoryChooser dc;

    public DC(String title, File ini){
        this.dc = new DirectoryChooser();
        this.dc.setInitialDirectory(ini);
        this.dc.setTitle(title);
    }

    public DirectoryChooser getDc(){
        return dc;
    }
}
