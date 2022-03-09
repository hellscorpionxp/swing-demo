package com.tony;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainApp {

  private JFrame frame;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        MainApp window = new MainApp();
        window.frame.setVisible(true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Create the application.
   *
   * @wbp.parser.entryPoint
   */
  public MainApp() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1269, 585);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setBorder(new LineBorder(new Color(0, 0, 0)));
    frame.getContentPane().add(panel, BorderLayout.NORTH);
    panel.setLayout(new GridLayout(0, 2, 0, 0));

    JList<String> list = new JList<>();
    list.setModel(new DefaultListModel<>());
    JScrollPane panel_1 = new JScrollPane(list);
    panel_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    panel_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

    JLabel lblNewLabel = new JLabel("URL");
    panel.add(lblNewLabel);

    textField = new JTextField();
    panel.add(textField);
    textField.setColumns(10);

    JLabel lblNewLabel_1 = new JLabel("ID");
    panel.add(lblNewLabel_1);

    textField_1 = new JTextField();
    panel.add(textField_1);
    textField_1.setColumns(10);

    JLabel lblNewLabel_2 = new JLabel("Password");
    panel.add(lblNewLabel_2);

    textField_2 = new JTextField();
    panel.add(textField_2);
    textField_2.setColumns(10);

    JLabel lblNewLabel_3 = new JLabel("cmds");
    panel.add(lblNewLabel_3);

    textField_3 = new JTextField();
    panel.add(textField_3);
    textField_3.setColumns(10);

    JButton btnNewButton = new JButton("Start");
    btnNewButton.addActionListener(e -> {
      Request request = new Request.Builder()
          .url(HttpUrl.parse(textField.getText()).newBuilder().addQueryParameter("p1", textField_1.getText())
              .addQueryParameter("p2", textField_2.getText()).addQueryParameter("p3", textField_3.getText()).build())
          .get().build();
      OkHttpClient client = new OkHttpClient();
      Call call = client.newCall(request);
      try {
        Response response = call.execute();
//        System.out.println(response.body().string());
        ((DefaultListModel) list.getModel()).addElement(response.body().string());
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });

    panel.add(btnNewButton);
  }

}
