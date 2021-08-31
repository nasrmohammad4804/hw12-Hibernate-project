package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.*;
import domain.embeddable.Average;
import reository.impl.StudentRepositoryImpl;
import service.StudentService;
import service.TeacherLessonService;
import util.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepositoryImpl>
        implements StudentService {

    private TeacherLessonService teacherLessonService;

    private GradeServiceImpl gradeService;

    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }

    public void initialize() {

        teacherLessonService = ApplicationContext.getApplicationContext().getTeacherLessonService();
        gradeService = ApplicationContext.getApplicationContext().getGradeService();
    }

    @Override
    public Student register() {

        Long studentCode = ApplicationContext.getApplicationContext().getStudentCode();


        System.out.println("student code :" + studentCode);

        System.out.println("enter firstName");
        String firstName = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        System.out.println("enter lastName");
        String lastName = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        System.out.println("enter password");
        String password = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        System.out.println("enter your birthDay with format xxxx-yy-zz");
        LocalDate birthDay = LocalDate.parse(ApplicationContext.getApplicationContext().getScannerForString().nextLine());

        Student student = new Student(firstName, lastName, password, birthDay, studentCode);
        save(student); //go to baseServiceImpl

        return student;
    }

    @Override
    public Optional<Student> login() {

        Optional<Student> optionalStudent = Optional.empty();

        System.out.println("enter yourStudentCode");
        Long studentCode = ApplicationContext.getApplicationContext().getScannerForInteger().nextLong();

        Optional<Student> optional = repository.findByStudentCode(studentCode);

        Predicate<Optional<Student>> predicate1 = Optional::isPresent;

        System.out.println("enter your password");
        String password = ApplicationContext.getApplicationContext().getScannerForString().nextLine();

        Predicate<String> predicate2 = (x) -> x.equals(optional.get().getPassword());

        if (predicate1.test(optional) && predicate2.test(password)) {

            return Optional.of(optional.get());

        }
        return optionalStudent;
    }

    @Override
    public void showGrade(Student student) {
        try {
            repository.showGrade(student);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" student until dont have any lesson choose !!!\n");
        }
    }

    @Override
    public void chooseLessons(Student student) {

        Grade grade = gradeService.getGradeOfStudent(student);
        if (grade == null) {
            grade = new Grade();
            grade.setStudent(student);
        }

        System.out.println("enter id  witch one of lesson presented");

        System.out.println("*".repeat(80));
        teacherLessonService.findAll().forEach(x -> System.out.println("id :" + x.getId() + "   " + x.getLesson() + "\t" +
                "capacity :" + x.getCapacity() + "\t\t" +
                x.getTeacher().printInformationWhenStudentSelectUnit()));
        System.out.println("*".repeat(80));

        Long id = ApplicationContext.getApplicationContext().getScannerForInteger().nextLong();

        Optional<TeacherLesson> optional = teacherLessonService.findById(id);
        if (optional.isEmpty()) {
            System.out.println("dont have result with this id");
            return;
        }

        if (grade.getResults().stream().anyMatch(x -> x.getTeacherLesson().
                getLesson().equals(optional.get().getLesson()))) {
            System.out.println("this student already this lesson and dont permit one lesson more than choose\n");
            return;
        }

        if (optional.get().getCapacity() == 0) {
            System.out.println("this lesson full by this master and dont allow any student choose");
            return;
        }
        grade.getResults().add(new Average(grade, optional.get()));

        int capacity = optional.get().getCapacity();
        optional.get().setCapacity(--capacity);

        entityManager.getTransaction().begin();

        try {
            entityManager.persist(grade);
            entityManager.persist(optional.get());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }


    @Override
    public void showAllStudentOfTeacher(List<Student> students) {

        System.out.println("------------------------------------------------");
        students.stream().map(x -> x.getId() + "   " + x.getFirsName() + "  " + x.getLastName() + "  " + x.getStudentCode())
                .forEach(System.out::println);
        System.out.println("------------------------------------------------");
    }

    @Override
    public Long countAll() {

        return repository.countAll();
    }

    public Average getAverage(Student student, Teacher teacher, Lesson lesson) {

        try {

            return repository.getAverage(student, teacher, lesson);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
