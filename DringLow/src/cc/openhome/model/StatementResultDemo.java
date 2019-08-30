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

public class StatementResultDemo {
    public static void main(String[] args) {
        DBInterface c = null;
        try {
            c = new Connector();
            c.courseInsert(1211, 0, "物理學", "林伯星");
            c.addAppraise(1210, 3, 3, 3, "測試", "test");
            c.addBook(0, "testbook", 1000, "test", 0, "tester");
            c.addStuff(0, "testsruff", 1000, "test", 0, "tester");
            c.addGossip(0, "test!", "yosoro", "tester");
            c.addGossipReply(0, "haha", "tester");
            c.addUser("123@456.789", "tester", "pass");
            if(c.verifyUser("tester", "test")) {
                System.out.println("ok!");
            }
            else {
                System.out.println("no!");
            }

            if(c.verifyUser("tester", "pass")) {
                System.out.println("ok!");
            }
            else {
                System.out.println("no!");
            }

            c.Close();

            if (false) {
                IOException e = new IOException();
                throw e;
            }
            if (false) {
                ClassNotFoundException e = new ClassNotFoundException();
                throw e;
            }
            if (false) {
                SQLException e = new SQLException();
                throw e;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(c != null) {
                c.Close();
            }
        } 
    }
}