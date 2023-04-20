const loginWriter = $('#writer').val();

$('[name=commentInsertBtn]').click(function () { // 댓글 등록 버튼 클릭시

    let insertData = $('[name=commentInsertForm]').serialize(); // commentInsertForm의 내용을 가져옴
    commentInsert(insertData);
});

// 댓글 리스트
function commentList() {

    $.ajax({
        url: "/main/comments",
        type: "GET",
        contentType: 'application/json',

        success: function (data) {

            let commentList = JSON.parse(data);
            console.log(commentList);

            let $table = $("#commentTable tbody");
            $table.html("");

            for (let idx in commentList) {

                let tableTd = '<tr>';
                let deleteBtn = '<p class="deleteBtn" onclick="commentDelete(' + commentList[idx].commentNo + ',\'' + commentList[idx].writer + '\');">' + '삭제</p>';

                tableTd += '<td class="writer-regDate">';
                tableTd += '<p class="writer">' + commentList[idx].writer + '</p>';
                tableTd += '<p class="regDate">' + commentList[idx].regDate + '</p>';
                tableTd += '<td class="content">' + commentList[idx].content + '</td>';
                tableTd += '<td class="commentLike-delete">';
                tableTd += '<p onclick="commentLike(' + commentList[idx].commentNo + ');">' + '좋아요 : ' + commentList[idx].commentLike + '</p>';

                if (loginWriter === commentList[idx].writer) {

                    tableTd += deleteBtn;
                }

                $table.append(tableTd);

            }
        },
        error: function () {

            alert('오류!')
        }

    });
}

// 댓글 등록
function commentInsert(insertData) {
    $.ajax({
        url: '/main/commentInsert',
        type: 'POST',
        data: insertData,

        success: function () {
            commentList();
            $('.content').val(''); // 댓글 등록 성공시 foam 내용 초기화
        },

        error: function (error) {
            console.log(error);
        }
    });
}

$(document).ready(function () {
    commentList(); // 페이지 로딩시 댓글 목록 출력
});

// 댓글 삭제
function commentDelete(commentNo, writer) {

    if (loginWriter == writer) {

        $.ajax({

            url: "/main/comment/" + commentNo,
            type: "POST",
            success: function () {

                alert('댓글이 삭제 되었습니다!');
                commentList();
            }, error: function () {

                alert('본인이 작성한 댓글만 지울 수 있습니다.')
            }
        });
    }
}

function commentLike(commentNo) {

    $.ajax({

        url: "/main/commentLike/" + commentNo,
        type: "POST",
        success: function () {

            alert('좋아요 성공!');
            commentList();
        },
        error: function () {

            alert('이미 좋아요를 누르셨습니다.');
        }
    });
}