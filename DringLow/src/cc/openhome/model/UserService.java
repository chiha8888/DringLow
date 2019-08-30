package cc.openhome.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;


public class UserService {
	String USERS;
	String CHATTINGROOM="C:/workspace/DringLow/chattingroom";
	
	public UserService(String user) {
		USERS=user;
	}
	
	public boolean checkLogin(String username,String password) throws IOException {
		 if(username!=null&&password!=null) {
			 for(String file:new File(USERS).list()) {
				 if(file.equals(username)) {
					 BufferedReader reader=new BufferedReader(new FileReader(USERS+"/"+file+"/profile"));
					 String pass=reader.readLine().split("\t")[1];
					 if(pass.equals(password)) {
						 reader.close();
						 return true;
					 }
					 reader.close();
				 }
			 }
		 }
		 return false;
	}
	
	public void addMessage(String username,String blabla) throws IOException {
		String file = CHATTINGROOM + "/" + new Date().getTime() + ".txt";
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        writer.write(username+":\r\n"+blabla);
        writer.close();
	}
	
	public void createUserData(String email,String username,String password) throws IOException{
		File userfile=new File(USERS+"/"+username);
		userfile.mkdir();
		BufferedWriter writer=new BufferedWriter(new FileWriter(userfile+"/profile"));
		writer.write(email+'\t'+password);
		writer.close();
	}
	
	public boolean isInvalidUsername(String username) {
		for(String file : new File(USERS).list()) {
			if(file.equals(username))
				return true;
		}
		return username==null;
	}
	
	public TreeMap<Date, String> readMessages() throws IOException{
		File file=new File(CHATTINGROOM);
		String[] txts=file.list();
		TreeMap<Date, String> messages= new TreeMap<Date, String>(new DateComparator());
		for(String txt:txts) {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(CHATTINGROOM+"/"+txt), "UTF-8"));
			String text=null;
			StringBuilder builder=new StringBuilder();
			while((text=reader.readLine())!=null) {
				builder.append(text+"<br>");
				
			}
			Date date=new Date(Long.parseLong(txt.substring(0,txt.indexOf(".txt"))));
			messages.put(date, builder.toString());
			reader.close();
		}
		return messages;
	}
	
	private class TxtFilenameFilter implements FilenameFilter{
		@Override
		public boolean accept(File dir,String name) {
			return name.endsWith(".txt");
		}
	}

	private class DateComparator implements Comparator<Date>{
		@Override
		public int compare(Date o1, Date o2) {
			return -o1.compareTo(o2);
		}
	}
	
}
