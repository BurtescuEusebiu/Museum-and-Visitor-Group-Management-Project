package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TourGuide implements Guide {
    private String email;

    public TourGuide(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void notify(String event, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
            writer.write("To: " + email + " ## " + event);
            writer.close();
        } catch (IOException e) {}
    }
}
