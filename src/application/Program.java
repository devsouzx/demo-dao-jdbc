package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("=== test 1 - seller find by id ===");
    Seller seller = sellerDao.findById(1);
    System.out.println(seller);

    System.out.println("\n=== test 2 - sellers find by department ===");
    Department department = new Department(2, null);
    List<Seller> sellers = sellerDao.findByDepartment(department);
    for (Seller seller1 : sellers) {
      System.out.println(seller1);
    }

    System.out.println("\n=== test 2 - find all sellers ===");
    List<Seller> sellerList = sellerDao.findAll();
    for (Seller s : sellerList) {
      System.out.println(s);
    }

    System.out.println("\n=== test 2 - insert seller ===");
    seller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
    sellerDao.insert(seller);
    System.out.println("Inserted! New id = " + seller.getId());

    System.out.println("\n=== test 2 - update seller ===");
    seller = sellerDao.findById(1);
    seller.setName("Martha Waine");
    sellerDao.update(seller);
    System.out.println("Update completed");

    System.out.println("\n=== test 2 - delete seller ===");
    System.out.print("enter id for delete test: ");
    int id = sc.nextInt();
    sellerDao.deleteById(id);
    System.out.println("Deleted");
    sc.close();
  }
}
