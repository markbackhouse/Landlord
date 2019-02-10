package moves;

public class PassMove implements Move {
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean beats(Move other) {
        return false;
    }

    @Override
    public MoveType getType() {
        return MoveType.PASS;
    }
}
