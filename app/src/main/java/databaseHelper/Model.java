package databaseHelper;

/**
 * Created by som on 26/02/17.
 */

public class Model {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderName(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    int id;
    String senderNumber;


    public Model(String senderNumber)
    {

        this.senderNumber=senderNumber;
    }

    public Model(int id,String senderNumber)
    {
        this.id=id;
        this.senderNumber=senderNumber;
    }

    Model()
    {

    }
}
