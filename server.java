// Server Side
import java.util.*;
import java.io.*;
import java.net.*;
class server {
	public static void main(String str[])throws Exception
	{
		String msg, temp="", choice="", pid="";
		int c, flag=1, eFlag=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Waiting for client...");
		try {
			ServerSocket ss = new ServerSocket(8888);
			Socket s = ss.accept();
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			System.out.println("Connected to client.");
			while(true) {
				System.out.print("Server: ");
				System.out.println("\n--------Menu--------\n1.List of Process\n2.Kill process\n3.Shutdown\n4.Restart\n5.Log off\n6.Exit\nEnter your choice: ");
	        	c = sc.nextInt();
	        	if(c == 2) {
	        		System.out.print("Enter PID: ");
	        		pid = sc.nextLine();
	        		pid = sc.nextLine();
	        	}
        		switch(c) {
	        		case 1:
						out.writeUTF("1");
						msg = in.readUTF();
						System.out.println("Client: " + msg);
						break;
	        		case 2:
	        			temp = "2" + pid;
	        			out.writeUTF(temp);
						msg = in.readUTF();
						System.out.println("Client: " + msg);
	        			break;
	        		case 3:
	        			out.writeUTF("3");
						msg = in.readUTF();
						System.out.println("Client: " + msg);
						break;
					case 4:
	        			out.writeUTF("4");
						msg = in.readUTF();
						System.out.println("Client: " + msg);
						break;
					case 5:
	        			out.writeUTF("5");
						msg = in.readUTF();
						System.out.println("Client: " + msg);
						break;
	        		case 6:
	 					out.writeUTF("exit");
						System.out.println("Connection terminated!");
						flag = 0;
						break;
	        		default:
	        			System.out.println("InValid Option!");
	        	}	        	
	        	if(flag == 0) {
	        		break;
	        	}		
			}	
		} catch(Exception e) {
			System.out.println(e);
		}
	}	
}
