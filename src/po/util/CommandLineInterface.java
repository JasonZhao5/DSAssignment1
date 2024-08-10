package po.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 */
public class CommandLineInterface {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Set up a scanner and invoke the selected method.
     *
     * @param invoker the object the underlying method is invoked from
     * @param clazz   the class of function object
     */
    public void setScanner(Object invoker, Class<?> clazz) {

        Method[] methods = clazz.getMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        // generate the method options
        while (true) {
            System.out.println("Please select an option and enter the corresponding number to run it:");
            for (int i = 0; i < methods.length; i++) {
                System.out.println(i + 1 + ". " + methods[i].getName());
            }
            System.out.println(methods.length + 1 + ". exit");

            int choice = methods.length + 1;
            if (scanner.hasNextInt()) {
                int nextInt = scanner.nextInt();
                if (nextInt >= 1 && nextInt <= methods.length + 1)
                    choice = nextInt - 1;
            } else {
                throw new RuntimeException("Invalid input.");
            }
            if (choice == methods.length + 1) {
                break;
            }

            // invoke the chosen method
            try {
                Method method = methods[choice];
                if (method.getParameterCount() != 0) {
                    System.out.println("Pleas enter the parameter:");
                    Class<?> parameterType = method.getParameterTypes()[0];
                    if (parameterType.getName().equals("int")) {
                        System.out.println(method.invoke(invoker, scanner.nextInt()));
                    } else if (parameterType.equals(String.class)) {
                        System.out.println(method.invoke(invoker, scanner.next()));
                    }
                } else {
                    System.out.println(method.invoke(invoker));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Input any key to continue.");
            if (scanner.hasNext()) {
                scanner.next();
                continue;
            }

        }

        scanner.close();
    }

}
