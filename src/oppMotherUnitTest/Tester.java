package oppMotherUnitTest;

import static org.junit.Assert.*;

import org.junit.*;

public class Tester {

	public static int totalScore = 0;

	@BeforeClass
	public static void beforeTesting() {
		totalScore = 0;
	}

	@AfterClass
	public static void afterTesting() {
		System.out.println("Total tests passed out of 10 : " + totalScore);
	}

	@Test
	public void testMedPack() {
		fail("Not yet implemented");
	}

	@Test
	public void testEnemyManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testLossCondition() {
		fail("Not yet implemented");
	}

	// Crash if no asset (picture missing)

}
