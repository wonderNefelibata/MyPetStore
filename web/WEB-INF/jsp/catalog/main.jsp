<%@ include file="../common/top.jsp"%>
<div id="Welcome">
    <div id="WelcomeContent">
<%--        <c:if--%>
<%--            test="${sessionScope.accountBean != null }">--%>
<%--            <c:if test="${sessionScope.accountBean.authenticated}">--%>
<%--                Welcome ${sessionScope.accountBean.account.firstName}!--%>
<%--            </c:if>--%>
<%--        </c:if>--%>

    <%--功能：显示登录用户的first name--%>
    </div>
</div>

<div id="Main">
    <%--侧边栏--%>
    <div id="Sidebar">
        <div id="SidebarContent">
<%--            <stripes:link--%>
<%--                beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                event="viewCategory">--%>
<%--            <stripes:param name="categoryId" value="FISH" />--%>
<%--            <img src="../images/fish_icon.gif" />--%>
<%--        </stripes:link> <br />--%>
<%--            Saltwater, Freshwater <br />--%>
<%--            <stripes:link--%>
<%--                    beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                    event="viewCategory">--%>
<%--                <stripes:param name="categoryId" value="DOGS" />--%>
<%--                <img src="../images/dogs_icon.gif" />--%>
<%--            </stripes:link> <br />--%>
<%--            Various Breeds <br />--%>
<%--            <stripes:link--%>
<%--                    beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                    event="viewCategory">--%>
<%--                <stripes:param name="categoryId" value="CATS" />--%>
<%--                <img src="../images/cats_icon.gif" />--%>
<%--            </stripes:link> <br />--%>
<%--            Various Breeds, Exotic Varieties <br />--%>
<%--            <stripes:link--%>
<%--                    beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                    event="viewCategory">--%>
<%--                <stripes:param name="categoryId" value="REPTILES" />--%>
<%--                <img src="../images/reptiles_icon.gif" />--%>
<%--            </stripes:link> <br />--%>
<%--            Lizards, Turtles, Snakes <br />--%>
<%--            <stripes:link--%>
<%--                    beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean"--%>
<%--                    event="viewCategory">--%>
<%--                <stripes:param name="categoryId" value="BIRDS" />--%>
<%--                <img src="../images/birds_icon.gif" />--%>
<%--            </stripes:link> <br />--%>

            <a href=""><img src="images/fish_icon.gif"></a><br/>
            Saltwater, Freshwater<br/>
            <a href=""><img src="images/dogs_icon.gif"></a><br/>
            Various Breeds<br/>
            <a href=""><img src="images/cats_icon.gif"></a><br/>
            Various Breeds,Exotic Varieties<br/>
            <a href=""><img src="images/reptiles_icon.gif"></a><br/>
            Lizards, Turtles, Snakes<br/>
            <a href=""><img src="images/birds_icon.gif"></a><br/>
            Exotic Varieties
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
<%--            <%--这是大鸟--%>
                <area alt="Birds" coords="72,2,280,250"
                      href="categoryForm?categoryId=BIRDS" shape="RECT" />
                <area alt="Fish" coords="2,180,72,250"
                      href="" shape="RECT" />
                <area alt="Dogs" coords="60,250,130,320"
                      href="" shape="RECT" />
                <area alt="Reptiles" coords="140,270,210,340"
                      href="" shape="RECT" />
                <area alt="Cats" coords="225,240,295,310"
                      href="" shape="RECT" />
                <area alt="Birds" coords="280,180,350,250"
                      href="S" shape="RECT" />
            </map>
            <img height="355" src="images/splash.gif" align="middle"
                 usemap="#estoremap" width="350" />
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/bottom.jsp"%>