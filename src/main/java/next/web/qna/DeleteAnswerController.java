package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import next.model.User;



/*
 * 9. 상세보기 화면의 답변 목록에서 답변 삭제
 * 답변 삭제 또한 화면을 깜빡이지 않고 구현이 가능하도록 AJAX로 구현
 */

public class DeleteAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(DeleteAnswerController.class);	
	
    private AnswerDao answerDao = new AnswerDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        Answer answer = answerDao.findById( ServletRequestUtils.getRequiredLongParameter(request, "answerId"));
        answerDao.delete(answer);
        
        
        // 해당 Question의 Answer 갯수 감소
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(answer.getQuestionId());
        question.countDown();
        questionDao.update(question);
        
	    ModelAndView mav = jsonView();
	    mav.addObject("result", Result.ok());
        
        return mav;
    }
}



