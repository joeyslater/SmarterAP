package edu.gatech.edutech.smarterap.api;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stormpath.spring.security.provider.StormpathUserDetails;

import edu.gatech.edutech.smarterap.model.User;
import edu.gatech.edutech.smarterap.util.UserBuilderUtil;

/**
 * Created by Scott on 11/7/15.
 */
@Controller
@RequestMapping("/api/account")
public class ApiLoginController {

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User config(@AuthenticationPrincipal final StormpathUserDetails details)
    {
        final User user = UserBuilderUtil.build(details);
        return user;
    }

}
