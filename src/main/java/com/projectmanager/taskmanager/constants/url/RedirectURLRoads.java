package com.projectmanager.taskmanager.constants.url;

/**
 * It should keep all the roads in the application.
 */
public class RedirectURLRoads {
    //
    public final static String REDIRECT_INDEX = "redirect:/";
    public final static String REDIRECT_LOGIN_PAGE = "redirect:/login";

    // comes from spring security and its setted up in the websecurity.java file  - at the config method.
    public final static String REDIRECT_LOGIN_LOGOUT = "redirect:/login?logout";

    public final static String REDIRECT_REGISTER_PAGE = "redirect:/register";

    private RedirectURLRoads() {
    }
}
