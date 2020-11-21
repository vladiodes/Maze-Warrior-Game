package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Trap;
import Tiles.Player;
public class QueensTrap extends Trap {
    public QueensTrap(Position position, Player player) {
        super('Q', position, "Queen's Trap", new Health(250,250), 50, 10, new Experience(100,100),  player, 3, 7);
    }
}
