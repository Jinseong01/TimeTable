# TimeTable

## 개요
- 예상 UI
<img width="367" alt="image" src="https://github.com/user-attachments/assets/3f4dddfc-5e6b-4e42-b10e-fde76b62f1f7">

- 유스케이스 다이어그램
![image](https://github.com/user-attachments/assets/846284a0-d74c-49ab-baca-cbd539394eab)

## 설계
- 화면
<img width="438" alt="image" src="https://github.com/user-attachments/assets/9be36e93-2be1-4edb-b64c-c7e64df882d1">

- 클래스 다이어그램
![subject](https://github.com/user-attachments/assets/c068d32b-7fb9-43fa-af3e-c8d7f7c54a00)

## 구현
- 최종 클래스 다이어그램
![image](https://github.com/user-attachments/assets/3bb23eeb-b1e1-4ee1-b509-54cb826f49bd)

## 사용 설명서
1. ![image](https://github.com/user-attachments/assets/f2e3819d-57be-489e-894f-bbe1deb60b9c)
- ID와 PW를 입력한 뒤, 로그인 버튼을 누른다.
- ID 또는 PW를 모를 경우, 로그인 창의 돋보기 버튼을 누른다.
- 배경음악은 좌측 상단의 버튼을 눌러서 중지하거나 다시 재생할 수 있다.

2. ![image](https://github.com/user-attachments/assets/435ce2c2-17a2-4d0a-b9c8-0aa33fe05c0a)
- ID를 모르는 경우, 이름을 입력하여 찾는다.
- PW를 모르는 경우, 이름과 ID를 입력하여 찾는다.

3. ![image](https://github.com/user-attachments/assets/61465563-4a44-47f3-b969-15dbbca55acd)
- 주/야간과 학년을 선택한 뒤, 희망하는 과목을 선택하고 우측 상단의 생성 버튼을 누른다.

4. ![image](https://github.com/user-attachments/assets/b3ec823b-6ebd-45ca-a0a1-27b5a695a973)
- 이전에 선택한 각 과목들의 랜덤 수업들로 구성된 시간표 중 마음에 드는 시간표를 1개 선택한 뒤에 우측 상단의 저장 버튼을 누른다.

5. ![image](https://github.com/user-attachments/assets/95f80829-86d6-44ec-830d-9c0f17623e3e)
- 선택한 시간표에서 마음에 들지 않는 수업은 좌측의 리스트에서 선택한 뒤 제거 버튼을 누르고, 추가하고 싶은 수업은 우측의 전체 수업 리스트에서 선택한 뒤 추가 버튼을 누른다.
- 시간이 겹치는 수업을 추가할 경우 ‘불가하다’는 창이 뜬다.
- 선택 가능한 최대 수업의 개수는 각 학년 전공 과목의 전체 개수이다. 이를 초과해서 추가할 경우 ‘불가하다’는 창이 뜬다.