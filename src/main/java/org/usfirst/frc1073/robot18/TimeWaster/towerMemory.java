package org.usfirst.frc1073.robot18.TimeWaster;

public class towerMemory {

	private int[][] intSet;
	private Disk[][] memory;
	private boolean firstRun;

	private Tower tow;

	public towerMemory(Tower tow) {
		this.tow = tow;
	}

	public void run() {
		if (firstRun == false) {
			setup();
		}
		else {
			helper();
		}
		print();
	}

	/** First Run */
	private void setup() {
		intSet = tow.getState();
		memory = new Disk[3][towerRenderer.meme.disks];

		for (int n = 0; n < memory.length; n++) {
			for (int m = 0; m < memory[n].length; m++) {
				memory[n][m] = new Disk(0);
			}
		}
		
		for (int n = 0; n < memory.length; n++) {
			for (int m = 0; m < memory[n].length; m++) {
				memory[n][m].setSize(intSet[n+1][m]);
			}
		}
		firstRun = true;
	}

	/** Every run after first run */
	private void helper() {
		for (int n = 0; n < memory.length; n++) {
			for (int m = 0; m < memory[n].length; m++) {
				memory[n][m].setSize(intSet[n+1][m]);
			}
		}
	}

	/** Prints array */
	private void print() {
		boolean done = false;
		String pole1 = " ", pole2 = " ", pole3 = " ";
		int m = towerRenderer.meme.disks - 1;
		while (done == false) {
			System.out.println(memory[0][m].runner() + " | " + memory[1][m].runner() + " | " + memory[2][m].runner());
			m--;
			if (m < 0) {
				done = true;
			}
		}
	}
}