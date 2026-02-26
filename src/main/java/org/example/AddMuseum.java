package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AddMuseum implements Command {
    private Set<Museum> museums;
    private Museum museum;
    private String path;

    public AddMuseum(Set<Museum> museums, Museum museum, String path) {
        this.museums = museums;
        this.museum = museum;
        this.path = path;
    }

    @Override
    public void execute() {
        museums.add(museum);
        try {BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write((int)museum.getCode() + ": " + museum.getName() + "\n");
            writer.close();
        }
        catch (IOException e) {};
    }
}
