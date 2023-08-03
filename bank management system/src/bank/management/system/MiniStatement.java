package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class MiniStatement extends JFrame implements ActionListener{
    
    MiniStatement(String pinnumber){
        
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);
        
        JLabel bank = new JLabel("US Banck");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);
        
        try{
            Conn conn = new Conn();
//            ResultSet rs = conn.s.executeQuery("select * from login where pin = '1234'");
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card Number: " + "XXXX-XXXX-XXXX-" + rs.getString("cardnumber").substring(11, 16));
            }
        } catch (Exception ae){
            System.out.println(ae);
        }
        
        try{
            Conn conn = new Conn();
            int bal = 0;
//            ResultSet rs = conn.s.executeQuery("select * from login where pin = '1234'");
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()){
                mini.setText(mini.getText()  + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                 if(rs.getString("type").equals("Deposit")){
                        bal += Integer.parseInt(rs.getString("amount"));
                    }else{
                        bal  -= Integer.parseInt(rs.getString("amount"));
                    }
            }
            balance.setText("Your current account balance is Rs "+ bal);
        }catch (Exception ae){
            System.out.println(ae);
        }
        
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
    
    public static void main(String[] args){
        new MiniStatement("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
