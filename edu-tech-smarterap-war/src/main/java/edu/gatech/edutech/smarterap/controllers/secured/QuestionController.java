package edu.gatech.edutech.smarterap.controllers.secured;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.gatech.edutech.smarterap.controllers.CrudController;
import edu.gatech.edutech.smarterap.dtos.Answer;
import edu.gatech.edutech.smarterap.dtos.Question;
import edu.gatech.edutech.smarterap.dtos.QuestionQuery;
import edu.gatech.edutech.smarterap.dtos.json.JsonResponse;
import edu.gatech.edutech.smarterap.services.CrudService;
import edu.gatech.edutech.smarterap.services.QuestionService;
import edu.gatech.edutech.smarterap.utils.ExamViewConversionUtil;

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

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Question> uploadBlackboard(@RequestBody final String data)
	{
		return ExamViewConversionUtil.convert(data);
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

	@RequestMapping(value = "/merge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void merge(@RequestBody final List<Question> dtos, final HttpServletRequest request)
	{
		for (final Question dto : dtos)
		{
			final List<Answer> ans = dto.getAnswers();
			dto.setAnswers(null);
			for (final Answer answer : ans)
			{
				answer.setQuestion(dto);
			}
			dto.setAnswers(ans);
		}
		crudService.merge(dtos);
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
		return crudService.delete(Question.class, uid);
	}

	@RequestMapping(value = "/{uid}/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResponse<Question> delete2(final Long uid)
	{
		return crudService.delete(Question.class, uid);
	}

	@Override
	public JsonResponse<Question> update(final Long uid, final Question dto)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
