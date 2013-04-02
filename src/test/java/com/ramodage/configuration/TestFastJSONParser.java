package com.ramodage.configuration;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: rixonmathew
 * Date: 19/01/13
 * Time: 7:49 PM
 */
public class TestFastJSONParser {

    static class Group {
        private Long id;
        private String name;
        private List<User> users = new ArrayList<User>();

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public List<User> getUsers() { return users; }
        public void setUsers(List<User> users) { this.users = users; }
    }

    static class User {
        private Long id;
        private String name;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void testJSONParsing() {
        String jsonString = "{   \"name\":\"admin\",\"id\":0,\"users\":[    {\"name\":\"guest\",\"id\":2},    {\"name\":\"root\",\"id\":3}    ]    }";
        Group group = JSON.parseObject(jsonString, Group.class);
        List<User> users = group.getUsers();
        for (User user:users) {
            System.out.println("user = " + user);
        }
        System.out.println("group.getName() = " + group.getName());

    }

}
