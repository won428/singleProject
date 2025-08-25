package com.itgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
