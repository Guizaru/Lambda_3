package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);

        List<Employee> employees = new ArrayList<>();
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

            String line = bf.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                Double salary = Double.valueOf(fields[2]);
                line = bf.readLine();
                employees.add(new Employee(name, email, salary));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();
        System.out.printf("Email of people whose salary is more than %.2f:", salary);
        System.out.println();
        employees.stream().filter(employee -> employee.getSalary() > salary)
                .sorted(Comparator.comparing(Employee::getEmail))
                .forEach(employee -> System.out.println(employee.getEmail()));

        double sum = employees.stream()
                .filter(employee -> employee.getName().charAt(0) == 'M')
                .mapToDouble(Employee::getSalary).sum();

        System.out.printf("Sum of salary of people whose name starts with 'M': %.2f", sum);
    }
}