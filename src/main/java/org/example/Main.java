package org.example;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void museumsReader(Database db, String inPath, String outPath) throws IOException {
        Scanner sc = new Scanner(new File(inPath));
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            try {String[] tokens = line.split("\\|");
                Location location = new Location.LocationBuilder(tokens[3],Integer.parseInt(tokens[16]))
                        .setLocality(tokens[4])
                        .setAdminUnit(tokens[3])
                        .setAddress(tokens[6])
                        .setLatitude(Integer.parseInt(tokens[18].replace(",","")))
                        .setLongitude(Integer.parseInt(tokens[19].replace(",","")))
                        .build();
                Person manager = null;
                if(tokens[13] != "") {
                    String[] name = tokens[13].split(" ");
                    manager = new Director(name[1],name[0],"director");
                }
                Museum.Builder builder = new Museum.Builder(tokens[2], Long.parseLong(tokens[1]), Long.parseLong(tokens[14]), location);

                if (!tokens[9].isEmpty()) builder.setFax(tokens[9]);
                if (!tokens[12].isEmpty()) builder.setEmail(tokens[12]);
                if (!tokens[8].isEmpty()) builder.setPhoneNumber(tokens[8]);
                if (!tokens[11].isEmpty()) builder.setUrl(tokens[11]);
                if (!tokens[15].isEmpty()) builder.setProfile(tokens[15]);
                if (!tokens[10].isEmpty()) builder.setFoundingYear(Integer.parseInt(tokens[10]));
                if(manager != null) builder.setManager(manager);

                Museum museum = builder.build();
                Command cmd = new AddMuseum(db.getMuseums(),museum,outPath);
                cmd.execute();
            }
            catch (Exception e){
                BufferedWriter bw = new BufferedWriter(new FileWriter(outPath,true));
                bw.write("Exception: Data is broken. ## (" + line +")\n");
                bw.close();
            }
        }
    }

    public static void groupReader(Database db, String inPath, String outPath) throws IOException {
        Scanner sc = new Scanner(new File(inPath));
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] tokens = line.split("\\|");
            Integer code = Integer.parseInt(tokens[9]);
            String timetable = tokens[10];
            GroupCommand groupCommand = GroupCommand.fromString(tokens[0]);
            Command cmd = null;
            switch (groupCommand) {
                case ADD_GUIDE:
                    if (tokens[3].equals("profesor")) {
                        Group mainGroup = db.getMainGroup(code, timetable);
                        Person guide = new Professor(tokens[1], tokens[2], tokens[8]);
                        guide.setAge(Integer.parseInt(tokens[4]));
                        if (!tokens[5].isEmpty()) guide.setEmail(tokens[5]);
                        ((Professor) guide).setSchool(tokens[6]);
                        ((Professor) guide).setExperience(Integer.parseInt(tokens[7]));
                        cmd = new AddGuide(db.getMuseum(code), mainGroup, (Professor) guide, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            if (e instanceof GuideExistsException)
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(new guide: " + mainGroup.getGuide().toString() + ")\n");
                            else
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(new guide: " + guide.toString() + ")\n");
                            bw.close();
                        }
                    } else {
                        Person guide = new Student(tokens[1], tokens[2], tokens[8]);
                        guide.setAge(Integer.parseInt(tokens[4]));
                        if (!tokens[5].isEmpty()) guide.setEmail(tokens[5]);
                        ((Student) guide).setSchool(tokens[6]);
                        ((Student) guide).setStudyYear(Integer.parseInt(tokens[7]));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                        bw.write(code + " ## " + timetable + " ## " + "GuideTypeException: Guide must be a professor." + " ## " + "(new guide: " + guide.toString() + ")\n");
                        bw.close();
                    }
                    break;
                case ADD_MEMBER:
                    if (tokens[3].equals("student")) {
                        Person member = new Student(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        Group mainGroup = db.groupExists(code, timetable);
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Student) member).setSchool(tokens[6]);
                        ((Student) member).setStudyYear(Integer.parseInt(tokens[7]));
                        cmd = new AddMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            bw.write(code + " ## " + timetable + " ## " +
                                    e.getLocalizedMessage() + " ## " + "(new member: " + member.toString() + ")\n");
                            bw.close();
                        }

                    } else {
                        Person member = new Professor(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        Group mainGroup = db.groupExists(code, timetable);
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Professor) member).setSchool(tokens[6]);
                        ((Professor) member).setExperience(Integer.parseInt(tokens[7]));
                        cmd = new AddMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            bw.write(code + " ## " + timetable + " ## " +
                                    e.getLocalizedMessage() + " ## " + "(new member: " + member.toString() + ")\n");
                            bw.close();
                        }
                    }

                    break;
                case REMOVE_GUIDE:
                    Group mainGroup = db.groupExists(code, timetable);
                    cmd = new RemoveGuide(db.getMuseum(code),mainGroup, outPath);
                    try {
                        cmd.execute();
                    } catch (Exception e) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                        bw.write(code + " ## " + timetable + " ## " +
                                e.getLocalizedMessage() + " ## " + "(removed member: " + mainGroup.getGuide().toString() + ")\n");
                        bw.close();
                    }
                    break;
                case REMOVE_MEMBER:
                    if (tokens[3].equals("student")) {
                        mainGroup = db.groupExists(code, timetable);
                        Person member = new Student(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Student) member).setSchool(tokens[6]);
                        ((Student) member).setStudyYear(Integer.parseInt(tokens[7]));
                        cmd = new RemoveMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            if (e instanceof PersonNotExistsException) {
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(" + member + ")\n");
                            } else {
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(removed member: " + member + ")\n");
                            }
                            bw.close();
                        }
                    } else {
                        Person member = new Professor(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        mainGroup = db.groupExists(code, timetable);
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Professor) member).setSchool(tokens[6]);
                        ((Professor) member).setExperience(Integer.parseInt(tokens[7]));
                        cmd = new RemoveMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            if (e instanceof PersonNotExistsException) {
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(" + member + ")\n");
                            } else {
                                bw.write(code + " ## " + timetable + " ## " +
                                        e.getLocalizedMessage() + " ## " + "(removed member: " + member + ")\n");
                            }
                            bw.close();
                        }
                    }
                    break;
                case FIND_GUIDE:
                    if (tokens[3].equals("profesor")) {
                        mainGroup = db.getMainGroup(code, timetable);
                        Person guide = new Professor(tokens[1], tokens[2], tokens[8]);
                        guide.setAge(Integer.parseInt(tokens[4]));
                        if (!tokens[5].isEmpty()) guide.setEmail(tokens[5]);
                        ((Professor) guide).setSchool(tokens[6]);
                        ((Professor) guide).setExperience(Integer.parseInt(tokens[7]));
                        cmd = new FindGuide(mainGroup, outPath, (Professor) guide);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            bw.write(code + " ## " + timetable + " ## " +
                                    e.getLocalizedMessage() + " ## " + "(find guide: " + guide.toString() + ")\n");
                            bw.close();
                        }
                    } else {
                        Person guide = new Student(tokens[1], tokens[2], tokens[8]);
                        guide.setAge(Integer.parseInt(tokens[4]));
                        if (!tokens[5].isEmpty()) guide.setEmail(tokens[5]);
                        ((Student) guide).setSchool(tokens[6]);
                        ((Student) guide).setStudyYear(Integer.parseInt(tokens[7]));
                        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                        bw.write(code + " ## " + timetable + " ## " + "GuideTypeException: Guide must be a professor." + " ## " + "(new guide: " + guide.toString() + ")\n");
                        bw.close();
                    }
                    break;
                case FIND_MEMBER:
                    if (tokens[3].equals("student")) {
                        Person member = new Student(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        mainGroup = db.groupExists(code, timetable);
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Student) member).setSchool(tokens[6]);
                        ((Student) member).setStudyYear(Integer.parseInt(tokens[7]));
                        cmd = new FindMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            bw.write(code + " ## " + timetable + " ## " +
                                    e.getLocalizedMessage() + " ## " + "(new member: " + member.toString() + ")\n");
                            bw.close();
                        }

                    } else {
                        Person member = new Professor(tokens[1], tokens[2], tokens[8]);
                        member.setAge(Integer.parseInt(tokens[4]));
                        mainGroup = db.groupExists(code, timetable);
                        if (!tokens[5].isEmpty()) member.setEmail(tokens[5]);
                        ((Professor) member).setSchool(tokens[6]);
                        ((Professor) member).setExperience(Integer.parseInt(tokens[7]));
                        cmd = new FindMember(mainGroup, member, outPath);
                        try {
                            cmd.execute();
                        } catch (Exception e) {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath, true));
                            bw.write(code + " ## " + timetable + " ## " +
                                    e.getLocalizedMessage() + " ## " + "(new member: " + member.toString() + ")\n");
                            bw.close();
                        }
                    }
                    break;
            }

        }
    }

    public static void eventReader(Database db, String inPath, String outPath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(inPath));
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String [] tokens = line.split("\\|");
            Integer code = Integer.valueOf(tokens[1]);
            Museum museum = db.getMuseum(code);
            Command cmd = new AddEvent(museum,outPath,tokens[2]);
            cmd.execute();
        }
    }
    public static void main(String[] args) throws IOException {
        Database db = Database.getInstance();
        String type = args[0];
        if(type.equals(PathTypes.MUSEUMS.toString().toLowerCase())) {
            String inPath = args[1] + ".in";
            String outPath = args[1] + ".out";
            museumsReader(db,inPath,outPath);
        }
        else if(type.equals(PathTypes.GROUPS.toString().toLowerCase())) {
            String inPath = args[1] + ".in";
            String outPath = args[1] + ".out";
            groupReader(db,inPath,outPath);
        }
        else if(type.equals((PathTypes.LISTENER.toString().toLowerCase()))) {
            String museumsIn = args[1] + ".in";
            String museumsOut = args[1] + ".out";
            String groupsIn = args[2] + ".in";
            String groupsOut = args[2] + ".out";
            String eventsIn = args[3] + ".in";
            String eventsOut = args[3] + ".out";
            museumsReader(db,museumsIn,museumsOut);
            groupReader(db,groupsIn,groupsOut);
            eventReader(db,eventsIn,eventsOut);
        }
        db.resetDatabase();
    }
}
