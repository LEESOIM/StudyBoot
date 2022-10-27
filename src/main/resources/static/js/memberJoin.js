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


//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡJqueryAjaxㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    //$.get("URL?param1=값&param2=값2", function(응답 Data를 받는 변수명){})
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



$("#test2").click(function(){
    let id="abcd";
    $.ajax({
        type:"GET",
        url:"idCheck",
        data:{
            id:id
        },
        success:function(data){
            console.log("Data : ",data);
        },
        error:function(xhr,status,error){
            console.log("Xhr : ",xhr);
            console.log("Status : ",status);
            console.log("Error : ",error);
        }
    });
})

$("#test3").click(function(){
    let id="1234";
    let name="iu";
    let ar = [1,2,3];
    $.ajax({
        type:"POST",
        url:"test",
        traditional:true, //배열을 전송할때 사용, true
        data:{
            id:id,
            name:name,
            ar:ar
        },
        success:function(reulst){
            console.log("reulst : ",reulst);
        }
    })
})


let count=3;
$("#s1Add").click(function(){
    
    //추가
    let add = '<option id="abc'+count+'">'+count+'</option>';
    $("#s1").append(add);
    count++;

    //삭제
    //$("#s1Add").remove(); //선택자 포함, 하위 자식들 까지 모두 삭제
})

$("#s1").click(function(){
    $("#s1").empty(); //선택자 제외, 자식들만 삭제
})



//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡJqueryAjaxㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ



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



$("#test").click(function(){
    let id="123";
    let name="soim";

    //$.post("URL", {param1:값1, param2:값2,...}, callback function)
    $.post("test",{
        id:id,
        name:name
    }, function(result){
        //result = JSON.parse(result);
        //"{키:값}"
        console.log("Result : ", result);
        console.log("Name : ", result.name);
    })
})


