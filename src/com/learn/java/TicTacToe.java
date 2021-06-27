package com.learn.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

  Random random = new Random();
  JFrame frame = new JFrame();
  JPanel titlePanel = new JPanel();
  JPanel buttonsPanel = new JPanel();
  JLabel textField = new JLabel();
  JButton[] gameButtons = new JButton[9];
  boolean player1_turn;

  // Constructor
  public TicTacToe() {
    // The exit application default window close operation.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Frame size width: 800px, height: 800px
    frame.setSize(800, 800);
    // Frame background color
    frame.getContentPane().setBackground(new Color(50, 50, 50));
    // Frame border
    frame.setLayout(new BorderLayout());

    // Game title background color
    textField.setBackground(new Color(25, 25, 25));
    // Game title text color
    textField.setForeground(new Color(25, 255, 0));
    // Game title font
    textField.setFont(new Font("Ink free", Font.BOLD, 75));
    // Game title alignment
    textField.setHorizontalAlignment(JLabel.CENTER);
    // Game title text
    textField.setText("Tic-Tac-Toe Game");
    textField.setOpaque(true);

    // Set title panel position
    titlePanel.setLayout(new BorderLayout());
    titlePanel.setBounds(0, 0, 800, 100);

    // Set button panel layout with 3 rows and 3 columns
    buttonsPanel.setLayout(new GridLayout(3, 3));
    buttonsPanel.setBackground(new Color(25, 25, 25));
    // Add game buttons to buttons panel
    for (int i = 0; i < gameButtons.length; i++) {
      gameButtons[i] = new JButton();
      gameButtons[i].setFont(new Font("Ink free", Font.BOLD, 75));
      gameButtons[i].setBackground(new Color(25, 25, 25));
      gameButtons[i].setFocusable(false);
      gameButtons[i].addActionListener(this);
      buttonsPanel.add(gameButtons[i]);
    }

    // First turn (either X or O)
    firstTurn();

    titlePanel.add(textField);
    frame.add(titlePanel, BorderLayout.NORTH);
    frame.add(buttonsPanel);
    frame.setVisible(true);
  }

  // Methods
  @Override
  public void actionPerformed(ActionEvent event) {
    for (JButton gameButton : gameButtons) {
      // The object on which the Event initially occurred.
      if (event.getSource().equals(gameButton)) {
        if (player1_turn) {
          if (gameButton.getText().equals("")) {
            gameButton.setForeground(new Color(255, 0, 0));
            gameButton.setText("X");
            textField.setText("O turn");
            player1_turn = false;
            // Check for win
            checkWin();
          }
        } else {
          if (gameButton.getText().equals("")) {
            gameButton.setForeground(new Color(255, 255, 0));
            gameButton.setText("O");
            textField.setText("X turn");
            player1_turn = true;
            // Check for win
            checkWin();
          }
        }
      }
    }
  }

  public void firstTurn() {
    // Display first X/O turn after 1 second
    Timer tm = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (random.nextInt(2) == 0) {
          player1_turn = true;
          textField.setText("X turn");
        } else {
          player1_turn = false;
          textField.setText("O turn");
        }
      }
    });
    tm.setRepeats(false);
    tm.start();
  }

  public void checkWin() {
    // check if X wins
    if (gameButtons[0].getText().equals("X") && gameButtons[1].getText().equals("X") && gameButtons[2].getText().equals("X")) {
      xWins(0, 1, 2);
    }
    if (gameButtons[3].getText().equals("X") && gameButtons[4].getText().equals("X") && gameButtons[5].getText().equals("X")) {
      xWins(3, 4, 5);
    }
    if (gameButtons[6].getText().equals("X") && gameButtons[7].getText().equals("X") && gameButtons[8].getText().equals("X")) {
      xWins(6, 7, 8);
    }
    if (gameButtons[0].getText().equals("X") && gameButtons[3].getText().equals("X") && gameButtons[6].getText().equals("X")) {
      xWins(0, 3, 6);
    }
    if (gameButtons[1].getText().equals("X") && gameButtons[4].getText().equals("X") && gameButtons[7].getText().equals("X")) {
      xWins(1, 4, 7);
    }
    if (gameButtons[2].getText().equals("X") && gameButtons[5].getText().equals("X") && gameButtons[8].getText().equals("X")) {
      xWins(2, 5, 8);
    }
    if (gameButtons[0].getText().equals("X") && gameButtons[4].getText().equals("X") && gameButtons[8].getText().equals("X")) {
      xWins(0, 4, 8);
    }
    if (gameButtons[2].getText().equals("X") && gameButtons[4].getText().equals("X") && gameButtons[6].getText().equals("X")) {
      xWins(2, 4, 6);
    }

    // Check if O wins
    if (gameButtons[0].getText().equals("O") && gameButtons[1].getText().equals("O") && gameButtons[2].getText().equals("O")) {
      oWins(0, 1, 2);
    }
    if (gameButtons[3].getText().equals("O") && gameButtons[4].getText().equals("O") && gameButtons[5].getText().equals("O")) {
      oWins(3, 4, 5);
    }
    if (gameButtons[6].getText().equals("O") && gameButtons[7].getText().equals("O") && gameButtons[8].getText().equals("O")) {
      oWins(6, 7, 8);
    }
    if (gameButtons[0].getText().equals("O") && gameButtons[3].getText().equals("O") && gameButtons[6].getText().equals("O")) {
      oWins(0, 3, 6);
    }
    if (gameButtons[1].getText().equals("O") && gameButtons[4].getText().equals("O") && gameButtons[7].getText().equals("O")) {
      oWins(1, 4, 7);
    }
    if (gameButtons[2].getText().equals("O") && gameButtons[5].getText().equals("O") && gameButtons[8].getText().equals("O")) {
      oWins(2, 5, 8);
    }
    if (gameButtons[0].getText().equals("O") && gameButtons[4].getText().equals("O") && gameButtons[8].getText().equals("O")) {
      oWins(0, 4, 8);
    }
    if (gameButtons[2].getText().equals("O") && gameButtons[4].getText().equals("O") && gameButtons[6].getText().equals("O")) {
      oWins(2, 4, 6);
    }
  }

  public void xWins(int a, int b, int c) {
    // Set winner buttons to green
    gameButtons[a].setBackground(Color.green);
    gameButtons[b].setBackground(Color.green);
    gameButtons[c].setBackground(Color.green);

    // Display X wins
    textField.setText("X wins");

    // Disable all buttons
    for (JButton gameButton : gameButtons) {
      gameButton.setEnabled(false);
    }
  }

  public void oWins(int a, int b, int c) {
    // Set winner buttons to green
    gameButtons[a].setBackground(Color.green);
    gameButtons[b].setBackground(Color.green);
    gameButtons[c].setBackground(Color.green);

    // Display O wins
    textField.setText("O wins");

    // Disable all buttons
    for (JButton gameButton : gameButtons) {
      gameButton.setEnabled(false);
    }
  }
}
