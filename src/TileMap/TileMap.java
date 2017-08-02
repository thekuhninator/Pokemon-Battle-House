//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package TileMap;

import Entity.Pokemon;
import Pathfinder.Path;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileMap {
    private double x;
    private double y;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    private double tween;
    public static final int GRASS = 0;
    public static final int DIRT = 1;
    public static final int LAKE = 2;
    public static final int DESERT = 3;
    public static final int STONE = 4;
    public static final int PATH = 5;
    public static final int BEACH = 6;
    public static final int FLORAL = 7;
    private int[][] collision;
    private int[][] map;
    private int[][] depth;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    public static final int LEDGE = 2;
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;
    private float alpha;
    private boolean visible;
    private double visibleSpeed;
    public static final int BACKGROUND = 0;
    public static final int FOREGROUND = 1;
    private Path path;
    private BufferedImage[] arrows;
    private int[] arrowPath;
    private boolean drawPath;
    private boolean[][] possiblePaths;
    private boolean drawPossiblePaths;
    private boolean[][] visited;
    private boolean drawing;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        this.numRowsToDraw = 720 / tileSize + 2;
        this.numColsToDraw = 1280 / tileSize + 2;
        this.tween = 1.0D;
        this.numRows = 1;
        this.numCols = 2;
        this.visible = true;
        this.alpha = 1.0F;
        this.visibleSpeed = 0.05D;
        this.visited = new boolean[100][100];
        this.drawPossiblePaths = false;

        try {
            BufferedImage spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Images/SpriteSheets/Arrow-SpriteSheet128.png"));
            int numArrows = spriteSheet.getWidth() / tileSize;
            this.arrows = new BufferedImage[numArrows];

            for(int x = 0; x < numArrows; ++x) {
                this.arrows[x] = spriteSheet.getSubimage(x * tileSize, 0, tileSize, tileSize);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public void setVisible(boolean b) {
        this.visible = b;
    }

    public void loadTiles(String s) {
        try {
            BufferedImage tileset = ImageIO.read(this.getClass().getResourceAsStream(s));
            this.numTilesAcross = tileset.getWidth() / this.tileSize;
            int numRows = 3;
            int numCols = this.numTilesAcross;
            this.tiles = new Tile[numRows][numCols];

            for(int j = 0; j < numRows; ++j) {
                for(int i = 0; i < numCols; ++i) {
                    this.tiles[j][i] = new Tile(tileset.getSubimage(i * this.tileSize, j * this.tileSize, this.tileSize, this.tileSize), 0);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.numTilesAcross = this.tiles[0].length;
    }

    public void loadTileset(BufferedImage bi) {
        this.tileset = bi;
        this.numTilesAcross = this.tiles[0].length;
    }

    public void loadMap(String s) {
        if(s.substring(s.length() - 3) == "tme") {
            try {
                InputStream in = this.getClass().getResourceAsStream(s);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                br.readLine();
                br.readLine();
                Integer.parseInt(br.readLine());
                this.numCols = Integer.parseInt(br.readLine());
                this.numRows = Integer.parseInt(br.readLine());
                this.map = new int[this.numRows][this.numCols];
                this.collision = new int[this.numRows][this.numCols];
                this.depth = new int[this.numRows][this.numCols];
                this.width = this.numCols * this.tileSize;
                this.height = this.numRows * this.tileSize;
                this.xmax = 0;
                this.xmin = 1280 - this.width;
                this.ymax = 720 - this.height;
                this.ymin = 0;
                String delims = "\\s+";

                int row;
                String line;
                String[] tokens;
                int col;
                for(row = 0; row < this.numRows; ++row) {
                    line = br.readLine();
                    tokens = line.split(delims);

                    for(col = 0; col < this.numCols; ++col) {
                        this.map[row][col] = Integer.parseInt(tokens[col]);
                    }
                }

                for(row = 0; row < this.numRows; ++row) {
                    line = br.readLine();
                    tokens = line.split(delims);

                    for(col = 0; col < this.numCols; ++col) {
                        this.collision[row][col] = Integer.parseInt(tokens[col]);
                    }
                }

                for(row = 0; row < this.numRows; ++row) {
                    line = br.readLine();
                    tokens = line.split(delims);

                    for(col = 0; col < this.numCols; ++col) {
                        this.depth[row][col] = Integer.parseInt(tokens[col]);
                    }
                }
            } catch (Exception var10) {
                System.out.println("Could not load map: " + s);
                var10.printStackTrace();
            }
        } else {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(s)));
                Integer.parseInt(br.readLine());
                this.numCols = Integer.parseInt(br.readLine());
                this.numRows = Integer.parseInt(br.readLine());
                this.map = new int[this.numRows][this.numCols];
                String delim = "\\s+";

                for(int row = 0; row < this.numRows; ++row) {
                    String line = br.readLine();
                    String[] tokens = line.split(delim);

                    for(int col = 0; col < this.numCols; ++col) {
                        this.map[row][col] = Integer.parseInt(tokens[col]);
                    }
                }

                this.width = this.numCols * this.tileSize;
                this.height = this.numRows * this.tileSize;
                this.xmax = 0;
                this.xmin = 1280 - this.width;
                this.ymax = 0;
                this.ymin = 720 - this.height;
            } catch (Exception var9) {
                System.out.println("Couldn't load maps/" + s);
                var9.printStackTrace();
            }
        }

    }

    public int getTileSize() {
        return this.tileSize;
    }

    public double getx() {
        return this.x;
    }

    public double gety() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }

    public int getType(int row, int col) {
        int rc = this.map[row][col];
        int r = rc / this.numTilesAcross;
        int c = rc % this.numTilesAcross;
        return this.tiles[r][c].getType();
    }

    public int getDepth(int row, int col) {
        return row >= 0 && row < this.numRows && col >= 0 && col < this.numCols?this.depth[row][col]:0;
    }

    public void setTween(double d) {
        this.tween = d;
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * this.tween;
        this.y += (y - this.y) * this.tween;
        this.fixBounds();
        this.colOffset = (int)(-this.x) / this.tileSize;
        this.rowOffset = (int)(-this.y) / this.tileSize;
    }

    public void setPositionImmediately(double x, double y) {
        this.x = x;
        this.y = y;
        this.fixBounds();
        this.colOffset = (int)(-this.x) / this.tileSize;
        this.rowOffset = (int)(-this.y) / this.tileSize;
    }

    public void fixBounds() {
        if(this.x < (double)this.xmin) {
            this.x = (double)this.xmin;
        }

        if(this.y < (double)this.ymin) {
            this.y = (double)this.ymin;
        }

        if(this.x > (double)this.xmax) {
            this.x = (double)this.xmax;
        }

        if(this.y > (double)this.ymax) {
            this.y = (double)this.ymax;
        }

    }

    public void update() {
        if(this.visible && this.alpha < 1.0F) {
            this.alpha = (float)((double)this.alpha + this.visibleSpeed);
        } else if(!this.visible && (double)this.alpha > 0.5D) {
            this.alpha = (float)((double)this.alpha - this.visibleSpeed);
        }

        if(this.alpha > 1.0F) {
            this.alpha = 1.0F;
        }

        if((double)this.alpha < 0.5D) {
            this.alpha = 0.5F;
        }

    }

    public int[][] toMatrix() {
        return this.collision;
    }

    public boolean blocked(Pokemon pokemon, int tx, int ty) {
        return false;
    }

    public void draw(Graphics2D g, int d, double playerX, double playerY) {
        this.drawing = true;
        if(d == 1) {
            g.setComposite(AlphaComposite.getInstance(3, this.alpha));
        }

        int row;
        for(row = this.rowOffset; row < this.rowOffset + this.numRowsToDraw && row < this.numRows; ++row) {
            for(int col = this.colOffset; col < this.colOffset + this.numColsToDraw && col < this.numCols; ++col) {
                int rc = this.map[row][col];
                int r = rc / this.numTilesAcross;
                int c = rc % this.numTilesAcross;
                g.drawImage(this.tiles[r][c].getImage(), (int)this.x + col * this.tileSize, (int)this.y + row * this.tileSize, (ImageObserver)null);
                if(this.drawPossiblePaths) {
                    g.setComposite(AlphaComposite.getInstance(3, 0.5F));
                    if(this.possiblePaths[row][col]) {
                        g.setColor(Color.BLUE);
                        g.fillRect((int)this.x + col * this.tileSize, (int)this.y + row * this.tileSize, this.tileSize, this.tileSize);
                    } else {
                        g.setColor(Color.RED);
                        g.fillRect((int)this.x + col * this.tileSize, (int)this.y + row * this.tileSize, this.tileSize, this.tileSize);
                    }

                    g.setComposite(AlphaComposite.getInstance(3, 1.0F));
                }

                g.setColor(Color.WHITE);
                if(col == (int)(playerX / (double)this.tileSize) && row == (int)(playerY / (double)this.tileSize)) {
                    g.setComposite(AlphaComposite.getInstance(3, 0.5F));
                    g.setColor(Color.BLUE);
                    g.fillRect((int)this.x + col * this.tileSize, (int)this.y + row * this.tileSize, this.tileSize, this.tileSize);
                    g.setComposite(AlphaComposite.getInstance(3, 1.0F));
                }
            }
        }

        if(this.drawPath && this.path != null && this.path.exists() && this.path.getLength() != 0 && this.arrows != null && this.arrowPath != null) {
            for(row = 1; row < this.path.getLength(); ++row) {
                g.drawImage(this.arrows[this.arrowPath[row]], (int)this.x + this.path.getCol(row) * this.tileSize, (int)this.y + this.path.getRow(row) * this.tileSize, (ImageObserver)null);
            }
        }

        this.drawing = false;
    }

    public void pathFinderVisited(int x, int y) {
        this.visited[x][y] = true;
    }

    public void clearVisited() {
        for(int x = 0; x < this.getNumRows(); ++x) {
            for(int y = 0; y < this.getNumCols(); ++y) {
                this.visited[x][y] = false;
            }
        }

    }

    public boolean visited(int x, int y) {
        return this.visited[x][y];
    }

    public void setPath(Path path, int[] arrows) {
        this.path = path;
        this.arrowPath = arrows;
        this.drawPath = true;
    }

    public void deletePath() {
        this.drawPath = false;
        this.path = new Path();
        this.drawPossiblePaths = false;
        this.drawPath = false;
    }

    public boolean pathExists() {
        return this.path != null;
    }

    public void setPossiblePaths(boolean[][] possiblePaths) {
        this.possiblePaths = possiblePaths;
        this.drawPossiblePaths = true;
    }

    public boolean isPossible(int row, int col) {
        return this.possiblePaths[row][col];
    }

    public boolean isDrawing() {
        return this.drawing;
    }

    public void newMap(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.map = new int[numRows][numCols];
        this.collision = new int[numRows][numCols];
        this.depth = new int[numRows][numCols];
        this.width = numCols * this.tileSize;
        this.height = numRows * this.tileSize;
        this.width = numCols * this.tileSize;
        this.height = numRows * this.tileSize;
        this.xmax = 0;
        this.xmin = 1280 - this.width;
        this.ymax = 0;
        this.ymin = 720 - this.height;
    }

    public void fillAll(int type) {
        for(int r = 0; r < this.numRows; ++r) {
            for(int c = 0; c < this.numCols; ++c) {
                int rc = type + this.numTilesAcross + 1;
                this.map[r][c] = rc;
            }
        }

    }

    public void floral() {
        int numFlowers = 30;
        int colOffset = 1;
        int rowOffset = 2;

        for(int n = 0; n < numFlowers; ++n) {
            int posX = colOffset + (int)(Math.random() * (double)(this.numCols - colOffset * 2));
            int posY = rowOffset + (int)(Math.random() * (double)(this.numRows - rowOffset * 2));
            int r = (int)(Math.random() * 3.0D);
            int c = (int)(Math.random() * 3.0D);
            int type = r + this.numTilesAcross * c + 21;
            this.map[posY][posX] = type;
        }

    }

    public void dirtPatches() {
        int type = 1;
        int numPatches = 4;
        int rowOffset = 4;
        int colOffset = 4;

        for(int n = 0; n < numPatches; ++n) {
            int posX = colOffset + (int)(Math.random() * (double)(this.numCols - colOffset * 2));
            int posY = rowOffset + (int)(Math.random() * (double)(this.numRows - rowOffset * 2));
            this.map[posY][posX] = type * 3 + this.numTilesAcross * 0;
            this.map[posY + 1][posX] = type * 3 + this.numTilesAcross * 2;
            this.map[posY][posX + 1] = type * 3 + this.numTilesAcross * 0 + 2;
            this.map[posY + 1][posX + 1] = type * 3 + this.numTilesAcross * 2 + 2;
        }

    }

    public void patches(int type, int startR, int startC, int endR, int endC) {
        int numPatches = 4;

        for(int n = 0; n < numPatches; ++n) {
            int posX = (int)(Math.random() * (double)(endC - startC)) + startC;
            int posY = (int)(Math.random() * (double)(endR - startR)) + startR;
            this.map[posY][posX] = type * 3 + this.numTilesAcross * 0;
            this.map[posY + 1][posX] = type * 3 + this.numTilesAcross * 2;
            this.map[posY][posX + 1] = type * 3 + this.numTilesAcross * 0 + 2;
            this.map[posY + 1][posX + 1] = type * 3 + this.numTilesAcross * 2 + 2;
        }

    }

    public void cornucopia() {
        int row = this.numRows / 2 - this.numRows / 5;
        int col = this.numCols / 2 - this.numCols / 5;
        int cornWidth = this.numCols / 5;
        int cornHeight = this.numRows / 5;
        int type = 2;
        boolean big = true;
        this.map[row][col] = type * 3 * this.numTilesAcross;

        for(int r = row; r < row + cornHeight; ++r) {
            for(int c = col; c < col + cornWidth; ++c) {
                int rc;
                if(r == row) {
                    rc = type * 3 + 1;
                } else if(c == col + cornWidth - 1) {
                    rc = type * 3 + this.numTilesAcross + 2;
                } else if(c == col) {
                    rc = type * 3 + this.numTilesAcross;
                } else {
                    rc = type * 3 + this.numTilesAcross + 1;
                }

                this.map[r][c] = rc;
            }
        }

        this.map[row][col] = type * 3;
        this.map[row + cornHeight - 1][col] = type * 3 + this.numTilesAcross * 2;
        this.map[row][col + cornWidth - 1] = type * 3 + 2;
        this.map[row + cornHeight - 1][col + cornWidth - 1] = type * 3 + this.numTilesAcross * 2 + 2;
    }

    public void fill(int type, int row, int col, int height, int width) {
        this.map[row][col] = type * 3 * this.numTilesAcross;

        for(int r = row; r < row + height; ++r) {
            for(int c = col; c < col + width; ++c) {
                int rc;
                if(r == row) {
                    rc = type * 3 + 1;
                } else if(r == row + height - 1) {
                    rc = type * 3 + this.numTilesAcross * 2 + 1;
                } else if(c == col) {
                    rc = type * 3 + this.numTilesAcross;
                } else if(c == col + width - 1) {
                    rc = type * 3 + this.numTilesAcross + 2;
                } else {
                    rc = type * 3 + this.numTilesAcross + 1;
                }

                this.map[r][c] = rc;
            }
        }

        this.map[row][col] = type * 3;
        this.map[row + height - 1][col] = type * 3 + this.numTilesAcross * 2;
        this.map[row][col + width - 1] = type * 3 + 2;
        this.map[row + height - 1][col + width - 1] = type * 3 + this.numTilesAcross * 2 + 2;
    }
}
