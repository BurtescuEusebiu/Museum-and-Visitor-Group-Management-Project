package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddGuide implements Command {
    private Group group;
    private Professor professor;
    private String path;
    private Museum museum;

    public AddGuide(Museum museum, Group group, Professor guide, String outPath) {
        this.group = group;
        this.professor = guide;
        this.path = outPath;
        this.museum = museum;
    }

    @Override
    public void execute() {
        group.addGuide(professor);
        TourGuide guide = new TourGuide(professor.getEmail());
        if(museum != null) museum.addGuide(guide);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer.write(group.getMuseumCode() + " ## "+ group.getTimetable() + " ## " + "new guide: " + professor.toString() + "\n");
            writer.close();
        }
        catch (IOException e) {};
    }
}
