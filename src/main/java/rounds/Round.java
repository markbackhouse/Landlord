package rounds;

import moves.Move;
import moves.MoveType;
import players.PlayerMove;

import java.util.List;

import static moves.MoveType.BOMB;
import static moves.MoveType.PASS;

public class Round {

    private MoveType roundType;
    private List<PlayerMove> playerMoves;

    public Round(MoveType roundType, List<PlayerMove> playerMoves) {
        this.roundType = roundType;
        this.playerMoves = playerMoves;
    }

    public void beginRound(PlayerMove playerMove) {
        MoveType moveType = playerMove.getMove().getType();
        if (playerMoves.isEmpty() && !moveType.equals(PASS)) {
            playerMoves.add(playerMove);
            roundType = moveType;
        }
    }

    public void playMove(PlayerMove playerMove) {
        MoveType moveType = playerMove.getMove().getType();
        if (moveType.equals(PASS) || moveType.equals(BOMB)) {
            playerMoves.add(playerMove);
        }

        if (moveType.equals(roundType)) {
            int index = playerMoves.size() - 1;
            Move previousMove = getPreviousNonPassPlayerMove(index).getMove();
            Move newMove = playerMove.getMove();

            if (newMove.beats(previousMove)) {
                playerMoves.add(playerMove);
            }
        }
    }

    private PlayerMove getPreviousNonPassPlayerMove(int index) {
        PlayerMove playerMove = playerMoves.get(index);
        if (playerMove.getMove().getType().equals(PASS)) {
            return getPreviousNonPassPlayerMove(index - 1);
        }
        return playerMove;
    }
}
