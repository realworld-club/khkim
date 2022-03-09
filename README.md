참고 page : https://bluewow.notion.site/Real-World-163dac84e30f45f5986c5bf5ab287085

## Branch
- **master** : real-world-v2
- **kotlin** : 코틀린 전환을 위한 맛보기 버전(진행중)
- **hexagonal** : hexagonal 아키텍처 적용 (미적용)
- **real-world** : 연습용
- **real-world-v2** : realworld 요구사항 만족. JPA 사용

## 특징
### application 과 core package 의 분리
- boilerplate 한 코드와 비즈니스 영역을 분리
- layered architecture + DI 를 이용하여 도메인의 의존성 관리

### TEST 코드
- web 영역은 restAssured 를 이용하여 가독성 중심의 코드
- fixture 를 이용하여 반복적인 코드 제거
- service 영역에 대한 테스트 코드 (실용적인 관점)

### 다양한 접근과 고민
- ManyToMany 를 통한 접근은 항상 안좋은가?
- DTO 객체는 어떻게 전달하는게 가장 효과적일까?
- 각 레이어의 책임은 어디까지인가?
