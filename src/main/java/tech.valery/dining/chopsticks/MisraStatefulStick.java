package tech.valery.dining.chopsticks;

import net.jcip.annotations.GuardedBy;

public class MisraStatefulStick extends LockChopstick {

    @GuardedBy("this")
    private boolean isClean;

    public MisraStatefulStick() {
    }
}
