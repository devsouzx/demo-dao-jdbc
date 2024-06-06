package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.implementation.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
  public static void main(String[] args) {
    Department department = new Department(1, "books");
    Seller seller = new Seller(12, "joao", "joao@gmail.com", new Date(), 3000.0, department);
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println(seller);
  }
}
