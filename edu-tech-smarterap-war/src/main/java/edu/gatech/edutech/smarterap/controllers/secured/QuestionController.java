package edu.gatech.edutech.smarterap.controllers.secured;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.Question;

@Controller
@RequestMapping("/api/question")
public class QuestionController implements CrudController<Question> {

	@Override
	public Set<Question> readAll() {	
		return newHashSet();
	}

	@Override
	public Question read(@PathVariable Long uid) {
		return new Question();
	}

	@Override
	public Question create() {
		// TODO Auto-generated method stub
		return null;
	}
}
