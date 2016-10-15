package com.mzdev.board.settings;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebXml implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ServletContextXml.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		FilterRegistration.Dynamic charEncodingFilter =
				servletContext.addFilter("charEncodingFilter", getCharacterEncodingFilter());
		
		rootContext.setServletContext(servletContext);
		ServletRegistration.Dynamic mzServlet = 
				servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		
		charEncodingFilter.addMappingForServletNames(null, true, mzServlet.getName());
		
		mzServlet.addMapping("/");
		mzServlet.setLoadOnStartup(1);
		
	}
	
	
	// characterEncoding "UTF-8"
	private CharacterEncodingFilter getCharacterEncodingFilter(){
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		
		return filter;
		
	}

}
