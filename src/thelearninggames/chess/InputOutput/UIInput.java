package thelearninggames.chess.InputOutput;


import thelearninggames.chess.ui.GameUI;


public class UIInput implements InputManager,OutputManager {

    GameUI ui;

    public UIInput(GameUI ui){
        this.ui = ui;
    }

    int from = -1;
    int to = -1;

    void update(){
        while(from == -1 || to == -1){
            ui.getLastMove();
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }

    }

    @Override
    public int getFrom() {
        update();
        int temp = from;
        from = -1;
        return temp;
    }

    @Override
    public int getTo() {
        int temp = to;
        to = -1;
        return temp;
    }

    @Override
    public void setFrom(int a) {
        from = a;
    }

    @Override
    public void setTo(int a) {
        to = a;

    }
}
