import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main1{
    static Connection getConnection() throws Exception {
    return DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/college_system",
        "root",
        "Ansh@1827"
    );
}

static void addStudent(int rollNo, String name, String course, String email) {
    try {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO students VALUES ("
            + rollNo + ", '" + name + "', '" + course + "', '" + email + "')");
        con.close();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
static Object[][] getStudentData() {
    try {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        // Store data in a list first
        java.util.ArrayList<Object[]> list = new java.util.ArrayList<>();
        while (rs.next()) {
            Object[] row = {
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getString("email")
            };
            list.add(row);
        }
        con.close();

        // Convert list to array
        return list.toArray(new Object[0][0]);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return new Object[0][0];
    }
}
static Object[][] searchStudent(int rollNO){
    try{
        Connection con=getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs= stmt.executeQuery(
             "SELECT * FROM students WHERE roll_no = " + rollNO
        );
         java.util.ArrayList<Object[]> list = new java.util.ArrayList<>();
        while (rs.next()) {
             Object[] row = {
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getString("course"),
                rs.getString("email")
            };
            list.add(row);
        }
        con.close();
        return list.toArray(new Object[0][0]);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return new Object[0][0];
    }
}
static Object[][] getAttendancepercentage(){
    try{
        Connection con=getConnection();
         Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
        "Select students.roll_no, students.name," +
        "Count(*)As Total_days,"+
        "SUM(attendance.status = 'Present') AS present_days, " +
        "(SUM(attendance.status = 'Present') / COUNT(*)) * 100 AS percentage " +
         "FROM students JOIN attendance ON students.roll_no = attendance.roll_no " +
            "GROUP BY students.roll_no, students.name"
        );
        java.util.ArrayList<Object[]> list = new java.util.ArrayList<>();
        while (rs.next()) {
            Object[] row = {
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getInt("total_days"),
                rs.getInt("present_days"),
                rs.getDouble("percentage") + "%"
            };
            list.add(row);
        }
        con.close();
        return list.toArray(new Object[0][0]);
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        return new Object[0][0];
    }
}
static void deleteStudent(int rollNo) {
    try {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM students WHERE roll_no = " + rollNo);
        con.close();
        JOptionPane.showMessageDialog(null, "Student deleted successfully!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
    static void openDashboard() {
    JFrame dashboard = new JFrame("Dashboard");
    dashboard.setSize(400, 400);
    dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    dashboard.setLocationRelativeTo(null);
    dashboard.setLayout(null);

    // Title
    JLabel title = new JLabel("Welcome Admin!", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 18));
    title.setBounds(50, 20, 300, 30);

    // Buttons
    JButton addStudentBtn = new JButton("Add Student");
    addStudentBtn.setBounds(130, 80, 150, 35);

    JButton viewStudentBtn = new JButton("View Students");
    viewStudentBtn.setBounds(130, 130, 150, 35);

    JButton attendanceBtn = new JButton("Mark Attendance");
    attendanceBtn.setBounds(130, 180, 150, 35);

    JButton resultsBtn = new JButton("View Results");
    resultsBtn.setBounds(130, 230, 150, 35);
    JButton attPercentBtn = new JButton("Attendance %");
    attPercentBtn.setBounds(130, 280, 150, 35);
    dashboard.getContentPane().setBackground(new Color(230, 240, 255));

    addStudentBtn.setBackground(new Color(76, 175, 80));
    addStudentBtn.setForeground(Color.WHITE);

    viewStudentBtn.setBackground(new Color(33, 150, 243));
    viewStudentBtn.setForeground(Color.WHITE);
    attendanceBtn.setBackground(new Color(255, 152, 0));
    attendanceBtn.setForeground(Color.WHITE);
    resultsBtn.setBackground(new Color(156, 39, 176));
    resultsBtn.setForeground(Color.WHITE);
    attPercentBtn.setBackground(new Color(0, 150, 136));
    attPercentBtn.setForeground(Color.WHITE);
    Font btnFont = new Font("Arial", Font.BOLD, 13);
   addStudentBtn.setFont(btnFont);
    viewStudentBtn.setFont(btnFont);
    attendanceBtn.setFont(btnFont);
    resultsBtn.setFont(btnFont);
    attPercentBtn.setFont(btnFont);
    // Add to dashboard
    dashboard.add(attPercentBtn);
    dashboard.add(title);
    dashboard.add(addStudentBtn);
    dashboard.add(viewStudentBtn);
    dashboard.add(attendanceBtn);
    dashboard.add(resultsBtn);
    addStudentBtn.addActionListener(e->{
        JFrame addFrame=new JFrame("Add Student");
        addFrame.setSize(400, 300);
        addFrame.setLocationRelativeTo(null);
        addFrame.setLayout(null);
        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(50, 30, 100, 25);
        JTextField rollField = new JTextField();
        rollField.setBounds(160, 30, 150, 25);
         JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 100, 25);
         JTextField nameField = new JTextField();
        nameField.setBounds(160, 70, 150, 25);
         JLabel courseLabel = new JLabel("Course:");
         courseLabel.setBounds(50, 110, 100, 25);
         JTextField courseField = new JTextField();
         courseField.setBounds(160, 110, 150, 25);
         JLabel emailLabel = new JLabel("Email:");
         emailLabel.setBounds(50, 150, 100, 25);
         JTextField emailField = new JTextField();
         emailField.setBounds(160, 150, 150, 25);
        JButton saveBtn = new JButton("Save Student");
         saveBtn.setBounds(130, 200, 130, 30);
         addFrame.add(rollLabel); addFrame.add(rollField);
         addFrame.add(nameLabel); addFrame.add(nameField);
         addFrame.add(courseLabel); addFrame.add(courseField);
         addFrame.add(emailLabel); addFrame.add(emailField);
         addFrame.add(saveBtn);
         saveBtn.addActionListener(ev -> {
        String rollText = rollField.getText().trim();
    String name = nameField.getText().trim();
    String course = courseField.getText().trim();
    String email = emailField.getText().trim();
    if (rollText.isEmpty() || name.isEmpty() || course.isEmpty() || email.isEmpty()) {
    JOptionPane.showMessageDialog(addFrame, "Please fill all fields!");
    return;
}
    try {
    int rollNo = Integer.parseInt(rollText);
    addStudent(rollNo, name, course, email);
    JOptionPane.showMessageDialog(addFrame, "Student saved successfully!");
    addFrame.dispose();
}   catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(addFrame, "Roll No must be a number!");
}
});
         addFrame.setVisible(true);
    });
    viewStudentBtn.addActionListener(e -> {
    JFrame viewFrame = new JFrame("All Students");
    viewFrame.setSize(520, 430);
    viewFrame.setLocationRelativeTo(null); 
    viewFrame.setLayout(null);
    Object[][] data = getStudentData();
    String[] columns = {"Roll No", "Name", "Course", "Email"};
    JTable table = new JTable(data, columns);   
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(10, 45, 460, 300);
    viewFrame.add(scrollPane);
    JLabel deleteLabel = new JLabel("Delete Roll No:");
     deleteLabel.setBounds(20, 350, 120, 25);
    JTextField deleteField = new JTextField();
    deleteField.setBounds(140, 350, 100, 25);
    JButton deleteBtn = new JButton("Delete");
    deleteBtn.setBounds(250, 350, 90, 25);
    deleteBtn.addActionListener(ev -> {
    int rollNo = Integer.parseInt(deleteField.getText());
    deleteStudent(rollNo);
    Object[][] refreshData = getStudentData();
    table.setModel(new javax.swing.table.DefaultTableModel(refreshData, columns));
});

viewFrame.add(deleteLabel);
viewFrame.add(deleteField);
viewFrame.add(deleteBtn);
JLabel searchLabel = new JLabel("Search Roll No:");
searchLabel.setBounds(20, 10, 120, 25);

JTextField searchField = new JTextField();
searchField.setBounds(140, 10, 100, 25);

JButton searchBtn = new JButton("Search");
searchBtn.setBounds(250, 10, 80, 25);

JButton showAllBtn = new JButton("Show All");
showAllBtn.setBounds(340, 10, 90, 25);

// Move scrollPane down to make room for search bar
scrollPane.setBounds(10, 45, 460, 300);

searchBtn.addActionListener(ev -> {
    int rollNo = Integer.parseInt(searchField.getText());
    Object[][] searchdata = searchStudent(rollNo);
    table.setModel(new javax.swing.table.DefaultTableModel(searchdata, columns));
});

showAllBtn.addActionListener(ev -> {
    Object[][] alldata = getStudentData();
    String[] cols = {"Roll No", "Name", "Course", "Email"};
    table.setModel(new javax.swing.table.DefaultTableModel(alldata, cols));
});

viewFrame.add(searchLabel);
viewFrame.add(searchField);
viewFrame.add(searchBtn);
viewFrame.add(showAllBtn);
    viewFrame.setVisible(true);
});
attendanceBtn.addActionListener(e -> {
    JFrame attFrame = new JFrame("Mark Attendance");
    attFrame.setSize(400, 300);
    attFrame.setLocationRelativeTo(null);
    attFrame.setLayout(null);

    JLabel rollLabel = new JLabel("Roll No:");
    rollLabel.setBounds(50, 40, 100, 25);
    JTextField rollField = new JTextField();
    rollField.setBounds(160, 40, 150, 25);

    JLabel dateLabel = new JLabel("Date:");
    dateLabel.setBounds(50, 90, 100, 25);
    JTextField dateField = new JTextField("2026-07-12");
    dateField.setBounds(160, 90, 150, 25);

    JLabel statusLabel = new JLabel("Status:");
    statusLabel.setBounds(50, 140, 100, 25);

    // Dropdown for Present/Absent
    String[] options = {"Present", "Absent"};
    JComboBox<String> statusBox = new JComboBox<>(options);
    statusBox.setBounds(160, 140, 150, 25);

    JButton saveBtn = new JButton("Mark Attendance");
    saveBtn.setBounds(120, 200, 160, 30);

    saveBtn.addActionListener(ev -> {
        try {
            int rollNo = Integer.parseInt(rollField.getText());
            String date = dateField.getText();
            String status = (String) statusBox.getSelectedItem();

            Connection con = getConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO attendance (roll_no, date, status) VALUES ("
                + rollNo + ", '" + date + "', '" + status + "')");
            con.close();

            JOptionPane.showMessageDialog(attFrame, "Attendance marked successfully!");
            attFrame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(attFrame, "Error: " + ex.getMessage());
        }
    });

    attFrame.add(rollLabel); attFrame.add(rollField);
    attFrame.add(dateLabel); attFrame.add(dateField);
    attFrame.add(statusLabel); attFrame.add(statusBox);
    attFrame.add(saveBtn);

    attFrame.setVisible(true);
});
resultsBtn.addActionListener(e -> {
    JFrame resultsFrame = new JFrame("View Results");
    resultsFrame.setSize(500, 400);
    resultsFrame.setLocationRelativeTo(null);
    resultsFrame.setLayout(null);

    JLabel rollLabel = new JLabel("Enter Roll No:");
    rollLabel.setBounds(50, 30, 120, 25);
    JTextField rollField = new JTextField();
    rollField.setBounds(180, 30, 150, 25);

    JButton searchBtn = new JButton("Search");
    searchBtn.setBounds(340, 30, 80, 25);

    String[] columns = {"Name", "Subject", "Marks", "Grade"};
    JTable table = new JTable(new Object[0][0], columns);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(30, 80, 430, 250);

    searchBtn.addActionListener(ev -> {
        try {
            int rollNo = Integer.parseInt(rollField.getText());
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT students.name, results.subject, results.marks " +
                "FROM students JOIN results ON students.roll_no = results.roll_no " +
                "WHERE students.roll_no = " + rollNo
            );

            java.util.ArrayList<Object[]> list = new java.util.ArrayList<>();
            while (rs.next()) {
                int marks = rs.getInt("marks");

                // Calculate grade
                String grade;
                if (marks >= 90) grade = "A+";
                else if (marks >= 80) grade = "A";
                else if (marks >= 70) grade = "B";
                else if (marks >= 60) grade = "C";
                else grade = "F";

                Object[] row = {
                    rs.getString("name"),
                    rs.getString("subject"),
                    marks,
                    grade
                };
                list.add(row);
            }
            con.close();

            // Update table
            Object[][] data = list.toArray(new Object[0][0]);
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(resultsFrame, "Error: " + ex.getMessage());
        }
    });

    resultsFrame.add(rollLabel);
    resultsFrame.add(rollField);
    resultsFrame.add(searchBtn);
    resultsFrame.add(scrollPane);

    resultsFrame.setVisible(true);
});
attPercentBtn.addActionListener(e -> {
    JFrame attPctFrame = new JFrame("Attendance Percentage");
    attPctFrame.setSize(550, 400);
    attPctFrame.setLocationRelativeTo(null);

    String[] columns = {"Roll No", "Name", "Total Days", "Present Days", "Percentage"};
    Object[][] data = getAttendancepercentage();

    JTable table = new JTable(data, columns);
    JScrollPane scrollPane = new JScrollPane(table);
    attPctFrame.add(scrollPane);

    attPctFrame.setVisible(true);
});
     dashboard.setVisible(true);
}
    public static void main(String args[]){
       JFrame frame=new JFrame("College Management System- Login");
       frame.setSize(400,250);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.setLayout(null);
       JLabel title= new JLabel("College Management System", SwingConstants.CENTER);
       title.setFont(new Font("Arial",Font.BOLD,16));
       title.setBounds(50, 20, 300, 30);
       JLabel userLabel= new JLabel("Username:");
        userLabel.setBounds(80, 80, 100, 25);
         JTextField userField = new JTextField();
        userField.setBounds(180, 80, 150, 25);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(80, 120, 100, 25);
         JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 120, 150, 25);
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 170, 100, 30);
        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        loginButton.addActionListener(e->{
        String username=userField.getText();
        String password = new String(passField.getPassword());
         if (username.equals("AnshSharma") && password.equals("Ansh123")) {
        frame.dispose(); // close login window
         openDashboard(); // open dashboard
    } else {
        JOptionPane.showMessageDialog(frame, "Wrong username or password!");
    }
});
        frame.setVisible(true); 
    }
}