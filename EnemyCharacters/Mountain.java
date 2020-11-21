package EnemyCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Boss;
import Tiles.Monster;
import Tiles.Player;

public class Mountain extends Boss {
    public Mountain(Position position, Player player) {
        super('M',position,"The Mountain",new Health(1000,1000),60,25,new Experience(500,500),player,6,3);
    }
}
