package sistema;

import java.awt.EventQueue;
import vista.FrmMain;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frmMain = new FrmMain();
					frmMain.setVisible(true);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
