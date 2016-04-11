$(function(){
    function navTabPageBreak(a){
        alert(a);
        var form = document.forms[1];
        var pp = form.find("input[name='pageSize']").val();
        alert(pp);
    }
})