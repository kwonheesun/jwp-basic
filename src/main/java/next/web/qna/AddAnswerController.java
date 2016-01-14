package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Answer;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                ServletRequestUtils.getRequiredLongParameter(request, "questionId"));
        Answer savedAnswer = answerDao.insert(answer);
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);
        return mav;
    }

}
