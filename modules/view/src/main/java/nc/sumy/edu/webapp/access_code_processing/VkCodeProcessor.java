package nc.sumy.edu.webapp.access_code_processing;

import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/VkCodeProcessor")
public class VkCodeProcessor extends HttpServlet {
    private static final CodeProcessor PROCESSOR = new CodeProcessor();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PROCESSOR.processOAuth2(request, response, SocialNetworks.VK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PROCESSOR.processOAuth2(request, response, SocialNetworks.VK);
    }
}
