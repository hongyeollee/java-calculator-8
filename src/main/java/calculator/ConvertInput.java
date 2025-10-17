package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertInput {

    private static final String DEFAULT_SEPARATOR = "[,:]";
    private static final String CUSTOM_STARTER = "//";
    private static final Pattern CUSTOM_HEADER = Pattern.compile("^//(.+?)(\\r?\\n)(.*)$");
    private static final Pattern BASIC_PATTERN = Pattern.compile("^\\d+(?:[,:]\\d+)*$");

    private static String[] splitBySeparator(String input) {
        if (input == null || input.isBlank()) {
            return new String[0];
        }

        if (input.startsWith(CUSTOM_STARTER)) {
            String normalized = normalizeNewline(input);
            Matcher matcher = CUSTOM_HEADER.matcher(normalized);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(
                        "커스텀 구분자 형식 오류: \"//<구분자>\\n<숫자들>\" 형태여야 합니다. 예) //;\\n1;2;3"
                );
            }

            String customHeader = matcher.group(1);
            String numbers = matcher.group(3);

            if (numbers.isBlank()) {
                return new String[0];
            }
            if (customHeader.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 1글자여야 합니다.");
            }
            return numbers.split(Pattern.quote(customHeader));
        }

        return input.split(DEFAULT_SEPARATOR);
    }

    private static String normalizeNewline(String s) {
        return s.replace("\\r\\n", "\r\n")
                .replace("\\n", "\n");
    }

    public static int[] toNumberArray(String input) {
        String[] toArrayInput = splitBySeparator(input);

        if (toArrayInput.length == 0) {
            return new int[0];
        }

        int[] numbers = new int[toArrayInput.length];

        for (int i = 0; i < toArrayInput.length; i++) {
            String value = toArrayInput[i].strip();

            if (value.isEmpty()) {
                throw new IllegalArgumentException("잘못된 형식의 값이 있습니다.");
            }

            int number = Integer.parseInt(value);

            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }

            numbers[i] = number;
        }
        return numbers;
    }
}
