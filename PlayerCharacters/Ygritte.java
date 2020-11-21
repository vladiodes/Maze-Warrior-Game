package PlayerCharacters;

import GameUtilities.Experience;
import GameUtilities.Health;
import GameUtilities.Position;
import Tiles.Hunter;

public class Ygritte extends Hunter {
    public Ygritte(Position position) {
        super('@',position,"Ygritte",new Health(220,220),30,2,new Experience(0,50),6);
    }
}
