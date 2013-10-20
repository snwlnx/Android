package com.conwayGame.conwaygame;

import android.os.Handler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by korolkov on 10/10/13.
 */
public  class Grid extends Thread implements Serializable{


    public static final int STATE_ALIVE = 1, STATE_DEAD = 0, MaxNeighbors = 3, MinNeighbors = 2;

    private class ElementCoordinates  {
        int rowPos;
        int colPos;

        ElementCoordinates(int rowPos, int colPos){
            this.colPos = colPos;
            this.rowPos = rowPos;
        }

    }
    public class  GridElement         {

        GridElement(int rowPos,int colPos) {
            coordinates = new ElementCoordinates(rowPos,colPos);
        }

        private ElementCoordinates coordinates;

        private  int  elementState = STATE_DEAD;

        public   int  getElementState() {
            return elementState;
        }

        public ElementCoordinates getCoordinates(){
            return coordinates;
        }

        public boolean isAlive(){
            return (elementState == STATE_ALIVE)?true:false;
        }


        private  void turnState() {
            elementState = (elementState == STATE_ALIVE)? STATE_DEAD : STATE_ALIVE;
        }


        private  int  countNeighbors() {
            int neighborCount  = 0;
            ArrayList<Grid.GridElement> rowElements;

            for(int i = coordinates.rowPos - 1 ; i < coordinates.rowPos + 2; i++ ){
                rowElements = grids.get(i);
                if (rowElements == null) {
                    continue;
                }
                for (int j = coordinates.colPos - 1; j < coordinates.colPos + 2; j++){
                    if (coordinates.rowPos == i && coordinates.colPos == j )
                        continue;
                    if (rowElements.get(j) != null && rowElements.get(j).isAlive()){
                        neighborCount++;
                    }
                }
            }
            return neighborCount;
        }


        public void updateState() {

            int neighborsCount = countNeighbors();

            if (elementState == STATE_ALIVE &&
                    (neighborsCount < MinNeighbors || neighborsCount > MaxNeighbors)) {

                    itemsTurnStateAfterStep.add(getCoordinates());

            } else if (elementState == STATE_DEAD && neighborsCount == MaxNeighbors) {
                    itemsTurnStateAfterStep.add(getCoordinates());
            }
        }
    }



    private ArrayList<ArrayList<GridElement>> grids;

    public  int generation = 0;

    private int gridSize   = 0;

    private Handler handler = new Handler() ;

    private ArrayList<ElementCoordinates> itemsTurnStateAfterStep;

    private boolean gameContinued = true;
    private boolean threadRunning = false;


    private GameActivity activity;

    public boolean isGameContinued(){
        return gameContinued;
    }


    public int getElementState(int position) {
        int rowPos = (position+1)/gridSize,
            colPos = (position+1)%gridSize;

        return grids.get(rowPos).get(colPos).getElementState();
    }


    public void turnElementState(int rowPos, int columnPos) {
        grids.get(rowPos).get(columnPos).turnState();
    }

    public void turnElementState(int position) {
        grids.get((position+1)/gridSize).get((position+1)%gridSize).turnState();
    }

    public   int getElementsCount(){
        return gridSize*gridSize;
    }


    public Grid(GameActivity activity, int gridSize){
        initGrid(gridSize);
        this.activity           = activity;
        itemsTurnStateAfterStep = new ArrayList<ElementCoordinates>();
    }

    private void initGrid(int gridSize){
        this.gridSize  = gridSize;
        grids          = new ArrayList<ArrayList<GridElement>>();

        GridElement            element;
        ArrayList<GridElement> rowElements;

        for (int i = 0 ; i < gridSize + 2; i++) {
            rowElements = new ArrayList<GridElement>();
            for (int j = 0 ; j < gridSize + 2;j++) {
                element = new GridElement(i,j);
                rowElements.add(element);
            }
            grids.add(i,rowElements);
        }

    }

    public  void updateGrid(){
        ArrayList<GridElement> rowElements;
        for (int i = 1 ; i <= gridSize;i++) {
            rowElements = grids.get(i);
            for (int j = 1 ; j <= gridSize;j++) {
                rowElements.get(j).updateState();
            }
        }
        generation++;
        turnItemsStateAfterStep();
    }

    private  void turnItemsStateAfterStep(){
        if (itemsTurnStateAfterStep.isEmpty()) {
            gameContinued = false;
            return;
        }
        for (ElementCoordinates coordinates : itemsTurnStateAfterStep) {
            turnElementState(coordinates.rowPos, coordinates.colPos);
        }
        itemsTurnStateAfterStep.clear();
    }

    public void pause(){
        handler.removeCallbacks(this);
    }

    public void start(){
        if (!threadRunning) {
            handler.postDelayed(this,1000);
            threadRunning = true;
            gameContinued = true;
        }else {
            handler.removeCallbacks(this);
            threadRunning = false;
        }
    }

    @Override
    public void run() {
        if (!isGameContinued() ){
            handler.removeCallbacks(this);
        }else {
            updateGrid();
            activity.changeGridImages();
            handler.postDelayed(this,1000);
        }

    }
}
