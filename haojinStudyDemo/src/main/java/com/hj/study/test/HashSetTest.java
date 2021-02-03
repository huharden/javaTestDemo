package com.hj.study.test;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetTest {
    @Test
    public void setTest() {
        Person p1 = new Person(1, "zs");
        Person p2 = new Person(1, "ls");

        Set<Person> set = new HashSet<>(2);
        set.add(p1);
        set.add(p2);
        System.out.println(set);

        int MAXIMUM_CAPACITY = 1 << 30;

        System.out.println(MAXIMUM_CAPACITY);

    }

    class Person {
        private Integer id;
        private String name;

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(id, person.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
