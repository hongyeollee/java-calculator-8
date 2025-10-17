package calculator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ConvertInput 클래스 테스트(클래스 내부의 private 메소드 private 메소드 splitBySeparator, normalizeNewline 간접 검증)")
public class ConvertInputTest {

    @DisplayName("toNumberArray(String input) 메소드에서 문자열의 입력값을 계산이 가능할 수 있도록 숫자타입의 배열로 변환한다.")
    @Test
    void 문자열의_입력값을_입력하면_숫자타입의_배열로_변환한다() {
        int[] toNumberArrayCase1 = ConvertInput.toNumberArray("1,2:3");
        assertThat(toNumberArrayCase1).isEqualTo(new int[]{1, 2, 3});

        int[] toNumberArrayCase2 = ConvertInput.toNumberArray("//@\n3@4@5");
        assertThat(toNumberArrayCase2).isEqualTo(new int[]{3, 4, 5});
    }

    @DisplayName("toNumberArray(String input) 메소드에서 문자열의 입력값이 기본 구분자의 경우 빈문자열, 커스텀 구분자의 경우 //구분자\n+빈문자열인 경우에는 빈배열을 반환한다.")
    @Test
    void 문자열의_입력값을_구분자별로_구별하여_입력하면_빈배열로_반환한다() {
        int[] toNumberArrayCase1 = ConvertInput.toNumberArray("");
        assertThat(toNumberArrayCase1).isEqualTo(new int[0]);

        int[] toNumberArrayCase2 = ConvertInput.toNumberArray("//@\n");
        assertThat(toNumberArrayCase2).isEqualTo(new int[0]);
    }

    @DisplayName("toNumberArray(String input) 메소드에서 잘못된 기본 구분자 입력을 한 경우 IllegalArgumentException 을 던진다.")
    @Test
    void 잘못된_기본_구분자_입력값과_커스텀_구분자를_사용했을때_예외규칙을_던진다() {
        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("1;2,4"));

        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("/;\n1;2;3"));
    }

    @DisplayName("toNumberArray(String input) 메소드에서 커스텀 구분자 형식이 잘못된 경우 IllegalArgumentException 을 던진다")
    @Test
    void 잘못된_커스텀_구분자_형식을_사용했을때_예외규칙을_던진다() {
        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("//@\n1@2,4"));

        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("//1;2;3"));

        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("//\n1;2;3"));
    }

    @DisplayName("toNumberArray(String input) 메소드에서 정규화의 처리를 추가입력하거나 커스텀 구분자를 2개 사용한 경우 IllegalArgumentException 을 던진다")
    @Test
    void 커스텀_구분자를_2개이상_사용했을때_예외규칙을_던진다() {
        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("//!@\n1!@2!@4"));

        assertThrows(IllegalArgumentException.class,
                () -> ConvertInput.toNumberArray("//;\\\n1;2;3"));
    }

    @DisplayName("toNumberArray(String input) 메소드에서 커스텀 구분자를 사용할때 입력된 값의 중간값이 없는 경우 IllegalArgumentException 을 던진다")
    @Test
    void 커스텀_구분자_입력값의_중간값이_없을때_예외규칙을_던진다() {
        assertThrows(IllegalArgumentException.class, () -> ConvertInput.toNumberArray(("//;\n1;;3")));
    }

    @DisplayName("toNumberArray(String input) 메소드에서 계산값에 음수를 사용한 경우 IllegalArgumentException 을 던진다")
    @Test
    void 계산값에_음수를_사용했을때_예외규칙을_던진다() {
        assertThrows(IllegalArgumentException.class, () -> ConvertInput.toNumberArray(("2,10:-2")));
        assertThrows(IllegalArgumentException.class, () -> ConvertInput.toNumberArray(("//;\n1;-10;3")));
    }
}