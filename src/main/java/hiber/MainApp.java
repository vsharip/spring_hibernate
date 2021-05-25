package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.add(new User("Ivan", "Ivanov", "ivanov@mail.ru", new Car("Lada", 2115)));
        userService.add(new User("Sergey", "Sergeev", "sergeev@mail.ru", new Car("Volvo", 60)));
        userService.add(new User("Andrey", "Andreyeev", "andreyeev@mail.ru", new Car("Bmw", 5)));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car owner " + user.getFirstName() + ": " + user.getUserCar());
            System.out.println();
        }

        List<User> usersCars = userService.listUsersCars("Volvo",60);
        for (User user : usersCars ) {
            System.out.println(user.getUserCar()
                    + " принадлежит " + user.getFirstName() + " " + user.getLastName() + " "
                    + "id_number = " +  user.getId());
        }

        context.close();
    }
}
