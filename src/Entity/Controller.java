//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Entity;

import TileMap.TileMap;

public class Controller {
    private double posX;
    private double posY;
    private double speedX;
    private double speedY;
    private double maxSpeed;
    private double startSpeed;
    private double stopSpeed;
    private int oldCol;
    private int oldRow;
    private TileMap tileMap;
    private int tileSize;
    private int numCols;
    private int numRows;
    private boolean right;
    private boolean left;
    private boolean up;
    private boolean down;
    private boolean inControl;

    public Controller(TileMap tm) {
        this.tileMap = tm;
        this.tileSize = tm.getTileSize();
        this.numCols = tm.getNumCols();
        this.numRows = tm.getNumRows();
        this.speedX = 0.0D;
        this.speedY = 0.0D;
        this.right = this.left = this.up = this.down = false;
        this.inControl = true;
        this.posX = 0.0D;
        this.posY = 0.0D;
        this.maxSpeed = 15.0D;
        this.startSpeed = 7.7D;
        this.stopSpeed = 4.0D;
    }

    public void update() {
        if(this.right) {
            this.speedX += this.startSpeed;
            if(this.speedX > this.maxSpeed) {
                this.speedX = this.maxSpeed;
            }
        } else if(this.left) {
            this.speedX -= this.startSpeed;
            if(Math.abs(this.speedX) > this.maxSpeed) {
                this.speedX = -this.maxSpeed;
            }
        } else if(this.speedX > 0.0D) {
            this.speedX -= this.stopSpeed;
            if(this.speedX < 0.0D) {
                this.speedX = 0.0D;
            }
        } else if(this.speedX < 0.0D) {
            this.speedX += this.stopSpeed;
            if(this.speedX > 0.0D) {
                this.speedX = 0.0D;
            }
        }

        if(this.down) {
            this.speedY += this.startSpeed;
            if(this.speedY > this.maxSpeed) {
                this.speedY = this.maxSpeed;
            }
        } else if(this.up) {
            this.speedY -= this.startSpeed;
            if(Math.abs(this.speedY) > this.maxSpeed) {
                this.speedY = -this.maxSpeed;
            }
        } else if(this.speedY > 0.0D) {
            this.speedY -= this.stopSpeed;
            if(this.speedY < 0.0D) {
                this.speedY = 0.0D;
            }
        } else if(this.speedY < 0.0D) {
            this.speedY += this.stopSpeed;
            if(this.speedY > 0.0D) {
                this.speedY = 0.0D;
            }
        }

        this.posX += this.speedX;
        if((int)(this.posX / (double)this.tileSize) > this.numCols - 1) {
            this.posX = (double)((this.numCols - 1) * this.tileSize);
        }

        if((int)this.posX < 0) {
            this.posX = 0.0D;
        }

        this.posY += this.speedY;
        if((int)(this.posY / (double)this.tileSize) > this.numRows - 1) {
            this.posY = (double)((this.numRows - 1) * this.tileSize);
        }

        if((int)this.posY < 0) {
            this.posY = 0.0D;
        }

    }

    public boolean positionChanged() {
        if(this.oldRow == (int)(this.posY / (double)this.tileSize) && this.oldCol == (int)(this.posX / (double)this.tileSize)) {
            return false;
        } else {
            this.oldRow = (int)(this.posY / (double)this.tileSize);
            this.oldCol = (int)(this.posX / (double)this.tileSize);
            return true;
        }
    }

    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void stop() {
        this.right = this.left = this.up = this.down = false;
    }

    public void keyPressed(int k) {
        if(this.inControl) {
            switch(k) {
                case 37:
                    this.left = true;
                    this.right = false;
                    break;
                case 38:
                    this.up = true;
                    this.down = false;
                    break;
                case 39:
                    this.right = true;
                    this.left = false;
                    break;
                case 40:
                    this.down = true;
                    this.up = false;
            }
        }

    }

    public void keyReleased(int k) {
        switch(k) {
            case 37:
                this.left = false;
                this.posX = (double)((int)this.posX);
                break;
            case 38:
                this.up = false;
                this.posY = (double)((int)this.posY);
                break;
            case 39:
                this.right = false;
                this.posX = (double)((int)this.posX);
                break;
            case 40:
                this.down = false;
                this.posY = (double)((int)this.posY);
        }

    }
}
