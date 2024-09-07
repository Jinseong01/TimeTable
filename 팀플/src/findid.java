import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
public class findid extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField typingusername;
	private JTextField typingusername2;
	private JTextField typingid;
	private CourseSelectionFrame courseSelectionFrame;
	public findid() {
		super("사용자 아이디/비밀번호 찾기");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel findid = new JLabel("아이디 찾기");
		findid.setBounds(97, 25, 70, 15);
		contentPane.add(findid);
		
		JLabel findpw = new JLabel("비밀번호 찾기");
		findpw.setBounds(340, 25, 84, 15);
		contentPane.add(findpw);
		
		JLabel username = new JLabel("사용자 이름");
		username.setBounds(12, 85, 70, 15);
		contentPane.add(username);
		
		typingusername = new JTextField();
		typingusername.setBounds(88, 82, 116, 21);
		contentPane.add(typingusername);
		typingusername.setColumns(10);
		
		JLabel username2 = new JLabel("사용자 이름");
		username2.setBounds(260, 85, 70, 15);
		contentPane.add(username2);
		
		JLabel id = new JLabel("아이디");
		id.setBounds(272, 146, 44, 15);
		contentPane.add(id);
		
		typingusername2 = new JTextField();
		typingusername2.setBounds(340, 82, 116, 21);
		contentPane.add(typingusername2);
		typingusername2.setColumns(10);
		
		typingid = new JTextField();
		typingid.setBounds(340, 143, 116, 21);
		contentPane.add(typingid);
		typingid.setColumns(10);
		
		JButton findidbtn = new JButton("찾기");
		findidbtn.addActionListener(new ActionListener() {
			 

			public void actionPerformed(ActionEvent e) {
				 String inputtedUsername = typingusername.getText();
				try {
                    BufferedReader reader = new BufferedReader(new FileReader("./data/info.txt"));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        String storedUsername = parts[2].trim();

                        if (inputtedUsername.equals(storedUsername)) {
                            String foundID = parts[0].trim();

                            // 사용자에게 찾은 아이디를 알려주는 다이얼로그 표시
                            JOptionPane.showMessageDialog(null, "찾은 아이디: " + foundID, "아이디 찾기 결과", JOptionPane.INFORMATION_MESSAGE);

                            // 현재 프레임 닫기
                          
                            return;
                        }
                    }

                    // 사용자 이름과 일치하는 정보를 찾지 못한 경우
                    JOptionPane.showMessageDialog(null, "입력된 이름과 일치하는 정보를 찾을 수 없습니다.", "아이디 찾기 결과", JOptionPane.WARNING_MESSAGE);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
			}
		});
		findidbtn.setBounds(70, 177, 97, 23);
		contentPane.add(findidbtn);
		
		JButton findpwbtn = new JButton("찾기");
		findpwbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//비밀번호 찾기String inputtedUsername = typingusername2.getText();
				  String inputtedUsername = typingusername2.getText();
				String inputtedID = typingid.getText();

		        try {
		            BufferedReader reader = new BufferedReader(new FileReader("./data/info.txt"));
		            String line;

		            while ((line = reader.readLine()) != null) {
		                String[] parts = line.split(":");
		                String storedUsername = parts[2].trim();
		                String storedID = parts[0].trim();

		                if (inputtedUsername.equals(storedUsername) && inputtedID.equals(storedID)) {
		                    String foundPassword = parts[1].trim();

		                    // 사용자에게 찾은 비밀번호를 알려주는 다이얼로그 표시
		                    JOptionPane.showMessageDialog(null, "찾은 비밀번호: " + foundPassword, "비밀번호 찾기 결과", JOptionPane.INFORMATION_MESSAGE);

		                    // 현재 프레임 닫기
		                    dispose();
		                    return;
		                }
		            }

		            // 입력된 아이디와 사용자 이름과 일치하는 정보를 찾지 못한 경우
		            JOptionPane.showMessageDialog(null, "입력된 정보와 일치하는 비밀번호를 찾을 수 없습니다.", "비밀번호 찾기 결과", JOptionPane.WARNING_MESSAGE);

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		findpwbtn.setBounds(327, 229, 97, 23);
		contentPane.add(findpwbtn);
	}
	
}
