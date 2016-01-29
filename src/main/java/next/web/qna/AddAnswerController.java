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
import next.model.Result;

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
        
      // 해당 Question의 Answer 갯수 증가
      long questionId = Long.parseLong(request.getParameter("questionId"));
      QuestionDao questionDao = new QuestionDao();
      Question question = questionDao.findById(questionId);
      question.countUp();
      questionDao.update(question);
      
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);
//        mav.addObject("result", Result.ok());
        
        // 깜박이지 않고도 알아서 실행 되도록 수정하기
        
        return mav;
    }

}
