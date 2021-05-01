package bowling.domain;

import bowling.domain.state.State;

import static bowling.util.BowlingFixture.FRAME_START_INDEX;

public class NormalFrame implements Frame {

    private State state;
    private Frame next;
    private final int index;

    public NormalFrame(final int index) {
        this.state = State.initialize();
        this.next = Frame.initialize();
        this.index = index;
    }

    public static final Frame initialize() {
        return new NormalFrame(FRAME_START_INDEX);
    }

    public static final Frame from(final int index) {
        return new NormalFrame(index);
    }

    @Override
    public final Frame bowl(final HitCount hitCOunt) {
        state = state.bowl(hitCOunt);
        return this;
    }

}