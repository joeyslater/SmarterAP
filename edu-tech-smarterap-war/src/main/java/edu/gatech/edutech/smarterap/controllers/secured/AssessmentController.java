package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Assessment;

@Controller
@RequestMapping("/api/assessment")
public class AssessmentController implements CrudController<Assessment>{

	@Override
	public Set<Assessment> readAll() {
		return null;
	}

	@Override
	public Assessment read(Long uid) {
		return null;
	}

	@Override
	public Assessment create() {
		return null;
	}

}
