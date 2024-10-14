package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public static List<User> findAll() throws SQLException {
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users";

    try (var con = DB.getConnection();
        var stmt = con.createStatement();
        var rs = stmt.executeQuery(query);) {

      var users = new ArrayList<User>();
      while (rs.next()) {
        var id = rs.getInt("id");
        var username = rs.getString("username");
        var firstName = rs.getString("firstName");
        var lastName = rs.getString("lastName");
        var email = rs.getString("email");
        var avatar = rs.getString("avatar");

        users.add(new User(id, username, firstName, lastName, email, avatar));
      }

      return users;
    }
  }

  public static User findById(int _id) throws SQLException {
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users WHERE id = ?";

    try (var connection = DB.getConnection();
         var statement = connection.prepareStatement(query)) {
      statement.setInt(1, _id);

      try (var resultSet = statement.executeQuery()) {

        if (!resultSet.next()) {
          return null;
        }

        var id = resultSet.getInt("id");
        var username = resultSet.getString("username");
        var firstName = resultSet.getString("firstName");
        var lastName = resultSet.getString("lastName");
        var email = resultSet.getString("email");
        var avatar = resultSet.getString("avatar");
        return new User(id, username, firstName, lastName, email, avatar);
      }
    }
  }
}

