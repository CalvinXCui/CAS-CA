package com.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.action.LoginAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SuccessInterceptor extends AbstractInterceptor {
	private Map<String,Object> session ;
	HttpServletRequest request;

    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
		request = ServletActionContext.getRequest();
		String url=request.getRequestURI();
        session= ActionContext.getContext().getSession();
        String userName=(String)session.get("userName");
        if(!url.contains("login!leanIndex")){
        	if(userName==null && !"".equals(userName)){
            	if(url.contains("login!ifc")){
            		session.put("userName", "ifc");
            		LoginAction a=new LoginAction();
                	a.leanIndex();
            	}else{
                    return "login";
            	}
            }
        	/*if(userName==null && !"".equals(userName)){
                return "login";
            }*/
        }else{
        	LoginAction a=new LoginAction();
        	a.leanIndex();
        }

        return arg0.invoke();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*******************************************************/

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
