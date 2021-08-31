package service.program;

import domain.Student;
import service.impl.LessonServiceImpl;
import service.impl.StudentServiceImpl;
import util.ApplicationContext;
import util.HibernateUtil;

import java.util.Optional;

public class App {

    private StudentServiceImpl studentService = ApplicationContext.getApplicationContext().getStudentService();
    private TeacherPanel teacherPanel = new TeacherPanel();
    private LessonServiceImpl lessonService = ApplicationContext.getApplicationContext().getLessonService();

    public void initialize() {
        ApplicationContext.getApplicationContext().getTeacherService().initialize();
        ApplicationContext.getApplicationContext().getStudentService().initialize();
    }

    {
        if (lessonService.findAll().size() == 0)
            lessonService.defaultLessonInTerm();
    }

    public void chooseWorkDirectory() {

        System.out.println("1.go to teacherPanel\n2.go to studentPanel");

        switch (ApplicationContext.getApplicationContext().getScannerForInteger().nextInt()) {

            case 1 -> teacherPanel.start(this);

            case 2 -> start();

            default -> {
                System.out.println("your input not valid try again ###\n");
                chooseWorkDirectory();
            }
        }
    }

    private void start() {

        System.out.println("1.register");
        System.out.println("2.login");
        System.out.println("3. go to teacherPanel");
        System.out.println("4.exit");

        switch (ApplicationContext.getApplicationContext().getScannerForInteger().nextInt()) {

            case 1 -> studentPanel(studentService.register());


            case 2 -> {
                Optional<Student> optional = studentService.login();
                if (optional.isEmpty()) {
                    System.out.println("dont error occurred maybe forget studentCode or password  ");
                    start();
                }
                studentPanel(optional.get());

            }
            case 3 -> teacherPanel.start(this);

            case 4 -> {
                System.out.println("have nice day !!!");
                ApplicationContext.getApplicationContext().getEntityManager().close();
                HibernateUtil.entityManagerFactory().close();
                System.exit(0);
            }

        }
    }

    private void studentPanel(Student student) {
        System.out.println("1.choose lesson witch present in term\n" +
                "2.showMyLesson\n3.back");

        switch (ApplicationContext.getApplicationContext().getScannerForInteger().nextInt()) {

            case 1 -> {
                studentService.chooseLessons(student);
                studentPanel(student);
            }

            case 2 -> {
                studentService.showGrade(student);
                studentPanel(student);
            }
            case 3 -> start();

            default -> {
                System.out.println("your input not valid try again ###\n");
                studentPanel(student);
            }
        }
    }

}
