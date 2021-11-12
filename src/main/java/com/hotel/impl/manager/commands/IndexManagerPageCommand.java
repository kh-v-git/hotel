package com.hotel.impl.manager.commands;

import com.hotel.Command;
import com.hotel.impl.request.Request;
import com.hotel.impl.request.RequestRepository;
import com.hotel.impl.request.RequestRepositorySQLImpl;
import com.hotel.impl.request.RequestService;
import com.hotel.security.RequiresRole;
import com.hotel.utils.DataPatternValidation;
import com.hotel.utils.enums.UserRequestStatusEnum;
import com.hotel.utils.enums.UserRolesEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * command to view index manager page
 */
@RequiresRole(UserRolesEnum.MANAGER)
public class IndexManagerPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(IndexManagerPageCommand.class);

    /**
     * Collect data for index_manager.jsp
     *
     * @param request  http request
     * @param response http response
     * @throws Exception maintenance exception
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        List<Request> userRequestList = requestService.getRequestList(UserRequestStatusEnum.REQUESTED.toString().toLowerCase(), Arrays.asList("status"));

        if (!userRequestList.isEmpty()) {
            request.setAttribute("requestList", userRequestList);
        } else {
            request.setAttribute("statusCommand", "Users has no active requests");
            log.log(Level.DEBUG, "Users has no active requests");
        }
        pageLocale(request, "secured-manager.command");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pages/manager/index_manager.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<Request> getUserRequestList(int userId) {
        RequestRepository requestRepository = new RequestRepositorySQLImpl();
        RequestService requestService = new RequestService(requestRepository);
        return requestService.getRequestList(userId);
    }

    private void pageLocale(HttpServletRequest localeRequest, String servletCommand) {
        String queryString = Optional.ofNullable(localeRequest.getQueryString())
                .map(Object::toString)
                .map(String::trim)
                .orElse("");
        if (servletCommand.isEmpty()) {
            servletCommand = "secured-admin.command";
        }
        localeRequest.getSession().setAttribute("pageQuery", queryString);
        localeRequest.getSession().setAttribute("pageCommand", servletCommand);
    }
}
