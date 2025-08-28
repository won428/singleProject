package com.itgroup.dao;

import com.itgroup.bean.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 유저 관련 Dao
// 유저 Dao 메소드 시작할때 now = on 끝날때 now = off
public class UsersDao extends SuperDao{




    public int createuser(String id, String password, String name, int hp,  int exp, int dmg){
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into users values(?,?,?,?,?,?)";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,password);
            pstmt.setString(3,name);
            pstmt.setInt(4,hp);
            pstmt.setInt(5,exp);
            pstmt.setInt(6,dmg);
            cnt = pstmt.executeUpdate();

            conn.commit();
            try {
                conn.rollback();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }catch (Exception ex2){
                ex2.printStackTrace();
        }finally {
            try {
                if(pstmt != null){}
                if(conn != null){}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }



        return cnt;
    }


    // 회원 전체 조회
    public List<Users> checkusers() {
        List<Users> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from users";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Users user = new Users();
                user.setId(rs.getString("id"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setHp(rs.getInt("hp"));
                user.setExp(rs.getInt("exp"));


                users.add(user);
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



        return users;
    }

    public Users findid(String id) {
        Users user = new Users();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from users where id = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setHp(rs.getInt("hp"));
            user.setExp(rs.getInt("exp"));
            user.setDmg(rs.getInt("dmg"));
            user.setLv(rs.getInt("lv"));
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

        return user;
    }


    public void updatehp(int hp, String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update users set hp = ? where id = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,hp);
            pstmt.setString(2,id);
            pstmt.executeUpdate();

            conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2) {

                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void updateexp(int exp, String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "update users set exp = ? where id = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,exp);
            pstmt.setString(2,id);
            pstmt.executeUpdate();

            conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2) {

                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public int signup(String id, String password, String name) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into users(id,password,name)values(?,?,?)";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,password);
            pstmt.setString(3,name);
            cnt = pstmt.executeUpdate();

            conn.commit();
            try {
                conn.rollback();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }catch (Exception ex2){
            ex2.printStackTrace();
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }

    public int deleteid(String deleteid) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from users where id = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,deleteid);
            cnt = pstmt.executeUpdate();

            conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return cnt;
    }

    public int updateid(String update, int updatenum, String updateID) {
        int cnt = -1;
        String sql = "update users set "+ update + "= ? where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,updatenum);
            pstmt.setString(2,updateID);
            cnt = pstmt.executeUpdate();
            conn.commit();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return cnt;
    }

    public int updatelv(String id, int lvhp, int lvexp, int lvdmg, int lvup) {
        int cnt = -1;
        String sql = "update users set hp = ?, exp = ?, dmg = ?, lv = ? where id = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,lvhp);
            pstmt.setInt(2,lvexp);
            pstmt.setInt(3,lvdmg);
            pstmt.setInt(4,lvup);
            pstmt.setString(5,id);
            cnt =pstmt.executeUpdate();
        }catch (Exception ex){
            try {
                conn.rollback();
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }finally {
            try {
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


        return cnt;
    }
}

