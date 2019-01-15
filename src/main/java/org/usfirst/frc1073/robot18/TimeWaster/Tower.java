package org.usfirst.frc1073.robot18.TimeWaster;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Tower {

	public int disks;
	private int step;
	private int[] pole1, pole2, pole3;
	private int[][] status;

	public Tower(int disks) {
		this.disks = disks;
	}

	/** Solves tower based on input */
	public void solve() {
		int temp = disks;
		pole1 = new int[25];
		pole2 = new int[25];
		pole3 = new int[25];

		/* Sets up first pole */
		for (int n = 0; n < pole1.length; n++) {
			pole1[n] = temp;
			temp--;
		}

		/* Checks for disk amount validity */
		if (isValid(disks) == true) {
			System.out.println("Step " + step + ": Starting point");
			SmartDashboard.putNumber("Tower Step", step);
			System.out.println(size(pole1) + " " + size(pole2) + " " + size(pole3));
			towerRenderer.boi.run();
			moveTower(disks, "1", "3", "2");
		}
		else {
			System.out.println("Tower count invalid! Must be within range 1-25 disks.");
			System.out.println("You gave " + disks + ".");
		}
	}

	/** @return boolean if valid input */
	private boolean isValid(int disks) {
		boolean valid = false;
		if (1 <= disks && disks <= 24) {
			valid = true;
		}
		return valid;
	}

	private void moveTower(int numDisks, String string, String string2, String string3) {
		/*
		 * Makes a usable integer array of arrays to later be
		 * used as a disk array
		 */
		status = getState();

		if (numDisks == 1) {
			moveOneDisk(string, string2);
		}
		else {
			moveTower(numDisks - 1, string, string3, string2);
			moveOneDisk(string, string2);
			moveTower(numDisks - 1, string3, string2, string);
		}
	}

	private void moveOneDisk(String string, String string2) {
		step++;

		System.out.println("Step " + step + ": Move one disk from " + string + " to " + string2);

		/* Swaps around disks */
		status[poleNum(string2)][topDisk(string2) + 1] = status[poleNum(string)][topDisk(string)];
		status[poleNum(string)][topDisk(string)] = 0;

		System.out.println(size(pole1) + " " + size(pole2) + " " + size(pole3));
		if (string.equals("1") && string2.equals("2")) {
			System.out.println(" >>>>>                  ");
		}
		else if (string.equals("1") && string2.equals("3")) {
			System.out.println(" >>>>>   >>>>>>         ");
		}
		else if (string.equals("2") && string2.equals("1")) {
			System.out.println("         <<<<<<         ");
		}
		else if (string.equals("2") && string2.equals("3")) {
			System.out.println("         >>>>>>         ");
		}
		else if (string.equals("3") && string2.equals("1")) {
			System.out.println("         <<<<<<   <<<<< ");
		}
		else if (string.equals("3") && string2.equals("2")) {
			System.out.println("                  <<<<< ");
		}
		towerRenderer.boi.run();
	}

	/** Uses a string to hold onto pole data */
	private int poleNum(String string) {
		int poleNum = 0;

		if (string.equals("1")) {
			poleNum = 1;
		}
		else if (string.equals("2")) {
			poleNum = 2;
		}
		else if (string.equals("3")) {
			poleNum = 3;
		}

		return poleNum;
	}

	/** @return topDisk array value of the top disk  */
	private int topDisk(String poleStr) {
		int topDisk = disks;
		boolean firstPass = true;

		if (poleStr.equals("1")) {
			for (int n = 0; n < pole1.length; n++) {
				if (pole1[n] == 0 && firstPass == true) {
					topDisk = n - 1;
					firstPass = false;
				}
			}
		}
		else if (poleStr.equals("2")) {
			for (int n = 0; n < pole2.length; n++) {
				if (pole2[n] == 0 && firstPass == true) {
					topDisk = n - 1;
					firstPass = false;
				}
			}
		}
		else if (poleStr.equals("3")) {
			for (int n = 0; n < pole3.length; n++) {
				if (pole3[n] == 0 && firstPass == true) {
					topDisk = n - 1;
					firstPass = false;
				}
			}
		}

		return topDisk;
	}

	/** @return size number of values above 0 in the "pole" */
	private int size(int[] pole) {
		int size = 0;
		for (int n = 0; n < disks; n++) {
			if (pole[n] != 0) {
				size++;
			}
		}
		return size;
	}

	/** @return array equal to the three pegs */
	public int[][] getState() {
		int[][] state = new int[4][disks];
		state[1] = pole1;
		state[2] = pole2;
		state[3] = pole3;
		return state;
	}
}
