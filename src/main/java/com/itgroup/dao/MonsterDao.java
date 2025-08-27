package com.itgroup.dao;

import com.itgroup.bean.Bag;
import com.itgroup.bean.Item;
import com.itgroup.bean.Monster;
import com.itgroup.bean.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MonsterDao extends SuperDao {

    public int createMonster(int monstercode, String mname, int hp, int item, int exp, int dmg) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into monster values(?,?,?,?,?,?)";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, monstercode);
            pstmt.setString(2, mname);
            pstmt.setInt(3, hp);
            pstmt.setInt(4, item);
            pstmt.setInt(5, exp);
            pstmt.setInt(6, dmg);
            cnt = pstmt.executeUpdate();

            conn.commit();
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex2) {
            ex2.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                }
                if (conn != null) {
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        return cnt;
    }

    public List<Monster> checkmonster() {
        List<Monster> monsters = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from monster";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Monster monster = new Monster();
                monster.setMonstercode(rs.getInt("monstercode"));
                monster.setMname(rs.getString("mname"));
                monster.setHp(rs.getInt("hp"));
                monster.setItem(rs.getInt("item"));
                monster.setExp(rs.getInt("exp"));
                monster.setDmg(rs.getInt("dmg"));

                monsters.add(monster);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                }
                if (pstmt != null) {
                }
                if (conn != null) {
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return monsters;
    }

    public Monster checkmonsterOne(int monstercode) {
        Monster monster = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from monster where monstercode = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, monstercode);
            rs = pstmt.executeQuery();

            if (rs.next()){
                monster = new Monster();
                monster.setMonstercode(rs.getInt("monstercode"));
                monster.setMname(rs.getString("mname"));
                monster.setHp(rs.getInt("hp"));
                monster.setItem(rs.getInt("item"));
                monster.setExp(rs.getInt("exp"));
                monster.setDmg(rs.getInt("dmg"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){}
                if(pstmt != null){}
                if(conn != null){}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return monster;
    }


    public Item getMonsterItem(int monstercode) {
        Item item = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from monster m join item i on m.item = i.itemcode where m.monstercode = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,monstercode);
            rs = pstmt.executeQuery();
            if(rs.next()){
                item = new Item();
                item.setIname(rs.getString("iname"));
                item.setItemcode(rs.getInt("itemcode"));
                item.setPrice(rs.getInt("price"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();};
                if(pstmt != null){pstmt.close();};
                if(conn != null){conn.close();};
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return item;
    }

    public List<Monster> allmonster() {
        List<Monster> monsters = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from monster";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Monster m = new Monster();
                m.setMonstercode(rs.getInt("monstercode"));
                m.setMname(rs.getString("mname"));
                m.setHp(rs.getInt("hp"));
                m.setItem(rs.getInt("item"));
                m.setExp(rs.getInt("exp"));
                m.setDmg(rs.getInt("dmg"));
                monsters.add(m);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();};
                if(pstmt != null){pstmt.close();};
                if(conn != null){conn.close();};
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return monsters;
    }

    public int deletemonster(int code) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from monster where monstercode = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,code);
            cnt = pstmt.executeUpdate();

        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }
}


