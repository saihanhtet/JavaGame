import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class RockPaperScissor extends JFrame {
    JLabel computer_choice,result;
    JLabel computerScore,playerScore;
    int player_score = 0;
    int computer_score = 0;
    ImageIcon imageRock,imagePaper,imageScissor;
    public void setup() {
        imageRock = new ImageIcon(new ImageIcon("src\\resources\\rock.png").getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
        imagePaper = new ImageIcon(new ImageIcon("src\\resources\\paper.png").getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
        imageScissor = new ImageIcon(new ImageIcon("src\\resources\\scissor.png").getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));

        // create labels
        JLabel score = new JLabel("Score");
        score.setBounds(10,0,100,100);
        computerScore= new JLabel();
        computerScore.setText("Computer : 0");
        computerScore.setBounds(10,20,100,100);
        playerScore= new JLabel();
        playerScore.setText("Player : 0");
        playerScore.setBounds(10,35,100,100);


        computer_choice = new JLabel();
        computer_choice.setHorizontalTextPosition(JLabel.CENTER);
        computer_choice.setVerticalTextPosition(JLabel.BOTTOM);
        computer_choice.setBounds(250,90,100,100);

        result = new JLabel();
        result.setHorizontalTextPosition(JLabel.CENTER);
        result.setVerticalTextPosition(JLabel.BOTTOM);
        result.setFont(new Font("Console",Font.BOLD,20));
        result.setBounds(270,450,100,100);

        JLabel label_com = new JLabel();
        label_com.setText("Computer");
        label_com.setBounds(260,180,100,100);

        JLabel label_vs = new JLabel();
        label_vs.setText("VS");
        label_vs.setBounds(280,200,100,100);

        JLabel label_play = new JLabel();
        label_play.setText("Player");
        label_play.setBounds(270,220,100,100);


        JLabel label_rock = new JLabel();
        label_rock.setText("Rock");
        label_rock.setIcon(imageRock); // set image into label
        label_rock.setHorizontalTextPosition(JLabel.CENTER);
        label_rock.setVerticalTextPosition(JLabel.BOTTOM);
        label_rock.setBounds(150,300,100,100);

        JLabel label_paper = new JLabel();
        label_paper.setText("Paper");
        label_paper.setIcon(imagePaper); // set image into label
        label_paper.setHorizontalTextPosition(JLabel.CENTER);
        label_paper.setVerticalTextPosition(JLabel.BOTTOM);
        label_paper.setBounds(250,300,100,100);

        JLabel label_scissor = new JLabel();
        label_scissor.setText("Scissor");
        label_scissor.setIcon(imageScissor); // set image into label
        label_scissor.setHorizontalTextPosition(JLabel.CENTER);
        label_scissor.setVerticalTextPosition(JLabel.BOTTOM);
        label_scissor.setBounds(350,300,100,100);

        // adding mouse listener to labels
        label_rock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculate(label_rock.getText());
            }
        });
        label_paper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculate(label_paper.getText());
            }
        });
        label_scissor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calculate(label_scissor.getText());
            }
        });

        // adding components into frame
        add(label_rock);
        add(label_paper);
        add(label_scissor);
        add(computer_choice);
        add(result);
        add(score);
        add(playerScore);
        add(computerScore);
        add(label_com);
        add(label_play);
        add(label_vs);
        setLayout(null);
        revalidate();
        repaint();
    }

    public void calculate(String player){
        String[] data = {"Rock","Paper","Scissor"};
        Random rand = new Random();
        String computer = data[rand.nextInt(data.length)];
        computer_choice.setText(computer.toUpperCase());
        if (computer.equals("Rock")){
            computer_choice.setIcon(imageRock);
        }
        else if (computer.equals("Paper")){
            computer_choice.setIcon(imagePaper);
        }
        else{
            computer_choice.setIcon(imageScissor);
        }
        // calculation
        String res= "";
        if (player.equals(computer)){
            res = "Draw";
        }
        else if (player.equals("Rock")){
            if (computer.equals("Paper")){
                res = "Loss";
                computer_score++;
            }
            else {
                res = "Win";
                player_score++;
            }
        }
        else if (player.equals("Paper")){
            if (computer.equals("Scissor")){
                res = "Loss";
                computer_score++;
            }
            else{
                res = "Win";
                player_score++;
            }
        }
        else{
            if (computer.equals("Rock")){
                res = "Loss";
                computer_score++;
            }
            else{
                res = "Win";
                player_score++;
            }
        }
        result.setText(res);
        playerScore.setText("Player : "+player_score);
        computerScore.setText("Computer : "+computer_score);
    }

    public static void main(String[] args) {
        // Creating Frame
        RockPaperScissor game = new RockPaperScissor();
        game.setTitle("Rock Paper Scissor");
        game.setBounds(200,20,600,600);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getContentPane().setBackground(Color.WHITE);
        game.setResizable(false);
        game.setVisible(true);
        game.setup();


    }
}