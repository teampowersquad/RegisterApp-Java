package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

@Controller
// Define a route handler for requesting the view/document
@RequestMapping(value = "/mainMenu")
public class MainMenuRouteController extends BaseRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start(
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request // Use this to access the current session and associated user
	) {
		final Optional<ActiveUserEntity> activeUserEntity = this.getCurrentUser(request);
		// If there is an active user then redirect to the Sign In view/document with error
		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		}
		// If there is not active user, add error message and the Main Menu view/document
		ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.MAIN_MENU.getViewName()),
				queryParameters);
		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			this.isElevatedUser(activeUserEntity.get()));
		return modelAndView;
	}
}
