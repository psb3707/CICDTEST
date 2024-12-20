const commentButton = document.getElementById("comment-btn")

if(commentButton){
    commentButton.addEventListener('click',event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/comments`,{
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                post_id: document.getElementById('post-id').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('댓글이 작성되었습니다.');
                window.location.reload();
            });
    });
}