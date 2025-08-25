package com.itgroup.dao;

import com.itgroup.bean.Bag;
import com.itgroup.bean.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BagDao extends SuperDao{
    public List<Bag> findbag(String id) {
        List<Bag> bag = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select b.id, b.itemcode, i.iname from bag b join item i on b.itemcode = i.itemcode where b.id = ? order by i.iname asc";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Bag b = new Bag();
                b.setId(rs.getString("id"));
                b.setItemcode(rs.getInt("itemcode"));
                b.setIname(rs.getString("iname"));

                bag.add(b);
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

        return bag;
    }

    public void updatebag(String id, int itemcode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "insert into bag values(?,?)";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setInt(2,itemcode);
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
}
