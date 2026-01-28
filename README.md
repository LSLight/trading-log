# 📈 주식 사팔사팔 (Stock Trading Journal)

> **"뇌동매매 멈춰!"** ✋  
> 나만의 주식 매매 전략을 세우고, 실현 손익과 평단가를 자동으로 계산해주는 **노션(Notion) 스타일의 주식 투자 일지** 서비스입니다.

<br/>

## 📸 Screen Shot
<img src="https://github.com/user-attachments/assets/3d6a25ae-1847-4c15-805d-e552df3d47ca" alt="Dashboard" width="100%">

<br/>

## ✨ Key Features (핵심 기능)

### 1. 📋 전략 보드 (Kanban Board)
- **국장 / 미장 분리:** 국내 주식과 해외 주식을 별도 컬럼으로 관리
- **전략 메모:** 종목별 **매수 타점 / 매도 타점**을 미리 기록하여 뇌동매매 방지
- **컬러 태그:** RSI, 볼린저밴드 등 보조지표나 섹터를 색상 태그로 시각화

### 2. 📊 스마트 매매 일지 (Trading Log)
- **사이드 피크(Side Peek):** 화면 전환 없이, 카드를 클릭하면 우측에서 상세 내역이 슬라이딩
- **자동 계산:** 매수/매도 기록만 입력하면 **현재 보유 수량, 평단가(Avg), 실현 수익금** 자동 산출
- **통화 구분:** 국장은 `₩(원)`, 미장은 `$(달러)` 및 소수점까지 디테일하게 표시

### 3. 📅 투자 캘린더 (Calendar)
- **일별 기록:** 캘린더 날짜를 클릭하여 그날의 시황이나 투자 생각 메모
- **직관적 UI:** 팝업 없이 카드 내부에서 바로 작성하고 저장

### 4. 🎨 노션(Notion) 스타일 UX
- **커스텀 타이틀:** 페이지 제목과 컬럼 이름을 내 마음대로 수정 가능 (브라우저 저장)
- **깔끔한 디자인:** 가독성을 높인 카드 레이아웃과 감성적인 색감

<br/>

## 🛠 Tech Stack (기술 스택)

| 분류 | 기술 |
| :-- | :-- |
| **Backend** | Java 17, Spring Boot 3.x, JPA (Hibernate) |
| **Database** | H2 Database (In-Memory) |
| **Frontend** | HTML5, CSS3, JavaScript (Vanilla), Thymeleaf |
| **Tool** | IntelliJ IDEA, Git |

<br/>

## 🚀 How to Run (실행 방법)

1. **프로젝트 클론**
   ```bash
   git clone [https://github.com/LSLight/trading-log.git](https://github.com/LSLight/trading-log.git)