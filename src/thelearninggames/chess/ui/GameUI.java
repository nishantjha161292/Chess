package thelearninggames.chess.ui;
import com.sun.tools.javac.util.Pair;
import thelearninggames.chess.core.Game;
import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.player.PlayerFactory;
import thelearninggames.chess.player.PlayerType;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameUI extends JFrame implements MouseListener{

    private Thread t;
    private Game game;
    private JPanel board;
    private JPanel[] tiles;
    private JLabel[] peices; // for test
    private JMenuBar menuBar;
    public  static volatile int firstSelection = -1;
    public  static volatile int secondSelection = -1;
    private int prevselection = -1;


    public GameUI() {
        super("Chess");
        game = new Game(this, PlayerFactory.getPlayers(PlayerType.UI, PlayerType.UI));

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
            peices[i] = new JLabel();
            peices[i].setForeground(Color.CYAN);
            tiles[i] = new JPanel();
            tiles[i].setName(String.valueOf(i));
            tiles[i].add(peices[i]);
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
                peices[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource(filename)));
            }
            else
                peices[i].setIcon(null);
        }
        super.repaint();
    }

    public static Pair<Integer,Integer> getLast2Clicks(){
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
        if(firstSelection == selection){
            firstSelection = - 1;
            p.setBorder(BorderFactory.createEmptyBorder());;
        }
        else if(firstSelection == -1){
            firstSelection = selection;
            p.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        }
        else if(secondSelection == -1){
            secondSelection = selection;
            p.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            if(firstSelection != -1)
                tiles[firstSelection].setBorder(BorderFactory.createEmptyBorder());
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
}
