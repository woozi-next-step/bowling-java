package bowling.domain;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public final class FinalFrameOpportunity {

    private static final int START = 0;
    private static final int END = 3;

    private final int opportunity;

    private FinalFrameOpportunity(final int opportunity) {
        this.opportunity = opportunity;
    }

    public static final FinalFrameOpportunity initialize() {
        return new FinalFrameOpportunity(START);
    }

    public final boolean isFinish() {
        if (opportunity == END) {
            return TRUE;
        }
        return FALSE;
    }
}
