package next.web.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

/*
 * 답변 목록을 정적인 html이 아니라 데이터베이스에 저장되어 있는 답변을 출력하도록 구현한다.
 * 단, jstl과 el만으로 구현
 */

public class ShowController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
	
	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	private Question question;
	private List<Answer> answers;
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		
		logger.debug("questionId : {}", questionId);
		
		question = questionDao.findById(questionId);
		answers = answerDao.findAllByQuestionId(questionId);
		
		ModelAndView mav = jstlView("/qna/show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		
		logger.info(String.valueOf(questionId));
		logger.info(String.valueOf(question.getCountOfComment()));

		return mav;
	}
}
