package com.itgroup.dao;

import com.itgroup.bean.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao extends SuperDao{
    public int createItem(String iname, int itemcode, int price){
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into item values(?,?,?)";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,iname);
            pstmt.setInt(2,itemcode);
            pstmt.setInt(3,price);

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

    public List<Item> allitem() {
        List<Item> items = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * from item";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Item i = new Item();
                i.setIname(rs.getString("iname"));
                i.setItemcode(rs.getInt("itemcode"));
                i.setPrice(rs.getInt("price"));
                items.add(i);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(rs != null){rs.close();}
                if(pstmt != null){pstmt.close();}
                if(conn != null){conn.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return items;
    }

    public int deleteitem(int code) {
        int cnt = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "delete from item where itemcode = ?";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,code);
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

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        return cnt;
    }
}
