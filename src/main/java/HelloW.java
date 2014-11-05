import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-11-4
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class HelloW {


    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private BrokerService service;

    public void before() throws Exception {

        service = BrokerFactory.createBroker("xbean:activemq.xml");
        service.start();

        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //destination = session.createQueue("MyQueue");
        destination = session.createTopic("EVENTS.QUOTES");
    }

    public static void main(String[] args) throws Exception {
        System.out.println( "Hello World! papap" );
        HelloW app = new HelloW();
        app.before();
        Thread.sleep(10000);
        System.out.println("server over ");
    }
}
