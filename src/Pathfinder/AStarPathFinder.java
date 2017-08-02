//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Pathfinder;

import Entity.Pokemon;
import TileMap.TileMap;
import java.util.ArrayList;
import java.util.Collections;

public class AStarPathFinder implements PathFinder {
    private ArrayList closed;
    private AStarPathFinder.SortedList open;
    private TileMap map;
    private int maxSearchDistance;
    private AStarPathFinder.Node[][] nodes;
    private boolean allowDiagMovement;
    private AStarHeuristic heuristic;

    public AStarPathFinder(TileMap map, int maxSearchDistance, boolean allowDiagMovement) {
        this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
    }

    public AStarPathFinder(TileMap map, int maxSearchDistance, boolean allowDiagMovement, AStarHeuristic heuristic) {
        this.closed = new ArrayList();
        this.open = new AStarPathFinder.SortedList();
        //this.open = new AStarPathFinder.SortedList((AStarPathFinder.SortedList)null);

        this.heuristic = heuristic;
        this.map = map;
        this.maxSearchDistance = maxSearchDistance;
        this.allowDiagMovement = allowDiagMovement;
        this.nodes = new AStarPathFinder.Node[map.getNumRows()][map.getNumCols()];

        for(int x = 0; x < map.getNumRows(); ++x) {
            for(int y = 0; y < map.getNumCols(); ++y) {
                this.nodes[x][y] = new AStarPathFinder.Node(x, y);
            }
        }

    }

    public Path findPath(Pokemon mover, int sourceRow, int sourceCol, int targetRow, int targetCol) {
        this.nodes[sourceRow][sourceCol].cost = 0.0F;
        this.nodes[sourceRow][sourceCol].depth = 0;
        this.closed.clear();
        this.open.clear();
        this.open.add(this.nodes[sourceRow][sourceCol]);
        this.nodes[targetRow][targetCol].parent = null;
        int maxDepth = 0;

        while(maxDepth < this.maxSearchDistance && this.open.size() != 0) {
            AStarPathFinder.Node current = this.getFirstInOpen();
            if(current == this.nodes[targetRow][targetCol]) {
                break;
            }

            this.removeFromOpen(current);
            this.addToClosed(current);

            for(int x = -1; x < 2; ++x) {
                for(int y = -1; y < 2; ++y) {
                    if((x != 0 || y != 0) && (this.allowDiagMovement || x == 0 || y == 0)) {
                        int xp = x + current.col;
                        int yp = y + current.row;
                        if(this.isValidLocation(mover, sourceRow, sourceCol, xp, yp)) {
                            float nextStepCost = current.cost + this.getMovementCost(mover, current.row, current.col, yp, xp);
                            AStarPathFinder.Node neighbour = this.nodes[yp][xp];
                            this.map.pathFinderVisited(yp, xp);
                            if(nextStepCost < neighbour.cost) {
                                if(this.inOpenList(neighbour)) {
                                    this.removeFromOpen(neighbour);
                                }

                                if(this.inClosedList(neighbour)) {
                                    this.removeFromClosed(neighbour);
                                }
                            }

                            if(!this.inOpenList(neighbour) && !this.inClosedList(neighbour)) {
                                neighbour.cost = nextStepCost;
                                neighbour.heuristic = this.getHeuristicCost(mover, yp, xp, targetRow, targetCol);
                                maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                                this.addToOpen(neighbour);
                            }
                        }
                    }
                }
            }
        }

        if(this.nodes[targetRow][targetCol].parent == null) {
            return null;
        } else {
            Path path = new Path();

            for(AStarPathFinder.Node target = this.nodes[targetRow][targetCol]; target != this.nodes[sourceRow][sourceCol]; target = target.parent) {
                path.prependStep(target.row, target.col);
            }

            path.prependStep(sourceRow, sourceCol);
            return path;
        }
    }

    protected AStarPathFinder.Node getFirstInOpen() {
        return (AStarPathFinder.Node)this.open.first();
    }

    protected void addToOpen(AStarPathFinder.Node node) {
        this.open.add(node);
    }

    protected boolean inOpenList(AStarPathFinder.Node node) {
        return this.open.contains(node);
    }

    protected void removeFromOpen(AStarPathFinder.Node node) {
        this.open.remove(node);
    }

    protected void addToClosed(AStarPathFinder.Node node) {
        this.closed.add(node);
    }

    protected boolean inClosedList(AStarPathFinder.Node node) {
        return this.closed.contains(node);
    }

    protected void removeFromClosed(AStarPathFinder.Node node) {
        this.closed.remove(node);
    }

    protected boolean isValidLocation(Pokemon mover, int sourceRow, int sourceCol, int x, int y) {
        boolean invalid = y < 0 || x < 0 || y >= this.map.getNumRows() || x >= this.map.getNumCols();
        if(!invalid && (sourceCol != x || sourceRow != y)) {
            invalid = this.map.blocked(mover, x, y);
        }

        return !invalid;
    }

    public float getMovementCost(Pokemon mover, int sourceRow, int sourceCol, int targetRow, int targetCol) {
        return 1.0F;
    }

    public float getHeuristicCost(Pokemon mover, int row, int col, int targetRow, int targetCol) {
        return this.heuristic.getCost(this.map, mover, row, col, targetRow, targetCol);
    }

    private class Node implements Comparable {
        private int row;
        private int col;
        private float cost;
        private AStarPathFinder.Node parent;
        private float heuristic;
        private int depth;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int setParent(AStarPathFinder.Node parent) {
            this.depth = parent.depth + 1;
            this.parent = parent;
            return this.depth;
        }

        public int compareTo(Object other) {
            AStarPathFinder.Node o = (AStarPathFinder.Node)other;
            float f = this.heuristic + this.cost;
            float of = o.heuristic + o.cost;
            return f < of?-1:(f > of?1:0);
        }
    }

    private class SortedList {
        private ArrayList list;

        private SortedList() {
            this.list = new ArrayList();
        }

        public Object first() {
            return this.list.get(0);
        }

        public void clear() {
            this.list.clear();
        }

        public void add(Object o) {
            this.list.add(o);
            Collections.sort(this.list);
        }

        public void remove(Object o) {
            this.list.remove(o);
        }

        public int size() {
            return this.list.size();
        }

        public boolean contains(Object o) {
            return this.list.contains(o);
        }
    }
}
