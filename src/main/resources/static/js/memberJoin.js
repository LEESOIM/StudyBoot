console.log("memberJoin");

//약관 동의 Check
$("#all").click(function(e){
    let ch = $(this).prop("checked"); //GET:prop("속성명")
    $(".check").prop("checked", ch); //SET:prop("속성명", "값")
})

$(".check").click(function(){

    let flag=true;
                        // index번호, 배열에서 꺼내온 Element       
    $(".check").each(function(index, items){
        let ch = $(items).prop("checked");
        if(!ch){
            flag=false;
        }
        console.log(ch);
    })

    $("#all").prop("checked", flag);
})

//id, pw, pw2, name, email
let results = [false, false, false, false, false]

//ID Check
$("#id").blur(function(){
    let id = $("#id").val()
    let result = nullCheck(id.trim(), "#idCheck", "아이디");
    results[0]=result;
    // if(!result){
    //     $("#idCheck").html("아이디를 입력해주세요")
    // }else{
    //     $("#idCheck").html("")
    // }

    $.get("./idCheck?id="+id, function(data){
        console.log("data : ",data);
        if(data==0){
            $("#idCheck").html("⭕사용가능한 아이디입니다");
            result[0]=true;
        }else{
            $("#idCheck").html("❌중복된 아이디입니다");
            $("#id").val("");
            result[0]=false;
        }
    })
})


//PW Check
$("#pw").blur(function(){
    let result = nullCheck($(this).val().trim(), "#pwCheck", "비밀번호");
    results[1]=result;
})

// $("#pw").change(function(){
//     $("#pw2").val("")
//     result[2]=false;
//     $("#pw2Check").html("")
// })

$("#pw2").blur(function(){
    let result = equals($("#pw").val(), $("#pw2").val())

    if(!result){
         $("#pw2Check").html("비밀번호가 다릅니다")
    }else{
        $("#pw2Check").html("")

        $("#pw").change(function(){
            if(equals($("#pw").val(), $("#pw2").val())){
                $("#pw2Check").html("");
            }else{
                $("#pw2Check").html("비밀번호가 다릅니다");
            }
        })
    }
    results[2]=result;
})


//Name Check
$("#name").blur(function(){
    let result = nullCheck($(this).val().trim(), "#nameCheck", "이름");
    results[3]=result;
})


//Email Check
$("#email").blur(function(){
    let result = nullCheck($(this).val().trim(), "#emailCheck", "이메일");
    results[4]=result;
})


$("#joinBtn").click(function(){

    if(results.includes(false)){//false가 한개라도 포함되어있으면
        alert("입력이 덜 됐으요");
    }else{
        //$("#joinForm").submit();
        alert("전송완료");
    }

    // let c = true;
    // $.each(results, function(index, item){
    //     if(!item){ //한개라도 false라면
    //         alert("입력이 덜 됐으요");
    //         c=false;
    //         return false;
    //     }
    // })

    // //event 강제 실행
    // if(c){
    //     $("#joinForm").submit();
    // }
})