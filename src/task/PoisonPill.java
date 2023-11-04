package task;

import exceptions.PoisonPillException;

public class PoisonPill extends Task {
    @Override
    public void run() {
        throw new PoisonPillException();
    }
}