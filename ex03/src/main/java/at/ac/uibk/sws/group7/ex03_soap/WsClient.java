package at.ac.uibk.sws.group7.ex03_soap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by shasi on 4/15/18.
 */
public class WsClient {
    public static void main(final String[] args) throws Throwable {
        if (args.length == 0) {
            System.err.println("No service method specified.");
            System.exit(1);
        }

        Service service = Service.create(
                new URL(WsServer.URL + "?wsdl"),
                new QName("http://ex03_soap.group7.sws.uibk.ac.at/", "ProductServiceService")
        );
        IProductService productService = service.getPort(IProductService.class);

        switch (args[0]) {
            case "getProduct":
                if (args.length > 1) {
                    System.out.println(productService.getProduct(Integer.valueOf(args[1])));
                } else {
                    System.err.println("No product ID specified.");
                    System.exit(1);
                }
                break;
            case "getProductList":
                System.out.println(Arrays.toString(productService.getProductList().toArray()));
                break;
            case "order":
                if (args.length > 2) {
                    boolean ordered = productService.order(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
                    System.out.println(ordered ? "Order placed." : "Order declined.");
                } else {
                    System.err.println("No product ID or quantity specified.");
                    System.exit(1);
                }
                break;
            default:
                System.err.println("Method not found.");
                System.exit(1);
        }

        System.exit(0);
    }
}
