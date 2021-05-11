## How to create client and server to connect through RMI server

1. Create Interface (ex: ServerService)
2. Create A class to implement that database (ex: ServerServiceImp)
3. Create a stub (ServerSerivce) to export instant of of implementation (ServerSerivceImp)
4. Register stub to RMI server with a name
5. Client will lookup for that stub by the same that server register


## How to run

1. Start rmi server
2. Start server and client