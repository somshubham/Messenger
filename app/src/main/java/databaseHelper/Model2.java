package databaseHelper;

/**
 * Created by som on 26/02/17.
 */

public class Model2 {



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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;

    public Model2(String senderNumber,String msg)
    {

        this.senderNumber=senderNumber;
        this.msg=msg;
    }

    public Model2(int id,String senderNumber,String msg)
    {
        this.id=id;
        this.senderNumber=senderNumber;
        this.msg=msg;
    }

    Model2()
    {

    }
}
