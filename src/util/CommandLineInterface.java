package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Remote;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        while (true) {
            System.out.println("Please select an option and enter the corresponding number to run it:");
            for (int i = 1; i < methods.length; i++) {
                System.out.println(i + ". " + methods[i].getName());
            }
            System.out.println(methods.length + 1 + ". exit");

            int choice = scanner.nextInt();
            if (choice == methods.length + 1) {
                break;
            }

            //invoke the method
            try {
                if (methods[choice].getParameterCount() != 0) {
                    System.out.println("Pleas enter the parameter:");
                    System.out.println(methods[choice].invoke(invoker, scanner.nextInt()));
                } else {
                    System.out.println(methods[choice].invoke(invoker));
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        scanner.close();
    }

}
