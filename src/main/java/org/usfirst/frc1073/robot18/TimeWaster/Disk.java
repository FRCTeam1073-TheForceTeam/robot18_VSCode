package org.usfirst.frc1073.robot18.TimeWaster;

public class Disk {

	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Disk(int size) {
		this.size = size;
	}

	public String runner() {
		int run = size;
		return stringOut(run);
	}

	private String stringOut(int size) {
		String out = "      ";

		if (10 > size && size > 0) {
			out = (" -0" + size + "- ");
		}
		else if (size > 0) {
			out = (" -" + size + "- ");
		}
		
		return out;
	}
}
