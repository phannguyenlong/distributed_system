import javax.xml.ws.Endpoint;

import service.UserServiceImple;

/**
 * This is the server
 * Server will publish the the service on the URL
 */
public class ServerPublisher {
    public static void main(String[] args) {
        String WS_URL = "http://localhost:8080/ws/users";

        // start the service, can access to the url above in broswer
        // register the implementation at that url
		Endpoint.publish(WS_URL, new UserServiceImple());
		System.out.println("Services are running on the URL " + WS_URL);
	}
}