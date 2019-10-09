package game;

import DatabaseHelper.DiceDatabaseHelper;

public class Token {
    private int position = -1;
    private DiceDatabaseHelper diceDatabaseHelper = new DiceDatabaseHelper();
    public void place(String emailId, int startingPoint) {
        diceDatabaseHelper.insert(emailId, startingPoint);
        this.position = startingPoint;
    }

    public void setPosition(String emailId, int numberOnDice) {
        this.position +=numberOnDice;
        diceDatabaseHelper.updatePosition(emailId, this.position);
    }

    public int getPosition(String emailId) {
        return position;
    }
}
