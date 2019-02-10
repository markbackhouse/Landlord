package players;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moves.Move;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerMove {
    private Player player;
    private Move move;
}
