package home.exception;

/**
 * Created by qijie on 2016/3/30.
 */
public class ShowMessageException extends RuntimeException {

    public ShowMessageException(String message){
        super(message);
    }
    public ShowMessageException(){
        super();
    }
}
