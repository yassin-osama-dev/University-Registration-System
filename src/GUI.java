import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private RegistrationSystem system;
    private JTextArea outputArea;

    private Student student;
    private Professor professor;

    public GUI() {

        try {
            system = new RegistrationSystem();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not load courses file");
        }

        student = new Student(
                "Yassin",
                "S1001",
                "yassin@gmail.com",
                "CS",
                3.5
        );

        professor = new Professor(
                "Dr Ahmed",
                "P2001",
                "ahmed@uni.com",
                "CS"
        );

        setTitle("University Registration System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2,3,10,10));

        JButton showCoursesBtn = new JButton("Show Courses");
        JButton addStudentBtn = new JButton("Add Student");
        JButton addProfessorBtn = new JButton("Add Professor");
        JButton registerBtn = new JButton("Register Courses");
        JButton studentBtn = new JButton("Student Profile");
        JButton professorBtn = new JButton("Professor Courses");

        buttonPanel.add(showCoursesBtn);
        buttonPanel.add(addStudentBtn);
        buttonPanel.add(addProfessorBtn);
        buttonPanel.add(registerBtn);
        buttonPanel.add(studentBtn);
        buttonPanel.add(professorBtn);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(outputArea);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        showCoursesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                outputArea.setText("=== COURSES ===\n\n");

                for (Courses c : system.courses) {

                    outputArea.append(
                            c.getCourseCode()
                                    + " - "
                                    + c.getTitle()
                                    + " ("
                                    + c.getCredits()
                                    + " credits)\n"
                    );
                }
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    system.Add_Student(student);

                    outputArea.setText(
                            "Student added successfully"
                    );

                } catch (Exception ex) {

                    outputArea.setText(
                            "Error adding student"
                    );
                }
            }
        });

        addProfessorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    system.Add_Proff(professor);

                    outputArea.setText(
                            "Professor added successfully"
                    );

                } catch (Exception ex) {

                    outputArea.setText(
                            "Error adding professor"
                    );
                }
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    if(system.courses.size() >= 2) {

                        Courses c1 =
                                system.courses.get(0);

                        Courses c2 =
                                system.courses.get(1);

                        student.registerCourse(
                                c1,
                                "Fall 2026"
                        );

                        student.registerCourse(
                                c2,
                                "Fall 2026"
                        );

                        professor.assignCourses(c1);
                        professor.assignCourses(c2);

                        outputArea.setText(
                                "Courses Registered:\n\n"
                                        + c1.getTitle()
                                        + "\n"
                                        + c2.getTitle()
                        );

                    }

                } catch (Exception ex) {

                    outputArea.setText(
                            ex.getMessage()
                    );
                }
            }
        });

        studentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                outputArea.setText(
                        "=== STUDENT PROFILE ===\n\n"
                );

                outputArea.append(
                        "Name: "
                                + student.getName()
                                + "\n"
                );

                outputArea.append(
                        "ID: "
                                + student.getID()
                                + "\n"
                );

                outputArea.append(
                        "Email: "
                                + student.getEmail()
                                + "\n\n"
                );

                outputArea.append(
                        "Registered Courses:\n"
                );

                for(Courses c :
                        student.getRegisteredCourses()) {

                    outputArea.append(
                            c.getCourseCode()
                                    + " - "
                                    + c.getTitle()
                                    + "\n"
                    );
                }

                outputArea.append(
                        "\nTotal Credits: "
                                + student.CalculateCredithours()
                );
            }
        });

        professorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                outputArea.setText(
                        "=== PROFESSOR COURSES ===\n\n"
                );

                for(Courses c :
                        professor.getTeachingCourses()) {

                    outputArea.append(
                            c.getCourseCode()
                                    + " - "
                                    + c.getTitle()
                                    + "\n"
                    );
                }
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new GUI().setVisible(true);
            }
        });
    }
}