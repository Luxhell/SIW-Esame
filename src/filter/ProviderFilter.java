package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ProviderManager;

@WebFilter("/portaleProvider/*")
public class ProviderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		ProviderManager loginBean = (ProviderManager)((HttpServletRequest)request).getSession().getAttribute("providerManager");

		if (loginBean == null || !loginBean.isLogged()) {
			String contextPath = ((HttpServletRequest)request).getContextPath();
			((HttpServletResponse)response).sendRedirect(contextPath + "/login_provider.xhtml");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// DEVE rimanere vuoto
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// DEVE rimanere vuoto
	}

}