<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>AJAX Submit</title>
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<h1>텍스트를 서버로 제출하기</h1>

<%--<div id="result"></div>--%>
<pre id="result"></pre>

<script>
    $(document).ready(function () {
        // 버튼 클릭 시 AJAX 요청 보내기
        $("#submit-button").click(function () {
            var requestBody = $("#text-input").val();
            console.log("requestbody:" + requestBody);
            let datas = requestBody
            // let datas = {"one":{"two":{"three":{"message":["안녕","<script>alert('XSS Attack!')<\/script>","잘가"]}}}}

            <%--requestBody = {--%>
            <%--    "test" : {"test" : {"test" : [<script>alert('XSS Attack!')</script>,2,3,4]}}--%>
            // }
            // console.log(JSON.stringify(requestBody));
            console.log(datas)
            $.ajax({
                type: "POST", // POST 요청
                url: "/submit-body", // 요청을 보낼 경로
                contentType: "application/json", // 요청 데이터 타입 설정
                // data: JSON.stringify(requestBody), // 요청 데이터를 JSON 문자열로 변환
                // data: JSON.stringify(datas), // 요청 데이터를 JSON 문자열로 변환
                data: datas, // 요청 데이터를 JSON 문자열로 변환
                success: function (response) {
                    $("#result").text(response.data)
                    console.log(response);
                    // 서버로부터의 응답을 처리
                    // $("#result").html(response);

                    // console.log(JSON.parse(response.userInput));
                    // console.log(JSON.parse(response.userInput).test);
                    // console.log(JSON.parse(response.userInput).test.test);
                    // res = JSON.parse(response.userInput);
                    // console.log("ddddd")
                    // // console.log(res.test);
                    // console.log(res);
                    // // console.log(res.test.test[0]);
                    // // var dddd = res.test.test.test[0];
                    // // $("#result").html(response.userInput);
                    // console.log(res.one.two.three.message[0]);
                    // console.log(res.one.two.three.message[1]);
                    // console.log(res.one.two.three.message[2]);
                    // $("#result").html(res.one.two.three.message[1]);
                    // $("#result").text(res.test.test);
                    // $("#result").text(response.one.two.three.message[1]);
                    // $("#result").text(JSON.parse(response));
                    // $("#result").text(JSON.stringify(response));
                    console.log(response.message);
                    $("#result").text(response.message);
                    <%--{"one":{"two":{"three":{"message":"안녕, <script>alert('XSS Attack!')</script>,잘가"}}}}--%>
                    // {"one":{"two":{"three":{"message":["안녕","<script>alert('XSS Attack!')<script>","잘가"]}}}}

                }
            });
        });
    });
</script>

<input type="text" id="text-input" placeholder="텍스트를 입력하세요">
<button id="submit-button">제출</button>

</body>
</html>
