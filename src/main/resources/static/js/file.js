let count=0;
let flag=true;

$("#fileAdd").click(function(){
    if(flag){
        let size = $("#fileBox").attr("data-file-size");
        if(size==undefined){
            size=0;
        }
        count=size;
        flag=false;
    }

    if(count<5) {
        let add='<div id="file'+count+'">';
        add=add+'<input type="file" name="files">';
;       add=add+'<button type="button" class="del">X</button>';
        add=add+'</div>';
        $("#fileBox").append(add);
        count++;
    }else{
        alert("최대 5개만 가능")
    }
})

//이벤트 위임
//$("부모선택자").on("이벤트명", "자식선택자", callback function)
$("#fileBox").on("click", ".del", function(){
    $(this).parent().remove();
    count--;
})



//글수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB, HDD에 파일 삭제
    let check = confirm("되돌릴수 음슴 ㄹㅇ삭제?");
    if(flag){
        let size = $("#fileBox").attr("data-file-size");
        count=size;
        flag=false;
    }

    if(check){
        let fileNum = $(this).attr("data-file-num");
        console.log("before result this : ", $(this));
        const btn = $(this);
        
        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("Result : ",result)
                console.log("after result this : ", $(this));
                btn.parent().remove();
                count--;
            },
            error:function(){
                console.log("Error 발생");
            }
        })
    }
})
