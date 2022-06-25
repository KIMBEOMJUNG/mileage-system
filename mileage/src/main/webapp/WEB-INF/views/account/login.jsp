<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Login</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">TRIPLE CLUB MILEAGE</h1>
                                    </div>
                                    <form class="user">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="InputNickname"
                                                placeholder="닉네임을 입력해주세요.">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="InputPw"
                                                placeholder="암호를 입력해주세요.">
                                        </div>
                                        <a href="javascript:login();" class="btn btn-primary btn-user btn-block">
                                            Login
                                        </a>
                                        <hr>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <script>
        async function login() {
            if($("#InputNickname").val().length <= 0){
                alert("닉네임을 입력해주세요.");
                return;
            }
            if($("#InputPw").val().length <= 0){
                alert("암호를 입력해주세요.");
                return;
            }
            let data = {userId: $("#InputNickname").val(), pw: $("#InputPw").val()};
            try {
                let result = await axios({ url: '/account/login', method: 'post', data});
                if(result.data == null || result.data == '') {
                    alert("존재하지 않는 회원입니다.");
                } else {                    
                    location.href='/place/list'
                }
            } catch (error) {
                alert("로그인중 문제가 발생했습니다. 다시 시도해주세요.");
            }
        }
    </script>

</body>

</html>