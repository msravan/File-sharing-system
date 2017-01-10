
import java.io.*;
import java.net.*;


public class Client {

  static BufferedOutputStream bos = null;
  static Socket sock = null;
  static int bytesRead;
  static OutputStream os,bs=null;
  
  public static void main (String [] args ) throws IOException {
	  sock = new Socket("127.0.0.1", 2323);
      os = sock.getOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(os);
      BufferedWriter cbw = new BufferedWriter(osw);
      cbw.write(args[0]+System.getProperty("line.separator"));
      cbw.flush();  
      if(args[0].equals("start")){
    	  System.out.println("connected");
    	  System.exit(0);
      }
      // FILE DOWNLOAD
      if(args[0].equals("download")){
    	 String sdown=args[1];
    	 String cdown=args[2];    	 
      byte [] mybytearray  = new byte [1000000];
      bs = sock.getOutputStream();
      OutputStreamWriter bsw = new OutputStreamWriter(bs);
      BufferedWriter bbw = new BufferedWriter(bsw);
      bbw.write(sdown+System.getProperty("line.separator"));
      bbw.flush();
      InputStream is1 = sock.getInputStream();
      InputStreamReader isr1 = new InputStreamReader(is1);
      BufferedReader br1 = new BufferedReader(isr1);
      String clientf = br1.readLine();
      int k=br1.read();
      System.out.println(k);
      String complete=cdown+"/"+clientf;
   
      System.out.println(complete);
      
      FileOutputStream fos = new FileOutputStream(complete);
      DataInputStream dis = new DataInputStream(is1);
     bytesRead = dis.read(mybytearray,0,k);
     fos.write(mybytearray,0,bytesRead);
    
    System.out.println("File Downloaded");
    System.out.println("Downloaded "+bytesRead+"bytes" );
    fos.close();
    is1.close();
    

}
      
      //FILE UPLOAD
      if(args[0].equals("upload")){
    	  String sdown=args[2];
    	  String cdown=args[1];
    	  File myfile=new File(cdown);
    	  String z=myfile.getName();
    	  z=sdown+"/"+z;
    	  bs = sock.getOutputStream();
          OutputStreamWriter bsw = new OutputStreamWriter(bs);
          BufferedWriter bbw = new BufferedWriter(bsw);
          System.out.println(z);
          bbw.write(z+System.getProperty("line.separator"));
          bbw.flush();
          bbw.write((int) myfile.length());
          bbw.flush();
          byte [] mybytearray  = new byte [(int)myfile.length()];
          FileInputStream fis = new FileInputStream(myfile);
          
          BufferedInputStream bis = new BufferedInputStream(fis);
          DataInputStream dis = new DataInputStream(bis);
          dis.readFully(mybytearray,0,mybytearray.length);
          
          DataOutputStream dos = new DataOutputStream(os);
          dos.write(mybytearray,0,mybytearray.length);
          System.out.println("Sending " + myfile.getName() + "(" + mybytearray.length + " bytes)");
          
          dos.flush();
          System.out.println("Done.");
          dis.close();
    	  
    	  
    	  
      }
      
     if(args[0].equals("mkdir")){
    	 String md=args[1];
    	 bs = sock.getOutputStream();
         OutputStreamWriter bsw = new OutputStreamWriter(bs);
         BufferedWriter bbw = new BufferedWriter(bsw);
         bbw.write(md+System.getProperty("line.separator"));
         bbw.flush();
         InputStream is = sock.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String op=br.readLine();
         System.out.println(op);
         
         
    	 
     }
     if(args[0].equals("rmdir")){
    	 String md=args[1];
    	 bs = sock.getOutputStream();
         OutputStreamWriter bsw = new OutputStreamWriter(bs);
         BufferedWriter bbw = new BufferedWriter(bsw);
         bbw.write(md+System.getProperty("line.separator"));
         bbw.flush();
         InputStream is = sock.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String op=br.readLine();
         System.out.println(op);
         }
      
     if(args[0].equals("rm")){
    	 String md=args[1];
    	 bs = sock.getOutputStream();
         OutputStreamWriter bsw = new OutputStreamWriter(bs);
         BufferedWriter bbw = new BufferedWriter(bsw);
         bbw.write(md+System.getProperty("line.separator"));
         bbw.flush();
         InputStream is = sock.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String op=br.readLine();
         //String jp=br.readLine();
         System.out.println(op);
         }
     
     if(args[0].equals("dir")){
    	 String md=args[1];
    	 bs = sock.getOutputStream();
         OutputStreamWriter bsw = new OutputStreamWriter(bs);
         BufferedWriter bbw = new BufferedWriter(bsw);
         bbw.write(md+System.getProperty("line.separator"));
         bbw.flush();
    	 File fs=new File(md);
    	 if(!fs.exists() && !fs.isDirectory()){
    		 System.out.println("ERROR-03: File does not Exist");
    	 }
    	 else{
    	 String[] flist=fs.list();
    	 int k=flist.length;
    	 
         InputStream is = sock.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         for(int j=0;j<k;j++){
         String op=br.readLine();
         System.out.println(op);}}
    	 }
     
     
     if(args[0]=="shutdown"){
         InputStream is = sock.getInputStream();
         InputStreamReader isr = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(isr);
         String op=br.readLine();
         System.out.println(op);
         System.exit(0);
    	 
     }
     }
}


