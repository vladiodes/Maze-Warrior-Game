package EnemyCharacters;
import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Trap;
import Tiles.Player;
public class BonusTrap extends Trap {
    public BonusTrap(Position position, Player player) {
        super('B', position, "Bonus Trap", new Health(1,1), 1, 1, new Experience(250,250),  player, 1, 5);
    }
}
