package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.PinsNullPointerException;

import java.util.Objects;

public class Spare extends Finish {

    private final Pins firstPins;
    private final Pins secondPins;

    public static final State of(final Pins firstPins, final Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    private Spare(final Pins firstPins, final Pins secondPins) {
        validateNull(firstPins, secondPins);
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    private final void validateNull(Pins firstPins, Pins secondPins) {
        if (Objects.isNull(firstPins) || Objects.isNull(secondPins)) {
            throw new PinsNullPointerException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

    @Override
    public String description() {
        return null;
    }
}