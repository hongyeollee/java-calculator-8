package calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("InputString 클래스 테스트")
public class InputStringTest {
    @DisplayName("inputValue() 메소드에서 빈문자열, 공백 그리고 미입력시 0을 반환한다.")
    @Test
    void 빈문자열_또는_미입력시_0을_반환한다() {
        assertThat(InputString.inputValue("")).isEqualTo("0");
        assertThat(InputString.inputValue("    ")).isEqualTo("0");
        assertThat(InputString.inputValue(null)).isEqualTo("0");
    }

    @DisplayName("inputValue() 메소드에서 문자열 입력시 문자열을 반환한다.")
    @Test
    void 입력값에_문자열을_반환한다() {
        assertThat(InputString.inputValue("1,2:3")).isEqualTo("1,2:3");
        assertThat(InputString.inputValue("//@\n1@2@3")).isEqualTo("//@\n1@2@3");
    }
}
