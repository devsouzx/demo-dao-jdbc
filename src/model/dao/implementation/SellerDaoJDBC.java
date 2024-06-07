package model.dao.implementation;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
  private Connection connection;

  public SellerDaoJDBC(Connection connection) {
    this.connection = connection;
  }

  @Override
  public void insert(Seller seller) {

  }

  @Override
  public void update(Seller seller) {

  }

  @Override
  public void deleteById(Integer id) {

  }

  @Override
  public Seller findById(Integer id) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
              "SELECT seller.*,department.Name as DepName "
                      + "FROM seller INNER JOIN department "
                      + "ON seller.DepartmentId = department.Id "
                      + "WHERE seller.Id = ?");

      statement.setInt(1, id);

      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Department department = instantiateDepartment(resultSet);
        Seller seller = instantiateSeller(resultSet, department);
        return seller;
      }
      return null;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  @Override
  public List<Seller> findAll() {
    return List.of();
  }

  @Override
  public List<Seller> findByDepartment(Department department) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.prepareStatement(
              "SELECT seller.*,department.Name as DepName " +
                      "FROM seller INNER JOIN department " +
                      "ON seller.DepartmentId = department.Id " +
                      "WHERE DepartmentId = ? " +
                      "ORDER BY Name"
      );

      statement.setInt(1, department.getId());

      resultSet = statement.executeQuery();

      List<Seller> sellers = new ArrayList<>();

      Map<Integer, Department> departmentMap = new HashMap<>();

      while (resultSet.next()) {

        department = departmentMap.get(resultSet.getInt("DepartmentId"));

        if (department == null) {
          department = instantiateDepartment(resultSet);
          departmentMap.put(resultSet.getInt("DepartmentId"), department);
        }

        Seller seller = instantiateSeller(resultSet, department);
        sellers.add(seller);
      }
      return sellers;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      DB.closeStatement(statement);
      DB.closeResultSet(resultSet);
    }
  }

  private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
    Department department = new Department();
    department.setId(resultSet.getInt("DepartmentId"));
    department.setName(resultSet.getString("DepName"));
    return department;
  }

  private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
    Seller seller = new Seller();
    seller.setId(resultSet.getInt("Id"));
    seller.setName(resultSet.getString("Name"));
    seller.setEmail(resultSet.getString("Email"));
    seller.setBirthDate(resultSet.getDate("BirthDate"));
    seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
    seller.setDepartment(department);
    return seller;
  }
}
