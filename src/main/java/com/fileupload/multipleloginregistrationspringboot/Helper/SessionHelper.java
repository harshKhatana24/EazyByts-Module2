package com.fileupload.multipleloginregistrationspringboot.Helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    public static void removeMessage(){
        System.out.println("Removing message from session");
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getSession();

        try{
            session.removeAttribute("msg");
        }catch (Exception e){
            System.out.println("Error in SessionHelper: "+e);
            e.printStackTrace();
        }

    }

}