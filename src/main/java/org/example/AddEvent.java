package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddEvent implements Command {
    private Museum museum;
    private String path;
    private String message;

    public AddEvent(Museum museum, String path, String message) {
        this.museum = museum;
        this.path = path;
        this.message = message;
    }

    @Override
    public void execute() {
        museum.notify(message,path);
    }
}
