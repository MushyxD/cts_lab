package ro.ase.cts.seminar13.UnitTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import ro.ase.cts.seminar13.Student;
import ro.ase.cts.seminar13.exceptions.StudentExceptionWrongValue;

class StudentTestCase {

	/* test fixtures */
	Student student;
	static String DEFAUlT_NAME = "Anonim";
	static int DEFAULT_VARSTA = 18;
	static int DEFAULT_NOTE[] = {};
	static int NR_NOTE_DEFAULT=3;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DEFAULT_NOTE=new int[NR_NOTE_DEFAULT];
		DEFAULT_NOTE[0]=9;
		DEFAULT_NOTE[1]=9;
		DEFAULT_NOTE[2]=10;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearDownAfterClass was called");
	}

	@BeforeEach
	void setUp() throws Exception {
		student = new Student(DEFAUlT_NAME, DEFAULT_VARSTA, DEFAULT_NOTE);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("tearDown was called");
	}

	@Test
	void testStudentConstructorRight() {
		String studentName = "Alex";
		int studentAge = 27;
		int grades[] = { 8, 8, 9 };

		Student student = new Student(studentName, studentAge, grades);

		assertEquals(studentName, student.getNume(), "Name is not equal");
		assertEquals(studentAge, student.getVarsta(), "Age is not equal");
		assertEquals(grades, student.getNote(), "Grades is not equal");
	}

	@Test
	void testStudentConstructorRightNotNull() {
		int grades[] = { 8, 8, 9 };
		Student student = null;

		try {
			student = new Student("Laura", 21, grades);
		} catch (Exception e) {
			fail("Constructorul nu trebuie sa arunce exceptie");
		}
		assertNotNull(student);
	}

	/*
	 * error condition
	 */

	@Test
	void testStudentSetVarstaErrorCondition() {
		assertThrows(StudentExceptionWrongValue.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				student.setVarsta(-1);
			}

		});
	}

	@Test
	void testStudentGetVarstaRight() {
		int expectedValue = DEFAULT_VARSTA;
		int actualValue = student.getVarsta();

		assertEquals(expectedValue, actualValue);
	}

	@Test
	void testCalculMedieErrorCondition() {
		try {
			student.setNote(null);
		} catch (StudentExceptionWrongValue e) {
			e.printStackTrace();
		}
		assertThrows(StudentExceptionWrongValue.class,()->{
			student.calculMedie();	
			});
	}
	
	@Test
	void testCalculMedieErrorRightDouaZecimale() throws StudentExceptionWrongValue {
		float actualValue=student.calculMedie();
		float expectedValue=9.33f;
		assertEquals(expectedValue, actualValue);
	}
	
	//boundary check
		@Test
		void testSetNoteBoundaryCondition() {
			int invalidDataSetNote[]=new int[3];
			for(int i=0;i<2;i++) {
				invalidDataSetNote[i]=8;
			}
			invalidDataSetNote[2]=20;
			assertThrows(StudentExceptionWrongValue.class,()->{
				student.setNote(invalidDataSetNote);
			});
		}
	
}
