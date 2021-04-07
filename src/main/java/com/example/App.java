package com.example;

public class App {
    public static void main(final String[] args) {

//        try (final DataBaseConnection connection = DataBaseConnection.getInstance()) {
//
//            final DaoFactory daoFactory = new DaoFactory(connection.getConnection());
//            final Dao userDao = daoFactory.createDao(User.class);
//            final Dao carDao = daoFactory.createDao(Car.class);
//
//            final ObjectMapper objectMapper = new ObjectMapper();
//            userDao.getAll().forEach(value -> {
//                try {
//                    System.out.println(objectMapper.writeValueAsString(value));
//                } catch (final IOException e) {
//                    e.printStackTrace();
//                }
//
//            });
//
//            userDao.getAll().forEach(System.out::println);
//            userDao.sortingByName().forEach(System.out::println);
//            carDao.getAll().forEach(System.out::println);
//            carDao.sortingByName().forEach(System.out::println);
//
//        } catch (final IOException | SQLException | IllegalAccessException | InstantiationException | NoSuchMethodException ex) {
//            ex.printStackTrace();
//        }

    }
}
