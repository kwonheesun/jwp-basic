package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class UpdateQuestionController extends AbstractController {
    private QuestionDao questionDao = new QuestionDao();
    private UserDao userDao = new UserDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user != null){
			Question question = questionDao.findById(Long.parseLong(request.getParameter("questionId")));
			User user = userDao.findByUserId(writer);
			
		}
		
    	
    	User user = userDao.findByUserId(request.getParameter("userId"));
    	
    	User updateUser = new User(
                request.getParameter("userId"), 
                request.getParameter("password"), 
                request.getParameter("name"),
                request.getParameter("email"));
        User user = userDao.findByUserId(updateUser.getUserId());
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        user.update(updateUser);
        userDao.update(user);
        return jstlView("redirect:/qna/list.next");
    }
}
