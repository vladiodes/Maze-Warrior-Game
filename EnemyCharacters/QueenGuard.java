package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class QueenGuard extends Monster {
    public QueenGuard(Position position, Player player) {
        super('q', position, "Queen's Guard", new Health(400,400), 20, 15, new Experience(100,100),  player,5);
    }
}
