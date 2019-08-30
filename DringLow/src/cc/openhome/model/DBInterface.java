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

public interface DBInterface {
    public List<List<String>> getCourses(int tag) throws SQLException;

    public List<String> getCourse(long id) throws SQLException;
    public List<List<String>> getCourseAppraises(long id) throws SQLException;
    public boolean addAppraise(long id, int teacher, int sweet,
        int rich, String appraise, String username) throws SQLException;

    public List<List<String>> getBooks(int tag) throws SQLException;
    public List<String> getBook(long id) throws SQLException;
    public boolean addBook(long id, String name, int price,
        String describe, int tag, String username) throws SQLException;

    public List<List<String>> getStuffs(int tag) throws SQLException;
    public List<String> getStuff(long id) throws SQLException;
    public boolean addStuff(long id, String name, int price,
        String describe, int tag, String username) throws SQLException;

    public List<List<String>> getGossips() throws SQLException;
    public boolean addGossip(long id, String title, String content,
        String username) throws SQLException;

    public List<String> getGossip(long id) throws SQLException;
    public List<List<String>> getGossipReply(long id) throws SQLException;
    public boolean addGossipReply(long id, String content,
        String username) throws SQLException;

    public boolean addUser(String email, String Username,
        String Password) throws SQLException;
    public boolean verifyUser(String Username, String Password) throws SQLException;
    public boolean userExist(String Username) throws SQLException;

    public void Close();
    public boolean courseInsert(long id, int tag, String name, String teacher);

    //下面這些不要用
    public boolean courseUpdate(long id, int score, int diff, int rew);
    public List<String> courseSelect(long id);
}