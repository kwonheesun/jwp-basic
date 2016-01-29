package next.web.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;



//리스트 출력
/*
* Q&A 서비스를 모바일에서도 서비스할 계획이라 API를 추가해야 한다.
* API는 JSON 또는 XML 형식으로 제공할 계획
* 질문 목록 데이터를 /api/list.next URL로 접근 가능하도록 서비스
*/


public class MoblieShowController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private List<Question> questions;
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		questions = questionDao.findAll();
		
		ModelAndView mav = jstlView("/qna/list.jsp");
		mav.addObject("questions", questions);
		return mav;
	}
}