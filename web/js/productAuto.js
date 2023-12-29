
$(function (){
    $('#keyword').on('keyup',function (){
        var keyword=$(this).val();
        console.log(keyword);
        if(keyword!=''&&keyword!=null&&keyword.length!==0){
            $.ajax({
                    type    : 'GET',
                    url     : 'http://localhost:8080/mypetstore/productAuto?keyword='+keyword,
                    success : function (data){
                        console.log(data);
                        var  productListHTML='';
                        for(var i=0;i<data.length;i++){
                            productListHTML+='<li class=\"productAutoItem\" data-productId=\"';
                            productListHTML+=data[i].productId;
                            productListHTML+='\">';
                            productListHTML+='[';
                            productListHTML+=data[i].categoryId;
                            productListHTML+=']: ';
                            productListHTML+=data[i].name;
                            productListHTML+='</li>'
                        }
                        $('#productAutoList').html(productListHTML);//动态渲染html
                        $('#productAutoComplete').show();
                    },
                    error   : function (errorMsg){
                        console.log(errorMsg);
                    }
                }
            )
        }
        else {
            $('#productAutoComplete').hide();
        }
    })

    //利用事件冒泡，动态绑定
    $(document).on('click','.productAutoItem',function (){
        console.log("aaa");
        var productId=$(this).data('productid');
        $('#productAutoComplete').hide();
        $('#keyword').val('');
        window.location.href="http://localhost:8080/mypetstore/productForm?productId="+productId;
    })

    $('#productAutoComplete').on('mouseleave',function (){
        $(this).hide();
       // $('#keyword').val('');
    })
})
