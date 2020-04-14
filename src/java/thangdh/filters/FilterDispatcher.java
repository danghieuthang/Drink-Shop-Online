/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangdh.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thangdh.dtos.UserDTO;

/**
 *
 * @author dhtha
 */
public class FilterDispatcher implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private final String loginPage = "login.jsp";
    private final List<String> user;
    private final List<String> admin;
    private final List<String> allUser;

    public FilterDispatcher() {
        user = new ArrayList<>();
        user.add("");
        user.add("login.jsp");
        user.add("LoginController");
        user.add("LogoutController");
        user.add("SearchDrinkController");
        user.add("AddToCartController");
        user.add("UpdateCartController");
        user.add("CheckOutController");
        user.add("RemoveDrinkCartController");
        user.add("shop.jsp");
        user.add("index.jsp");
        user.add("cart.jsp");
        user.add("checkout.jsp");
        user.add("ViewCartController");
        user.add("ViewHistoryController");
        user.add("history.jsp");
        user.add("single_shop.jsp");
        user.add("ViewDrinkController");
        user.add("registration.jsp");
        user.add("RegistrationController");
        user.add("LoginGoogleController");
        user.add("PaymentPaypalController");
        user.add("AuthorizePaymentController");
        user.add("ExecutePaymentController");
        
        //
        admin = new ArrayList<>();
        admin.add("");
        admin.add("login.jsp");
        admin.add("LoginController");
        admin.add("LogoutController");
        admin.add("SearchDrinkController");
        admin.add("AdminSearchDrinkController");
        admin.add("CreateDrinkController");
        admin.add("UpdateDrinkController");
        admin.add("DeleteDrinkController");
        admin.add("index.jsp");
        admin.add("shop.jsp");
        admin.add("manager.jsp");
        admin.add("update_drink.jsp");
        admin.add("create_drink.jsp");
        admin.add("single_shop.jsp");
        admin.add("ViewDrinkController");
        admin.add("registration.jsp");
        admin.add("RegistrationController");
        admin.add("LoginGoogleController");
        //
        allUser = new ArrayList<>();
        allUser.add("");
        allUser.add("login.jsp");
        allUser.add("LoginController");
        allUser.add("LogoutController");
        allUser.add("shop.jsp");
        allUser.add("index.jsp");
        allUser.add("SearchDrinkController");
        allUser.add("single_shop.jsp");
        allUser.add("ViewDrinkController");
        allUser.add("registration.jsp");
        allUser.add("RegistrationController");
        allUser.add("LoginGoogleController");

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String url = loginPage;
        try {

//            if ((uri.contains(".js") || uri.contains(".jpg") || uri.contains(".png") || uri.contains(".gif")
//                    || uri.contains(".jpeg")) ||uri.contains(".ttf") || uri.contains(".css") || uri.contains(".scss") && !uri.contains(".jsp")) {
//                chain.doFilter(request, response);
//            } else {
            if (uri.contains(".") && !uri.contains(".jsp") && !uri.contains("loginGoogle")) {
                chain.doFilter(request, response);
            } else {
                int lastIndex = uri.lastIndexOf("/");
                String resource = uri.substring(lastIndex + 1);
                if (resource.length() > 0) {
                    url = resource.substring(0, 1).toUpperCase() + resource.substring(1) + "Controller";
                    if (resource.lastIndexOf(".jsp") > 0) {
                        url = resource;
                    }
                }
                if (url != null) {
                    if (url.contains("login.jsp") || url.contains("LoginController")) {
                        //chain.doFilter(request, response);
                        RequestDispatcher rd = req.getRequestDispatcher(url);
                        rd.forward(request, response);
                        return;
                    }
                    HttpSession session = req.getSession(false);
                    if (session == null || session.getAttribute("USER") == null && allUser.contains(url)) {
                        RequestDispatcher rd = req.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("USER") != null) {
                        UserDTO userDTO = (UserDTO) session.getAttribute("USER");
                        String role = (String) userDTO.getRoleID();
                        if (role.equalsIgnoreCase("Admin") && admin.contains(url)) {
                            //chain.doFilter(request, response);
                            RequestDispatcher rd = req.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else if (!role.equalsIgnoreCase("Admin") && user.contains(url)) {
//                           chain.doFilter(request, response);
                            RequestDispatcher rd = req.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            res.sendRedirect("login.jsp");
                        }
                    } else {
                        res.sendRedirect("login.jsp");
                    }

                } else {
                    chain.doFilter(request, response);
                    return;
                }
            }

        } catch (Exception e) {
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
