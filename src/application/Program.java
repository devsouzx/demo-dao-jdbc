package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {
  public static void main(String[] args) {
    SellerDao sellerDao = DaoFactory.createSellerDao();

    System.out.println("test 1 - seller find by id");
    Seller seller = sellerDao.findById(1);

    System.out.println(seller);
  }
}
