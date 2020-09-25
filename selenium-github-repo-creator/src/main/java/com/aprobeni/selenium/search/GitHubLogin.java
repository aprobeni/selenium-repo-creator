package com.aprobeni.selenium.search;

public class GitHubLogin {
	public static String github = "http://www.github.com/login";
	public static void main(String[] args) {
			GitConfig.init();
			Gui gui = new Gui();
			gui.setVisible(true);
		
	}
}
