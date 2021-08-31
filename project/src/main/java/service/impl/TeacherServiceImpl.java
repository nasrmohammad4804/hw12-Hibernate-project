package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.*;
import domain.embeddable.Average;
import domain.embeddable.TeacherLessonId;
import domain.enumaration.StudentState;
import reository.impl.TeacherRepositoryImpl;
import service.TeacherService;
import util.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long, TeacherRepositoryImpl>
        implements TeacherService {


    private StudentServiceImpl studentService;

    private LessonServiceImpl lessonService;

    private GradeServiceImpl gradeService;

    public TeacherServiceImpl(TeacherRepositoryImpl repository) {
        super(repository);
    }

    public void initialize() {
        studentService = ApplicationContext.getApplicationContext().getStudentService();
        lessonService = ApplicationContext.getApplicationContext().getLessonService();
        gradeService = ApplicationContext.getApplicationContext().getGradeService();
    }

    @Override
    public Optional<Teacher> login() {

        long teacherCode = 0;
        String password = "";
        try {

            System.out.println("enter teacher code !!");
            teacherCode = ApplicationContext.getApplicationContext().getScannerForInteger().nextLong();

            System.out.println("enter your password");
            password = ApplicationContext.getApplicationContext().getScannerForString().nextLine();
        } catch (Exception e) {
            System.out.println("input not valid try again");
        }
        return repository.findByTeacherCode(teacherCode, password);


    }


    @Override
    public void confirmStudentGrade(Teacher teacher) {

        System.out.println("enter your lesson Name witch teaching in term ..\n");
        String lessonName = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        Optional<Lesson> optional = lessonService.findAll().stream().filter
                (x -> x.getName().equals(lessonName)).findFirst();

        if (optional.isEmpty()) {
            System.out.println("this lesson for teacher not exists ");
            return;
        }

        List<Student> students = repository.getAllStudents(teacher, optional.get());

        if (students.isEmpty()) {
            System.out.println("dont have any student in lesson \n");
            return;
        }

        studentService.showAllStudentOfTeacher(students);
        System.out.println("enter witch one student from all your students");

        Optional<Student> student = studentService.findById((long) ApplicationContext
                .getApplicationContext().getScannerForInteger().nextInt());

        if (student.isEmpty()) {
            System.out.println("the id of student not exits\n");
            return;
        }
        Grade grade = gradeService.getGradeOfStudent(student.get());
        Average average = studentService.getAverage(student.get(), teacher, optional.get());

        System.out.println("enter score for this student");

        int score = ApplicationContext.getApplicationContext().getScannerForInteger().nextInt();

        for (int i = 0; i < grade.getResults().size(); i++)
            if (grade.getResults().get(i).equals(average))
                grade.getResults().set(i, average);

        average.setScore(score);

        average.setState(score >= 10 ? StudentState.PASSED : StudentState.FAIL);

        gradeService.update(grade);

    }

    @Override
    public void addTeacherAlreadyExists() {

        entityManager.getTransaction().begin();
        Teacher teacher1 = new Teacher("mohammad ali", "zare", "ali1354",
                LocalDate.of(1354, 2, 15), 128769, 7_000_000);
        teacher1.setAddress(new Address("09025412138", "yazd", "javad21@gmail.com", 652137L));

        Teacher teacher2 = new Teacher("masoud", "zahmatkesh", "mpzadmin",
                LocalDate.of(1367, 5, 28), 4521762L, 10_600_000);
        teacher2.setAddress(new Address("09104213278", "tehran", "mas431@gmail.com", 432139L));

        Teacher teacher3 = new Teacher("majid", "nikzar", "majid1350",
                LocalDate.of(1350, 1, 15), 32176L, 8_500_000);

        teacher3.setAddress(new Address("090132156749", "hamedan", "!majidcs231@gmail.com", 561237L));

        Teacher teacher4 = new Teacher("reza", "safaee", "reza1356",
                LocalDate.of(1356, 11, 23), 7652893L, 6_800_000);

        teacher4.setAddress(new Address("09135467239", "isfahan", "safae432@yahoo.com", 378316L));

        Teacher teacher5 = new Teacher("ali", "gharian", "ali1347",
                LocalDate.of(1347, 10, 7), 678123L, 9_000_000);

        teacher5.setAddress(new Address("09144512785", "yazd", "gharainqw@gmail.com", 764125L));

        Teacher teacher6 = new Teacher("ahmad reza", "eslami", "ahmad1340",
                LocalDate.of(1340, 5, 24), 375419L, 8_200_000);
        teacher6.setAddress(new Address("09903216652", "mashhad", "ahmadeslam1346@gmail.com",
                31452L));
        try {

            entityManager.persist(teacher1);
            entityManager.persist(teacher2);
            entityManager.persist(teacher3);
            entityManager.persist(teacher4);
            entityManager.persist(teacher5);
            entityManager.persist(teacher6);
            entityManager.getTransaction().commit();
            System.out.println("added 10 teacher !!!\n");
        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void presentOfLesson(Teacher teacher) {

        List<Lesson> lessonList = lessonService.findAll();
        lessonList.stream().map(lesson -> "id  :" + lesson.getId() + "    name :"
                + lesson.getName() + "    unit :" + lesson.getUnit())
                .forEach(System.out::println);

        System.out.println("enter one lesson witch your are presentation !!! ");
        String lessonName = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        long result = teacher.getTeacherLessons().stream().
                filter(x -> x.getLesson().getName().equals(lessonName)).count();

        if (result != 0) {

            System.out.println("you have choosed this lesson dont have allow choose this lesson \n");
            return;
        }

        Optional<Lesson> optional = lessonList.stream().filter(x -> x.getName().equals(lessonName)).findAny();

        if (optional.isEmpty()) {
            System.out.println("this lesson Name witch you entered not valid\n");
            return;
        }

        System.out.println("enter your capacity for this lesson in term");
        int capacity = ApplicationContext.getApplicationContext().getScannerForInteger().nextInt();

        TeacherLesson teacherLesson = new TeacherLesson(new TeacherLessonId(teacher.getId(), optional.get().getId()),
                optional.get(), teacher, capacity);

        teacher.getTeacherLessons().add(teacherLesson);
        update(teacher);

    }


}
