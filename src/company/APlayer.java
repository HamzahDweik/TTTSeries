package company;

public abstract class APlayer {

    //holds the name and mark of the player
    private String name;
    private String mark;

    //initializes the name and mark
    public APlayer(String name, String mark){
        setName(name);
        setMark(mark);
    }

    //returns the name of the player
    public String getName(){
        return name;
    }

    //sets the name of the player
    public void setName(String name){
        this.name = name;
    }

    //gets the mark of the player
    public String getMark(){
        return mark;
    }

    //sets the mark of the player
    public void setMark(String mark){
        this.mark = mark;
    }

    //abstracts the getBoard method
    public abstract int getBoard(int range);

    //abstracts the select value method
    public abstract int selectValue(int range);

}
