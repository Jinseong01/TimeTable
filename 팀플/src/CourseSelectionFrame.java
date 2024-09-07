import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class CourseSelectionFrame extends JFrame {
    private JPanel coursePanel;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> timeComboBox;
    private JLabel userNameLabel;
    private TimetableFrame timetableFrame;
    
    private List<String> selectedCourses;
    private String[] years = {"2학년", "3학년"};
    private String[] times = {"주간", "야간"};
    private List<List<String>> courses = Arrays.asList(
            Arrays.asList("객체지향언어2", "데이터통신", "알고리즘", "오픈소스소프트웨어", "선형대수", "모바일&스마트시스템", "웹프로그래밍", "빅데이터기초", "게임그래픽&애니메이션"),
            Arrays.asList("시스템프로그래밍", "설계패턴", "네트워크프로그래밍", "컴퓨터비젼", "고급모바일프로그래밍", "웹프레임워크1", "데이터베이스설계", "가상현실")
    );

    public CourseSelectionFrame() {
        selectedCourses = new ArrayList<>();  // 이 부분을 추가하여 초기화

        try {
            // Look and feel 설정
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("과목 선택");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userNameLabel = new JLabel();
        
        coursePanel = new JPanel(new GridLayout(0, 1));

        yearComboBox = new JComboBox<>(years);
        timeComboBox = new JComboBox<>(times);

        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCoursePanel();
            }
        });

        JButton generateButton = new JButton("생성");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateSelectedCourses();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        topPanel.add(userNameLabel);
        topPanel.add(timeComboBox);
        topPanel.add(yearComboBox);
        topPanel.add(generateButton);


        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(coursePanel), BorderLayout.CENTER);

        getContentPane().add(panel);
        setSize(600, 400);
    }

    public void updateCoursePanel() {
        coursePanel.removeAll();
        int yearIndex = yearComboBox.getSelectedIndex();
        if (yearIndex >= 0) {
            for (String course : courses.get(yearIndex)) {
                JCheckBox checkBox = new JCheckBox(course);
                coursePanel.add(checkBox);
            }
        }
        revalidate();
        repaint();
    }

    public void generateSelectedCourses() {
        selectedCourses.clear();
        int yearIndex = yearComboBox.getSelectedIndex();
        String selectedYear = years[yearIndex];
        for (Component component : coursePanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    selectedCourses.add(checkBox.getText());
                }
            }
        }

        dispose();

        timetableFrame = new TimetableFrame(selectedCourses, (String) timeComboBox.getSelectedItem(), selectedYear);
        timetableFrame.setVisible(true);
    }
    
    public void setUserName(String userName) {
    	userNameLabel.setText(userName + "님");
    	
    }
}
