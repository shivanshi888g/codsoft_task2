import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator {
    private JFrame frame;
    private JTextField[] marksFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;
    private JButton calculateButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StudentGradeCalculator window = new StudentGradeCalculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StudentGradeCalculator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Student Grade Calculator");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Enter Marks for 5 Subjects (out of 100):");
        titleLabel.setBounds(50, 20, 300, 25);
        frame.getContentPane().add(titleLabel);

        String[] subjects = {"English", "Maths", "Science", "History", "Geography"};
        marksFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            JLabel subjectLabel = new JLabel(subjects[i] + ":");
            subjectLabel.setBounds(50, 60 + (i * 40), 100, 25);
            frame.getContentPane().add(subjectLabel);

            marksFields[i] = new JTextField();
            marksFields[i].setBounds(150, 60 + (i * 40), 50, 25);
            frame.getContentPane().add(marksFields[i]);
            marksFields[i].setColumns(10);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 260, 100, 25);
        frame.getContentPane().add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setBounds(50, 300, 300, 25);
        frame.getContentPane().add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        averagePercentageLabel.setBounds(50, 330, 300, 25);
        frame.getContentPane().add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setBounds(50, 360, 300, 25);
        frame.getContentPane().add(gradeLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResults();
            }
        });
    }

    private void calculateResults() {
        int totalMarks = 0;
        int numSubjects = marksFields.length;

        for (JTextField marksField : marksFields) {
            try {
                int marks = Integer.parseInt(marksField.getText());
                totalMarks += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter valid marks for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        double averagePercentage = (double) totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage));
        gradeLabel.setText("Grade: " + grade);
    }

    private String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B+";
        } else if (averagePercentage >= 60) {
            return "B";
        } else if (averagePercentage >= 50) {
            return "C";
        } else if (averagePercentage >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}
