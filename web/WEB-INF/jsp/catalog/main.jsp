<%@ include file="../common/top.jsp"%>
<script>
    function show(obj,id) {
        var objDiv = $("#"+id+"");
        //css样式设置
        $(objDiv).css("display","block");
        $(objDiv).css("border-radius", "10px");
        $(objDiv).css("border-width", "2px");
        $(objDiv).css("border-color", "rgba(91,91,91,0.65)");
        $(objDiv).css("background", "rgba(255,255,255,0.8)");
        $(objDiv).css("font-family", "Consolas");
        $(objDiv).css("left", event.clientX + 10);
        $(objDiv).css("top", event.clientY + 10);
    }
    function hide(obj,id) {
        var objDiv = $("#"+id+"");
        $(objDiv).css("display", "none");
    }
</script>

<div id="Welcome">
    <div id="WelcomeContent" style="
    height: 65px;
    font-size: 36px;
    text-align: center;
    padding-top: 20px;
    color: #215496;
    font-weight: bold;">
        Welcome to Mypetstore!
    </div>
</div>

<div class="main" id="Main">
    <div id="Sidebar">
        <div id="SidebarContent">
<%--            <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif"></a><br/>--%>
<%--&lt;%&ndash;            Saltwater, Freshwater&ndash;%&gt;--%>
<%--            <span><img src="/images/dog2.gif"></span>--%>
<%--            <br/>--%>
<%--            <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif"></a><br/>--%>
<%--            Various Breeds<br/>--%>
<%--            <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif"></a><br/>--%>
<%--            Various Breeds,Exotic Varieties<br/>--%>
<%--            <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif"></a><br/>--%>
<%--            Lizards, Turtles, Snakes<br/>--%>
<%--            <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif"></a><br/>--%>
<%--            Exotic Varieties--%>

    <div id="flip1" style="height: 40px; width: 100%; alignment: left; border-radius: 5px;">
        <a href="categoryForm?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
        <i class="arrowMored"></i>
    </div>
    <div id="panel1" style="height: 70px; width: 100%; alignment: left; border-radius: 5px;">
        <%--            <br/> Saltwater, Freshwater <br/>--%>
        <a href="productForm?productId=FI-SW-01"><img src="images/fish1.gif" style="width: 90px; height: 90px;"/></a>
        <a href="productForm?productId=FI-FW-02"><img src="images/fish2.gif" style="width: 90px; height: 90px;"/></a>
        <a href="productForm?productId=FI-FW-01"><img src="images/fish3.gif" style="width: 90px; height: 90px;"/></a>
    </div>


    <div id="flip2" style="height: 40px; width: 100%; alignment: left; border-radius: 5px;">
        <a href="categoryForm?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
        <i class="arrowMored"></i>
    </div>
    <div id="panel2" style="height: 70px; width: 100%; alignment: left; border-radius: 5px;">
        <%--            <br/> Saltwater, Freshwater <br/>--%>

            <a href="productForm?productId=K9-BD-01"><img src="images/dog3.gif" style="width: 90px; height: 90px;"/></a>
            <a href="productForm?productId=K9-CW-01"><img src="images/dog4.gif" style="width: 90px; height: 90px;"/></a>
            <a href="productForm?productId=K9-DL-01"><img src="images/dog5.gif" style="width: 90px; height: 90px;"/></a>
    </div>


    <div id="flip3" style="height: 40px; width: 100%; alignment: left; border-radius: 5px;">
        <a href="categoryForm?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
        <i class="arrowMored"></i>
    </div>
    <div id="panel3" style="height: 70px; width: 100%; alignment: left; border-radius: 5px;">
        <%--            <br/> Saltwater, Freshwater <br/>--%>
            <a href="productForm?productId=FL-DLH-02"><img src="images/cat1.gif" style="width: 90px; height: 90px;"/></a>
            <a href="productForm?productId=FL-DSH-01"><img src="images/cat2.gif" style="width: 90px; height: 90px;"/></a>
    </div>


    <div id="flip4" style="height: 40px; width: 100%; alignment: left; border-radius: 5px;">
        <a href="categoryForm?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
        <i class="arrowMored"></i>
    </div>
    <div id="panel4" style="height: 70px; width: 100%; alignment: left; border-radius: 5px;">
        <%--            <br/> Saltwater, Freshwater <br/>--%>
        <a href="productForm?productId=RP-SN-01"><img src="images/snake1.gif" style="width: 90px; height: 90px;"/></a>
        <a href="productForm?productId=RP-LI-02"><img src="images/lizard1.gif" style="width: 90px; height: 90px;"/></a>
    </div>


    <div id="flip5" style="height: 40px; width: 100%; alignment: left; border-radius: 5px;">
        <a href="categoryForm?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
        <i class="arrowMored"></i>
    </div>
    <div id="panel5" style="height: 70px; width: 100%; alignment: left; border-radius: 5px;">
        <%--            <br/> Saltwater, Freshwater <br/>--%>
        <a href="productForm?productId=AV-SB-02"><img src="images/bird1.gif" style="width: 90px; height: 90px;"/></a>
        <a href="productForm?productId=AV-CB-01"><img src="images/bird2.gif" style="width: 90px; height: 90px;"/></a>
    </div>
        </div>
    </div>

    <div id="MainImage">
        <div id="MainImageContent">
            <map name="estoremap">
                <area onmouseout="javascript:hide(this, 'BirdsTip');" onMouseOver="javascript:show(this,'BirdsTip');" alt="Birds" coords="72,2,280,250" href="categoryForm?categoryId=BIRDS" shape="rect" />
                <area onmouseout="javascript:hide(this, 'FishTip');" onMouseOver="javascript:show(this,'FishTip');" alt="Fish" coords="2,180,72,250" href="categoryForm?categoryId=FISH" shape="rect" />
                <area onmouseout="javascript:hide(this, 'DogsTip');" onMouseOver="javascript:show(this,'DogsTip');" alt="Dogs" coords="60,250,130,320" href="categoryForm?categoryId=DOGS" shape="rect" />
                <area onmouseout="javascript:hide(this, 'ReptilesTip');" onMouseOver="javascript:show(this,'ReptilesTip');" alt="Reptiles" coords="140,270,210,340" href="categoryForm?categoryId=REPTILES" shape="rect" />
                <area onmouseout="javascript:hide(this, 'CatsTip');" onMouseOver="javascript:show(this,'CatsTip');" alt="Cats" coords="225,240,295,310" href="categoryForm?categoryId=CATS" shape="rect" />
                <area onmouseout="javascript:hide(this, 'BirdsTip');" onMouseOver="javascript:show(this,'BirdsTip');" alt="Birds" coords="280,180,350,250" href="categoryForm?categoryId=BIRDS" shape="rect" />
            </map>
            <img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
            <div id="inform" style="display: none"></div>
        </div>
    </div>
    <div id="Separator">&nbsp;</div>
    <div id="BirdsTip" style="position:absolute;display:none;border:1px solid silver;background:silver;">
        Category:   BIRDS
        <br>
        AV-CB-01   Amazon Parrot
        <br>
        AV-SB-02   Finch
    </div>
    <div id="FishTip" style="position:absolute;display:none;border:1px solid silver;background:silver;">
        Category:   FISH
        <br>
        FI-FW-01   Koi
        <br>
        FI-FW-02   Goldfish
        <br>
        FI-SW-01   Angelfish
        <br>
        FI-SW-02   Tiger Shark
    </div>
    <div id="DogsTip" style="position:absolute;display:none;border:1px solid silver;background:silver;">
        Category:   DOGS
        <br>
        K9-BD-01   Bulldog
        <br>
        K9-CW-01   Chihuahua
        <br>
        K9-DL-01   Dalmation
        <br>
        K9-PO-02   Poodle
        <br>
        K9-RT-01   Golden Retriever
        <br>
        K9-RT-02   Labrador Retriever
    </div>
    <div id="ReptilesTip" style="position:absolute;display:none;border:1px solid silver;background:silver;">
        Category:   REPTILES
        <br>
        RP-LI-02   Iguana
        <br>
        RP-SN-01   Rattlesnake
    </div>
    <div id="CatsTip" style="position:absolute;display:none;border:1px solid silver;background:silver;">
        Category:   CATS
        <br>
        FL-DLH-02  Persian
        <br>
        FL-DSH-01  Manx
    </div>
</div>



<%@ include file="../common/bottom.jsp"%>