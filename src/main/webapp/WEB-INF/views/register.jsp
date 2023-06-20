<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <style>
        .container {
            width: 800px;
            margin: 0 auto;
            margin-top: 200px;
        }

        .f-top {
            display: flex;
            flex-direction: column;
        }

        input {
            text-align: center;
            font-size: 28px;
            width: 100%;
            height: 50px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="" method="post">
        <div class="f-top">
            <input type="text" name="userId" placeholder="아이디">
            <input type="password" name="password" placeholder="비밀번호">
            <input type="email" name="email" placeholder="이메일">
            <input type="text" name="name" placeholder="이름">
            <input type="text" name="nick" placeholder="닉네임">
            <input type="tel" name="tel" placeholder="전화번호">
            <input type="text" name="birth" placeholder="주민등록번호">
        </div>

        <button type="submit">회원가입</button>
    </form>
</div>

</body>
</html>
