package edu.gatech.edutech.smarterap.utils;

import static com.google.common.collect.Sets.newHashSet;
import static javax.xml.xpath.XPathConstants.NODE;
import static javax.xml.xpath.XPathConstants.NODESET;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.common.collect.Sets;

import edu.gatech.edutech.smarterap.dtos.Answer;
import edu.gatech.edutech.smarterap.dtos.Question;
import edu.gatech.edutech.smarterap.enums.QuestionType;

public final class ExamViewConversionUtil
{
	//Change this to whatever you have locally. Use the main method below to test this.
	//We will eventually do this via upload.
	private static final String		LOCAL_PATH				= "C:\\Users\\Joey\\Desktop\\test.dat";

	private static final String		EXP_QUESTION			= "presentation/flow/flow[@class=\"QUESTION_BLOCK\"]";
	private static final String		EXP_QUESTION_TYPE		= "itemmetadata/bbmd_questiontype";
	private static final String		EXP_RESPONSES			= "presentation/flow/flow[@class=\"RESPONSE_BLOCK\"]";
	private static final String		EXP_RESPONSE			= "response_lid/render_choice/flow_label";
	private static final String		EXP_RESPONSE_CORRECT	= "resprocessing/respcondition[@title=\"correct\"]/conditionvar/varequal";
	private static final String		EXP_RESPONSE_LABEL		= "response_label/@ident";

	private static DocumentBuilder	builder;
	private static XPath			xpath;

	private ExamViewConversionUtil()
	{

	}

	public static void convert()
	{
		try
		{
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xpath = XPathFactory.newInstance().newXPath();
			final String questionsFile = FileUtils.readFileToString(new File(LOCAL_PATH));
			final Document document = builder.parse(new InputSource(new StringReader(questionsFile)));

			final Set<Question> questions = Sets.newHashSet();
			final NodeList items = document.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++)
			{
				questions.add(createQuestionFromNode(items.item(i)));
			}

			for (final Question question : questions)
			{
				for (final Answer answer : question.getAnswers())
				{
					System.out.println(answer.getText());
				}
			}
		}
		catch (IOException | SAXException | XPathExpressionException | ParserConfigurationException e)
		{
			e.printStackTrace();
		}
	}

	private static Question createQuestionFromNode(final Node item) throws XPathExpressionException
	{
		final Node questionNode = (Node) xpath.evaluate(EXP_QUESTION, item, NODE);
		final Node responsesNode = (Node) xpath.evaluate(EXP_RESPONSES, item, NODE);
		final Node questionTypeNode = (Node) xpath.evaluate(EXP_QUESTION_TYPE, item, NODE);

		final String correctAnswer = ((Node) xpath.evaluate(EXP_RESPONSE_CORRECT, item, NODE)).getTextContent().trim();

		final Question question = new Question();
		question.setText(fix(questionNode.getTextContent().trim()));
		question.setAnswers(createAnswersFromNode(responsesNode, correctAnswer));
		question.setType(QuestionType.getValueFromString(questionTypeNode.getTextContent().trim()));
		return question;
	}

	private static String fix(String html)
	{
		//TODO Eventually use REGEX
		html = html.replace("style=\"font-family:'courier new'\"", "class=\"code\"");
		html = html.replace("style=\"font-family:'Courier New'\"", "class=\"code\"");
		html = html.replaceAll("//(.*?)<", "<span class=\"code comment\">//$1</span><");
		html = html.replace("style=\"font-size:10pt\"", "");
		return html;
	}

	private static Set<Answer> createAnswersFromNode(final Node responsesNode, final String correctAnswer) throws XPathExpressionException
	{
		final NodeList responses = (NodeList) xpath.evaluate(EXP_RESPONSE, responsesNode, NODESET);
		final Set<Answer> answers = newHashSet();

		for (int i = 0; i < responses.getLength(); i++)
		{
			final Answer answer = new Answer();
			answer.setOrder(i);
			answer.setText(fix(responses.item(i).getTextContent().trim()));
			answer.setCorrect(correctAnswer.equalsIgnoreCase(xpath.evaluate(EXP_RESPONSE_LABEL, responses.item(i))));
			answers.add(answer);
		}
		return answers;
	}

	public static void main(final String[] args)
	{
		ExamViewConversionUtil.convert();
	}

}
