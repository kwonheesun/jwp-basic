package next.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.UserDao;
import next.model.User;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class CreateController extends AbstractController {
    private UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(
                request.getParameter("userId"), 
                request.getParameter("password"), 
                request.getParameter("name"),
                request.getParameter("email"));
        userDao.insert(user);
        return jstlView("redirect:/qna/list.next");
    }
}
