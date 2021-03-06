from concurrent import futures
import logging

import grpc

import helloworld_pb2
import helloworld_pb2_grpc


class Greeter(helloworld_pb2_grpc.GreeterServicer):

    def SayHello(self, request, context):
        # print("request 1 -----", request)
        return helloworld_pb2.HelloReply(message='Hello, %s!' % request)
    def SayHelloAgain(self, request, context):
        # print("request 2 -----",request)
        if (request.name == 'People'):
            print('true')
            return helloworld_pb2.HelloReply(message = request.name)
        else:    
        # response = helloworld_pb2.HelloReply(message='Hello again, %s!' % request)
            print('false')
            return helloworld_pb2.HelloReply(message=request.name)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    helloworld_pb2_grpc.add_GreeterServicer_to_server(Greeter(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()
    

if __name__ == '__main__':
    logging.basicConfig()
    serve()
