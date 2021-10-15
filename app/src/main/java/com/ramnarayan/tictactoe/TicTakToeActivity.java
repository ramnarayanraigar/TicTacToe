package com.ramnarayan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTakToeActivity extends AppCompatActivity {
    private TextView textWinnersName;
    ArrayList<Integer> birdList = new ArrayList<>();
    int[] arr = {10, 10, 10, 10, 10, 10, 10, 10, 10};
    private boolean isPlayerOne = true;
    private int winnersPosition = 0;
    private int tieCount = 0;
    private boolean isWon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tak_toe);

        GridView gvTicTakToe = findViewById(R.id.gridTicTakToe);
        textWinnersName = findViewById(R.id.textWinnersName);
        Button btnReset = findViewById(R.id.btnReset);
        addInList();

        TicTakToeAdapter ticTakToeAdapter = new TicTakToeAdapter(this, R.layout.activity_tic_tak_toe, birdList, arr);
        gvTicTakToe.setAdapter(ticTakToeAdapter);


        gvTicTakToe.setOnItemClickListener((parent, view, position, id) -> {
            if (tieCount != 9 && !isWon) {
                if (isAlreadyFilled(position)) {
                    if (isPlayerOne) {
                        isPlayerOne = false;
                        arr[position] = 1;
                    } else {
                        isPlayerOne = true;
                        arr[position] = 2;
                    }
                    tieCount++;
                } else {
                    Toast.makeText(TicTakToeActivity.this, "Choose another location", Toast.LENGTH_LONG).show();
                }

                if (isWon()) {
                    isWon = true;
                    textWinnersName.setText(winnerName(winnersPosition));
                }

                ticTakToeAdapter.updateData(arr);

                if (tieCount == 9 && !isWon) {
                    textWinnersName.setText(getString(R.string.tie));
                }
            } else {
                if (!isWon)
                    textWinnersName.setText(getString(R.string.tie));
            }

        });

        btnReset.setOnClickListener(view -> {
            Arrays.fill(arr, 10);
            isPlayerOne = true;
            winnersPosition = 0;
            tieCount = 0;
            textWinnersName.setText("");
            ticTakToeAdapter.updateData(arr);
            isWon = false;
        });
    }

    private boolean isWon() {
        return isOneRowSame() || isOneColumnSame() || isOneDiagonalSame();
    }

    private boolean isOneRowSame() {
        if ((arr[0] == arr[1]) && (arr[2] == arr[1]) && (arr[0] != 10)) {
            winnersPosition = 0;
            return isNotDefaultValue(arr[0]);
        } else if ((arr[3] == arr[4]) && (arr[4] == arr[5]) && (arr[3] != 10)) {
            winnersPosition = 3;
            return isNotDefaultValue(arr[3]);
        } else if ((arr[6] == arr[7]) && (arr[7] == arr[8]) && (arr[6] != 10)) {
            winnersPosition = 6;
            return isNotDefaultValue(arr[6]);

        }

        return false;
    }

    private boolean isOneColumnSame() {
        if ((arr[0] == arr[3]) && (arr[3] == arr[6]) && (arr[3] != 10)) {
            winnersPosition = 0;
            return isNotDefaultValue(arr[0]);
        } else if ((arr[1] == arr[4]) && (arr[4] == arr[7]) && (arr[1] != 10)) {
            winnersPosition = 1;
            return isNotDefaultValue(arr[1]);
        } else if ((arr[2] == arr[5]) && (arr[5] == arr[8]) && (arr[2] != 10)) {
            winnersPosition = 2;
            return isNotDefaultValue(arr[2]);
        }
        return false;
    }

    private boolean isOneDiagonalSame() {
        if ((arr[0] == arr[4]) && (arr[4] == arr[8]) && (arr[0] != 10)) {
            winnersPosition = 0;
            return isNotDefaultValue(arr[0]);
        } else if ((arr[2] == arr[4]) && (arr[4] == arr[6]) && (arr[2] != 10)) {
            winnersPosition = 2;
            return isNotDefaultValue(arr[2]);
        }

        return false;
    }

    private boolean isAlreadyFilled(int position) {
        return arr[position] == 10;
    }

    private boolean isNotDefaultValue(int value) {
        return value != 10;
    }

    private String winnerName(int position) {
        if (arr[position] == 1) {
            return "A Win";
        } else return "B Win";
    }

    private void addInList() {
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
        birdList.add(0);
    }
}