<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Blank</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <c:import url="/frame/side.do" />
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <c:import url="/frame/top.do" />
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">리뷰 수정</h1>

                    <div class="card shadow mb-4">
                        <!-- <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">List</h6>
                        </div> -->
                        ${placeId}
                        <div class="card-body">
                            <form id="form">
                                <div class="form-group">
                                    <textarea class="form-control form-control-user" id="InputContext" style="border-radius: 0rem;" placeholder="내용을 입력해주세요..">${item.content}</textarea>
                                </div>
                                <div class="form-group">
                                    <div class="custom-control custom-checkbox small">
                                        <input type="checkbox" class="custom-control-input" id="imgCheck">
                                        <label class="custom-control-label" for="imgCheck">사진 올리기 테스트용(체크시 임의의 이미지id값 삽입)</label>
                                    </div>
                                </div>
                                <hr>
                                <a href="javascript:submit();" class="btn btn-primary btn-user btn-block">
                                    리뷰 수정하기
                                </a>
                            </form>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript-->
    <script src="/vendor/jquery/jquery.min.js"></script>
    <script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/js/sb-admin-2.min.js"></script>

    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>

    <script>
        async function checkboxState() {
            let imgData = '${item.attachedPhotoIds}'.replace("[","").replace("]","");
            if(imgData != null && imgData != undefined && imgData != ''){
                const imgDataArr = imgData.split(','); 
                if(imgDataArr.length > 0){
                    $("input:checkbox[id='imgCheck']").prop("checked", true);
                }
            }
        }
        async function submit() {
            let tempImgId = [];
            if($("#InputContext").val().length <= 0){
                alert("내용을 입력해주세요.");
                return;
            }
            if($('input:checkbox[id="imgCheck"]').is(":checked") == true){
                tempImgId.push("photoTestIds-value-사진테스트 수정 입니다");
            }
            let data = {
                type: "REVIEW",
                action: "MOD",
                reviewId: "${item.reviewId}",
                content: $("#InputContext").val(),
                attachedPhotoIds: tempImgId,
                userId: "${id}",
                placeId: "${item.placeId}"
            };
            try {
                let result = await axios({ url: '/events', method: 'post', data });
                alert("리뷰 올리기가 완료되었습니다.");
                location.href='/place/detail?&id='+"${item.placeId}";
            } catch (error) {
                alert("저장중 문제가 발생했습니다. 다시 시도해주세요.");
            }
        }

        checkboxState();
    </script>

</body>

</html>