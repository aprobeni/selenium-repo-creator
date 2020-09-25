package com.aprobeni.selenium.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Gui extends JFrame {
	public Gui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/com/aprobeni/selenium/search/github.png")));
		setTitle("GitHub");
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		repoName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, repoName, 35, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, repoName, 5, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, repoName, 58, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, repoName, -5, SpringLayout.EAST, getContentPane());
		repoName.setText("");
		getContentPane().add(repoName);
		repoName.setColumns(10);
		repoName.setVisible(true);
		
		JCheckBox checkMark = new JCheckBox("Public (default private)");
		checkMark.setFont(new Font("Tahoma", Font.PLAIN, 13));
		springLayout.putConstraint(SpringLayout.NORTH, checkMark, 6, SpringLayout.SOUTH, repoName);
		springLayout.putConstraint(SpringLayout.WEST, checkMark, 0, SpringLayout.WEST, repoName);
		getContentPane().add(checkMark);
		
		JCheckBox readmeMark = new JCheckBox("Add readme (Default = no)");
		readmeMark.setFont(new Font("Tahoma", Font.PLAIN, 13));
		springLayout.putConstraint(SpringLayout.NORTH, readmeMark, 0, SpringLayout.SOUTH, checkMark);
		springLayout.putConstraint(SpringLayout.WEST, readmeMark, 0, SpringLayout.WEST, repoName);
		getContentPane().add(readmeMark);
		
		JButton githubButton = new JButton("GitHub login");
		springLayout.putConstraint(SpringLayout.SOUTH, githubButton, 30, SpringLayout.NORTH, getContentPane());
		githubButton.setBackground(Color.LIGHT_GRAY);
		springLayout.putConstraint(SpringLayout.WEST, githubButton, 5, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, githubButton, -5, SpringLayout.EAST, getContentPane());
		githubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					ChromeOptions options = new ChromeOptions();
					options.addArguments("start-maximized");
					WebDriver driver = new ChromeDriver(options);
			        driver.get(GitHubLogin.github);
			        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			        driver.findElement(By.id("login_field")).sendKeys(GitConfig.getUsername());
			        driver.findElement(By.id("password")).sendKeys(GitConfig.getPassword());
			        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			        driver.findElement(By.name("commit")).click();
			        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			        driver.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/summary")).click();
			        driver.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/details-menu/a[1]")).click();
			        
			        repoName.setVisible(true);
			        driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[1]/div/div/div[2]/div/nav/a[2]")).click();
			        try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			        driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[2]/div/div[2]/div[2]/div/div[1]/form/div[2]/a")).click();
			        
			        System.out.println("Text: " + repoName.getText() + " | End ot the repoName's text");
			        if (!repoName.getText().equals("")) {
			        	System.out.println("Working : " + repoName.getText());
			        	driver.findElement(By.xpath("//*[@id=\"repository_name\"]")).sendKeys(repoName.getText());
			        	if (readmeMark.isSelected()) driver.findElement(By.xpath("//*[@id=\"repository_auto_init\"]")).click();
			        	if (!checkMark.isSelected()) driver.findElement(By.xpath("//*[@id=\"repository_visibility_private\"]")).click();
			        	try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			        	driver.findElement(By.xpath("//*[@id=\"new_repository\"]/div[4]/button")).click();
			        	JFrame f = new JFrame();
			            JOptionPane.showMessageDialog(f,"Done with the thread!" + "\n" + "Closing the chrome when clicked 'OK'!", "Info", JOptionPane.INFORMATION_MESSAGE); 
			        	driver.quit();
			        }
			        else {
			        	System.out.println("Error: showing dialog");
			            JFrame f = new JFrame();
			            JOptionPane.showMessageDialog(f,"Please provide a repository name." + "\n" + "Closing the chrome when clicked 'OK'!", "Error", JOptionPane.ERROR_MESSAGE); 
			            driver.quit();
			            System.exit(0);
			        }
				}
		});
		getContentPane().add(githubButton);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int mwidth = dim.width;
		int mheight = dim.height;
		int width = 300;
		int height = 200;
		setBounds((mwidth/2)-(width/2),(mheight/2)-(height/2), width, height);
		
	}
	private static final long serialVersionUID = 1L;
	private JTextField repoName;
}
