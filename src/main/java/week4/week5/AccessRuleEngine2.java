package Step_semester_3.src.main.java.week4.week5;

class AccessRuleEngine2 {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            return fieldModifier.equals("private")
                    ? "DENIED" : "ALLOWED";
        }

        if (accessorContext.equals(
                "DIFFERENT_PACKAGE")) {

            return fieldModifier.equals("public")
                    ? "ALLOWED" : "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            return (fieldModifier.equals("protected") ||
                    fieldModifier.equals("public"))
                    ? "ALLOWED" : "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            return fieldModifier.equals("public")
                    ? "ALLOWED" : "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {

        String[] words = accessorContext.toLowerCase()
                .split("_");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(
                Character.toUpperCase(word.charAt(0))
            );

            result.append(word.substring(1));
            result.append(" ");
        }

        return result.toString().trim();
    }
}
