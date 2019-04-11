import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.awt.*;
public class GraphPlot extends JPanel{
    static int x = 140 , y = 300;
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/try","root","root");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM graph");
            int x1 = 20;
            g.setColor(Color.BLACK);
            g.drawLine(20,30,20,300);
            g.setColor(Color.BLACK);
            g.drawLine(20,300,650,300);
            ArrayList<Integer> Val = new ArrayList<>();
            while(rs.next()) {
                int val = rs.getInt(1);
                Val.add(val);
            }
            int hor_inc = 600/Val.size();

            g2.setStroke(new BasicStroke(3));
            for(int i = 0 ; i < Val.size() ; i++){
                g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.setColor(Color.ORANGE);
                g2.drawLine(x1,y,x,350-30*Val.get(i));
                x1 = x;
                x += hor_inc;
                y = 350-30*Val.get(i);
            }
            con.close();
        }catch(Exception e){ System.out.println(e);}

    }

    public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Graph Plotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.white);
        frame.setSize(700, 400);

        GraphPlot panel = new GraphPlot();

        frame.add(panel);

        frame.setVisible(true);
    }
}
