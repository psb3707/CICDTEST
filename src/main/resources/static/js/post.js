const deleteButton = document.getElementById('delete-btn');
const modifyButton = document.getElementById('modify-btn');
const createButton = document.getElementById('create-btn');
const createNewButton = document.getElementById('create-btn-empty');
const likeBtn = document.getElementById('like-btn');

if(likeBtn){
    likeBtn.addEventListener('click', event => {
        console.log("좋아요 버튼");
        let id = document.getElementById('post-id').value;

        fetch(`/api/posts/likes/${id}`,{
            method: "PUT"
        }).then(()=>{
            alert('좋아요를 눌렀습니다.');
            window.location.reload();
        })
    })
}

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('post-id').value;
        fetch(`/api/posts/${id}`,{
            method: 'DELETE'
        }).then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/posts');
        });
    });
}

if (modifyButton) {
    modifyButton.addEventListener('click',event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/posts/${id}`,{
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(() => {
                alert('수정이 완료되었습니다.');
                location.replace(`/posts/${id}`);
            });
    });
}

if (createButton) {
    createButton.addEventListener('click', event =>{

        fetch("/api/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
                tags: document.getElementById("tag").value.split(',').map(tag => tag.trim())
            }),
        }).then(() => {
            alert("등록이 완료되었습니다.");
            location.replace("/posts");
        });
    });
}

if (createNewButton) {
    createNewButton.addEventListener('click', event =>{
        fetch("/api/posts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("등록이 완료되었습니다.");
            location.replace("/posts");
        });
    });
}