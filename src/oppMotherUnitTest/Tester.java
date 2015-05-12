package oppMotherUnitTest;

import static org.junit.Assert.*;
import game2D.*;
import game2D.abstracts.*;
import game2D.projectiles.*;

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
		// represents player with full health.
		int playerHealth = 100;

		// five medpacks in map
		mockMedPackManager medPacks = new mockMedPackManager(5);
		for (int i = 0; i < 5; i++)
			medPacks.add();

		for (int i = 0; i < 5; i++) {
			// Player takes damage
			playerHealth -= (i * 10);
			if (playerHealth < 100) {
				medPacks.handleCollision(medPacks.getActive().get(0));
				playerHealth += 20;
			}
			if (playerHealth > 100)
				playerHealth = 100;
		}

		// Should only be one medpack left after test.
		assertEquals(1, medPacks.getActive().size());

		// Player should have 70 hp after test.
		assertEquals(playerHealth, 70);

		totalScore++;

	}

	@Test
	public void testBulletManager() {
		int tempScore = 0;
		Double happy = 100.0; // Number of bullets
		Double sad = 1.0; // Bullet type
		try {
			mockMapOne testLevel = new mockMapOne(happy, sad);
			BulletManager testManager = testLevel.getBullets();
			assertEquals(testManager.getActive().size(), 0);
			for (int i = 0; i < happy.intValue(); i++) {
				testManager.testCreate();
			}
			assertEquals(testManager.getActive().size(), happy.intValue());
			for (int i = 10; i < 20; i++) {
				testManager.testRemove(i);
			}
			assertEquals(testManager.getActive().size(), happy.intValue() - 10);
		} catch (SlickException e) {
			fail("Slick Exception");
			e.printStackTrace();
		}
		totalScore++;
	}

	@Test
	public void testEnemyManager() { // White-box
		Double happy = 100.0; // Number of Enemies
		Double sad = 100.0; // Health
		try {
			mockMapOne testLevel = new mockMapOne(happy, sad);
			EnemyManager testManager = testLevel.getEnemies();
			assertEquals(testManager.getActive().size(), 0);
			for (int i = 0; i < happy.intValue(); i++) {
				testManager.testAdd();
			}
			assertEquals(testManager.getActive().size(), happy.intValue());
			ArrayList<Entity> tempor = testManager.getActive();
			for (int i = 10; i < 20; i++) {
				testManager.remove(tempor.get(i));
			}
			assertEquals(testManager.getActive().size(), happy.intValue() - 10);
		} catch (SlickException e) {
			fail("Slick Exception");
			e.printStackTrace();
		}
		totalScore++;
	}

	@Test
	public void testPlayer() { // White-box
		Double happy = 100.0;
		try {
			Player toTest = new Player(happy);
			assertEquals(toTest.isDamaged(), false);
			toTest.addHealth(20);
			assertEquals(toTest.getHealth(), toTest.getMaxHealth());
			toTest.takeDamage(50);
			assertEquals(toTest.getHealth(), toTest.getMaxHealth() - 50);
			toTest.takeDamage(50);
			assertEquals(toTest.isDead(), true);
		} catch (SlickException e) {
			fail("SlickException");
			e.printStackTrace();
		}

		totalScore++;
	}

	@Test
	public void testLossCondition() { // Black-box
		int tempScore = 0;
		Double happy = 1.0; // Number of Players
		Double sad = 100.0; // Health
		try {
			mockMapOne testLevel = new mockMapOne(happy, sad);
			PlayerManager testManager = testLevel.getPlayers();
			assertEquals(testManager.getHealth(happy.intValue()),
					sad.intValue());
			testManager.get(happy.intValue()).takeDamage(100);
			assertEquals(testManager.areDead(), true);
			assertEquals(testLevel.GameOver(), true);
		} catch (SlickException e) {
			fail("Slick Exception");
			e.printStackTrace();
		}
		totalScore++;
	}

	@Test
	public void testUI() { // Black-box
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
		totalScore++;

	}

	// Crash if no asset (picture missing)

}
