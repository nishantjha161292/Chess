package thelearninggames.chess.ui;

import javax.sound.sampled.Clip;
import javax.swing.*;

import IO.IOManager.InputType;

import thelearninggames.chess.core.Game;
import thelearninggames.chess.core.GameState;
import thelearninggames.chess.pieces.Piece;

import java.awt.*;
import java.awt.event.*;

public class Chess extends JFrame{
	
	private Thread t;
    private Game game;
    private JPanel board;
    private JPanel[] tiles;
    private JLabel[] pieces; // for test
    private JMenuBar menuBar;
    private JMenu multiplayer ;
    private JMenuItem server ;
    private JMenuItem client ;
    private JMenuItem startButton;
    
    Clip clip;
   
   
    protected JLabel currentPlayer = new JLabel("Current Player :      ");
    private static Chess object;
   
	private Chess() {
		super("Chess");
        this.setMinimumSize(new Dimension(550,550));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        drawMenuBar();
       
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GameBoardServices.getObject().setInputType(InputType.UI);
                GameBoardServices.getObject().startGame();
                
            }
        });
        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GameBoardServices.getObject().setInputType(InputType.NETWORK_SERVER);
            	GameBoardServices.getObject().startGame();
            }
        });
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GameBoardServices.getObject().setInputType(InputType.NETWORK_CLIENT);
                String ip = JOptionPane.showInputDialog("Enter IP address of server","127.0.0.1");
                GameBoardServices.getObject().startGame(ip);
            }
        });
        
        this.setVisible(true);

    }
	
	public void repaint(GameState state) {
	    Piece[] currentState = state.getState();
	    for(int i = 0; i < 64; i ++) {
	        if(currentState[i] != null){
	            String filename = "resource/"+currentState[i].toString()+currentState[i].getColor().toString()+".png";
	            pieces[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource(filename)));
	        }
	        else
	            pieces[i].setIcon(null);
	    }
	}

	public static Chess newGame() {
		if(object == null){
			object = new Chess();
		}
		
		return object;
	}
	
	public void selectSquare(int squareNumber){
		JPanel p =tiles[squareNumber];
		p.setBorder(BorderFactory.createLineBorder(Color.CYAN));
	}
	
	public void removeSelection(int squareNumber){
		JPanel p =tiles[squareNumber];
		p.setBorder(BorderFactory.createEmptyBorder());
	}

	public void setCurrentPlayer(String player){
		currentPlayer.setText("Current Player : " + player);
	}

	void drawMenuBar(){
	    menuBar = new JMenuBar();
	    startButton = new JMenuItem("SinglePlayer");
	    multiplayer = new JMenu("Multiplayer");
	    server = new JMenuItem("Create Game");
	    client = new JMenuItem("Join Game");
	    menuBar.add(startButton);
	    menuBar.add(multiplayer);
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
	        

	        boolean startWithWhite = true;
	        for(int i = 0; i < 64; i++){
	            pieces[i] = new JLabel();
	            pieces[i].setForeground(Color.CYAN);
	            tiles[i] = new JPanel();
	            tiles[i].setName(String.valueOf(i));
	            tiles[i].add(pieces[i]);
	            tiles[i].setSize(new Dimension(50,50));
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
	            GameBoardViewController.getObject().enableMouseInteraction(tiles[i]);
	        }

	    this.add(board,BorderLayout.CENTER);
	}
}
