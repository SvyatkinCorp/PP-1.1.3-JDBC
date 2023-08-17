package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity                          //Эта аннотация перед классом укажет Hibernate, что это не просто класс, а специальный класс, объекты которого нужно хранить в базе данных.
@Table(name = "users")        //    Если имя класса и имя таблицы совпадают, то аннотацию @Table можно не указывать.
public class User {
    @Id                 // С ее помощью можно задать первичный ключ для таблицы
    @GeneratedValue(strategy = GenerationType.IDENTITY)            // тобы Hibernate самостоятельно генерировал ID твоих объектов при добавлении их в базу,
    private Long id;

    @Column                // это маппинг колонок таблицы на поля класса. В самом простом варианте Hibernate просто сам замапит поля твоего entity-класса на колонки нужной таблицы
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
