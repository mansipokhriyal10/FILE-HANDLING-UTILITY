import java.io.*;
import java.util.*;

public class FileHandler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        String fileName = "File.txt";

        while (true) {
            System.out.println("1. Write");
            System.out.println("2. Read");
            System.out.println("3. Modify");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();
            sc.nextLine(); // consume newline

            if (ch == 1) {
                try (FileWriter fw = new FileWriter(fileName)) {
                    System.out.println("Enter text:");
                    String text = sc.nextLine();
                    fw.write(text);
                    System.out.println("Written.");
                } catch (IOException e) {
                    System.out.println("Error writing: " + e.getMessage());
                }
            } else if (ch == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading: " + e.getMessage());
                }
            } else if (ch == 3) {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    ArrayList<String> lines = new ArrayList<>();
                    String line;
                    while ((line = br.readLine()) != null) {
                        lines.add(line);
                    }

                    for (int i = 0; i < lines.size(); i++) {
                        System.out.println((i + 1) + ": " + lines.get(i));
                    }

                    System.out.print("Line to change: ");
                    int num = sc.nextInt();
                    sc.nextLine(); // consume newline

                    if (num > 0 && num <= lines.size()) {
                        System.out.print("New text: ");
                        String newtext = sc.nextLine();
                        lines.set(num - 1, newtext);

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                            for (String l : lines) {
                                bw.write(l);
                                bw.newLine();
                            }
                            System.out.println("Modified.");
                        }
                    } else {
                        System.out.println("Invalid line number.");
                    }
                } catch (IOException e) {
                    System.out.println("Error modifying: " + e.getMessage());
                }
            } else if (ch == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Wrong choice.");
            }
        }

        sc.close();
    }
}
