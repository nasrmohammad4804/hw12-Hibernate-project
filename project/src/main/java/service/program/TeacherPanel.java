package service.program;

import domain.Teacher;
import service.impl.TeacherServiceImpl;
import util.ApplicationContext;

import java.util.Optional;

public class TeacherPanel {

    private final TeacherServiceImpl teacherService;


    private App app;
    {
        teacherService = ApplicationContext.
                getApplicationContext().getTeacherService();

        if (teacherService.findAll().size() == 0)
            teacherService.addTeacherAlreadyExists();

    }

    public void start(App app) {

        this.app=app;

        System.out.println("1.login\n2.exit"); //exit - mean back to user

        switch (ApplicationContext.getApplicationContext().getScannerForInteger().nextInt()) {

            case 1 -> {

                Optional<Teacher> optional = teacherService.login();
                if (optional.isEmpty()) {
                    System.out.println("dont error occurred maybe forget teacherCode or password");
                    start(app);
                }
                teacherPanel(optional.get());
            }
            case 2 -> app.chooseWorkDirectory();

            default -> {
                System.out.println("your input not valid try again ###\n");
                start(app);
            }
        }
    }

    private void teacherPanel(Teacher teacher) {

        System.out.println("1.dear teacher select witch on lesson your are presentation");
        System.out.println("2. enter grade for student");
        System.out.println("3.back");

        switch (ApplicationContext.getApplicationContext().getScannerForInteger().nextInt()) {

            case 1 -> {
                teacherService.presentOfLesson(teacher);
                teacherPanel(teacher);
            }

            case 2 -> {
                teacherService.confirmStudentGrade(teacher);
                teacherPanel(teacher);
            }
            case 3 -> start(app);

            default -> {
                System.out.println("your input not valid try again ###\n");
                teacherPanel(teacher);
            }

        }

    }
}
