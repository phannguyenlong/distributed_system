package service;

import javax.jws.WebService;

/**
 * This is the implemntation of the service
 * This will be register and when client lookup will return this implementation
 */
@WebService(endpointInterface = "service.UserService")
public class UserServiceImple implements UserService{
    @Override
    public int sum(int x, int y) {
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        return x - y;
    }

    @Override
    public int multiply(int x, int y) {
        return x * y;
    }
}
