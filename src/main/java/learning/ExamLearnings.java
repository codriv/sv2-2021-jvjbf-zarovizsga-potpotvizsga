package learning;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExamLearnings {

    private Map<String, Integer> learnings = new HashMap<>();

    public Map<String, Integer> getLearnings() {
        return learnings;
    }

    public void readFromFile(Path path) {
        try (Scanner scanner = new Scanner(path)){
            while (scanner.hasNext()) {
                putLearning(scanner.nextLine());
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file.");
        }
    }

    private void putLearning(String line) {
        String[] parts = line.split(";");
        String name = parts[0];
        double hours = 0;
        for (int i = 1; i < parts.length; i++) {
            hours += Double.parseDouble(parts[i].replace(",", "."));
        }
        learnings.put(name, (int)(hours * 60));
    }

    public double getAverageLearningInMinutes() {
        int numberOfStudents = learnings.size();
        if (learnings.size() == 0) {
            throw new IllegalArgumentException("There are no learning times.");
        }
        int allOfMinutes = learnings.values().stream().mapToInt(i->i).sum();
        return (double)allOfMinutes / numberOfStudents;
    }
}
