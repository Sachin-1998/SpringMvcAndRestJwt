console.log("this is script file")

const toggleSidebar = () => {
    if ($(".sidebar").is(":visible"))
    {
        //true
        //show karna hai
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
    }
    else
    {
        //false
        //hide karna hai
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
    }
};
