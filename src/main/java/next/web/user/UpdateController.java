package next.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.UserDao;
import next.model.User;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class UpdateController extends AbstractController {
    private UserDao userDao = new UserDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
