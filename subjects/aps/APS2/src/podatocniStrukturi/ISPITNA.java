package podatocniStrukturi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ISPITNA {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<String> stack = new Stack<>();
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            if (line.startsWith("#")) {
                if (!stack.isEmpty()) {
                    String str = stack.peek();
                    char PrStack = str.charAt(str.length() - 1);
                    char PrString = line.charAt(line.length() - 1);
                    if (Character.isDigit(PrStack) && Character.isDigit(PrString)) {
                        int IntStack = Integer.parseInt(String.valueOf(PrStack));
                        int IntString = Integer.parseInt(String.valueOf(PrString));
                        if (IntStack > IntString) {
                            flag = false;
                            break;
                        }
                    } else if (Character.isLetter(PrStack) && Character.isLetter(PrString)) {
                        if (PrStack != PrString) {
                            flag = false;
                            break;
                        }
                    } else if (Character.isDigit(PrStack) && Character.isLetter(PrString)) {
                    } else {
                        flag = false;
                        break;
                    }

                }
                stack.push(line.substring(1));
            } else if (line.endsWith("#")) {
                if (!stack.isEmpty()) {
                    if (stack.peek().equals(line.substring(0, line.length() - 1))) {
                        stack.pop();
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
        }

        if (!stack.isEmpty() || !flag) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }

    }
}
