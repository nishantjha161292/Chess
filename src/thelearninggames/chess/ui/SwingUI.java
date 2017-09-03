package thelearninggames.chess.ui;
import thelearninggames.chess.core.Game;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.player.InputManager;
import thelearninggames.chess.player.PlayerFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SwingUI extends JFrame implements MouseListener, GameUI, InputManager{

    private Thread t;
    private Game game;
    private JPanel board;
    private JPanel[] tiles;
    private JLabel[] pieces; // for test
    private JMenuBar menuBar;
    private static int moveNumber = 0;
    public  static volatile int firstSelection = -1;
    public  static volatile int secondSelection = -1;
    private int prevselection = -1;
    private JLabel currentPlayer = new JLabel("Current Player :      ");
    private GameState gs= new GameState();
   

    public SwingUI() {
        super("Chess");
        game = new Game(this, PlayerFactory.getPlayers(this,this));

        this.setMinimumSize(new Dimension(550,550));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        board = new JPanel();
        board.setBackground(Color.DARK_GRAY);
        board.setMinimumSize(new Dimension(500,500));
        board.setLayout(new GridLayout(8,8,2,2));
        tiles = new JPanel[64];
        pieces = new JLabel[64];

        boolean startWithWhite = true;
        for(int i = 0; i < 64; i++){
            pieces[i] = new JLabel();
            pieces[i].setForeground(Color.CYAN);
            tiles[i] = new JPanel();
            tiles[i].setName(String.valueOf(i));
            tiles[i].add(pieces[i]);
            tiles[i].setSize(new Dimension(50,50));
            tiles[i].addMouseListener(this);
            if(i%8 == 0)
                startWithWhite = !startWithWhite;

            if(startWithWhite){
                if(i%2 == 0 )
                    tiles[i].setBackground(Color.WHITE);
                else
                    tiles[i].setBackground(Color.GRAY);
            }
            else{
                if(i%2 == 0 )
                    tiles[i].setBackground(Color.GRAY);
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
        menuBar.add(currentPlayer);
        this.add(menuBar,BorderLayout.PAGE_START);
        this.add(board,BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void repaint() {
        Piece[] state = game.getState().getState();
        for(int i = 0; i < 64; i ++) {
            if(state[i] != null){
                String filename = "resource/"+state[i].toString()+state[i].getColor().toString()+".png";
                pieces[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource(filename)));
            }
            else
                pieces[i].setIcon(null);
        }
        currentPlayer.setText("Current Player : " + game.getCurrentPlayer().getColor().toString());
        firstSelection = -1;
        secondSelection = -1;
        super.repaint();
    }

    public Pair<Integer,Integer> getLastMove(){
        int first = firstSelection;
        int second = secondSelection;
        if(first != -1 && second != -1) {
            firstSelection = -1;
            secondSelection = -1;
        }
        return new Pair<>(first, second);
    }

    @Override
    public synchronized void  mouseClicked(MouseEvent e) {
        JPanel p = (JPanel)e.getSource();

        int selection = Integer.parseInt(p.getName());
        if(moveNumber%2 == 0){
        	if(firstSelection == selection || game.getState().at(selection) == null || game.getState().at(selection).getColor() != game.getCurrentPlayer().getColor()){
                firstSelection = - 1;
                secondSelection = -1;
                p.setBorder(BorderFactory.createEmptyBorder());
            }
        	else{
        		firstSelection = selection;
                p.setBorder(BorderFactory.createLineBorder(Color.CYAN));
                moveNumber++;
        	}
                
            }
       	else{
       		secondSelection = selection;
            p.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            if(firstSelection != -1)
                tiles[firstSelection].setBorder(BorderFactory.createEmptyBorder());
            moveNumber++;
       	}
        if(prevselection != -1)
            tiles[prevselection].setBorder(BorderFactory.createEmptyBorder());
        prevselection = selection;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public int getFrom() {
        while(firstSelection == -1){
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){

            }
        }
        return firstSelection;
    }

    @Override
    public int getTo() {
        while(secondSelection == -1){
            try {
                Thread.sleep(500);
            }catch(InterruptedException e){

            }
        }
        int temp = secondSelection;
        firstSelection = -1;
        secondSelection = -1;
        return temp;
    }
}
