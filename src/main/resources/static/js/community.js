/*
    提交回复
 */
function post() {
    var postId = $("#post_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("回复内容不能为空！");
        return;
    }
    $.ajax(
        {
            type: "POST",
            url: "/comment",
            data: JSON.stringify(
                {
                    "parentId": postId,
                    "content": content,
                    "type":1
                }
            ),
            success: function(response) {
                if(response.code == 200){
                    window.location.reload();
                    // $("#comment_section").hide();
                }else{
                    alert(response.message);
                }
            },
            dataType: "json",
            contentType: 'application/json'
        }
    )
}
/*
    展开二级回复
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    if(!comments.hasClass("in")){
        comments.addClass("in");
        e.classList.add("active");
    }else{
        comments.removeClass("in");
        e.classList.remove("active");
    }

}
