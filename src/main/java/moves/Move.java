package moves;

public interface Move {
    boolean isValid();
    boolean beats(Move other);
}
