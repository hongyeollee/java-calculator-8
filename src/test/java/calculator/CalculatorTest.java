package calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Calculator 클래스 테스트")
public class CalculatorTest {
    @DisplayName("calculate(String input) 메소드에서 기본 구분자를 통해 변환된 숫자 2,3,4의 총합인 9을 반환한다.")
    @Test
    void 도출된_숫자의_총합을_계산한다_베이직케이스() {
        int sumCase1 = Calculator.calculate("2,3:4");
        assertThat(sumCase1).isEqualTo(9);
    }

    @DisplayName("calculate(String input) 메소드에서 커스텀 구분자를 통해 변환된 숫자 1,2,3의 총합인 6을 반환한다.")
    @Test
    void 도출된_숫자의_총합을_계산한다_커스텀케이스() {
        int sumCase2 = Calculator.calculate("//;\n1;2;3");
        assertThat(sumCase2).isEqualTo(6);
    }

    @Test
    @DisplayName("calculate(String input) 메소드에서 계산하려는 문자열의 값이 빈문자인 경우는 총합인 0을 반환한다.")
    void 기본_구분자와_커스텀_구분자_문자열의_계산값_빈입력_0() {
        assertThat(Calculator.calculate("")).isEqualTo(0);
        assertThat(Calculator.calculate("//;\n")).isEqualTo(0);
    }
}
