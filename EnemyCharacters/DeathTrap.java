package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Player;
import Tiles.Trap;

public class DeathTrap extends Trap {
    public DeathTrap(Position position, Player player) {
        super('D', position, "Death Trap", new Health(500,500), 100, 20, new Experience(250,250),  player, 1, 10);
    }
}
