package numbers;

import java.util.List;

public class RisingNumbers {

    public int getNumberOfSixDigitRisingNumbers(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sixDigitNumbers = numbers.stream().filter(number -> String.valueOf(number).split("").length == 6).toList();
        int numberOfSixDigitRisingNumbers = 0;
        Integer[] digits = new Integer[6];
        for (Integer number: sixDigitNumbers) {
            fillDigits(digits, number);
            if (isRisingNumber(digits, number)) {
                numberOfSixDigitRisingNumbers++;
            }
        }
        return numberOfSixDigitRisingNumbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("There are no numbers!");
        }
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("There are no numbers!");
        }
    }

    private boolean isRisingNumber(Integer[] digits, Integer number) {
        for (int i = 1; i < 5; i++) {
            if (digits[i - 1] >= digits[i] || digits[i] >= digits[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void fillDigits(Integer[] digits, Integer number) {
        String[] stringNumbers = String.valueOf(number).split("");
        for (int i = 0; i < 6; i++) {
            digits[i] = Integer.parseInt(stringNumbers[i]);
        }
    }
}
