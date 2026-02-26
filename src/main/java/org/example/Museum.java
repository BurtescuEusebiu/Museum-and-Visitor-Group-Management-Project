package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Museum {
    private String name;
    private List<TourGuide> registeredGuides = new ArrayList<>();
    private long code;
    private long supervisorCode;
    private Location location;

    private Person manager;
    private Integer foundingYear;
    private String phoneNumber;
    private String fax;
    private String email;
    private String url;
    private String profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Museum museum = (Museum) o;
        return code == museum.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    private Museum(Builder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.phoneNumber = builder.phoneNumber;
        this.fax = builder.fax;
        this.email = builder.email;
        this.url = builder.url;
        this.profile = builder.profile;
    }

    public long getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public void addGuide(TourGuide guide) {
        registeredGuides.add(guide);
    }

    public TourGuide findTourGuide(String mail) {
        return registeredGuides.stream()
                .filter(guide -> guide.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void removeGuide(String mail) {
        TourGuide guide = findTourGuide(mail);
        registeredGuides.remove(guide);
    }

    public void notify(String message, String path) {
        for (int i = registeredGuides.size() - 1; i >= 0; i--) {
            Guide guide = registeredGuides.get(i);
            guide.notify("Message: " + this.name + " (" + this.code + ") " + message + "\n", path);
        }

    }

    public static class Builder {
        private final String name;
        private final long code;
        private final long supervisorCode;
        private final Location location;

        private Person manager;
        private Integer foundingYear;
        private String phoneNumber;
        private String fax;
        private String email;
        private String url;
        private String profile;

        public Builder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        public Builder setManager(Person manager) {
            this.manager = manager;
            return this;
        }

        public Builder setFoundingYear(Integer foundingYear) {
            this.foundingYear = foundingYear;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setFax(String fax) {
            this.fax = fax;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Museum build() {
            return new Museum(this);
        }
    }
}