File-sharing-system-FSS

The FSS consists of two types of entities: a file server and many clients. 
In this FSS: 
1. The client can upload files to the file server. If the file already exists, replaces it.
2. The client can download a given file from the file server, by providing the  full filename path to the file server. If the file does not already exist, it returns an error message on standard error and return a non-zero error code.
3. The client can list the file system objects (files and directories) on a file  server directory, including the file server’s root (“/”) directory. If the requested directory does not exist, it can report an error message on standard error and return a non-zero  error code.  
4. The client create a directory (if the directory does not exist) and  remove empty directories (if the directory is empty), and can report whether  or not these operations succeeded. If there’s an error, it reports an error message  on standard error and returns a non-zero error code.
5. The client can remove a given file from the file server, by providing the full filename path to the file server. If the file does not already   exist, it returns an error  message on standard error and return a non-zero error code. 
6. The file server can allow multiple clients to simultaneously connect to a single file  server for upload/download, and allow for apparently-simultaneous transfer. 
7. Clients can cleanly shut down the file server. 
8. The system  can support the resume upload and download: If a file transfer between a client and a server is interrupted (because the network, server, or client has failed), the same client can be able to resume upload/download at the file server from the same  point of progress by re-requesting the same filename. This means, the client cannot have to upload/download the data that is already uploaded/downloaded. This system also handles server crashes too, uses “flush()” in the server.


Commands:

To Start the Server-
java -cp FSS.jar Server start
  
To Start the Client-
java -cp FSS.jar Client start

**NOTE: I used fixed port number and host as sending port number to client is not possible before establishing connection. Host I used is “127.0.0.1” and Portnumber is “2323”

To Upload a File to Server-
java -cp FSS.jar Client upload <path_on_client/dir/file.txt> </path/filename/onserver/dir>

To Download a File from Server-
java -cp FSS.jar Client download </path/existing_filename/on/server/file.txt> <path_on_client/dir>

To List Files and Directories of a particular Directory-
java -cp FSS.jar Client dir </path/existing_directory/on/server>

To create a Directory on Server-
java -cp FSS.jar Client mkdir </path/new_directory/on/server>

To Remove a Directory from Server-
java -cp FSS.jar Client rmdir </path/existing_directory/on/server>

To Remove a file from Server-
java -cp FSS.jar Client rm </path/existing_filename/on/server>

To Close Server from Client module-
java -cp FSS.jar Client shutdown

** I did this project on windows machine and I think it works on other machines. I tested the code and it is working perfectly. If by chance, any of the features doesn’t work, please let me know. Thank You.


