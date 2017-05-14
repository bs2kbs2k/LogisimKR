/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.cburch.logisim.gui.main.Frame;
import com.cburch.logisim.gui.opts.OptionsFrame;
import com.cburch.logisim.gui.prefs.PreferencesFrame;
import com.cburch.logisim.proj.Project;
import com.cburch.logisim.proj.ProjectActions;
import com.cburch.logisim.util.MacCompatibility;

class MenuStore extends Menu implements ActionListener {
	private LogisimMenuBar menubar;

	public MenuStore(LogisimMenuBar menubar) {
		this.menubar = menubar;
		int menuMask = getToolkit().getMenuShortcutKeyMask();
		this.addActionListener(this);
	}

	public void localeChanged() {
		this.setText("½ºÅä¾î");
	}

	@Override
	void computeEnabled() {
		setEnabled(true);
		menubar.fireEnableChanged();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		Project proj = menubar.getProject();
		if (src == this) {
			System.out.println("Store Clicked");
		}
	}
}
