package com.conwayGame.conwaygame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by korolkov on 10/11/13.
 */
public class GameActivity extends Activity implements EnterSizeDialog.EnterSizeListener {

    private int  gridSize = 10;

    ImageAdapter adapter;
    Grid         grid;
    GridView     gridview;


    private void createGrid(int gridSize){
        grid =  new Grid(this,gridSize);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_activity);

        gridview = (GridView) findViewById(R.id.gridview);

        createGrid(gridSize);

        adapter =  new ImageAdapter(this, grid);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(GameActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                ImageView imageView  = (ImageView)v;
                grid.turnElementState(position);
                imageView.setImageResource((grid.getElementState(position)== Grid.STATE_ALIVE)?
                                                        R.drawable.ic_launcher:R.drawable.sample_0);
            }
        });

    }

    public void startGame(View view) {
        grid.start();
    }


    public void pauseGame(View view) {
        grid.pause();
        EnterSizeDialog enterTextDialog = EnterSizeDialog.newInstance();
        enterTextDialog.show(getFragmentManager(),"");
    }

    @Override
    public void enterSize(EditText editText) {
        gridSize = Integer.parseInt(editText.getText().toString());
        if (gridSize > 0 ) {
            createGrid(gridSize);
            adapter = new ImageAdapter(this, grid);
            gridview.setAdapter(adapter);
            gridview.setNumColumns(gridSize);
            findViewById(R.id.btnStart).setEnabled(true);
        }

    }

    public void changeGridImages(){
        adapter.notifyDataSetChanged();
        ((TextView)findViewById(R.id.txt)).setText("Generation   "+ grid.generation);
    }
    public void finish(){
        ((TextView)findViewById(R.id.txt)).setText("Game Finished");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void randLocation(View view) {
        grid.randLocation();
    }
}
