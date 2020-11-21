package GameUtilities;

public class MessageHandler {
    private static MessageHandler msgHandler=null;

    private MessageHandler(){

    }

    public static MessageHandler getMsgHandler(){
        if(msgHandler==null)
            msgHandler=new MessageHandler();
        return msgHandler;
    }

    public void SendMessage(String msg){
        System.out.println(msg);
    }

}
