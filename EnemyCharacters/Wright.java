package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Monster;
import Tiles.Player;

public class Wright extends Monster {
    public Wright(Position position, Player player) {
        super('z', position, "Wright", new Health(600,600), 30, 15, new Experience(100,100),  player,3);
    }
}
