import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Person;
import com.revature.exceptions.PersonNotFoundException;
import com.revature.hibernate.PersonHibernate;

public class PersonHibernateTest {
	public static PersonHibernate personDao;
	public static Person testPerson;
	
	@Test
	public static void setup() {
		personDao = new PersonHibernate();
		testPerson = new Person();
	}
	@Test
	public void getByIdNotNull() throws PersonNotFoundException {
		Person p = null;
		testPerson = personDao.getByUsername("aivery1");
		System.out.println(testPerson);
		assertNotNull(testPerson);
	}

}
