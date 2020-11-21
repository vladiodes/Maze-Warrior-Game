package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import GameUtilities.Resource;
import Tiles.Rogue;

public class Bronn extends Rogue {
    public Bronn(Position position) {
        super('@', position, "Bronn", new Health(250,250), 35, 3, new Experience(0,50),  50, new Resource(100,100));
    }
}
