import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class TimetableFrame extends JFrame {
	private JTabbedPane tabbed;
	private ttGenerator tt = new ttGenerator();
	
	private subject[][] selected;
	private subject[] 객지;
	private subject[] 데통;
	private subject[] 선대;
	private subject[] 알고;
	private subject[] 오소;
	private subject[] 모스시;
	private subject[] 웹프;
	private subject[] 빅데;
	private subject[] 겜그;
	
	private subject[] 시프;
	private subject[] 설패;
	private subject[] 네프;
	private subject[] 비젼;
	private subject[] 고모프;
	private subject[] 웹크;
	private subject[] 데베;
	private subject[] 가상;
	
	private JPanel topPanel;
	private JPanel ttPanel[];
	
    public TimetableFrame(List<String> selectedCourses, String timeOfDay, String selectedYear) {
    	System.out.println("생성시작");
    	
    	setTitle("시간표");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        
    	init_courseArray(selectedCourses, selectedYear);
    	
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel timeLabel = new JLabel("수업 시간: " + timeOfDay);
        topPanel.add(timeLabel);
        JLabel yearLabel = new JLabel("선택된 학년: " + selectedYear);
        topPanel.add(yearLabel);
        
        tabbed = new JTabbedPane();

        ttPanel = new JPanel[3];
        for(int index=0; index<ttPanel.length; index++) {
        	make_tt(index, selectedCourses, timeOfDay, selectedYear);
        	
            ttPanel[index] = new JPanel(new GridLayout(27, 5));
            ttPanel[index].setBorder(new LineBorder(Color.BLACK));
            
            //빈라벨 부착
            for(int i=0; i<27*5; i++) {
                JLabel label = new JLabel();
                ttPanel[index].add(label);
            }
            
            //selected내용 삽입&색칠
            for(subject course:selected[index]) {
            	setLabelText(ttPanel[index], (((int)((course.getStartTime()-9)*2)) * 5 + course.getDay()), course.getSub_name()+ "[" + course.getClassname()+"]");
            	setLabelText(ttPanel[index], (((int)((course.getStartTime()-9)*2) +1) * 5 + course.getDay()), course.getClassroom() + "/" + course.getStartTime()+"~"+course.getEndTime());        	
                setLabelRangeBackgroundColor(ttPanel[index], (((int)((course.getStartTime()-9)*2)) * 5 + course.getDay()), ((int)( (course.getEndTime()-course.getStartTime()) * 2)), getRandomColor());
            }
            
            tabbed.addTab(index+"번", ttPanel[index]);
        }

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tabbed, BorderLayout.CENTER);
        setContentPane(panel);
    }
    
    public Color getRandomColor () {
    	Random random = new Random();
    	int r = random.nextInt(106) + 150;
    	int g = random.nextInt(106) + 150;
    	int b = random.nextInt(106) + 150;
    	Color randomColor = new Color(r, g, b);
    	return randomColor;
    }
    
    public void setLabelText(JPanel ttPanel, int index, String newText) {
        Component[] components = ttPanel.getComponents();

        if (index >= 0 && index < components.length && components[index] instanceof JLabel) {
            JLabel label = (JLabel) components[index];
            label.setText(newText);
        }
    }
    
    public void setLabelRangeBackgroundColor(JPanel ttPanel, int startIndex, int rowCount, Color color) {
        Component[] components = ttPanel.getComponents();

        for (int i = 0; i < rowCount; i++) {
            int targetIndex = startIndex + i * 5;

            if (targetIndex >= 0 && targetIndex < components.length && components[targetIndex] instanceof JLabel) {
                JLabel label = (JLabel) components[targetIndex];
                label.setOpaque(true);
                label.setBackground(color);
            }
        }
    }
    
    public void init_courseArray(List<String> selectedCourses, String selectedYear) {
    	System.out.println("학년에 맞는 과목들 초기화 시작");
    	selected = new subject[3][selectedCourses.size()];
    	
    	switch(selectedYear) {
    	case "2학년":
    		객지 = new subject[]{
    				new subject(1, "객체지향언어2", "7", 2, 13.5, 16.5, "황기태", "미B104"),
    				new subject(1, "객체지향언어2", "8", 2, 16.5, 18.0, "유상미", "공201"),
    				new subject(1, "객체지향언어2", "9", 0, 13.5, 15.0, "이찬수", "탐203"),
    				new subject(1, "객체지향언어2", "0", 4, 15.0, 18.0, "김의찬", "공201"),
    				new subject(0, "객체지향언어2", "A", 0, 13.5, 15.0, "김진환", "공309"),
    				new subject(0, "객체지향언어2", "B", 3, 16.5, 18.0, "유상미", "공202"),
    				new subject(0, "객체지향언어2", "C", 2, 10.5, 12.0, "황기태", "미B104"),
    				new subject(2, "객체지향언어2", "N", 0, 18.0, 19.5, "김진환", "공309"),
    				new subject(2, "객체지향언어2", "O", 4, 18.0, 21.0, "김의찬", "공201")
    		};
    		데통 = new subject[]{
    				new subject(1, "데이터통신", "7", 4, 16.0, 17.0, "황호영", "상203"),
    				new subject(1, "데이터통신", "8", 1, 17.0, 18.0, "김동욱", "공305"),
    				new subject(0, "데이터통신", "A", 4, 10.0, 11.0, "황호영", "상203"),
    				new subject(0, "데이터통신", "B", 4, 11.0, 12.0, "황호영", "상203"),
    				new subject(0, "데이터통신", "C", 4, 15.0, 16.0, "황호영", "상203"),
    				new subject(0, "데이터통신", "D", 1, 16.0, 17.0, "김동욱", "공305"),
    				new subject(2, "데이터통신", "N", 1, 17.0, 18.0, "황호영", "상203"),
    				new subject(2, "데이터통신", "O", 1, 18.0, 19.0, "김동욱", "공305")
    		};
    		알고 = new subject[]{
    				new subject(1, "알고리즘", "7", 2, 15.0, 16.5, "심규현", "공309"),
    				new subject(1, "알고리즘", "8", 4, 12.0, 13.0, "권영미", "상306"),
    				new subject(0, "알고리즘", "A", 4, 11.0, 12.0, "권영미", "상306"),
    				new subject(0, "알고리즘", "B", 2, 15.0, 16.5, "유상미", "공201"),
    				new subject(0, "알고리즘", "C", 2, 9.0, 10.5, "강희중", "공202"),
    				new subject(0, "알고리즘", "D", 2, 10.5, 12.0, "강희중", "공202"),
    				new subject(2, "알고리즘", "N", 3, 18.0, 19.5, "유상미", "공202"),
    				new subject(2, "알고리즘", "O", 2, 19.5, 21.0, "유상미", "공201")
    		};
    		오소 = new subject[]{
    				new subject(1, "오픈소스소프트웨어", "7", 0, 15.0, 16.5, "김남윤", "상607"),
    				new subject(0, "오픈소스소프트웨어", "A", 3, 13.5, 15.0, "김남윤", "상607"),
    				new subject(0, "오픈소스소프트웨어", "B", 3, 15.0, 16.5, "김남윤", "상602"),
    				new subject(2, "오픈소스소프트웨어", "N", 0, 18.0, 19.5, "김남윤", "상607")
    		};
    		선대 = new subject[]{
    				new subject(0, "선형대수", "A", 1, 17.0, 18.0, "권영미", "상203"),
    				new subject(2, "선형대수", "N", 1, 18.0, 19.0, "권영미", "상203")
    		};
    		모스시 = new subject[]{
    				new subject(1, "모바일&스마트시스템", "7", 1, 13.0, 16.0, "황기태", "공102"),
    				new subject(1, "모바일&스마트시스템", "8", 1, 13.0, 16.0, "한기준", "공309"),
    				new subject(1, "모바일&스마트시스템", "9", 0, 13.5, 16.5, "이재문", "공101"),
    				new subject(0, "모바일&스마트시스템", "A", 3, 9.0, 12.0, "황기태", "공102"),
    				new subject(0, "모바일&스마트시스템", "B", 1, 13.0, 16.0, "이재문", "공101"),
    				new subject(0, "모바일&스마트시스템", "C", 3, 9.0, 12.0, "이재문", "공101"),
    				new subject(2, "모바일&스마트시스템", "N", 1, 19.0, 22.0, "한기준", "공309")
    		};
    		웹프 = new subject[]{
    				new subject(1, "웹프로그래밍", "7", 3, 15.0, 16.5, "김인희", "공201"),
    				new subject(1, "웹프로그래밍", "8", 2, 13.5, 15.0, "심규현", "공309"),
    				new subject(0, "웹프로그래밍", "A", 0, 9.0, 12.0, "김성동", "상606"),
    				new subject(0, "웹프로그래밍", "B", 3, 13.5, 15.0, "김인희", "공201"),
    				new subject(2, "웹프로그래밍", "N", 3, 18.0, 19.5, "김인희", "공201"),
    				new subject(2, "웹프로그래밍", "O", 2, 18.0, 19.5, "심규현", "공309")
    		};
    		빅데 = new subject[]{
    				new subject(0, "빅데이터기초", "A", 2, 10.5, 12.0, "신성", "상306"),
    				new subject(0, "빅데이터기초", "B", 2, 12.0, 13.5, "신성", "상306"),
    				new subject(2, "빅데이터기초", "N", 3, 19.5, 21.0, "신성", "공102")
    		};
    		겜그 = new subject[]{
    				new subject(1, "게임그래픽&애니메이션", "7", 1, 16.0, 17.0, "이항찬", "공202"),
    				new subject(0, "게임그래픽&애니메이션", "A", 1, 10.0, 11.0, "이항찬", "공202"),
    				new subject(0, "게임그래픽&애니메이션", "B", 1, 11.0, 12.0, "이항찬", "공202"),
    				new subject(2, "게임그래픽&애니메이션", "N", 1, 18.0, 19.0, "이항찬", "공202")
    		};
    		break;
    	case "3학년":
    		시프 = new subject[] {
    				new subject(1, "시스템프로그래밍", "7", 0, 16.5, 18.0, "안영아", "공202"),
    				new subject(1, "시스템프로그래밍", "8", 0, 16.5, 18.0, "전원호", "공309"),
    				new subject(0, "시스템프로그래밍", "A", 0, 15.0, 16.5, "김진환", "공309"),
    				new subject(0, "시스템프로그래밍", "B", 0, 15.0, 16.5, "안영아", "공202"),
    				new subject(0, "시스템프로그래밍", "C", 0, 12.0, 13.5, "전원호", "공309"),
    				new subject(2, "시스템프로그래밍", "N", 0, 18.0, 19.5, "안영아", "공202")
    		};
    		설패 = new subject[] {
    				new subject(1, "설계패턴", "7", 0, 13.5, 15.0, "정인상", "공305"),
    				new subject(1, "설계패턴", "8", 0, 13.5, 15.0, "한기준", "미B104"),
    				new subject(0, "설계패턴", "A", 0, 10.5, 12.0, "정인상", "공305"),
    				new subject(0, "설계패턴", "B", 2, 10.5, 12.0, "정인상", "공305"),
    				new subject(0, "설계패턴", "C", 0, 10.5, 12.0, "한기준", "미B104"),
    				new subject(2, "설계패턴", "N", 0, 18.0, 19.5, "정인상", "공305")
    		};
    		네프 = new subject[] {
    				new subject(1, "네트워크프로그래밍", "7", 2, 16.5, 18.0, "신성", "상304"),
    				new subject(0, "네트워크프로그래밍", "A", 3, 16.5, 18.0, "허준영", "상306"),
    				new subject(0, "네트워크프로그래밍", "B", 2, 15.0, 16.5, "허준영", "상304"),
    				new subject(0, "네트워크프로그래밍", "C", 0, 15.0, 16.5, "허준영", "탐203"),
    				new subject(2, "네트워크프로그래밍", "N", 0, 19.5, 21.0, "허준영", "상306")
    		};
    		비젼 = new subject[] {
    				new subject(1, "컴퓨터비젼", "7", 2, 10.5, 12.0, "엄종석", "공201"),
    				new subject(0, "컴퓨터비젼", "A", 2, 13.5, 15.0, "엄종석", "미B107")
    		};
    		고모프 = new subject[] {
    				new subject(1, "고급모바일프로그래밍", "7", 1, 17.0, 18.0, "허준영", "상306"),
    				new subject(0, "고급모바일프로그래밍", "A", 1, 11.0, 12.0, "허준영", "탐203"),
    				new subject(0, "고급모바일프로그래밍", "B", 1, 12.0, 13.0, "허준영", "탐203"),
    				new subject(2, "고급모바일프로그래밍", "N", 1, 18.0, 19.0, "허준영", "상306")
    		};
    		웹크 = new subject[] {
    				new subject(1, "웹프레임워크1", "7", 3, 16.5, 18.0, "심규현", "공309"),
    				new subject(0, "웹프레임워크1", "A", 0, 15.0, 16.5, "박승현", "공102"),
    				new subject(0, "웹프레임워크1", "B", 3, 16.5, 18.0, "박승현", "공305"),
    				new subject(0, "웹프레임워크1", "C", 3, 13.5, 15.0, "심규현", "공309"),
    				new subject(2, "웹프레임워크1", "N", 0, 19.5, 21.0, "박승현", "공102")
    		};
    		데베 = new subject[] {
    				new subject(1, "데이터베이스설계", "7", 0, 16.5, 18.0, "장재영", "상501"),
    				new subject(0, "데이터베이스설계", "A", 3, 10.5, 12.0, "장재영", "공305"),
    				new subject(0, "데이터베이스설계", "B", 3, 15.0, 16.5, "장재영", "공305"),
    				new subject(2, "데이터베이스설계", "N", 0, 18.0, 19.5, "장재영", "상501")
    		};
    		가상 = new subject[] {
    				new subject(1, "가상현실", "7", 1, 15.0, 17.0, "김진모/조세홍", "공201"),
    				new subject(1, "가상현실", "8", 1, 17.0, 19.0, "김진모/조세홍", "공201"),
    				new subject(0, "가상현실", "A", 1, 12.0, 14.0, "김진모/조세홍", "공201")
    		};
    		break;
    	}
    	

		
		
    }
    
    public void make_tt(int num, List<String> selectedCourses, String timeOfDay, String selectedYear) {
    	System.out.println("랜덤 생성 시작");
		//랜덤 데이터 선택
		Random random = new Random();
		int r;
		int index=0;
		String course;
		boolean collision; //충돌여부 true가 충돌O / false가 충돌X
		int timeType = "주간".equals(timeOfDay) ? 0 : 2;
		
		for(int i=0; i<selectedCourses.size(); i++) {
	    	System.out.println(i + "단계 추가");
			course = selectedCourses.get(i); 
			collision = true;
			switch(selectedYear) {
			case "2학년":
				switch(course) {
				case "객체지향언어2":
					if(checkRest(num, selectedCourses, 객지, timeType)) { //충돌 X 수업이 존재한다면
						r = tt.chooseNewSubject(collision, selected[num], 객지, random, timeType);
						selected[num][index++] = 객지[r];
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "데이터통신":
					if(checkRest(num, selectedCourses, 데통, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 데통, random, timeType);
						selected[num][index++] = 데통[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "알고리즘":
					if(checkRest(num, selectedCourses, 알고, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 알고, random, timeType);
						selected[num][index++] = 알고[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "오픈소스소프트웨어":
					if(checkRest(num, selectedCourses, 오소, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 오소, random, timeType);
						selected[num][index++] = 오소[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "선형대수":
					if(checkRest(num, selectedCourses, 선대, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 선대, random, timeType);
						selected[num][index++] = 선대[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "모바일&스마트시스템":
					if(checkRest(num, selectedCourses, 모스시, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 모스시, random, timeType);
						selected[num][index++] = 모스시[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected.length; t++) {
							selected[t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "웹프로그래밍":
					if(checkRest(num, selectedCourses, 웹프, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 웹프, random, timeType);
						selected[num][index++] = 웹프[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "빅데이터기초":
					if(checkRest(num, selectedCourses, 빅데, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 빅데, random, timeType);
						selected[num][index++] = 빅데[r];
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "게임그래픽&애니메이션":
					if(checkRest(num, selectedCourses, 겜그, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 겜그, random, timeType);
						selected[num][index++] = 겜그[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				}
				break;
			case "3학년":
				switch(course) {
				case "시스템프로그래밍":
					if(checkRest(num, selectedCourses, 시프, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 시프, random, timeType);
						selected[num][index++] = 시프[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "설계패턴":
					if(checkRest(num, selectedCourses, 설패, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 설패, random, timeType);
						selected[num][index++] = 설패[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "네트워크프로그래밍":
					if(checkRest(num, selectedCourses, 네프, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 네프, random, timeType);
						selected[num][index++] = 네프[r];
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "컴퓨터비젼":
					if(checkRest(num, selectedCourses, 비젼, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 비젼, random, timeType);
						selected[num][index++] = 비젼[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "고급모바일프로그래밍":
					if(checkRest(num, selectedCourses, 고모프, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 고모프, random, timeType);
						selected[num][index++] = 고모프[r];
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "웹프레임워크1":
					if(checkRest(num, selectedCourses, 웹크, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 웹크, random, timeType);
						selected[num][index++] = 웹크[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "데이터베이스설계":
					if(checkRest(num, selectedCourses, 데베, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 데베, random, timeType);
						selected[num][index++] = 데베[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				case "가상현실":
					if(checkRest(num, selectedCourses, 가상, timeType)) {
						r = tt.chooseNewSubject(collision, selected[num], 가상, random, timeType);
						selected[num][index++] = 가상[r]; 
					}
					else { //초기화
						System.out.println("초기화");
						for(int t=0; t<selected[num].length; t++) {
							selected[num][t]=null;
						}
						index=0;
						i=-1;
						continue;
					}
					break;
				}
				break;
			}			
		}
	}
    
    public boolean checkRest(int num, List<String> selectedCourses, subject[] subjectType, int timeType) {
    	boolean possible;
    	int i,j;
    	for(i=0; i<subjectType.length; i++) {
    		possible = true; //선택한 가능한게 존재
    		for(j=0; selected[num][j]!=null; j++) {
				if(subjectType[i].getDay_night()==timeType || subjectType[i].getDay_night()==1) { //주야간 같을때만
	    			if(selected[num][j].getDay() == subjectType[i].getDay()) { //요일이 같은 경우
	    				if((selected[num][j].getStartTime() <= subjectType[i].getStartTime() &&
	        				subjectType[i].getStartTime() < selected[num][j].getEndTime()) ||
	        				(selected[num][j].getStartTime() < subjectType[i].getEndTime() &&
	        				subjectType[i].getEndTime() <= selected[num][j].getEndTime())) { //시간 충돌이 있는 경우
	    					possible = false;
	    					break;
	    				}
	    			}
				}
				else { //주야간 다르면 선택 불가
					possible = false;
					break;
				}
    		}
    		//selected[]전체와 subjectType 중 수업1개의 충돌을 비교
    		//possible이 마지막에 true인 경우 -> 가능한 수업이 존재한다?
    		if(possible==false) {
				System.out.println("subjectType" + i + "와 selected" + j + " 충돌");
    		}
    		else {
    			System.out.println("선택 가능한 수업 존재" + i);
    			return true;
    		}
    	}
    	//다 돌았지만 하나도 가능한게 없다
    	System.out.println("모든 수업 불가");
    	return false;
    }

    
    public class subject {
    	private int day_night; //주간0 합반1 야간2
    	private String sub_name; //과목명
    	private String className; //분반
    	private int day; //요일 0~4
    	private double start_time; //시작 시간
    	private double end_time; //종료시간
    	private String professor; //교수
    	private String classroom; //강의실


    	public subject(int day_night, String sub_name, String className, int day, double start_time, double end_time, String professor, String classroom) {
    		this.day_night = day_night;
    		this.sub_name=sub_name;
    		this.className=className;
    		this.day=day;
    		this.start_time=start_time;
    		this.end_time=end_time;
    		this.professor=professor;
    		this.classroom=classroom;
    	}
    	
    	public int getDay_night() {
    		return this.day_night;
    	}
    	public String getSub_name() {
    		return this.sub_name;
    	}
    	public String getClassname() {
    		return this.className;
    	}
    	public int getDay() {
    		return this.day;
    	}
    	public double getStartTime() {
    		return this.start_time;
    	}
    	public double getEndTime() {
    		return this.end_time;
    	}
    	public String getProfessor() {
    		return this.professor;
    	}
    	public String getClassroom() {
    		return this.classroom;
    	}
    }
    public class ttGenerator {
		//랜덤숫자로 기존 수업들과 충돌여부 고려
		public int chooseNewSubject(boolean collision, subject[] selected, subject[] subjectType, Random random, int timeType) {
			System.out.println("ttGenerator실행");
			int r;
			while(collision) { //충돌안할때까지
				r = random.nextInt(subjectType.length); //랜덤
				
				if(subjectType[r].getDay_night()==timeType || subjectType[r].getDay_night()==1) { //주간일때만 or 야간일때만
					collision = false;
					for(int i=0; selected[i]!=null; i++) { //이전 데이터와 비교
						if(selected[i].getDay()==subjectType[r].getDay()) { //요일이 같을때만 충돌여부 확인
							if((selected[i].getStartTime()<= subjectType[r].getStartTime() &&
									subjectType[r].getStartTime() < selected[i].getEndTime()) ||
									(selected[i].getStartTime()< subjectType[r].getEndTime() &&
											subjectType[r].getEndTime() <= selected[i].getEndTime())) { //시간이 겹친다면
								collision = true; //충돌함
								break; //다시 while문으로
							}
						}
					}
					
					if(!collision) { //for문을 진행하고도 false가 유지된다면 충돌이 X다는 것
						System.out.println("ttGenerator-충돌X " + r);
						return r;
					}
				}
			}
			System.out.println("오류 발생");
			return -1;
		}
	}
}
