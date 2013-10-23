package com.conwayGame.conwaygame;

import android.os.Handler;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by korolkov on 10/10/13.
 */
public  class Grid extends Thread {


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

        private void die(){
            elementState = STATE_DEAD;
        }

        private void live(){
            elementState = STATE_ALIVE;
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

    private int gridSize;

    private int timePerCycle = 1000;

    private Random rand = new Random(System.currentTimeMillis());

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

    private void live(int position) {
        grids.get((position+1)/gridSize).get((position+1)%gridSize).live();
    }

    private void clr() {
        for (int j = 0 ; j < gridSize + 2;j++) {
            grids.get(0).get(j).die();
        }
        for (int j = 0 ; j < gridSize + 2;j++) {
            grids.get(gridSize + 1).get(j).die();
        }
        for (int i = 0 ; i < gridSize + 2;i++) {
            grids.get(i).get(0).die();
        }
        for (int i = 0 ; i < gridSize + 2;i++) {
            grids.get(i).get(gridSize+1).die();
        }
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

    private void initGrid(int gridLength){
        this.gridSize  = gridLength;
        grids          = new ArrayList<ArrayList<GridElement>>();

        ArrayList<GridElement> rowElements;

        for (int i = 0 ; i < gridSize + 2; i++) {
            rowElements = new ArrayList<GridElement>();
            for (int j = 0 ; j < gridSize + 2;j++) {
                rowElements.add(new GridElement(i,j));
            }
            grids.add(i,rowElements);
        }

    }

    public  void updateGrid(){
        clr();
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

    private void clear(){
        ArrayList<GridElement> rowElements;

        for (int i = 0 ; i < gridSize + 2; i++) {
            rowElements = grids.get(i);
            for (int j = 0 ; j < gridSize + 2;j++) {
                rowElements.get(j).die();
            }
        }
    }

    private void rand(){
        for(int i = 0 ; i < (gridSize*gridSize)/4; i++){
            live(rand.nextInt(gridSize*gridSize));
        }
    }


    public void randLocation(){
        clear();
        rand();
        activity.changeGridImages();
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
            handler.postDelayed(this,timePerCycle);
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
            generation = 0;
            activity.finish();
            handler.removeCallbacks(this);
        }else {
            updateGrid();
            activity.changeGridImages();
            handler.postDelayed(this,timePerCycle);
        }

    }
}
