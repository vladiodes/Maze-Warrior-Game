package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import GameUtilities.Resource;
import Tiles.Mage;

public class Thoros extends Mage {
    public Thoros(Position position) {
        super('@', position, "Thoros of Myr", new Health(250,250), 25, 4, new Experience(0,50),  4, new Resource(150/4,150), 20, 20, 3);
    }
}
