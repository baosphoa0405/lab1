/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.utils;

/**
 *
 * @author Admin
 */
public class MyConstants {

    public static final String SITE_KEY = "6Ld3YtMaAAAAAKMPQUGsnPMTXfj_xa0KHpH7ICn5";
    public static final String SECRET_KEY = "6Ld3YtMaAAAAAJXB-HFRgy3XYnB3fNAeoi8Ldlcy";

    public static String GOOGLE_CLIENT_ID = "410682010873-rjejja7nbfg3cjr7nvhorsliptu90eva.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "waAXh2ZMT2rHYdlOD-k19BAJ";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/J3.L.P0016/LoginGoogleServlet";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
    
    public static int QUANITY_ITEM_IN_PAGE = 20;
    
    public static int STATUS_REQUEST_NEW = 1;
    public static int STATUS_REQUEST_IN_ACTIVE = 2;
    public static int STATUS_REQUEST_ACTIVE = 3;
    public static int STATUS_REQUEST_DELETE = 4;
    
    public static int STATUS_ACCOUNT_NEW = 1;
    public static int STATUS_ACCOUNT_ACTIVE = 2;
    
    public static int ROLE_ACCOUNT_USER = 1;
    public static int ROLE_ACCOUNT_ADMIN = 2;
    
}
