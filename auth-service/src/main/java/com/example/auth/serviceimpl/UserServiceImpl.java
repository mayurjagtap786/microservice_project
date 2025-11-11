package com.example.auth.serviceimpl;


import com.example.auth.beans.User;
import com.example.auth.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service("userService")
public class UserServiceImpl implements IUserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final List<User> list = new ArrayList<>();
  
	private static int count=0;
	static {

		list.add(new User(++count, "Mayur", "", "Jagtap", "mayurjagtap786@gmail.com", "9819701658"));
		list.add(new User(++count, "Sagar", "", "Patil", "sagar.patil@gmail.com", "8745961452"));
		list.add(new User(++count, "Siddhesh", "", "Lotlikar", "siddhesh.lotlikar@gmail.com", "9956412378"));
		list.add(new User(++count, "Charmi", "", "Chandane", "charmi.chandane@gmail.com", "9845621478"));
		list.add(new User(++count, "Bhagyashree", "", "Samant", "bhagyashree.samant@gmail.com", "7845123658"));
        list.add(new User(++count, "Gaurav", "", "Samant", "gaurav.samant@gmail.com", "98457895214"));
        list.add(new User(++count, "Sakshi", "", "Samant", "sakshi.samant@gmail.com", "54897897878"));

	}


     @Override
    public Optional<User> getUser(int id) {

        return list.stream().filter(user -> user.getUserID()==id).findAny();
    }

    @Override
    public List<User> getAllUser() {
       return  list;
    }

    @Override
    public User saveUser(User user) {
        int userId = list.size();
        user.setUserID(++userId);
        list.add(user);
        log.debug("User has been successfully saved.");
        return user;
    }

    @Override
    public void deleteUserById(int id) {

        Predicate<? super User> predicate = user -> user.getUserID() == id;
        list.removeIf(predicate);
    }
}
