package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
 
/**
 * This is the interface of the service, will be pass to client
 * Add WebMethod berfore method that client want to use
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {
    @WebMethod
    int sum(int x, int y);

    @WebMethod
    int minus(int x, int y);

    @WebMethod
    int multiply(int x, int y);
}
