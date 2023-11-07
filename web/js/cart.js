function num2string(num) {
    num += '';
    var pos_decimal = num.indexOf('.');
    if(pos_decimal < 0){
        pos_decimal = num.length;
        num += '.';
    }
    while (num.length <= pos_decimal + 2){
        num += '0';
    }
    return num;
}

function formatMoney(num){
    return '$'+ num2string(num);
}

$(".cartItem").each(function (){
    var MainThis = $(this);
    $(this).on("input","input",function (){
        var itemId = $(this).attr("name");
        $.ajax({
            data    :{quantity: this.value,
                itemId  : itemId},
            type    :"POST",
            url     :"changeQuantity",
            success :function (data){
                MainThis.children("#"+itemId).text(formatMoney(data.total));
                $("#subtotal").text(formatMoney(data.subtotal));
            }
        })
    })
})