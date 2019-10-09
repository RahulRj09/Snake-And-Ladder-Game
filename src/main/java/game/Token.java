package game;

import DatabaseHelper.DiceDatabaseHelper;

public class Token {
    private int position = -1;

    public void place(String emailId, int startingPoint) {
        DiceDatabaseHelper diceDatabaseHelper = new DiceDatabaseHelper();
        diceDatabaseHelper.updatePosition(emailId, startingPoint);
        this.position = startingPoint;
    }

    public void setPosition(int numberOnDice) {
        this.position +=numberOnDice;
    }

    public int getPosition(String emailId) {
        return position;
    }
}
