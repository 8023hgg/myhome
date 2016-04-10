$(function(){
    var path = $("#contextPath").val();
    $(".download").click(function(){
        var fid = $(this).attr("rel");
        if(fid == ""){
            alert("参数异常!!");
            return;
        }
        var hem = "";
        hem += "<form action='"+path+"/file/download' id='downLoadForm' method='get'>";
        hem += "<input name='fid' value='"+fid+"' type='hidden'/>";
        hem += "</form>";
        $("#formDiv").html(hem);
        $("#downLoadForm").submit();
    })

    $(".del").click(function(){
        var fid = $(this).attr("rel");
        if(fid == ""){
            alert("参数异常!!");
            return;
        }
        $.ajax({
            url:path+"/file/del-file",
            data:{"fid":fid},
            type:"post",
            dataType:"text",
            success:function(data){
                var dat = eval('('+data+')');
                if(dat == "T"){
                    alert("删除成功！！");
                    window.location.reload();
                }else{
                    alert("删除失败！！");
                }
            }
        })
    })
})