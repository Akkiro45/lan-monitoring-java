// Client Side
import java.util.*;
import java.io.*;
import java.net.*;
class client {
	public static void main(String str[])throws Exception
	{
		String msg, temp, strPid = "";
		int c, pid;
		Scanner sc = new Scanner(System.in);
		try {
			Socket s = new Socket("localhost", 8888);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			System.out.println("Connected to server.");
			while(true) {
				temp = in.readUTF();
				if(temp.equals("exit")) {
					out.writeUTF(" ");
					System.out.println("Connection terminated!");
					break;
				} 
				if((temp.charAt(0)) == ('2')) {
					c = Character.getNumericValue(temp.charAt(0));
					for(int i=1; i<temp.length(); i++) {
						strPid = strPid + temp.charAt(i);
					}
					pid = Integer.parseInt(strPid);
					msg = exec(c, pid);
					out.writeUTF(msg);
				}
				else {
					c = Integer.parseInt(temp);
					msg = exec(c, 0);
					out.writeUTF(msg);
				}
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public static String exec(int c, int pid) {
		String data;
		try {
			Process p = Runtime.getRuntime().exec("tasklist");
			switch(c) {
				case 1:
					p = Runtime.getRuntime().exec("tasklist");
					data = getResult(p);
					return data;
				case 2:
					p = Runtime.getRuntime().exec("taskkill /PID " + pid);
            		data = getResult(p);
					return data;
				case 3:
					p = Runtime.getRuntime().exec("shutdown /s");
					return "Shutdown";
				case 4:
					p = Runtime.getRuntime().exec("shutdown /r");
					return "Restarted";
				case 5:
					p = Runtime.getRuntime().exec("shutdown /l");
					return "Logedoff";
				default:
					return "Invalid Option!";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "";
	}
	public static String getResult(Process p) {
		String res;
		String msg = "";
	    try {	
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			res = stdInput.readLine();
			msg = res;
			if(res != null) {
				System.out.println(res);
				while ((res = stdInput.readLine()) != null) {
					msg = msg + "\n" + res;
				}
			}
			res = stdError.readLine();
			if(res != null) {
				System.out.println(res);
			}
		} 
		catch (IOException e) {
			System.out.println("Exception");
			System.out.println(e);
		}
		return msg;
	}
}