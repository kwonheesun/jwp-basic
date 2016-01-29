package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

/*
 * 질문 추가 로직은 QuestionDao.insert 이용
 * HttpServletRequest에서 값을 추출할 때는 ServletRequestUtils class 활용
 * 질문 성공 후 질문 목록 페이지로 이동
 */
public class AddQuestionController  extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddQuestionController.class);	
	
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		if(user != null){
			Question question = new Question(
					request.getParameter("writer"), 
					request.getParameter("title"), 
					request.getParameter("contents"));
			questionDao.insert(question);
			logger.info("성공");
		}
		// 로그인 하세요 출력 필요
		
		return jstlView("redirect:/qna/list.next");
	}

}

