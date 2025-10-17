package calculator;

public class Calculator {

    public static int calculate(String input) {
        int[] numbers = ConvertInput.toNumberArray(input);

        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum;
    }
}
