
    $(document).ready(function () {
        $('#summernote').summernote({
            width: 700, height: 400,
            minHeight: null, maxHeight: null,
            toolbar: [
                ['fontsize', ['fontsize']],
                ['style', ['bold']],
                ['insert', ['picture']]
            ],
            fontSizes: ['10', '20', '30', '40', '50', '60', '70', '80', '90', '100'],
            callbacks: {
                onImageUpload: function (files, editor, welEditable) { //이미지 첨부
                    for (let i = files.length - 1; i >= 0; i--) { // 다중 업로드
                        uploadSummernoteImageFile(files[i], this);
                    }
                }
            }

        });
    });

    //글쓰기 중 사진을 넣을때 저장시키고 불러오는 함수
    function uploadSummernoteImageFile(file, el) {
        let formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url: "/uploadSummernoteImageFile",	//Controller 요청 주소
            type: "POST",
            enctype: 'multipart/form-data',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: (data) => {
                $(el).summernote('editor.insertImage', data.url);
            }
        });
    }

    document.getElementById("submitBtn").addEventListener("click", function () {
        // 글작성올리기전 검증(빈칸)후에 비동기로 db에 전송
        let titlt = $('input[name="title"]').val(); // 제목
        let destination = $('input[name="destination"]').val(); // 여행 구분
        let planType = $('input[name="planType"]').val(); // 여행 목적지
        let startDay = $('input[name="startDay"]').val(); //여행시작
        let endDay = $('input[name="endDay"]').val(); 	  //여행끝
        let personnel = $('input[name="personnel"]').val(); //인원
        let finishDate = $('input[name="finishDate"]').val(); //여행모집 마감
        let content = $('textarea[name="content"]').innerHTML;

        if (titlt === "") {
            alert("제목을 입력해주세요")
            return false;
        } else if (destination === "") {
            alert("여행지를 선택해주세요")
            return false;
        } else if (startDay === "") {
            alert("시작일을 입력해주세요")
            return false;
        } else if (endDay === "") {
            alert("종료일을 입력해주세요")
            return false;
        } else if (personnel === "") {
            alert("인원수를 입력해주세요")
            return false;
        } else if (finishDate === "") {
            alert("마감일을 입력해주세요")
            return false;
        } else if (content === "") {
            alert("본문을 입력해주세요")
            return false;
        } else {

            let content = $('#summernote').summernote('code'); //에디터 내용가져오기

            let imageFileNameList = []; //내용 중 이미지 태그를 추출 후 리스트로 만들기
            $(content).find('img').each(function () {
                let imageUrl = $(this).attr('src');
                let fileName = imageUrl.split('/').pop();
                imageFileNameList.push(fileName);
            });

            $.ajax({
                type: "POST",	//요청 method
                contentType: "application/json; charset=utf-8",	//json 포맷 utf-8 내용으로 통신하겠다
                url: "/joinmaking_process", //어디 경로로 요청할건지
                data: JSON.stringify({	//JSON string 으로 변환
                    title: titlt,
                    destination: destination,
                    startDay: startDay,
                    planType: planType,
                    endDay: endDay,
                    personnel: personnel,
                    content: content,
                    finishDate: finishDate,
                    imageFileNameList: imageFileNameList
                }),	//파라미터로 같이 담아서 보낼 것들
                success: (data) => {
                    window.location.href = "/join_view";
                    return true;
                },	//요청에 대해 성공한 경우 수행할 내용
                error: () => {
                    alert('실행 오류');
                }	//요청이 실패,오류난 경우 수행할 내용

            });
        }
    });
    

    $(document).ready(function () {
        $('#summernote').summernote({
            width: 700, height: 400,
            minHeight: null, maxHeight: null,
            toolbar: [
                ['fontsize', ['fontsize']],
                ['style', ['bold']],
                ['insert', ['picture']]
            ],
            fontSizes: ['10', '20', '30', '40', '50', '60', '70', '80', '90', '100'],
            callbacks: {
                onImageUpload: function (files, editor, welEditable) { //이미지 첨부
                    for (let i = files.length - 1; i >= 0; i--) { // 다중 업로드
                        uploadSummernoteImageFile(files[i], this);
                    }
                }
            }
        });

        // 시작일과 종료일 비교하여 지나간 날짜에는 비활성화 처리
        $('input[name="startDay"]').on('change', function () {
            const startDate = new Date($(this).val());
            const endDate = new Date($('input[name="endDay"]').val());

            if (startDate > endDate) {
                $('input[name="endDay"]').val('');
            }

            // 비활성화 처리
            const currentDate = new Date();
            if (startDate < currentDate) {
                $('input[name="endDay"]').prop('disabled', true);
            } else {
                $('input[name="endDay"]').prop('disabled', false);
            }
        });

        $('input[name="endDay"]').on('change', function () {
            const startDate = new Date($('input[name="startDay"]').val());
            const endDate = new Date($(this).val());

            if (startDate > endDate) {
                $(this).val('');
            }
        });

        // 현재 날짜 이전의 날짜 비활성화 처리
        const currentDate = new Date().toISOString().split('T')[0];
        $('input[name="startDay"]').attr('min', currentDate);
        $('input[name="endDay"]').attr('min', currentDate);
    });
