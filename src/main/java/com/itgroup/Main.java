package com.itgroup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        DataManager dma = new DataManager();

        while (true){
            System.out.println("메뉴를 선택하세요");
            System.out.println("0: 종료, 1: 접속, 2: 회원가입, 3: 관리자");
            int menu = scan.nextInt();
            switch (menu){
                case 0:
                    System.out.println("프로그램을 종료합니다");
                    System.exit(0);
                    break;
                case 1: // 접속 후 행동 메소드 : 아이디로 접속 > 접속한 아이디의 정보 출력 > 메뉴선택 > 캐릭터 정보 확인, 사냥, 가방확인, 나가기
                    dma.usermenu();
                    break;
                case 2: // 회원가입하기
                    dma.signup();
                    break;
                case 3: // 전체유저 확인, 캐릭터 삭제, 캐릭터 생성, 아이템 생성, 몬스터 생성, 유저 강제종료
                    dma.mastermenu();
                    break;
            }

        }
    }
}