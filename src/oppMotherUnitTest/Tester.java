package oppMotherUnitTest;

import static org.junit.Assert.*;
import game2D.*;
import game2D.states.levels.*;

import java.util.*;

import org.junit.*;
import org.newdawn.slick.*;

import testclasses.*;

public class Tester {

	public static int totalScore = 0;

	@BeforeClass
	public static void beforeTesting() {
		totalScore = 0;

	}

	@AfterClass
	public static void afterTesting() {
		System.out.println("Total tests passed out of 6 : " + totalScore);
	}

	@Test
	public void testMedPack() {

	}

	@Test
	public void testEnemyManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayer() { // White-box
		int tempScore = 0;
		MapOne testLevel = new MapOne();
		Double happy = 100.0;
		try {
			Player toTest = new Player(happy);
			if (!toTest.isDamaged()) {
				tempScore++;
			}
			toTest.addHealth(20);
			if (toTest.getHealth() == toTest.getMaxHealth()) {
				tempScore++;
			}
			toTest.takeDamage(50);
			if (toTest.getHealth() == toTest.getMaxHealth() - 50) {
				tempScore++;
			}
			toTest.takeDamage(50);
			if (toTest.isDead()) {
				tempScore++;
			}
		} catch (SlickException e) {
			fail("SlickException");
			e.printStackTrace();
		}

		if (tempScore == 4) {
			totalScore++;
		} else {
			fail("Missed at least one point");
		}
	}

	@Test
	public void testLossCondition() {
		fail("Not yet implemented");
	}

	@Test
	public void testUI() {
		mockUI ui = new mockUI();
		Random rand = new Random();
		// Test Health Counter
		for (int i = 0; i < 20; i++) {
			int damage = rand.nextInt(100);
			int wep = rand.nextInt(2);
			ui.update(damage, wep);

			assertEquals(ui.getDigit(3).id, ui.getNumbers()[(damage) % 10].id);
			assertEquals(ui.getDigit(2).id, ui.getNumbers()[(damage) / 10].id);
			assertEquals(ui.getDigit(1).id, ui.getNumbers()[(damage) / 100].id);

			assertEquals(ui.getWep(), wep);

			ui.update(100, wep);
		}

	}

	// Crash if no asset (picture missing)

}
