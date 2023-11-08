package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @DisplayName("입력한 금액을 통해 로또를 몇장 살 수 있는지 반환한다.")
    @Test
    void countTickets(){
        //given
        Game game = new Game(List.of(1, 2, 3, 4, 5, 6),7);
        //when
        assertThat(16).isEqualTo(game.countTickets(16000));
        //then
    }

    @DisplayName("입력한 금액이 1000으로 나누어떨어지지 않으면 예외를 발생시킨다.")
    @Test
    void countTicketsException(){
        //given
        Game game = new Game(List.of(1, 2, 3, 4, 5, 6),7);
        //when
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> game.countTickets(13500));

        assertThat(e.getMessage()).isEqualTo("[ERROR]1,000원 단위로만 입력가능합니다.");
    }

    @DisplayName("당첨 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createGameByOverSize() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 6, 7),5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createGameByDuplicatedNumber() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 5),6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 미리 입력 받은 6자리 중 포함된다면 예외가 발생한다.")
    @Test
    void validateDuplicateBonusNumber() {
        assertThatThrownBy(() -> new Game(List.of(1, 2, 3, 4, 5, 6),6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}