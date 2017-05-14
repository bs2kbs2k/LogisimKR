/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.cburch.logisim.gui.generic.LFrame;
import com.cburch.logisim.util.MacCompatibility;

class MenuLogisimKR extends JMenu implements ActionListener {
	private LogisimMenuBar menubar;
	private JMenuItem errorReport = new JMenuItem();
	private JMenuItem visitCafe = new JMenuItem();
	private LFrame helpFrame;

	public MenuLogisimKR(LogisimMenuBar menubar) {
		this.menubar = menubar;

		errorReport.addActionListener(this);
		visitCafe.addActionListener(this);

		add(errorReport);
		if (!MacCompatibility.isAboutAutomaticallyPresent()) {
			addSeparator();
			add(visitCafe);
		}
	}

	public void localeChanged() {
		this.setText(Strings.get("logisimkrMenu"));
		errorReport.setText(Strings.get("logisimkrErrorReport"));
		visitCafe.setText(Strings.get("logisimkrVisitCafe"));
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == errorReport) {
			try {
				URI cafeURI = new URL("http://logisimkr.ga/ErrorReport/").toURI();
				java.awt.Desktop.getDesktop().browse(cafeURI);
			}catch(Exception err) { }
		} else if (src == visitCafe) {
			try {
				URI cafeURI = new URL("http://cafe.naver.com/powdertoy/").toURI();
				java.awt.Desktop.getDesktop().browse(cafeURI);
			}catch(Exception err) { }
		}
	}
	
	/*private void loadBroker() {
		String helpUrl = Strings.get("helpsetUrl");
		if (helpUrl == null) helpUrl = "doc/doc_en.hs";
		if (helpSet == null || helpFrame == null || !helpUrl.equals(helpSetUrl)) {
			ClassLoader loader = MenuLogisimKR.class.getClassLoader();
			try {
				URL hsURL = HelpSet.findHelpSet(loader, helpUrl);
				if (hsURL == null) {
					disableHelp();
					JOptionPane.showMessageDialog(menubar.getParentWindow(),
							Strings.get("helpNotFoundError"));
					return;
				}
				helpSetUrl = helpUrl;
				helpSet = new HelpSet(null, hsURL);
				helpComponent = new JHelp(helpSet);
				if (helpFrame == null) {
					helpFrame = new LFrame();
					helpFrame.setTitle(Strings.get("helpWindowTitle"));
					helpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					helpFrame.getContentPane().add(helpComponent);
					helpFrame.pack();
				} else {
					helpFrame.getContentPane().removeAll();
					helpFrame.getContentPane().add(helpComponent);
					helpComponent.revalidate();
				}
			} catch (Exception e) {
				disableHelp();
				e.printStackTrace();
				JOptionPane.showMessageDialog(menubar.getParentWindow(),
						Strings.get("helpUnavailableError"));
				return;
			}
		}
	}*/
/*
	private void showHelp(String target) {
		loadBroker();
		try {
			helpComponent.setCurrentID(target);
			helpFrame.toFront();
			helpFrame.setVisible(true);
		} catch (Exception e) {
			disableHelp();
			e.printStackTrace();
			JOptionPane.showMessageDialog(menubar.getParentWindow(),
					Strings.get("helpDisplayError"));
		}
	}
*/
}
