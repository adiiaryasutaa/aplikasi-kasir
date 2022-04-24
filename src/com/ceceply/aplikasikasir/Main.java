package com.ceceply.aplikasikasir;

import com.ceceply.aplikasikasir.GUI.MainGUI;

import javax.swing.*;

/**
 *
 *
 */
public class Main {

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		startApp();
	}

	/**
	 *
	 *
	 */
	public static void startApp() {
		MainGUI mainGUI = new MainGUI();
		mainGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainGUI.setVisible(true);
	}
}
