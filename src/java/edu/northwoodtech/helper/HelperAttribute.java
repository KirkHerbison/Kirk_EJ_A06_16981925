/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.northwoodtech.helper;

import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// this helper class has methods that can be used to help with session attributes
public class HelperAttribute {

    //this method will remove all attributes, essentially reseting the storage
    public static void removeSessionAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        Enumeration<String> sessionList = session.getAttributeNames();

        while (sessionList.hasMoreElements()) {
            String current = sessionList.nextElement();
            session.removeAttribute(current);
        }

    }

    //this method can be used to set the items in a map to session
    public static void setSessionAttributes(HttpServletRequest request,
            Map items) {
        items.forEach((key, value)-> 
        request.getSession().setAttribute(key.toString(), value));
    }

}//end of class
