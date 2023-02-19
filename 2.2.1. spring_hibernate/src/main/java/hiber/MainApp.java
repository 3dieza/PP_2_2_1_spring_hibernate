package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new Car("BMW", 666));
      userService.add(new Car("Nissan", 7));
      userService.add(new Car("Porsche", 1));
      userService.add(new Car("Lada", 0));




      List<Car> cars = userService.listCars();

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars.get(1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars.get(0)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars.get(3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars.get(2)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      for (Car car : cars) {
         System.out.println("Id = "+car.getId());
         System.out.println("model = "+car.getmodel());
         System.out.println("series = "+car.getseries());
         System.out.println();
      }
      System.out.println("Кто ездит на: \"Lada 0\"");
      System.out.println("Ответ: "+userService.findOwner("Lada", 0));


      context.close();
   }
}
