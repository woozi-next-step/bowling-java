package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.state.Pins;
import bowling.exception.NoActionBowlException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @DisplayName("Frames 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames).isNotNull();
    }


    @DisplayName("Frames 인스턴스가 현재 Frame 의 순서를 반환하는지 테스트")
    @Test
    void 반환_순서() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.sequence()).isEqualTo(1);
    }

    @DisplayName("Frames 인스턴스가 인덱스를 기준으로 특정 Frame 을 반환하는지 테스트")
    @Test
    void 반환_특정_frame() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.get(0).sequence()).isEqualTo(1);
    }


    @DisplayName("Frames 인스턴스 현재 frame 종료 여부 테스트")
    @Test
    void 종료() {
        // when
        Frames frames = Frames.initialize();

        // then
        assertThat(frames.isFinish()).isFalse();
    }

    @DisplayName("Frames 인스턴스가 모든 bowl() 호츌시 종료 여부 반환하는지 테스트")
    @Test
    void 투구_bowl() {
        // given
        Frames frames = Frames.initialize();

        // when
        for (int i = 0; i < 12; i++) {
            frames.bowl(Pins.valueOf(10));
        }

        // then
        assertAll(
                () -> assertThat(frames.isFinish()).isTrue(),
                () -> assertThat(frames.sequence()).isEqualTo(10)
        );
    }

    @DisplayName("Frames 인스턴스가 투구를 전부 했음에도 bowl() 호츌시 예외처리 여부 테스트")
    @Test
    void 검증_bowl() {
        // given
        Frames frames = Frames.initialize();

        // when
        while (!frames.isFinish()) {
            frames.bowl(Pins.valueOf(10));
        }

        // then
        assertThatThrownBy(() -> frames.bowl(Pins.valueOf(1)))
                .isInstanceOf(NoActionBowlException.class)
                .hasMessage("현재 상태에서는 더 이상 볼을 던질 수 없습니다.");
    }

    @DisplayName("Frames 인스턴스가 프레임별 점수를 리스트를 반환하는지 테스트")
    @Test
    void 반환_scores() {
        // given
        Frames firstFrames = Frames.initialize();
        Frames secondFrames = Frames.initialize();
        Frames thirdFrames = Frames.initialize();

        // when
        while (!firstFrames.isFinish()) {
            firstFrames.bowl(Pins.valueOf(1));
        }
        while (!secondFrames.isFinish()) {
            secondFrames.bowl(Pins.valueOf(5));
        }
        while (!thirdFrames.isFinish()) {
            thirdFrames.bowl(Pins.valueOf(10));
        }

        // when
        assertAll(
                () -> assertThat(firstFrames.scores().sum()).isEqualTo(20),
                () -> assertThat(secondFrames.scores().sum()).isEqualTo(150),
                () -> assertThat(thirdFrames.scores().sum()).isEqualTo(300)
        );

    }

}