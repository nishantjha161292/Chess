package thelearninggames.chess.ui;
import thelearninggames.chess.core.Game;
import thelearninggames.chess.core.Pair;
import thelearninggames.chess.pieces.Piece;
import thelearninggames.chess.player.InputManager;
import thelearninggames.chess.player.NetworkInputOutput;
import thelearninggames.chess.player.OutputManager;
import thelearninggames.chess.player.PlayerFactory;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private JMenu multiplayer ;
    private JMenuItem server ;
    private JMenuItem client ;
    private JMenuItem start;
    private static volatile int moveNumber = 0;
    public  static volatile int firstSelection = -1;
    public  static volatile int secondSelection = -1;
    private int prevselection = -1;
    private JLabel currentPlayer = new JLabel("Current Player :      ");
    Clip clip;
    InputManager iplayer1 = this;
    InputManager iplayer2 = this;
    OutputManager oplayer1 = null;
    OutputManager oplayer2 = null;
    boolean startWithWhite = true;
    SwingUI that = this;

    public SwingUI() {
        super("Chess");
        this.setMinimumSize(new Dimension(550,550));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        drawMenuBar();
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startgame();
            }
        });
        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NetworkInputOutput n = new NetworkInputOutput();
                iplayer2 = n;
                oplayer1 = n;
                startgame();
            }
        });
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = JOptionPane.showInputDialog("Enter IP address of server","127.0.0.1");
                NetworkInputOutput n = new NetworkInputOutput(ip);
                iplayer1 = n;
                oplayer2 = n;
                startgame();
            }
        });
        this.setVisible(true);
        initMusic();
    }

    void startgame(){
        game = new Game(this, PlayerFactory.getPlayers(iplayer1,oplayer1,iplayer2,oplayer2));
        drawBoard();
        initMusic();
        if(t == null) {
            t = new Thread(game);
            t.start();
        }
        runMusic();
    }

    void drawMenuBar(){
        menuBar = new JMenuBar();
        start = new JMenuItem("SinglePlayer");
        multiplayer = new JMenu("Multiplayer");
        server = new JMenuItem("Create Game");
        client = new JMenuItem("Join Game");
        menuBar.add(start);
        menuBar.add(currentPlayer);
        multiplayer.add(server);
        multiplayer.add(client);
        menuBar.add(multiplayer);
        this.add(menuBar,BorderLayout.PAGE_START);
    }

    void drawBoard(){

        board = new JPanel();
        board.setBackground(Color.DARK_GRAY);
        board.setMinimumSize(new Dimension(500,500));
        board.setLayout(new GridLayout(8,8,2,2));
        tiles = new JPanel[64];
        pieces = new JLabel[64];

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
        this.add(board,BorderLayout.CENTER);
    }

    void initMusic(){
        try{
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("resource/203.wav"));
            clip.open(inputStream);

        }catch(Exception e){
            System.out.print("Sound Exception");
        }
    }

    void runMusic(){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
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
