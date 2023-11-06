//index
$(document).ready(function(){
    $("#other").addClass('main-hide');
    $('#finish').hide();
    $('#next_step').show();
    /*下一步*/
    $('#next_step').bind('click', function () {
        index++;
        ControlContent(index);
    });
});
var index=0;
function ControlContent(index) {
    $(document).ready(function(){
        $("#Catalog").hide().fadeIn();
    });
    var stepContents = ["basicInfo","other"];
    var key;//数组中元素的索引值
    for (key in stepContents) {
        var stepContent = stepContents[key];//获得元素的值
        if (key == index) {
            if(stepContent=='basicInfo'){
                $('#next_step').show();
                $('#finish').hide();
            }
            if(stepContent=='work'){
                $('#next_step').hide();
                $('#finish').show();
            }
            $('#'+stepContent).removeClass('main-hide');
            $('#point'+key).addClass('c-select');
            $('#line'+key).removeClass('b-select');
        }else {
            $('#'+stepContent).addClass('main-hide');
            if(key>index){
                $('#point'+key).removeClass('c-select');
                $('#line'+key).removeClass('b-select');
            }else if(key<index){
                $('#point'+key).addClass('c-select');
                $('#line'+key).addClass('b-select');
            }
        }
    }
}