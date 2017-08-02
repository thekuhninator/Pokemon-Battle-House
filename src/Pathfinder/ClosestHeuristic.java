//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Pathfinder;

import Entity.Pokemon;
import TileMap.TileMap;

public class ClosestHeuristic implements AStarHeuristic {
    public ClosestHeuristic() {
    }

    public float getCost(TileMap map, Pokemon mover, int x, int y, int tx, int ty) {
        float dx = (float)(tx - x);
        float dy = (float)(ty - y);
        float result = (float)Math.sqrt((double)(dx * dx + dy * dy));
        return result;
    }
}
