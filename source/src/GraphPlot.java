import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.awt.*;
public class GraphPlot extends JPanel{
    static int x = 0 , y = 0;
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/try","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM graph");
            while(rs.next()) {
                System.out.print(rs.getInt(1));
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
        g.drawLine();
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Draw Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.white);
        frame.setSize(600, 400);

        GraphPlot panel = new GraphPlot();

        frame.add(panel);

        frame.setVisible(true);
    }
}
