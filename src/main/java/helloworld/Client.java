package helloworld;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

public class Client {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    // Contruct Client
    public Client (Channel channel) {
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void greet (String name, String pass ) {

        HelloRequest request = HelloRequest.newBuilder().setName(name).setPass(pass).build();
        HelloReply response = null;

        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println("exception ----- " + e);
            return;
        }
        System.out.println("Response sayHello ----- "+response.getMessage());
        try {
            response = blockingStub.sayHelloAgain(request);
        } catch ( StatusRuntimeException e) {
            System.out.println("exception 2 -----" + e);
            return;
        }
        System.out.println("Response sayHelloAgain ----- "+response.getMessage());
    }

    public static void main(String[] args) {
        String user = "son";
        String pass = "123456";
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();

        Client client = new Client(channel);

        client.greet(user, pass);

    }
}
