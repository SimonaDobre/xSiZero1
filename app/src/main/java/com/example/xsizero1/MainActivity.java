package com.example.xsizero1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int player = 10, completedBoxes;
    int[][] variants;
    boolean somebodyWon;
    Button newGameBtn;
    TextView messageTV;
    ImageView i0, i1, i2, i3, i4, i5, i6, i7, i8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void actualizeValuesInVariants(int player, int clickedTag) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                if (variants[i][j] == clickedTag) {
                    variants[i][j] = player;
                }
            }
        }
    }

    private boolean checkAnyPossibleWinnerVariant() {
        for (int i = 0; i < 8; i++) {
            if (variants[i][0] == variants[i][1] && variants[i][1] == variants[i][2]) {
                // Toast.makeText(this, " jucatorul " + player + " castigat", Toast.LENGTH_SHORT).show();
                somebodyWon = true;
                newGameBtn.setVisibility(View.VISIBLE);
                messageTV.setVisibility(View.VISIBLE);
                if (player == 10) {
                    messageTV.setText("Jucatorul A a castigat!");
                } else {
                    messageTV.setText("Jucatorul B a castigat!");
                }
                return true;
            }
        }
        if (!somebodyWon && completedBoxes == 9) {
            newGameBtn.setVisibility(View.VISIBLE);
            messageTV.setVisibility(View.VISIBLE);
            messageTV.setText("Remiza!");
        }
        return false;
    }

    void newGame() {
        somebodyWon = false;
        i0.setImageResource(0);
        i1.setImageResource(0);
        i2.setImageResource(0);
        i3.setImageResource(0);
        i4.setImageResource(0);
        i5.setImageResource(0);
        i6.setImageResource(0);
        i7.setImageResource(0);
        i8.setImageResource(0);
        variants = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        newGameBtn.setVisibility(View.INVISIBLE);
        messageTV.setVisibility(View.INVISIBLE);
        completedBoxes = 0;
    }

    void initViews() {
        messageTV = findViewById(R.id.textView);
        newGameBtn = findViewById(R.id.button);
        newGameBtn.setOnClickListener(this);
        i0 = findViewById(R.id.imageView0);
        i0.setOnClickListener(this);
        i1 = findViewById(R.id.imageView1);
        i1.setOnClickListener(this);
        i2 = findViewById(R.id.imageView2);
        i2.setOnClickListener(this);
        i3 = findViewById(R.id.imageView3);
        i3.setOnClickListener(this);
        i4 = findViewById(R.id.imageView4);
        i4.setOnClickListener(this);
        i5 = findViewById(R.id.imageView5);
        i5.setOnClickListener(this);
        i6 = findViewById(R.id.imageView6);
        i6.setOnClickListener(this);
        i7 = findViewById(R.id.imageView7);
        i7.setOnClickListener(this);
        i8 = findViewById(R.id.imageView8);
        i8.setOnClickListener(this);
        variants = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    }

    void clickOnABox(int clickedID) {
        if (!somebodyWon) {
            completedBoxes++;
            Log.i("boxes = ", completedBoxes + "");
            ImageView clickedBox = findViewById(clickedID);
            int tagApasat = Integer.parseInt(clickedBox.getTag().toString());
            if (player == 10) {
                clickedBox.setImageResource(R.drawable.x_transparent);
            } else {
                clickedBox.setImageResource(R.drawable.zero_transparent);
            }
            actualizeValuesInVariants(player, tagApasat);
            checkAnyPossibleWinnerVariant();
            player = -player;
        }
    }

    @Override
    public void onClick(View view) {
        int clickedID = view.getId();
        switch (clickedID) {
            case R.id.button:
                newGame();
                break;
            case R.id.imageView0:
            case R.id.imageView1:
            case R.id.imageView2:
            case R.id.imageView3:
            case R.id.imageView4:
            case R.id.imageView5:
            case R.id.imageView6:
            case R.id.imageView7:
            case R.id.imageView8:
                Log.i("clicat pe ", view.getId() + "");
                clickOnABox(clickedID);
                break;
            default:
                return;
        }
    }

}
