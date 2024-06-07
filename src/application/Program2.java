package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    System.out.println("=== test 1 - departments find by id ===");
    Department department = departmentDao.findById(2);
    System.out.println(department);

    System.out.println("\n=== test 2 - find all departments ===");
    List<Department> departments = departmentDao.findAll();
    for (Department d : departments) {
      System.out.println(d);
    }

    System.out.println("\n=== test 2 - insert department ===");
    department = new Department(null, "Cars");
    departmentDao.insert(department);
    System.out.println("Inserted! New id = " + department.getId());

    System.out.println("\n=== test 2 - update department ===");
    department = departmentDao.findById(1);
    department.setName("Novels");
    departmentDao.update(department);
    System.out.println("Update completed");

    System.out.println("\n=== test 2 - delete department ===");
    System.out.print("enter id for delete test: ");
    int id = sc.nextInt();
    departmentDao.deleteById(id);
    System.out.println("Deleted");
    sc.close();
  }
}
