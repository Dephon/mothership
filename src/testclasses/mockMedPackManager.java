package testclasses;

import java.util.*;

public class mockMedPackManager {

	public mockMedPackManager(int maxAmount) {

		// gameBounds = bounds; NOT SURE IF I'LL USE THIS
		maxCount = maxAmount;
		entities = new ArrayList<MockMedPack>();
		activeNdxs = new ArrayList<Integer>();
		count = 0;
		ndx = 0;

		for (int i = 0; i < maxAmount; i++) {
			entities.add(new MockMedPack());
		}
	}

	public void add() {
		MockMedPack entity = entities.get(ndx);
		if (count < maxCount) {
			entity.create();
			activeNdxs.add(ndx);
			count++;
			ndx++;
			if (ndx == maxCount)
				ndx = 0;
		}
	}

	public void remove(MockMedPack entity) {
		activeNdxs.remove((Integer) entities.indexOf(entity));
		count--;
	}

	public ArrayList<MockMedPack> getActive() {
		ArrayList<MockMedPack> activeMedPacks = new ArrayList<MockMedPack>();
		for (int i = 0; i < activeNdxs.size(); i++)
			activeMedPacks.add(entities.get(activeNdxs.get(i)));
		return activeMedPacks;
	}

	public void handleCollision(MockMedPack medPack) {
		medPack.handleCollision();
		if (medPack.isDead())
			remove(medPack);
	}

	private int ndx;
	private int count;
	private int maxCount;
	ArrayList<MockMedPack> entities;
	ArrayList<Integer> activeNdxs;
}
