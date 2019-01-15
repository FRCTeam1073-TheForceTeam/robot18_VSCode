package org.usfirst.frc1073.robot18.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ArrayMultiplier extends Command {
	
	private int multi;
	
	private int[] arr;
	
	/***
	 * @author Nathaniel
	 * 
	 * Multiplies an array by the multiplier until one or more of the array values is greater than 100
	 * 
	 * @param multiplier (Defaults to 5)
	 * @param array (Defaults to [7, 2, 5])
	 */
	public ArrayMultiplier() {
		this.multi = 5;
	}
	
	
	/***
	 * @author Nathaniel
	 * 
	 * Multiplies an array by the multiplier until one or more of the array values is greater than 100
	 * 
	 * @param multiplier Multiplies the numbers in the array once per loop
	 * @param array (Defaults to [7, 2, 5])
	 */
	public ArrayMultiplier(int multiplier) {
		this.multi = multiplier;
	}
	
	/***
	 * @author Nathaniel
	 * 
	 * Multiplies an array by the multiplier until one or more of the array values is greater than 100
	 * 
	 * @param multiplier (Defaults to 5)
	 * @param array The array to be multiplied
	 */
	public ArrayMultiplier(int[] array) {
		this.arr = array;
	}
	
	/***
	 * @author Nathaniel
	 * 
	 * Multiplies an array by the multiplier until one or more of the array values is greater than 100
	 * 
	 * @param multiplier Multiplies the numbers in the array once per loop
	 * @param array The array to be multiplied
	 */
	public ArrayMultiplier(int multiplier, int[] array) {
		this.multi = multiplier;
		this.arr = array;
	}

	protected void initialize() {
		if (arr.length == 0) {
			arr = new int[]{7, 2, 5};
		}	
	}
	
	protected void execute() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] *= multi;
		}
	}
	
	protected boolean isFinished() {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 100) {
				return true;
			}
		}
		return false;
	}
}
