//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Pathfinder;

import Entity.Pokemon;
import TileMap.TileMap;

public interface AStarHeuristic {
    float getCost(TileMap var1, Pokemon var2, int var3, int var4, int var5, int var6);
}
