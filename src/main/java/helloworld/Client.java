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

    public void greet (String name ) {
        System.out.println("");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = null;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            System.out.println(e);
            return;
        }
        System.out.println(response);
        try {
            response = blockingStub.sayHelloAgain(request);
        } catch ( StatusRuntimeException e) {
            System.out.println(e);
            return;
        }
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) {
        String user = "People";
        String target = "localhost:50051";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();

        Client client = new Client(channel);

        client.greet(user);

    }
}
