<%-- 
    Document   : footer
    Created on : Feb 3, 2022, 8:48:14 AM
    Author     : Kirk Herbison
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer>
            <label class="parent"></label><span id="privacy_policy">View Our Privacy Policy</span>
            <div class="positionable" id="privacy_dialog" title="Privacy Policy" style="display: none;">
                <p>We respect you and your privacy. We only collect information that is necessary to 
                    rent one of our vehicles. We never sell your information</p>
            </div>
            <p>Developer: ${developer} - EJ_A04</p>
            <p>&copy; Copyright: ${currentYear}</p>
            <p>${param.page}</p>
        </footer>
    </body>
</html>
