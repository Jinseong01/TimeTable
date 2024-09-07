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

public class loginFrame extends JFrame {
    private JPanel myPanel;
    private JLabel ID;
    private JLabel PW;
    private JTextField ID_Data;
    private JPasswordField PW_Data;
    private JButton login;
    private ImageIcon login_img;
    private CourseSelectionFrame courseSelectionFrame;
    private Clip backgroundMusic;
    private ImageIcon musicimg;
    private boolean isMusicPlaying = true;
    private static final String WAV_FILE_PATH = "./data/music1.wav";
    public loginFrame() {
        super("로그인 창");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        playWav(WAV_FILE_PATH);
       
        myPanel = new myPanel();
        setContentPane(myPanel);
        getContentPane().setLayout(null);
        myPanel.revalidate(); // 창 새로고침
        myPanel.repaint();   // 창 새로고침

        ID = new JLabel("학번");
        ID.setLocation(110, 100);
        ID.setSize(70, 30);
        ID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        getContentPane().add(ID);

        ID_Data = new JTextField();
        ID_Data.setLocation(190, 100);
        ID_Data.setSize(120, 30);
        getContentPane().add(ID_Data);

        PW = new JLabel("비밀번호");
        PW.setLocation(110, 140);
        PW.setSize(70, 30);
        PW.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        getContentPane().add(PW);

        PW_Data = new JPasswordField();
        PW_Data.setLocation(190, 140);
        PW_Data.setSize(120, 30);
        getContentPane().add(PW_Data);

        login_img = new ImageIcon("./Image/login.png");
        login_img.setImage(login_img.getImage().getScaledInstance(150, 63, java.awt.Image.SCALE_SMOOTH));
        login = new JButton(login_img);
        login.setLocation(147, 244);
        login.setSize(150, 63);
        login.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setOpaque(false);
        login.setFocusPainted(false);
        
        courseSelectionFrame = new CourseSelectionFrame();

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ID = ID_Data.getText();
                String PW = new String(PW_Data.getPassword());
                
                if (check(ID, PW)) {
                    courseSelectionFrame.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "잘못 입력했습니다. 다시 시도하세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(login);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon("./Image//일시정지.png"));
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 stopmusic();
        	}
        });
        btnNewButton.setBounds(364, 340, 120, 121);
        getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("아이디/비밀번호 찾기");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new findid();
        	}
        });
        btnNewButton_1.setBounds(126, 194, 171, 23);
        getContentPane().add(btnNewButton_1);
    }
    private void playWav(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
           

           
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private void stopmusic() {
        if (backgroundMusic != null) {
            if (isMusicPlaying) {
                backgroundMusic.stop(); // 배경음 일시 정지
            } else {
                backgroundMusic.start(); // 배경음 재생 재개
            }
            isMusicPlaying = !isMusicPlaying; // 재생 상태 토글
        }
    }
    public boolean check(String ID, String PW) {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader("./data/info.txt"));
    		String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String storedID = parts[0].trim();
                String storedPassword = parts[1].trim();
                
                if (ID.equals(storedID) && PW.equals(storedPassword)) {
                	String userName = parts[2].trim();
                	courseSelectionFrame.setUserName(userName);
                	return true; // 자격 증명 일치
                }
            }
    	}
    	catch (IOException ex) {
            ex.printStackTrace();
        }
    	return false;
    }
    
    class myPanel extends JPanel {
        private ImageIcon icon = new ImageIcon("./Image/상상부기.jpg");
        private Image img = icon.getImage();
        int first_img=0; //첫번째 이미지
        int second_img=img.getWidth(null); //두번째 이미지, img의 넓이를 가져옴
        public void paintComponent(Graphics g) {
            super.paintComponent(g); //캔버스 비우기
            g.drawImage(img, first_img, 0, this); //첫번째, 두번째 이미지 그리기
            g.drawImage(img, second_img, 0, this);
        }
        public myPanel() {
           setFocusable(true);
           new Thread(new Runnable() {
              @Override
              public void run() { // 이미지가 화면 밖으로 나가면, X축을 이미지의 넓이 좌표로 이동
                 while(true) { // 1번이미지가 나가면 2번뒤에 붙고, 2번이 나가면 1번뒤에 붙는 무한루프
                    first_img--;
                    second_img--;
                    if(first_img< -(img.getWidth(null))) {
                       first_img=img.getWidth(null);
                    }
                    if(second_img<-(img.getWidth(null))) {
                       second_img=img.getWidth(null);
                    }
                    repaint();
                    try {
                       Thread.sleep(5);
                    } catch (InterruptedException e) {
                       e.printStackTrace();
                    }
                 }
              }
              }).start();
           
        }
    }

    public static void main(String[] args) {
        new loginFrame();
    }
}
