package com.itgroup.dao;

import com.itgroup.bean.Bag;
import com.itgroup.bean.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class BagDao extends SuperDao{
    public Set<Bag> findbag(String id) {
        Set<Bag> bag = new HashSet<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select count(b.itemcode) as itemcount , b.id, b.itemcode, i.iname from bag b join item i on b.itemcode = i.itemcode where b.id = ? group by b.id, b.itemcode, i.iname order by i.iname asc";
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
                b.setCount(rs.getInt("itemcount"));

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

   /* public Bag selectitem(String dropitemname, String id) {
        Bag b = new Bag();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select i.iname from bag b join item i on b.itemcode = i.itemcode where i.iname = '?' and b.id = '?'";
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dropitemname);
            pstmt.setString(2,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                b.setIname(rs.getString("iname"));
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

        return b;
    }*/

    public int deleteall(String id) {
        int cnt = -1;
        String sql = "delete from bag where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            cnt = pstmt.executeUpdate();
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

    public int checkitem(String deleteitemname, String id) {
        int cnt = -1;
        String sql = "select 1 from item i join bag b on i.itemcode = b.itemcode where i.iname = ? and b.id = ? group by b.id";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deleteitemname);
            pstmt.setString(2,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                cnt = rs.getInt("1");
            }
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            return cnt;
        }
    }

    public int deleteitems(String id, int itemcode, int itemnum) {
        int cnt = -1;
        String sql = "delete from bag where id = ? and itemcode = ? and rownum <= ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setInt(2,itemcode);
            pstmt.setInt(3,itemnum);
            cnt = pstmt.executeUpdate();
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

    public Item changecodename(String iname) {
        Item i = new Item();
        String sql = "select * from item where iname = ? ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,iname);
            rs = pstmt.executeQuery();
            while (rs.next()){
                i.setIname(rs.getString("iname"));
                i.setItemcode(rs.getInt("itemcode"));
                i.setPrice(rs.getInt("price"));
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
        return i;
    }

    /*public int deleteone(String dropitemname, String id) {     // sql 오류 사용안함 //
        int cnt = -1;
        String sql = "delete from bag b join item i on b.itemcod = i.itemcode where i.iname = ? and rownum = 1 and b.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,dropitemname);
            pstmt.setString(2,id);
            cnt = pstmt.executeUpdate();
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
    }*/
}
