package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Answer;
import edu.gatech.edutech.smarterap.dtos.Question;
import edu.gatech.edutech.smarterap.dtos.QuestionQuery;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionController implements CrudController<Question>
{
	@Autowired
	private CrudService		crudService;

	@Autowired
	private QuestionService	questionService;

	@Override
	public Long count()
	{
		return crudService.count(Question.class);
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Pair<List<Question>, Long> query(@RequestBody final QuestionQuery questionQuery)
	{
		return questionService.query(questionQuery);
	}

	@Override
	public List<Question> readAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question read(final Long uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Question> create(@RequestBody final Question dto, final HttpServletRequest request)
	{
		final List<Answer> ans = dto.getAnswers();
		dto.setAnswers(null);
		for (final Answer answer : ans)
		{
			answer.setQuestion(dto);
		}
		dto.setAnswers(ans);
		return crudService.create(dto);
	}

	@Override
	public JsonResponse<Question> delete(final Long uid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonResponse<Question> update(final Long uid, final Question dto)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
