# be-java-cafe
마스터즈 2023 스프링 카페 

## 기능 목록
- [x] 루트 경로일 때 홈 화면을 출력한다.
- [x] 아이디, 비밀번호, 이름, 이메일을 입력받아 회원가입을 한다.
  - 같은 id를 가진 회원이 있다면 중복 회원 예외를 발생시킨다. 
  - 회원가입이 완료되면 회원 목록 조회로 이동한다.
- [x] 회원 목록 조회 화면에서 이름을 클릭하면 해당 유저의 프로필 화면을 출력한다.


## URL
|__HTTP Method__|__URL__|__기능__|
|---------------|-------|-------|
|GET|/|홈 화면 출력|
|GET|/users/join|회원 가입 화면 출력|
|POST|/users|회원 가입|
|GET|/users|회원 목록 조회 화면 출력|
|GET|/users/{userId}|유저 프로필 화면 조회|


## 동작 화면
<details>
<summary>회원 가입 화면</summary>
<div>

![join](https://user-images.githubusercontent.com/57451700/228421158-31b1cb57-4d2a-4f82-a076-1f45b592725b.png)

</div>
</details>

<details>
<summary>회원 목록 화면</summary>
<div>

![list](https://user-images.githubusercontent.com/57451700/228422180-0ce2e663-7884-4416-af84-e9b22a9e9577.png)

</div>
</details>

<details>
<summary>회원 프로필 화면</summary>
<div>

![profile](https://user-images.githubusercontent.com/57451700/228422257-efad632c-1b2e-4466-ad0c-86f25ca3e782.png)
</div>
</details>