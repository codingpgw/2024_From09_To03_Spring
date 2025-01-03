<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd_HH:mm:ss" /></c:set> 

<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="${CP}/resources/images/favicon.ico" type="image/x-icon" >
<link rel="stylesheet" href="${CP}/resources/assets/css/simplemde.min.css">
<title>게시글 등록</title>
<link rel="stylesheet" href="${CP}/resources/assets/css/board/form.css?date=${sysDate}">
<link rel="stylesheet" href="/ehr/resources/assets/css/main/main.css?date=${sysDate}">

<script src="${CP}/resources/assets/js/simplemde.min.js"></script>
<script src="${CP}/resources/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/resources/assets/js/cmn/common.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {
      console.log('DOMContentLoaded');
      
      const uploadButton = document.querySelector("#uploadBtn");
      console.log(uploadButton);
      const fileInput = document.querySelector("#file");
      
      uploadButton.addEventListener("click",function(event){
          console.log("uploadButton click");
          event.preventDefault();
          
          //form 생성
          let formData = new FormData();
          
          const files = fileInput.files;
          
          
          for(let i=0;i<files.length;i++){
        	  formData.append('uploadfile',files[i]);  
        	  console.log("files:{}",files[i]);
          }
          
          $.ajax({
              type: "POST",
              url: "/ehr/file/asyncMultiUpload.do",
              processData: false,
              contentType: false,
              async: true,
              dataType: "json",
              data: formData,
              success: function(response) {
                  console.log("success response:" + response);
                  
                  let target = document.querySelector("#tableTbody");
                  
                  let listData = "";
                  
                  response.forEach(function(fileVO){
                	  console.log("fileVO.orgFileName:{}",fileVO.orgFileName);
                	  
                	  listData += "<tr>";
                	  listData += "    <td>"+fileVO.orgFileName+"</td>";
                	  listData += "    <td>"+fileVO.savePath+"</td>";
                	  listData += "    <td>"+fileVO.fileSize+"</td>";
                	  listData += "    <td>"+fileVO.extension+"</td>";
                	  listData += "    <td style='display:none;'>"+fileVO.saveFileName+"</td>";
                	  listData += "</tr>";
                  });
                  
                  //target.empty();
                  target.append(listData);
                  
              },
              error: function(response) {
                  console.log("error:" + response);
              }
          });//-------ajax end
          
      });
      
});
</script>
</head>
<body>
  <div class="form-container">
    <h2>파일 업로드:${hello}</h2>  
    <hr class="title-underline"/>
    
      <form action="#"  method="post" enctype="multipart/form-data">
          <div class="form-group">
              <label for="file">파일</label>
              <input type="file" name="file" id="file" multiple="multiple">
          </div>
          
          <div class="form-group">
              <label for="uploadBtn">업로드</label>
              <input type="submit" id="uploadBtn" value="업로드" > 
          </div>   
      </form>
      <!--// form area -->
      
      <h2>upload file</h2>
      <table id="fileList">
        <thead>
            <tr>
                <th>원본 파일명</th>
                <th>저장 경로</th>
                <th>파일 크기</th>
                <th>확장자</th>
                <th style="display: none;">저장 파일명</th>
            </tr>
        </thead>
        <tbody id="tableTbody">
            
        </tbody>
      </table>
  </div>
 </body>
</html>