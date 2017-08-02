//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package TileMap;

public class MapMaker {
    private TileMap map;
    private int tileSize;
    private int numRows;
    private int numCols;

    public MapMaker(int tileSize, int tiles) {
        this.map = new TileMap(tileSize);
        switch(tiles) {
            case 0:
                this.map.loadTiles("/Tiles/TileSet 2.png");
            default:
        }
    }

    public void makeMap(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.map.newMap(numRows, numCols);
        this.map.fillAll(0);
        this.map.floral();
        this.map.patches(4, 0, 0, numRows / 2 - 6, numCols - 1);
        this.map.patches(5, numRows / 2 + 4, 0, numRows - 1, numCols - 1);
        this.map.dirtPatches();
        this.map.cornucopia();
    }

    public TileMap getMap() {
        return this.map;
    }
}
