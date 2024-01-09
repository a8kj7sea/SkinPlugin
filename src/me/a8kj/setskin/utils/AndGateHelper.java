package me.a8kj.setskin.utils;

import java.util.ArrayList;
import java.util.List;

public class AndGateHelper {

	private List<Integer> numbers;

	// append
	// toBoolean

	public AndGateHelper() {
		numbers = new ArrayList<>();
	}

	public AndGateHelper append(int number) {
		add(number);
		return this;
	}

	public AndGateHelper append(boolean condition) {
		if (!condition) {
			add(0);
		} else {
			add(1);
		}
		return this;
	}

	public boolean toBoolean() {
		int numbersListSize = numbers.size();
		int index = 0;
		for (int number : numbers) {
			if (number == 1) {
				index++;
			}
		}

		return (index == numbersListSize);
	}

	private void add(int number) {
		if (number > 0 || number < 0) {
			numbers.add(1);
		} else {
			numbers.add(0);
		}
	}
}