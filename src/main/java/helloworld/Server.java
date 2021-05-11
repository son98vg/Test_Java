package helloworld;

import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class Server {
    private io.grpc.Server server;
    private  void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        System.out.println("Server started " + port);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final Server server = new Server();
        server.start();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
//
//        @Override
//        public  void sayHelloAgain(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//            HelloReply reply = HelloReply.newBuilder().setMessage("Hello again " + req.getName()).build();
//            responseObserver.onNext(reply);
//            responseObserver.onCompleted();
//        }
    }
}
