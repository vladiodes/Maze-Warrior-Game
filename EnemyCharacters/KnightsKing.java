package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Boss;
import Tiles.Monster;
import Tiles.Player;

public class KnightsKing extends Boss {
    public KnightsKing(Position position, Player player) {
        //frequency wasn't mentioned - so came up on my own
        super('K', position, "Night's King", new Health(5000,5000), 300, 150, new Experience(5000,5000),  player,8,3);
    }
}
