package calculator;

import camp.nextstep.edu.missionutils.Console;

public class InputString {

    public static String start() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }

    public static String inputValue(String value) {
        if (value == null || value.isBlank()) {
            return "0";
        }
        return value;
    }
}
