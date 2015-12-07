package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Assessment;
import edu.gatech.edutech.smarterap.dtos.Course;
import edu.gatech.edutech.smarterap.dtos.StudentAssessment;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.AssessmentService;
import edu.gatech.edutech.smarterap.services.CrudService;

@RestController
@RequestMapping("/api/assessment")
public class AssessmentController implements CrudController<Assessment>
{
	@Autowired
	private CrudService			crudService;

	@Autowired
	private AssessmentService	assessmentService;

	@Override
	public Long count()
	{
		return crudService.count(Assessment.class);
	}

	@Override
	public List<Assessment> readAll()
	{
		return crudService.list(Assessment.class);
	}

	@ResponseBody
	@RequestMapping(value = "/student/course/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StudentAssessment> readAllAssessmentsForCourse(@PathVariable final Long courseId)
	{
		return assessmentService.readAllStudentAssessmentsForCourse(courseId, 0, 10);
	}

	@Override
	public Assessment read(@PathVariable final Long uid)
	{
		return crudService.get(Assessment.class, uid);
	}

	@ResponseBody
	@RequestMapping(value = "/student/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public StudentAssessment readStudentAssessment(@PathVariable final Long uid)
	{
		return assessmentService.readStudentAssessment(uid);
	}

	@ResponseBody
	@RequestMapping(value = "/teacher/{uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Assessment readTeacherAssessment(@PathVariable final Long uid)
	{
		return crudService.get(Assessment.class, uid);
	}

	@Override
	public JsonResponse<Assessment> create(final @RequestBody Assessment dto, final HttpServletRequest request)
	{
		if (dto.getCourseId() != null)
		{
			final Course course = crudService.get(Course.class, dto.getCourseId());
			if (course != null)
			{
				dto.setCourse(course);
			}
		}
		return crudService.create(dto);
	}

	@Override
	public JsonResponse<Assessment> delete(final Long uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Assessment> update(final Long uid, final Assessment dto)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
