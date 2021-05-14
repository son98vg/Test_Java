### install env
```pip install -r requirements.txt```


### Gen file from proto file
```
python -m grpc_tools.protoc --proto_path=. ./helloworld.proto --python_out=. --grpc_python_out=.
```
