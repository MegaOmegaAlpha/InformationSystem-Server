package connection;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;

public class MainClient1 {

    private static final Logger logger = Logger.getLogger(MainClient1.class);
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 0066);
            while (true) {
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                String answer = (String) objectInputStream.readObject();
                System.out.println(answer);
            }
        } catch (ConnectException e) {
            logger.error("Connection is not set", e);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
