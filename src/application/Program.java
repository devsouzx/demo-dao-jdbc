package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
  public static void main(String[] args) {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("test 1 - seller find by id");
    Seller seller = sellerDao.findById(1);

    System.out.println("test 2 - sellers find by department");
    Department department = new Department(2, null);
    List<Seller> sellers = sellerDao.findByDepartment(department);
    for (Seller seller1 : sellers) {
      System.out.println(seller1);
    }

    System.out.println(seller);
  }
}
