package thelearninggames.chess.ui;
import thelearninggames.chess.core.Game;
import thelearninggames.chess.pieces.Piece;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUI extends JFrame{

    private Thread t;
    private Game game;
    private JPanel board;
    private JPanel[] tiles;
    private JLabel[] peices; // for test
    private JMenuBar menuBar;


    public GameUI(){
        super("Chess");
        game = new Game(this);

        this.setMinimumSize(new Dimension(550,550));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        board = new JPanel();
        board.setBackground(Color.DARK_GRAY);
        board.setMinimumSize(new Dimension(500,500));
        board.setLayout(new GridLayout(8,8,2,2));
        tiles = new JPanel[64];
        peices = new JLabel[64];

        boolean startWithWhite = true;
        for(int i = 0; i < 64; i++){
            peices[i] = new JLabel("");
            peices[i].setForeground(Color.CYAN);
            tiles[i] = new JPanel();
            tiles[i].add(peices[i]);
            tiles[i].setSize(new Dimension(50,50));
            if(i%8 == 0)
                startWithWhite = !startWithWhite;

            if(startWithWhite){
                if(i%2 == 0 )
                    tiles[i].setBackground(Color.WHITE);
                else
                    tiles[i].setBackground(Color.BLACK);
            }
            else{
                if(i%2 == 0 )
                    tiles[i].setBackground(Color.BLACK);
                else
                    tiles[i].setBackground(Color.WHITE);
            }

            board.add(tiles[i]);
        }

        menuBar = new JMenuBar();
        JMenuItem start = new JMenuItem("Start");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Game Running");
                if(t == null) {
                    t = new Thread(game);
                    t.start();
                }
            }
        });
        menuBar.add(start);
        this.add(menuBar,BorderLayout.PAGE_START);
        this.add(board,BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void repaint() {
        Piece[] piece = game.getState().getState();
        for(int i = 0; i < 64; i ++) {
            if(piece[i] != null)
                peices[i].setText(piece[i].toString());
            else
                peices[i].setText("");
        }
        super.repaint();
    }
}
