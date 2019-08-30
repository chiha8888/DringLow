package cc.openhome.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.ArrayList;

public class Connector implements DBInterface {
    DBSource dbsource;
    Connection conn;
    PreparedStatement book_ins;
    //PreparedStatement book_upd;
    PreparedStatement book_sel_id;
    PreparedStatement book_sel_tag;
    PreparedStatement book_sel_all;

    PreparedStatement course_ins;
    PreparedStatement course_upd;
    PreparedStatement course_sel_id;
    PreparedStatement course_sel_tag;
    PreparedStatement course_sel_all;

    PreparedStatement course_appra_ins;
    //PreparedStatement course_appra_upd;
    PreparedStatement course_appra_sel_id;
    PreparedStatement course_appra_sel_all;

    PreparedStatement gossip_ins;
    PreparedStatement gossip_upd;
    PreparedStatement gossip_sel_id;
    PreparedStatement gossip_sel_all;

    PreparedStatement gossip_reply_ins;
    //PreparedStatement gossip_reply_upd;
    PreparedStatement gossip_reply_sel_id;
    PreparedStatement gossip_reply_sel_all;

    PreparedStatement stuff_ins;
    //PreparedStatement stuff_upd;
    PreparedStatement stuff_sel_id;
    PreparedStatement stuff_sel_tag;
    PreparedStatement stuff_sel_all;

    PreparedStatement user_ins;
    //PreparedStatement user_upd;
    PreparedStatement user_sel_username;
    PreparedStatement user_sel_all;

    public Connector() {
        DBSource dbsource = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            dbsource = new SimpleDBSource();
            conn = dbsource.getConnection();

            book_ins = conn.prepareStatement(
                "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?)");
            //book_upd = conn.prepareStatement();
            book_sel_id = conn.prepareStatement(
                "SELECT * FROM book WHERE id = ?");
            book_sel_tag = conn.prepareStatement(
                "SELECT * FROM book WHERE tag = ?");
            book_sel_all = conn.prepareStatement(
                "SELECT * FROM book");

            course_ins = conn.prepareStatement(
                "INSERT INTO course VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            course_upd = conn.prepareStatement(
                "UPDATE course SET score = score + ?, difficulty = difficulty + ?, reward = reward + ?, student = student + 1 WHERE id = ?");
            course_sel_id = conn.prepareStatement(
                "SELECT * FROM course WHERE id = ?");
            course_sel_tag = conn.prepareStatement(
                "SELECT * FROM course WHERE tag = ?");
            course_sel_all = conn.prepareStatement(
                "SELECT * FROM course");

            course_appra_ins = conn.prepareStatement(
                "INSERT INTO course_appra VALUES(?, ?, ?)");
            //course_appra_upd = conn.prepareStatement();
            course_appra_sel_id = conn.prepareStatement(
                "SELECT * FROM course_appra WHERE id = ?");
            course_appra_sel_all = conn.prepareStatement(
                "SELECT * FROM course_appra");

            gossip_ins = conn.prepareStatement(
                "INSERT INTO gossip VALUES(?, ?, ?, ?, ?)");
            gossip_upd = conn.prepareStatement(
                "UPDATE gossip SET reply = reply + 1 WHERE id = ?");
            gossip_sel_id = conn.prepareStatement(
                "SELECT * FROM gossip WHERE id = ?");
            gossip_sel_all = conn.prepareStatement(
                "SELECT * FROM gossip");

            gossip_reply_ins = conn.prepareStatement(
                "INSERT INTO gossip_reply VALUES(?, ?, ?)");
            //gossip_reply_upd = conn.prepareStatement();
            gossip_reply_sel_id = conn.prepareStatement(
                "SELECT * FROM gossip_reply WHERE id = ?");
            gossip_reply_sel_all = conn.prepareStatement(
                "SELECT * FROM gossip_reply");

            stuff_ins = conn.prepareStatement(
                "INSERT INTO stuff VALUES(?, ?, ?, ?, ? ,?)");
            //stuff_upd = conn.prepareStatement();
            stuff_sel_id = conn.prepareStatement(
                "SELECT * FROM stuff WHERE id = ?");
            stuff_sel_tag = conn.prepareStatement(
                "SELECT * FROM stuff WHERE tag = ?");
            stuff_sel_all = conn.prepareStatement(
                "SELECT * FROM stuff");

            user_ins = conn.prepareStatement(
                "INSERT INTO user VALUES(?, ?, ?)");
            //user_upd = conn.prepareStatement();
            user_sel_username = conn.prepareStatement(
                "SELECT * FROM user WHERE username = ?");
            user_sel_all = conn.prepareStatement(
                "SELECT * FROM user");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean courseInsert(long id, int tag, String name, String teacher) {
        try {
            course_ins.setLong(1, id);
            course_ins.setInt(2, tag);
            course_ins.setString(3, name);
            course_ins.setString(4, teacher);
            course_ins.setInt(5, 0);
            course_ins.setInt(6, 0);
            course_ins.setInt(7, 0);
            course_ins.setInt(8, 0);
            
            course_ins.executeUpdate();
            course_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return true;
    }
    public boolean courseUpdate(long id, int score, int diff, int rew) {
        try {
            course_upd.setInt(1, score);
            course_upd.setInt(2, diff);
            course_upd.setInt(3, rew);
            course_upd.setLong(4, id);
            
            course_upd.executeUpdate();
            course_upd.clearParameters();
        }
        catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return true;
    }
    public List<String> courseSelect(long id){
        List<String> ret = new ArrayList<>();
        try {
            course_sel_id.setLong(1, id);

            ResultSet result = course_sel_id.executeQuery();
            while(result.next()) {
                ret.add(result.getString(1));
                ret.add(result.getString(2));
                ret.add(result.getString(3));
                ret.add(result.getString(4));
                ret.add(result.getString(5));
                ret.add(result.getString(6));
                ret.add(result.getString(7));
                ret.add(result.getString(8));
            }
            course_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<>();
            //e.printStackTrace();
        }
        return ret;
    }
    public void Close() {
        if(book_ins != null) {
            try {
                book_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(book_upd != null) {
            try {
                book_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(book_sel_id != null) {
            try {
                book_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(book_sel_tag != null) {
            try {
                book_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(book_sel_all != null) {
            try {
                book_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(course_ins != null) {
            try {
                course_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(course_upd != null) {
            try {
                course_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(course_sel_id != null) {
            try {
                course_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(course_sel_tag != null) {
            try {
                course_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(course_sel_all != null) {
            try {
                course_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(course_appra_ins != null) {
            try {
                course_appra_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(course_appra_upd != null) {
            try {
                course_appra_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(course_appra_sel_id != null) {
            try {
                course_appra_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(course_appra_sel_tag != null) {
            try {
                course_appra_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(course_appra_sel_all != null) {
            try {
                course_appra_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(gossip_ins != null) {
            try {
                gossip_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(gossip_upd != null) {
            try {
                gossip_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(gossip_sel_id != null) {
            try {
                gossip_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(gossip_sel_tag != null) {
            try {
                gossip_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(gossip_sel_all != null) {
            try {
                gossip_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(gossip_reply_ins != null) {
            try {
                gossip_reply_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(gossip_reply_upd != null) {
            try {
                gossip_reply_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(gossip_reply_sel_id != null) {
            try {
                gossip_reply_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(gossip_reply_sel_tag != null) {
            try {
                gossip_reply_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(gossip_reply_sel_all != null) {
            try {
                gossip_reply_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(stuff_ins != null) {
            try {
                stuff_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(stuff_upd != null) {
            try {
                stuff_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(stuff_sel_id != null) {
            try {
                stuff_sel_id.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(stuff_sel_tag != null) {
            try {
                stuff_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        if(stuff_sel_all != null) {
            try {
                stuff_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(user_ins != null) {
            try {
                user_ins.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(user_upd != null) {
            try {
                user_upd.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(user_sel_username != null) {
            try {
                user_sel_username.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        /*if(user_sel_tag != null) {
            try {
                user_sel_tag.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }*/
        if(user_sel_all != null) {
            try {
                user_sel_all.close();
            }   
            catch(SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null) {
            try {
                dbsource.closeConnection(conn);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<List<String>> getCourses(int tag) throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            ResultSet result = null;
            if(tag != 999) {
                course_sel_tag.setInt(1, tag);
                result = course_sel_tag.executeQuery();
            }
            else {
                result = course_sel_all.executeQuery();
            }
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                cour.add(result.getString(1));
                //cour.add(result.getString(2));
                cour.add(result.getString(3));
                cour.add(result.getString(4));
                cour.add(result.getString(5));
                cour.add(result.getString(6));
                cour.add(result.getString(7));
                cour.add(result.getString(8));
                ret.add(cour);
            }
            course_sel_tag.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            //e.printStackTrace();
        }
        return ret;
    }

    public List<String> getCourse(long id) throws SQLException{
        List<String> ret = new ArrayList<>();
        try {
            course_sel_id.setLong(1, id);

            ResultSet result = course_sel_id.executeQuery();
            while(result.next()) {
                //ret.add(result.getString(1));
                //ret.add(result.getString(2));
                ret.add(result.getString(3));
                ret.add(result.getString(4));
                ret.add(result.getString(5));
                ret.add(result.getString(6));
                ret.add(result.getString(7));
                ret.add(result.getString(8));
            }
            course_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<>();
            //e.printStackTrace();
        }
        return ret;
    }
    public List<List<String>> getCourseAppraises(long id) throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            course_appra_sel_id.setLong(1, id);

            ResultSet result = course_appra_sel_id.executeQuery();
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                cour.add(result.getString(2));
                cour.add(result.getString(3));
                ret.add(cour);
            }
            course_appra_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            //e.printStackTrace();
        }
        return ret;
    }
    public boolean addAppraise(long id, int teacher, int sweet,
        int rich, String appraise, String username) throws SQLException{
        try {
            course_appra_ins.setLong(1, id);
            course_appra_ins.setString(2, username);
            course_appra_ins.setString(3, appraise);
            course_appra_ins.executeUpdate();
            course_appra_ins.clearParameters();

            if (!courseUpdate(id, teacher, sweet, rich)){
                throw new Exception();
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<List<String>> getBooks(int tag) throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            ResultSet result = null;
            if(tag != 999) {
                book_sel_tag.setInt(1, tag);
                result = book_sel_tag.executeQuery();
            }
            else {
                result = book_sel_all.executeQuery();
            }
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                cour.add(result.getString(1));
                //cour.add(result.getString(2));
                cour.add(result.getString(3));
                cour.add(result.getString(4));
                cour.add(result.getString(5));
                //cour.add(result.getString(6));
                ret.add(cour);
            }
            book_sel_tag.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            //e.printStackTrace();
        }
        return ret;
    }
    public List<String> getBook(long id) throws SQLException{
        List<String> ret = new ArrayList<>();
        try {
            book_sel_id.setLong(1, id);

            ResultSet result = book_sel_id.executeQuery();
            while(result.next()) {
                //ret.add(result.getString(1));
                //ret.add(result.getString(2));
                ret.add(result.getString(3));
                ret.add(result.getString(4));
                ret.add(result.getString(5));
                ret.add(result.getString(6));
            }
            book_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<>();
            //e.printStackTrace();
        }
        return ret;
    }
    public boolean addBook(long id, String name, int price,
        String describe, int tag, String username) throws SQLException{
        try {
            book_ins.setLong(1, id);
            book_ins.setInt(2, tag);
            book_ins.setString(3, name);
            book_ins.setInt(4, price);
            book_ins.setString(5, username);
            book_ins.setString(6, describe);
            book_ins.executeUpdate();
            book_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<List<String>> getStuffs(int tag) throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            ResultSet result = null;
            if(tag != 999){
                stuff_sel_tag.setInt(1, tag);
                result = stuff_sel_tag.executeQuery();
            }
            else {
                result = stuff_sel_all.executeQuery();
            }
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                cour.add(result.getString(1));
                //cour.add(result.getString(2));
                cour.add(result.getString(3));
                cour.add(result.getString(4));
                cour.add(result.getString(5));
                //cour.add(result.getString(6));
                ret.add(cour);
            }
            stuff_sel_tag.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            //e.printStackTrace();
        }
        return ret;
    }
    public List<String> getStuff(long id) throws SQLException{
        List<String> ret = new ArrayList<>();
        try {
            stuff_sel_id.setLong(1, id);

            ResultSet result = stuff_sel_id.executeQuery();
            while(result.next()) {
                //ret.add(result.getString(1));
                //ret.add(result.getString(2));
                ret.add(result.getString(3));
                ret.add(result.getString(4));
                ret.add(result.getString(5));
                ret.add(result.getString(6));
            }
            stuff_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<>();
            //e.printStackTrace();
        }
        return ret;
    }
    public boolean addStuff(long id, String name, int price,
        String describe, int tag, String username) throws SQLException{
        try {
            stuff_ins.setLong(1, id);
            stuff_ins.setInt(2, tag);
            stuff_ins.setString(3, name);
            stuff_ins.setInt(4, price);
            stuff_ins.setString(5, username);
            stuff_ins.setString(6, describe);
            stuff_ins.executeUpdate();
            stuff_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<List<String>> getGossips() throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            ResultSet result = gossip_sel_all.executeQuery();
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                cour.add(result.getString(1));
                cour.add(result.getString(2));
                cour.add(result.getString(3));
                cour.add(result.getString(4));
                //cour.add(result.getString(5));
                ret.add(cour);
            }
            gossip_sel_all.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            e.printStackTrace();
        }
        return ret;
    }
    public boolean addGossip(long id, String title, String content,
        String username) throws SQLException{
        try {
            gossip_ins.setLong(1, id);
            gossip_ins.setInt(2, 0);
            gossip_ins.setString(3, username);
            gossip_ins.setString(4, title);
            gossip_ins.setString(5, content);
            gossip_ins.executeUpdate();
            gossip_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<String> getGossip(long id) throws SQLException{
        List<String> ret = new ArrayList<>();
        try {
            gossip_sel_id.setLong(1, id);

            ResultSet result = gossip_sel_id.executeQuery();
            while(result.next()) {
                //ret.add(result.getString(1));
                //ret.add(result.getString(2));
                ret.add(result.getString(4));
                ret.add(result.getString(5));
                ret.add(result.getString(3));
            }
            gossip_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<>();
            //e.printStackTrace();
        }
        return ret;
    }
    public List<List<String>> getGossipReply(long id) throws SQLException{
        List<List<String>> ret = new ArrayList<List<String>>();
        try {
            gossip_reply_sel_id.setLong(1, id);

            ResultSet result = gossip_reply_sel_id.executeQuery();
            while(result.next()) {
                List<String> cour = new ArrayList<String>();
                //cour.add(result.getString(1));
                cour.add(result.getString(2));
                cour.add(result.getString(3));
                ret.add(cour);
            }
            gossip_reply_sel_id.clearParameters();
        }
        catch (Exception e) {
            ret = new ArrayList<List<String>>();
            //e.printStackTrace();
        }
        return ret;
    }
    public boolean addGossipReply(long id, String content,
        String username) throws SQLException{
        try {
            gossip_upd.setLong(1, id);
            gossip_upd.executeUpdate();
            gossip_upd.clearParameters();

            gossip_reply_ins.setLong(1, id);
            gossip_reply_ins.setString(2, username);
            gossip_reply_ins.setString(3, content);
            gossip_reply_ins.executeUpdate();
            gossip_reply_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean addUser(String email, String username,
        String password) throws SQLException{
        try {
            user_ins.setString(1, username);
            user_ins.setString(2, email);
            user_ins.setString(3, password);
            user_ins.executeUpdate();
            user_ins.clearParameters();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    public boolean verifyUser(String username, String password) throws SQLException{
        boolean ret = false;
        try {
            user_sel_username.setString(1, username);

            ResultSet result = user_sel_username.executeQuery();
            while(result.next()) {
                if(password.equals(result.getString(3))) {
                    ret = true;
                }
            }
            user_sel_username.clearParameters();
        }
        catch (Exception e) {
            ret = false;
            //e.printStackTrace();
        }
        return ret;
    }
    public boolean userExist(String username) throws SQLException{
        boolean ret = false;
        try {
            user_sel_username.setString(1, username);

            ResultSet result = user_sel_username.executeQuery();
            while(result.next()) {
                ret = true;
            }
            user_sel_username.clearParameters();
        }
        catch (Exception e) {
            ret = false;
            //e.printStackTrace();
        }
        return ret;
    }
}
