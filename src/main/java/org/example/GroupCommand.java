package org.example;

public enum GroupCommand {
    ADD_GUIDE,
    ADD_MEMBER,
    REMOVE_MEMBER,
    REMOVE_GUIDE,
    FIND_GUIDE,
    FIND_MEMBER;

    public static GroupCommand fromString(String input) {
        String formattedInput = input.replace(" ", "_");
        for (GroupCommand cmd : GroupCommand.values()) {
            if (cmd.name().equals(formattedInput)) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + input);
    }
}

