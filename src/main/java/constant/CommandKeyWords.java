package constant;

import exception.ErrorHandler;

public enum CommandKeyWords {
    LOCATION("LOCATION"), BYE("BYE"), MARKS("MARKS");
    private String value;

    /**
     * javadoc.
     *
     * @return string which shows all available command keywords.
     */
    private static String getSerializedString() {
        String keywords = "";
        int numberOfKeys = values().length;
        int index = 0;
        for (CommandKeyWords v : values()) {
            index += 1;
            String punctuation = index == numberOfKeys ? "." : ", ";
            keywords = keywords.concat(v.getValue()).concat(punctuation);
        }

        return keywords;
    }

    CommandKeyWords(String value) {
        this.value = value;
    }

    /**
     * javadoc.
     *
     * @return enum value.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

    /**
     * javadoc.
     *
     * @param value is an enum string for validating if the given value is valid.
     * @throws ErrorHandler customized error.
     */
    public static CommandKeyWords getEnum(String value) throws ErrorHandler {
        for (CommandKeyWords v : values()) {
            if (v.getValue().equalsIgnoreCase(value)) {
                return v;
            }
        }

        throw new ErrorHandler(ErrorMessage.INVALID_COMMAND + " Please starts your command with "
            + getSerializedString()
            + " (case insensitive)");
    }
}

