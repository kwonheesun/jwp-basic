package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class AddAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddAnswerController.class);	
	
    private AnswerDao answerDao = new AnswerDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                ServletRequestUtils.getRequiredLongParameter(request, "questionId"));
        
        Answer savedAnswer = answerDao.insert(answer);
        
      //Question 갯수 증가
      long questionId = Long.parseLong(request.getParameter("questionId"));
      QuestionDao questionDao = new QuestionDao();
      Question question = questionDao.findById(questionId);
      question.countUp();
      questionDao.update(question);
      
      question = questionDao.findById(questionId);
      
      logger.info(String.valueOf(question.getCountOfComment()));
        
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);
        
        logger.info(request.getParameter("questionId") + "에 추가되었습니다.");
        
        return mav;
    }

}
