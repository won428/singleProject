package com.itgroup;

import com.itgroup.bean.Bag;
import com.itgroup.bean.Item;
import com.itgroup.bean.Monster;
import com.itgroup.bean.Users;
import com.itgroup.dao.BagDao;
import com.itgroup.dao.ItemDao;
import com.itgroup.dao.MonsterDao;
import com.itgroup.dao.UsersDao;

import java.util.List;
import java.util.Scanner;

public class DataManager {
    Scanner scan = new Scanner(System.in);
    private UsersDao udao = null;
    private MonsterDao mdao = null;
    private ItemDao idao = null;
    private BagDao bdao = null;

    private String id; // 유저 아이디 프라이머리 키
    private String password; // 유저 비밀번호 not null
    private String name; // 유저 닉네임 not null
    private String mname; // 몬스터 닉네임 not null
    private int item;
    private String iname;
    private int itemcode;
    private int price;
    private int monstercode; // 몬스터 코드 프라이머리키
    private int hp; // 유저 hp default 100
    private int exp; // 유저 경험치 default 0
    private int dmg;

    public DataManager(){

        this.udao = new UsersDao();
        this.mdao = new MonsterDao();
        this.idao = new ItemDao();
        this.bdao = new BagDao();
    }

    //관리자 전체 메뉴 호출(전체유저 확인, 캐릭터 삭제, 캐릭터 생성, 아이템 생성, 몬스터 생성)
    public boolean mastermenu() {

        System.out.println("관리자 메뉴를 실행합니다.");
        while(true){
            System.out.println("메뉴를 선택해주세요.");
            System.out.println("0: 돌아가기, 1: 전체유저 확인, 2: 유저 생성, 3: 유저 삭제, 4: 아이템 생성, 5: 아이템 삭제, 6: 몬스터 생성, 7: 몬스터 삭제");
            int menu = scan.nextInt();
            switch (menu){
                case 0:
                    System.out.println("관리자 메뉴를 종료합니다.");
                    return false;
                case 1:
                    List<Users> users = udao.checkusers();
                    for (Users user : users){
                        System.out.println(user);
                    }
                    break;
                case 2:
                    int cnt = -1;
                    System.out.println("생성할 유저 id를 입력하세요");
                    id = scan.next();
                    System.out.println("비밀번호를 입력하세요");
                    password = scan.next();
                    System.out.println("닉네임을 입력하세요");
                    name = scan.next();
                    System.out.println("hp를 입력하세요");
                    hp = scan.nextInt();
                    System.out.println("exp를 입력하세요");
                    exp = scan.nextInt();
                    System.out.println("dmg를 입력하세요");
                    dmg = scan.nextInt();
                    cnt = udao.createuser(id,password,name,hp,exp,dmg);

                    if(cnt == -1){
                        System.out.println("유저 생성에 실패 했습니다.");
                    }else if(cnt > 0){
                        System.out.println("유저 생성에 성공 했습니다.");
                    }else{

                    }
                    break;
                case 3:
                    break;
                case 4:
                    cnt = -1;
                    System.out.println("아이템을 생성합니다.");
                    System.out.println("아이템의 이름을 입력하세요.");
                    iname = scan.next();
                    System.out.println("아이템의 코드를 입력하세요.");
                    itemcode = scan.nextInt();
                    System.out.println("아이템의 가격을 입력하세요.");
                    price = scan.nextInt();
                    cnt = idao.createItem(iname,itemcode,price);
                    if (cnt == -1){
                        System.out.println("아이템 생성에 실패했습니다.");
                    }else if(cnt > 0){
                        System.out.println("아이템 생성에 성공했습니다.");
                    }else{

                    }
                    break;

                case 6:
                    cnt = -1;
                    System.out.println("몬스터를 생성합니다.");
                    System.out.println("몬스터의 코드를 입력해주세요.");
                    monstercode = scan.nextInt();
                    System.out.println("몬스터의 이름을 입력해주세요.");
                    mname = scan.next();
                    System.out.println("몬스터의 hp를 입력해주세요.");
                    hp = scan.nextInt();
                    System.out.println("몬스터의 드랍 아이템코드를 1개 입력해주세요.");
                    item = scan.nextInt();
                    System.out.println("몬스터의 경험치를 입력해주세요.");
                    exp = scan.nextInt();
                    System.out.println("몬스터의 데미지를 입력해주세요.");
                    dmg = scan.nextInt();
                    cnt = mdao.createMonster(monstercode,mname,hp,item,exp,dmg);
                    if(cnt == -1){
                        System.out.println("몬스터 생성에 실패하였습니다.");
                    }else if(cnt > 0){
                        System.out.println("몬스터 생성에 성공하였습니다.");
                    }else{

                    }

                    break;

            }
        }
    }


    public boolean usermenu() {

        System.out.println("접속할 id를 입력 해주세요.");
        id = scan.next();
        System.out.println(id + "님, 접속을 환영합니다! ");
        while(true){
        System.out.println("메뉴를 선택해주세요.");
        System.out.println("0: 돌아가기, 1: 캐릭터 정보 조회, 2: 인벤토리 조회, 3: 몬스터 사냥");
        int menu = scan.nextInt();
            switch (menu){
                case 0:
                    System.out.println("접속을 종료합니다.");
                    return false;
                case 1:
                    Users you = udao.findid(id);
                    System.out.println(you);
                    break;
                case 2:
                    System.out.println("보유아이템");
                    List<Bag> bags = bdao.findbag(id);
                    for (Bag bag : bags){
                        String iname = bag.getIname();
                        System.out.println(iname);
                    }
                    break;
                case 3:
                    System.out.println("사냥할 몬스터를 선택하세요");
                    List<Monster> monsters = mdao.checkmonster();
                    System.out.println("몬스터 목록"); // 몬스터 선택 보기 만들기
                    for(Monster monster : monsters){
                        monstercode = monster.getMonstercode();
                        mname = monster.getMname();
                        hp = monster.getHp();
                        item = monster.getItem();
                        exp = monster.getExp();
                        dmg = monster.getDmg();
                        System.out.print(monstercode +"."+ mname+" ");
                    }

                    you = udao.findid(id); // 접속자 객체 생성
                    monstercode = scan.nextInt(); // 위 보기를 보고 몬스터 선택, 몬스터 번호는 몬스터 코드와 동일하게 설정

                    Monster m = mdao.checkmonsterOne(monstercode); // 몬스터 코드 매개변수로 몬스터 객체 생성
                    if(m != null) { // 없는 몬스터일경우 널 방지로 if구문 넣기

                        int yhp = you.getHp(); // 현재 접속중인 유저의 hp
                        int yexp = you.getExp(); // 현재 접속중인 유저의 exp

                        Item i = mdao.getMonsterItem(monstercode); //몬스터가 가지고 있는 item 정보 가져오기
                        iname = i.getIname(); // 몬스터가 가지고 있는 아이템의 이름
                        itemcode = i.getItemcode(); // 몬스터가 가지고 있는 아이템 코드
                        dmg = m.getDmg(); // 몬스터의 데미지
                        int mhp = m.getHp();
                        int mexp = m.getExp(); // 선택한 몬스터의 exp
                        int ydmg = you.getDmg();
                        int after = yhp - dmg; // 사냥후 유저의 hp

                        if (ydmg < mhp) {// hp가 0 되는걸 방지
                            System.out.println("몬스터가 너무 강합니다.");
                        } else if(yhp < dmg){
                            System.out.println("체력이 너무 적습니다.");
                        }else if(ydmg > mhp){
                            int nowexp = yexp + mexp; // 사냥을 성공하면
                            udao.updateexp(nowexp, id);
                            bdao.updatebag(id, itemcode);
                            udao.updatehp(after, id);
                            System.out.println("체력이 " + after + "만큼 남았습니다.");
                            System.out.println(iname + "을(를) 획득했습니다.");
                            System.out.println("경험치가 " + nowexp + "이(가) 되었습니다.");
                        }else{

                        }
                    }else {
                        System.out.println("존재하지 않는 몬스터 입니다.");
                    }
                    break;

            }
        }

    }
}
