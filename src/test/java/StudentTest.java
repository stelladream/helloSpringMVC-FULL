import kr.ac.hansung.cse.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/dao-context.xml")

public class StudentTest {

    @PersistenceContext
    private EntityManager testEntityManager;

    @Test
    public void testStudentLifecycle() {
        // Transient 상태의 객체 생성
        Student student = new Student();
        student.setFirstName("alice");
        student.setLastName("Kim");
        student.setEmail("alice.Kim@hansung.ac.kr");

        // Persistent 상태로 전환
        // 영속 상태로 전환
        testEntityManager.persist(student);

        // ID를 사용하여 엔티티 조회
        Student persistedStudent = testEntityManager.find(Student.class, student.getId());
        assertNotNull(persistedStudent, "Persisted student should not be null");
        assertEquals("alice", persistedStudent.getFirstName(), "First name should be 'alice'");

        // 변경 감지 (Dirty checking)을 통해 엔티티 업데이트
        persistedStudent.setEmail("updated.email@hansung.ac.kr");
        testEntityManager.flush(); // 변경사항을 데이터베이스에 즉시 반영

        // 업데이트 확인
        Student updatedStudent = testEntityManager.find(Student.class, persistedStudent.getId());
        assertEquals("updated.email@hansung.ac.kr", updatedStudent.getEmail(), "Email should be updated");

        // 엔티티 분리 (Detached 상태로 전환)
        testEntityManager.detach(updatedStudent);
        updatedStudent.setEmail("another.update@hansung.ac.kr");

        // 분리된 엔티티 상태에서 변경을 시도해도 데이터베이스에 반영되지 않음을 확인
        Student detachedStudent = testEntityManager.find(Student.class, updatedStudent.getId());

        assertEquals("updated.email@hansung.ac.kr", detachedStudent.getEmail(),
                "Email should not be updated after detachment");
    }
}
