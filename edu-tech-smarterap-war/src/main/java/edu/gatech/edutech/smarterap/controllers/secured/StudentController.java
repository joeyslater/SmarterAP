package edu.gatech.edutech.smarterap.controllers.secured;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.User;

@Controller
@RequestMapping("/api/student")
public class StudentController implements CrudController<User> {

	@Override
	public Set<User> readAll() {	
		return newHashSet();
	}

	@Override
	public User read(@PathVariable Long uid) {
		return null;
	}

	@Override
	public User create() {
		return null;
	}
}
