package models.moves;

public interface Move {
    boolean isValid();
    boolean beats(Move other);
}
