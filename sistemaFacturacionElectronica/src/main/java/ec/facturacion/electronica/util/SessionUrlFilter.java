package ec.facturacion.electronica.util;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author KevinArnold
 */

@WebFilter("*.jsf")
public class SessionUrlFilter implements Filter{

    FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse res=(HttpServletResponse) response;
        HttpSession session=req.getSession(true);
        
        String requestUrl=req.getRequestURL().toString();
        
        String[] urlPermitidaSinSesion=new String[]
        {
            "/index.jsf"
        	,"javax"
        };
        
        boolean redireccionarPeticion;
        
        if(session.getAttribute("active")==null && session.getAttribute("cliente") == null)
        {            
            redireccionarPeticion=true;
            
            for(String item : urlPermitidaSinSesion)
            {
                if(requestUrl.contains(item))
                {
                    redireccionarPeticion=false;
                    
                    break;
                }
            }
        }
        else
        {
            redireccionarPeticion=false;
        }
        
        if(redireccionarPeticion)
        {
            res.sendRedirect(req.getContextPath()+"/index.html");
        }
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig=null;
    }
    
}