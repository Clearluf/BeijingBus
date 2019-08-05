package com.joe.UI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import com.joe.test.BusUtil;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;

public class Busui {
	private JFrame frmByjoe;
	private JTextField LineName;
	String lineName;
	String lineID;
	String stationID;
	ArrayList<String> bus = new ArrayList<String>();
	ArrayList<String> station = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busui window = new Busui();
					window.frmByjoe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Busui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmByjoe = new JFrame();
		frmByjoe.setResizable(false);
		frmByjoe.getContentPane().setFont(new Font("宋体", Font.ITALIC, 14));
		frmByjoe.setTitle("\u5317\u4EAC\u5B9E\u65F6\u516C\u4EA4\u67E5\u8BE2 By_Joe");
		frmByjoe.setBounds(100, 100, 501, 556);
		frmByjoe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("公交线路");
		label.setBounds(24, 82, 56, 30);
		label.setFont(new Font("宋体", Font.ITALIC, 14));
		LineName = new JTextField();
		LineName.setBounds(110, 82, 213, 30);
		LineName.setFont(new Font("宋体", Font.ITALIC, 14));
		LineName.setColumns(10);

		JButton check = new JButton("Check");
		check.setFont(new Font("宋体", Font.ITALIC, 14));
		check.setBounds(356, 82, 100, 30);

		JLabel label_1 = new JLabel("方向");
		label_1.setFont(new Font("宋体", Font.ITALIC, 14));
		label_1.setBounds(24, 139, 35, 30);
		JComboBox<String> box1 = new JComboBox<String>();
		box1.setBounds(110, 139, 213, 30);
		JButton Button1 = new JButton("Check");
		Button1.setFont(new Font("宋体", Font.ITALIC, 14));
		Button1.setBounds(356, 139, 100, 30);

		JLabel label_2 = new JLabel("站点");
		label_2.setFont(new Font("宋体", Font.ITALIC, 14));
		label_2.setBounds(24, 203, 35, 30);
		JComboBox<String> box2 = new JComboBox<String>();
		box2.setBounds(110, 203, 213, 30);
		JButton Button2 = new JButton("Check");
		Button2.setFont(new Font("宋体", Font.ITALIC, 14));
		Button2.setBounds(356, 203, 100, 30);

		frmByjoe.getContentPane().setLayout(null);
		frmByjoe.getContentPane().add(label);
		frmByjoe.getContentPane().add(LineName);
		frmByjoe.getContentPane().add(label_1);
		frmByjoe.getContentPane().add(box2);

		frmByjoe.getContentPane().add(check);
		frmByjoe.getContentPane().add(Button1);
		frmByjoe.getContentPane().add(Button2);
		frmByjoe.getContentPane().add(box1);
		frmByjoe.getContentPane().add(label_2);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(110, 275, 346, 30);
		frmByjoe.getContentPane().add(formattedTextField);

		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u7ED3\u679C");
		lblNewLabel.setFont(new Font("宋体", Font.ITALIC, 14));
		lblNewLabel.setBounds(24, 274, 70, 30);
		frmByjoe.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("src/buslogo.jpg"));
		lblNewLabel_1.setBounds(198, 10, 100, 50);
		frmByjoe.getContentPane().add(lblNewLabel_1);

		check.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lineName = LineName.getText();
				new BusUtil();
				bus = BusUtil.selectLine(lineName);
				box1.removeAllItems();
				box1.addItem(bus.get(0).split(" ")[0]);
				box1.addItem(bus.get(1).split(" ")[0]);
			}
		});

//        Button1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {}
//        });
//        
//        Button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {}
//        });
		Button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				box2.removeAllItems();
				// TODO Auto-generated method stub
				int lineIndex = box1.getSelectedIndex();
				lineID = bus.get(lineIndex).split(" ")[1];
				new BusUtil();
				station = BusUtil.getStationList(lineName, lineID);
				for (int i = 0; i < station.size(); i++) {
					box2.addItem(station.get(i));
				}
			}
		});
		Button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int stationIndex = box2.getSelectedIndex();
				String result = new BusUtil().getArrivingTime(lineName, lineID, stationIndex);
				System.out.println(lineName + " " + lineID + " " + stationIndex);
				formattedTextField.setText(result);
			}
		});
	}
}