package game2D.pickups;

import game2D.*;
import game2D.abstracts.*;

import org.newdawn.slick.*;

public class MedPackManager extends Manager {

	public MedPackManager(Porygon bounds, int maxAmount) throws SlickException {
		super(bounds, maxAmount);
		for (int i = 0; i < maxAmount; i++) {
			entities.add(new MedPack());
		}

	}

	// public

}
