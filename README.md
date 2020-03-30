# java-chess
체스 게임 구현을 위한 저장소

## 구현할 기능 목록

### domain 기능

- [x] 체스판과 기물을 초기화

- [x] 팀이 번갈아가면서 자신의 기물을 이동
    - [x] 상대편의 기물일 경우 이동 불가

- [x] 기물을 현재 위치에서 특정 위치로 이동가능한지 확인
    - [x] 다른 기물을 고려하지 않는 부분(RouteFinder)
        - [x] 특정 위치에서 특정 위치로 이동하기 위해 거치는 경로를 구함
            - [x] 기물 별로 이동할 수 있는 방향들을 구함
            - [x] 현재 위치에서 각 방향으로 가능한 만큼 전진하여 체스판을 벗어나기 전까지의 경로를 반환
                - [x] 폰은 Rank 2에 있을 때에만 2번 전진, 나머지는 1번 전진
                - [x] 킹은 1번 전진
                - [x] 룩, 비숍, 나이트, 퀸은 체스판을 벗어날 때까지 전진
            - [x] 경로에 목표 위치가 있는지 확인
    
    - [x] 다른 기물을 고려해야 하는 부분(MovingExecutor)
        - [x] 최종 경로 내에 다른 기물이 있는지 확인
            - [x] 룩, 비숍, 퀸, 킹은 다른 기물이 있으면 이동 불가
            - [x] 폰은 아래의 경우를 제외하고 이동 불가
                - [x] 전진 경로가 비어있을 경우에만 이동 가능
                - [x] 대각선 전진 경로에 상대편 기물이 있을 경우에만 이동 가능
            - [x] 나이트는 경로 내에 다른 기물이 있어도 이동 가능
        - [x] 이동하고자 하는 위치에 기물이 있는지 확인
            - [x] 상대편 기물이 있을 경우 : 상대편 기물 삭제 후 이동 가능
            - [x] 우리편 기물이 있을 경우 : 이동 불가

- [x] 기물을 특정 위치로 이동하고 그 위치의 기물을 삭제
    - [x] 나이트는 경로에 있는 상대편의 기물도 삭제해야 함

- [x] King을 잡았을 경우 게임이 종료

### view 기능
- [x] 게임 시작 및 종료를 위한 명령어 안내문 출력
- [x] 게임 시작 및 종료를 위한 명령어 입력
- [x] 체스판 출력