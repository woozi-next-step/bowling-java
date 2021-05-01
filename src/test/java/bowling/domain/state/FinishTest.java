package bowling.domain.state;

import bowling.domain.HitCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinishTest {

    @DisplayName("Finish 인스턴스가 끝났는지 반환 테스트")
    @Test
    void 검증() {
        Finish finish = new Finish() {
            @Override
            public boolean isFinish() {
                return super.isFinish();
            }
        };
        assertThat(finish.isFinish()).isTrue();
    }

    @DisplayName("Finish 인스턴스가 bowl() 호출시, 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        Finish finish = new Finish() {
            @Override
            public boolean isFinish() {
                return super.isFinish();
            }
        };
        assertThatThrownBy(()-> finish.bowl(HitCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("해당 상태에서는 더 이상 투구를 할 수 없습니다.");
    }
}