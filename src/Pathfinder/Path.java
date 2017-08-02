//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Pathfinder;

import java.util.ArrayList;

public class Path {
    private ArrayList steps = new ArrayList();

    public Path() {
    }

    public int getLength() {
        return this.steps.size();
    }

    public boolean exists() {
        return this.steps != null;
    }

    public Path.Step getStep(int index) {
        return (Path.Step)this.steps.get(index);
    }

    public int getRow(int index) {
        return this.getStep(index).row;
    }

    public int getCol(int index) {
        return this.getStep(index).col;
    }

    public void appendStep(int row, int col) {
        this.steps.add(new Path.Step(row, col));
    }

    public void prependStep(int row, int col) {
        this.steps.add(0, new Path.Step(row, col));
    }

    public boolean contains(int row, int col) {
        return this.steps.contains(new Path.Step(row, col));
    }

    public class Step {
        private int row;
        private int col;

        public Step(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public int hashCode() {
            return this.row * this.col;
        }

        public boolean equals(Object other) {
            if(other instanceof Path.Step) {
                Path.Step o = (Path.Step)other;
                return o.row == this.row && o.col == this.col;
            } else {
                return false;
            }
        }
    }
}
