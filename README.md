# MadcampWeek2
# SoolLog(술록)

*by. 1분반 조수연, 박현규*

---

## 1. Introduce

> 술을 좋아하는 사람들을 위한 칵테일 백과사전 및 로그, 기록 서비스
> 

---

## 2. Member

- 박현규 - KAIST 전기및전자공학부
- 조수연 - 이화여자대학교 컴퓨터공학과

---

## 3. Environment

- Language : Kotlin, Node.js, MySQL
- OS : Android
    - `minSdkVersion` : 30
    - `compileSdk` : 34
    - `targetSdk` : 34
- IDE : Android Studio
- Target Device : Galaxy S10e

---

## 4. Application

### A. 로그인 화면

**[ Major features ]**

![image](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/cb70288c-9694-4c6d-9fc2-eeba847487e7/Untitled.png)

- 자체 로그인과 회원가입 기능이 구현되어 있습니다.
- 이미 데이터베이스에 존재하는 아이디로 회원가입을 하려고 할때, 존재하는 아이디라는 알림을 띄웁니다.
- 정보를 입력하지 않으면 입력을 하라는 알림이 나옵니다.
- 데이터 베이스 내 유저정보와 비교하여 로그인을 성공하면 메인화면으로 전환됩니다.

### B. 로그인 화면

![image2](https://prod-files-secure.s3.us-west-2.amazonaws.com/f6cb388f-3934-47d6-9928-26d2e10eb0fc/484ce159-b984-4e53-af58-2e76540c41ff/Untitled.png)

- 메인화면에서는 4가지의 서비스를 지원합니다.
- 술을 추천 받아 마시고 기록하는 로그,
- 500개에 달하는 데이터베이스를 바탕으로 취향에 맞는 술을 추천 받고, 레시피를 따라 만들 수 있는 추천기능
- 새로운 칵테일을 만들고 공유하고 싶어하는 사람들을 위해 기록을 할 수 있는 My Cocktail
- 정보를 입력하지 않으면 입력을 하라는 알림이 나옵니다.
- 데이터 베이스 내 유저정보와 비교하여 로그인을 성공하면 메인화면으로 전환됩니다.

  
