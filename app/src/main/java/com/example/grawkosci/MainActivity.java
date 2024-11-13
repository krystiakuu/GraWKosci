package com.example.grawkosci;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    private TextView head;
    private TextView[] kosci = new TextView[5];
    private TextView wyniklosowania;
    private TextView wynikcalkowity;
    private TextView rzuty;
    private Button throwButton;
    private Button resetButton;
    private int calywynik = 0;
    private int liczbarzutow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        head = findViewById(R.id.head);
        kosci[0] = findViewById(R.id.kosci1);
        kosci[1] = findViewById(R.id.kosci2);
        kosci[2] = findViewById(R.id.kosci3);
        kosci[3] = findViewById(R.id.kosci4);
        kosci[4] = findViewById(R.id.kosci5);
        wyniklosowania = findViewById(R.id.WynikLosowania);
        wynikcalkowity = findViewById(R.id.WynikCalkowity);
        rzuty = findViewById(R.id.Rzuty);
        throwButton = findViewById(R.id.throwButton);
        resetButton = findViewById(R.id.resetButton);

        throwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        Random random = new Random();
        int[] numery = new int[5];
        for (int i = 0; i < 5; i++) {
            numery[i] = random.nextInt(6) + 1;
        }
        wyswietlwynikikosci(numery);
        int wynik = obliczwynik(numery);
        updateScore(wynik);
        nowaLiczbaLosowan();
    }

    private int obliczwynik(int[] numery) {
        int[] indeksy = new int[7];
        for (int result : numery) {
            indeksy[result]++;
        }
        int wynik = 0;
        for (int i = 1; i <= 6; i++) {
            if (indeksy[i] >= 2) {
                wynik += i * indeksy[i];
            }
        }
        return wynik;
    }

    private void resetGame() {
        for (TextView kosc : kosci) {
            kosc.setText("?");
        }
        calywynik = 0;
        liczbarzutow = 0;
        wyniklosowania.setText("Wynik tego losowania: 0");
        wynikcalkowity.setText("Wynik gry: 0");
        rzuty.setText("Liczba rzutow: 0");
    }

    private void updateScore(int nowywynik) {
        calywynik += nowywynik;
        wynikcalkowity.setText("Wynik gry: " + calywynik);
        wyniklosowania.setText("Wynik tego losowania: " + nowywynik);
    }

    private void nowaLiczbaLosowan() {
        liczbarzutow++;
        rzuty.setText("Liczba rzutow: " + liczbarzutow);
    }

    private void wyswietlwynikikosci(int[] numery) {
        for (int i = 0; i < numery.length; i++) {
            kosci[i].setText(String.valueOf(numery[i]));
        }
    }
}