package com.hj.study.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class HashMapTest {

    @Test
    public void hashMapTest() {

        //HashMap自定义key对象必须要重写 hashCode 和 equals 方法

        Map<Person, Person> personMap = new HashMap<>(2);
        Person p1 = new Person(1);
        Person p2 = new Person(1);

        personMap.put(p1, p1);
        System.out.println(personMap.get(p2));
        System.out.println("p1->HashCode:" + p1.hashCode());
        System.out.println("p2->HashCode:" + p2.hashCode());
        System.out.println("p1 和 p2是否相等：" + p1.equals(p2));

        //HashMap中key重复放时，key保持不变，value会更新

        Map<Person, String> personMap2 = new HashMap<>(2);
        Person p3 = new Person(1, "zs");
        Person p4 = new Person(1, "ls");

        personMap2.put(p3, "p3");
        personMap2.put(p4, "p4");
        for (Map.Entry<Person, String> map : personMap2.entrySet()) {
            System.out.println("key: " + map.getKey());
            System.out.println("value: " + map.getValue());
        }


    }

    class Person {
        private Integer id;
        private String name;

        public Person(Integer id) {
            this.id = id;
        }

        public Person(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" + "id=" + id + '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Person person = (Person) obj;
            return Objects.equals(id, person.id);
        }

    }



}
