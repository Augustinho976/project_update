package display;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DisplayCrud {
    private JTextField txtId;
    private JTextField txtName;
    private JTextArea txtGrade;
    private JTextField txtMarks;
    private JPanel Main;
    private JButton saveButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable table1;
    private JTextField txtAvg;
    private DbUtils Dbutils;

    public DisplayCrud() {
        average();
        showTableData();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = txtId.getText();
                String name = txtName.getText();
                String grade = txtGrade.getText();
                String marks = txtMarks.getText();


                Connection connection = null;
                PreparedStatement pst;
                ResultSet rs = null;
                String host = "localhost";
                String port = "5432";
                String db_name = "postgres";
                String username = "postgres";
                String password = "33744525";
                try {
                    String sql = "INSERT into skool1" + " (name,grade,marks)" + "values(?,?,?)";
                    connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
                    pst= connection.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setString(2, grade);
                    pst.setInt(3, Integer.parseInt(marks));
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Inserted");
                }
                catch (Exception e){
                    e.printStackTrace();

                }
                showTableData();
                average();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = txtId.getText();
                String name = txtName.getText();
                String grade = txtGrade.getText();
                String marks = txtMarks.getText();


                Connection connection = null;
                PreparedStatement pst;
                ResultSet rs = null;
                String host = "localhost";
                String port = "5432";
                String db_name = "postgres";
                String username = "postgres";
                String password = "33744525";
                try {
                    String sql = "DELETE from skool1 where student_id=?";
                    connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
                    pst= connection.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(id));

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Deleted");
                }
                catch (Exception e){
                    e.printStackTrace();

                }
                showTableData();
                average();

            }

        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String id = txtId.getText();
                String name = txtName.getText();
                String grade = txtGrade.getText();
                String marks = txtMarks.getText();


                Connection connection = null;
                PreparedStatement pst;
                ResultSet rs = null;
                String host = "localhost";
                String port = "5432";
                String db_name = "postgres";
                String username = "postgres";
                String password = "33744525";
                try {
                    String sql = "UPDATE skool1 SET name=?,grade=?, marks=? WHERE student_id=?";
                    connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
                    pst= connection.prepareStatement(sql);
                    pst.setInt(4, Integer.parseInt(id));
                    pst.setString(1, name);
                    pst.setString(2, grade);
                    pst.setInt(3, Integer.parseInt(marks));


                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Updated");
                }
                catch (Exception e){
                    e.printStackTrace();

                }
                showTableData();
                average();

            }
        });
    }
    public void showTableData(){
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pst;
        String host = "localhost";
        String port = "5432";
        String db_name = "postgres";
        String username = "postgres";
        String password = "33744525";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
            String query = "Select * from skool1";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            table1.setModel(Dbutils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void average() {
        float sum = 0;
        for (int i = 0; i < table1.getRowCount(); i++) {
            sum = sum + Integer.parseInt(table1.getValueAt(i, 3).toString());

        }
        float avg = sum/table1.getRowCount();
        txtAvg.setText((Float.toString(avg)));
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("DisplayCrud");
        frame.setContentPane(new DisplayCrud().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
