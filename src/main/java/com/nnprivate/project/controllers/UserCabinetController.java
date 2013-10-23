package com.nnprivate.project.controllers;

import com.nnprivate.project.common.Constants;
import com.nnprivate.project.model.sql.domain.User;
import com.nnprivate.project.service.SecurityService;
import com.nnprivate.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 */
@Controller
public class UserCabinetController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = Constants.USER_CABINET_PAGE, method = RequestMethod.GET)
    public ModelAndView userCabinet(ModelMap model){
        UserDetails userDetails = securityService.getUserDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        putUserToModel(user, model);

        return new ModelAndView(Constants.USER_CABINET_PAGE, model);
    }

    private void putUserToModel(User user, ModelMap model) {
        model.put("email", user.getEmail());
        model.put("created", user.getCreated());
    }
}
