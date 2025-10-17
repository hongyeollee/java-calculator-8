package calculator;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String startInputString = InputString.start(); // 프로그램 시작 및 문자열 입력 시작
        String normalizedInput = InputString.inputValue(startInputString); // 문자열 입력에 대해 입력된 값 정리
        int resultCalculate = Calculator.calculate(normalizedInput); // 정리된 값에 대한 값의 정리 및 계산
        Output.result(resultCalculate); //계산된 최종값 도출
    }
}
