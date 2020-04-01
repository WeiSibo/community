/*
    提交回复
 */
function post() {
    var postId = $("#post_id").val();
    var content = $("#comment_content").val();
    comment2target(postId, 1, content);
}

function comment2target(targetId, type, content) {
    if(!content){
        alert("回复内容不能为空！");
        return;
    }
    $.ajax(
        {
            type: "POST",
            url: "/comment",
            contentType: 'application/json',
            data: JSON.stringify(
                {
                    "parentId": targetId,
                    "content": content,
                    "type": type
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
            dataType: "json"
        }
    )
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    comment2target(commentId, 2, content);
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
