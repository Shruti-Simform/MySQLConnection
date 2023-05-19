package com.mysql;

import java.sql.*;
import java.util.Scanner;

public class MySQLCon {
    public static void main(String[] args) {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_shruti";
        String user = "freedb_Shruti";
        String password = "V7zFp3#ME*E?ph%";

        try {
            Scanner sc = new Scanner(System.in);
            int option;
            String query;
            boolean state = true;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            System.out.println("Connection is successful to the database : " + url);
            while (state) {
                System.out.println("---OPERATIONS---");
                System.out.println("1.Show data\n2.Insert data\n3.Delete data by empId\n4.Delete all records\n5.Update record on the basis of Id\n6.Exit");
                System.out.print("Choose an option from above: ");
                option = sc.nextInt();
                if (option == 1) {
                    query = "Select * from employee";
                    statement = con.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    System.out.println("***employee Table***");
                    while (rs.next()) {
                        System.out.print("empID: " + rs.getInt("empId"));
                        System.out.print("|empName: " + rs.getString("empName"));
                        System.out.print("|empDepartment: " + rs.getString("empDepartment"));
                        System.out.println("|empSalary: " + rs.getInt("empSalary"));
                    }
                    rs.close();
                } else if (option == 2) {
                    System.out.print("Employee name: ");
                    String name = sc.next();
                    System.out.print("Department: ");
                    String dept = sc.next();
                    System.out.print("Salary: ");
                    int salary = sc.nextInt();
                    query = "Insert into employee (empName, empDepartment, empSalary) values('" + name + "','" + dept + "'," + salary + ")";
                    statement.execute(query);
                    System.out.println("Data inserted!!");
                } else if (option == 3) {
                    System.out.print("Enter id for which you want to delete record: ");
                    int id = sc.nextInt();
                    query = "Delete from employee where empId = " + id;
                    PreparedStatement p = con.prepareStatement(query);
                    p.execute();
                    System.out.println("Data deleted for empId " + id + "!!");
                } else if (option == 4) {
                    System.out.print("Are you sure? Y/N : ");
                    char bool = sc.next().charAt(0);
                    if (bool == 'Y' || bool == 'y') {
                        query = "TRUNCATE TABLE employee";
                        statement.execute(query);
                        System.out.println("All records deleted!!");
                    } else if (bool == 'N' || bool == 'n') {
                        System.out.println("No deletion operation occurred!!");
                    } else {
                        System.out.println("Invalid input. Database closed!!");
                        state = false;
                    }
                } else if (option == 5) {
                    System.out.print("Update on Id:");
                    int conditionValue = sc.nextInt();
                    System.out.print("Update attribute: ");
                    String col = sc.next();
                    System.out.print("Updated value: ");
                    String value = sc.next();
                    query = "Update employee set " + col + "= '" + value + "' where empId = " + conditionValue;
                    PreparedStatement p = con.prepareStatement(query);
                    p.execute();
                    System.out.println("Record updated for empId " + conditionValue + "!!");
                } else if (option == 6) {
                    System.out.println("Database closed!!");
                    state = false;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
