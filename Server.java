
import java.io.*;
import java.net.*;
public class Server {

  static int bytesRead;
  

@SuppressWarnings("resource")
public static void main (String [] args ) throws IOException {
	  
	    FileInputStream fis = null;
	   BufferedInputStream bis = null;
	    OutputStream os;
	    ServerSocket servsock=null;
	    Socket sock = null;
	  if(args[0].equals("start")){
		  System.out.println("Starting the socket server");
	        servsock = new ServerSocket(2323);
	        System.out.println("Waiting for clients...");
	        sock = servsock.accept();
	        System.out.println("Connected");
	  }
	  while(!servsock.isClosed()){
		 sock=servsock.accept();
	  InputStream is = sock.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String option = br.readLine();
     
      
      if(option.equals("download")){
          String clientdown = br.readLine();
          System.out.println(clientdown);
          
          File myFile = new File (clientdown);
          
          String z= myFile.getName();
          System.out.println(z);
          os = sock.getOutputStream();
          OutputStreamWriter osw = new OutputStreamWriter(os);
          BufferedWriter bw = new BufferedWriter(osw);
          bw.write(z+System.getProperty("line.separator"));
          bw.flush();
          bw.write((int) myFile.length());
          bw.flush();
          
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          
          bis = new BufferedInputStream(fis);
          DataInputStream dis = new DataInputStream(bis);
          dis.readFully(mybytearray,0,mybytearray.length);
          
          DataOutputStream dos = new DataOutputStream(os);
          dos.write(mybytearray,0,mybytearray.length);
          System.out.println("Sending " + myFile.getName() + "(" + mybytearray.length + " bytes)");
          dos.flush();
          System.out.println("Done.");
         // if(servsock!=null)servsock.close();
         // dis.close();
        }
      else if(option.equals("upload")){
    	  System.out.println("This is upload");
          String upcomplete = br.readLine();
          System.out.println(upcomplete);
          int k=br.read();
          System.out.println(k);
          byte [] mybytearray  = new byte [1000000];
    	  FileOutputStream fos = new FileOutputStream(upcomplete);
          DataInputStream dis = new DataInputStream(is);
         bytesRead = dis.read(mybytearray,0,k);
         fos.write(mybytearray,0,bytesRead);
    
    System.out.println("File Received");
    System.out.println("Received "+bytesRead+"bytes" );
    fos.close();
    }
      
      else if(option.equals("mkdir")){
    	  boolean bool;
    	  String dirdown = br.readLine();
    	  File d=new File(dirdown);
    	  if(d.isDirectory() && !d.isFile()){
    		  System.out.println("Directory Exists");
    		  
    	  if(d.list().length==0){
    		  System.out.println("Empty Directory");
    		  d.delete();
    		  System.out.println("Directory Deleted");
    		  os = sock.getOutputStream();
    		  OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
    		  bw.write("Empty Directory: Deleted"+System.getProperty("line.separator"));
    		  bw.flush();
    		  
    		  
    		  
    	  }}
    	  else{
    	  bool=d.mkdirs();
    	  if(bool){
    		  System.out.println("New Directory Created");
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              bw.write("New Directory Created "+dirdown+System.getProperty("line.separator"));
              bw.flush();
    		  
    	  }}
    	  
    	  
    	  
      }
      
      else if(option.equals("rmdir")){
    	  String dirdown = br.readLine();
    	  File d=new File(dirdown);
    	  String s=d.getName();
    	  if(d.isDirectory() && d.exists() && !d.isFile()){
    		  d.delete();
    		  System.out.println(s+" directory is deleted");
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              bw.write(s+" directory is deleted"+System.getProperty("line.separator"));
              bw.flush();}
    	  else{
    		  System.out.println("Directory Does not Exist");
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              bw.write("ERROR 04: "+s+" Directory does not Exist"+System.getProperty("line.separator"));
              bw.flush();
    	  }
    		  
    		  
    		  
    	  }
      
      else if(option.equals("rm")){
    	  String dirdown = br.readLine();
    	  File d=new File(dirdown);
    	  String s=d.getName();
    	  if(d.isFile() && !d.isDirectory() && d.exists()){
    		  d.delete();
    		  System.out.println(s+" file is deleted");
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              bw.write(s+" file is deleted"+System.getProperty("line.separator"));
              bw.flush();
    		  }
    	  else{
    		  System.out.println("ERROR 05: File Does not Exist");
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              bw.write("ERROR 05: "+s+" File does not Exist"+System.getProperty("line.separator"));
              bw.flush();
    	  }
    	  
    	  }
      
      else if(option.equals("dir")){
    	  String dirdown = br.readLine();
    	  File d=new File(dirdown);
    	  String[] flist=d.list();
    	 // String s=d.getName();
    	  if(d.isDirectory() && d.exists() && !d.isFile()){
    		  os = sock.getOutputStream();
              OutputStreamWriter osw = new OutputStreamWriter(os);
              BufferedWriter bw = new BufferedWriter(osw);
              for(int i=0; i<flist.length;i++){
              
              String x=flist[i]+System.getProperty("line.separator");
            	  
              bw.write(x);
              }
              
              bw.flush();}
    	  else{
    		  System.out.println("Directory Does not Exist");
    	  }
      }
      
      else if(option.equals("shutdown")){
    	  System.out.println("Server is Shutting down");
    	  os = sock.getOutputStream();
          OutputStreamWriter osw = new OutputStreamWriter(os);
          BufferedWriter bw = new BufferedWriter(osw);
          bw.write("Server Shutdown");
          bw.flush();
          servsock.close();
    	  
    	  
      }
      }
}  
}

