package com.aprobeni.selenium.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GitConfig {
		public static File configFile = new File("data.cfg");
		private static String username = "your_username";
		private static String password = "your_password";
		
		public static void init() {
			
			try {
				if (!configFile.exists()) configFile.createNewFile();
				
				BufferedReader br = new BufferedReader(new FileReader(configFile));
				while (br.ready()) {
					String sor = br.readLine();
					try {
						String[] cuccos = sor.split("=",2);
						String key = cuccos[0];
						String value = cuccos[1];
						
						if (key.equals("username")) username = value;
						if (key.equals("password")) password = value;
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("----------------------------------------");
						System.out.println("Invalid config file syntax in line: " + sor);
						e.printStackTrace();
						System.out.println("----------------------------------------");
					}
				}
				br.close();
				BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));
				bw.write("username=" + username);
				bw.write("\npassword=" + password);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static String getUsername() {
			return username;
		}

		public static String getPassword() {
			return password;
		}
}
