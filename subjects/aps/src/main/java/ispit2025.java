import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Object> stack = new Stack<>();
        int bodyCount = 0;
        boolean isValid = true;
        Pattern openingHeading = Pattern.compile("^#Heading([1-6])$");
        Pattern closingHeading = Pattern.compile("^Heading([1-6])#$");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.equals("#Body")) {
                stack.push("Body");
                bodyCount++;
            } else if (line.equals("Body#")) {
                if (stack.isEmpty() || !stack.peek().equals("Body")) {
                    isValid = false;
                    break;
                }
                stack.pop();
                bodyCount--;
            } else {
                Matcher openMatcher = openingHeading.matcher(line);
                if (openMatcher.matches()) {
                    int X = Integer.parseInt(openMatcher.group(1));
                    if (bodyCount > 0) {
                        isValid = false;
                        break;
                    }
                    if (!stack.isEmpty()) {
                        Object top = stack.peek();
                        if (top instanceof Integer) {
                            int prevLevel = (Integer) top;
                            if (X <= prevLevel) {
                                isValid = false;
                                break;
                            }
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    stack.push(X);
                } else {
                    Matcher closeMatcher = closingHeading.matcher(line);
                    if (closeMatcher.matches()) {
                        int X = Integer.parseInt(closeMatcher.group(1));
                        if (bodyCount > 0) {
                            isValid = false;
                            break;
                        }
                        if (stack.isEmpty() || !stack.peek().equals(X)) {
                            isValid = false;
                            break;
                        }
                        stack.pop();
                    }
                }
            }

            if (!isValid) {
                break;
            }
        }

        if (!stack.isEmpty()) {
            isValid = false;
        }

        System.out.println(isValid ? "true" : "false");
    }
}
