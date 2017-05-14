/*
 * Copyright (c) 2011, Carl Burch.
 * 
 * This file is part of the Logisim source code. The latest
 * version is available at http://www.cburch.com/logisim/.
 *
 * Logisim is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Logisim is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Logisim; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.cburch.logisim;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import com.cburch.logisim.gui.start.Startup;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	public static final LogisimVersion VERSION = LogisimVersion.get(3, 0, 2); //�߿�:���ο� ���� ����� ���, �����Ͻʽÿ�.
	public static final String VERSION_NAME = VERSION.toString();
	public static final int COPYRIGHT_YEAR = 2017;
	
	public void start(Stage primaryStage) {
		try{
			String Ver = "3.0.2"; //�߿�:���ο� ���� ����� ���, �����Ͻʽÿ�.
			int VerInt = 2; //�߿�:���ο� ���� ����� ���, �����Ͻʽÿ�.
			
			URL url = new URL("http://logisimkr.pe.hu/downloads/Update.Info");
			URLConnection urlCon = url.openConnection();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String data = bufReader.readLine();
			String[] dataArr = data.split(";");
			
			if(Integer.parseInt(dataArr[0])>VerInt){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("LogisimKR �ڵ� ������Ʈ");
				alert.setHeaderText(Ver + " �� " + dataArr[1] + " ������Ʈ�� �����մϴ�!");
				alert.setContentText("���ο� ������ ����� �� �ֽ��ϴ�.\n���� ������Ʈ�� �ٿ�ε� �Ͻðڽ��ϱ�?\n\n" + dataArr[2].replace("��", "\n"));
				
				//ButtonType exeButton = new ButtonType("exe���Ϸ� �ٿ�ε�");
				ButtonType jarButton = new ButtonType("jar���Ϸ� �ٿ�ε�");
				ButtonType cancelButton = new ButtonType("���", ButtonData.CANCEL_CLOSE);
				
				alert.getButtonTypes().setAll(/*exeButton, */jarButton, cancelButton);
				
				Optional<ButtonType> result = alert.showAndWait();
				/*if (result.get() == exeButton){
					FileChooser fileChooser = new FileChooser();
					FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("exe���� (*.exe)", "*.exe");
					fileChooser.setInitialFileName("LogisimKR-" + dataArr[1]);
					fileChooser.setTitle("LogisimKR�� �ٿ�ε��� ��ġ�� �����ϼ���.");
		            fileChooser.getExtensionFilters().add(exeFilter);
		            File file = fileChooser.showSaveDialog(primaryStage);
		            
		            if(file != null){
		            	FileOutputStream fOutputStream = new FileOutputStream(file.getPath());
		            	InputStream iStream = new URL(dataArr[3]).openStream();
		            	byte[] exeBuf = new byte[1024];
		            	double Length = 0;
		            	double Temp = 0;
		            	while ((Length = iStream.read(exeBuf)) > 0) {
		    				Temp += Length / 1024 / 1024;
		    				fOutputStream.write(exeBuf, 0, (int)Length);
		    			}
		            	fOutputStream.close();
		            	iStream.close();
		            	Alert exeAlert = new Alert(AlertType.INFORMATION);
		            	exeAlert.setTitle("LogisimKR �ڵ� ������Ʈ");
		            	exeAlert.setHeaderText("������Ʈ �ٿ�ε带 �Ϸ��߽��ϴ�!");
		            	exeAlert.setContentText("���������� ������Ʈ�� �ٿ�ε��߽��ϴ�.\n�� â�� �ݰ�, ������Ʈ�� ���Ϸ� �����Ͻñ� �ٶ��ϴ�.");
		            	exeAlert.show();
		            }
				} else */if (result.get() == jarButton) {
					FileChooser fileChooser = new FileChooser();
					FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("jar���� (*.jar)", "*.jar");
					fileChooser.setInitialFileName("LogisimKR-" + dataArr[1]);
					fileChooser.setTitle("LogisimKR�� �ٿ�ε��� ��ġ�� �����ϼ���.");
		            fileChooser.getExtensionFilters().add(exeFilter);
		            File file = fileChooser.showSaveDialog(primaryStage);
		            
		            if(file != null){
		            	FileOutputStream fOutputStream = new FileOutputStream(file.getPath());
		            	InputStream iStream = new URL(dataArr[3]).openStream();
		            	byte[] jarBuf = new byte[1024];
		            	double Length = 0;
		            	double Temp = 0;
		            	while ((Length = iStream.read(jarBuf)) > 0) {
		            		Temp += Length / 1024 / 1024;
		    				fOutputStream.write(jarBuf, 0, (int)Length);
		            	}
		            	fOutputStream.close();
		            	iStream.close();
		            	Alert jarAlert = new Alert(AlertType.INFORMATION);
		            	jarAlert.setTitle("LogisimKR �ڵ� ������Ʈ");
		            	jarAlert.setHeaderText("������Ʈ �ٿ�ε带 �Ϸ��߽��ϴ�!");
		            	jarAlert.setContentText("���������� ������Ʈ�� �ٿ�ε��߽��ϴ�.\n�� â�� �ݰ�, ������Ʈ�� ���Ϸ� �����Ͻñ� �ٶ��ϴ�.");
		            	jarAlert.show();
		            }
				}
			}
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("OOPS!");
			alert.setHeaderText("������Ʈ ������ ������ �� �����ϴ�.");
			alert.setContentText("PC�� �������� �����̰ų�, ������ ������ ������ϴ�.");
			

			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			String exceptionText = stringWriter.toString();

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(textArea, 0, 0);
			
			alert.getDialogPane().setExpandableContent(expContent);
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		Startup startup = Startup.parseArgs(args);
		if (startup == null) {
			System.exit(0);
		} else {
			startup.run();
		}
		launch(args);
	}
}
