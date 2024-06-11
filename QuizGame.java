import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGame extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private Question[] questions = {
        new Question("Where is Ram Mandir located in India?", new String[]{"Delhi", "Gurgaon", "Ayodhya", "Varanasi"}, 2),
        new Question("What is the capital of India ?", new String[]{"Mumbai", "New Delhi", "Chennai", "Bangalore"}, 1),
        new Question("Which state is called rice bowl of India ?", new String[]{"Andhra Pradesh", "Goa", "Kerala", "Tamil Nadu"}, 0)
    };

    public QuizGame() {
        setTitle("Quiz Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel("", JLabel.CENTER);
        options = new JRadioButton[4];
        group = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        setLayout(new BorderLayout());
        add(questionLabel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question q = questions[currentQuestionIndex];
            questionLabel.setText(q.getQuestion());
            String[] choices = q.getChoices();

            for (int i = 0; i < options.length; i++) {
                options[i].setText(choices[i]);
                options[i].setSelected(false);
            }
        } else {
            showScore();
        }
    }

    private void showScore() {
        JOptionPane.showMessageDialog(this, "Your score: " + score + "/" + questions.length);
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            Question q = questions[currentQuestionIndex];
            int selectedAnswer = -1;

            for (int i = 0; i < options.length; i++) {
                if (options[i].isSelected()) {
                    selectedAnswer = i;
                    break;
                }
            }

            if (selectedAnswer == q.getCorrectAnswer()) {
                score++;
            }

            currentQuestionIndex++;
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizGame game = new QuizGame();
            game.setVisible(true);
        });
    }
}

class Question {
    private String question;
    private String[] choices;
    private int correctAnswer;

    public Question(String question, String[] choices, int correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

